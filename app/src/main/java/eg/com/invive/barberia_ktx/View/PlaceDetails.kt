package eg.com.invive.barberia_ktx.View

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import eg.com.invive.barberia_ktx.Adapters.DetailsViewPagerAdapter
import eg.com.invive.barberia_ktx.Adapters.ImageSliderAdapter
import eg.com.invive.barberia_ktx.R
import eg.com.invive.barberia_ktx.Utils.initPlaceList
import eg.com.invive.barberia_ktx.Utils.navigateToGoogleMap
import kotlinx.android.synthetic.main.fragment_place_details.*
import kotlinx.android.synthetic.main.place_details_bar_content.*


class PlaceDetails : BaseFragment(R.layout.fragment_place_details) {

    companion object{
        const val PLACE_INFO="place_info"
    }

    val placeInfo by lazy { initPlaceList()[0] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }

    private fun initViews(){

        val sliderAdapter=ImageSliderAdapter(requireContext(),placeInfo.imagesList)
        view_pager.apply {
            setSliderAdapter(sliderAdapter)
            indicatorRadius = 3
            setIndicatorAnimation(IndicatorAnimationType.WORM) //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
            setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
            setInfiniteAdapterEnabled(false)
            indicatorSelectedColor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)  resources.getColor(R.color.blue5,null)  else resources.getColor(R.color.blue5)
            indicatorUnselectedColor = Color.GRAY
            scrollTimeInSec = 3
            isAutoCycle = true
            startAutoCycle()
        }
        detailsViewPager2.adapter=DetailsViewPagerAdapter(this, initPlaceList()[0])
        TabLayoutMediator(tabLayout,detailsViewPager2){tab, position ->
            when(position){
                0->{tab.text=resources.getString(R.string.serviceTab)}
                1->{tab.text=resources.getString(R.string.commentsTab)}
                2->{tab.text=resources.getString(R.string.packageTab)}
            }

        }.attach()


        title.text=placeInfo.name
        pro_address.text=placeInfo.address
        val open=resources.getString(R.string.open)
        val close=resources.getString(R.string.close)
        pro_status.text=if (placeInfo.isOpen)  open else close
        vote_number.text="("+placeInfo.voteNumbers.toString()+" "+"تقييم"+")"
        ratingBar.rating=placeInfo.rate

        mapButton.setOnClickListener {
            val intent=Intent()
            intent.navigateToGoogleMap(requireContext(),placeInfo.location)
        }

        booking.setOnClickListener { navigate(R.id.action_placeDetails_to_booking2) }

    }





}