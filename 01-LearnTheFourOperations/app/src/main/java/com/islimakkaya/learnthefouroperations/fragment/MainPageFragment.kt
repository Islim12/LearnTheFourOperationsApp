package com.islimakkaya.learnthefouroperations.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.islimakkaya.learnthefouroperations.R
import com.islimakkaya.learnthefouroperations.adapter.CategoriesAdapter
import com.islimakkaya.learnthefouroperations.databinding.FragmentMainPageBinding
import com.islimakkaya.learnthefouroperations.entity.Categories


class MainPageFragment : Fragment() {
    private lateinit var design: FragmentMainPageBinding
    private lateinit var adapter: CategoriesAdapter
    private lateinit var categoriesList: ArrayList<Categories>
     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         design = DataBindingUtil.inflate(inflater,R.layout.fragment_main_page, container, false)
         design.homePageRecyclerList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

         val c1 = Categories(1, "Addition", "plus")
         val c2 = Categories(2, "Subtraction", "minus")
         val c3 = Categories(3, "Multiplication", "multiplication")
         val c4 = Categories(4, "Division", "division")
         val c5 = Categories(5, "Profile", "profile")
         val c6 = Categories(6, "About Us", "about_us")

         categoriesList = ArrayList()

         categoriesList.add(c1)
         categoriesList.add(c2)
         categoriesList.add(c3)
         categoriesList.add(c4)
         categoriesList.add(c5)
         categoriesList.add(c6)

         adapter = CategoriesAdapter(requireContext(), categoriesList)
         design.homePageRecyclerList.adapter = adapter

         return design.root
    }


}