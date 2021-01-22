package eg.com.invive.barberia_ktx.Adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import eg.com.invive.barberia_ktx.Interface.POJOBase
import eg.com.invive.barberia_ktx.POJO.PlaceInfo
import eg.com.invive.barberia_ktx.View.DetailsList

class DetailsViewPagerAdapter(fragment: Fragment,val placeInfo: PlaceInfo): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int =3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{DetailsList(placeInfo.serviceList as MutableList<POJOBase>,DetailsRecyclerCardViewAdapter.SERVICE_LIST)}
            1->{DetailsList(placeInfo.commentList as MutableList<POJOBase>,DetailsRecyclerCardViewAdapter.COMMENTS_LIST)}
            2->{DetailsList(placeInfo.packageList as MutableList<POJOBase>,DetailsRecyclerCardViewAdapter.PACKAGE_LIST)}
            else -> {DetailsList(placeInfo.serviceList as MutableList<POJOBase>,DetailsRecyclerCardViewAdapter.SERVICE_LIST)}
        }
    }
}