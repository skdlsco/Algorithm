#include <algorithm>
#include <cmath>
#include <iostream>
#include <map>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;
int N;
int ans;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  cin >> ans;
  ans = (ans - 2) * 180;
  for (int i = 1; i < N; i++) {
    int a;
    cin >> a;
    if (i > 0)
      ans += a * 180;
  }
  cout << ans << "\n";
}