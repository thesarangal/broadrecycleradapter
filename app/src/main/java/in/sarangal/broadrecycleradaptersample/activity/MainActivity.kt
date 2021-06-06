package `in`.sarangal.broadrecycleradaptersample.activity

import `in`.sarangal.broadrecycleradapter.BroadRecyclerAdapter
import `in`.sarangal.broadrecycleradapter.decorator.CustomSpaceDecorator
import `in`.sarangal.broadrecycleradapter.itemviewmodel.BaseItemViewModel
import `in`.sarangal.broadrecycleradapter.listener.ItemLongClickListener
import `in`.sarangal.broadrecycleradaptersample.R
import `in`.sarangal.broadrecycleradaptersample.databinding.ActivityMainBinding
import `in`.sarangal.broadrecycleradaptersample.itemviewmodel.ContactItemViewModel
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* Data Binding */
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        /* Initialize */
        initialize()

        /* Set Listeners */
        setListeners()

        /* Set Observers */
        setObservers()
    }

    /**
     * Set Observers
     * */
    private fun setObservers() {

        /* Observer to notify change in Adapter List */
        viewModel.adapter?.getMutableLiveDataList()?.observe(this) { list ->
            if (list.isNotEmpty()) {
                binding.recyclerView.visibility = View.VISIBLE
                binding.noItemFound.visibility = View.INVISIBLE
            } else {
                binding.recyclerView.visibility = View.INVISIBLE
                binding.noItemFound.visibility = View.VISIBLE
            }
        }
    }

    /**
     * Set Listeners
     * */
    private fun setListeners() {

        /* Add Item Click */
        binding.addItem.setOnClickListener {
            viewModel.addItem()
        }

        /* Add Item Click */
        binding.addTitle.setOnClickListener {
            viewModel.addTitle()
        }
    }

    /**
     * Initialize Components
     * */
    private fun initialize() {

        /* Add Margin Around The Item */
        val bottomOffsetDecoration = CustomSpaceDecorator(
            spacing = resources.getDimension(R.dimen._16dp).toInt(),
            orientation = CustomSpaceDecorator.Orientation.VERTICAL
        )
        binding.recyclerView.addItemDecoration(bottomOffsetDecoration)

        /**
        Reference: README.md
        I. Adding Simple Single View List in Adapter

        val itemList = ArrayList<TitleItemViewModel>()
        itemList.add(TitleItemViewModel("Title 1"))
        itemList.add(TitleItemViewModel("Title 2"))
        itemList.add(TitleItemViewModel("Title 3"))
        itemList.add(TitleItemViewModel("Title 4"))

        val adapter = BroadRecyclerAdapter(itemList)
        binding.recyclerView.adapter = adapter


        II. Adding Simple Multi View List in Adapter

        val itemList = ArrayList<BaseItemViewModel>()
        itemList.add(TitleItemViewModel("Title 1"))
        itemList.add(ContactItemViewModel("Item Name 1"))
        itemList.add(TitleItemViewModel("Title 3"))
        itemList.add(ContactItemViewModel("Item Name 2"))

        val adapter = BroadRecyclerAdapter(itemList)
        binding.recyclerView.adapter = adapter


        III. Add Items Dynamically

        val itemList = ArrayList<BaseItemViewModel>()
        itemList.add(TitleItemViewModel("Title 1"))
        itemList.add(ContactItemViewModel("Item Name 1"))
        itemList.add(TitleItemViewModel("Title 3"))
        itemList.add(ContactItemViewModel("Item Name 2"))

        val adapter = BroadRecyclerAdapter()
        binding.recyclerView.adapter = adapter

        adapter.addAll(itemList)

        IV. Add Click Listener in Item

        /* Step 1. Add Following Atributes in Item XML Layout */
        android:clickable="true"
        android:focusable="true"
        android:onClick="@{(view) -> data.onItemClick(view)}"

        /* Step 2. Add Click Interface in Adapter */
        val adapter = BroadRecyclerAdapter(itemList,
        itemClickListener = object : BaseItemClickListener {
        override fun onItemClick(view: View, value: BaseItemViewModel) {

        /* Step 4. Identify Multiple View Callback by Class Type */
        if(value is ContactItemViewModel){
        when(view.id){

        /* Step 5. Identify Multiple Click by View Id */
        R.id.delete -> {

        /* Remove Item From Adapter */
        viewModel.adapter?.remove(value)
        }
        else -> {
        Toast.makeText(
        this@MainActivity,
        "Item Selected: ${value.checkBoxField.get()}",
        Toast.LENGTH_LONG
        ).show()
        }
        }
        }
        }
        })
        binding.recyclerView.adapter = adapter

         */

        /* Adapter Component */
        if (viewModel.adapter == null) {
            viewModel.adapter = BroadRecyclerAdapter(
                itemClickListener = object : ItemLongClickListener {

                    /**
                     * Callback of RecyclerView Item Long Click Listener
                     *
                     * @param view View on which user is clicked
                     * @param value Object of the List: You need to cast this object to the type of
                     * list which has been used in the adapter.
                     *
                     * */

                    /**
                     * Callback of RecyclerView Item Long Click Listener
                     *
                     * @param view View on which user is clicked
                     * @param value Object of the List: You need to cast this object to the type of
                     * list which has been used in the adapter.
                     *
                     * */

                    /**
                     * Callback of RecyclerView Item Long Click Listener
                     *
                     * @param view View on which user is clicked
                     * @param value Object of the List: You need to cast this object to the type of
                     * list which has been used in the adapter.
                     *
                     * */

                    /**
                     * Callback of RecyclerView Item Long Click Listener
                     *
                     * @param view View on which user is clicked
                     * @param value Object of the List: You need to cast this object to the type of
                     * list which has been used in the adapter.
                     *
                     * */
                    /**
                     * Callback of RecyclerView Item Long Click Listener
                     *
                     * @param view View on which user is clicked
                     * @param value Object of the List: You need to cast this object to the type of
                     * list which has been used in the adapter.
                     *
                     * */
                    /**
                     * Callback of RecyclerView Item Long Click Listener
                     *
                     * @param view View on which user is clicked
                     * @param value Object of the List: You need to cast this object to the type of
                     * list which has been used in the adapter.
                     *
                     * */
                    /**
                     * Callback of RecyclerView Item Long Click Listener
                     *
                     * @param view View on which user is clicked
                     * @param value Object of the List: You need to cast this object to the type of
                     * list which has been used in the adapter.
                     *
                     * */
                    /**
                     * Callback of RecyclerView Item Long Click Listener
                     *
                     * @param view View on which user is clicked
                     * @param value Object of the List: You need to cast this object to the type of
                     * list which has been used in the adapter.
                     *
                     * */
                    override fun onItemLongClick(view: View, value: BaseItemViewModel) {
                        if (value is ContactItemViewModel) {

                            when (view.id) {

                                R.id.info -> {

                                    Toast.makeText(
                                        this@MainActivity,
                                        "Item Long Pressed",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                    }

                    /**
                     * Callback of RecyclerView Click Listener
                     *
                     * @param view View on which user is clicked
                     * @param value Object of the List: You need to cast this object to the type of
                     * list which has been used in the adapter.
                     *
                     * */

                    /**
                     * Callback of RecyclerView Click Listener
                     *
                     * @param view View on which user is clicked
                     * @param value Object of the List: You need to cast this object to the type of
                     * list which has been used in the adapter.
                     *
                     * */

                    /**
                     * Callback of RecyclerView Click Listener
                     *
                     * @param view View on which user is clicked
                     * @param value Object of the List: You need to cast this object to the type of
                     * list which has been used in the adapter.
                     *
                     * */

                    /**
                     * Callback of RecyclerView Click Listener
                     *
                     * @param view View on which user is clicked
                     * @param value Object of the List: You need to cast this object to the type of
                     * list which has been used in the adapter.
                     *
                     * */
                    /**
                     * Callback of RecyclerView Click Listener
                     *
                     * @param view View on which user is clicked
                     * @param value Object of the List: You need to cast this object to the type of
                     * list which has been used in the adapter.
                     *
                     * */
                    /**
                     * Callback of RecyclerView Click Listener
                     *
                     * @param view View on which user is clicked
                     * @param value Object of the List: You need to cast this object to the type of
                     * list which has been used in the adapter.
                     *
                     * */
                    /**
                     * Callback of RecyclerView Click Listener
                     *
                     * @param view View on which user is clicked
                     * @param value Object of the List: You need to cast this object to the type of
                     * list which has been used in the adapter.
                     *
                     * */
                    /**
                     * Callback of RecyclerView Click Listener
                     *
                     * @param view View on which user is clicked
                     * @param value Object of the List: You need to cast this object to the type of
                     * list which has been used in the adapter.
                     *
                     * */
                    override fun onItemClick(view: View, value: BaseItemViewModel) {
                        if (value is ContactItemViewModel) {

                            when (view.id) {

                                R.id.delete -> {

                                    /* Remove Item From Adapter */
                                    viewModel.adapter?.remove(value)
                                }

                                R.id.info -> {

                                    Toast.makeText(
                                        this@MainActivity,
                                        "Please long press on the button",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }

                                else -> {

                                    Toast.makeText(
                                        this@MainActivity,
                                        "Item Selected: ${value.checkBoxField.get()}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                    }
                }
            )
        }

        /* Set Adapter */
        binding.recyclerView.adapter = viewModel.adapter
    }
}