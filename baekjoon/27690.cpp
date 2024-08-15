#include <algorithm>
#include <iostream>

using namespace std;

typedef long long int ll;

ll N;
int T;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  while (T--) {
    cin >> N;
    cout << (N * (N - 1)) / 2 << "\n";
  }
}