#include <algorithm>
#include <iostream>
#include <stack>
#include <vector>
using namespace std;

const int MAX = 4242;
int N, M, A, B, C, D, T, K, legal;

int num = 1;
int pathNum = 1;
vector<int> graph[MAX];

int node[MAX];
int low[MAX];
int visited[MAX];

int groupNum = 1;
int group[MAX];
stack<int> st;

// 1..N, N + 1..N + M
int value[MAX];

void scc(int cur) {
  low[cur] = node[cur] = num++;
  visited[cur] = 1;
  st.push(cur);
  for (int next : graph[cur]) {
    if (!node[next]) {
      scc(next);
      low[cur] = min(low[cur], low[next]);
    } else if (visited[next]) {
      low[cur] = min(low[cur], node[next]);
    }
  }
  if (node[cur] == low[cur]) {
    while (!st.empty()) {
      int v = st.top();
      st.pop();
      group[v] = groupNum;
      visited[v] = 0;
      if (v == cur)
        break;
    }
    groupNum++;
  }
}

int oppo(int x) { return (x > pathNum) ? x - pathNum : x + pathNum; }

void init() {
  num = groupNum = legal = 1;
  pathNum = N + M;
  for (int i = 1; i <= pathNum * 2; i++) {
    value[i] = -1;
    node[i] = low[i] = group[i] = visited[i] = 0;
    graph[i].clear();
  }
}

int dfs(int n) {
  if (value[n] == 0)
    return 0;
  for (int next : graph[n]) {
    if (value[next] == 0 || value[oppo(next)] == 1)
      return 1;
    value[next] = 1;
    value[oppo(next)] = 0;

    if (!visited[next]) {
      visited[next] = 1;
      if (dfs(next))
        return 1;
    }
  }
  return 0;
}

void solve() {
  cin >> N >> M >> K;
  init();
  for (int i = 0; i < K; i++) {
    cin >> A >> B >> C >> D;
    // 시작점과 출발점이 같은 경우는 넘어간다.
    if (A == C && B == D)
      continue;
    // 경로가 2개인경우
    if (A != C && B != D) {
      // 가로 먼저가는 경로
      graph[(A < C) ? oppo(N + B) : (N + B)].push_back((B < D) ? A : oppo(A));
      graph[(A < C) ? oppo(N + B) : (N + B)].push_back((A < C) ? (N + D)
                                                               : oppo(N + D));

      graph[((B < D) ? oppo(C) : C)].push_back((B < D) ? A : oppo(A));
      graph[((B < D) ? oppo(C) : C)].push_back((A < C) ? (N + D) : oppo(N + D));
      // 세로 먼저가는 경로
      graph[(B < D) ? oppo(A) : A].push_back((A < C) ? (N + B) : oppo(N + B));
      graph[(B < D) ? oppo(A) : A].push_back((B < D) ? C : oppo(C));
      graph[(A < C) ? oppo(N + D) : (N + D)].push_back((A < C) ? (N + B)
                                                               : oppo(N + B));
      graph[(A < C) ? oppo(N + D) : (N + D)].push_back((B < D) ? C : oppo(C));

    } else {
      // 경로가 1개인 경우
      if (B == D) {
        if (value[N + B] != -1 && value[N + B] != A < C)
          legal = 0;
        value[N + B] = A < C;
        value[oppo(N + B)] = A > C;
      } else {
        if (value[A] != -1 && value[A] != B < D)
          legal = 0;
        value[A] = B < D;
        value[oppo(A)] = B > D;
      }
    }
  }
  if (!legal) {
    cout << "No\n";
    return;
  }
  for (int i = 1; i <= pathNum * 2; i++) {
    if (!node[i])
      scc(i);
  }
  for (int i = 1; i <= pathNum; i++) {
    if (group[i] == group[oppo(i)]) {
      cout << "No\n";
      return;
    }
  }
  for (int i = 1; i <= pathNum * 2; i++) {
    if (value[i] != -1 && !visited[i]) {
      if (dfs(i)) {
        cout << "No\n";
        return;
      }
    }
  }
  cout << "Yes\n";
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  while (T--)
    solve();
  return 0;
}