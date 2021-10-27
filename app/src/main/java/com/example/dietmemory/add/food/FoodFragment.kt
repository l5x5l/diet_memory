package com.example.dietmemory.add.food

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.dietmemory.R
import com.example.dietmemory.add.AddActivity
import com.example.dietmemory.add.food.models.FoodRecordData
import com.example.dietmemory.config.BaseFragment
import com.example.dietmemory.databinding.FragmentAddFoodBinding
import com.google.android.material.chip.Chip
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

class FoodFragment : BaseFragment<FragmentAddFoodBinding>(FragmentAddFoodBinding::bind, R.layout.fragment_add_food), FoodContract.View {

    private val presenter = FoodPresenter()
    private var uri : Uri ?= null

    private val getImageFromGallery = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result : ActivityResult ->
        binding.ivPhoto.setImageURI(result.data?.data)
        uri = result.data?.data

        //test
        if (uri != null){
            val storage : FirebaseStorage = FirebaseStorage.getInstance()
            val fileName = "IMAGE_${SimpleDateFormat("yyyymmdd_HHmmss", Locale.getDefault()).format(Date())}_.png"
            val imageRef = storage.reference.child("images/").child(fileName)

            imageRef.putFile(uri!!).addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener{
                    Log.d("image upload", it.toString())
                    presenter.tryGetFoodRecord(it.toString())
                }
                //presenter.tryGetFoodRecord(fileName)
            }.addOnFailureListener{
                Log.d("image upload", "failure")
            }
        }
    }

    private val getImageFromCamera = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val extras = result.data?.extras
        if (extras != null) {
            val storage : FirebaseStorage = FirebaseStorage.getInstance()
            val fileName = "IMAGE_${SimpleDateFormat("yyyymmdd_HHmmss", Locale.getDefault()).format(Date())}_.png"
            val imageRef = storage.reference.child("images/").child(fileName)

            val imageBitmap = extras.get("data") as Bitmap
            binding.ivPhoto.setImageBitmap(imageBitmap)

            val baos = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()

            val uploadTask = imageRef.putBytes(data)
            uploadTask.addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener {
                    Log.d("image upload", it.toString())
                    presenter.tryGetFoodRecord(it.toString())
                }
            }.addOnFailureListener {
                Log.d("image upload", "failure")
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.takeView(this)

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

        binding.btnSave.setOnClickListener {
            var checkIdx = 0
            for (idx in 0 until binding.cgCategory.childCount){
                val chip = binding.cgCategory.getChildAt(idx) as Chip
                if (chip.isChecked){
                    checkIdx = idx
                    break
                }
            }
            presenter.tryPostAddFood(checkIdx, binding.etFoodName.text.toString())
        }
    }

    override fun onDestroyView() {
        presenter.dropView()
        super.onDestroyView()
    }

    override fun applyFoodRecord(food: FoodRecordData) {
        binding.etCalorie.setText(food.calo.toString())
        binding.etFat.setText(food.fat.toString())
        binding.etCarbohydrate.setText(food.carbo.toString())
        binding.etProtein.setText(food.protein.toString())
        binding.etFoodName.setText(food.foodName)
    }

    override fun applyPostAddFood(isSuccess: Boolean) {
        if (isSuccess){
            (activity as AddActivity).drop(1)
        } else {
            Toast.makeText(activity as AddActivity, "데이터 추가에 실패했습니다 잠시 후에 실행해주세요", Toast.LENGTH_SHORT).show()
        }
    }
}