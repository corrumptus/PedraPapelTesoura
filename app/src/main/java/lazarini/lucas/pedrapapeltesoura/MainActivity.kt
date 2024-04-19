package lazarini.lucas.pedrapapeltesoura

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import lazarini.lucas.pedrapapeltesoura.databinding.ActivityMainBinding
import lazarini.lucas.pedrapapeltesoura.utils.IntentMessages.QUANTIDADE_BOTS

class MainActivity : AppCompatActivity() {
    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.vs1botBt.setOnClickListener {
            Intent(this, JogoActivity::class.java).apply {
                putExtra(QUANTIDADE_BOTS, 1)
                startActivity(this)
            }
        }

        activityMainBinding.vs2botBt.setOnClickListener {
            Intent(this, JogoActivity::class.java).apply {
                putExtra(QUANTIDADE_BOTS, 2)
                startActivity(this)
            }
        }
    }
}