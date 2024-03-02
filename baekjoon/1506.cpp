#include <algorithm>
#include <iostream>
#include <string>
#include <vector>
#include <stack>

using namespace std;

int N;
int cost[101];
string s;
vector<int> graph[101];
int counter = 1;
int node[101];
int low[101];
int visited[101];
stack<int> st;
vector<vector<int>> sccList;

void scc(int here) {
	st.push(here);
	node[here] = counter++;
	low[here] = node[here];
	visited[here] = 1;
	for (int next: graph[here]) {
		if (!node[next]) {
			scc(next);
			low[here] = min(low[here], low[next]);
		} else if (visited[next]) {
			low[here] = min(low[here], node[next]);
		}
	}
	if (low[here] == node[here]) {
		vector<int> group;
		while (!st.empty()){
			int cur = st.top();
			st.pop();
			group.push_back(cur);
			visited[cur] = 0;
			if (cur == here)
				break;
		}
		sccList.push_back(group);
	}
}

int main() {
	cin.tie(0);
	cout.tie(0);
	ios_base::sync_with_stdio(false);
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> cost[i];
	}
	for (int u = 0; u < N; u++) {
		cin >> s;
		for (int v = 0; v < N; v++) {
			if (s[v] == '1')
				graph[u].push_back(v);
		}
	}
	for (int i = 0; i < N; i++) {
		if (!node[i])
			scc(i);
	}
	int ans = 0;
	for (auto group: sccList) {
		int v = 1000001;
		for (auto u: group) {
			v = min(v, cost[u]);
		}
		ans += v;
	}
	cout << ans;
	return 0;
}
