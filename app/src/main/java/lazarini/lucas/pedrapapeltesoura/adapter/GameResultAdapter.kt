package lazarini.lucas.pedrapapeltesoura.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import lazarini.lucas.pedrapapeltesoura.R
import lazarini.lucas.pedrapapeltesoura.databinding.TileGameResultBinding
import lazarini.lucas.pedrapapeltesoura.utils.JoKenPoGameResult
import lazarini.lucas.pedrapapeltesoura.utils.JoKenPoResult

class GameResultAdapter(
    context: Context,
    private val resultList: List<JoKenPoGameResult>
): ArrayAdapter<JoKenPoGameResult>(context, R.layout.tile_game_result, resultList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val gameResult = resultList[position]

        val tileGameResultBinding: TileGameResultBinding?

        val gameResultTileView = if (convertView != null)
            convertView
        else {
            tileGameResultBinding = TileGameResultBinding.inflate(
                context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                parent,
                false
            )

            tileGameResultBinding.root.apply {
                tag = tileGameResultBinding
            }
        }

        (gameResultTileView.tag as TileGameResultBinding).playerTv.text = gameResult.playerPlay.play
        (gameResultTileView.tag as TileGameResultBinding).bot1Tv.text = gameResult.bot1Play.play

        if (gameResult.bot2Play != null)
            (gameResultTileView.tag as TileGameResultBinding).bot2Tv.text = gameResult.bot2Play.play
        else (gameResultTileView.tag as TileGameResultBinding).bot2Tv.visibility = View.GONE

        (gameResultTileView.tag as TileGameResultBinding).resultTv.text = gameResult.result.result
        (gameResultTileView.tag as TileGameResultBinding).resultTv.setTextColor(
            Color.parseColor(
                when (gameResult.result) {
                    JoKenPoResult.VITORIA -> "#00ff00"
                    JoKenPoResult.EMPATE -> "#0000ff"
                    JoKenPoResult.DERROTA -> "#ff0000"
                }
            )
        )

        return gameResultTileView
    }
}