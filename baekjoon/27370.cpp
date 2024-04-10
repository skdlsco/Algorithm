#include <algorithm>
#include <iostream>

using namespace std;

typedef long long int ll;

int T, N, A, B;

void solve() {
  ll aSum = 0, bSum = 0, center = 0;
  cin >> N >> A >> B;
  if (A > B)
    swap(A, B);
  for (int i = 0; i < N; i++) {
    ll x;
    cin >> x;
    if (x - A < B - x) {
      aSum += (x - A) * 2;
    } else if (x - A > B - x) {
      bSum += (B - x) * 2;
    } else {
      center++;
    }
  }
  ll centerPos = (A + B) / 2;

  while (center--) {
    if (aSum < bSum) {
      aSum += (centerPos - A) * 2;
    } else {
      bSum += (B - centerPos) * 2;
    }
  }
  cout << aSum + bSum << " " << abs(aSum - bSum) << "\n";
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
