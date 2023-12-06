#include <iostream>
#include <vector>
#include <queue>

using namespace std;

typedef long long int ll;
typedef pair<ll, ll> pll;
ll INF = 98765432100;

typedef struct Data_ {
	ll cost;
	ll node;
	int flag;
} Data;

struct cmp {
	bool operator()(Data a, Data b) {
		return a.cost > b.cost;
	}
};

int N, M;
vector<pll> graph[4001];
ll fox[4001];
ll wolf[2][4001];

int main() {
	cin.tie(0);
	ios_base::sync_with_stdio(false);
	cout.tie(0);
	cin >> N >> M;
	for (int i = 1; i <= N; i++ ){
		fox[i] = INF;
		wolf[0][i] = INF;
		wolf[1][i] = INF;
	}
	for (int i = 0; i < M; i++) {
		int a, b, d;
		cin >> a >> b >> d;
		graph[a].push_back(make_pair(b, d * 2));
		graph[b].push_back(make_pair(a, d * 2));
	}
	// 여우야
	priority_queue<Data, vector<Data>, cmp> pq;
	pq.push({0, 1,0});
	fox[1] = 0;
	while (!pq.empty()) {
		ll cur = pq.top().node;
		ll cost = pq.top().cost;
		int flag = pq.top().flag;
		pq.pop();
		if (fox[cur] < cost)
			continue;
		for (auto next : graph[cur]) {
			ll nextNode = next.first;
			ll nextCost = cost + next.second;
			if (fox[nextNode] > nextCost) {
				fox[nextNode] = nextCost;
				pq.push({nextCost, nextNode, flag});
			}
		}
	}
	// 늑대야
	priority_queue<Data, vector<Data>, cmp> pq2;
	pq2.push({0,1,0});
	wolf[0][1] = 0;
	while (!pq2.empty()) {
		ll cur = pq2.top().node;
		ll cost = pq2.top().cost;
		int flag = pq2.top().flag;
		pq2.pop();
		if (wolf[flag][cur] < cost)
			continue;

		for (auto next : graph[cur]) {
			ll nextNode = next.first;
			ll nextCost = cost + (flag ? next.second * 2 : next.second / 2);
			if (wolf[(flag + 1) % 2][nextNode] > nextCost) {
				wolf[(flag + 1) % 2][nextNode] = nextCost;
				pq2.push({nextCost, nextNode, (flag + 1) % 2});
			}
		}
	}
	int cnt = 0;
	for (int i = 1; i <= N; i ++) {
		if (fox[i] < wolf[0][i] && fox[i] < wolf[1][i])
			cnt++;
	}
	cout << cnt << "\n";
}
