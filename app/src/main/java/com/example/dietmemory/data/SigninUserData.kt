package com.example.dietmemory.data

import com.example.dietmemory.config.GlobalApplication

data class SigninUserData(var email : String = GlobalApplication.STRING_DEFAULT, var password : String = GlobalApplication.STRING_DEFAULT,
                          var name : String = GlobalApplication.STRING_DEFAULT, var phoneNum : String = GlobalApplication.STRING_DEFAULT,
                          var sex : String = GlobalApplication.STRING_DEFAULT, var height : Int = -1, var weight : Int = -1, var exercise : Int = -1)
