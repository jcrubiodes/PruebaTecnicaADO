package mx.com.multiplica.ado.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.com.multiplica.ado.R
import mx.com.multiplica.ado.beans.ItemMenu


class AdaptadorMenu(private val mList: List<ItemMenu>) :
    RecyclerView.Adapter<AdaptadorMenu.ViewHolder>() {

    var callbackAdaptadorMenu: IAdaptadorMenu? = null


    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_menu, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.item_image)
        val textView: TextView = itemView.findViewById(R.id.item_text)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position] as ItemMenu
        holder.imageView.setImageResource(ItemsViewModel.idIc)
        holder.textView.text = ItemsViewModel.texto
        holder.imageView.setOnClickListener {
            callbackAdaptadorMenu?.onClickItemMenu(
                ItemsViewModel.idItem, ItemsViewModel.opcionWS
            )
        }
    }

    interface IAdaptadorMenu {
        fun onClickItemMenu(idItem: Int, opcionWS: Int)
    }

}