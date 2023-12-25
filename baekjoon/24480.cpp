#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

int N, M, R;

vector<int> graph[100001];
int ans[100001];
int visited[100001];
int num = 1;
void dfs(int here) {
  visited[here] = 1;
  ans[here] = num++;
  for (auto next : graph[here]) {
    if (!visited[next]) {
      visited[next] = 1;
      dfs(next);
    }
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M >> R;

  int u, v;
  for (int i = 1; i <= M; i++) {
    cin >> u >> v;
    graph[u].push_back(v);
    graph[v].push_back(u);
  }
  for (int i = 1; i <= N; i++) {
    sort(graph[i].begin(), graph[i].end(), greater<int>());
  }
  dfs(R);
  for (int i = 1; i <= N; i++) {
    cout << ans[i] << "\n";
  }
  return 0;
}