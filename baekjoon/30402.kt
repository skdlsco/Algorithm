package `A`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    for (y in 0 until 15) {
        val line = reader.readLine().split(" ")
        for (x in 0 until 15) {
            if (line[x] == "w") {
                println("chunbae")
                return
            } else if (line[x] == "b") {
                println("nabi")
                return
            } else if (line[x] == "g") {
                println("yeongcheol")
                return
            }
        }
    }
}
    