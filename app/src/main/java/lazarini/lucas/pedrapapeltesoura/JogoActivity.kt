package lazarini.lucas.pedrapapeltesoura

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import lazarini.lucas.pedrapapeltesoura.databinding.ActivityJogoBinding
import lazarini.lucas.pedrapapeltesoura.utils.IntentMessages.QUANTIDADE_BOTS
import lazarini.lucas.pedrapapeltesoura.utils.JoKenPoGame
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
    }
}