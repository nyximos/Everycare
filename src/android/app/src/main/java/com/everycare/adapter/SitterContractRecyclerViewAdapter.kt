//package com.everycare.adapter
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.everycare.R
//import com.everycare.dto.SitterContractListDTO
//
//class SitterContractRecyclerViewAdapter (val context: Context, val boardList: ArrayList<SitterContractListDTO>?, val click: (SitterContractListDTO) -> Unit) :
//    RecyclerView.Adapter<SitterContractRecyclerViewAdapter.SitterContractViewHolder>() {
//
//
//    inner class SitterContractViewHolder(view: View?, click: (SitterContractListDTO) -> Unit) :
//        RecyclerView.ViewHolder(view!!) {
////        val dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE
//
//        val id: TextView? = view?.findViewById(R.id.id)
//        val title: TextView? = view?.findViewById(R.id.title)
//        val createdAt: TextView? = view?.findViewById(R.id.date)
//        val views: TextView? = view?.findViewById(R.id.views)
//
//        fun bind(board: SitterContractListDTO?, context: Context) {
//            id?.text = board?.id.toString()
//            title?.text = board?.title
//            createdAt?.text = board?.createdAt
//            views?.text = board?.views.toString()
//
//            itemView.setOnClickListener {
//                if (board != null) {
//                    click(board)
//                }
//            }
//        }
//    }
//
//    /* ViewHolder 만들때 사용하는 함수
//    RecyclerView는 ViewHolder를 새로 만들어야 할 때마다 이 메서드를 호출합니다.
//    이 메서드는 ViewHolder와 그에 연결된 View를 생성하고 초기화하지만 뷰의 콘텐츠를 채우지는 않습니다.
//    ViewHolder가 아직 특정 데이터에 바인딩된 상태가 아니기 때문입니다.
//     */
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SitterContractViewHolder {
//        val view = LayoutInflater
//            .from(context)
//            .inflate(R.layout.item_list, parent, false)
//        return SitterContractViewHolder(view, click)
//    }
//
//    /* ViewHolder에 데이터 바인딩하는 함수
//    RecyclerView는 ViewHolder를 데이터와 연결할 때 이 메서드를 호출합니다.
//    이 메서드는 적절한 데이터를 가져와서 그 데이터를 사용하여 뷰 홀더의 레이아웃을 채웁니다.
//    예를 들어 RecyclerView가 이름 목록을 표시하는 경우 메서드는 목록에서 적절한 이름을 찾아 뷰 홀더의 TextView 위젯을 채울 수 있습니다.
//    */
//    override fun onBindViewHolder(holder: SitterContractViewHolder, position: Int) {
//        holder.bind(boardList?.get(position), context)
//    }
//
//    /*
//    RecyclerView는 데이터 세트 크기를 가져올 때 이 메서드를 호출합니다.
//    예를 들어 주소록 앱에서는 총 주소 개수가 여기에 해당할 수 있습니다.
//    RecyclerView는 이 메서드를 사용하여, 항목을 추가로 표시할 수 없는 상황을 확인합니다.
//     */
//    override fun getItemCount(): Int {
//        return boardList?.size ?: 0
//    }
//}
