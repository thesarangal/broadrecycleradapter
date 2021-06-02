package `in`.sarangal.broadrecycleradaptersample.activity

import `in`.sarangal.broadrecycleradapter.listener.BaseItemClickListener
import `in`.sarangal.broadrecycleradapter.itemviewmodel.BaseItemViewModel
import `in`.sarangal.broadrecycleradapter.adapter.BroadRecyclerAdapter
import `in`.sarangal.broadrecycleradaptersample.itemviewmodel.ContactItemViewModel
import `in`.sarangal.broadrecycleradaptersample.R
import `in`.sarangal.broadrecycleradaptersample.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity(), BaseItemClickListener {

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
        viewModel.adapter?.getMutableLiveDataList()?.observe(this){ list ->
            if(list.isNotEmpty()) {
                binding.recyclerView.visibility = View.VISIBLE
                binding.noItemFound.visibility = View.GONE
            } else {
                binding.recyclerView.visibility = View.GONE
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

        /* Add Item on Top Click */
        binding.addBeginningItem.setOnClickListener {
            viewModel.addItemOnTop()
        }
    }

    /**
     * Initialize Components
     * */
    private fun initialize() {

        /* Adapter Component */
        if(viewModel.adapter == null){
            viewModel.adapter = BroadRecyclerAdapter(
                itemClickListener = this
            )
        }

        /* Set Adapter */
        binding.recyclerView.adapter = viewModel.adapter
    }

    /**
     * Callback of RecyclerView Click Listener
     *
     * @param view View on which user is clicked
     * @param value Object of the List: You need to cast this object to the type of
     * list which has been used in the adapter.
     *
     * */
    override fun onItemClick(view: View, value: BaseItemViewModel) {
        if(value is ContactItemViewModel){

            when(view.id){

                R.id.delete -> {

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
}