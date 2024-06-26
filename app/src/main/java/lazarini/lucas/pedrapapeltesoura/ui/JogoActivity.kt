package lazarini.lucas.pedrapapeltesoura.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import lazarini.lucas.pedrapapeltesoura.R
import lazarini.lucas.pedrapapeltesoura.databinding.ActivityJogoBinding
import lazarini.lucas.pedrapapeltesoura.utils.IntentMessages.GAME_RESULTS
import lazarini.lucas.pedrapapeltesoura.utils.IntentMessages.QUANTIDADE_BOTS
import lazarini.lucas.pedrapapeltesoura.utils.JoKenPoGame
import lazarini.lucas.pedrapapeltesoura.utils.JoKenPoGameResult
import lazarini.lucas.pedrapapeltesoura.utils.JoKenPoPlayType
import lazarini.lucas.pedrapapeltesoura.utils.JoKenPoVersion

class JogoActivity : AppCompatActivity() {
    private val activityJogoBinding: ActivityJogoBinding by lazy {
        ActivityJogoBinding.inflate(layoutInflater)
    }

    private lateinit var game: JoKenPoGame

    private val gameResults: MutableList<JoKenPoGameResult> = mutableListOf()

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

        activityJogoBinding.lagartoBt.setOnClickListener {
            playerPlayHandler(JoKenPoPlayType.LAGARTO)
        }

        activityJogoBinding.spockBt.setOnClickListener {
            playerPlayHandler(JoKenPoPlayType.SPOCK)
        }

        activityJogoBinding.toolbarIn.voltarBt.setOnClickListener {
            Intent().apply {
                putExtra(GAME_RESULTS, gameResults.toTypedArray())
                setResult(RESULT_OK, this)
                finish()
            }
        }
    }

    private fun playerPlayHandler(play: JoKenPoPlayType) {
        if (game.isEnded())
            return

        activityJogoBinding.escolhaBot1.text = game.getEscolhaBot1().play
        activityJogoBinding.escolhaBot1.setBackgroundColor(Color.parseColor("#334155"))

        if (game.version == JoKenPoVersion.VS_2_BOT) {
            activityJogoBinding.escolhaBot2.text = game.getEscolhaBot2()?.play
            activityJogoBinding.escolhaBot2.setBackgroundColor(Color.parseColor("#334155"))
        }

        activityJogoBinding.escolhaPlayer.text = play.play
        activityJogoBinding.escolhaPlayer.setBackgroundColor(Color.parseColor("#334155"))

        val result = game.getResult(play)

        activityJogoBinding.resultadoTv.text = result.result
        activityJogoBinding.vencedoresTv.text = game.winners()

        gameResults.add(
            JoKenPoGameResult(
                result,
                play,
                game.getEscolhaBot1(),
                game.getEscolhaBot2()
            )
        )
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

    override fun onDestroy() {
        super.onDestroy()
        Intent().apply {
            putExtra(GAME_RESULTS, gameResults.toTypedArray())
            setResult(RESULT_OK, this)
        }
    }
}