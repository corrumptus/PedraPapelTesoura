package lazarini.lucas.pedrapapeltesoura.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import lazarini.lucas.pedrapapeltesoura.adapter.GameResultAdapter
import lazarini.lucas.pedrapapeltesoura.databinding.ActivityGameResultsBinding
import lazarini.lucas.pedrapapeltesoura.utils.IntentMessages.GAME_RESULTS
import lazarini.lucas.pedrapapeltesoura.utils.JoKenPoGameResult

class GameResultsActivity : AppCompatActivity() {
    private val activityGameResultBinding: ActivityGameResultsBinding by lazy {
        ActivityGameResultsBinding.inflate(layoutInflater)
    }

    private lateinit var gameResultsAdapter: GameResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityGameResultBinding.root)

        val gameResults = intent.getParcelableArrayExtra(GAME_RESULTS)
            ?.map { p -> p as JoKenPoGameResult }
            ?.toTypedArray()

        gameResultsAdapter = GameResultAdapter(
            this,
            if (gameResults != null) gameResults.toList()
            else listOf()
        )

        activityGameResultBinding.toolbarIn.apply {
            setSupportActionBar(toolbar)
        }

        activityGameResultBinding.toolbarIn.voltarBt.setOnClickListener {
            finish()
        }

        activityGameResultBinding.resultsLv.adapter = gameResultsAdapter
    }
}