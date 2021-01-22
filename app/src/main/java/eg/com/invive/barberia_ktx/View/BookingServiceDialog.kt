package eg.com.invive.barberia_ktx.View

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import eg.com.invive.barberia_ktx.Adapters.ServiceCardViewAdapter
import eg.com.invive.barberia_ktx.POJO.ServiceInfo
import eg.com.invive.barberia_ktx.R
import eg.com.invive.barberia_ktx.Utils.initServiceList
import eg.com.invive.barberia_ktx.Utils.initVerticalList
import eg.com.invive.barberia_ktx.Utils.showMessage
import kotlinx.android.synthetic.main.booking_service_dialog.*
import java.util.*

class BookingServiceDialog:DialogFragment() {

    private val serviceInfoList: MutableList<ServiceInfo> = ArrayList<ServiceInfo>()
    private val serviceCardViewAdapter by lazy { ServiceCardViewAdapter(requireContext(), mutableListOf(),false){dismiss()} }



    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(
                resources.getDimensionPixelSize(R.dimen._250sdp),
                resources.getDimensionPixelSize(R.dimen._450sdp)
            )
        }
    }

 

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.booking_service_dialog, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        initView()

    }


    private fun initView(){

        serviceInfoList.addAll(initServiceList())
        serviceCardViewAdapter.updateServiceList(serviceInfoList)

        serviceRecycler.initVerticalList(serviceCardViewAdapter)

    }

}