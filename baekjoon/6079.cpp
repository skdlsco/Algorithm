#include <algorithm>
#include <cmath>
#include <iostream>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int N;
int dy[] = {1, 1, -1, -1};
int dx[] = {1, -1, 1, -1};

int targetCnt;
vector<pii> kings;
vector<pii> ans;

char board[31][31];

int f(int x, int y, int cnt) {
  if (cnt == targetCnt)
    return 1;
  for (int d = 0; d < 4; d++) {
    int ny = y + dy[d];
    int nx = x + dx[d];
    int nny = y + dy[d] * 2;
    int nnx = x + dx[d] * 2;
    if (1 <= nny && nny <= N && 1 <= nnx && nnx <= N && board[ny][nx] == 'o') {
      board[ny][nx] = '+';
      ans.push_back({nnx, nny});
      if (f(nnx, nny, cnt + 1)) {
        return 1;
      }
      ans.pop_back();
      board[ny][nx] = 'o';
    }
  }
  return 0;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int y = 1; y <= N; y++) {
    for (int x = 1; x <= N; x++) {
      cin >> board[y][x];
      if (board[y][x] == 'K')
        kings.push_back({x, y});
      if (board[y][x] == 'o')
        targetCnt++;
    }
  }

  for (pii start : kings) {
    ans.clear();
    ans.push_back(start);
    if (f(start.first, start.second, 0)) {
      for (pii p : ans) {
        cout << p.second << " " << p.first << "\n";
      }
      return 0;
    }
  }
  cout << "impossible";
}