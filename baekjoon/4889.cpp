#include <algorithm>
#include <iostream>
#include <string>

using namespace std;

typedef long long int ll;

int N, P;
string s;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  int caseNum = 1;
  while (true) {
    cin >> s;
    if (s[0] == '-')
      break;
    int ans = 0;
    int cnt = 0;
    for (char c : s) {
      if (c == '{') {
        cnt++;
      } else {
        if (cnt == 0) {
          cnt++;
          ans++;
        } else {
          cnt--;
        }
      }
    }
    if (cnt)
      ans += cnt / 2;
    cout << caseNum << ". " << ans << "\n";
    caseNum++;
  }
  return 0;
}
