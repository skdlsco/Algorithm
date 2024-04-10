#include <algorithm>
#include <iostream>

using namespace std;

typedef long long int ll;

ll N;
ll ans = 1;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  if (N == 1) {
    cout << 1;
    return 0;
  }

  int table = 1;
  for (int i = N; i > 0; i -= 2) {
    ans *= (i * (i - 1)) / 2;
    ans /= table;
    table++;
  }
  cout << ans;
  return 0;
}