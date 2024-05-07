package lazarini.lucas.pedrapapeltesoura

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import lazarini.lucas.pedrapapeltesoura.databinding.ActivityMainBinding
import lazarini.lucas.pedrapapeltesoura.utils.IntentMessages.QUANTIDADE_BOTS
import lazarini.lucas.pedrapapeltesoura.utils.JoKenPoGameResult
import lazarini.lucas.pedrapapeltesoura.utils.JoKenPoVersion

class MainActivity : AppCompatActivity() {
    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val gameResults: MutableList<JoKenPoGameResult> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.vs1botBt.setOnClickListener {
            Intent(this, JogoActivity::class.java).apply {
                putExtra(QUANTIDADE_BOTS, JoKenPoVersion.VS_1_BOT)
                startActivity(this)
            }
        }

        activityMainBinding.vs2botBt.setOnClickListener {
            Intent(this, JogoActivity::class.java).apply {
                putExtra(QUANTIDADE_BOTS, JoKenPoVersion.VS_2_BOT)
                startActivity(this)
            }
        }
    }
}