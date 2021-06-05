package `in`.sarangal.broadrecycleradapter.itemviewmodel

import `in`.sarangal.broadrecycleradapter.listener.BaseItemClickListener
import `in`.sarangal.broadrecycleradapter.listener.ItemLongClickListener
import android.view.View
import androidx.recyclerview.widget.RecyclerView

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
     * Reference of ViewHolder and Adapter
     * */
    var adapterReferences: AdapterReferences ?= null

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
     * Called for XML Layout view "app:onLongClick"
     *
     * @param view View on which User clicked
     * */
    open fun onItemLongClick(view: View) {
        clickListener?.apply {
            if(this is ItemLongClickListener){
                onItemLongClick(view, this@BaseItemViewModel)
            }
        }
    }

    /**
     * @return Reference of ViewHolder of the item
     * */
    fun getViewHolder() = adapterReferences?.getViewHolder()

    /**
     * @return List of Items of the Adapter
     * */
    fun getAdapterItemList() = adapterReferences?.getAdapterList()

    /**
     * @return Layout file i.e R.layout.view_design
     */
    abstract val viewType: Int

    /**
     * Interface to Get References from Adapter
     * */
    interface AdapterReferences {

        /**
         * @return Reference of Current Item's ViewHolder
         * */
        fun getViewHolder() : RecyclerView.ViewHolder

        /**
         * @return List of Current Adapter's Items
         * */
        fun getAdapterList() : List<BaseItemViewModel>
    }
}