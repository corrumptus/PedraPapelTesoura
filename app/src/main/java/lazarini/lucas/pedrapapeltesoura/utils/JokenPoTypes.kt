package lazarini.lucas.pedrapapeltesoura.utils

enum class JoKenPoPlayType(val play: String) {
    PEDRA("\uD83D\uDC4A"),
    PAPEL("✋"),
    TESOURA("✌\uFE0F");

    fun counter(): JoKenPoPlayType {
        return when(this) {
            JoKenPoPlayType.PEDRA -> JoKenPoPlayType.PAPEL
            JoKenPoPlayType.PAPEL -> JoKenPoPlayType.TESOURA
            JoKenPoPlayType.TESOURA -> JoKenPoPlayType.PEDRA
        }
    }

    fun countered(): JoKenPoPlayType {
        return when(this) {
            JoKenPoPlayType.PEDRA -> JoKenPoPlayType.TESOURA
            JoKenPoPlayType.PAPEL -> JoKenPoPlayType.PEDRA
            JoKenPoPlayType.TESOURA -> JoKenPoPlayType.PAPEL
        }
    }
}

enum class JoKenPoVersion(val version: Int) {
    VS_1_BOT(1),
    VS_2_BOT(2)
}

enum class JoKenPoResult(val result: String) {
    VITORIA("Vitória"),
    EMPATE("Empate"),
    DERROTA("Derrota")
}