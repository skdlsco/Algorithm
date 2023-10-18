package `11651`

import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val scanner = Scanner(System.`in`)
    val count = scanner.nextInt()
    val array = ArrayList<Pair<Int, Int>>()

    repeat(count) {
        array.add(Pair(scanner.nextInt(), scanner.nextInt()))
    }
    array.sortWith(kotlin.Comparator { o1, o2 ->
        if (o1.second != o2.second)
            o1.second - o2.second
        else
            o1.first - o2.first
    })
    array.forEach {
        println("${it.first} ${it.second}")
    }
}