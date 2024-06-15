#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

typedef long long int ll;

int N, K;
ll arr[2000001];
ll ans, cur;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> K;
  for (int i = 0; i < N; i++) {
    int g, x;
    cin >> g >> x;
    arr[x] += g;
  }
  for (int i = 0; i <= min(1000000, K * 2); i++) {
    cur += arr[i];
  }
  ans = cur;
  for (int i = K * 2 + 1; i <= 1000000; i++) {
    cur -= arr[i - K * 2 - 1];
    cur += arr[i];
    ans = max(ans, cur);
  }
  cout << ans;
  return 0;
}
