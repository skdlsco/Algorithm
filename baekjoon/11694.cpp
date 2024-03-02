#include <algorithm>
#include <iostream>

using namespace std;

int N;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);

  int ans = 0;
  int cnt = 0;
  cin >> N;
  int x;
  for (int i = 0; i < N; i++) {
    cin >> x;
    if (x == 1) cnt++;
    ans ^= x;
  }
  if (cnt == N) {
    if (cnt % 2)
      cout << "cubelover\n";
    else
      cout << "koosaga\n";
  } else {
    if (ans)
      cout << "koosaga\n";
    else
      cout << "cubelover\n";
  }
  return 0;
}
