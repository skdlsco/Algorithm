#include <algorithm>
#include <iostream>
using namespace std;
typedef long long ll;

ll N, K;
int s = -1, e = -1, cur;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> K;
  for (int i = 1; i <= N; i++) {
    int num;
    cin >> num;
    if ((num | K) > K) {
      s = -1;
      e = -1;
      cur = 0;
      continue;
    }
    if (s == -1)
      s = e = i;
    else
      e++;
    cur |= num;
    if (cur == K)
      break;
  }
  if (s == -1 || cur != K)
    cout << -1 << "\n";
  else
    cout << s << " " << e << "\n";
  return 0;
}
