#include <algorithm>
#include <cmath>
#include <iostream>
#include <map>
#include <queue>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;
int N;
ll sum;

struct Data {
  int idx;
  ll x;
  ll y;
};

vector<Data> arr;
ll MAX_V = 9223372036854775807;
ll ans[500001];
ll minX[500001];

bool comp(const Data &a, const Data &b) {
  if (a.y == b.y) {
    ll aDiff = a.y - a.x;
    ll bDiff = b.y - b.x;
    return aDiff < bDiff;
  }
  return a.y < b.y;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    ll x, y;
    cin >> x >> y;
    arr.push_back({i, x, y});
    ans[i] = MAX_V;
  }
  sort(arr.begin(), arr.end(), comp);
  minX[N - 1] = arr[N - 1].x;
  for (int i = N - 2; i >= 0; i--) {
    minX[i] = min(minX[i + 1], arr[i].x);
  }

  // 첫번째가 외부에 있을 때.
  ll ySum = 0;
  for (int i = 0; i < N; i++) {
    ans[i] = ySum + minX[i];
    ySum += arr[i].y;
  }
  // 첫번째가 내부에 있을 때.
  ySum = 0;
  int maxIdx = 0;
  for (int i = 0; i < N; i++) {
    // arr[maxIdx].y <= arr[i].y
    if (arr[i].y - arr[i].x > arr[maxIdx].y - arr[maxIdx].x) {
      maxIdx = i;
    }
    ySum += arr[i].y;
    ans[i] = min(ans[i], ySum + arr[maxIdx].x - arr[maxIdx].y);
  }
  for (int i = 0; i < N; i++) {
    cout << ans[i] << "\n";
  }
}
