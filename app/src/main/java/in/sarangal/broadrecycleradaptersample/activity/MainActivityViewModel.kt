package `in`.sarangal.broadrecycleradaptersample.activity

import `in`.sarangal.broadrecycleradapter.BroadRecyclerAdapter
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
     * Add Another View Item to List
     * */
    fun addTitle(){
        adapter?.add(
            TitleItemViewModel("Title")
        )
    }
}