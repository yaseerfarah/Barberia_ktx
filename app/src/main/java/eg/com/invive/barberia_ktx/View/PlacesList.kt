package eg.com.invive.barberia_ktx.View

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.button.MaterialButtonToggleGroup
import eg.com.invive.barberia_ktx.Adapters.MapInfoWindowAdapter
import eg.com.invive.barberia_ktx.Adapters.PlaceCardViewAdapter
import eg.com.invive.barberia_ktx.POJO.PlaceInfo
import eg.com.invive.barberia_ktx.R
import eg.com.invive.barberia_ktx.Utils.*
import kotlinx.android.synthetic.main.fragment_places_list.*
import kotlinx.android.synthetic.main.map_view.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs


class PlacesList : BaseFragment(R.layout.fragment_places_list) {

    lateinit var googleMap: GoogleMap

   private val placesList by lazy { initPlaceList() }
    private val navigateToDetails:(PlaceInfo)->Unit={ placeInfo->navigate(R.id.action_global_placeDetails,
        bundleOf(PlaceDetails.PLACE_INFO to placeInfo)
    )}
    private  val recyclerListAdapter by lazy { PlaceCardViewAdapter(requireContext(),placesList,PlaceCardViewAdapter.VERTICAL_LIST )
    {placeInfo ->  navigateToDetails(placeInfo)} }
    private  val viewPagerAdapter by lazy { PlaceCardViewAdapter(requireContext(),placesList,PlaceCardViewAdapter.VIEW_PAGE_LIST)
    {placeInfo ->  navigateToDetails(placeInfo)} }
   private val markerList= ArrayList<Marker>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        map.onCreate(savedInstanceState)
        initViews()

    }



    private fun initViews(){
        initMap()
        verticalRecycler.initVerticalList(recyclerListAdapter)
       toggleGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
           if (isChecked){
               when(checkedId){
                   R.id.mapToggle->{mapLayout.showAnimX();verticalRecycler.hideAnimStart()}
                   R.id.listToggle->{mapLayout.hideAnimEnd();verticalRecycler.showAnimX()}
               }
           }

       }

        filter.setOnClickListener { navigate(R.id.action_placesList_to_filter2) }
    }


    private fun initMap(){
        map.getMapAsync {gMap->
            googleMap=gMap
            val mapInfoWindowAdapter = MapInfoWindowAdapter(requireContext())
            googleMap.setInfoWindowAdapter(mapInfoWindowAdapter)
            mapViewPager2.initViewPager2(viewPagerAdapter,markerList,googleMap)
            displayMyLocation()
            initMarkers()
        }
    }


    private fun displayMyLocation() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        googleMap.apply {
            isMyLocationEnabled = true
            uiSettings.isMyLocationButtonEnabled = false
            setOnMarkerClickListener {
                mapViewPager2.currentItem = markerList.indexOf(it)
                return@setOnMarkerClickListener false
            }

        }

    }


    private fun initMarkers() {
        markerList.clear()
        for (i in 0 until placesList.size){
            val markerOptions = MarkerOptions()
            markerOptions.position(placesList[i].location)
            val m = googleMap.addMarker(markerOptions)
            m.tag = placesList[i]
            m.showInfoWindow()
            markerList.add(m)
        }

        markerList[0].showInfoWindow()
        googleMap.  moveCamera(CameraUpdateFactory.newLatLngZoom(markerList[0].position, 15f))

    }









    override fun onStart() {
        map?.onStart()
        super.onStart()
    }

    override fun onResume() {
        map?.onResume()
        super.onResume()
    }

    override fun onPause() {
        map?.onPause()
        super.onPause()
    }

    override fun onStop() {
        map?.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        map?.onDestroy()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        map?.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        map?.onLowMemory()
        super.onLowMemory()
    }



}