#include <algorithm>
#include <iostream>
#include <stack>
#include <vector>
using namespace std;
typedef long long ll;

int N, ans, num;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  while (N--) {
    cin >> num;
    if (num % 2)
      ans = ans ^ (num + 1) / 2;
    else
      ans = ans ^ (num - 1) / 2;
  }
  if (ans > 0)
    cout << "koosaga";
  else
    cout << "cubelover";
  return 0;
}
