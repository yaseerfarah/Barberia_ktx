package eg.com.invive.barberia_ktx.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.smarteist.autoimageslider.SliderViewAdapter
import eg.com.invive.barberia_ktx.R
import eg.com.invive.barberia_ktx.Utils.loadImage
import eg.com.invive.barberia_ktx.Utils.show

class ImageSliderAdapter(val context: Context, val  imageList:List<String>):
    SliderViewAdapter<ImageSliderAdapter.ImageHolder>() {






    override fun onCreateViewHolder(parent: ViewGroup?): ImageHolder=
        ImageHolder(LayoutInflater.from(parent?.getContext()).inflate(R.layout.image_slider, parent, false))

    override fun getCount(): Int =imageList.size

    override fun onBindViewHolder(viewHolder: ImageHolder?, position: Int) {
        viewHolder?.bind(context,imageList[position])
    }


    //////////////////////////////////////////////////////////////////////////////////
    class ImageHolder(itemView: View) : SliderViewAdapter.ViewHolder(itemView) {
        fun bind(context: Context,imageUrl:String) {
            val  imageView = itemView.findViewById<ImageView>(R.id.image_slider)
            val  progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar)
            progressBar.show()
            imageView.loadImage(imageUrl,null,progressBar)

        }


    }


}