package com.example.aroundegypt

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aroundegypt.network.APIClient
import com.example.dto.FavDataBase

class HomeFragment : Fragment(), RecommendedListener, RecentListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ExperienceViewModel
    private lateinit var experienceAdapter: RecommendedAdapter
    private lateinit var recentAdapter: RecentAdapter

    companion object {
        const val EXPERIENCE_ID = "package com.example.aroundegypt;id"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecommendedRecyclerView(view)
        setUpRecentRecyclerView(view)

        setupViewModel()
        viewModel.getExperiencedData()
        viewModel.getRecentData()

        viewModel.experiences.observe(viewLifecycleOwner) { newData ->
            experienceAdapter.updateData(newData.data)
        }
        viewModel.recent.observe(viewLifecycleOwner) { newData ->
            recentAdapter.updateData(newData.data)
        }
    }

    private fun setUpRecommendedRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.rv_recommend)
        recyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        experienceAdapter = RecommendedAdapter(listOf(), this)
        recyclerView.adapter = experienceAdapter
    }

    private fun setUpRecentRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.rv_recent)
        recyclerView.layoutManager =
            LinearLayoutManager(requireActivity())
        recentAdapter = RecentAdapter(listOf(), this)
        recyclerView.adapter = recentAdapter
    }

    private fun setupViewModel() {
        val service = APIClient.getInstance()
        val favDao = FavDataBase.getInstance(requireContext()).favDao()

        val factory = ExperienceFactory(service,favDao)
        viewModel = ViewModelProvider(this, factory)[ExperienceViewModel::class.java]
    }

    override fun onExperienceClick(id: String) {
        navigateToExperience(id)
    }

    private fun navigateToExperience(id: String) {
        val intent = Intent(requireActivity(), ExperienceActivity()::class.java)
        intent.putExtra(EXPERIENCE_ID, id)
        startActivity(intent)
    }

    override fun onRecommendedClick(id: String) {
        val intent = Intent(requireActivity(), ExperienceActivity()::class.java)
        intent.putExtra(EXPERIENCE_ID, id)
        startActivity(intent)
    }
}