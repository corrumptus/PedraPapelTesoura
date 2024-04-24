package lazarini.lucas.pedrapapeltesoura.utils

class JoKenPoGame(val version: JoKenPoVersion) {
    private lateinit var playerChoose: JoKenPoPlayType

    private var escolhaBot1: JoKenPoPlayType = randomJokenpoPlayType()

    private var escolhaBot2: JoKenPoPlayType = randomJokenpoPlayType()

    private var result: JoKenPoResult? = null

    private fun randomJokenpoPlayType(): JoKenPoPlayType {
        return JoKenPoPlayType.entries.toTypedArray().random()
    }

    fun getResult1Bot(playerChoose: JoKenPoPlayType): JoKenPoResult {
        if (playerChoose == escolhaBot1)
            return JoKenPoResult.EMPATE

        if (playerChoose == escolhaBot1.counter())
            return JoKenPoResult.VITORIA

        return JoKenPoResult.DERROTA
    }

    fun getresult2Bots(playerChoose: JoKenPoPlayType): JoKenPoResult {
        val playerChooseTheCounterPlayOfTheAllBots = playerChoose == escolhaBot1.counter()
                && playerChoose == escolhaBot2.counter()

        if (playerChooseTheCounterPlayOfTheAllBots)
            return JoKenPoResult.VITORIA

        val playerChooseTheSamePlayAsOtherBots = playerChoose == escolhaBot1
                && playerChoose == escolhaBot2

        val playerChooseTheSamePlayAsOtherBot1AndNotLoseForTheBot2 = playerChoose == escolhaBot1
                && playerChoose == escolhaBot2.counter()

        val playerChooseTheSamePlayAsOtherBot2AndNotLoseForTheBot1 = playerChoose == escolhaBot2
                && playerChoose == escolhaBot1.counter()

        if (
            playerChooseTheSamePlayAsOtherBots ||
            playerChooseTheSamePlayAsOtherBot1AndNotLoseForTheBot2 ||
            playerChooseTheSamePlayAsOtherBot2AndNotLoseForTheBot1
        )
            return JoKenPoResult.EMPATE

        return JoKenPoResult.DERROTA
    }

    fun getEscolhaBot1(): JoKenPoPlayType {
        return escolhaBot1
    }

    fun getEscolhaBot2(): JoKenPoPlayType {
        return escolhaBot2
    }

    fun resetGame() {
        escolhaBot1 = randomJokenpoPlayType()
        escolhaBot2 = randomJokenpoPlayType()
        result = null
    }
}