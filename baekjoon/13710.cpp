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

ll pre[30];
ll ans;
int arr[100001];
int N;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> arr[i];
    ans += arr[i];
    for (int j = 0; j < 30; j++) {
      int bit = !!(arr[i] & (1 << j));
      if (bit)
        pre[j] = i - pre[j];
      ans += pre[j] * (1 << j);
      pre[j] += bit;
    }
  }
  cout << ans;
}
