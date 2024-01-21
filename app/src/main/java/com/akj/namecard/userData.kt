package com.akj.namecard

import android.os.Parcelable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
     var photo: String = "",
     var name: String = "홍길동",
     var age: Int = 0,
     var birth: String = "1990-01-01",
     var email: String = "example@gmail.com",
     var moreInform : String = ""
) : Parcelable


class MyViewModel : ViewModel() {
    private var userData by mutableStateOf(UserData())

    fun setPhoto(newPhoto : String){ userData = userData.copy(photo = newPhoto) }
    fun getPhoto() : String { return userData.photo }

    fun setName(newName : String){ userData = userData.copy(name = newName) }
    fun getName() : String { return userData.name }

    fun setAge(newAge : Int){ userData= userData.copy(age = newAge) }
    fun getAge() : Int { return userData.age }

    fun setBirth(newBirth : String){ userData = userData.copy(birth = newBirth) }
    fun getBirth() : String { return userData.birth }

    fun setEmail(newEmail : String){ userData = userData.copy(email = newEmail) }
    fun getEmail() : String { return userData.email }

    fun setInform(newInform : String){ userData = userData.copy(moreInform = newInform) }
    fun getInform() : String { return userData.moreInform }

}
