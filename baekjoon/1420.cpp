#include <algorithm>
#include <iostream>
#include <queue>
#include <vector>

using namespace std;

typedef long long int ll;

struct Edge {
  int next;
  int ref;
  int cap;
  int flow;
  Edge(int next, int ref, int cap) : next(next), ref(ref), cap(cap), flow(0) {}
};
const int MAX_V = 20004;
const int dx[4] = {0, 0, 1, -1};
const int dy[4] = {1, -1, 0, 0};

int N, M;
vector<Edge> graph[MAX_V];
int level[MAX_V];
int work[MAX_V];
int S, E;
int map[101][101];

bool bfs() {
  fill(level, level + MAX_V, -1);
  queue<int> q;
  q.push(S);
  level[S] = 1;
  while (!q.empty()) {
    int cur = q.front();
    q.pop();
    for (Edge &edge : graph[cur]) {
      if (level[edge.next] == -1 && edge.cap - edge.flow > 0) {
        level[edge.next] = level[cur] + 1;
        q.push(edge.next);
      }
    }
  }
  return level[E] != -1;
}

int dfs(int cur, int flow) {
  if (cur == E)
    return flow;
  for (int &i = work[cur]; i < graph[cur].size(); i++) {
    Edge &edge = graph[cur][i];
    int next = edge.next;
    int able = edge.cap - edge.flow;
    if (level[next] == level[cur] + 1 && able > 0) {
      int df = dfs(next, min(able, flow));
      if (df > 0) {
        graph[cur][i].flow += df;
        graph[next][edge.ref].flow -= df;
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
  cin >> N >> M;
  for (int y = 0; y < N; y++) {
    for (int x = 0; x < M; x++) {
      char c;
      cin >> c;
      map[y][x] = c;
      int in = (y * M + x) * 2;
      int out = (y * M + x) * 2 + 1;
      graph[in].push_back(Edge(out, graph[out].size(), 1));
      graph[out].push_back(Edge(in, graph[in].size() - 1, 0));
    }
  }
  for (int y = 0; y < N; y++) {
    for (int x = 0; x < M; x++) {
      if (map[y][x] == '#')
        continue;
      if (map[y][x] == 'K') {
        S = (y * M + x) * 2 + 1;
      }
      if (map[y][x] == 'H') {
        E = (y * M + x) * 2;
      }
      for (int d = 0; d < 4; d++) {
        int ny = y + dy[d];
        int nx = x + dx[d];
        if (0 <= ny && ny < N && 0 <= nx && nx < M && map[ny][nx] != '#') {
          int in = (ny * M + nx) * 2;
          int out = (y * M + x) * 2 + 1;
          graph[out].push_back(Edge(in, graph[in].size(), 1));
          graph[in].push_back(Edge(out, graph[out].size() - 1, 0));
        }
        if ((map[y][x] == 'K' || map[y][x] == 'H') &&
            (map[ny][nx] == 'K' || map[ny][nx] == 'H')) {
          cout << "-1\n";
          return 0;
        }
      }
    }
  }
  int ans = 0;
  while (bfs()) {
    fill(work, work + MAX_V, 0);
    while (1) {
      int flow = dfs(S, 1e9);
      if (flow == 0)
        break;
      ans += flow;
    }
  }
  cout << ans << "\n";
  return 0;
}
