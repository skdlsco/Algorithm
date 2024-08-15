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
typedef pair<int, int> pii;

int N;
int dp[26][26];
int ans;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= i; j++) {
      cin >> dp[i][j];
      dp[i][j] += max(dp[i - 1][j], dp[i - 1][j - 1]);
      ans = max(dp[i][j], ans);
    }
  }
  cout << ans;
  return 0;
}
