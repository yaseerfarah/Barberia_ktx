package eg.com.invive.barberia_ktx.View

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.recyclerview.widget.LinearLayoutManager
import eg.com.invive.barberia_ktx.Adapters.HomeCardViewAdapter
import eg.com.invive.barberia_ktx.POJO.HomeRecyclerData
import eg.com.invive.barberia_ktx.POJO.PlaceInfo
import eg.com.invive.barberia_ktx.R
import eg.com.invive.barberia_ktx.Utils.initHomeRecycler
import eg.com.invive.barberia_ktx.Utils.initPlaceList
import eg.com.invive.barberia_ktx.Utils.initVerticalList
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


class Home : BaseFragment(R.layout.fragment_home) {




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeCardViewAdapter=HomeCardViewAdapter(requireContext(),initHomeRecycler(),navController)
        homeRecycler.initVerticalList(homeCardViewAdapter)


    }

    override fun backHandle(){
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (getMainDrawer().isDrawerOpen()){
                getMainDrawer().closeDrawer()
            }else{
                requireActivity().finish()
            }

        }
    }






}