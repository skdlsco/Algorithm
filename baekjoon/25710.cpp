#include <algorithm>
#include <iostream>
using namespace std;

typedef long long ll;

int N;

int arr[1000];
int ans;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  while (N--) {
    int cur;
    cin >> cur;
    arr[cur]++;
  }
  for (int i = 1; i < 1000; i++) {
    if (!arr[i])
      continue;
    for (int j = 1; j < 1000; j++) {
      if (!arr[j])
        continue;
      if (i == j && arr[j] <= 1)
        continue;
      int temp = i * j;
      int sum = 0;
      while (temp) {
        sum += temp % 10;
        temp /= 10;
      }
      ans = max(sum, ans);
    }
  }
  cout << ans << "\n";
  return 0;
}