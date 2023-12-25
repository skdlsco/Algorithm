#include <algorithm>
#include <iostream>
#include <queue>
#include <stack>
#include <vector>

using namespace std;

const int SIZE = 100001;

int N, M;
vector<int> graph[SIZE];
int low[SIZE];
int num[SIZE];
int visited[SIZE];
int cnt = 0;
int groupNum[SIZE];
vector<vector<int>> scc;
stack<int> st;

vector<int> newGraph[SIZE];
int inDegree[SIZE];

void dfs(int here) {
  visited[here] = 1;
  num[here] = ++cnt;
  low[here] = cnt;
  st.push(here);
  for (auto next : graph[here]) {
    if (num[next] == 0) {
      dfs(next);
      low[here] = min(low[here], low[next]);
    } else if (visited[next]) {
      low[here] = min(low[here], num[next]);
    }
  }
  if (low[here] == num[here]) {
    vector<int> group;
    int poped;
    while (!st.empty()) {
      poped = st.top();
      st.pop();
      groupNum[poped] = scc.size();
      group.push_back(poped);
      visited[poped] = 0;
      if (poped == here)
        break;
    }
    scc.push_back(group);
  }
}

int main() {
  cin >> N >> M;
  int v, u;
  for (int i = 0; i < M; i++) {
    cin >> v >> u;
    graph[v].push_back(u);
  }
  for (int i = 1; i <= N; i++) {
    if (num[i] == 0)
      dfs(i);
  }
  for (auto group : scc) {
    for (auto node : group) {
      int curGroup = groupNum[node];
      for (auto next : graph[node]) {
        int nextGroup = groupNum[next];
        if (curGroup == nextGroup)
          continue;
        newGraph[curGroup].push_back(nextGroup);
        inDegree[nextGroup]++;
      }
    }
  }
  int ans = 0;
  for (int i = 0; i < scc.size(); i++) {
    ans += !inDegree[i];
  }
  cout << ans;
  return 0;
}