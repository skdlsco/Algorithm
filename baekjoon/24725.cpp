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

priority_queue<pii> pq;

int N, M;
int ans;
int dx[] = {0, 1, 1, 1, 0, -1, -1, -1};
int dy[] = {1, 1, 0, -1, -1, -1, 0, 1};

char check[4][2]{{'E', 'I'}, {'N', 'S'}, {'F', 'T'}, {'P', 'J'}};
char input[201][201];
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;

  for (int y = 0; y < N; y++) {
    for (int x = 0; x < M; x++) {
      cin >> input[y][x];
    }
  }
  for (int y = 0; y < N; y++) {
    for (int x = 0; x < M; x++) {
      for (int d = 0; d < 8; d++) {
        int valid = 1;
        for (int i = 0; i < 4; i++) {
          int cy = y + dy[d] * i;
          int cx = x + dx[d] * i;

          if (cy >= N || cx >= M || cy < 0 || cx < 0) {
            valid = 0;
            break;
          }
          int exist = 0;
          for (int j = 0; j < 2; j++) {
            exist |= input[cy][cx] == check[i][j];
          }
          if (!exist) {
            valid = 0;
            break;
          }
        }
        if (valid)
          ans++;
      }
    }
  }
  cout << ans;
}