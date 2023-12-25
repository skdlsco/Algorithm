#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;
int N, K;
ll MOD = 1000000007;
ll tree[11][2097153];

void update(ll tree[], int node, int s, int e, int idx, ll v) {
  if (idx < s || e < idx)
    return;
  if (s == e) {
    tree[node] = v;
  } else {
    update(tree, node * 2, s, (s + e) / 2, idx, v);
    update(tree, node * 2 + 1, (s + e) / 2 + 1, e, idx, v);
    tree[node] = (tree[node * 2] + tree[node * 2 + 1]) % MOD;
  }
}

ll query(ll tree[], int node, int s, int e, int l, int r) {
  if (r < s || e < l)
    return 0L;
  if (l <= s && e <= r)
    return tree[node];
  return (query(tree, node * 2, s, (s + e) / 2, l, r) +
          query(tree, node * 2 + 1, (s + e) / 2 + 1, e, l, r)) %
         MOD;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> K;
  int num;
  for (int i = 1; i <= N; i++) {
    cin >> num;
    for (int j = 1; j <= K; j++) {
      ll cnt = query(tree[j - 1], 1, 1, N, 1, num - 1);
      if (j == 1)
        cnt = 1;
      update(tree[j], 1, 1, N, num, cnt);
    }
  }
  ll ans = query(tree[K], 1, 1, N, 1, N);
  cout << ans << "\n";
  return 0;
}