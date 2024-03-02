#include <algorithm>
#include <cstring>
#include <iostream>
using namespace std;

int N, T;
int dp[5001];

int mex(int n) {
  if (n < 2) return 0;
  int check[5001];
  memset(check, 0, sizeof(check));
  n -= 2;
  for (int i = 0; i <= n; i++) {
    int l = dp[i];
    int r = dp[n - i];
    check[l ^ r] = 1;
  }
  for (int i = 0; i <= 5000; i++) {
    if (!check[i]) return i;
  }
  return 0;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  dp[0] = 0;
  for (int i = 1; i <= 5000; i++) {
    dp[i] = mex(i);
  }
  while (T--) {
    int n;
    cin >> n;
    if (dp[n])
      cout << "First"
           << "\n";
    else
      cout << "Second"
           << "\n";
  }
  return 0;
}
