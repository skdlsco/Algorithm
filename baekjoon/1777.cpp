#include <algorithm>
#include <iostream>

using namespace std;
typedef long long ll;
typedef pair<int, int> pii;
int N;
int ans[100001];
int input[100001];
pii tree[300001];

void init(int node, int s, int e) {
  if (s == e) {
    tree[node] = {1, s};
  } else {
    init(node * 2, s, (s + e) / 2);
    init(node * 2 + 1, (s + e) / 2 + 1, e);
    pii l = tree[node * 2];
    pii r = tree[node * 2 + 1];
    tree[node] = {l.first + r.first, s};
  }
}

void update(int node, int s, int e, int idx) {
  if (idx < s || e < idx)
    return;
  if (s == e) {
    tree[node] = {0, s};
  } else {
    update(node * 2, s, (s + e) / 2, idx);
    update(node * 2 + 1, (s + e) / 2 + 1, e, idx);
    pii l = tree[node * 2];
    pii r = tree[node * 2 + 1];
    if (l.first == 0)
      tree[node] = r;
    else
      tree[node] = {l.first + r.first, l.second};
  }
}

int query(int node, int s, int e, int v) {
  if (tree[node].first == v)
    return tree[node].second;
  pii r = tree[node * 2 + 1];
  if (v == r.first)
    return r.second;
  else if (v > r.first)
    return query(node * 2, s, (s + e) / 2, v - r.first);
  else
    return query(node * 2 + 1, (s + e) / 2 + 1, e, v);
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 1; i <= N; i++) {
    cin >> input[i];
  }
  init(1, 1, N);
  for (int i = N; i >= 1; i--) {
    int target = query(1, 1, N, input[i] + 1);
    update(1, 1, N, target);
    ans[target] = i;
  }
  for (int i = 1; i <= N; i++) {
    cout << ans[i] << " ";
  }
  return 0;
}