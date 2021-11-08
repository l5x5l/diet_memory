package com.example.dietmemory.add.exercise

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dietmemory.R
import com.example.dietmemory.add.AddActivity
import com.example.dietmemory.add.exercise.adapter.exerciseAdapter
import com.example.dietmemory.config.BaseFragment
import com.example.dietmemory.databinding.FragmentAddExerciseBinding
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

class ExerciseFragment : BaseFragment<FragmentAddExerciseBinding>(FragmentAddExerciseBinding::bind, R.layout.fragment_add_exercise), ExerciseContract.View {

    private val presenter = ExercisePresenter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.takeView(this)

        setBtn()
        setRecyclerView()
        setText()
    }

    override fun onDestroyView() {
        presenter.dropView()
        super.onDestroyView()
    }

    override fun applyPostExer(isSuccess: Boolean) {
        if (isSuccess) {
            (activity as AddActivity).drop(2)
        } else {
            Toast.makeText(activity as AddActivity, "데이터 추가에 실패했습니다 잠시 후에 실행해주세요", Toast.LENGTH_SHORT).show()
        }
    }

    override fun applyTimeChange(time: Int, cal: Int) {
        binding.tvTimeValue.text = getString(R.string.add_minute, time)
        binding.tvTotalConsumptionCalorieValue.text = (cal * time / 10).toString()
    }

    override fun applyExerChange(exName: String, time: Int, cal: Int) {
        binding.tvChooseExercise.text = exName
        binding.tvTimeValue.text = getString(R.string.add_minute, time)
        binding.tvTotalConsumptionCalorieValue.text = (cal * time / 10).toString()
    }

    // set view
    private fun setRecyclerView(){
        binding.rvExercise.layoutManager = LinearLayoutManager(activity as AddActivity, LinearLayoutManager.VERTICAL, false)
        binding.rvExercise.adapter = exerciseAdapter(activity as AddActivity, ::setExercise)
    }

    private fun setBtn() {
        binding.btnTimeDecrease.setOnClickListener {
            presenter.updateTime(false)
        }

        binding.btnTimeIncrease.setOnClickListener {
            presenter.updateTime(true)
        }

        binding.btnGallery.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
            intent.type = "image/*"
            getImageFromGallery.launch(intent)
        }

        binding.btnPhoto.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            getImageFromCamera.launch(intent)
        }

        binding.tvChooseExercise.setOnClickListener {
            binding.rvExercise.visibility = View.VISIBLE
        }

        binding.btnSave.setOnClickListener {
            saveToFirebase()
        }
    }

    private fun setText(){
        binding.tvTimeValue.text = getString(R.string.add_minute, 0)
        binding.tvTotalConsumptionCalorieValue.text = "0"
    }


    // firebase
    private fun saveToFirebase() {
        val storage : FirebaseStorage = FirebaseStorage.getInstance()
        val fileName = "IMAGE_${SimpleDateFormat("yyyymmdd_HHmmss", Locale.getDefault()).format(Date())}_.png"
        val imageRef = storage.reference.child("images/").child(fileName)

        val imageBitmap = (binding.ivPhoto.drawable as BitmapDrawable).bitmap

        val baos = ByteArrayOutputStream()
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val uploadTask = imageRef.putBytes(data)
        (activity as AddActivity).showLoadingDialog()
        uploadTask.addOnSuccessListener {
            imageRef.downloadUrl.addOnSuccessListener {
                presenter.tryPostExer(it.toString())
                (activity as AddActivity).dismissLoadingDialog()
            }
        }.addOnFailureListener {
            Log.d("image upload", "failure")
            (activity as AddActivity).dismissLoadingDialog()
        }
    }

    private val getImageFromGallery = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result : ActivityResult ->
        binding.ivPhoto.setImageURI(result.data?.data)
    }

    private val getImageFromCamera = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val extras = result.data?.extras
        if (extras != null) {
            val imageBitmap = extras.get("data") as Bitmap
            binding.ivPhoto.setImageBitmap(imageBitmap)
        }
    }

    // function which call in adapter
    private fun setExercise(name : String, cal : Int){
        binding.rvExercise.visibility = View.GONE
        presenter.changeExercise(name, cal)
    }

}