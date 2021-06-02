package `in`.sarangal.broadrecycleradaptersample.itemviewmodel

import `in`.sarangal.broadrecycleradapter.itemviewmodel.BaseItemViewModel
import `in`.sarangal.broadrecycleradaptersample.R
import androidx.databinding.ObservableBoolean

class ContactItemViewModel(
    val name: String
) : BaseItemViewModel() {

    /* Ui Fields */
    val checkBoxField = ObservableBoolean()

    /**
     * @return Layout file i.e R.layout.view_design
     */
    override val viewType: Int
        get() = R.layout.inflate_item_view

}