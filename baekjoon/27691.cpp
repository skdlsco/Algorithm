#include <iostream>
typedef long long int ll;
using namespace std;
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