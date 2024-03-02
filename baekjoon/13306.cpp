#include <algorithm>
#include <iostream>
#include <stack>
#include <vector>
using namespace std;
typedef long long ll;

int N, Q;
int d;
int group[200001];
stack<string> ans;
stack<pair<int, int>> query;
int graph[200001];
int enable[200001];

int find(int node) {
  if (group[node] == node)
    return node;
  return group[node] = find(group[node]);
}

void join(int a, int b) {
  if (a > b)
    swap(a, b);
  int pa = find(a);
  int pb = find(b);
  if (pa == pb)
    return;
  group[pb] = pa;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> Q;
  for (int i = 0; i <= N; i++) {
    group[i] = i;
    enable[i] = 1;
  }
  for (int i = 2; i <= N; i++)
    cin >> graph[i];
  for (int i = 0; i < Q + N - 1; i++) {
    int x, c, b;
    cin >> x;
    if (x) {
      cin >> c >> b;
      query.push({c, b});
    } else {
      cin >> b;
      enable[b] = 0;
      query.push({-1, b});
    }
  }
  for (int i = 1; i <= N; i++) {
    if (enable[i])
      join(i, graph[i]);
  }
  while (!query.empty()) {
    int c = query.top().first;
    int b = query.top().second;
    query.pop();
    if (c == -1) {
      join(b, graph[b]);
    } else {
      ans.push(find(c) == find(b) ? "YES" : "NO");
    }
  }
  while (!ans.empty()) {
    cout << ans.top() << "\n";
    ans.pop();
  }
  return 0;
}
