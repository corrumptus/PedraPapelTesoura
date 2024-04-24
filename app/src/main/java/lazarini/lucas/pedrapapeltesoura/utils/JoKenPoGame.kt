package lazarini.lucas.pedrapapeltesoura.utils

class JoKenPoGame(val version: JoKenPoVersion) {
    private lateinit var playerChoose: JoKenPoPlayType

    private var escolhaBot1: JoKenPoPlayType = randomJokenpoPlayType()

    private var escolhaBot2: JoKenPoPlayType = randomJokenpoPlayType()

    private var result: JoKenPoResult? = null

    private fun randomJokenpoPlayType(): JoKenPoPlayType {
        return JoKenPoPlayType.entries.toTypedArray().random()
    }

    fun getEscolhaBot1(): JoKenPoPlayType {
        return escolhaBot1
    }

    fun getEscolhaBot2(): JoKenPoPlayType {
        return escolhaBot2
    }
}