#include <bits/stdc++.h>
using namespace std;
using ll = long long;
using pii = pair<int, int>;
using pll = pair<ll, ll>;

int n, m, r[201];
pll a[20];
ll sf = 0, ss = 0;

int main() {

  scanf("%d %d", &n, &m);
  memset(r, -1, sizeof(r));

  for (int i = 0; i < min(n, 50); i++) {
    int x;
    scanf("%1d", &x);
    sf <<= 1LL;
    sf |= x;
  }
  for (int i = 50; i < min(n, 100); i++) {
    int x;
    scanf("%1d", &x);
    ss <<= 1LL;
    ss |= x;
  }

  for (int j = 0; j < m; j++) {
    int x;
    ll f = 0, s = 0;
    for (int i = 0; i < min(n, 50); i++) {
      scanf("%1d", &x);
      f <<= 1LL;
      f |= x;
    }
    for (int i = 50; i < min(n, 100); i++) {
      scanf("%1d", &x);
      s <<= 1LL;
      s |= x;
    }
    a[j] = {f, s};
  }

  for (int i = 0; i < (1 << m); i++) {
    ll f = sf, s = ss;
    for (int j = 0; j < m; j++) {
      if (i & (1 << j)) {
        f ^= a[j].first;
        s ^= a[j].second;
      }
    }
    int one = __builtin_popcountll(f) + __builtin_popcountll(s);
    int zero = n - one;
    int idx = one - zero + 100;
    if (r[idx] == -1)
      r[idx] = i;
  }

  for (int i = -n + 100; i <= n + 100; i++) {
    if (r[i] == -1)
      printf("-1");
    else {
      // printf("%d: ", i);
      printf("%d ", __builtin_popcount(r[i]));
      for (int j = 0; j < m; j++)
        if (r[i] & (1 << j))
          printf("%d ", j + 1);
    }
    printf("\n");
  }
}
