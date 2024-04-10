#include <algorithm>
#include <iostream>

using namespace std;
typedef long long int ll;

ll N;

ll sum;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  sum = ((N - 1L) * (N)) / 2;
  while (N--) {
    int cur;
    cin >> cur;
    sum -= cur;
  }
  cout << -sum;
  return 0;
}
