package com.white.whiterabbit.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.white.whiterabbit.R
import com.white.whiterabbit.model.db.CompanyEntity
import kotlinx.android.synthetic.main.row_search.view.*

class SearchAdapter(private var ser:List<CompanyEntity>):RecyclerView.Adapter<SearchAdapter.MViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_search, parent, false)
        return MViewHolder(view)
    }

    override fun onBindViewHolder(vh: MViewHolder, position: Int) {
        //renderSearch
        vh.bind(ser[position])
    }

    override fun getItemCount(): Int {
        return ser.size
    }

    fun update(data:List<CompanyEntity>){
        ser= data
        notifyDataSetChanged()
    }

    class MViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val textViewName:TextView = view.textViewName
        private val textViewCompanyName:TextView = view.textViewCompanyName
        private val imageView:ImageView = view.imageView
        private val card:CardView = view.idCard
        fun bind(cmpEnt:CompanyEntity){
            textViewName.text = "Name : "+cmpEnt.name
            textViewCompanyName.text ="Company Name : "+cmpEnt.companyName
            Glide.with(imageView.context).load(cmpEnt.profile_image).into(imageView)

            card.setOnClickListener(View.OnClickListener {
                val intent = Intent(it.context,DetailActivity::class.java)
                intent.putExtra("EXTRA_PEOPLE", cmpEnt)
                it.context.startActivity(intent)
            })
        }
    }
}