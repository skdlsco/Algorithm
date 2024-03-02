#include <algorithm>
#include <cstring>
#include <iostream>
using namespace std;

int N;
int dp[2001];

int mex(int n) {
  int check[5001];
  memset(check, 0, sizeof(check));
  for (int i = 1; i < n; i++) {
    int left = n % i;
    int pile = ((n / i) % 2) ? i : 0;
    check[dp[left] ^ dp[pile]] = 1;
  }
  for (int i = 0; i <= 5001; i++) {
    if (!check[i]) return i;
  }
  return 0;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  dp[0] = 0;
  dp[1] = 0;
  for (int i = 2; i <= 2000; i++) {
    dp[i] = mex(i);
  }
  int ans = 0;
  for (int i = 0; i < N; i++) {
    int num;
    cin >> num;
    ans ^= dp[num];
  }
  if (ans)
    cout << "First"
         << "\n";
  else
    cout << "Second"
         << "\n";
  return 0;
}
