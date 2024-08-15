#include <algorithm>
#include <cmath>
#include <iostream>
#include <map>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int isWin(string s, char c) {

  for (int i = 0; i < 3; i++) {
    // 가로
    if (s[i * 3] == c && s[i * 3 + 1] == c && s[i * 3 + 2] == c)
      return 1;
    // 세로
    if (s[i] == c && s[3 + i] == c && s[6 + i] == c)
      return 1;
  }
  // 대각선
  if (s[0] == c && s[4] == c && s[8] == c)
    return 1;
  if (s[2] == c && s[4] == c && s[6] == c)
    return 1;
  return 0;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);

  while (1) {
    string s;
    cin >> s;
    if (s == "end")
      break;
    int OCnt = 0;
    int XCnt = 0;
    for (char c : s) {
      OCnt += c == 'O';
      XCnt += c == 'X';
    }
    int OWin = isWin(s, 'O');
    int XWin = isWin(s, 'X');
    if (OCnt > XCnt || XCnt - OCnt >= 2 || (OWin && XWin) || (OWin && XCnt > OCnt) || (!OWin && !XWin && OCnt + XCnt != 9) || (XWin && XCnt <= OCnt))
      cout << "invalid\n";
    else
      cout << "valid\n";
  }
  return 0;
}
