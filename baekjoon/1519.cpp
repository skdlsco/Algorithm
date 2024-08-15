#include <algorithm>
#include <cmath>
#include <iostream>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int N;
int dp[1000001];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;

  for (int i = 10; i <= N; i++) {
    string s = to_string(i);
    dp[i] = i;
    for (int j = 0; j < s.length(); j++) {
      int num = 0;
      for (int k = j; k < s.length(); k++) {
        num *= 10;
        num += s[k] - '0';
        if (num == 0 || num == i)
          continue;
        if (!dp[i - num])
          dp[i] = min(dp[i], num);
      }
    }
    if (dp[i] == i)
      dp[i] = 0;
  }
  if (!dp[N]) {
    cout << -1;
    return 0;
  }
  cout << dp[N] << "\n";
}