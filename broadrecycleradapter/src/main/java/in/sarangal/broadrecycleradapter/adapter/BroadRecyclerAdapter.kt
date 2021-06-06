package `in`.sarangal.broadrecycleradapter.adapter

import `in`.sarangal.broadrecycleradapter.BR
import `in`.sarangal.broadrecycleradapter.listener.BaseItemClickListener
import `in`.sarangal.broadrecycleradapter.itemviewmodel.BaseItemViewModel
import `in`.sarangal.broadrecycleradapter.utils.notifyObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView

/**
 * Common RecyclerView Adapter:
 *
 * Help to:
 * 1. Add Item
 * 2. Remove Item
 * 3. Update Item
 * 4. Add Click Listener
 * and many more
 *
 * @param <TYPE> Type of List's object
 * @param itemList List of the Objects
 * @param itemClickListener Interface to handle callback of item click
 */
class BroadRecyclerAdapter<TYPE : BaseItemViewModel>(
    private val itemList: ArrayList<TYPE> = ArrayList(),
    private val itemClickListener: BaseItemClickListener? = null
) : RecyclerView.Adapter<BroadRecyclerAdapter<TYPE>.ViewHolder<TYPE>>() {

    /**
     * Mutable Live Data Containing List of Adapter Items
     *
     * Why Mutable Live Data Used?
     *
     * Use
     * @see BroadRecyclerAdapter#getMutableLiveDataList
     * to observe the state of items of adapter.
     *
     * Use Case: Help to Show/Hide "No Item Found" from the View
     * */
    private val itemsLiveData: MutableLiveData<MutableList<TYPE>> = MutableLiveData()

    init {

        /* Set Data List to LiveData */
        itemsLiveData.value = itemList
    }

    /**
     * @return LiveData Instance of the items
     * */
    fun getMutableLiveDataList() = itemsLiveData

    /**
     * If need to update the last item layout
     * in case of last item bottom margin in Pagination
     * */
    private var isUpdateLastItem = false

    /**
     * @param value TRUE/FALSE to update last item view when list update.
     * */
    fun setLastItemUpdate(value: Boolean) {
        this.isUpdateLastItem = value
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<TYPE> {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link android.widget.ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     *
     * Override {@link #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: ViewHolder<TYPE>, position: Int) {
        itemsLiveData.value?.let { itemList ->
            if (position >= itemList.size) {
                return
            }

            /* Bind Data with View */
            holder.bind(itemList[position], itemClickListener)
        }
    }

    /**
     * Generic ViewHolder supporting
     *
     * @param <TYPE> Type of List's object
     * @param itemView View of the Adapter Item
     */
    inner class ViewHolder<TYPE : BaseItemViewModel>(itemView: View) : RecyclerView.ViewHolder(itemView),
        BaseItemViewModel.AdapterReferences {

        /**
         * Data Binding Component
         * */
        private var binding: ViewDataBinding? = null

        /**
         * Bind Data with View
         *
         * @param value List Object
         * @param itemClickListener Interface for Click Callback
         * */
        fun bind(value: TYPE, itemClickListener: BaseItemClickListener?) {
            value.adapterReferences = this@ViewHolder
            value.clickListener = itemClickListener
            binding?.setVariable(BR.data, value)
        }

        init {

            /* Initialize Data Binding */
            binding = DataBindingUtil.bind(itemView)
        }

        /**
         * @return Reference of Current Item's ViewHolder
         * */
        override fun getViewHolder(): RecyclerView.ViewHolder {
            return this@ViewHolder
        }

        /**
         * @return List of Current Adapter's Items
         * */
        override fun getAdapterList(): List<BaseItemViewModel> {
            return this@BroadRecyclerAdapter.getItems() ?: listOf()
        }
    }

    /**
     * Return the view type of the item at <code>position</code> for the purposes
     * of view recycling.
     *
     * <p>The default implementation of this method returns 0, making the assumption of
     * a single view type for the adapter. Unlike ListView adapters, types need not
     * be contiguous. Consider using id resources to uniquely identify item view types.
     *
     * @param position position to query
     * @return integer value identifying the type of the view needed to represent the item at
     *                 <code>position</code>. Type codes need not be contiguous.
     */
    override fun getItemViewType(position: Int): Int {
        itemsLiveData.value?.let { itemList  ->
            if (itemList.size > position) return itemList[position].viewType
        }
        return 0
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int = itemsLiveData.value?.size ?: 0

    /**
     * Method to add items in the list by clearing previous list
     *
     * @param itemsList         List of items
     * @param notifyDataSet     Notify the adapter
     */
    fun setItems(itemsList: List<TYPE>?, notifyDataSet: Boolean = true) {

        if(itemsList == null || itemsList.isEmpty()) return

        this.itemsLiveData.value?.apply {

            /* Clear List */
            clear()

            /* Add Item in Adapter */
            this@BroadRecyclerAdapter.addAll(itemsList)
        }?.also {
            if (notifyDataSet) {
                notifyDataSetChanged()

                /* Notify LiveData */
                this@BroadRecyclerAdapter.itemsLiveData.notifyObserver()
            }
        }
    }

    /**
     * @return List of all adapter items.
     */
    fun getItems(): List<TYPE>? {
        return itemsLiveData.value
    }

    /**
     * @return Single item of the list by position
     */
    fun getItem(position: Int): TYPE? {

        itemsLiveData.value?.let { itemList ->
            if (position < itemList.size) {
                return itemList[position]
            }
        }

        return null
    }

    /**
     * Adds the specified element to the end of this list
     * and notify item inserted
     *
     * @param item to add in list
     */
    fun add(item: TYPE?) {

        if(item == null) return

        itemsLiveData.value?.apply {

            /* Add Item */
            add(item)
        }?.let {

            val lastItemPosition = it.size - 1

            if (isUpdateLastItem && lastItemPosition > 0) {
                notifyItemChanged(lastItemPosition - 1)
            }

            notifyItemInserted(lastItemPosition)

            /* Notify LiveData */
            this@BroadRecyclerAdapter.itemsLiveData.notifyObserver()
        }
    }

    /**
     * Adds the specified element to the end of this list
     * and notify item inserted
     *
     * @param item to add in list
     * @param index position to add item
     */
    fun add(index: Int, item: TYPE?) {

        if(item == null) return

        itemsLiveData.value?.apply {

            /* Add Item on Specify Index */
            add(index, item)
        }?.let {

            if (isUpdateLastItem && index > 0) {
                notifyItemChanged(index - 1)
            }

            notifyItemInserted(index)

            /* Notify LiveData */
            this@BroadRecyclerAdapter.itemsLiveData.notifyObserver()
        }
    }

    /**
     * Adds the specified element to the beginning of this list
     * and notify item inserted
     *
     * @param item to add in list
     */
    fun addToTop(item: TYPE?) {

        if(item == null) return

        itemsLiveData.value?.apply {

            /* Add Item on beginning index */
            this.add(0, item)
        }?.let {
            notifyItemInserted(0)

            /* Notify LiveData */
            this@BroadRecyclerAdapter.itemsLiveData.notifyObserver()
        }
    }

    /**
     * Adds all of the elements of the specified collection to the end of this list.
     * and notify adapter
     *
     * The elements are appended in the order they appear in the collection.
     *
     * @param items to add in list
     */
    fun addAll(items: List<TYPE>?) {

        if(items == null || items.isEmpty()) return

        this.itemsLiveData.value?.apply {

            /* Add List of Items */
            addAll(items)
        }?.let {

            val newItemPosition = it.size - items.size

            if (isUpdateLastItem && newItemPosition > 0) {
                notifyItemChanged(newItemPosition - 1)
            }

            notifyItemRangeInserted(newItemPosition, items.size)

            /* Notify LiveData */
            this@BroadRecyclerAdapter.itemsLiveData.notifyObserver()
        }
    }

    /**
     * Clear list of the adapter
     */
    fun clear() {
        itemsLiveData.value?.apply {

            /* Clear list of Adapter */
            clear()
        }?.let {
            notifyDataSetChanged()

            /* Notify LiveData */
            this@BroadRecyclerAdapter.itemsLiveData.notifyObserver()
        }
    }

    /**
     * Removes an element from the list.
     *
     * @param item to be removed
     */
    fun remove(item: TYPE) {
        itemsLiveData.value?.apply {
            val position = indexOf(item)
            if (position > -1) {
                removeAt(position)
                notifyItemRemoved(position)

                /* Notify LiveData */
                this@BroadRecyclerAdapter.itemsLiveData.notifyObserver()

                if (isUpdateLastItem && (itemCount > 0)) {
                    notifyItemChanged(itemCount - 1)
                }
            }
        }
    }

    /**
     * @return TRUE if item list is not empty else FALSE
     */
    val isNotEmpty: Boolean = itemCount != 0
}