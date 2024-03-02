#include <algorithm>
#include <iostream>

using namespace std;
typedef long long ll;
ll N, B, C;
ll sum, ans, target;
ll arr[1000005];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> arr[i];
    sum += arr[i];
  }
  B = 3;
  C = 2;
  if (B <= C) {
    cout << sum * B;
    return 0;
  }
  for (int i = 0; i < N; i++) {
    if (arr[i + 1] > arr[i + 2]) {
      target = min(arr[i], arr[i + 1] - arr[i + 2]);
      ans += (B + C) * target;
      arr[i] -= target;
      arr[i + 1] -= target;
    }

    target = min(arr[i], min(arr[i + 1], arr[i + 2]));
    ans += (B + C * 2) * target;
    arr[i] -= target;
    arr[i + 1] -= target;
    arr[i + 2] -= target;

    target = min(arr[i], arr[i + 1]);
    ans += (B + C) * target;
    arr[i] -= target;
    arr[i + 1] -= target;
    ans += B * arr[i];
  }
  cout << ans;
  return 0;
}
