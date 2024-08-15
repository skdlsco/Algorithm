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

ll ans;
int N, W;
vector<ll> five, three;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> W;
  for (int i = 0; i < N; i++) {
    ll weight, value;
    cin >> weight >> value;
    if (weight == 3)
      three.push_back(value);
    else
      five.push_back(value);
  }
  sort(five.rbegin(), five.rend());
  sort(three.rbegin(), three.rend());
  for (int i = 1; i < three.size(); i++) {
    three[i] += three[i - 1];
  }
  for (int i = 1; i < five.size(); i++) {
    five[i] += five[i - 1];
  }
  for (int i = 0; i <= W; i++) {
    int threeCnt = min((int)three.size(), i / 3);
    int fiveCnt = min((int)five.size(), (W - i) / 5);
    ll sum = 0;
    if (threeCnt > 0)
      sum += three[threeCnt - 1];
    if (fiveCnt > 0)
      sum += five[fiveCnt - 1];
    ans = max(ans, sum);
  }
  cout << ans;
  return 0;
}