package eg.com.invive.kotlin_first.POJO

import com.google.gson.annotations.SerializedName

data class ObjectBaseResponse<out T>(
    @SerializedName("status")
    val status:String,
    @SerializedName("data")
    val objectResponse:T,
    @SerializedName("message")
    val message:String)