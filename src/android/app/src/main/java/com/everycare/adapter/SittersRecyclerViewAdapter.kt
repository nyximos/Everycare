package com.everycare.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
        val hopefulRegion: TextView? = view?.findViewById(R.id.hopefulRegion)
        val profile: ImageView? = view?.findViewById(R.id.profile)

        fun bind(sitter: SitterListDTO?, context: Context) {
            id?.text = sitter?.id.toString()
            name?.text = sitter?.name.toString()
            preferredType?.text = sitter?.preferredType.toString()
            age?.text = sitter?.age.toString()
            hopefulRegion?.text = sitter?.hopefulRegion.toString()
            desiredHourlyWage?.text = sitter?.hourlyWage.toString()
            desiredMonthlyWage?.text = sitter?.monthlyWage.toString()

            Glide.with(context)
                .load(uri(sitter?.storeFileNames?.get(0)))
//                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_Ih6HsXZiSdvWwpUraD2QUVqyu7U067mFHHPn_d98p2LVZLEwVWSPR7H61VmGJ3wVu6o&usqp=CAU")
                .into(profile!!)


            itemView.setOnClickListener{
                if(sitter !=null) {
                    click(sitter)
                }
            }
        }

        private fun uri(fileName: String?): Uri? {
            return Uri.parse("https://localhost:8086/api/images/" + fileName.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SitterViewHolder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.sitter_list, parent, false)
        return SitterViewHolder(view,click)
    }

    override fun onBindViewHolder(holder: SitterViewHolder, position: Int) {
        holder.bind(sitterList?.get(position), context)
    }

    override fun getItemCount(): Int {
        return sitterList?.size ?: 0
    }


}