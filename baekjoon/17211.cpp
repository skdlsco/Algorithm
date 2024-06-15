#include <algorithm>
#include <cmath>
#include <iomanip>
#include <iostream>
#include <map>
#include <queue>
#include <set>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;

int N, feeling;
double dp[101][2];
double table[2][2];
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> feeling;
  dp[0][feeling] = 1000;
  dp[0][!feeling] = 0;
  for (int i = 0; i < 2; i++) {
    for (int j = 0; j < 2; j++) {
      cin >> table[i][j];
    }
  }
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < 2; j++) {
      dp[i + 1][j] += dp[i][j] * table[j][j];
      dp[i + 1][!j] += dp[i][j] * table[j][!j];
    }
  }
  cout << round(dp[N][0]) << "\n" << round(dp[N][1]) << "\n";
  return 0;
}
