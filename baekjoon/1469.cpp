#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

typedef long long int ll;

int N;
int ans[16];
int arr[8];
int used[8];

int bf(int idx) {
  if (idx >= N * 2) return 1;
  if (ans[idx] != -1) return bf(idx + 1);
  for (int i = 0; i < N; i++) {
    if (used[i]) continue;
    if (arr[i] + idx + 1 < N * 2 && ans[arr[i] + idx + 1] == -1) {
      used[i] = 1;
      ans[idx] = arr[i];
      ans[arr[i] + idx + 1] = arr[i];
      if (bf(idx + 1)) return 1;
      used[i] = 0;
      ans[idx] = -1;
      ans[arr[i] + idx + 1] = -1;
    }
  }
  return 0;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N * 2; i++) {
    ans[i] = -1;
  }
  for (int i = 0; i < N; i++) {
    cin >> arr[i];
    if (arr[i] > N * 2 - 2) {
      cout << "-1\n";
      return 0;
    }
  }
  sort(arr, arr + N);
  if (bf(0)) {
    for (int i = 0; i < N * 2; i++) {
      cout << ans[i] << " ";
    }
  } else {
    cout << "-1\n";
  }
  return 0;
}
