package mx.com.multiplica.ado

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import mx.com.multiplica.ado.beans.DetailsMovie
import mx.com.multiplica.ado.beans.ResultsYouTubeVideo
import mx.com.multiplica.ado.databinding.FragmentSecondBinding
import mx.com.multiplica.ado.presents.detailsmovie.ContratoDetail
import mx.com.multiplica.ado.presents.detailsmovie.MVP_ConsultingMovieDetail
import mx.com.multiplica.ado.utils.Constantes

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment(), ContratoDetail.Vista {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private var idMovie: Int = 0
    private var urlYouTube: String = ""

    private var requestOptions: RequestOptions? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        val args = getArguments()
        this.idMovie = args?.getInt(Constantes.ID_MOVIE, 0)!!
        MVP_ConsultingMovieDetail(this).consultingDetailMovie(idMovie)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestOptions = RequestOptions()
        val bundle = Bundle()
        binding.btnShowTube.setOnClickListener {
            bundle.putString(Constantes.STR_URLVIDEOYOUTUBE, urlYouTube)
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun actualizaProgress(progress: Int) {
        binding.progressBarSec.progress = progress
        if (progress == 100) {
            MVP_ConsultingMovieDetail(this).consultingYouTubeMovie(idMovie)
        }
        if (progress >= 100) {
            binding.progressBarSec.visibility = View.GONE
        } else {
            binding.progressBarSec.visibility = View.VISIBLE
        }
    }

    override fun actualizaVistaDetail(detailsMovie: DetailsMovie) {
        Glide.with(App.instance)
            .setDefaultRequestOptions(requestOptions?.placeholder(R.drawable.wait_image)!!)
            .setDefaultRequestOptions(requestOptions?.error(R.drawable.error_imagen)!!)
            .load(Constantes.getImageURL("w1280", detailsMovie.poster_path))
            .into(binding.imgDetailsMovie)
        binding.txtTitle.text = detailsMovie.title
        binding.txtOverView.text = detailsMovie.overview
    }

    override fun mostrarVideoYouTube(urlYouTubeVideo: ResultsYouTubeVideo) {
        if (urlYouTubeVideo.results.size > 0) {
            binding.cvYoutube.visibility = View.VISIBLE
            urlYouTube = urlYouTubeVideo.results.get(0).key
        }
    }
}