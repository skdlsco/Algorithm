package `29791`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toLong() }
    val A = reader.readLine().split(" ").map { it.toInt() }.sorted()
    val B = reader.readLine().split(" ").map { it.toInt() }.sorted()
    var aCnt = 0
    var coolDown = -1
    var imm = -1
    for (a in A) {
        if (a >= coolDown) {
            coolDown = a + 100
            if (a >= imm) {
                aCnt++
                imm = a + 90
            }
        }
    }

    var bCnt = 0
    coolDown = -1
    imm = -1
    for (b in B) {
        if (b >= coolDown) {
            coolDown = b + 360
            if (b >= imm) {
                bCnt++
                imm = b + 90
            }
        }
    }
    writer.write("${aCnt} ${bCnt}\n")
    writer.flush()
}
    