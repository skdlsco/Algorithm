package tutorial


// v -> 정점 개수
// dist[i][j] -> 정점 i에서 정점j로 가는 비용
fun floydWarshall(v: Int, dist: Array<Array<Long>>) {
    // m을 거쳐 가는 경로를 업데이트
    for (m in 1..v) {
        for (s in 1..v) {
            for (e in 1..v) {
                if (dist[s][e] > dist[s][m] + dist[m][e])
                    dist[s][e] = dist[s][m] + dist[m][e]
            }
        }
    }
}