package `17081`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.Writer
import java.util.StringTokenizer
import java.util.TreeMap
import kotlin.math.max
import kotlin.math.min

data class Pos(val x: Int = 0, val y: Int = 0)

val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)
val commandMap = TreeMap<Char, Int>().apply {
    set('D', 0)
    set('U', 1)
    set('R', 2)
    set('L', 3)
}

interface Component

class Empty : Component
class Wall : Component
class Trap : Component {
    val damage = 5
}

interface Item : Component
class Weapon(val p: Int) : Item
class Armor(val p: Int) : Item
class Accessory(val type: AccessoryType) : Item
enum class AccessoryType {
    HR, RE, CO, EX, DX, HU, CU
}

class Player() {
    var maxHp = 20
    var curHp = 20
    var level = 1
    var maxExp = 5
    var curExp = 0
    var weapon: Weapon? = null
    var armor: Armor? = null
    var accessory: Array<Accessory?> = Array(4) { null }
    var defaultDamage = 2
    var defaultDefense = 2
    val damage: Int
        get() = (weapon?.p ?: 0) + defaultDamage
    val defense: Int
        get() = (armor?.p ?: 0) + defaultDefense

    var startPos: Pos = Pos()
    var pos = Pos()

    fun init(pos: Pos) {
        this.pos = pos
        startPos = pos.copy()
    }

    fun has(type: AccessoryType): Boolean {
        return accessory.any { it?.type == type }
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("LV : ${level}\n")
        sb.append("HP : ${max(curHp, 0)}/${maxHp}\n")
        sb.append("ATT : ${defaultDamage}+${weapon?.p ?: 0}\n")
        sb.append("DEF : ${defaultDefense}+${armor?.p ?: 0}\n")
        sb.append("EXP : ${curExp}/${maxExp}")
        return sb.toString()
    }
}

class Monster(val name: String, val damage: Int, val defense: Int, val maxHp: Int, val exp: Int, val isBoss: Boolean) :
    Component {
    var curHp = maxHp
}

class Game(val N: Int, val M: Int, val map: Array<Array<Component>>, val player: Player) {
    var message = "Press any key to continue."
    var turn = 0
    var isEnd = false

    private fun move(commandIdx: Int) {
        var nextPos = Pos(player.pos.x + dx[commandIdx], player.pos.y + dy[commandIdx])
        if (isOutOfMap(nextPos) || isWall(nextPos)) {
            nextPos = player.pos
        }
        player.pos = nextPos
    }

    fun doTurn(command: Char) {
        turn++
        val commandIdx = commandMap[command]!!
        move(commandIdx)
        doAction()
    }

    private fun doAction() {
        val cell = map[player.pos.y][player.pos.x]
        when (cell) {
            is Monster -> fight(cell)
            is Item -> openBox(cell)
            is Trap -> trapped(cell)
        }
    }

    private fun trapped(trap: Trap) {
        val damage = if (player.has(AccessoryType.DX)) 1 else trap.damage
        player.curHp -= damage
        if (player.curHp <= 0)
            died(trap)
    }

    private fun openBox(item: Item) {
        when (item) {
            is Weapon -> player.weapon = item
            is Armor -> player.armor = item
            is Accessory -> {
                if (!player.has(item.type)) {
                    for (i in player.accessory.indices) {
                        if (player.accessory[i] == null) {
                            player.accessory[i] = item
                            break
                        }
                    }
                }
            }
        }
        map[player.pos.y][player.pos.x] = Empty()
    }

    private fun died(reason: Component) {
        if (player.has(AccessoryType.RE)) {
            for (i in player.accessory.indices) {
                if (player.accessory[i]?.type == AccessoryType.RE)
                    player.accessory[i] = null
            }
            player.pos = player.startPos.copy()
            player.curHp = player.maxHp
            if (reason is Monster)
                reason.curHp = reason.maxHp
        } else {
            if (reason is Monster)
                message = "YOU HAVE BEEN KILLED BY ${reason.name}.."
            else
                message = "YOU HAVE BEEN KILLED BY SPIKE TRAP.."
            isEnd = true
        }
    }

    private fun fight(monster: Monster) {
        var isPlayerTurn = true
        var isPlayerFirstAttack = true
        var isMonsterFirstAttack = true

        if (monster.isBoss)
            encounterBoss()
        while (monster.curHp > 0 && player.curHp > 0) {
            if (isPlayerTurn) {
                val bonus = if (isPlayerFirstAttack) getBonusRate() else 1
                monster.curHp -= max(1, player.damage * bonus - monster.defense)
                isPlayerFirstAttack = false
            } else {
                if (!(monster.isBoss &&isMonsterFirstAttack && player.has(AccessoryType.HU)))
                    player.curHp -= max(1, monster.damage - player.defense)
                isMonsterFirstAttack = false
            }
            isPlayerTurn = !isPlayerTurn
        }
        if (player.curHp <= 0) {
            died(monster)
        }
        if (monster.curHp <= 0) {
            map[player.pos.y][player.pos.x] = Empty()
            player.curExp += (monster.exp * if (player.has(AccessoryType.EX)) 1.2 else 1.0).toInt()
            if (player.curExp >= player.maxExp)
                levelUp()
            if (player.accessory.any { it?.type == AccessoryType.HR }) {
                player.curHp = min(player.maxHp, player.curHp + 3)
            }
            if (monster.isBoss) {
                message = "YOU WIN!"
                isEnd = true
            }
        }
    }

    fun levelUp() {
        player.level++
        player.maxHp += 5
        player.curHp = player.maxHp
        player.defaultDamage += 2
        player.defaultDefense += 2
        player.maxExp = player.level * 5
        player.curExp = 0
    }

    fun getBonusRate(): Int {
        if (!player.has(AccessoryType.CO))
            return 1
        if (player.has(AccessoryType.DX))
            return 3
        return 2
    }

    fun encounterBoss() {
        if (player.has(AccessoryType.HU))
            player.curHp = player.maxHp
    }

    private fun isOutOfMap(pos: Pos): Boolean {
        return pos.x !in 0 until M || pos.y !in 0 until N
    }

    private fun isWall(pos: Pos): Boolean {
        return map[pos.y][pos.x] is Wall
    }

    fun printGameState(writer: Writer) {
        for (y in 0 until N) {
            for (x in 0 until M) {

                val c = if (player.curHp > 0 && x == player.pos.x && y == player.pos.y) {
                    '@'
                } else {
                    when (map[y][x]) {
                        is Empty -> '.'
                        is Trap -> '^'
                        is Monster -> if ((map[y][x] as Monster).isBoss) 'M' else '&'
                        is Item -> 'B'
                        is Wall -> '#'
                        else -> '.'
                    }
                }
                writer.write("${c}")
            }
            writer.write("\n")
        }
        writer.write("Passed Turns : ${turn}\n")
        writer.write(player.toString())
        writer.write("\n")
        writer.write(message)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }

    // map init
    val map = Array<Array<Component>>(N) { Array(M) { Empty() } }
    var monsterCnt = 1
    var boxCnt = 0
    val player = Player()
    var bossPos = Pos(0, 0)
    for (y in 0 until N) {
        val row = reader.readLine()
        for (x in 0 until M) {
            when (row[x]) {
                '#' -> map[y][x] = Wall()
                '^' -> map[y][x] = Trap()
                '@' -> player.init(Pos(x, y))
                'M' -> bossPos = Pos(x, y)
            }
            if (row[x] == '&')
                monsterCnt++
            if (row[x] == 'B')
                boxCnt++
        }
    }
    val command = reader.readLine()
    repeat(monsterCnt) {
        val tokenizer = StringTokenizer(reader.readLine())
        val R = tokenizer.nextToken().toInt() - 1
        val C = tokenizer.nextToken().toInt() - 1
        val S = tokenizer.nextToken()
        val W = tokenizer.nextToken().toInt()
        val A = tokenizer.nextToken().toInt()
        val H = tokenizer.nextToken().toInt()
        val E = tokenizer.nextToken().toInt()
        val isBoss = R == bossPos.y && C == bossPos.x
        map[R][C] = Monster(S, W, A, H, E, isBoss)
    }
    repeat(boxCnt) {
        val tokenizer = StringTokenizer(reader.readLine())
        val R = tokenizer.nextToken().toInt() - 1
        val C = tokenizer.nextToken().toInt() - 1
        val T = tokenizer.nextToken()
        map[R][C] = when (T) {
            "W" -> Weapon(tokenizer.nextToken().toInt())
            "A" -> Armor(tokenizer.nextToken().toInt())
            else -> Accessory(AccessoryType.valueOf(tokenizer.nextToken()))
        }
    }
    val game = Game(N, M, map, player)
    for (c in command) {
        game.doTurn(c)
        if (game.isEnd)
            break
    }
    game.printGameState(writer)
    writer.flush()
}
    