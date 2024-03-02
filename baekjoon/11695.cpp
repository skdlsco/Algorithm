#include <algorithm>
#include <iostream>
using namespace std;
typedef long long ll;

int N, M;

ll ans, sum;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;

  for (int y = 0; y < N; y++) {
    sum = 0;
    for (int x = 0; x < M; x++) {
      ll num;
      cin >> num;
      sum += num;
    }
    ans ^= sum;
  }

  if (ans > 0)
    cout << "august14";
  else
    cout << "ainta";
  return 0;
}
