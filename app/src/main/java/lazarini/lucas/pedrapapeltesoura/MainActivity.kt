package lazarini.lucas.pedrapapeltesoura

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import lazarini.lucas.pedrapapeltesoura.databinding.ActivityMainBinding
import lazarini.lucas.pedrapapeltesoura.utils.IntentMessages.GAME_RESULTS
import lazarini.lucas.pedrapapeltesoura.utils.IntentMessages.QUANTIDADE_BOTS
import lazarini.lucas.pedrapapeltesoura.utils.JoKenPoGameResult
import lazarini.lucas.pedrapapeltesoura.utils.JoKenPoVersion

class MainActivity : AppCompatActivity() {
    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val gameResults: MutableList<JoKenPoGameResult> = mutableListOf()

    private lateinit var gameActivityResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.vs1botBt.setOnClickListener {
            Intent(this, JogoActivity::class.java).apply {
                putExtra(QUANTIDADE_BOTS, JoKenPoVersion.VS_1_BOT)
                gameActivityResult.launch(this)
            }
        }

        activityMainBinding.vs2botBt.setOnClickListener {
            Intent(this, JogoActivity::class.java).apply {
                putExtra(QUANTIDADE_BOTS, JoKenPoVersion.VS_2_BOT)
                gameActivityResult.launch(this)
            }
        }

        gameActivityResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode != RESULT_OK)
                return@registerForActivityResult

            (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                result.data?.getParcelableArrayExtra(GAME_RESULTS, JoKenPoGameResult::class.java)
            else
                result.data?.getParcelableArrayExtra(GAME_RESULTS) as Array<JoKenPoGameResult>
            )?.let {
                gameResults.addAll(it)
            }
        }
    }
}