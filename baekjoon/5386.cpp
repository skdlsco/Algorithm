#include <algorithm>
#include <iostream>

using namespace std;

int N, K, T;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  while (T--) {
    cin >> N >> K;
    if (K % 2) {
      cout << N % 2 << "\n";
    } else {
      if (N % (K + 1) == K)
        cout << K << "\n";
      else
        cout << ((N % 2) ^ ((N / (K + 1)) % 2)) << "\n";
    }
  }
  return 0;
}
