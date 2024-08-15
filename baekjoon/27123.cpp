#include <algorithm>
#include <iostream>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

ll N, M, P, ans, ans2;

ll gcd(ll a, ll b) {
  if (b == 0)
    return a;
  return gcd(b, a % b);
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M >> P;

  ll N2 = abs(N - P);
  for (int y = 1; y <= M; y++) {
    ll x = y * N / M;
    x++;
    ans += max(0LL, N - x);
  }
  for (int y = 1; y <= M; y++) {
    ll x = y * N2 / M;
    x++;
    ans2 += max(0LL, N2 - x);
  }
  if (P > N)
    ans += M - 1;
  else if (N2 != 0) {
    ans2 += gcd(N2, M) - 1;
    ans2 *= -1;
  }
  cout << ans + ans2;
  return 0;
}