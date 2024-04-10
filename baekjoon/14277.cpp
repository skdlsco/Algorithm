#include <algorithm>
#include <iostream>
#include <string>

using namespace std;

typedef long long int ll;

ll a, b, c, d, u;
ll ans;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> a >> b >> c >> d >> u;

  if (u < a)
    ans = 0;
  else
    ans = ((u - a) / b) + 1;
  if (d == 1) {
    if (c <= u) {
      if (a <= c) {
        if ((c - a) % b != 0)
          ans++;
      } else
        ans++;
    }
  } else {
    for (ll i = c; i <= u; i *= d) {
      if (i < a)
        ans++;
      else if ((i - a) % b != 0)
        ans++;
    }
  }
  cout << ans << "\n";
  return 0;
}
