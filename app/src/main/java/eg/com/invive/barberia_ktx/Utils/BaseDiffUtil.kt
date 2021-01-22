package eg.com.invive.barberia_ktx.Utils

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import eg.com.invive.barberia_ktx.Interface.POJOBase

class BaseDiffUtil(private val context: Context, private val oldList: List<POJOBase>, private val newList: List<POJOBase>):
    DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id==newList[newItemPosition].id

    override fun getOldListSize(): Int=oldList.size


    override fun getNewListSize(): Int =newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition]==newList[newItemPosition]
}