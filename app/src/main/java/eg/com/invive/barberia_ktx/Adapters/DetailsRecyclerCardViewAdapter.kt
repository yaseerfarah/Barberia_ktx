package eg.com.invive.barberia_ktx.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import eg.com.invive.barberia_ktx.Interface.POJOBase
import eg.com.invive.barberia_ktx.POJO.*
import eg.com.invive.barberia_ktx.R
import eg.com.invive.barberia_ktx.Utils.*
import java.lang.String
import java.util.*

class DetailsRecyclerCardViewAdapter(val context: Context, var dataList:MutableList<POJOBase>, val typeView: Int):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    companion object {
        const val COMMENTS_LIST=1
        const val SERVICE_LIST=2
        const val PACKAGE_LIST=3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when(typeView){
            COMMENTS_LIST ->{
                CommentlHolder(
                    LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.comment_list_cardview, parent, false)
                )
            }
            SERVICE_LIST ->{
                ServiceHolder(
                    LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.service_list_cardview, parent, false)
                )
            }
            PACKAGE_LIST ->{
               PackageHolder(
                    LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.package_list_cardview, parent, false)
                )
            }
            else->{CommentlHolder(
                LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.comment_list_cardview, parent, false)
            )}
        }

    override fun getItemCount(): Int =dataList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       when (holder){
           is CommentlHolder->{holder.bind(context,dataList[holder.bindingAdapterPosition] as CommentInfo)}
           is ServiceHolder->{holder.bind(context,dataList[holder.bindingAdapterPosition] as ServiceInfo)}
           is PackageHolder->{holder.bind(context, dataList[holder.bindingAdapterPosition] as PackageInfo)}
       }

    }


    fun updateServiceList(newDataList: List<POJOBase>) {
        val placeInfoBaseDiffUtil: BaseDiffUtil =
            BaseDiffUtil(context, this.dataList, newDataList)
        val diffResult = DiffUtil.calculateDiff(placeInfoBaseDiffUtil)
        this.dataList.clear()
        this.dataList.addAll(newDataList)
        diffResult.dispatchUpdatesTo(this)
    }



    //////////////////////////////////////////////////////////////////////////////
    class CommentlHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(context: Context, commentInfo: CommentInfo) {
           val  name = itemView.findViewById<TextView>(R.id.title)
           val image = itemView.findViewById<ImageView>(R.id.user_image)
           val rate = itemView.findViewById<RatingBar>(R.id.ratingBar)
            val comment = itemView.findViewById<TextView>(R.id.comment)
            val date = itemView.findViewById<TextView>(R.id.comment_time)

            with(commentInfo){
                name.text=this.userName
                image.loadImage(this.imageUrl,null,null)
                rate.rating=this.rate
                comment.text=this.comment
                date.text=this.date
            }


        }
    }


    //////////////////////////////////////////////////////////////////////////////
    class PackageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(context: Context, packageInfo: PackageInfo) {
            val  name = itemView.findViewById<TextView>(R.id.title)
            val details = itemView.findViewById<TextView>(R.id.details)
            val chipGroup = itemView.findViewById<ChipGroup>(R.id.chip_group)
            val exPriceLayout = itemView.findViewById<LinearLayout>(R.id.exPrice_layout)
            val offer = itemView.findViewById<TextView>(R.id.offer)
            val exPrice = itemView.findViewById<TextView>(R.id.exPrice)
            val price = itemView.findViewById<TextView>(R.id.price)

            with(packageInfo){
                name.text=this.name
                details.text=this.details
                if (this.hasOffer){
                    exPriceLayout.show()
                    offer.text=this.offer.toString() + "%"+" خصم"
                    exPrice.text= String.format(Locale.US, "%.2f", this.exPrice) + " ج.م"
                }else{
                    exPriceLayout.hide()
                }
                price.text=String.format(Locale.US, "%.2f", this.price) + " ج.م"
                for (i in this.serviceList.indices){
                    val chip = LayoutInflater.from(context)
                        .inflate(R.layout.service_chip, null, false) as Chip
                    chip.text= this.serviceList[i].name
                    chipGroup.addView(chip)
                }
            }

        }
    }


    //////////////////////////////////////////////////////////////////////////////
    class ServiceHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(context: Context, serviceInfo: ServiceInfo) {
            val  name = itemView.findViewById<TextView>(R.id.title)
            val details = itemView.findViewById<TextView>(R.id.details)
            val exPriceLayout = itemView.findViewById<LinearLayout>(R.id.exPrice_layout)
            val offer = itemView.findViewById<TextView>(R.id.offer)
            val exPrice = itemView.findViewById<TextView>(R.id.exPrice)
            val price = itemView.findViewById<TextView>(R.id.price)

            with(serviceInfo){
                name.text=this.name
                details.text=this.details
                if (this.hasOffer){
                    exPriceLayout.show()
                    offer.text=this.offer.toString() + "%"+" خصم"
                    exPrice.text= String.format(Locale.US, "%.2f", this.exPrice) + " ج.م"
                }else{
                    exPriceLayout.hide()
                }
                price.text=String.format(Locale.US, "%.2f", this.price) + " ج.م"
            }
        }
    }




}