#include <algorithm>
#include <iostream>

using namespace std;
typedef long long ll;
int N, Q;
ll tree[262145];
ll lazy[262145];

void propagation(int node, int s, int e) {
  ll data = lazy[node];
  if (data) {
    tree[node] = (e - s + 1) - tree[node];
  }
  if (s != e) {
    lazy[node * 2] ^= data;
    lazy[node * 2 + 1] ^= data;
  }
  lazy[node] = 0;
}

void update(int node, int s, int e, int l, int r) {
  propagation(node, s, e);
  if (r < s || e < l)
    return;
  if (l <= s && e <= r) {
    lazy[node] ^= 1;
    propagation(node, s, e);
  } else {
    update(node * 2, s, (s + e) / 2, l, r);
    update(node * 2 + 1, (s + e) / 2 + 1, e, l, r);
    tree[node] = tree[node * 2] + tree[node * 2 + 1];
  }
}

ll query(int node, int s, int e, int l, int r) {
  propagation(node, s, e);
  if (r < s || e < l)
    return 0L;
  if (l <= s && e <= r) {
    return tree[node];
  } else {
    return query(node * 2, s, (s + e) / 2, l, r) +
           query(node * 2 + 1, (s + e) / 2 + 1, e, l, r);
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> Q;
  int C, L, R, idx;
  for (int i = 1; i <= Q; i++) {
    cin >> C >> L >> R;
    if (C == 0) {
      update(1, 1, N, L, R);
    } else {
      cout << query(1, 1, N, L, R) << "\n";
    }
  }
  return 0;
}