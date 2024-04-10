#include <algorithm>
#include <iostream>
#include <string>
using namespace std;
typedef long long int ll;

int N, M;
int board[3001][3001];
ll cnt;
ll dp[3001][3001];
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  for (int y = 0; y < N; y++) {
    string s;

    cin >> s;
    for (int x = 0; x < M; x++) {
      board[y][x] = s[x] == 'B';
    }
  }
  for (int y = 0; y < N; y++) {
    for (int x = 0; x < M; x++) {
      if (y == 0 || x == 0) {
        dp[y][x] = 1;
      } else {
        if (board[y][x] == board[y][x - 1] || board[y][x] == board[y - 1][x] ||
            board[y][x] != board[y - 1][x - 1])
          dp[y][x] = 1;
        else
          dp[y][x] = min(dp[y - 1][x - 1], min(dp[y - 1][x], dp[y][x - 1])) + 1;
      }
      cnt += dp[y][x];
    }
  }
  cout << cnt << "\n";
  return 0;
}
