package `2250`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

data class Node(var left: Node? = null, var right: Node? = null)

class Solution() {
    var nodeCnt = 1
    val levelInfo = Array<Pair<Int, Int>>(10101) { Pair(-1, -1) }

    fun dfs(node: Node, level: Int) {
        node.left?.let { dfs(it, level + 1) }
        if (levelInfo[level].first == -1)
            levelInfo[level] = Pair(nodeCnt, nodeCnt)
        levelInfo[level] =
            levelInfo[level].copy(
                minOf(levelInfo[level].first, nodeCnt),
                maxOf(levelInfo[level].second, nodeCnt)
            )
        nodeCnt++
        node.right?.let { dfs(it, level + 1) }
    }

    fun solve(N: Int, tree: Node): Pair<Int, Int> {
        dfs(tree, 1)
        var retLevel = 0
        var width = 0
        for (level in 1..N) {
            if (levelInfo[level].first == -1)
                break
            val curWidth = levelInfo[level].second - levelInfo[level].first + 1
            if (width < curWidth) {
                retLevel = level
                width = curWidth
            }
        }
        return Pair(retLevel, width)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val nodeArr = Array<Node>(N + 1) { Node() }
    val hasParent = BooleanArray(N + 1) { false }
    repeat(N) {
        val (cur, left, right) = reader.readLine().split(" ").map { it.toInt() }
        if (left != -1) {
            nodeArr[cur].left = nodeArr[left]
            hasParent[left] = true
        }
        if (right != -1) {
            nodeArr[cur].right = nodeArr[right]
            hasParent[right] = true
        }
    }
    val root = nodeArr[hasParent.indexOfLast { !it }]
    val result = Solution().solve(N, root)
    writer.write("${result.first} ${result.second}\n")
    writer.flush()
}