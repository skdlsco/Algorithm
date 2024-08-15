#include <algorithm>
#include <iostream>
#include <map>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int R, G, M;

int gcd(int a, int b) {
  return b ? gcd(b, a % b) : a;
}

void print(int i) {
  cout << i << " " << R / i << " " << G / i << "\n";
}
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> R >> G;
  M = gcd(R, G);
  for (int i = 1; i * i <= M; i++) {
    if (M % i == 0) {
      print(i);
      if (i * i != M)
        print(M / i);
    }
  }
  return 0;
}