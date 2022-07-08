package com.everycare.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.everycare.BuildConfig
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
                .load(sitter?.storeFileNames?.get(0)?.let { getURLForResource(it) })
//                .load(findImage(context, sitter?.storeFileNames?.get(0)))
//                .load(uri(sitter?.storeFileNames?.get(0)))
                .error(R.drawable.sitter10)
//                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_Ih6HsXZiSdvWwpUraD2QUVqyu7U067mFHHPn_d98p2LVZLEwVWSPR7H61VmGJ3wVu6o&usqp=CAU")
                .into(profile!!)


            itemView.setOnClickListener{
                if(sitter !=null) {
                    click(sitter)
                }
            }
        }

        private fun getURLForResource(fileName: String): Any? {

            Log.d("경로", "경로명 : ${"android.resource://" + R::class.java.getPackage().getName() + "/" + "src/main/res/drawable/" + fileName}")

//            return "android.resource://" + R::class.java.getPackage().getName() + "/drawable/" + fileName
//            return "android.resource://" + R::class.java.getPackage().getName() + "/src/main/res/drawable/" + fileName
//            return Uri.parse("android.resource://" + R::class.java.getPackage().getName() + "/src/main/res/drawable/" + fileName)


            return Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID +"/"  + fileName).toString()

//            Log.d("경로", "경로명 : ${Environment.getExternalStorageDirectory().getAbsolutePath()}")
//            return Environment.getExternalStorageDirectory().getAbsolutePath()+fileName
//            return context.resources.getIdentifier(fileName, "drawable", context.packageName)
//            return Uri.parse(
//                "android.resource://" + R::class.java.getPackage().getName() + "/" + fileName
//            ).toString()


        }

//        fun findImage(context: Context, fileName: String?): Any {
//            return context.getResources().getIdentifier(fileName, "drawable", context.getPackageName());
//        }

        fun uri(fileName: String?): Uri? {
            return Uri.parse("https://10.30.3.62:8086/api/images/" + fileName.toString())
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