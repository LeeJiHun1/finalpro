package com.example.finalpro.result


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.finalpro.R
import com.example.finalpro.list.locationDao
import com.example.finalpro.list.Location
import kotlinx.android.synthetic.main.list_item_fresh.view.*

/* ResultFragment에서 검색 결과를 리사이클러뷰에 데이터를 보여주는 어댑터  */
class ResultAdapter() : RecyclerView.Adapter<ItemViewHolder>() {

    //검색한 데이터(List<FreshData)를 저장할 list
    var freshList: List<Location> = ArrayList()

    /* 뷰홀더를 생성하여 반환 */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        //list_item_fresh 뷰 inflate
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_fresh, parent, false)
        return ItemViewHolder(rootView)
    }

    //뷰홀더에 데이터 바인딩(bindItems() 함수를 호출)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItems(freshList[position])
    }

    //어댑터에서 관리할 아이템 갯수를 반환
    override fun getItemCount() = freshList.size
}//end of ResultAdapter

/* SaveFragment에서 DB에 저장된 검색 결과를 리사이클러뷰에 데이터를 보여주는 어댑터
   - 검색된 결과(SAVE_ID)를 KEY로 FreshData 테이블에서 해당 id를 찾아 화면에 출력
   - SAVE_ID로 저장된 모든 데이터를 리사이클러뷰의 랜더링 사이즈 만큼 씩 보여준다.
* */
class SaveAdpater : PagedListAdapter<Location, ItemViewHolder>(DIFF_CALLBACK) {
    /* 뷰홀더를 생성하여 반환 */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        //list_item_fresh 뷰 inflate
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_fresh, parent, false)
        return ItemViewHolder(rootView)
    }

    //뷰홀더에 데이터 바인딩(bindItems() 함수를 호출)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItems(getItem(position))//(getItem(position) == list<FreshData>
        Log.d("MDIFFER", "${getItem(position)}, SaveAdpater")
        //D/MDIFFER: FreshData(id=301, saveId=7, lname=과실류, mname=사과, sname=후지....SaveAdpater
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Location>() {
            override fun areItemsTheSame(oldConcert: Location, newConcert: Location): Boolean =
                oldConcert.id == newConcert.id

            override fun areContentsTheSame(oldConcert: Location, newConcert: Location): Boolean =
                oldConcert.id == newConcert.id
        }
    }
}//end of SaveAdpater

//뷰홀더 클래스 선언
class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // 검색한 경락가격정보를 아이템뷰(list_item_fresh)에 바인딩하는 함수
    fun bindItems(fresh: Location?) {
        fresh?.let {
            itemView.txt_lnmAdres.text = fresh.lnmAdres
            itemView.txt_unit.text = ""
            itemView.txt_rnAdres.text = fresh.rnAdres
            itemView.txt_zipno.text = "fresh.zipNo"
        }
    }
}//end of ItemViewHolder
