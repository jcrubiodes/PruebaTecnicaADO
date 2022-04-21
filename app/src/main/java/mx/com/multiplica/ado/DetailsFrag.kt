package mx.com.multiplica.ado

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import mx.com.multiplica.ado.beans.Details
import mx.com.multiplica.ado.beans.DetailsTV
import mx.com.multiplica.ado.beans.ResultsYouTubeVideo
import mx.com.multiplica.ado.databinding.FragDetailsBinding
import mx.com.multiplica.ado.presents.detailsmovie.ContratoDetail
import mx.com.multiplica.ado.presents.detailsmovie.MVP_ConsultingMovieDetail
import mx.com.multiplica.ado.utils.Constantes

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailsFrag : Fragment(), ContratoDetail.Vista {

    private var _binding: FragDetailsBinding? = null
    private val binding get() = _binding!!
    private var idMovie: Int = 0
    private var opcionMenu: Int = 0
    private var opcionWS: Int = 0
    private var opcionSearch = false
    private var urlYouTube: String = ""

    private var requestOptions: RequestOptions? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragDetailsBinding.inflate(inflater, container, false)
        val args = getArguments()
        this.idMovie = args?.getInt(Constantes.ID_MOVIE, 0)!!
        this.opcionMenu = args.getInt(Constantes.OPC_MENU, 0)
        this.opcionWS = args.getInt(Constantes.OPC_WS, 0)
        this.opcionSearch = args.getBoolean(Constantes.OPC_BUSQUEDA, false)
        if (opcionSearch) {
            opcionWS = opcionMenu - 3
            Log.e(Constantes.getTagConsole(), "opcion->" + opcionWS)
        }
        MVP_ConsultingMovieDetail(this).consultingDetailMovie(idMovie, opcionWS, 0)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestOptions = RequestOptions()
        binding.btnShowTube.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(Constantes.STR_URLVIDEOYOUTUBE, urlYouTube)
            findNavController().navigate(R.id.action_DetailMovieFrag_to_ListMoviesFrag, bundle)
        }
    }

    override fun respuestaWS(respuesta: String) {
        val gson = Gson()
        if (opcionWS == 0) {
            val res = gson.fromJson(
                respuesta,
                Details::class.java
            )
            actualizaVistaDetail(res)
        } else {
            val res = gson.fromJson(
                respuesta,
                DetailsTV::class.java
            )
            actualizaVistaDetailTV(res)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun actualizaProgress(progress: Int) {
        binding.progressBarSec.progress = progress
        if (progress == 100) {
            MVP_ConsultingMovieDetail(this).consultingYouTubeMovie(idMovie, opcionWS, 0)
        }
        if (progress >= 100) {
            binding.progressBarSec.visibility = View.GONE
        } else {
            binding.progressBarSec.visibility = View.VISIBLE
        }
    }

    override fun actualizaVistaDetail(details: Details) {
        Glide.with(App.instance)
            .setDefaultRequestOptions(requestOptions?.placeholder(R.drawable.wait_image)!!)
            .setDefaultRequestOptions(requestOptions?.error(R.drawable.error_imagen)!!)
            .load(Constantes.getImageURL("w1280", details.poster_path))
            .into(binding.imgDetailsMovie)
        binding.txtTitle.text = details.title
        binding.txtOverView.text = details.overview
        if (details.overview.isEmpty() || details.overview == null) {
            binding.txtOverView.text = App.instance.getString(R.string.sin_informacion)
        }
    }

    override fun actualizaVistaDetailTV(details: DetailsTV) {
        Glide.with(App.instance)
            .setDefaultRequestOptions(requestOptions?.placeholder(R.drawable.wait_image)!!)
            .setDefaultRequestOptions(requestOptions?.error(R.drawable.error_imagen)!!)
            .load(Constantes.getImageURL("w1280", details.poster_path))
            .into(binding.imgDetailsMovie)
        binding.txtTitle.text = details.name
        binding.txtOverView.text = details.overview
        if (details.overview.isEmpty() || details.overview == null) {
            binding.txtOverView.text = App.instance.getString(R.string.sin_informacion)
        }
    }

    override fun mostrarVideoYouTube(urlYouTubeVideo: ResultsYouTubeVideo) {
        if (urlYouTubeVideo.results.size > 0) {
            binding.cvYoutube.visibility = View.VISIBLE
            urlYouTube = urlYouTubeVideo.results.get(0).key
        }
    }
}