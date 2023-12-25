#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;
int N, R, Q;

int dp[100001];
vector<int> graph[100001];

void dfs(int cur, int prev) {
  dp[cur] = 1;
  for (auto next : graph[cur]) {
    if (next == prev)
      continue;
    dfs(next, cur);
    dp[cur] += dp[next];
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> R >> Q;
  int u, v;

  for (int i = 0; i < N - 1; i++) {
    cin >> u >> v;
    graph[u].push_back(v);
    graph[v].push_back(u);
  }
  dfs(R, 0);
  for (int i = 0; i < Q; i++) {
    cin >> u;
    cout << dp[u] << "\n";
  }
  return 0;
}