package lazarini.lucas.pedrapapeltesoura.utils

enum class JoKenPoPlayType(val play: String) {
    PEDRA("\uD83D\uDC4A"),
    PAPEL("✋"),
    TESOURA("✌\uFE0F"),
    LAGARTO("\uD83E\uDD8E"),
    SPOCK("\uD83D\uDD96");

    fun counters(): List<JoKenPoPlayType> {
        return when(this) {
            PEDRA -> listOf(PAPEL, SPOCK)
            PAPEL -> listOf(TESOURA, LAGARTO)
            TESOURA -> listOf(PEDRA, SPOCK)
            LAGARTO -> listOf(TESOURA, PEDRA)
            SPOCK -> listOf(PAPEL, LAGARTO)
        }
    }

    fun countereds(): List<JoKenPoPlayType> {
        return when(this) {
            PEDRA -> listOf(TESOURA, LAGARTO)
            PAPEL -> listOf(PEDRA, SPOCK)
            TESOURA -> listOf(PAPEL, LAGARTO)
            LAGARTO -> listOf(PAPEL, SPOCK)
            SPOCK -> listOf(PEDRA, TESOURA)
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