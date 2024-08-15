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
struct comp {
  bool operator()(const pii a, const pii b) {
    if (a.second == b.second)
      return a.first < b.first;
    return a.second < b.second;
  }
};
vector<pii> arr;
int ans;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    int x, m;
    cin >> x >> m;
    arr.push_back({x - m, x + m});
  }
  sort(arr.begin(), arr.end(), comp());
  int curX = -1234567890;
  for (pii item : arr) {
    if (item.first >= curX) {
      ans++;
      curX = item.second;
    }
  }
  cout << ans << "\n";
}