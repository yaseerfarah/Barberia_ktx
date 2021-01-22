package eg.com.invive.barberia_ktx.Utils

import android.content.res.Resources
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class DetailsItemDecoration(private val space:Int):
    RecyclerView.ItemDecoration() {

companion object{
    fun dpToPx( dp:Int, resources: Resources):Int {
        val r = resources;
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics).roundToInt();
    }
}



    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
       val position=parent.getChildLayoutPosition(view)
        val childCount=parent.adapter!!.itemCount

        val layoutManager=parent.layoutManager as LinearLayoutManager

        if (position==childCount-1) {
            outRect.bottom = space

        }

    }
}