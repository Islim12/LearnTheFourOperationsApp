package com.islimakkaya.learnthefouroperations.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.islimakkaya.learnthefouroperations.databinding.CardDesignBinding
import com.islimakkaya.learnthefouroperations.entity.Categories
import com.islimakkaya.learnthefouroperations.fragment.MainPageFragmentDirections
import kotlinx.android.synthetic.main.card_design.view.*

class CategoriesAdapter(var mContext: Context, var categoriesList: List<Categories>)
    : RecyclerView.Adapter<CategoriesAdapter.CardDesignHolder>(){
    inner class CardDesignHolder(categoryDesignBinding: CardDesignBinding)
        : RecyclerView.ViewHolder(categoryDesignBinding.root) {
        var cardDesign: CardDesignBinding

        init {
            this.cardDesign = categoryDesignBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val design = CardDesignBinding.inflate(layoutInflater, parent, false)
        return CardDesignHolder(design)
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val category = categoriesList.get(position)

        holder.cardDesign.categoryObject = category

        holder.cardDesign.cardImage.setImageResource(
            mContext.resources.getIdentifier(category.category_image_name, "drawable", mContext.packageName))

        holder.cardDesign.verticalCard.setOnClickListener {

            val id = category.category_id

            val transition: NavDirections = when (id) {
                1 -> MainPageFragmentDirections.toAdditionGame()
                2 -> MainPageFragmentDirections.toSubtractionGame()
                3 -> MainPageFragmentDirections.toMultiplicationGame()
                4 -> MainPageFragmentDirections.toDivisionGame()
                5 -> MainPageFragmentDirections.toProfilePage()
                else -> MainPageFragmentDirections.toAboutUsPage()
            }

            Navigation.findNavController(it).navigate(transition)
        }
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }
}
