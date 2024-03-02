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
vector<int> seq;
int ans[200001];

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
      seq.push_back(v);
      visited[v] = 0;
      if (v == cur)
        break;
    }
    groupNum++;
  }
}

int oppo(int x) { return (x > N) ? x - N : x + N; }

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  for (int i = 0; i < M; i++) {
    cin >> A >> B;
    A = (A < 0) ? oppo(-A) : A;
    B = (B < 0) ? oppo(-B) : B;
    graph[oppo(A)].push_back(B);
    graph[oppo(B)].push_back(A);
  }
  for (int i = 1; i <= N * 2; i++) {
    if (!node[i])
      scc(i);
  }
  for (int i = 1; i <= N; i++) {
    if (group[i] == group[oppo(i)]) {
      cout << 0 << "\n";
      return 0;
    }
  }
  cout << 1 << "\n";
  fill(ans, ans + N * 2 + 1, -1);
  for (auto s = seq.rbegin(); s != seq.rend(); s++) {
    int cur = *s;
    if (ans[cur] == -1) {
      ans[cur] = 0;
      ans[oppo(cur)] = 1;
    }
  }
  for (int i = 1; i <= N; i++) {
    cout << ans[i] << " ";
  }
  return 0;
}