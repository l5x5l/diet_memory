package com.example.dietmemory.add.food

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.dietmemory.R
import com.example.dietmemory.add.food.models.FoodRecordData
import com.example.dietmemory.config.BaseFragment
import com.example.dietmemory.databinding.FragmentAddFoodBinding
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class FoodFragment : BaseFragment<FragmentAddFoodBinding>(FragmentAddFoodBinding::bind, R.layout.fragment_add_food), FoodContract.View {

    private val presenter = FoodPresenter()
    private var uri : Uri ?= null

    private val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result : ActivityResult ->
        binding.ivPhoto.setImageURI(result.data?.data)
        uri = result.data?.data

        //test
        if (uri != null){
            val storage : FirebaseStorage = FirebaseStorage.getInstance()
            val fileName = "IMAGE_${SimpleDateFormat("yyyymmdd_HHmmss", Locale.getDefault()).format(Date())}_.png"
            val imageRef = storage.reference.child("images/").child(fileName)

            imageRef.putFile(uri!!).addOnSuccessListener {
                Log.d("image upload", "success")
                presenter.tryGetFoodRecord(fileName)
            }.addOnFailureListener{
                Log.d("image upload", "failure")
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.takeView(this)

        binding.ivPhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
            intent.type = "image/*"
            getContent.launch(intent)
        }

        binding.btnSave.setOnClickListener {
            /*if (uri != null){
                val storage : FirebaseStorage = FirebaseStorage.getInstance()
                val fileName = "IMAGE_${SimpleDateFormat("yyyymmdd_HHmmss", Locale.getDefault()).format(Date())}_.png"
                val imageRef = storage.reference.child("images/").child(fileName)
                imageRef.putFile(uri!!).addOnSuccessListener {
                    Log.d("image upload", "success")
                }.addOnFailureListener{
                    Log.d("image upload", "failure")
                }
                presenter.tryGetFoodRecord(fileName)
            }*/
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
}