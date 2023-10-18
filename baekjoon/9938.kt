package `9938`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val groupCapacities = Array<Int>(300001) { 1 }
val nodeGroup = Array<Int>(300001) { 0 }

fun getGroupRoot(node: Int): Int {
    if (nodeGroup[node] == node)
        return node
    val root = getGroupRoot(nodeGroup[node])
    nodeGroup[node] = root
    return root
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val (N, L) = reader.readLine().split(" ").map { it.toInt() }

    repeat(N) {
        val (a, b) = reader.readLine().split(" ").map { it.toInt() }
        // a와 b각각 그룹 체크 -> 비어있는 경우 -> 새그룹 생성
        // merge -> 그룹 id가 더 작은 그룹으로 합친다.

        // 새그룹
        if (nodeGroup[a] == 0)
            nodeGroup[a] = a
        if (nodeGroup[b] == 0)
            nodeGroup[b] = b

        var aRoot = getGroupRoot(a)
        var bRoot = getGroupRoot(b)
        if (aRoot > bRoot) {
            val temp = aRoot
            aRoot = bRoot
            bRoot = temp
        }
        var failure = false
        if (groupCapacities[aRoot] == 0 && groupCapacities[bRoot] == 0)
            failure = true
        else if (aRoot == bRoot)
            groupCapacities[aRoot]--
        else {
            // 그룹 merge
            nodeGroup[bRoot] = aRoot
            groupCapacities[aRoot] = groupCapacities[bRoot] + groupCapacities[aRoot] - 1
        }
        if (failure)
            writer.write("SMECE\n")
        else
            writer.write("LADICA\n")
    }
    writer.flush()
}