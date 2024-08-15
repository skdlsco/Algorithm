#include <algorithm>
#include <cmath>
#include <iostream>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

struct Data {
  ll x;
  ll a;
  int isCandidate;
  int idx;
};

bool operator<(Data a, Data b) {
  return a.x < b.x;
}

int N, Q;
ll ans[200001];
vector<Data> arr;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> Q;
  for (int i = 0; i < N; i++) {
    ll x, a;
    cin >> a >> x;
    arr.push_back({x, a, 0, -1});
  }
  for (int i = 0; i < Q; i++) {
    ll x;
    cin >> x;
    arr.push_back({x, 0, 1, i});
  }
  sort(arr.begin(), arr.end());
  ll sum = 0;
  ll p = 0;
  ll prev = arr[0].x;
  for (Data &d : arr) {
    sum += p * abs(prev - d.x);
    p += d.a;
    prev = d.x;
    if (d.isCandidate) {
      ans[d.idx] += sum;
    }
  }
  reverse(arr.begin(), arr.end());
  sum = 0;
  p = 0;
  prev = arr[0].x;
  for (Data &d : arr) {
    sum += p * abs(prev - d.x);
    p += d.a;
    prev = d.x;
    if (d.isCandidate) {
      ans[d.idx] += sum;
    }
  }
  for (int i = 0; i < Q; i++) {
    cout << ans[i] << "\n";
  }
}