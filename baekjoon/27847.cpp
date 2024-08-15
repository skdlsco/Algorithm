#include <algorithm>
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

struct Data {
  ll t;
  ll x;
  ll y;
};

int G, N, ans;
vector<Data> arr;

bool comp(const Data &a, const Data &b) {
  return a.t < b.t;
}

int check(const Data &a, const Data &b) {
  ll xDiff = a.x - b.x;
  ll yDiff = a.y - b.y;
  ll tDiff = a.t - b.t;
  return xDiff * xDiff + yDiff * yDiff <= tDiff * tDiff;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> G >> N;
  for (int i = 0; i < G; i++) {
    ll t, x, y;
    cin >> x >> y >> t;
    arr.push_back({t, x, y});
  }
  sort(arr.begin(), arr.end(), comp);
  for (int i = 0; i < N; i++) {
    ll t, x, y;
    cin >> x >> y >> t;
    Data d = {t, x, y};
    auto iter = lower_bound(arr.begin(), arr.end(), d, comp);
    int guilty = 1;
    if (iter == arr.end())
      guilty = check(d, *(arr.end() - 1));
    else if (iter == arr.begin()) {
      guilty = check(d, *iter);
    } else {
      guilty = check(d, *iter) && check(d, *(iter - 1));
    }
    if (!guilty)
      ans++;
  }
  cout << ans;
  return 0;
}