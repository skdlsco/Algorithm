#include <algorithm>
#include <iostream>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int T;
ll dp[1001];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  dp[0] = 1;
  dp[1] = 1;
  dp[2] = 2;
  for (int i = 3; i <= 1000; i++) {
    if (i % 2) {
      for (int c = 1; c <= i; c += 2) {
        dp[i] += dp[(i - c) / 2];
      }
    } else {
      for (int c = 2; c <= i; c += 2) {
        dp[i] += dp[(i - c) / 2];
      }
      dp[i] += dp[i / 2];
    }
  }
  while (T--) {
    int N;
    cin >> N;
    cout << dp[N] << "\n";
  }
  return 0;
}