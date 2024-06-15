#include <algorithm>
#include <cmath>
#include <iomanip>
#include <iostream>
#include <map>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;

int N, d, k, c;

int ans;
int cnt;
int arr[60001];
int dishCnt[3001];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> d >> k >> c;
  dishCnt[c]++;
  cnt++;
  for (int i = 0; i < N; i++) {
    cin >> arr[i];
    arr[i + N] = arr[i];
  }
  for (int i = 0; i < k; i++) {
    if (dishCnt[arr[i]] == 0)
      cnt++;
    dishCnt[arr[i]]++;
  }
  ans = cnt;
  for (int i = k; i < 2 * N; i++) {
    dishCnt[arr[i - k]]--;
    if (dishCnt[arr[i - k]] == 0)
      cnt--;
    if (dishCnt[arr[i]] == 0)
      cnt++;
    dishCnt[arr[i]]++;
    ans = max(ans, cnt);
  }
  cout << ans;
  return 0;
}
