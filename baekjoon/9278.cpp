#include <algorithm>
#include <cmath>
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

string s;
int MOD = 1000000;
// i 번째, 50원 개수
int dp[1002][1002];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  while (cin >> s) {
    if (s[0] == ')') {
      cout << "0\n";
      continue;
    }
    dp[0][1] = 1;
    for (int i = 1; i < s.length(); i++) {
      char c = s[i];
      for (int j = 0; j <= s.length() / 2; j++) {
        dp[i][j] = dp[i][j + 1] = 0;

        if (c == '(') {
          if (j == 0)
            dp[i][j] = 0;
          else
            dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % MOD;
        } else if (c == ')') {
          dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % MOD;
        } else {
          if (j != 0)
            dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % MOD;
          dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % MOD;
        }
      }
    }
    cout << dp[s.length() - 1][0] << "\n";
  }
}