#include <algorithm>
#include <cmath>
#include <iomanip>
#include <iostream>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;

ll N, T;

void solve() {
  cin >> N;
  ll ans = 0;
  ll temp = 5;
  while (temp <= N) {
    ans += N / temp;
    temp *= 5;
  }
  cout << ans << "\n";
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  while (T--) {
    solve();
  }
  return 0;
}
