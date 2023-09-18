package `30038`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder
import kotlin.math.max

val dx = arrayOf(0, 0, -1, 1)
val dy = arrayOf(-1, 1, 0, 0)
val directionMap = "udlr"

data class Pos(val x: Int, val y: Int)
interface Component
class Empty : Component
class Wall : Component
class Goal : Component
class Monster(var hp: Int, var def: Int, val exp: Int) : Component
enum class State {
    DEFAULT, OVERDOSE, CLEAR
}

class Player(var pos: Pos) : Component {
    var atk = 5
    var range = 1
    var speed = 1
    var needExp: Int = 10
    var exp = 0
    var level = 1
    var ap = 0
    var targetAp = 0
    var drugCnt = 0
    var state = State.DEFAULT
}

class Game(val N: Int, val M: Int, val board: Array<Array<Component>>, val player: Player) {

    fun act(command: String) {
        if (player.state == State.CLEAR)
            return
        if (command == "w")
            waitTurn()
        else if (command == "c")
            clear()
        else if (command.length == 2 && command.startsWith("d"))
            useDrug(command.last())
        else if (command.length == 2 && command.startsWith("a"))
            attack(command.last())
        else
            move(command)
        if (player.state == State.OVERDOSE && player.ap >= player.targetAp)
            player.state = State.DEFAULT
    }

    private fun move(command: String) {
        val d = directionMap.indexOf(command)
        val ny = player.pos.y + dy[d] * player.speed
        val nx = player.pos.x + dx[d] * player.speed
        if (ny in 0 until N && nx in 0 until M && board[ny][nx] !is Monster && board[ny][nx] !is Wall) {
            player.pos = Pos(nx, ny)
            player.ap++
        }
    }

    private fun attack(last: Char) {
        if (player.state == State.OVERDOSE)
            return
        val d = directionMap.indexOf(last)
        player.ap += 3
        var pos = Pos(player.pos.x, player.pos.y)
        var gainExp = 0
        for (i in 0 until player.range) {
            pos = Pos(pos.x + dx[d], pos.y + dy[d])
            if (board[pos.y][pos.x] is Wall)
                break
            if (board[pos.y][pos.x] is Monster) {
                val monster = board[pos.y][pos.x] as Monster
                monster.hp -= max(0, player.atk - monster.def)
                if (monster.hp <= 0) {
                    board[pos.y][pos.x] = Empty()
                    gainExp += monster.exp
                }
            }
        }
        player.exp += gainExp
        while (player.exp >= player.needExp) {
            player.exp -= player.needExp
            player.atk += player.level
            player.range++
            player.level++
            player.needExp += 10
        }
    }

    private fun useDrug(last: Char) {
        if (player.state == State.OVERDOSE)
            return
        val d = if (last == 'u') 1 else -1
        player.speed = max(0, player.speed + d)
        player.ap += 2
        player.drugCnt++
        if (player.drugCnt == 5) {
            player.drugCnt = 0
            player.state = State.OVERDOSE
            player.targetAp = player.ap + 10
        }
    }

    private fun clear() {
        if (player.state == State.OVERDOSE)
            return
        if (board[player.pos.y][player.pos.x] is Goal)
            player.state = State.CLEAR
    }

    private fun waitTurn() {
        player.ap++
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("${player.level} ${player.exp}\n")
        sb.append("${player.ap}\n")
        val monsters = ArrayList<Int>()
        for (y in 0 until N) {
            for (x in 0 until M) {
                val cell = if (player.pos.y == y && player.pos.x == x)
                    'p'
                else when (board[y][x]) {
                    is Empty -> '.'
                    is Wall -> '*'
                    is Goal -> 'g'
                    is Monster -> {
                        monsters.add((board[y][x] as Monster).hp)
                        'm'
                    }

                    else -> '.'
                }
                sb.append(cell)
            }
            sb.append("\n")
        }
        sb.append(monsters.joinToString(" "))
        return sb.toString()
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val board = Array<Array<Component>>(N) { Array(M) { Empty() } }
    val player = Player(Pos(-1, -1))
    for (y in 0 until N) {
        val line = reader.readLine()
        for (x in 0 until M) {
            board[y][x] = when (line[x]) {
                '.' -> Empty()
                '*' -> Wall()
                'g' -> Goal()
                'm' -> Monster(0, 0, 0)
                'p' -> {
                    player.pos = Pos(x, y)
                    Empty()
                }

                else -> Empty()
            }
        }
    }
    val k = reader.readLine().toInt()
    val monsterHp = reader.readLine().split(" ").map { it.toInt() }
    val monsterDef = reader.readLine().split(" ").map { it.toInt() }
    val monsterExp = reader.readLine().split(" ").map { it.toInt() }
    var monsterIdx = 0
    for (y in 0 until N) {
        for (x in 0 until M) {
            if (board[y][x] is Monster) {
                board[y][x] = Monster(monsterHp[monsterIdx], monsterDef[monsterIdx], monsterExp[monsterIdx])
                monsterIdx++
            }
        }
    }
    val game = Game(N, M, board, player)
    val s = reader.readLine().toInt()
    val commands = reader.readLine().split(" ")
    for (command in commands) {
        game.act(command)
    }
    writer.write(game.toString())
    writer.flush()
}
    