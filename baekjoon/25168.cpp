#include <algorithm>
#include <iostream>
#include <queue>
#include <vector>

using namespace std;
typedef pair<int, int> pii;

int N, M;
vector<pii> graph[10001];
int in[10001];
queue<int> qu;
int dp[10001];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  while (M--) {
    int S, E, W;
    cin >> S >> E >> W;
    graph[S].push_back({E, W});
    in[E]++;
  }
  for (int i = 1; i <= N; i++) {
    if (!in[i]) qu.push(i);
  }
  while (!qu.empty()) {
    int cur = qu.front();
    qu.pop();
    for (pii &node : graph[cur]) {
      int next = node.first;
      in[next]--;
      if (!in[next]) qu.push(next);
      dp[next] = max(dp[next], dp[cur] + node.second + (node.second >= 7));
    }
  }
  int ans = 0;
  for (int i = 1; i <= N; i++) {
    ans = max(ans, dp[i]);
  }
  cout << ans + 1 << "\n";
  return 0;
}
