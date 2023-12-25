#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

typedef pair<int, int> pii;
typedef long long ll;

int N;
pii arr[500001];
ll tree[2000001];
ll ans;

void update(int node, int s, int e, int idx, int v) {
  if (idx < s || e < idx)
    return;
  if (s == e) {
    tree[node] = v;
    return;
  }
  update(node * 2, s, (s + e) / 2, idx, v);
  update(node * 2 + 1, (s + e) / 2 + 1, e, idx, v);
  tree[node] = tree[node * 2] + tree[node * 2 + 1];
}

ll query(int node, int s, int e, int l, int r) {
  if (r < s || e < l)
    return 0;
  if (l <= s && e <= r)
    return tree[node];
  return query(node * 2, s, (s + e) / 2, l, r) +
         query(node * 2 + 1, (s + e) / 2 + 1, e, l, r);
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  int x;
  for (int i = 1; i <= N; i++) {
    cin >> x;
    arr[i] = {x, i};
  }
  sort(arr + 1, arr + N + 1, less<pii>());
  for (int i = 1; i <= N; i++) {
    arr[i] = {arr[i].second, i};
  }
  sort(arr + 1, arr + N + 1, less<pii>());
  for (int i = 1; i <= N; i++) {
    ans += query(1, 1, N, arr[i].second + 1, N);
    update(1, 1, N, arr[i].second, 1);
  }
  cout << ans;
  return 0;
}