#include <algorithm>
#include <cmath>
#include <iomanip>
#include <iostream>
#include <map>
#include <queue>
#include <set>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;

int N, M;
ll V, ans;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  for (int i = 0; i < N; i++) {
    ll n;
    cin >> n;
    if (V >= 0)
      V += n;
    else
      V -= n;
    V = max(0LL, V);
    if (V >= M)
      ans++;
  }
  cout << ans;
  return 0;
}
