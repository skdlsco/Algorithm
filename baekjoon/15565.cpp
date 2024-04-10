#include <algorithm>
#include <iostream>
#include <queue>

using namespace std;

typedef long long int ll;

int N, K;
int ans;
queue<int> q;
int cnt = 0;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> K;
  ans = N + 1;
  for (int i = 0; i < N; i++) {
    int cur;
    cin >> cur;
    q.push(cur);
    cnt += cur == 1;
    while (cnt >= K) {
      ans = min(ans, (int)q.size());
      int tmp = q.front();
      cnt -= tmp == 1;
      q.pop();
    }
  }
  if (ans == N + 1)
    cout << -1;
  else
    cout << ans;
  return 0;
}