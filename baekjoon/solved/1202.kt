package baekjoon.solved

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue
import java.util.TreeMap

class Jewel(val weight: Int, val value: Int) : Comparable<Jewel> {

    override fun compareTo(other: Jewel): Int {
        return if (this.value == other.value)
            this.weight - other.value
        else
            other.value - this.value
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, K) = reader.readLine().split(" ").map { it.toInt() }
    val jewel = PriorityQueue<Jewel>()
    val bag = TreeMap<Int, Int>()
    repeat(N) {
        val (w, v) = reader.readLine().split(" ").map { it.toInt() }
        jewel.add(Jewel(w, v))
    }
    repeat(K) {
        val size = reader.readLine().toInt()
        bag[size] = (bag[size] ?: 0) + 1
    }
    var sum = 0L
    while (bag.isNotEmpty() && jewel.isNotEmpty()) {
        val j = jewel.remove()
        val key = bag.ceilingKey(j.weight)
        if (key != null && key >= j.weight) {
            bag[key] = bag[key]!! - 1
            if (bag[key] == 0)
                bag.remove(key)
            sum += j.value
        }
    }
    println(sum)
}