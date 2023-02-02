package tutorial

import java.util.LinkedList
import java.util.Queue

fun topologicalSort(graph: Array<Queue<Int>>): ArrayList<Int> {
    val N = graph.size
    // 진입 차수 배열
    val inDegreeArr = Array<Int>(N) { 0 }
    graph.forEach { q ->
        q.forEach {
            inDegreeArr[it]++
        }
    }
    val queue: Queue<Int> = LinkedList()
    // 진입 차수가 0인 node를 queue에 넣는다
    // 진입 차수가 0이면 선행 조건이 없다는 의미
    inDegreeArr.forEachIndexed { i, inDegree ->
        if (inDegree == 0)
            queue.add(i)
    }
    val sortedArr = ArrayList<Int>(N)
    while (queue.isEmpty()) {
        val node = queue.remove()
        sortedArr.add(node)
        // 현재 노드에서 갈 수 있는 노드의 진입차수 차감
        // 진입차수가 0이 되면 queue에 넣는다
        graph[node].forEach {
            inDegreeArr[it]--
            if (inDegreeArr[it] == 0)
                queue.add(it)
        }
    }
    return sortedArr
}