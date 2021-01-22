package eg.com.invive.barberia_ktx.POJO

import android.os.Parcelable
import eg.com.invive.barberia_ktx.Interface.POJOBase
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommentInfo(override val id:Int, val userName:String, val imageUrl:String, val rate:Float, val comment:String, val date:String):
    POJOBase, Parcelable