#include <iostream>

using namespace std;
typedef long long ll;

int N, K;
ll MOD = 1000000003;

// 첫번째 색 선택
ll dp1[1001][1001][2];
// 첫번째 색 미선택
ll dp2[1001][1001][2];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> K;
  if (N == 1) {
    cout << 1;
    return 0;
  }
  if (N / 2 < K) {
    cout << 0;
    return 0;
  }
  dp1[1][1][1] = 1;
  dp2[1][0][0] = 1;
  for (int i = 2; i <= N; i++) {
    for (int k = 0; k <= K; k++) {
      dp1[i][k][0] = (dp1[i - 1][k][1] + dp1[i - 1][k][0]) % MOD;
      dp2[i][k][0] = (dp2[i - 1][k][1] + dp2[i - 1][k][0]) % MOD;
      if (k > 0) {
        dp1[i][k][1] = dp1[i - 1][k - 1][0];
        dp2[i][k][1] = dp2[i - 1][k - 1][0];
      }
    }
  }
  cout << (dp1[N][K][0] + dp2[N][K][1] + dp2[N][K][0]) % MOD;
  return 0;
}