#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

int N;
int dp[1001];
int mex[3];

void reset() {
  for (int i = 0; i < 3; i++)
    mex[i] = 0;
}

int get() {
  for (int i = 0; i < 3; i++) {
    if (!mex[i])
      return i;
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  dp[0] = 0;
  for (int i = 1; i <= 1000; i++) {
    reset();
    if (i >= 3)
      mex[dp[i - 3]] = 1;
    mex[dp[i - 1]] = 1;
    dp[i] = get();
  }
  if (dp[N])
    cout << "SK";
  else
    cout << "CY";
  return 0;
}