#include <algorithm>
#include <cmath>
#include <iostream>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;

int K, cnt;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> K;
  for (int i = 2; i < 1001; i++) {
    while (K % i == 0) {
      K /= i;
      cnt++;
    }
  }
  if (K != 1)
    cnt++;
  int ans = 0;
  int temp = 1;
  while (temp < cnt) {
    ans++;
    temp <<= 1;
  }
  cout << ans;
  return 0;
}
