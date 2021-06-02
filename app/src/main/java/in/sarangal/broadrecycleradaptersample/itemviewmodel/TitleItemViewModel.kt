package `in`.sarangal.broadrecycleradaptersample.itemviewmodel

import `in`.sarangal.broadrecycleradapter.itemviewmodel.BaseItemViewModel
import `in`.sarangal.broadrecycleradaptersample.R

class TitleItemViewModel(
    val name: String
) : BaseItemViewModel() {

    /**
     * @return Layout file i.e R.layout.view_design
     */
    override val viewType: Int
        get() = R.layout.inflate_title_view

}