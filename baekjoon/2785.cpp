#include <algorithm>
#include <iostream>

using namespace std;

typedef long long int ll;

int N, ans;
int arr[500001];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;

  for (int i = 0; i < N; i++) {
    cin >> arr[i];
  }
  sort(arr, arr + N);
  for (int i = 0; i < N; i++) {
    while (arr[i]-- && ans < N - i - 1) {
      ans++;
    }
  }
  cout << ans << "\n";
  return 0;
}
