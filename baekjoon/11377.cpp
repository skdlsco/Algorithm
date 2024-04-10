#include <algorithm>
#include <cstring>
#include <iostream>
#include <queue>
#include <vector>
using namespace std;

typedef long long ll;

const int MAX_V = 2020;

int N, M, K;

// 0 = S
// 1 = 벌점node
// 2 ~ N + 1 = 직원
// N + 2 ~ N + M + 1 = 일
// N + M + 2 = E
int c[MAX_V][MAX_V];
int f[MAX_V][MAX_V];
vector<int> graph[MAX_V];
int level[MAX_V];
int work[MAX_V];
int S, E;

bool bfs() {
  fill(level, level + MAX_V, -1);
  queue<int> q;
  level[S] = 0;
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
  cin >> N >> M >> K;
  S = 0;
  E = N + M + 2;
  graph[S].push_back(1);
  graph[1].push_back(S);
  c[S][1] = K;
  for (int i = 1; i <= M; i++) {
    graph[E].push_back(N + i + 1);
    graph[N + i + 1].push_back(E);
    c[N + i + 1][E] = 1;
  }
  for (int i = 1; i <= N; i++) {
    int C;
    cin >> C;
    while (C--) {
      int w;
      cin >> w;
      graph[i + 1].push_back(N + w + 1);
      graph[N + w + 1].push_back(i + 1);
      c[i + 1][N + w + 1] = 1;
    }
    graph[S].push_back(i + 1);
    graph[i + 1].push_back(S);
    c[S][i + 1] = 1;
    graph[1].push_back(i + 1);
    graph[i + 1].push_back(1);
    c[1][i + 1] = 1;
  }
  int total = 0;
  while (bfs()) {
    fill(work, work + MAX_V, 0);
    while (1) {
      int flow = dfs(S, 1e9);
      if (!flow)
        break;
      total += flow;
    }
  }
  cout << total;
  return 0;
}