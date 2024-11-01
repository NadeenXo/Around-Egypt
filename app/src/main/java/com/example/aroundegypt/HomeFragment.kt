package com.example.aroundegypt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aroundegypt.databinding.ItemRecommendBinding
import com.example.aroundegypt.network.APIClient

class HomeFragment : Fragment(), ExperienceListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ExperienceViewModel
    private lateinit var adapter: ExperienceAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView(view)

        setupViewModel()
        viewModel.getData()

        viewModel.meals.observe(viewLifecycleOwner) { newData ->
            adapter.updateData(newData.data)
        }
    }

    private fun setUpRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.rv_recommend)
        recyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        adapter = ExperienceAdapter(listOf(), this)
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        val service = APIClient.getInstance()
        val factory = ExperienceFactory(service)
        viewModel = ViewModelProvider(this, factory)[ExperienceViewModel::class.java]
    }

    override fun onExperienceClick(experience: ItemRecommendBinding) {
        //todo: goto another page
    }
}