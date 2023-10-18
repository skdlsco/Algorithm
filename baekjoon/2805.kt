package `2805`

import java.util.*
import kotlin.math.max

fun main() {
    val scanner = Scanner(System.`in`)
    val N = scanner.nextInt()
    val M = scanner.nextInt()
    val trees = Array<Int>(N) { 0 }
    var maxHeight = 0L
    var midHeight = 0L
    var minHeight = 0L

    repeat(N) {
        trees[it] = scanner.nextInt()
        if (trees[it] > maxHeight) maxHeight = trees[it].toLong()
    }

    while (minHeight < maxHeight) {
        var cutting = 0L
        midHeight = (maxHeight + minHeight) / 2
        if (midHeight == minHeight)
            break
        trees.forEach {
            if (it > midHeight)
                cutting += it - midHeight
        }
        if (cutting > M)
            minHeight = midHeight
        else if (cutting < M)
            maxHeight = midHeight
        else break
    }
    if (trees.size == 1)
        println(trees.first() - M)
    else
        println(midHeight)
}
