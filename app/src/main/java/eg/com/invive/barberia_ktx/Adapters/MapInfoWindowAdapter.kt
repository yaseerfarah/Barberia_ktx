package eg.com.invive.barberia_ktx.Adapters

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import eg.com.invive.barberia_ktx.POJO.PlaceInfo
import eg.com.invive.barberia_ktx.R

class MapInfoWindowAdapter(val context: Context):GoogleMap.InfoWindowAdapter {

    private var infoWindow: View = (context as Activity).layoutInflater
        .inflate(R.layout.map_info_window, null)


    private fun initInfoMap(marker: Marker) {
        val title: TextView = infoWindow.findViewById(R.id.name)
        val status: TextView = infoWindow.findViewById(R.id.status)
        val placeInfo: PlaceInfo = marker.tag as PlaceInfo
        title.text = "\u200e" + placeInfo.name
        val open = "\u200e" + context.resources.getString(R.string.open)
        val close = "\u200e" + context.resources.getString(R.string.close)
        status.text = if (placeInfo.isOpen) open else close
    }


    override fun getInfoContents(p0: Marker?): View? {
        p0?.let {  initInfoMap(it)}
        return infoWindow
    }

    override fun getInfoWindow(p0: Marker?): View? {
       return null
    }
}