package `Main`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val lambda = reader.readLine().toInt()

    if (lambda >= 620)
        println("Red")
    else if (lambda >= 590)
        println("Orange")
    else if (lambda >= 570)
        println("Yellow")
    else if (lambda>= 495)
        println("Green")
    else if (lambda >=450)
        println("Blue")
    else if (lambda >= 425)
        println("Indigo")
    else
        println("Violet")
    writer.flush()
}
    