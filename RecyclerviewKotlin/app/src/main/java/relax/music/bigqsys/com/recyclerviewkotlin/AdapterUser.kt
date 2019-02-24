package relax.music.bigqsys.com.recyclerviewkotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_user.view.*

class AdapterUser(var list: List<User>, var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
      var view=LayoutInflater.from(p0.context).inflate(R.layout.item_user,p0,false)
        return Item(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        (p0 as Item).binData(list[p1])
    }
     class Item(itemView: View):RecyclerView.ViewHolder(itemView){
        fun binData(user: User) {
            itemView.txtName.text=user.name
            itemView.txtAge.text=user.age.toString()
        }
    }
}