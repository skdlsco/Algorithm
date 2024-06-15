#include <algorithm>
#include <iostream>
#include <map>
#include <queue>
#include <vector>

using namespace std;

typedef long long int ll;

int T;
int N, M, L;
int A[11];
int B[11];
int P[11];
int ans = 123456789;
void solve() {
  cin >> N >> M >> L;
  ans = 123456789;
  for (int i = 0; i < N; i++) {
    cin >> A[i] >> B[i] >> P[i];
  }

  for (int i = 1; i < 1 << N; i++) {
    int money = M;
    int left = 0;
    int right = 0;
    for (int j = 0; j < N; j++) {
      if (i & (1 << j)) {
        money -= P[j];
        left += A[j];
        right += B[j];
      }
    }
    if (money >= 0 && left <= L && L <= right) {
      ans = min(ans, M - money);
    }
  }
  if (ans == 123456789)
    cout << "IMPOSSIBLE";
  else
    cout << ans;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  for (int i = 1; i <= T; i++) {
    cout << "Case #" << i << ": ";
    solve();
    cout << "\n";
  }
  return 0;
}
