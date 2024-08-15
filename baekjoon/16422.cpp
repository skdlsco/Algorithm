#include <algorithm>
#include <iostream>
#include <map>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int n, s;

int arr[1001][1001];
int ans[1000001];
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> n >> s;

  for (int r = 1; r <= n; r++) {
    for (int c = 1; c <= n; c++) {
      char cell;
      cin >> cell;
      arr[r][c] = arr[r - 1][c] + arr[r][c - 1] - arr[r - 1][c - 1] + (cell == 'D');
      if (r >= s && c >= s) {
        int cnt = arr[r][c] - arr[r - s][c] - arr[r][c - s] + arr[r - s][c - s];
        ans[cnt]++;
      }
    }
  }

  for (int i = 0; i <= s * s; i++) {
    if (ans[i])
      cout << i << " " << ans[i] << "\n";
  }
  return 0;
}