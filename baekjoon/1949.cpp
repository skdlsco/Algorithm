#include<iostream>
#include<vector>
using namespace std;

int N;
int cost[10001];
int dp[10001][2];
vector<int> graph[10001];

int max(int a, int b) {
	return a > b ? a : b;
}

void dfs(int cur, int prev) {
	int on = cost[cur];
	int off = 0;
	for (auto next: graph[cur]) {
		if (prev == next)
			continue;
		dfs(next, cur);
		off += max(dp[next][0], dp[next][1]);
		on += dp[next][0];
	}
	dp[cur][0] = off;
	dp[cur][1] = on;
}

int main()
{
	ios_base::sync_with_stdio(0); cin.tie(0);
	cin >> N;
	for (int i = 1 ; i <= N; i++) {
		cin >> cost[i];
	}
	int u, v;
	for (int i = 0 ; i < N - 1; i++) {
		cin >> u >> v;
		graph[u].push_back(v);
		graph[v].push_back(u);
	}
	dfs(1, 0);
	cout << max(dp[1][0], dp[1][1]) << "\n";
	return 0;
}
