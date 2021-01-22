package eg.com.invive.barberia_ktx.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import eg.com.invive.barberia_ktx.POJO.HomeRecyclerData
import eg.com.invive.barberia_ktx.POJO.PlaceInfo
import eg.com.invive.barberia_ktx.R
import eg.com.invive.barberia_ktx.Utils.initHorizontalList
import eg.com.invive.barberia_ktx.View.PlaceDetails

class HomeCardViewAdapter(val context: Context, val homeRecyclerDataList: MutableList<HomeRecyclerData>, val navController: NavController):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    companion object {
        const val HORIZONTAL_LIST=1
        const val SEARCH_VIEW=2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType== HORIZONTAL_LIST) HorizontalHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_recycler_card, parent, false))
    else SearchHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_search, parent, false))


    override fun getItemCount(): Int =homeRecyclerDataList.size+1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       when (holder){
           is SearchHolder->{holder.bind(context,navController)}
           is HorizontalHolder->{holder.bind(context,homeRecyclerDataList[holder.bindingAdapterPosition-1],navController)}
       }

    }


    override fun getItemViewType(position: Int): Int =
        if (position == 0) SEARCH_VIEW else HORIZONTAL_LIST



    //////////////////////////////////////////////////////////////////////////////
    class HorizontalHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(context: Context, homeRecyclerData: HomeRecyclerData,navController: NavController) {
           val  title = itemView.findViewById<TextView>(R.id.title)
           val more = itemView.findViewById<MaterialButton>(R.id.more)
           val recyclerView = itemView.findViewById<RecyclerView>(R.id.recycler)

            title.text=homeRecyclerData.title
            more.setOnClickListener { navController.navigate(R.id.action_home_to_placesList) }
            val placeCardViewAdapter=PlaceCardViewAdapter(context, homeRecyclerData.placeList,PlaceCardViewAdapter.HORIZONTAL_LIST)
            { placeInfo->navController.navigate(R.id.action_global_placeDetails,
                bundleOf(PlaceDetails.PLACE_INFO to placeInfo))}
            recyclerView.initHorizontalList(placeCardViewAdapter)

        }
    }


    //////////////////////////////////////////////////////////////////////////////////
    class SearchHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(context: Context,navController: NavController) {
           val  searchBar = itemView.findViewById<CardView>(R.id.search)
           val  goMapButton = itemView.findViewById<MaterialButton>(R.id.goMap)
           val mapImage = itemView.findViewById<ImageView>(R.id.imageMap)

            goMapButton.setOnClickListener { navController.navigate(R.id.action_home_to_placesList) }
            searchBar.setOnClickListener {  }

        }


    }




    }