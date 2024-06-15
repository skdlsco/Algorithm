#include <algorithm>
#include <iostream>
#include <map>
#include <queue>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;

int T, N;
int ans;
int status, start;
vector<pii> arr;

void solve() {
  arr.clear();
  ans = 0;
  cin >> N;
  for (int i = 0; i < N; i++) {
    int A, B;
    cin >> A >> B;
    arr.push_back({A, -1});
    arr.push_back({B, 1});
  }
  sort(arr.begin(), arr.end());
  for (pii &item : arr) {
    if (status == 0 && item.second == -1) {
      start = item.first;
    }
    status -= item.second;
    if (status == 0) {
      ans += item.first - start + 1;
    }
  }
  cout << ans << "\n";
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  while (T--) {
    solve();
  }
  return 0;
}
