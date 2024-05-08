package lazarini.lucas.pedrapapeltesoura.utils

class JoKenPoGame(val version: JoKenPoVersion) {
    private lateinit var playerChoose: JoKenPoPlayType

    private var escolhaBot1: JoKenPoPlayType = randomJokenpoPlayType()

    private var escolhaBot2: JoKenPoPlayType = randomJokenpoPlayType()

    private var result: JoKenPoResult? = null

    private fun randomJokenpoPlayType(): JoKenPoPlayType {
        return JoKenPoPlayType.entries.toTypedArray().random()
    }

    fun getResult(playerChoose: JoKenPoPlayType): JoKenPoResult {
        this.playerChoose = playerChoose

        result = if (version == JoKenPoVersion.VS_1_BOT)
            getResult1Bot(playerChoose)
        else getResult2Bots(playerChoose)

        return result as JoKenPoResult
    }

    private fun getResult1Bot(playerChoose: JoKenPoPlayType): JoKenPoResult {
        if (playerChoose == escolhaBot1)
            return JoKenPoResult.EMPATE

        if (escolhaBot1.counters().contains(playerChoose))
            return JoKenPoResult.VITORIA

        return JoKenPoResult.DERROTA
    }

    private fun getResult2Bots(playerChoose: JoKenPoPlayType): JoKenPoResult {
        val playerChooseTheCounterPlayOfTheAllBots = escolhaBot1.counters().contains(playerChoose)
            && escolhaBot2.counters().contains(playerChoose)

        if (playerChooseTheCounterPlayOfTheAllBots)
            return JoKenPoResult.VITORIA

        val playerChooseTheSamePlayAsOtherBots = playerChoose == escolhaBot1
            && playerChoose == escolhaBot2

        val playerChooseTheSamePlayAsOtherBot1AndNotLoseForTheBot2 = playerChoose == escolhaBot1
            && escolhaBot2.counters().contains(playerChoose)

        val playerChooseTheSamePlayAsOtherBot2AndNotLoseForTheBot1 = playerChoose == escolhaBot2
            && escolhaBot1.counters().contains(playerChoose)

        if (
            playerChooseTheSamePlayAsOtherBots ||
            playerChooseTheSamePlayAsOtherBot1AndNotLoseForTheBot2 ||
            playerChooseTheSamePlayAsOtherBot2AndNotLoseForTheBot1
        )
            return JoKenPoResult.EMPATE

        return JoKenPoResult.DERROTA
    }

    fun winners(): String {
        if (result == null)
            return ""

        if (result == JoKenPoResult.VITORIA)
            return "Player"

        if (result == JoKenPoResult.EMPATE)
            return "Player" +
                (if (playerChoose == escolhaBot1) ", Bot 1" else "") +
                (if (playerChoose == escolhaBot2) ", Bot 2" else "")

        if (escolhaBot1 == escolhaBot2)
            return "Bot 1, Bot 2"

        return if (escolhaBot2.counters().contains(escolhaBot1)) "Bot 1"
            else "Bot 2"
    }

    fun getEscolhaBot1(): JoKenPoPlayType {
        return escolhaBot1
    }

    fun getEscolhaBot2(): JoKenPoPlayType? {
        return if (version === JoKenPoVersion.VS_2_BOT) escolhaBot2
            else null
    }

    fun isEnded(): Boolean {
        return result != null
    }

    fun resetGame() {
        escolhaBot1 = randomJokenpoPlayType()
        escolhaBot2 = randomJokenpoPlayType()
        result = null
    }
}