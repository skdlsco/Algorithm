#include <algorithm>
#include <cmath>
#include <iomanip>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;

int T, N, E;
vector<int> graph[101];
int visited[101];

int dfs(int cur, int depth) {
  if (depth == 2)
    return 0;
  int cnt = 0;
  for (int next : graph[cur]) {
    if (!visited[next])
      cnt++;
    visited[next] = 1;
    cnt += dfs(next, depth + 1);
  }
  return cnt;
}

void solve() {
  cin >> N >> E;

  for (int i = 1; i <= N; i++) {
    graph[i].clear();
    visited[i] = 0;
  }
  char c;
  int u, v;
  for (int i = 0; i < E; i++) {
    cin >> c >> u >> c >> v;
    graph[u].push_back(v);
    graph[v].push_back(u);
  }
  int start;
  cin >> c >> start;
  visited[start] = 1;
  int cnt = dfs(start, 0);
  cout << "The number of supervillains in 2-hop neighborhood of v" << start << " is " << cnt << "\n";
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  while (T--) {
    solve();
  }
  return 0;
}
