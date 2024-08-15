#include <algorithm>
#include <cmath>
#include <iostream>
#include <map>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

string str;
int dp[201][201];
vector<int> divisorArr[201];

int check(int s, int e, int d) {
  int cnt = (e - s + 1) / d;
  for (int i = 1; i < cnt; i++) {
    for (int j = 0; j < d; j++) {
      if (str[s + j] != str[s + i * d + j])
        return 0;
    }
  }
  return 1;
}

void initDivisors() {
  for (int i = 2; i <= 200; i++) {
    for (int j = 1; j <= i; j++) {
      if (i % j == 0)
        divisorArr[i].push_back(j);
    }
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  initDivisors();
  cin >> str;
  for (int i = 0; i < str.length(); i++) {
    dp[i][i] = 1;
  }
  for (int i = 1; i < str.length(); i++) {
    for (int j = 0; j < str.length() - i; j++) {
      dp[j][j + i] = 1234567890;
      for (int k = j; k < j + i; k++) {
        dp[j][j + i] = min(dp[j][j + i], dp[j][k] + dp[k + 1][j + i]);
      }
      for (int divisor : divisorArr[i + 1]) {
        if (!check(j, j + i, divisor))
          continue;
        int cnt = (i + 1) / divisor;
        int len = 0;
        while (cnt) {
          cnt /= 10;
          len++;
        }
        int num = dp[j][j + divisor - 1];
        dp[j][j + i] = min(dp[j][j + i], num + len + 2);
      }
    }
  }
  cout << dp[0][str.length() - 1] << "\n";
}