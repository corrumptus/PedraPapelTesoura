package lazarini.lucas.pedrapapeltesoura

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import lazarini.lucas.pedrapapeltesoura.databinding.ActivityJogoBinding
import lazarini.lucas.pedrapapeltesoura.utils.IntentMessages.QUANTIDADE_BOTS
import lazarini.lucas.pedrapapeltesoura.utils.JoKenPoGame
import lazarini.lucas.pedrapapeltesoura.utils.JoKenPoPlayType
import lazarini.lucas.pedrapapeltesoura.utils.JoKenPoVersion

class JogoActivity : AppCompatActivity() {
    private val activityJogoBinding: ActivityJogoBinding by lazy {
        ActivityJogoBinding.inflate(layoutInflater)
    }

    private lateinit var game: JoKenPoGame

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityJogoBinding.root)

        activityJogoBinding.toolbarIn.apply {
            setSupportActionBar(toolbar)
        }

        game = JoKenPoGame(intent.getSerializableExtra(QUANTIDADE_BOTS) as JoKenPoVersion)

        if (game.version == JoKenPoVersion.VS_1_BOT) {
            activityJogoBinding.bot2Tv.visibility = View.GONE
            activityJogoBinding.escolhaBot2.visibility = View.GONE
        }

        activityJogoBinding.pedraBt.setOnClickListener {
            playerPlayHandler(JoKenPoPlayType.PEDRA)
        }

        activityJogoBinding.papelBt.setOnClickListener {
            playerPlayHandler(JoKenPoPlayType.PAPEL)
        }

        activityJogoBinding.tesouraBt.setOnClickListener {
            playerPlayHandler(JoKenPoPlayType.TESOURA)
        }
    }

    private fun playerPlayHandler(play: JoKenPoPlayType) {
        activityJogoBinding.escolhaBot1.text = game.getEscolhaBot1().play
        activityJogoBinding.escolhaBot1.setBackgroundColor(Color.parseColor("#334155"))

        if (game.version == JoKenPoVersion.VS_2_BOT) {
            activityJogoBinding.escolhaBot2.text = game.getEscolhaBot2()?.play
            activityJogoBinding.escolhaBot2.setBackgroundColor(Color.parseColor("#334155"))
        }

        activityJogoBinding.escolhaPlayer.text = play.play
        activityJogoBinding.escolhaPlayer.setBackgroundColor(Color.parseColor("#334155"))

        activityJogoBinding.resultadoTv.text = game.getResult(play).result
        activityJogoBinding.vencedoresTv.text = game.winners()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_new_game, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        game.resetGame()

        activityJogoBinding.escolhaBot1.text = ""
        activityJogoBinding.escolhaBot1.setBackgroundColor(Color.parseColor("#1e3a8a"))

        if (game.version == JoKenPoVersion.VS_2_BOT) {
            activityJogoBinding.escolhaBot2.text = ""
            activityJogoBinding.escolhaBot2.setBackgroundColor(Color.parseColor("#1e3a8a"))
        }

        activityJogoBinding.escolhaPlayer.text = ""
        activityJogoBinding.escolhaPlayer.setBackgroundColor(Color.parseColor("#1e3a8a"))

        activityJogoBinding.resultadoTv.text = ""
        activityJogoBinding.vencedoresTv.text = ""

        return true
    }
}