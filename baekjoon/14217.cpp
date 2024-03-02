#include <algorithm>
#include <iostream>
#include <vector>
#include <queue>
#include <cstring>

using namespace std;

int N, M, Q;
vector<int> graph[501];

void bfs() {
	queue<pair<int, int>> q;
	int visited[501];
	memset(visited, 0, sizeof(visited));
	visited[1] = 1;
	q.push({1, 1});
	while (!q.empty()) {
		int cur = q.front().first;
		int dist = q.front().second;
		q.pop();
		for (int next : graph[cur]) {
			if (!visited[next]) {
				q.push({next, dist + 1});
				visited[next] = dist;
			}
		}
	}
	cout << 0 << " ";
	for (int i = 2; i <= N; i++) {
		cout << (visited[i] ? visited[i] : -1)  << " ";
	}
	cout << "\n";
}

int main() {
	cin.tie(0);
	cout.tie(0);
	ios_base::sync_with_stdio(false);
	cin >> N >> M;
	while (M--) {
		int i, j;
		cin >> i >> j;
		graph[i].push_back(j);
		graph[j].push_back(i);
	}
	cin >> Q;
	while (Q--) {
		int a, i, j;
		cin >> a >> i >> j;
		if (a == 2) {
			graph[i].erase(find(graph[i].begin(), graph[i].end(), j));
			graph[j].erase(find(graph[j].begin(), graph[j].end(), i));
		} else {
			graph[i].push_back(j);
			graph[j].push_back(i);
		}
		bfs();
	}
}
