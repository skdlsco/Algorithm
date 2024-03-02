#include <algorithm>
#include <iostream>
#include <stack>
#include <vector>
using namespace std;

int T, N, M, A, B;
int num = 1;

int low[100001];
int label[100001];
int groupLabel[100001];
int visit[100001];
int inDegree[100001];
stack<int> s;
vector<int> graph[100001];
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

void init() {
  for (int i = 0; i <= N; i++) {
    low[i] = 0;
    label[i] = 0;
    groupLabel[i] = 0;
    visit[i] = 0;
    inDegree[i] = 0;
    graph[i].clear();
    num = 1;
  }
  sccList.clear();
}

void solve() {
  cin >> N >> M;
  init();

  while (M--) {
    cin >> A >> B;
    graph[A].push_back(B);
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
  int check = -1;
  for (int i = 0; i < sccList.size(); i++) {
    if (inDegree[i] == 0) {
      if (check != -1) {
        cout << "Confused\n";
        return;
      }
      check = i;
    }
  }
  if (check == -1)
    return;
  sort(sccList[check].begin(), sccList[check].end());
  for (auto node : sccList[check]) {
    cout << node << "\n";
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  while (T--) {
    solve();
    cout << "\n";
  }
  return 0;
}