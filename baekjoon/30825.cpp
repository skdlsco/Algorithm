#include <algorithm>
#include <iostream>

using namespace std;
typedef long long int ll;

ll ans;
int N, K;
ll prv;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> K;
  cin >> prv;
  for (int i = 1; i < N; i++) {
    int cur;
    cin >> cur;
    if (prv + K < cur) {
      ans += i * (cur - (prv + K));
      prv = cur;
    } else {
      ans += prv + K - cur;
      prv = prv + K;
    }
  }
  cout << ans;
  return 0;
}