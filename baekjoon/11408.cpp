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
  int cost;
  int flow;
  Edge(int next, int ref, int cap, int cost)
      : next(next), ref(ref), cap(cap), cost(cost), flow(0) {}
};

const int MAX_V = 803;
const int INF = 1e9;

vector<Edge> graph[MAX_V];
int cost, flow;
int S = 0, E, ans;
int costArr[MAX_V], inQueue[MAX_V], path[MAX_V];

void addEdge(int a, int b, int cost, int capacity) {
  graph[a].push_back(Edge(b, graph[b].size(), capacity, cost));
  graph[b].push_back(Edge(a, graph[a].size() - 1, 0, -cost));
}

int N, M;
int main() {
  // cin.tie(0);
  // cout.tie(0);
  // ios_base::sync_with_stdio(false);
  cin >> N >> M;
  E = N + M + 1;
  // 1 ... N 직원
  // N + 1 ... N + M 일
  for (int i = 1; i <= N; i++) {
    addEdge(0, i, 0, 1);
  }
  for (int i = N + 1; i <= N + M; i++) {
    addEdge(i, E, 0, 1);
  }
  for (int i = 1; i <= N; i++) {
    int able;
    cin >> able;
    for (int j = 1; j <= able; j++) {
      int job, jobCost;
      cin >> job >> jobCost;
      addEdge(i, N + job, jobCost, 1);
    }
  }

  while (1) {
    queue<int> q;
    // reset costArr, inQueue;
    fill(costArr, costArr + MAX_V, INF);
    fill(path, path + MAX_V, -1);
    costArr[S] = 0;
    q.push(S);
    inQueue[S] = true;
    while (!q.empty()) {
      int cur = q.front();
      q.pop();
      inQueue[cur] = false;
      for (Edge &e : graph[cur]) {
        // 최단 경로 & 용량존재 확인
        if (e.cap - e.flow > 0 && costArr[cur] + e.cost < costArr[e.next]) {
          costArr[e.next] = costArr[cur] + e.cost;
          path[e.next] = e.ref;
          if (!inQueue[e.next]) {
            q.push(e.next);
            inQueue[e.next] = true;
          }
        }
      }
    }
    // 경로가 없는 경우
    if (path[E] == -1)
      break;
    int df = INF;
    for (int cur = E; cur != S; cur = graph[cur][path[cur]].next) {
      // 역간선
      Edge &revE = graph[cur][path[cur]];
      // 돌려준다.
      Edge &e = graph[revE.next][revE.ref];
      df = min(df, e.cap - e.flow);
    }
    flow += df;
    // 최소 경로로 df개 흘려보냄
    cost += costArr[E] * df;
    // 유량 흘리기
    for (int cur = E; cur != S; cur = graph[cur][path[cur]].next) {
      Edge &revE = graph[cur][path[cur]];
      revE.flow -= df;
      graph[revE.next][revE.ref].flow += df;
    }
  }
  cout << flow << "\n" << cost << "\n";
  return 0;
}
