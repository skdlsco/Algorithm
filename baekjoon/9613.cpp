#include <algorithm>
#include <cmath>
#include <iostream>
#include <map>
#include <queue>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int T;
int N;
int arr[101];
ll sum;

int gcd(int a, int b) {
  return b ? gcd(b, a % b) : a;
}

void solve() {
  cin >> N;
  sum = 0;
  for (int i = 0; i < N; i++) {
    cin >> arr[i];
    for (int j = i - 1; j >= 0; j--) {
      sum += gcd(arr[i], arr[j]);
    }
  }
  cout << sum << "\n";
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  while (T--) {
    solve();
  }
}
