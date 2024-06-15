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

int dp[301][301];
int N, M, K;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M >> K;
  while (N--) {
    int x, y;
    cin >> x >> y;
    for (int i = 300; i > 0; i--) {
      for (int j = 300; j > 0; j--) {
        if (i < x || j < y)
          continue;
        dp[i][j] = max(dp[i][j], dp[i - x][j - y] + 1);
      }
    }
  }
  cout << dp[M][K];
  return 0;
}
