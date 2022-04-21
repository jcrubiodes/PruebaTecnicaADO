package mx.com.multiplica.ado

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import mx.com.multiplica.ado.adapters.AdapterPeliculas
import mx.com.multiplica.ado.beans.ResultMovie
import mx.com.multiplica.ado.beans.ResultsMovie
import mx.com.multiplica.ado.databinding.FragListmoviesBinding
import mx.com.multiplica.ado.presents.movies.ContratoPeliculas
import mx.com.multiplica.ado.presents.movies.MVP_ConsultaPeliculas
import mx.com.multiplica.ado.utils.Constantes


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListMoviesFrag : Fragment(), ContratoPeliculas.Vista {

    private var _binding: FragListmoviesBinding? = null
    var txtMsgHead: TextView? = null
    private var progressBar: ProgressBar? = null
    var gvLsPeliculas: ListView? = null
    var lista: ArrayList<ResultMovie>? = ArrayList()
    lateinit var adapter: AdapterPeliculas

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var pagination: Int = 0

    var opcionMenu: Int = 0
    var opcionWS: Int = 0
    var opcionBusqueda: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragListmoviesBinding.inflate(inflater, container, false)
        txtMsgHead = binding.root.findViewById(R.id.txtMsgHead)
        gvLsPeliculas = binding.root.findViewById(R.id.gvLsPeliculas)

        txtMsgHead?.text = getString(R.string.msg_sininfo)
        adapter = AdapterPeliculas(lista)
        gvLsPeliculas?.adapter = adapter
        progressBar = binding.root.findViewById(R.id.progress_Bar) as ProgressBar
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = getArguments()
        this.opcionMenu = args?.getInt(Constantes.OPC_MENU, 0)!!
        this.opcionWS = args.getInt(Constantes.OPC_WS, 0)
        this.opcionBusqueda = args.getBoolean(Constantes.OPC_BUSQUEDA, false)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_ListMoviesFrag_to_DetailMovieFrag)
        }
        binding.btnPagination.setOnClickListener {
            pagination++
            MVP_ConsultaPeliculas(this).consultingListMovies(pagination, opcionWS, opcionMenu)
        }
        binding.gvLsPeliculas.setOnItemClickListener { adapterView, view, i, l ->
            val bundle = Bundle()
            bundle.putInt(Constantes.ID_MOVIE, lista?.get(i)?.id!!)

            if (opcionWS == 2) {
                opcionWS = opcionMenu - 3
            }

            bundle.putInt(Constantes.OPC_MENU, opcionMenu)
            bundle.putInt(Constantes.OPC_WS, opcionWS)
            findNavController().navigate(R.id.action_ListMoviesFrag_to_DetailMovieFrag, bundle)
        }
        binding.btnPagination.callOnClick()
        if (opcionBusqueda) {
            muestraPantallaBuscar()
        }
    }

    fun muestraPantallaBuscar() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(binding.root.context)
        builder.setTitle("Buscar por:")
        val input = EditText(binding.root.context)
        input.setHint("Introduce el nombre de la película a a buscar")
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)
        builder.setPositiveButton("Buscar", DialogInterface.OnClickListener { dialog, which ->
            var m_Text = input.text.toString()
            if (!m_Text.isEmpty()) {
                MVP_ConsultaPeliculas(this).consultingListMoviesSearchs(
                    pagination,
                    opcionWS,
                    opcionMenu,
                    m_Text
                )
            }
        })
        builder.setNegativeButton(
            "Cancelar",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        builder.show()

    }

    override fun respuestaWS(respuesta: String) {
        val gson = Gson()
        val res = gson.fromJson(
            respuesta,
            ResultsMovie::class.java
        )
        actualizaVista(res)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun muestraToast(mensaje: String, tiempo: Int) {
    }

    fun actualizaVista(results: ResultsMovie) {
//        this.lista?.clear()
        this.lista?.addAll(results.results)
        txtMsgHead?.text = getString(R.string.msg_resultados)
        adapter.notifyDataSetChanged()
    }

    override fun actualizaProgress(progress: Int) {
        progressBar!!.progress = progress
        if (progress >= 100) {
            progressBar?.visibility = View.GONE
        } else {
            progressBar?.visibility = View.VISIBLE
        }
    }
}