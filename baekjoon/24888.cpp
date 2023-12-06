#include <iostream>
#include <vector>
#include <queue>

using namespace std;

typedef long long int ll;
typedef pair<ll, ll> pll;
ll INF = 9223372036854775807;

typedef struct Data_ {
	int node;
	ll dist;
	int code;
} Data;

struct cmp {
	bool operator()(Data a, Data b) {
		if (a.dist == b.dist)
			return a.code < b.code;
		else
			return a.dist > b.dist;
	}
};

int N, M;
vector<pll> graph[200001];
int path[200001];
pll dist[200001];
int hasPiece[200001];
priority_queue<Data, vector<Data>, cmp> pq;
int main() {
	cin.tie(0);
	ios_base::sync_with_stdio(false);
	cout.tie(0);
	cin >> N >> M;
	for (int i = 1; i <= N; i++ ){
		dist[i] = make_pair(INF, 0);
	}
	int u, v;
	ll w;
	for (int i = 0 ; i < M; i++) {
		cin >> u >> v >> w;
		graph[u].push_back(make_pair(v, w));
		graph[v].push_back(make_pair(u, w));
	}
	int total = 0;
	for (int i = 1; i <= N; i++) {
		cin >> hasPiece[i];
		total += hasPiece[i];
	}
	dist[1] = make_pair(0, hasPiece[1]);
	path[1] = 1;
	pq.push({ 1, 0, hasPiece[1] });
	while (!pq.empty()) {
		int cur = pq.top().node;
		ll d = pq.top().dist;
		int code = pq.top().code;
		pq.pop();
		if (dist[cur].first < d)
			continue;
		if (dist[cur].first == d && dist[cur].second > code)
			continue;
		for (auto n : graph[cur]) {
			int next = n.first;
			ll nextDist = d + n.second;
			int nextCode = code + hasPiece[next];
			if (dist[next].first > nextDist || (dist[next].first == nextDist && dist[next].second < nextCode)) {
				dist[next] = make_pair(nextDist, nextCode);
				path[next] = cur;
				pq.push({ next, nextDist, nextCode });
			}
		}
	}
	vector<int> route;
	int cnt = 0;
	route.push_back(N);
	int cur = N;
	while (path[cur] != cur) {
		cnt += hasPiece[cur];
		cur = path[cur];
		route.push_back(cur);
	}
	cnt += hasPiece[cur];
	if (cnt == total) {
		cout << route.size() << "\n";
		for (auto iter = route.rbegin(); iter != route.rend(); iter++) {
			cout << *iter << " ";
		}
	} else {
		cout << -1;
	}
}
