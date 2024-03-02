#include <algorithm>
#include <cstring>
#include <iostream>
#include <queue>
#include <vector>
using namespace std;

typedef long long ll;

const int MAX_V = 501;
int N, P;
int c[MAX_V][MAX_V], f[MAX_V][MAX_V];
int level[MAX_V];
int work[MAX_V];
vector<int> graph[MAX_V];
int S = 1, E = 2;

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
  while (P--) {
    int u, v;
    cin >> u >> v;
    c[u][v]++;
    graph[u].push_back(v);
    graph[v].push_back(u);
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