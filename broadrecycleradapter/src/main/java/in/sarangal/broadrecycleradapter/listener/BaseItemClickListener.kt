package `in`.sarangal.broadrecycleradapter.listener

import `in`.sarangal.broadrecycleradapter.itemviewmodel.BaseItemViewModel
import android.view.View

/**
 * Base RecyclerView Adapter Item Click Interface
 * */
interface BaseItemClickListener {

    /**
     * Callback of RecyclerView Click Listener
     *
     * @param view View on which user is clicked
     * @param value Object of the List: You need to cast this object to the type of
     * list which has been used in the adapter.
     *
     * */
    fun onItemClick(view: View, value: BaseItemViewModel)
}