#include <algorithm>
#include <iostream>
#include <stack>
#include <vector>
using namespace std;

int T, N, M, A, B;
int num = 1;

int low[200001];
int label[200001];
int groupLabel[200001];
int visit[200001];
int inDegree[200001];
stack<int> s;
vector<int> graph[200001];
vector<vector<int>> sccList;

void scc(int here) {
  visit[here] = 1;
  s.push(here);
  low[here] = num;
  label[here] = num++;
  for (auto next : graph[here]) {
    if (label[next] == 0) {
      scc(next);
      low[here] = min(low[here], low[next]);
    } else if (visit[next])
      low[here] = min(low[here], label[next]);
  }
  if (low[here] == label[here]) {
    vector<int> group;
    while (!s.empty()) {
      int cur = s.top();
      s.pop();
      groupLabel[cur] = sccList.size();
      group.push_back(cur);
      visit[cur] = 0;
      if (cur == here)
        break;
    }
    sccList.push_back(group);
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;

  while (M--) {
    cin >> A >> B;
    graph[A - 1].push_back(B - 1);
  }
  for (int i = 0; i < N; i++) {
    if (label[i] == 0)
      scc(i);
  }
  for (int i = 0; i < N; i++) {
    int group = groupLabel[i];
    for (auto next : graph[i]) {
      int nextGroup = groupLabel[next];
      if (group != nextGroup)
        inDegree[nextGroup]++;
    }
  }
  cin >> T;
  int ans = 0;
  while (T--) {
    int t;
    cin >> t;
    int group = groupLabel[t - 1];
    if (!inDegree[group]) {
      inDegree[group] = 1;
      ans++;
    }
  }
  for (int i = 0; i < sccList.size(); i++) {
    if (inDegree[i] == 0) {
      cout << "-1\n";
      return 0;
    }
  }
  cout << ans << "\n";
  return 0;
}