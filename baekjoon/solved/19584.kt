package `19584`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

// 스위핑 시간초과 나네..

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val node = Array<Int>(N + 1) { 0 }
    repeat(N) {
        val (_, y) = reader.readLine().split(" ").map { it.toInt() }
        node[it + 1] = y
    }
    val arr = Array<Pair<Int, Long>>(M * 2) { Pair(0, 0L) }
    repeat(M) {
        val (u, v, c) = reader.readLine().split(" ").map { it.toInt() }
        val s = minOf(node[u], node[v])
        val e = maxOf(node[u], node[v])
        arr[it * 2] = Pair(s, c.toLong())
        arr[it * 2 + 1] = Pair(e, -c.toLong())
    }
    arr.sortWith() { o1, o2 ->
        if (o1.first == o2.first && o1.second > o2.second)
            -1
        else
            o1.first - o2.first
    }
    var sum = 0L
    var ans = sum
    for ((_, c) in arr) {
        sum += c
        ans = max(ans, sum)
    }
    writer.write("${ans}\n")
    writer.flush()
}

//c++ 17 정답
//#include <algorithm>
//#include <iostream>
//using namespace std;
//typedef pair<long, long> pll;
//int N, M;
//int node[200001];
//pll arr[400001];
//
//bool cmp(const pll &p1, const pll &p2) {
//    if (p1.first == p2.first) {
//        return p1.second > p2.second;
//    } else {
//        return p1.first < p2.first;
//    }
//}
//
//int main() {
//    ios_base::sync_with_stdio(false);
//    cin.tie(NULL);
//    cout.tie(NULL);
//
//    cin >> N >> M;
//
//    for (int i = 1; i <= N; i++) {
//        int x, y;
//        cin >> x >> y;
//        node[i] = y;
//    }
//
//    for (int i = 0; i < M; i++) {
//        int u, v;
//        long long c;
//        cin >> u >> v >> c;
//        if (node[u] > node[v]) {
//            int temp = u;
//            u = v;
//            v = temp;
//        }
//        arr[i * 2] = {node[u], c};
//        arr[i * 2 + 1] = {node[v], -c};
//    }
//    sort(arr, arr + M * 2, cmp);
//    long long sum = 0;
//    long long ans = 0;
//    for (int i = 0; i < M * 2; i++) {
//        sum += arr[i].second;
//        if (ans < sum) ans = sum;
//    }
//    cout << ans << "\n";
//}