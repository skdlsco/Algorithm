#include <iostream>

using namespace std;
typedef long long ll;

ll A, B, T;

ll gcd(ll a, ll b) { return b ? gcd(b, a % b) : a; }

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  while (T--) {
    cin >> A >> B;
    while (A > 1) {
      ll C = B / A + 1;
      A = A * C - B;
      B = B * C;
      ll temp = gcd(A, B);
      A /= temp;
      B /= temp;
    }
    cout << B << "\n";
  }
  return 0;
}