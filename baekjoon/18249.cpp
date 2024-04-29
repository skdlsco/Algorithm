#include <algorithm>
#include <cmath>
#include <iomanip>
#include <iostream>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;

ll MOD = 1e9 + 7;
ll N, T;
ll dp[200001];

void init() {
  dp[0] = 1;
  dp[1] = 1;
  for (int i = 2; i < 200000; i++) {
    dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  init();
  cin >> T;
  while (T--) {
    cin >> N;
    cout << dp[N] << "\n";
  }
  return 0;
}
