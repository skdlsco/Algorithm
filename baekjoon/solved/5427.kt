package `5427`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))

data class Pos(val x: Int, val y: Int)

data class Info(val turn: Int, val pos: Pos, val type: Type)
enum class Type {
    NONE, WALL, FIRE, PLAYER
}

val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)

class Simulation() {
    val w: Int
    val h: Int
    val map: Array<Array<Type>>
    var start: Pos = Pos(0, 0)
    val queue: Queue<Info>

    init {
        val stringTokenizer = StringTokenizer(reader.readLine())
        w = stringTokenizer.nextToken().toInt()
        h = stringTokenizer.nextToken().toInt()
        map = Array<Array<Type>>(h) { Array(w) { Type.NONE } }
        // turn, pos
        queue = LinkedList<Info>()
        // init
        for (y in 0 until h) {
            val line = reader.readLine()
            for (x in 0 until w) {
                val cell = when (line[x]) {
                    '#' -> Type.WALL
                    '*' -> {
                        queue.add(Info(0, Pos(x, y), Type.FIRE))
                        Type.FIRE
                    }

                    '@' -> {
                        start = Pos(x, y)
                        Type.PLAYER
                    }

                    else -> Type.NONE
                }
                map[y][x] = cell
            }
        }
        queue.add(Info(0, start, Type.PLAYER))
    }

    fun simulate(): Int {
        while (queue.isNotEmpty()) {
            // fire or player
            val info = queue.remove()
            val result = when (info.type) {
                Type.PLAYER -> doPlayer(info.turn, info.pos)
                else -> doFire(info.turn, info.pos)
            }
            if (result > 0)
                return result
        }
        return -1
    }

    private fun doPlayer(turn: Int, pos: Pos): Int {
        repeat(4) {
            val nextX = pos.x + dx[it]
            val nextY = pos.y + dy[it]

            if (nextX in 0 until w && nextY in 0 until h
                && map[nextY][nextX] == Type.NONE
            ) {
                queue.add(Info(turn + 1, Pos(nextX, nextY), Type.PLAYER))
                map[nextY][nextX] = Type.PLAYER
            }
            if (nextX !in 0 until w || nextY !in 0 until h) {
                return turn + 1
            }
        }
        return 0
    }

    private fun doFire(turn: Int, pos: Pos): Int {
        repeat(4) {
            val nextX = pos.x + dx[it]
            val nextY = pos.y + dy[it]

            if (nextX in 0 until w && nextY in 0 until h
                && (map[nextY][nextX] != Type.WALL && map[nextY][nextX] != Type.FIRE)
            ) {
                queue.add(Info(turn + 1, Pos(nextX, nextY), Type.FIRE))
                map[nextY][nextX] = Type.FIRE
            }
        }
        return 0
    }
}


fun main() {
    val T = reader.readLine().toInt()

    repeat(T) {
        val simulation = Simulation()
        val result = simulation.simulate()
        if (result == -1)
            println("IMPOSSIBLE")
        else
            println(result)
    }
}