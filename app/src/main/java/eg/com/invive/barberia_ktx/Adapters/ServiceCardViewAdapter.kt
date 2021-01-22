package eg.com.invive.barberia_ktx.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import eg.com.invive.barberia_ktx.POJO.ServiceInfo
import eg.com.invive.barberia_ktx.R
import eg.com.invive.barberia_ktx.Utils.BaseDiffUtil
import eg.com.invive.barberia_ktx.Utils.hide
import java.lang.String
import java.util.*

class ServiceCardViewAdapter(val context: Context, var serviceInfoList: MutableList<ServiceInfo>, val closeEnable:Boolean, val onItemClicked: (ServiceInfo) -> Unit):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ServiceHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.service_cardview, parent, false))


    override fun getItemCount(): Int =serviceInfoList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ServiceHolder).bind(context,serviceInfoList[holder.bindingAdapterPosition],closeEnable,onItemClicked)
    }


    fun updateServiceList(serviceInfos: List<ServiceInfo>) {
        val placeInfoBaseDiffUtil: BaseDiffUtil =
            BaseDiffUtil(context, this.serviceInfoList, serviceInfos)
        val diffResult = DiffUtil.calculateDiff(placeInfoBaseDiffUtil)
        this.serviceInfoList.clear()
        this.serviceInfoList.addAll(serviceInfos)
        diffResult.dispatchUpdatesTo(this)
    }

//////////////////////////////////////////////////////////////////////////////////
    class ServiceHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(context: Context,serviceInfo: ServiceInfo,closeEnable: Boolean, onItemClicked: (ServiceInfo) -> Unit) {
            val container = itemView.findViewById<CardView>(R.id.serviceContainer)
            val color = itemView.findViewById<View>(R.id.color)
            val remove = itemView.findViewById<ImageView>(R.id.delete)
            val title = itemView.findViewById<TextView>(R.id.service_title)
            val price = itemView.findViewById<TextView>(R.id.service_price)

            color.setBackgroundColor(serviceInfo.color)
            title.text=serviceInfo.name
            price.text= String.format(
                Locale.US,
                "%.2f",
                serviceInfo.price
            ) + " ج.م"

            if (!closeEnable){
                remove.hide()
                container.setOnClickListener { onItemClicked(serviceInfo) }
            }  else{
                remove.setOnClickListener  { onItemClicked(serviceInfo) }
            }

        }
    }




}