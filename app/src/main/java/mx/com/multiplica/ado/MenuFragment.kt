package mx.com.multiplica.ado

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import mx.com.multiplica.ado.adapters.AdaptadorMenu
import mx.com.multiplica.ado.beans.ItemMenu
import mx.com.multiplica.ado.databinding.FragmentMenuBinding
import mx.com.multiplica.ado.utils.Constantes

class MenuFragment : Fragment(), AdaptadorMenu.IAdaptadorMenu {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private var adaptadorMenu: AdaptadorMenu? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val data = ArrayList<ItemMenu>()
        data.add(ItemMenu(0, 1, R.drawable.morepopular, "Peliculas - Populares"))
        data.add(ItemMenu(1, 1, R.drawable.tvseries, "Series - Populares"))
        data.add(ItemMenu(0, 2, R.drawable.topratedmovie, "Peliculas - Top Rated"))
        data.add(ItemMenu(1, 2, R.drawable.seriestop, "Series - Top Rated"))
        data.add(ItemMenu(2, 3, R.drawable.morepopular_buscar, "Buscar - Peliculas"))
        data.add(ItemMenu(2, 4, R.drawable.tvseries_buscar, "Buscar - Series"))
        adaptadorMenu = AdaptadorMenu(data)
        adaptadorMenu?.callbackAdaptadorMenu = this
        binding.gvMenu.layoutManager = GridLayoutManager(App.instance, 2)
        binding.gvMenu.adapter = adaptadorMenu
        binding.progressBar.visibility = View.GONE
        binding.txtMsgHead.text = getString(R.string.menu_title)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickItemMenu(idItem: Int, opcionWS: Int) {
        val bundle = Bundle()
        bundle.putInt(Constantes.OPC_MENU, idItem)
        bundle.putInt(Constantes.OPC_WS, opcionWS)
        bundle.putBoolean(Constantes.OPC_BUSQUEDA, false)
        when (opcionWS) {
            0 -> {
                findNavController().navigate(R.id.action_MenuFragment_to_ListMoviesFrag, bundle)
            }
            1 -> {
                findNavController().navigate(R.id.action_MenuFragment_to_ListSeriesFrag, bundle)
            }
            2 -> {
                bundle.putBoolean(Constantes.OPC_BUSQUEDA, true)
                if (idItem == 3) {
                    findNavController().navigate(R.id.action_MenuFragment_to_ListMoviesFrag, bundle)
                } else {
                    findNavController().navigate(R.id.action_MenuFragment_to_ListSeriesFrag, bundle)
                }
            }
        }
    }
}