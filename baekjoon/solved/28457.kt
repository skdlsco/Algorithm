package `28457`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

enum class CellType {
    NORMAL, GOLD, START, ISLAND, DONATE, SPACE
}

data class Cell(val type: CellType, val v: Int, var isBought: Boolean)

enum class CardType {
    GET_MONEY, GIVE_MONEY, DONATE_MONEY, MOVE
}

data class Card(val type: CardType, val v: Int)

class Game(val board: LinkedList<Cell>, var money: Int, val w: Int, val goldCard: LinkedList<Card>) {
    var donatedMoney = 0
    var inIsland = false
    var inIslandTurn = 0

    fun moveCell(i: Int) {
        repeat(i) {
            var cur = board.removeFirst()
            if (cur.type == CellType.START)
                money += w
            board.addLast(cur)
        }
    }

    fun drawCard(): Boolean {
        val card = goldCard.removeFirst()
        val success: Boolean = when (card.type) {
            CardType.GET_MONEY -> {
                money += card.v
                true
            }

            CardType.GIVE_MONEY -> {
                if (money < card.v)
                    false
                else {
                    money -= card.v
                    true
                }
            }

            CardType.DONATE_MONEY -> {
                if (money < card.v)
                    false
                else {
                    money -= card.v
                    donatedMoney += card.v
                    true
                }
            }

            CardType.MOVE -> {
                move(card.v)
                true
            }
        }
        goldCard.addLast(card)
        return success
    }

    fun doCell(cell: Cell): Boolean {
        when (cell.type) {
            CellType.NORMAL -> {
                if (!cell.isBought && money >= cell.v) {
                    money -= cell.v
                    cell.isBought = true
                }
            }

            CellType.GOLD -> {
                if (!drawCard())
                    return false
            }

            CellType.ISLAND -> {
                inIsland = true
                inIslandTurn = 1
            }

            CellType.DONATE -> {
                money += donatedMoney
                donatedMoney = 0
            }

            CellType.SPACE -> {
                while (board.first.type != CellType.START) {
                    moveCell(1)
                }
            }

            CellType.START -> {}
        }
        return true
    }

    fun move(i: Int): Boolean {
        moveCell(i)
        return doCell(board.first)
    }

    fun startTurn(dice1: Int, dice2: Int): Boolean {
        if (inIsland) {
            if (dice1 == dice2 || inIslandTurn >= 3)
                inIsland = false
            inIslandTurn++
            return true
        }
        return move(dice1 + dice2)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (n, s, w, g) = reader.readLine().split(" ").map { it.toInt() }

    val goldCard = Array<Card>(g) {
        val (t, x) = reader.readLine().split(" ").map { it.toInt() }
        val type = when (t) {
            1 -> CardType.GET_MONEY
            2 -> CardType.GIVE_MONEY
            3 -> CardType.DONATE_MONEY
            else -> CardType.MOVE
        }
        Card(type, x)
    }
    var t = 0
    val board = Array<Cell>(n * 4 - 4) {
        var v = 0
        val type = if (it % (n - 1) == 0) {
            when (t++) {
                0 -> CellType.START
                1 -> CellType.ISLAND
                2 -> CellType.DONATE
                else -> CellType.SPACE
            }
        } else {
            val input = reader.readLine().split(" ")
            if (input[0] == "G")
                CellType.GOLD
            else {
                v = input[1].toInt()
                CellType.NORMAL
            }
        }
        Cell(type, v, false)
    }
    val game = Game(LinkedList<Cell>().apply { addAll(board) }, s - w, w, LinkedList<Card>().apply { addAll(goldCard) })
    val I = reader.readLine().toInt()
    var isLose = false
    for (i in 0 until I) {
        val (dice1, dice2) = reader.readLine().split(" ").map { it.toInt() }
        if (!game.startTurn(dice1, dice2)) {
            isLose = true
            break
        }
    }
    val allBought = board.all {
        if (it.type == CellType.NORMAL)
            it.isBought
        else true
    }
    if (!allBought)
        isLose = true
    writer.write(if (isLose) "LOSE" else "WIN")
    writer.newLine()
    writer.flush()
}