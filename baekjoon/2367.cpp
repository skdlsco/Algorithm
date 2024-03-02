#include <algorithm>
#include <cstring>
#include <iostream>
#include <queue>
#include <vector>

using namespace std;
typedef pair<int, int> pii;

const int MAX_V = 401;
int N, K, D;
int level[MAX_V];
int c[MAX_V][MAX_V];
int f[MAX_V][MAX_V];
vector<int> graph[MAX_V];
int work[MAX_V];
// 0 = S
// 1 ~ N 사람
// N + 1 ~ N + D 음식
// N + D + 1 = E
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
  if (cur == E) return flow;
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
  cin >> N >> K >> D;
  S = 0;
  E = N + D + 1;
  for (int i = 1; i <= D; i++) {
    int w;
    cin >> w;
    graph[N + i].push_back(E);
    graph[E].push_back(N + i);
    c[N + i][E] += w;
  }
  for (int i = 1; i <= N; i++) {
    int z;
    cin >> z;
    graph[S].push_back(i);
    graph[i].push_back(S);
    c[S][i] += K;
    for (int j = 1; j <= z; j++) {
      int d;
      cin >> d;
      graph[i].push_back(N + d);
      graph[N + d].push_back(i);
      c[i][N + d] += 1;
    }
  }
  int total = 0;
  while (bfs()) {
    fill(work, work + MAX_V, 0);
    int flow = dfs(S, 1e9);
    if (flow == 0) break;
    total += flow;
  }
  cout << total;
  return 0;
}
