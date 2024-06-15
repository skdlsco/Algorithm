#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

typedef long long int ll;

int N;
int ans;
int cur;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    int num;
    cin >> num;
    if (cur < num)
      cur++;
    else
      cur = num;
    ans = max(ans, cur);
  }
  cout << ans;
  return 0;
}
