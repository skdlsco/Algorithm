#include <algorithm>
#include <iostream>
using namespace std;
typedef unsigned long long ll;

ll n;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> n;
  ll target = 18446744073709551615ULL - n;
  int ans = 64;
  while (target) {
    target /= 2;
    ans--;
  }
  cout << ans << "\n";
}
