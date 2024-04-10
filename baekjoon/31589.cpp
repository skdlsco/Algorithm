#include <algorithm>
#include <deque>
#include <iostream>

using namespace std;

typedef long long int ll;
int N, K;

deque<ll> dq;
ll sum;
ll pre;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> K;
  for (int i = 0; i < N; i++) {
    ll num;
    cin >> num;
    dq.push_back(num);
  }
  sort(dq.begin(), dq.end());
  for (int i = 0; i < K; i++) {
    ll cur;
    if (i % 2) {
      cur = dq.front();
      dq.pop_front();
    } else {
      cur = dq.back();
      dq.pop_back();
    }
    if (cur > pre)
      sum += cur - pre;
    pre = cur;
  }
  cout << sum;
  return 0;
}