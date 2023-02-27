package `16434`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.min

data class Info(val type: Int, val atk: Long, val hp: Long)

fun simulate(dungeon: List<Info>, defaultAtk: Long, maxHp: Long): Boolean {
    var curHp = maxHp
    var curAtk = defaultAtk
    for (info in dungeon) {
        if (info.type == 1) {
            val turn = info.hp / curAtk + if (info.hp % curAtk > 0) 1 else 0
            curHp -= (turn - 1) * info.atk
            if (curHp <= 0)
                return false
        } else {
            curAtk += info.atk
            curHp = min(maxHp, curHp + info.hp)
        }
    }
    return true
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, defaultAtk) = reader.readLine().split(" ").map { it.toLong() }
    val dungeon = ArrayList<Info>(N.toInt())

    repeat(N.toInt()) {
        val stringTokenizer = StringTokenizer(reader.readLine())
        val type = stringTokenizer.nextToken().toInt()
        val atk = stringTokenizer.nextToken().toLong()
        val hp = stringTokenizer.nextToken().toLong()
        dungeon.add(Info(type, atk, hp))
    }

    var left = 0L
    var right = Long.MAX_VALUE
    while (left < right) {
        val mid = left / 2 + right / 2
        if (simulate(dungeon, defaultAtk, mid)) {
            right = mid
        } else
            left = mid + 1
    }
    println(right)
}