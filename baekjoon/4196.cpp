#include <algorithm>
#include <iostream>
#include <stack>
#include <vector>
using namespace std;
typedef long long ll;
ll N, M, T;

int nodeCounter = 1;
int node[100001];
int low[100001];
int groupNum[100001];
int visit[100001];
vector<int> graph[100001];
stack<int> st;
vector<vector<int>> sccList;
int isSub[100001];
void scc(int here) {
  visit[here] = 1;
  node[here] = nodeCounter++;
  low[here] = node[here];
  st.push(here);
  for (int next : graph[here]) {
    if (!node[next]) {
      scc(next);
      low[here] = min(low[here], low[next]);
    } else if (visit[next]) {
      low[here] = min(low[here], node[next]);
    }
  }
  if (low[here] == node[here]) {
    vector<int> group;
    while (!st.empty()) {
      int cur = st.top();
      st.pop();
      visit[cur] = false;
      groupNum[cur] = sccList.size();
      group.push_back(cur);
      if (cur == here)
        break;
    }
    sccList.push_back(group);
  }
}

void init() {
  nodeCounter = 1;
  for (int i = 0; i <= 100000; i++) {
    node[i] = 0;
    low[i] = 0;
    groupNum[i] = 0;
    visit[i] = 0;
    graph[i].clear();
    isSub[i] = 0;
  }
  sccList.clear();
}

void solve() {
  int u, v;
  for (int i = 0; i < M; i++) {
    cin >> u >> v;
    graph[u].push_back(v);
  }

  for (int i = 1; i <= N; i++) {
    if (!node[i])
      scc(i);
  }
  for (int i = 1; i <= N; i++) {
    for (auto next : graph[i]) {
      if (groupNum[i] != groupNum[next])
        isSub[groupNum[next]] = true;
    }
  }
  int ans = 0;
  for (int i = 0; i < sccList.size(); i++) {
    ans += !isSub[i];
  }
  cout << ans << "\n";
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  while (T--) {
    cin >> N >> M;
    init();
    solve();
  }
  return 0;
}
