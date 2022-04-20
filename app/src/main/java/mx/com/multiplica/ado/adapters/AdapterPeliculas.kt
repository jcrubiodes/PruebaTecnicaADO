package mx.com.multiplica.ado.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import mx.com.multiplica.ado.App
import mx.com.multiplica.ado.R
import mx.com.multiplica.ado.beans.ResultMovie
import mx.com.multiplica.ado.utils.Constantes

class AdapterPeliculas(items: ArrayList<ResultMovie>?) : BaseAdapter() {

    private var requestOptions: RequestOptions? = null

    var items: ArrayList<ResultMovie>? = null

    init {
        requestOptions = RequestOptions()
        this.items = items
    }

    override fun getItem(position: Int): Any {
        return items?.get(position)!!
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items?.count()!!
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHoder: ViewHolder? = null

        var vista: View? = convertView
        if (vista == null) {
            vista = LayoutInflater.from(App.instance).inflate(R.layout.row_grid, null)
            viewHoder = ViewHolder(vista)
            vista.tag = viewHoder
        } else {
            viewHoder = vista.tag as? ViewHolder
        }
        val item = getItem(position) as ResultMovie
        viewHoder?.nombre?.text = item.title
        viewHoder?.fecha?.text = item.release_date
        viewHoder?.imagen?.setImageResource(R.drawable.error_imagen)
        try {
            Glide.with(App.instance)
                .setDefaultRequestOptions(requestOptions?.placeholder(R.drawable.wait_image)!!)
                .setDefaultRequestOptions(requestOptions?.error(R.drawable.error_imagen)!!)
                .load(Constantes.getImageURL("w300", item.backdrop_path))
                .into(viewHoder?.imagen!!)
        }catch (e: Exception) {

        }
        return vista!!
    }

    private class ViewHolder(view: View) {
        var nombre: TextView? = null
        var fecha: TextView? = null
        var imagen: ImageView? = null

        init {
            this.nombre = view.findViewById(R.id.item_text)
            this.fecha = view.findViewById(R.id.item_fecha)
            this.imagen = view.findViewById(R.id.item_image)
        }
    }
}