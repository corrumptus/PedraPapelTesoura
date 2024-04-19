package lazarini.lucas.pedrapapeltesoura

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import lazarini.lucas.pedrapapeltesoura.databinding.ActivityJogoBinding

class JogoActivity : AppCompatActivity() {

    private val activityJogoBinding: ActivityJogoBinding by lazy {
        ActivityJogoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityJogoBinding.root)
    }
}