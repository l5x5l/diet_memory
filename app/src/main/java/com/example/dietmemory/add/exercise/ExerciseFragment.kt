package com.example.dietmemory.add.exercise

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.dietmemory.R
import com.example.dietmemory.config.BaseFragment
import com.example.dietmemory.databinding.FragmentAddExerciseBinding

class ExerciseFragment : BaseFragment<FragmentAddExerciseBinding>(FragmentAddExerciseBinding::bind, R.layout.fragment_add_exercise) {

    private val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result : ActivityResult -> binding.ivPhoto.setImageURI(result.data?.data)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivPhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
            intent.type = "image/*"
            getContent.launch(intent)
        }
    }
}