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

int N;
int mask[11];
int visited[1 << 10];

int canWin(int deck) {
  int win = 0;
  int lose = 0;
  for (int i = 0; i < N; i++) {
    int enemy = deck & 2;
    int me = deck & 1;
    if (me != (enemy == 2)) {
      win += !!me;
      lose += !me;
    }
    deck >>= 2;
  }
  return win > lose;
}

void initMask() {
  int n = 1;
  for (int i = 1; i < 10; i++) {
    n |= 1 << (i - 1);
    mask[i] = n;
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  initMask();
  cin >> N;
  int start = 0;
  for (int i = 0; i < N * 2; i++) {
    char c;
    cin >> c;
    start |= (c == 'O') << i;
  }
  queue<pii> q;
  q.push({start, 0});
  while (!q.empty()) {
    auto [cur, cnt] = q.front();
    q.pop();
    if (canWin(cur)) {
      cout << cnt << "\n";
      break;
    }
    for (int i = 1; i < N * 2; i++) {
      int pick = (cur & (1 << i)) >> i;
      int front = cur & mask[i];
      int back = cur >> (i + 1);
      int next = (pick | (front << 1) | (back << (i + 1)));
      if (!visited[next]) {
        q.push({next, cnt + 1});
        visited[next] = 1;
      }
    }
  }
}