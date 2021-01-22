package eg.com.invive.barberia_ktx.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import eg.com.invive.barberia_ktx.POJO.PlaceInfo
import eg.com.invive.barberia_ktx.R
import eg.com.invive.barberia_ktx.Utils.BaseDiffUtil
import eg.com.invive.barberia_ktx.Utils.loadImage
import eg.com.invive.barberia_ktx.Utils.show

class PlaceCardViewAdapter(
    val context: Context,
    var placeinfoList: MutableList<PlaceInfo>,
    val typeView:Int,
    val onItemClicked: (PlaceInfo) -> Unit):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    companion object {
        const val HORIZONTAL_LIST=1
        const val VERTICAL_LIST=2
        const val VIEW_PAGE_LIST=3
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when(typeView){
            HORIZONTAL_LIST->{PlaceHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_cardview, parent, false))}
            VERTICAL_LIST->{PlaceHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cardview, parent, false))}
            VIEW_PAGE_LIST->{PlaceHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pager_cardview, parent, false))}
            else->{PlaceHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_cardview, parent, false))}
        }


    override fun getItemCount(): Int =placeinfoList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PlaceHolder).bind(context,placeinfoList[holder.bindingAdapterPosition],onItemClicked)
    }


    fun updateServiceList(placeInfos: List<PlaceInfo>) {
        val placeInfoBaseDiffUtil: BaseDiffUtil =
            BaseDiffUtil(context, this.placeinfoList, placeInfos)
        val diffResult = DiffUtil.calculateDiff(placeInfoBaseDiffUtil)
        this.placeinfoList.clear()
        this.placeinfoList.addAll(placeInfos)
        diffResult.dispatchUpdatesTo(this)
    }

//////////////////////////////////////////////////////////////////////////////////
    class PlaceHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(context: Context,placeInfo: PlaceInfo, onItemClicked: (PlaceInfo) -> Unit) {
            val container = itemView.findViewById<CardView>(R.id.card_view)
            val placeImage = itemView.findViewById<ImageView>(R.id.pro_image)
            val title = itemView.findViewById<TextView>(R.id.pro_name)
            val address = itemView.findViewById<TextView>(R.id.pro_address)
            val status = itemView.findViewById<TextView>(R.id.pro_status)
            val voteNumbers = itemView.findViewById<TextView>(R.id.rate_namber)
            val ratingBar = itemView.findViewById<RatingBar>(R.id.ratingBar)
            val progressBar = itemView.findViewById<ProgressBar>(R.id.progress)

            progressBar.show()
            placeImage.loadImage(placeInfo.imagesList[0],null,progressBar)
            title.text=placeInfo.name
            address.text=placeInfo.address
            val open=context.resources.getString(R.string.open)
            val close=context.resources.getString(R.string.close)
            status.text=if (placeInfo.isOpen)  open else close
            voteNumbers.text="("+placeInfo.voteNumbers.toString()+" "+"تقييم"+")"
            ratingBar.rating=placeInfo.rate
            container.findViewById<CardView>(R.id.card_view)
                .setOnClickListener { onItemClicked(placeInfo) }

        }
    }


}