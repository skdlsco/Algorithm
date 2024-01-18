#include <algorithm>
#include <iostream>

using namespace std;

int N;
int arr[100001];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> arr[i];
  }
  sort(arr, arr + N);
  int ans = 0;
  int sum = 0;
  for (int i = N - 1; i >= 0; i--) {
    ans = max(ans, arr[i] * (N - i));
  }
  cout << ans;
  return 0;
}
