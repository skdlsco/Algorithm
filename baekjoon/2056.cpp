#include <algorithm>
#include <iostream>
#include <queue>
#include <vector>

using namespace std;

int N, M;
vector<int> graph[10001];
int cost[10001];
int dp[10001];
int inDegree[10001];
queue<int> q;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 1; i <= N; i++) {
    cin >> cost[i] >> M;
    for (int j = 0; j < M; j++) {
      int u;
      cin >> u;
      graph[u].push_back(i);
      inDegree[i]++;
    }
  }
  for (int i = 1; i <= N; i++) {
    if (inDegree[i] == 0) {
      q.push(i);
      dp[i] = cost[i];
    }
  }
  while (!q.empty()) {
    int cur = q.front();
    q.pop();
    for (auto next : graph[cur]) {
      dp[next] = max(dp[next], dp[cur] + cost[next]);
      if (!--inDegree[next])
        q.push(next);
    }
  }
  int ans = 0;
  for (int i = 1; i <= N; i++) {
    ans = max(ans, dp[i]);
  }
  cout << ans;
  return 0;
}