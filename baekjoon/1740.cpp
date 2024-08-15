#include <iostream>

using namespace std;

typedef long long int ll;

ll N;
ll ans;
ll num = 1;

int main() {
  cin >> N;
  while (N) {
    if (N % 2)
      ans += num;
    N /= 2;
    num *= 3;
  }
  cout << ans;
}