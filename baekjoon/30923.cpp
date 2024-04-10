#include <algorithm>
#include <iostream>

using namespace std;

typedef long long int ll;

ll sum, N;
ll pre;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  sum += N * 2;
  for (int i = 0; i < N; i++) {
    ll cur;
    cin >> cur;
    sum += abs(pre - cur);
    sum += cur * 2;
    pre = cur;
  }
  sum += abs(pre - 0);
  cout << sum;
  return 0;
}