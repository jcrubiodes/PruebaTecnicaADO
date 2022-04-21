package mx.com.multiplica.ado

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
import com.google.android.youtube.player.YouTubePlayer.Provider
import com.google.android.youtube.player.YouTubePlayerView
import mx.com.multiplica.ado.databinding.ActivityVideoBinding
import mx.com.multiplica.ado.utils.Constantes
import mx.com.multiplica.ado.youtube.DeveloperKey

class VideoActivity : YouTubeBaseActivity(), OnInitializedListener {

    private var _binding: ActivityVideoBinding? = null

    private val RECOVERY_REQUEST = 1
    private var youTubeView: YouTubePlayerView? = null

    private val binding get() = _binding!!

    var urlyoutube: String = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        youTubeView = binding.youtubevideo as YouTubePlayerView
        youTubeView!!.initialize(DeveloperKey.DEVELOPER_KEY, this)
        val bundle: Bundle? = intent.extras
        urlyoutube = bundle!!.getString(Constantes.STR_URLVIDEOYOUTUBE).toString()
    }

    override fun onInitializationSuccess(
        provider: Provider?,
        player: YouTubePlayer,
        wasRestored: Boolean
    ) {
        if (!wasRestored) {
            player.cueVideo(urlyoutube)
        }
    }

    override fun onInitializationFailure(
        provider: Provider?,
        errorReason: YouTubeInitializationResult
    ) {
        if (errorReason.isUserRecoverableError) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show()
        } else {
            val error = String.format(getString(R.string.player_error), errorReason.toString())
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider()!!.initialize(DeveloperKey.DEVELOPER_KEY, this)
        }
    }

    protected fun getYouTubePlayerProvider(): Provider? {
        return youTubeView
    }
}