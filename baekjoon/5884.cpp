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

// [i] = axis, v
pii selected[3];
vector<pii> arr;

int check(int depth) {
  for (pii cow : arr) {
    int deleted = 0;
    for (int i = 0; i < depth; i++) {
      if (selected[i].first) {
        // x
        if (cow.first == selected[i].second)
          deleted = 1;
      } else {
        // y
        if (cow.second == selected[i].second)
          deleted = 1;
      }
    }
    if (!deleted) {
      if (depth == 3)
        return 0;
      selected[depth] = {1, cow.first};
      int x = check(depth + 1);
      selected[depth] = {0, cow.second};
      int y = check(depth + 1);
      return x || y;
    }
  }
  return 1;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;

  for (int i = 0; i < N; i++) {
    int x, y;
    cin >> x >> y;
    arr.push_back({x, y});
  }
  cout << check(0);
}