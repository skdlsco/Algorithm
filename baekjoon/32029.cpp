#include <algorithm>
#include <cmath>
#include <iostream>
#include <map>
#include <queue>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int N, A, B;
int arr[101];
int ans = 0;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> A >> B;
  for (int i = 0; i < N; i++) {
    cin >> arr[i];
  }
  // i 문제를 풀기 전에
  // 잠을 x만큼 잤을 때
  // 풀 수 있는 문제 수.
  sort(arr, arr + N);
  for (int i = 0; i < N; i++) {
    for (int x = 0; x < A; x++) {
      int j = 0;
      // i개 만큼 문제를 풀어야한다.
      int cnt = 0;
      int cur = 0;
      while (j < N && cnt < i) {
        if (cur + A <= arr[j]) {
          cur += A;
          cnt++;
        }
        j++;
      }
      if (cnt < i)
        continue;
      cur += B * x;
      while (j < N) {
        if (cur + A - x <= arr[j]) {
          cur += A - x;
          cnt++;
        }
        j++;
      }
      ans = max(ans, cnt);
    }
  }
  cout << ans;
  return 0;
}
