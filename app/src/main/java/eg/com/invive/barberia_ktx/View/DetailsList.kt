package eg.com.invive.barberia_ktx.View

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import eg.com.invive.barberia_ktx.Adapters.DetailsRecyclerCardViewAdapter
import eg.com.invive.barberia_ktx.Interface.POJOBase
import eg.com.invive.barberia_ktx.POJO.ServiceInfo
import eg.com.invive.barberia_ktx.R
import eg.com.invive.barberia_ktx.Utils.DetailsItemDecoration
import eg.com.invive.barberia_ktx.Utils.initVerticalList
import kotlinx.android.synthetic.main.fragment_details_list.*


class DetailsList(val dataList: MutableList<POJOBase>,val typeView:Int) : Fragment(R.layout.fragment_details_list) {




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            detailsRecycler.initVerticalList(DetailsRecyclerCardViewAdapter(requireContext(),dataList,typeView),DetailsItemDecoration(resources.getDimensionPixelSize(R.dimen._70sdp)))


    }

}