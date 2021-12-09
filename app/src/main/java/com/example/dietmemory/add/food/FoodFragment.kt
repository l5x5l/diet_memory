package com.example.dietmemory.add.food

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dietmemory.R
import com.example.dietmemory.add.AddActivity
import com.example.dietmemory.add.food.adapter.FoodAdapterView
import com.example.dietmemory.add.food.adapter.foodAdapter
import com.example.dietmemory.add.food.models.FoodInfoResponse
import com.example.dietmemory.add.food.models.FoodRecordData
import com.example.dietmemory.config.BaseFragment
import com.example.dietmemory.databinding.FragmentAddFoodBinding
import com.google.android.material.chip.Chip
import com.google.firebase.storage.FirebaseStorage
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class FoodFragment : BaseFragment<FragmentAddFoodBinding>(FragmentAddFoodBinding::bind, R.layout.fragment_add_food), FoodContract.View, FoodAdapterView {

    private val presenter = FoodPresenter()
    private var uri : Uri ?= null
    private var userInput = true

    private val getImageFromGallery = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result : ActivityResult ->
        binding.ivPhoto.setImageURI(result.data?.data)
        uri = result.data?.data

        if (uri != null){
            (activity as AddActivity).showLoadingDialog()
            val storage : FirebaseStorage = FirebaseStorage.getInstance()
            val fileName = "IMAGE_${SimpleDateFormat("yyyymmdd_HHmmss", Locale.getDefault()).format(Date())}_.png"
            val imageRef = storage.reference.child("images/").child(fileName)

            imageRef.putFile(uri!!).addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener{
                    Log.d("image upload", it.toString())
                    presenter.tryGetFoodRecord(it.toString())
                    (activity as AddActivity).dismissLoadingDialog()
                }
                //presenter.tryGetFoodRecord(fileName)
            }.addOnFailureListener{
                Log.d("image upload", "failure")
                (activity as AddActivity).dismissLoadingDialog()
            }
        }
    }

    private val getImageFromCamera = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val extras = result.data?.extras
        if (extras != null) {
            (activity as AddActivity).showLoadingDialog()
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
                    (activity as AddActivity).dismissLoadingDialog()
                }
            }.addOnFailureListener {
                Log.d("image upload", "failure")
                (activity as AddActivity).dismissLoadingDialog()
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

        setEditText()
        setRecyclerView()
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

    override fun applyFoodAuto(foods: ArrayList<String>) {
        (binding.rvFoodList.adapter as foodAdapter).applyData(foods)
    }

    override fun applyFoodInfo(food: FoodInfoResponse) {
        binding.etCalorie.setText(food.food.calo.toString())
        binding.etFat.setText(food.food.fat.toString())
        binding.etCarbohydrate.setText(food.food.carbo.toString())
        binding.etProtein.setText(food.food.protein.toString())
    }

    private fun setEditText(){
        val observableText = Observable.create(ObservableOnSubscribe { emitter : ObservableEmitter<String>? ->
            binding.etFoodName.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (userInput) emitter?.onNext(s.toString())
                    else userInput = true
                }

                override fun afterTextChanged(s: Editable?) {}

            })
        }).debounce(500, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io())

        binding.etFoodName.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                binding.rvFoodList.visibility = View.VISIBLE
            } else{
                binding.rvFoodList.visibility = View.GONE
            }
        }

        observableText.subscribe(object : Observer<String>{
            override fun onSubscribe(d: Disposable?) {}

            override fun onNext(t: String?) {
                if (t != null) presenter.tryGetFoodAuto(t)
            }

            override fun onError(e: Throwable?) {}

            override fun onComplete() {}
        })
    }

    private fun setRecyclerView(){
        binding.rvFoodList.layoutManager = LinearLayoutManager(activity as AddActivity, LinearLayoutManager.VERTICAL, false)
        binding.rvFoodList.adapter = foodAdapter(this)
        //(binding.rvFoodList.adapter as foodAdapter).applyTempData()
        presenter.tryGetFoodAuto("")
    }

    override fun applyFood(food: String) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etFoodName.windowToken, 0)
        userInput = false
        binding.etFoodName.setText(food)
        binding.rvFoodList.visibility = View.GONE
        binding.etFoodName.clearFocus()
        presenter.tryGetFoodInfo(food)
    }
}