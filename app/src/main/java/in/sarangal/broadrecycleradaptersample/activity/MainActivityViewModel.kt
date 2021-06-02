package `in`.sarangal.broadrecycleradaptersample.activity

import `in`.sarangal.broadrecycleradapter.adapter.BroadRecyclerAdapter
import `in`.sarangal.broadrecycleradapter.itemviewmodel.BaseItemViewModel
import `in`.sarangal.broadrecycleradaptersample.itemviewmodel.ContactItemViewModel
import `in`.sarangal.broadrecycleradaptersample.itemviewmodel.TitleItemViewModel
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {

    /* Adapter */
    var adapter : BroadRecyclerAdapter<BaseItemViewModel>?= null

    /**
     * Add Item to List
     * */
    fun addItem(){
        adapter?.add(
            ContactItemViewModel("Item")
        )
    }

    /**
     * Add Item on the top of the list
     * */
    fun addItemOnTop(){
        adapter?.addToTop(
            TitleItemViewModel("Title")
        )
    }
}