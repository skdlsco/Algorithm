package `1168`

import java.io.BufferedWriter
import java.io.OutputStreamWriter
import kotlin.math.ceil
import kotlin.math.log2
import kotlin.math.max
import kotlin.math.min

fun init(a: IntArray, tree: IntArray, node: Int, start: Int, end: Int) {
    if (start == end) {
        tree[node] = a[start]
    } else {
        init(a, tree, node * 2, start, (start + end) / 2)
        init(a, tree, node * 2 + 1, (start + end) / 2 + 1, end)
        tree[node] = tree[node * 2] + tree[node * 2 + 1]
    }
}

fun update(arr: IntArray, tree: IntArray, node: Int, start: Int, end: Int, index: Int, value: Int) {
    if (index < start || index > end) {
        return
    }
    if (start == end) {
        arr[index] = value
        tree[node] = value
        return
    }
    update(arr, tree, node * 2, start, (start + end) / 2, index, value)
    update(arr, tree, node * 2 + 1, (start + end) / 2 + 1, end, index, value)
    tree[node] = tree[node * 2] + tree[node * 2 + 1]
}

// return left~right 구간합
fun query(tree: IntArray, node: Int, start: Int, end: Int, left: Int, right: Int): Int {
    if (left > end || right < start) {
        return 0
    }
    if (left <= start && end <= right) {
        return tree[node]
    }
    val lsum = query(tree, node * 2, start, (start + end) / 2, left, right)
    val rsum = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right)
    return lsum + rsum
}

// return 구간합이 k가 되는 idx 반환
// left ~ right의 구간합이 k보다 작은 경우 -1 반환
fun find(tree: IntArray, node: Int, start: Int, end: Int, left: Int, right: Int, k: Int): Int {
    // leaf 일때
    if (start == end) {
        return if (k == 1)
            start
        else -1
    }

    val mid = (start + end) / 2
    val leftNode = node * 2
    val rightNode = node * 2 + 1
    val leftSum = query(tree, leftNode, start, mid, left, right)
    val rightSum  = query(tree, rightNode, mid + 1, end, left, right)
    // 구간에 남은 사람이 k보다 작은 경우 -1 반환
    if (leftSum + rightSum < k)
        return -1
    if (leftSum >= k)
        return find(tree, leftNode, start, mid, left, right, k)
    return find(tree, rightNode, mid + 1, end, left, right, k - leftSum)
}

fun main() {
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, K) = readLine()!!.split(" ").map { it.toInt() }
    val arr = IntArray(N) { 1 }
    // h = logN 올림 + 1
    // 트리 크기는 2^h
    val treeSize = 1 shl ceil(log2(N.toDouble())).toInt() + 1
    val tree = IntArray(treeSize)

    // 각 구간의 남은 사람의 수
    init(arr, tree, 1, 0, N - 1)


    var idx = 0
    writer.write("<")
    for (i in N downTo  1) {
        var step = K
        if (step > i) {
            step %= i
            if (step == 0)
                step = i
        }
        var nextIdx = find(tree, 1, 0, N - 1, idx, N - 1, step)
        if (nextIdx == -1) {
            // 오른쪽에 없음 1부터 다시 탐색해야함 이때 k에서 오른쪽 구간합을 빼야함
            // rightSum < step
            val rightSum = query(tree, 1, 0, N - 1, idx, N - 1)
            step -= rightSum
            nextIdx = find(tree, 1, 0, N - 1, 0, idx - 1, step)
        }
        idx = nextIdx
        writer.write("${nextIdx + 1}${if (i != 1)", " else ""}")
        // idx의 사람 제거
        update(arr, tree, 1, 0, N - 1, idx, 0)
    }
    writer.write(">")
    writer.flush()
}