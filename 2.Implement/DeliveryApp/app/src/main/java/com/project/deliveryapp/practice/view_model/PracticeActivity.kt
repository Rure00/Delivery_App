package com.project.deliveryapp.practice.view_model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.project.deliveryapp.databinding.ActivityPracticeBinding
import java.util.logging.Filter

class PracticeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPracticeBinding

    private val viewModel: ItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.selectedItem.observe(this, Observer {item ->
            binding.testText.text = item.name
        })

        viewModel.loadItems()
    }
}

class ListViewModel: ViewModel() {
    val filters = MutableLiveData<Set<Filter>>()

    private lateinit var originalList: LiveData<List<Item>>
    lateinit var filteredList: LiveData<List<Item>>

    fun addFilter(filter: Filter) {

    }
    fun removeFilter(filter: Filter) {

    }

}

class ListFragment: Fragment() {
    private val viewModel: ItemViewModel by activityViewModels()

    fun onItemClicked(item: Item) {
        viewModel.selectItem(item)
    }

}

class FilterFragment: Fragment() {
    private val viewModel: ListViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.filters.observe(viewLifecycleOwner, Observer{ set ->

        })
    }

    fun onFilterSelected(filter: Filter) {
        viewModel.addFilter(filter)
    }
    fun onFilterDeSelected(filter: Filter) {
        viewModel.removeFilter(filter)
    }
}
