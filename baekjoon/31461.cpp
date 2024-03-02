#include <algorithm>
#include <cstring>
#include <iostream>
#include <queue>
#include <vector>
using namespace std;

typedef long long ll;

int N, M, T;
int sx, sy, ex, ey;

int map[2][200001];

ll getLeft(int s) {
  ll sum = 0;
  ll ans = 0;
  for (int y = 0; y < 2; y++) {
    for (int x = 0; x < s; x++) {
      sum += map[y][x];
    }
  }
  for (int x = 0; x < s; x++) {
    ans = max(ans, sum);
    ans = max(ans, sum - map[0][x]);
    ans = max(ans, sum - map[1][x]);
    sum -= map[0][x];
    sum -= map[1][x];
  }
  return ans;
}

ll getRight(int s) {
  ll sum = 0;
  ll ans = 0;
  for (int y = 0; y < 2; y++) {
    for (int x = N - 1; x > s; x--) {
      sum += map[y][x];
    }
  }
  for (int x = N - 1; x > s; x--) {
    ans = max(ans, sum);
    ans = max(ans, sum - map[0][x]);
    ans = max(ans, sum - map[1][x]);
    sum -= map[0][x];
    sum -= map[1][x];
  }
  return ans;
}

void solve() {
  cin >> N;
  for (int y = 0; y < 2; y++) {
    for (int x = 0; x < N; x++) {
      cin >> map[y][x];
    }
  }
  cin >> sx >> sy >> ex >> ey;
  sx--;
  sy--;
  ex--;
  ey--;
  if (ex < sx) {
    swap(sx, ex);
    swap(sy, ey);
  }
  ll left = getLeft(sx);
  ll right = getRight(ex);
  ll ans = 0;
  if (sx == ex) {
    ans = max(left, right);
    if (ans < 0)
      ans = 0;
  } else {
    if (left + map[!sy][sx] > 0)
      ans += left + map[!sy][sx];
    else if (map[!sy][sx] > 0)
      ans += map[!sy][sx];
    if (right + map[!ey][ex] > 0)
      ans += right + map[!ey][ex];
    else if (map[!ey][ex] > 0)
      ans += map[!ey][ex];
    for (int x = sx + 1; x < ex; x++) {
      int temp = max(map[0][x], map[1][x]);
      temp = max(temp, map[0][x] + map[1][x]);
      ans += temp;
    }
  }
  ans += map[sy][sx] + map[ey][ex];
  cout << ans << "\n";
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  while (T--) {
    solve();
  }
  return 0;
}