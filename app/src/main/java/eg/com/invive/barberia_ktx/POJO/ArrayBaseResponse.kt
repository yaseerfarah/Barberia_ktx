package eg.com.invive.kotlin_first.POJO

import com.google.gson.annotations.SerializedName

data class ArrayBaseResponse<out T>(
    @SerializedName("status")
    val status:String,
    @SerializedName("data")
    val arrayResponse:List<T>,
    @SerializedName("message")
    val message:String)