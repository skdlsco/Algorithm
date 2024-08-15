#include <algorithm>
#include <cmath>
#include <iostream>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int N, K;

void printSwap(int s, int e) {
  cout << "swap " << s << " " << e << "\n";
}
void printReverse(int s, int e) {
  cout << "reverse " << s << " " << e << "\n";
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> K;

  if (N == 3) {
    cout << "NO";
  } else {
    cout << "YES\n";
    if (N == 2) {
      printReverse(1, 2);
      printReverse(1, 2);
      printReverse(1, 2);
      printReverse(1, 2);
      printReverse(1, 2);
    } else if (K == 1) {
      printSwap(1, N);
      printReverse(1, N - 1);
      printReverse(1, N - 2);
      printReverse(1, N);
      printReverse(1, N);
    } else if (K == N - 1) {
      printSwap(N - 1, N);
      printReverse(1, N - 1);
      printReverse(2, N - 1);
      printReverse(1, N);
      printReverse(1, N);
    } else {
      printReverse(1, K);
      printReverse(K + 1, N);
      printReverse(1, N);
      printReverse(1, N);
      printReverse(1, N);
    }
  }
}