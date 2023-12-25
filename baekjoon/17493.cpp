#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

int N, M;
vector<int> graph[200001];
int visited[200001];
// 0 켜기, 1 끄기(자식중 1개이상 켜짐), 2 다음에 반드시 키기
int dp[200001][3];

void dfs(int cur, int prev) {
  visited[cur] = 1;
  int sum = 0;
  for (int next : graph[cur]) {
    if (prev == next) continue;
    dfs(next, cur);
    dp[cur][0] += min(dp[next][0], min(dp[next][1], dp[next][2]));
    sum += min(dp[next][0], dp[next][1]);
    dp[cur][2] += min(dp[next][0], dp[next][1]);
  }
  dp[cur][0]++;
  dp[cur][1] = 200001;
  for (int next : graph[cur]) {
    if (prev == next) continue;
    dp[cur][1] =
        min(dp[cur][1], sum + dp[next][0] - min(dp[next][0], dp[next][1]));
  }
}

int main() {
  cin >> N >> M;
  int v, u;
  for (int i = 0; i < M; i++) {
    cin >> v >> u;
    graph[v].push_back(u);
    graph[u].push_back(v);
  }

  int ans = 0;
  for (int i = 1; i <= N; i++) {
    if (!visited[i]) {
      dfs(i, 0);
      ans += min(dp[i][0], dp[i][1]);
    }
  }
  // for (int i = 1; i <= N; i++) {
  //   cout << i << ": " << dp[i][0] << "," << dp[i][1] << "," << dp[i][2] <<
  //   "\n";
  // }
  cout << ans;
  return 0;
}