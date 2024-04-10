#include <algorithm>
#include <iostream>
#include <queue>
#include <vector>

using namespace std;

typedef long long int ll;

struct Edge {
  int next;
  int cap;
  int ref;
  int flow;
  Edge(int next, int cap, int ref) : next(next), cap(cap), ref(ref), flow(0) {}
};

const int MAX_V = 10005;

int N, T;
vector<Edge> graph[MAX_V];
int level[MAX_V];
int work[MAX_V];
int S = 0, E = MAX_V - 1;
int roomCapacity[3];

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
  if (cur == E) return flow;
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

void solve() {
  cin >> N;
  cin >> roomCapacity[0] >> roomCapacity[1] >> roomCapacity[2];
  for (int i = 0; i < MAX_V; i++) {
    graph[i].clear();
  }
  for (int i = 1; i <= N; i++) {
    graph[S].emplace_back(i, 1, (int)graph[i].size());
    graph[i].emplace_back(S, 0, (int)graph[S].size() - 1);
  }
  for (int room = 10001; room <= 10003; room++) {
    int M;
    cin >> M;
    for (int i = 0; i < M; i++) {
      int p;
      cin >> p;
      graph[p].emplace_back(room, 1, (int)graph[room].size());
      graph[room].emplace_back(p, 0, (int)graph[p].size() - 1);
    }
  }
  for (int room = 10001; room <= 10003; room++) {
    graph[room].emplace_back(E, roomCapacity[room - 10001],
                             (int)graph[E].size());
    graph[E].emplace_back(room, 0, (int)graph[room].size() - 1);
  }
  int ans = 0;
  while (bfs()) {
    fill(work, work + MAX_V, 0);
    while (1) {
      int flow = dfs(S, 1e9);
      if (flow == 0) break;
      ans += flow;
    }
  }
  cout << ans << "\n";
  for (int room = 10001; room <= 10003; room++) {
    for (Edge &edge : graph[room]) {
      if (edge.next != E && edge.cap == 0 && edge.flow < 0) {
        cout << (edge.next) << " " << (char)('A' + room - 10001) << "\n";
      }
    }
  }
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
