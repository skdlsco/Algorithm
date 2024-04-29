#include <algorithm>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;

int N;
char SET[] = "AEIOU";
int input[16][3];
int dp[1 << 16][5];
int ans;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    string str;
    cin >> str;
    char start = str[0];
    char end = str[str.length() - 1];
    input[i][2] = str.length();
    for (int j = 0; j < 5; j++) {
      if (start == SET[j]) input[i][0] = j;
      if (end == SET[j]) input[i][1] = j;
    }
  }
  for (int i = 0; i < 1 << N; i++) {
    for (int j = 0; j < 5; j++) {
      for (int k = 0; k < N; k++) {
        if (1 << k & i) continue;
        if (input[k][0] != j) continue;

        int next = i | (1 << k);
        dp[next][input[k][1]] =
            max(dp[next][input[k][1]], dp[i][j] + input[k][2]);
        ans = max(ans, dp[next][input[k][1]]);
      }
    }
  }
  cout << ans;
  return 0;
}
