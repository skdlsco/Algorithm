package tutorial

import kotlin.math.ceil
import kotlin.math.log2

// a input
// tree 세그먼트 트리
// node -> nodeIdx(트리의)
// start -> a의 start
// end -> a의 end

fun init(a: LongArray, tree: LongArray, node: Int, start: Int, end: Int) {
    if (start == end) {
        tree[node] = a[start]
    } else {
        init(a, tree, node * 2, start, (start + end) / 2)
        init(a, tree, node * 2 + 1, (start + end) / 2 + 1, end)
        tree[node] = tree[node * 2] + tree[node * 2 + 1] // 자식의 값의 합 (+가 아닌 다른 연산으로 바꿀 수 있겠죠)
    }
}

fun update(a: LongArray, tree: LongArray, node: Int, start: Int, end: Int, index: Int, value: Long) {
    if (index < start || index > end) {
        // 해당 노드가 update와 관련이 없는 경우
        return
    }
    if (start == end) {
        // leaf node 인경우
        a[index] = value
        tree[node] = value
        return
    }
    val mid = (start + end) / 2
    update(a, tree, node * 2, start, mid, index, value)
    update(a, tree, node * 2 + 1, mid + 1, end, index, value)
    tree[node] = tree[node * 2] + tree[node * 2 + 1]
}

fun query(tree: LongArray, node: Int, start: Int, end: Int, left: Int, right: Int): Long {
    if (left > end || right < start) {
        // 계산 구간에 포함되지 않는 경우
        return 0
    }
    // 구간 전체가 계산 구간에 포함된 경우 아래까지 내려갈 필요가 없다.
    if (left <= start && end <= right) {
        return tree[node]
    }
    val mid = (start + end) / 2
    val lsum = query(tree, node * 2, start, mid, left, right)
    val rsum = query(tree, node * 2 + 1, mid + 1, end, left, right)
    return lsum + rsum
}

fun main() {
    val N = 4
    val treeSize = 1 shl ceil(log2(N.toDouble())).toInt() + 1
    val a = LongArray(N) { it.toLong() + 1 }
    val tree = LongArray(treeSize) { 0 }
    init(a, tree, 1, 0, 3)
    update(a, tree, 1, 0, 3,2, 7)
    query(tree, 1, 0 ,3, 2, 3)
    println(tree)
}
