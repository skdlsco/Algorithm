#include <algorithm>
#include <iostream>

using namespace std;

typedef long long int ll;

ll K;
ll A[46], B[46];
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> K;
  A[0] = 1L;
  B[0] = 0L;
  for (int i = 1; i <= K; i++) {
    A[i] = B[i - 1];
    B[i] = B[i - 1] + A[i - 1];
  }
  cout << A[K] << " " << B[K] << "\n";
  return 0;
}