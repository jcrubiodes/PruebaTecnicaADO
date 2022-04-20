package mx.com.multiplica.ado

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import mx.com.multiplica.ado.adapters.AdapterPeliculas
import mx.com.multiplica.ado.beans.ResultMovie
import mx.com.multiplica.ado.beans.ResultsMovie
import mx.com.multiplica.ado.databinding.FragmentFirstBinding
import mx.com.multiplica.ado.presents.movies.ContratoPeliculas
import mx.com.multiplica.ado.presents.movies.MVP_ConsultaPeliculas
import mx.com.multiplica.ado.utils.Constantes

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), ContratoPeliculas.Vista {

    private var _binding: FragmentFirstBinding? = null
    var txtMsgHead: TextView? = null
    private var progressBar: ProgressBar? = null
    var gvLsPeliculas: ListView? = null
    var lista: ArrayList<ResultMovie>? = ArrayList()
    lateinit var adapter: AdapterPeliculas

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var pagination: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
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
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.btnPagination.setOnClickListener {
            pagination++
            MVP_ConsultaPeliculas(this).consultingListMovies(pagination)
        }
        binding.gvLsPeliculas.setOnItemClickListener { adapterView, view, i, l ->
            val bundle = Bundle()
            bundle.putInt(Constantes.ID_MOVIE, lista?.get(i)?.id!!)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }
        binding.btnPagination.callOnClick()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun muestraToast(mensaje: String, tiempo: Int) {
    }

    override fun actualizaVista(results: ResultsMovie) {
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