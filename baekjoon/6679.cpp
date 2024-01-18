#include <algorithm>
#include <iostream>

using namespace std;
int N;
int a, b, c;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  for (int i = 1000; i <= 9999; i++) {
    N = i;
    a = b = c = 0;
    while (N) {
      a += N % 10;
      N /= 10;
    }
    N = i;
    while (N) {
      b += N % 12;
      N /= 12;
    }
    N = i;
    while (N) {
      c += N % 16;
      N /= 16;
    }
    if (a == b && b == c)
      cout << i << "\n";
  }
  return 0;
}