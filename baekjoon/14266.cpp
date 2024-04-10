#include <algorithm>
#include <iostream>
#include <queue>
using namespace std;

typedef long long int ll;
typedef pair<ll, ll> pll;

int N;
struct Node {
  ll x, y;
  int d;
  bool operator<(const Node other) const {
    if (x * other.y == other.x * y) {
      return d < other.d;
    }
    return x * other.y > other.x * y;
  }
};

priority_queue<Node> pq;
ll x1, y1, x2, y2;
int ans;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  while (N--) {
    cin >> x1 >> y1 >> x2 >> y2;
    if (x1 * y2 > x2 * y1) {
      swap(x1, x2);
      swap(y1, y2);
    }
    pq.push({x1, y1, 1});
    pq.push({x2, y2, -1});
  }
  int cur = 0;
  while (!pq.empty()) {
    Node node = pq.top();
    cur += node.d;
    ans = max(ans, cur);
    pq.pop();
  }
  cout << ans;
  return 0;
}
