package lazarini.lucas.pedrapapeltesoura

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import lazarini.lucas.pedrapapeltesoura.databinding.ActivityGameResultsBinding

class GameResultsActivity : AppCompatActivity() {
    private val activityGameResultBinding: ActivityGameResultsBinding by lazy {
        ActivityGameResultsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityGameResultBinding.root)

        activityGameResultBinding.toolbarIn.apply {
            setSupportActionBar(toolbar)
        }

        activityGameResultBinding.toolbarIn.voltarBt.setOnClickListener {
            finish()
        }
    }
}