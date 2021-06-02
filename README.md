# BroadRecyclerAdapter
Advance RecyclerView Adapter

BroadRecyclerAdapter is a simple and easy implementation of RecyclerView Adapter.

Current Version: [![](https://jitpack.io/v/thesarangal/broadrecycleradapter.svg)](https://jitpack.io/#thesarangal/broadrecycleradapter)


### Features

- Simple and easier implementation
- With MVVM Support
- With Data Binding Support
- Multi-view Support

### How to To get a Git project into your build

#### Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

#### Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.thesarangal:broadrecycleradapter:VERSION_CODE'
	}

Done! The first time you request a project JitPack checks out the code, builds it and serves the build artifacts (jar, aar).

### How to use BroadRecyclerAdapter
#### I. Adding Simple Single View List in Adapter

    Kotlin:
    
    /* Step 1. Create Class extend with BaseItemViewModel Class */
    class TitleItemViewModel(val name: String) : BaseItemViewModel() {
    
        /* Step 2. Return Layout Resource of the Item */
        override val viewType: Int
            get() = R.layout.inflate_title_view
    
    }
    
    /* Step 3. Binding Item Layout with Class (Which is created in Step. 1) */
    <?xml version="1.0" encoding="utf-8"?>
    <layout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto">
    
        <data>
    
            <variable
                name="data"
                type="in.sarangal.broadrecycleradaptersample.itemviewmodel.TitleItemViewModel" />
        </data>
    
        <androidx.constraintlayout.widget.ConstraintLayout
            android:background="@android:color/holo_purple"
            android:layout_width="match_parent"
            android:layout_height="wrap_content">
    
            <com.google.android.material.textview.MaterialTextView
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_margin="@dimen/_16dp"
                android:text="@{data.name}"
                android:textStyle="bold"
                android:textColor="@color/white"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent" />
    
        </androidx.constraintlayout.widget.ConstraintLayout>
    </layout>
    
    /* Step 4. Prepare List for Adapter */
    val itemList = ArrayList<TitleItemViewModel>()
    itemList.add(TitleItemViewModel("Title 1"))
    itemList.add(TitleItemViewModel("Title 2"))
    itemList.add(TitleItemViewModel("Title 3"))
    itemList.add(TitleItemViewModel("Title 4"))
    
    /* Step 5. Create Adapter Object and Set on RecyclerView */
    val adapter = BroadRecyclerAdapter(itemList)
    binding.recyclerView.adapter = adapter
    
#### II. Adding Simple Multi View List in Adapter

    Kotlin:
    
    /* Step 1. Create First Class extend with BaseItemViewModel Class */
    class TitleItemViewModel(val name: String) : BaseItemViewModel() {
    
        /* Step 2. Return Layout Resource of the Item */
        override val viewType: Int
            get() = R.layout.inflate_title_view
    
    }
    
    /* Step 3. Create First Class extend with BaseItemViewModel Class */
    class ContactItemViewModel(val name: String) : BaseItemViewModel() {
    
        /* Step 4. Return Layout Resource of the Item */
        override val viewType: Int
            get() = R.layout.inflate_item_view
    
    }
    
    /* Step 5. Prepare List for Adapter */
    val itemList = ArrayList<BaseItemViewModel>()
    itemList.add(TitleItemViewModel("Title 1"))
    itemList.add(ContactItemViewModel("Item Name 1"))
    itemList.add(TitleItemViewModel("Title 3"))
    itemList.add(ContactItemViewModel("Item Name 2"))
    
    /* Step 6. Create Adapter Object and Set on RecyclerView */
    val adapter = BroadRecyclerAdapter(itemList)
    binding.recyclerView.adapter = adapter

#### II. Add Click Listener in Item

    Kotlin
    
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
    
#### III. Add Items Dynamically
##### Note: If you are using Kotlin Coroutines, make sure that you adding items to adapter on Main-Thread.

    Kotlin
    val adapter = BroadRecyclerAdapter(itemList)
    
    ...
    
    /* Adding Single Item */
    adapter.add(TitleItemViewModel("Title 1"))
    
    /* Adding List of Item */
    val itemList = ArrayList<BaseItemViewModel>()
    itemList.add(TitleItemViewModel("Title 1"))
    itemList.add(ContactItemViewModel("Item Name 1"))
    itemList.add(TitleItemViewModel("Title 3"))
    itemList.add(ContactItemViewModel("Item Name 2"))
    adapter.addAll(itemList)
    
#### IV. All Useful Methods
##### * Add Item

    adapter.add(/* OBJECT */)
    
##### * Add List on the Top

    adapter.addToTop(/* OBJECT */)
    
##### * Add List on specific Index

    adapter.add(INDEX_INTEGER, /* OBJECT */)
    
##### * Add List into Adapter

    adapter.addAll(/* LIST_OF_OBJECT */)
    
##### * Override New List on Adapter by Clear previous list

    adapter.setItems(/* LIST OF OBJECT */)
    
##### * Remove Single Item from Adapter

    adapter.remove(/* OBJECT */)
    
##### * Clear Adapter List

    adapter.clear()
    
##### * Get Single Item from List of Adapter

    adapter.getItem(/* POSITION */)
    
##### * Get Whole List of Items of Adapter

    adapter.getItems()
    
##### * Get MutableLiveDataList to Observer changes in Adapter List

    adapter.getMutableLiveDataList()
    
##### * Check if List of Adapter is empty or not

    adapter.isNotEmpty   
    
##### * Get count of items of adapter list

    adapter.itemCount
    
##### In addition, you can use all other methods of the RecyclerView Adapter.





#### Developed with ❤ by Sarangal
