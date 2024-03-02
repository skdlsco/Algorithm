#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;

int N, M, u;
int num = 1;
vector<int> graph[100001];
pair<int, int> range[100001];
int mapper[100001];
int tree[400000];

int ott(int here) {
  int end = num;
  mapper[here] = num++;
  for (auto next : graph[here]) {
    end = max(end, ott(next));
  }
  range[here] = {mapper[here], end};
  return end;
}

void update(int node, int s, int e, int l, int r, int v) {
  if (r < s || e < l)
    return;
  if (l <= s && e <= r) {
    tree[node] += v;
  } else {
    update(node * 2, s, (s + e) / 2, l, r, v);
    update(node * 2 + 1, (s + e) / 2 + 1, e, l, r, v);
  }
}

int query(int node, int s, int e, int idx) {
  if (idx < s || e < idx)
    return 0;
  if (s == e) {
    return tree[node];
  } else {
    tree[node * 2] += tree[node];
    tree[node * 2 + 1] += tree[node];
    tree[node] = 0;
    return query(node * 2, s, (s + e) / 2, idx) +
           query(node * 2 + 1, (s + e) / 2 + 1, e, idx);
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  for (int i = 1; i <= N; i++) {
    cin >> u;
    if (u != -1)
      graph[u].push_back(i);
  }
  ott(1);
  int c, i, w;
  while (M--) {
    cin >> c;
    if (c == 1) {
      cin >> i >> w;
      update(1, 1, N, range[i].first, range[i].second, w);
    } else {
      cin >> i;
      cout << query(1, 1, N, mapper[i]) << "\n";
    }
  }
  return 0;
}