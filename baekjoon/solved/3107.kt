package `3107`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val input = reader.readLine()

    val zeroPadding = StringBuilder()
    val num = StringBuilder()
    var block = 0
    for (i in input.indices) {
        if (input[i] != ':') {
            num.append(input[i])
        } else {
            if (num.isNotEmpty()) {
                zeroPadding.append(num.toString().padStart(4, '0'))
                block++
                num.clear()
            }
            zeroPadding.append(input[i])
        }
    }
    if (num.isNotEmpty()) {
        block++
        zeroPadding.append(num.toString().padStart(4, '0'))
    }
    val left = 8 - block
    val onlyZeroBlock = StringBuilder()
    for (i in 0 until left) {
        onlyZeroBlock.append("0000")
        if (i < left - 1)
            onlyZeroBlock.append(":")
    }
    val target = zeroPadding.indexOf("::")
    if (target != -1)
        zeroPadding.replace(target  + 1, target + 1, onlyZeroBlock.toString())

    writer.write(zeroPadding.trim(':').toString())
    writer.flush()
}