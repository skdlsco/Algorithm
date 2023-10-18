package `1927`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList

private class Heap<T : Comparable<T>>(capacity: Int = 1) {
    private val arr = ArrayList<T?>(capacity).apply { add(null) }
    val size: Int
        get() = arr.lastIndex

    private fun swap(idx1: Int, idx2: Int) {
        val temp = arr[idx1]
        arr[idx1] = arr[idx2]
        arr[idx2] = temp
    }

    fun push(element: T) {
        arr.add(element)
        var now = arr.lastIndex
        while (now > 1 && arr[now / 2]!! > element) {
            swap(now, now / 2)
            now /= 2
        }
    }

    fun pop(): T? {
        if (size < 1)
            return null
        val r = arr[1]
        val last = arr.last()
        arr[1] = last
        var now = 1
        while (now * 2 <= size) {
            if (arr[now]!! < arr[now * 2]!! && (now * 2 < size && arr[now]!! < arr[now * 2 + 1]!!))
                break
            val nextIdx = if (now * 2 < size && arr[now * 2]!! > arr[now * 2 + 1]!!)
                now * 2 + 1
            else
                now * 2
            if (nextIdx == arr.lastIndex)
                break
            swap(now, nextIdx)
            now = nextIdx
        }
        arr.removeLast()
        return r
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val stringTokenizer = StringTokenizer(reader.readLine())
    val N = stringTokenizer.nextToken().toInt()
    val heap = Heap<Int>()
    repeat(N) {
        val input = reader.readLine().toInt()
        if (input == 0) {
            val v = heap.pop()
            if (v == null) println(0) else println(v)
        } else
            heap.push(input)
    }
}