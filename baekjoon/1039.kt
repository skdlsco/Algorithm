package `1039`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.StringTokenizer

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val stringTokenizer = StringTokenizer(reader.readLine())
    val N = stringTokenizer.nextToken().toCharArray()
    val K = stringTokenizer.nextToken().toInt()
    if (N.size == 1 || (N.size == 2 && N.contains('0'))) {
        println(-1)
        return
    }
    val queue = LinkedList<Pair<CharArray, Int>>()
    queue.add(Pair(N, 0))
    val oddVisited = HashSet<String>()
    val evenVisited = HashSet<String>()
    evenVisited.add(N.joinToString(""))
    while (queue.isNotEmpty()) {
        val now = queue.pop()
        if (now.second < K) {
            for (i in 0 until N.size) {
                for (j in i + 1 until N.size) {
                    val next = now.first.clone()
                    val temp = next[i]
                    next[i] = next[j]
                    next[j] = temp
                    val nextNum = next.joinToString("")
                    if (now.second % 2 == 1 && !evenVisited.contains(nextNum)) {
                        queue.add(Pair(next, now.second + 1))
                        evenVisited.add(nextNum)
                    }
                    if (now.second % 2 == 0 && !oddVisited.contains(nextNum)) {
                        queue.add(Pair(next, now.second + 1))
                        oddVisited.add(nextNum)
                    }
                }
            }
        }
    }
    val result =
        if (K % 2 == 1)
            oddVisited.maxOf { it.toInt() }
        else
            evenVisited.maxOf { it.toInt() }
    println(result)
}