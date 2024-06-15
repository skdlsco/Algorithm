#include <algorithm>
#include <iostream>
#include <map>
#include <queue>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<ll, ll> pll;
int N, M;
ll ans;
ll gcd(ll a, ll b) {
  if (b == 0)
    return a;
  return gcd(b, a % b);
}

map<pll, vector<ll>> m;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  for (int i = 0; i < N; i++) {
    ll x, y;
    cin >> x >> y;
    ll temp = gcd(abs(x), abs(y));
    pll key = {x / temp, y / temp};
    auto iter = m.find(key);
    if (iter == m.end()) {
      vector<ll> v;
      v.push_back(abs(x) + abs(y));
      m.insert({key, v});
    } else {
      (*iter).second.push_back(abs(x) + abs(y));
    }
  }
  for (auto &[k, v] : m) {
    sort(v.begin(), v.end());
  }
  while (M--) {
    ll x, y;
    cin >> x >> y;
    ll temp = gcd(abs(x), abs(y));
    pll key = {x / temp, y / temp};
    auto iter = m.find(key);
    if (iter == m.end())
      continue;
    vector<ll> &v = (*iter).second;
    ll cnt = lower_bound(v.begin(), v.end(), abs(x) + abs(y) + 1) - v.begin();
    ans = max(ans, cnt);
  }
  cout << ans;
  return 0;
}
