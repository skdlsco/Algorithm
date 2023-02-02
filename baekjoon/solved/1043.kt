package `1043`

import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val (N, M) = readLine()!!.split(" ").map { it.toInt() }
    val knownList = Array<Boolean>(N) { false }
    val groupList = ArrayList<LinkedList<Int>>()
    var list = readLine()!!.split(" ").map { it.toInt() }
    list.slice(1 until list.size).forEach {
        knownList[it - 1] = true
    }
    repeat(N) {
        groupList.add(LinkedList<Int>().apply { add(it) })
    }
    val partyList = ArrayList<Array<Int>>()
    repeat(M) {
        var party = readLine()!!.split(" ").map { it.toInt() - 1 }.toTypedArray()
        party = party.sliceArray(1 until party.size)
        partyList.add(party)
        val joinList = ArrayList<LinkedList<Int>>()
        groupList.forEach { g ->
            for (p in party) {
                if (g.indexOf(p) != -1) {
                    joinList.add(g)
                    break
                }
            }
        }
        groupList.removeAll(joinList)
        val added = Array<Boolean>(N) { false }
        val group = LinkedList<Int>()
        joinList.forEach {
            it.forEach {
                if (!added[it]) {
                    group.add(it)
                    added[it] = true
                }
            }
        }
        groupList.add(group)
    }
    groupList.forEach {
        if (it.any { knownList[it] }) {
            it.forEach {
                knownList[it] = true
            }
        }
    }
    println(partyList.count {
        it.all { !knownList[it] }
    })
}