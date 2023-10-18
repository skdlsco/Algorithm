package `28067`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.Comparator
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.sqrt

data class Data(val a: Int, val b: Int, val c: Int)

fun getLength(x1: Int, y1: Int, x2: Int, y2: Int): Int {
    return abs(x2 - x1) * abs(x2 - x1) + abs(y2 - y1) * abs(y2 - y1)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() + 1 }
    val set = TreeSet<Data>() { o1, o2 ->
        if (o1.a == o2.a) {
            if (o1.b == o2.b)
                o1.c - o2.c
            else
                o1.b - o2.b
        } else
            o1.a - o2.a
    }
    val dots = N * M
    for (a in 0 until dots - 2) {
        val ax = a % M
        val ay = a / M
        for (b in a + 1 until dots - 1) {
            val bx = b % M
            val by = b / M
            for (c in b + 1 until dots) {
                val cx = c % M
                val cy = c / M
                if ((ax == bx && bx == cx) || (ay == by && by == cy))
                    continue
                val lengthArr = Array<Int>(3) { 0 }
                lengthArr[0] = getLength(ax, ay, bx, by)
                lengthArr[1] = getLength(bx, by, cx, cy)
                lengthArr[2] = getLength(cx, cy, ax, ay)
                lengthArr.sort()
                if ((by - ay) * (cx - bx) == (cy - by) * (bx - ax))
                    continue
                set.add(Data(lengthArr[0], lengthArr[1], lengthArr[2]))
            }
        }
    }
    writer.write("${set.size}")
    writer.flush()
}