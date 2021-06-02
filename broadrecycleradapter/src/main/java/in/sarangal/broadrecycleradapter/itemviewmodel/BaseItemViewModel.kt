package `in`.sarangal.broadrecycleradapter.itemviewmodel

import `in`.sarangal.broadrecycleradapter.listener.BaseItemClickListener
import android.view.View

/**
 * Base Item View Model:
 *
 * Extend this class to:
 * 1. Get benefits of data binding in the layout file
 * 2. Return layout file according to your view (if you are using multi views in recyclerview)
 *
 * */
abstract class BaseItemViewModel {

    /**
     * Position of the item in Adapter
     *
     * Note: Need to Notify Whole Adapter if You are adding/removing items
     * randomly from the list
     * */
    var position = 0

    /**
     * Click Listener Object
     * */
    var clickListener: BaseItemClickListener? = null

    /**
     * Called for XML Layout view "android:onClick"
     *
     * @param view View on which User clicked
     * */
    open fun onItemClick(view: View) {
        clickListener?.onItemClick(
            view, this
        )
    }

    /**
     * @return Layout file i.e R.layout.view_design
     */
    abstract val viewType: Int
}