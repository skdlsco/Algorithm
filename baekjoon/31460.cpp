#include <algorithm>
#include <cstring>
#include <iostream>
#include <queue>
#include <vector>
using namespace std;

typedef long long ll;

int N, M, T;

void solve() {
  cin >> N;
  if (N == 1) {
    cout << "0\n";
    return;
  }
  cout << "1";
  while (N-- > 2) {
    cout << "2";
  }
  cout << "1\n";
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  while (T--) {
    solve();
  }
  return 0;
}