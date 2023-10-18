package `29728`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val sieve = BooleanArray(5000001) { true }
    sieve[1] = false
    for (i in 2..5000000) {
        if (sieve[i]) {
            for (j in i + i..5000000 step i) {
                sieve[j] = false
            }
        }
    }
    val deque = ArrayDeque<Char>()
    var isReversed = false
    deque.add('B')
    var bCnt = 1
    var sCnt = 0
    for (i in 2..N) {
        if (sieve[i]) {
            if (isReversed) {
                if (deque.first() == 'B') {
                    bCnt--
                    sCnt++
                }
                deque.removeFirst()
                deque.addFirst('S')
                deque.addFirst('S')
                sCnt++
            } else {
                if (deque.last() == 'B') {
                    bCnt--
                    sCnt++
                }
                deque.removeLast()
                deque.addLast('S')
                deque.addLast('S')
                sCnt++
            }
            isReversed = !isReversed
        } else {
            if (isReversed) {
                deque.addFirst('B')
            } else {
                deque.addLast('B')
            }
            bCnt++
        }
    }
    writer.write("${bCnt} ${sCnt}")
    writer.flush()
}
    