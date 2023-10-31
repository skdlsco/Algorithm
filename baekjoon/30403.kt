package `B`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val line = reader.readLine()
    val colorMap = "roygbivROYGBIV"
    val colorCheck = Array<Boolean>(colorMap.length) { false }
    line.filter { colorMap.contains(it) }
            .map { colorMap.indexOf(it) }
            .forEach { colorCheck[it] = true }
    val canLower = (0 until 7).all { colorCheck[it] }
    val canBigger = (7 until 14).all { colorCheck[it] }
    if (canLower && canBigger)
        writer.write("YeS\n")
    else if (canLower)
        writer.write("yes\n")
    else if (canBigger)
        writer.write("YES\n")
    else
        writer.write("NO!\n")
    writer.flush()
}
    