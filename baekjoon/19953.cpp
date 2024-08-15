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

ll dy[] = {1, 0, -1, 0};
ll dx[] = {0, 1, 0, -1};
ll y, x;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  ll v, m, t;
  cin >> v >> m >> t;
  y += v;
  t--;
  for (int i = 1; i <= 4; i++) {
    v = v * m % 10;
    dy[i % 4] *= v;
    dx[i % 4] *= v;
    y += (t / 4) * dy[i % 4];
    x += (t / 4) * dx[i % 4];
  }
  t = t % 4;
  for (int i = 1; i < 1 + t; i++) {
    y += dy[i % 4];
    x += dx[i % 4];
  }
  cout << x << " " << y << "\n";
}