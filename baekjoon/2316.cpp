#include <algorithm>
#include <cstring>
#include <iostream>
#include <queue>
#include <vector>
using namespace std;

typedef long long ll;

const int MAX_V = 803;
int N, P;
int c[MAX_V][MAX_V], f[MAX_V][MAX_V];
int level[MAX_V];
int work[MAX_V];
vector<int> graph[MAX_V];
int S = 2, E = 2 * 2;

bool bfs() {
  fill(level, level + MAX_V, -1);
  level[S] = 0;
  queue<int> q;
  q.push(S);
  while (!q.empty()) {
    int cur = q.front();
    q.pop();
    for (int next : graph[cur]) {
      if (level[next] == -1 && c[cur][next] - f[cur][next] > 0) {
        level[next] = level[cur] + 1;
        q.push(next);
      }
    }
  }
  return level[E] != -1;
}

int dfs(int cur, int flow) {
  if (cur == E)
    return flow;
  for (int &i = work[cur]; i < graph[cur].size(); i++) {
    int next = graph[cur][i];
    int able = c[cur][next] - f[cur][next];
    if (level[next] == level[cur] + 1 && able > 0) {
      int df = dfs(next, min(able, flow));
      if (df > 0) {
        f[cur][next] += df;
        f[next][cur] -= df;
        return df;
      }
    }
  }
  return 0;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> P;
  for (int i = 1; i <= N; i++) {
    c[i * 2][i * 2 + 1] = (i <= 2) ? 1e9 : 1;
    graph[i * 2].push_back(i * 2 + 1);
    graph[i * 2 + 1].push_back(i * 2);
  }
  while (P--) {
    int u, v;
    cin >> u >> v;
    c[u * 2 + 1][v * 2]++;
    graph[u * 2 + 1].push_back(v * 2);
    graph[v * 2].push_back(u * 2 + 1);

    c[v * 2 + 1][u * 2]++;
    graph[v * 2 + 1].push_back(u * 2);
    graph[u * 2].push_back(v * 2 + 1);
  }
  int total = 0;
  while (bfs()) {
    fill(work, work + MAX_V, 0);
    while (1) {
      int flow = dfs(S, 1e9);
      if (flow == 0)
        break;
      total += flow;
    }
  }
  cout << total;
  return 0;
}