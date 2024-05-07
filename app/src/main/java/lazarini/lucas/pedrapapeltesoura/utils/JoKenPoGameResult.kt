package lazarini.lucas.pedrapapeltesoura.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class JoKenPoGameResult(
    val result: JoKenPoResult,
    val playerPlay: JoKenPoPlayType,
    val bot1Play: JoKenPoPlayType,
    val bot2Play: JoKenPoPlayType?
): Parcelable