package com.everycare.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.everycare.R
import com.everycare.dto.SitterListDTO

class SittersRecyclerViewAdapter(val context: Context, val sitterList: ArrayList<SitterListDTO>?, val click: (SitterListDTO) -> Unit) :
    RecyclerView.Adapter<SittersRecyclerViewAdapter.SitterViewHolder>(){


        inner class SitterViewHolder(view: View?, click: (SitterListDTO) -> Unit): RecyclerView.ViewHolder(view!!) {
            val id: TextView? = view?.findViewById(R.id.id)
            val name: TextView? = view?.findViewById(R.id.name)
            val preferredType: TextView? = view?.findViewById(R.id.preferredType)
            val age: TextView? = view?.findViewById(R.id.age)
            val desiredHourlyWage: TextView? = view?.findViewById(R.id.desiredHourlyWage)
            val desiredMonthlyWage: TextView? = view?.findViewById(R.id.desiredMonthlyWage)

            fun bind(sitter: SitterListDTO?, context: Context) {
                id?.text = sitter?.id.toString()
                name?.text = sitter?.name.toString()
                preferredType?.text = sitter?.preferredType.toString()
                age?.text = sitter?.age.toString()
                desiredHourlyWage?.text = sitter?.hourlyWage.toString()
                desiredMonthlyWage?.text = sitter?.monthlyWage.toString()

                itemView.setOnClickListener{
                    if(sitter !=null) {
                        click(sitter)
                    }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SitterViewHolder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.sitter_list, parent, false)
        return SitterViewHolder(view,click)    }

    override fun onBindViewHolder(holder: SitterViewHolder, position: Int) {
        holder.bind(sitterList?.get(position), context)
    }

    override fun getItemCount(): Int {
        return sitterList?.size ?: 0
    }


}