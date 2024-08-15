#include <algorithm>
#include <cmath>
#include <iostream>
#include <queue>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int drink[51][51][101];
int candidate[51];
int needCnt[51];
int good[51];

int N, M, D, S;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);

  cin >> N >> M >> D >> S;
  while (D--) {
    int p, m, t;
    cin >> p >> m >> t;
    drink[p][m][t] = 1;
  }
  while (S--) {
    int p, t;
    cin >> p >> t;

    fill_n(candidate, M + 1, 0);
    for (int i = 1; i <= M; i++) {
      for (int j = 1; j < t; j++) {
        if (drink[p][i][j])
          candidate[i] = 1;
      }
    }
    for (int i = 1; i <= M; i++) {
      if (!candidate[i])
        good[i] = 1;
    }
  }

  for (int i = 1; i <= N; i++) {
    fill_n(candidate, M + 1, 0);
    for (int j = 1; j <= M; j++) {
      for (int k = 1; k <= 100; k++) {
        if (drink[i][j][k])
          candidate[j] = 1;
      }
    }
    for (int j = 1; j <= M; j++) {
      if (candidate[j]) {
        needCnt[j]++;
      }
    }
  }
  int ans = 0;
  for (int i = 1; i <= M; i++) {
    if (!good[i])
      ans = max(ans, needCnt[i]);
  }
  cout << ans;
}