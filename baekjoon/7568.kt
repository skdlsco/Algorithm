package clear

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val count = scanner.nextInt()
    val array = ArrayList<Pair<Int, Int>>()

    repeat(count) {
        array.add(Pair(scanner.nextInt(), scanner.nextInt()))
    }

    array.forEachIndexed { index, pair ->
        var rank = 1
        array.forEachIndexed { i, p ->
            if (index != i && pair.first < p.first && pair.second < p.second)
                rank++
        }
        print("${rank} ")
    }
}