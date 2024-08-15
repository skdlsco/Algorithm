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

ll A, B;

ll calcXor(ll n) {
  ll ans = 0;
  for (ll i = 0; i <= 60; i++) {
    ll temp = 2LL << i;
    if (i == 0) {
      ans ^= !(((n - 1) / 2) % 2);
    } else if (n % temp >= (1LL << i) && !(((n % temp) - (1LL << i)) % 2)) {
      ans ^= 1LL << i;
    }
  }
  return ans;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> A >> B;
  cout << (calcXor(A) ^ calcXor(B) ^ A);
  return 0;
}