#include <algorithm>
#include <iostream>

using namespace std;

int N;
int arr[200001];

int mid(int i) {
  int maxV = max(max(arr[i], arr[i + 1]), arr[i + 2]);
  int minV = min(min(arr[i], arr[i + 1]), arr[i + 2]);
  return arr[i] ^ arr[i + 1] ^ arr[i + 2] ^ maxV ^ minV;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> arr[i];
  }
  if (N == 2) {
    cout << min(arr[0], arr[1]);
  } else {
    int ans = 0;
    for (int i = 0; i < N - 2; i++) {
      ans = max(ans, mid(i));
    }
    cout << ans;
  }
}