#include <algorithm>
#include <iostream>
#include <stack>
#include <vector>
using namespace std;

int N, M, A, B;
int num = 1;
vector<int> graph[200001];

int node[200001];
int low[200001];
int visited[200001];

int groupNum = 1;
int group[200001];
stack<int> st;

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

int oppo(int x) { return (x > N) ? x - N : x + N; }

void init() {
  for (int i = 0; i <= N * 2; i++) {
    node[i] = 0;
    low[i] = 0;
    visited[i] = 0;
    group[i] = 0;
    num = 1;
    groupNum = 1;
    graph[i].clear();
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  while (cin >> N >> M) {
    init();
    for (int i = 0; i < M; i++) {
      cin >> A >> B;
      A = (A < 0) ? oppo(-A) : A;
      B = (B < 0) ? oppo(-B) : B;
      graph[oppo(A)].push_back(B);
      graph[oppo(B)].push_back(A);
    }
    graph[oppo(1)].push_back(1);
    for (int i = 1; i <= N * 2; i++) {
      if (!node[i])
        scc(i);
    }
    int ans = 1;
    for (int i = 1; i <= N; i++) {
      if (group[i] == group[oppo(i)]) {
        ans = 0;
        break;
      }
    }
    cout << (ans ? "yes" : "no") << "\n";
  }
  return 0;
}