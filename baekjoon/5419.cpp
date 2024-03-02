#include <algorithm>
#include <iostream>
#include <cstring>

using namespace std;
typedef long long ll;
int T, N;

struct Island {
  int x;
  int y;
  int num;
};

bool compare(Island a, Island b) {
  return a.y > b.y;
}

bool compare2(Island a, Island b) {
  if (a.x == b.x)
    return a.y > b.y;
  return a.x < b.x;
}


Island arr[750001];
ll tree[262143];

void update(int node, int s, int e, int idx) {
    if (idx < s || e < idx)
        return;
    if (s == e)
        tree[node]++;
    else {
        update(node * 2, s, (s + e) / 2, idx);
        update(node * 2 + 1, (s + e) / 2 + 1, e, idx);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }
}

ll query(int node, int s, int e, int l, int r) {
    if (e < l || r < s)
        return 0L;
    if (l <= s && e <= r)
        return tree[node];
    return query(node * 2, s, (s + e) / 2, l, r) +
            query(node * 2 + 1, (s + e) / 2 + 1, e, l, r);
}

void solve() {
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> arr[i].x >> arr[i].y;
  }
  sort(arr, arr + N, compare);
  int prev = -2101010101;
  int cnt = 0;

  for (int i = 0; i < N; i++) {
    if (arr[i].y != prev)
      cnt++;
    arr[i].num = cnt;
    prev = arr[i].y;
  }
  ll ans = 0L;
  sort(arr, arr + N, compare2);
  memset(tree, 0, sizeof(ll) * 262142);
  for (int i = 0; i < N; i++) {
    ans += query(1, 1, N, 0, arr[i].num);
    update(1, 1, N, arr[i].num);
  }
  cout << ans << "\n";
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  while(T--){
    solve();
  }
  return 0;
}
