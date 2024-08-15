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

ll dp[101][2][21];
string target;
string arr[2];
int N;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> target >> arr[0] >> arr[1];
  N = arr[0].length();
  for (int i = 0; i < N; i++) {
    if (arr[0][i] == target[0])
      dp[i][0][0] = 1;
    if (arr[1][i] == target[0])
      dp[i][1][0] = 1;
  }

  for (int i = 1; i < target.length(); i++) {
    int sum[2] = {0};
    for (int j = 0; j < N; j++) {
      if (arr[0][j] == target[i])
        dp[j][0][i] = sum[0];
      if (arr[1][j] == target[i])
        dp[j][1][i] = sum[1];
      sum[0] += dp[j][1][i - 1];
      sum[1] += dp[j][0][i - 1];
    }
  }
  int ans = 0;
  for (int j = 0; j < N; j++) {
    ans += dp[j][0][target.length() - 1];
    ans += dp[j][1][target.length() - 1];
  }
  cout << ans;
}