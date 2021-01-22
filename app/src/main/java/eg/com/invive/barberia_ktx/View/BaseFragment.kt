package eg.com.invive.barberia_ktx.View

import android.content.Context
import android.os.Bundle
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import eg.com.invive.barberia_ktx.Interface.NavDrawer

open class BaseFragment(private val contentLayoutId:Int):Fragment(contentLayoutId) {

    val navController:NavController
    get() {return findNavController()}

    fun navigate(action: Int, bundle: Bundle? = null) {
        findNavController().navigate(action, bundle)
    }

    fun navigate(direction: NavDirections, args: Bundle? = null) {
        findNavController().navigate(direction.actionId, args)
    }

    fun navigateUp() {
        findNavController().navigateUp()
    }


    fun getMainDrawer() = activity as NavDrawer

    open fun backHandle(){
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (getMainDrawer().isDrawerOpen()){
                getMainDrawer().closeDrawer()
            }else{
                navigateUp()
            }

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        backHandle()
    }
}