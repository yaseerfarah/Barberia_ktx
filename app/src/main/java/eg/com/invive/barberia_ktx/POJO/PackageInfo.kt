package eg.com.invive.barberia_ktx.POJO

import android.os.Parcelable
import eg.com.invive.barberia_ktx.Interface.POJOBase
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PackageInfo(override val id:Int,
                       val name:String,
                       val details:String,
                       val price:Float,
                       val exPrice:Float?,
                       val offer:Int?,
                       val hasOffer:Boolean,
                       val serviceList:List<ServiceInfo> ): POJOBase, Parcelable