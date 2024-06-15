#include <algorithm>
#include <iostream>
#include <queue>
#include <vector>

using namespace std;

typedef long long int ll;

int N;

int cnt = -1;
ll ans;

void dfs(int len, int depth, ll num) {
  if (cnt > N)
    return;
  if (len == depth) {
    cnt++;
    if (cnt == N) {
      ans = num;
    }
    return;
  }
  for (int i = len - depth - 1; i < num % 10; i++) {
    dfs(len, depth + 1, num * 10 + i);
    if (cnt > N)
      return;
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 1; i <= 10; i++) {
    for (int j = i - 1; j <= 9; j++) {
      if (cnt > N)
        break;
      dfs(i, 1, j);
    }
  }
  if (N > 1022)
    cout << -1;
  else
    cout << ans;
  return 0;
}
