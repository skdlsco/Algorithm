#include <algorithm>
#include <iostream>
#include <map>
#include <queue>
#include <vector>

using namespace std;

typedef long long int ll;

int A, B;
double diff;
double v;
int C, D;
int gcd(int a, int b) {
  return b ? gcd(b, a % b) : a;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> A >> B;
  diff = 1.0;
  v = (double)A / B;

  for (int i = 2; i <= 32767; i++) {
    for (int j = A * i / B - 1; j < i && j <= A * i / B + 1; j++) {
      int g = gcd(j, i);
      double curV = (double)(j / g) / (i / g);

      if (diff > abs(v - curV) && !(A == j / g && B == i / g)) {
        C = j / g;
        D = i / g;
        diff = abs(v - curV);
      }
    }
  }
  cout << C << " " << D << "\n";
  return 0;
}
