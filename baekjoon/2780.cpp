#include <algorithm>
#include <cmath>
#include <deque>
#include <iostream>
#include <queue>
#include <string>
#include <vector>

using namespace std;
typedef long long ll;
typedef pair<int, int> pii;
int T;
int N;
int MOD = 1234567;
int dp[1001][10];
vector<int> path[10];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  for (int i = 0; i <= 9; i++) {
    dp[1][i] = 1;
  }
  path[0].push_back(7);

  path[1].push_back(2);
  path[1].push_back(4);

  path[2].push_back(1);
  path[2].push_back(3);
  path[2].push_back(5);

  path[3].push_back(2);
  path[3].push_back(6);

  path[4].push_back(1);
  path[4].push_back(5);
  path[4].push_back(7);

  path[5].push_back(2);
  path[5].push_back(4);
  path[5].push_back(6);
  path[5].push_back(8);

  path[6].push_back(3);
  path[6].push_back(5);
  path[6].push_back(9);

  path[7].push_back(4);
  path[7].push_back(8);
  path[7].push_back(0);

  path[8].push_back(5);
  path[8].push_back(7);
  path[8].push_back(9);

  path[9].push_back(6);
  path[9].push_back(8);
  for (int i = 2; i <= 1000; i++) {
    for (int j = 0; j <= 9; j++) {
      for (int k : path[j]) {
        dp[i][j] += dp[i - 1][k];
      }
      dp[i][j] %= MOD;
    }
  }
  while (T--) {
    cin >> N;
    int sum = 0;
    for (int i = 0; i <= 9; i++) {
      sum += dp[N][i];
    }
    cout << sum % MOD << "\n";
  }
  return 0;
}
