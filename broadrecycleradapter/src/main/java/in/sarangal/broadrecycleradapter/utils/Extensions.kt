package `in`.sarangal.broadrecycleradapter.utils

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Notify Mutable Live Data
 * */
fun <T> MutableLiveData<T>.notifyObserver() {
    this.postValue(this.value)
}

/**
 * Check Is Current Item is Last Item
 * */
fun RecyclerView.isLastVisible(): Boolean {
    val layoutManager = (layoutManager as LinearLayoutManager)
    val pos = layoutManager.findLastCompletelyVisibleItemPosition()

    val numItems: Int? = adapter?.itemCount

    if (numItems != null) {
        return numItems <= pos + 1
    }
    return false
}

/**
 * Long Click Listener
 *
 * Custom Binding & Extension Function
 * */
@BindingAdapter("onLongClick")
fun View.onLongClick(function: () -> Unit){
    setOnLongClickListener {

        /* Execute Block */
        function()

        true
    }
}