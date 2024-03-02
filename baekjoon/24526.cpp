#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

int N, M;
int ans;
int num[100001];
int low[100001];
int hasCycle[100001];
int visited[100001];
int cnt = 1;
vector<int> graph[100001];

void dfs(int cur) {
  low[cur] = num[cur] = cnt++;
  visited[cur] = 1;
  for (int next : graph[cur]) {
    if (!num[next])
      dfs(next);
    if (visited[next])
      low[cur] = min(low[cur], low[next]);
    hasCycle[cur] |= hasCycle[next];
  }
  visited[cur] = 0;
  if (num[cur] != low[cur])
    hasCycle[cur] = 1;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  while (M--) {
    int u, v;
    cin >> u >> v;
    graph[u].push_back(v);
  }
  for (int i = 1; i <= N; i++) {
    if (!num[i])
      dfs(i);
  }
  int ans = 0;
  for (int i = 1; i <= N; i++) {
    ans += !hasCycle[i];
  }
  cout << ans;
}
