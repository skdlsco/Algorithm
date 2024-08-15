#include <algorithm>
#include <cmath>
#include <iomanip>
#include <iostream>
#include <map>
#include <queue>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;

int T, N;
ll sum;
priority_queue<ll> pq;

void solve() {
  cin >> N;
  sum = 0;
  while (N--) {
    ll num;
    cin >> num;
    pq.push(-num);
  }
  while (pq.size() > 1) {
    ll A = -pq.top();
    pq.pop();
    ll B = -pq.top();
    pq.pop();
    sum += A + B;
    pq.push(-(A + B));
  }
  pq.pop();
  cout << sum << "\n";
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  while (T--)
    solve();
  return 0;
}
