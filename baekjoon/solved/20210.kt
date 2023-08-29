package `20210`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun cntZero(s: String): Int {
    var idx = 0
    while (idx < s.length) {
        if (s[idx] != '0')
            break
        idx++
    }
    return idx
}

open class Name(open val s: String) : Comparable<Name> {

    val numCompare = Comparator<Num> { o1, o2 ->
        var comp = o1.numberStr.length.compareTo(o2.numberStr.length)
        if (comp != 0)
            return@Comparator comp
        comp = o1.numberStr.compareTo(o2.numberStr)
        if (comp != 0)
            return@Comparator comp
        return@Comparator o1.zeroCnt.compareTo(o2.zeroCnt)
    }

    val strCompare = Comparator<String> { o1, o2 ->
        var o1Idx = 0
        var o2Idx = 0
        while (o1Idx < o1.length && o2Idx < o2.length) {
            var comp = o1[o1Idx].uppercaseChar().compareTo(o2[o2Idx].uppercaseChar())
            if (comp != 0)
                return@Comparator comp
            comp = o1[o1Idx].compareTo(o2[o2Idx])
            if (comp != 0)
                return@Comparator comp
            o1Idx++
            o2Idx++
        }
        return@Comparator o1.length.compareTo(o2.length)
    }

    override fun compareTo(other: Name): Int {
        return if (this is Num && other is Num)
            numCompare.compare(this, other)
        else if (this !is Num && other !is Num)
            strCompare.compare(this.s, other.s)
        else if (this is Num) -1
        else 1
    }

    override fun toString(): String {
        return s
    }
}

class Num(s: String) : Name(s) {
    val zeroCnt = cntZero(s)
    val numberStr = s.trimStart('0')
}

fun split(s: String): List<Name> {
    var prev = ' '
    val ret = ArrayList<String>()
    val sb = StringBuilder()
    for (c in s) {
        if (c.isDigit() xor prev.isDigit()) {
            if (sb.isNotBlank())
                ret.add(sb.toString())
            sb.clear()
        }
        sb.append(c)
        prev = c
    }
    if (sb.isNotBlank())
        ret.add(sb.toString())
    sb.clear()
    return ret.map {
        if (it.isNotBlank() && it[0].isDigit())
            Num(it)
        else
            Name(it)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val lines = (0 until N).map { reader.readLine() }.map { split(it) }
    val ans = lines.sortedWith() { o1, o2 ->
        var o1Idx = 0
        var o2Idx = 0
        while (o1Idx < o1.size && o2Idx < o2.size) {
            val comp = o1[o1Idx].compareTo(o2[o2Idx])
            if (comp != 0)
                return@sortedWith comp
            o1Idx++
            o2Idx++
        }
        o1.size.compareTo(o2.size)
    }
    println(ans.joinToString("\n") { it.joinToString("") })
    writer.flush()
}