package `3780`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer
import kotlin.math.abs

fun findAndUpdateCenter(clusterCenter: Array<Int>, lineArr: Array<Int>, node: Int): Int {
    if (clusterCenter[node] == node)
        return node
    val next = clusterCenter[node]
    val center = findAndUpdateCenter(clusterCenter, lineArr, clusterCenter[node])
    clusterCenter[node] = center
    lineArr[node] += lineArr[next]
    return center
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    repeat(T) {
        val N = reader.readLine().toInt()
        val clusterCenter = Array<Int>(N + 1) { it }
        val lineArr = Array<Int>(N + 1) { 0 }

        while (true) {
            val stringTokenizer = StringTokenizer(reader.readLine())
            val command = stringTokenizer.nextToken()
            when (command) {
                "E" -> {
                    val target = stringTokenizer.nextToken().toInt()
                    findAndUpdateCenter(clusterCenter, lineArr, target)
                    writer.write("${lineArr[target]}\n")
                }

                "I" -> {
                    // I -> a의 센터
                    // J -> b의 기업 -> 센터를 찾아야함
                    val I = stringTokenizer.nextToken().toInt()
                    val J = stringTokenizer.nextToken().toInt()

                    // find JCenter
                    val JCenter = findAndUpdateCenter(clusterCenter, lineArr, J)
                    // merge I, JCenter
                    clusterCenter[I] = JCenter
                    lineArr[I] += lineArr[J] + (abs(J - I) % 1000)
                }

                "O" -> {
                    break
                }
            }
        }
    }
    writer.flush()
}