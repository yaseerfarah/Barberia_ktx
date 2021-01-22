package eg.com.invive.barberia_ktx.POJO

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import eg.com.invive.barberia_ktx.Interface.POJOBase
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlaceInfo(override val id:Int,
                     val name:String,
                     val address:String,
                     val location:LatLng,
                     val rate:Float,
                     val voteNumbers:Int,
                     val isOpen:Boolean,
                     val imagesList: List<String>,
                     val serviceList:MutableList<ServiceInfo>,
                     val commentList:MutableList<CommentInfo>,
                     val packageList:MutableList<PackageInfo>):POJOBase, Parcelable