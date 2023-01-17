package `1072`

import kotlin.math.floor

fun getNewZ(X: Double, Y: Double, game: Int): Int = floor(100 * (Y + game) / (X + game)).toInt()

fun main() {
    val scanner = java.util.Scanner(System.`in`)
    val X = scanner.nextDouble()
    val Y = scanner.nextDouble()
    val Z = getNewZ(X, Y, 0)
    var newZ = 0
    if (Z >= 99) {
        println(-1)
        return
    }

    var maxGame = X.toInt()
    var minGame = 0
    var midGame = 0
    while (minGame < maxGame) {
        midGame = (minGame + maxGame) / 2
        newZ = getNewZ(X, Y, midGame)
        if (Z < newZ)
            maxGame = midGame - 1
        else
            minGame = midGame + 1
    }
    if (getNewZ(X, Y, maxGame) > Z)
        println(maxGame)
    else
        println(maxGame + 1)
}