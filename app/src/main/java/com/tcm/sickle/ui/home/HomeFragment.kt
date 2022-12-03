package com.tcm.sickle.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.tcm.sickle.data.TiktokBean
import com.tcm.sickle.databinding.FragmentHomeBinding
import com.tcm.sickle.ui.home.adapter.HomeAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val mList: MutableList<TiktokBean> = arrayListOf()
    private val mAdapter: HomeAdapter = HomeAdapter(mList)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        binding.homeRV.run {
//            layoutManager = GridLayoutManager(requireContext(), 2)
//            adapter = mAdapter
//        }
//
//        homeViewModel.ticktokData.observe(viewLifecycleOwner) {
//            mList.clear()
//            mList.addAll(it)
//            mAdapter.notifyDataSetChanged()
//        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}