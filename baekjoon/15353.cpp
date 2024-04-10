#include <algorithm>
#include <iostream>
#include <sstream>
#include <string>

using namespace std;

typedef long long int ll;

string a, b;
stringstream ans;

int flag;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> a >> b;
  if (a.length() < b.length())
    swap(a, b);
  reverse(a.begin(), a.end());
  reverse(b.begin(), b.end());
  for (int i = 0; i < a.length(); i++) {
    int cur = flag + (a[i] - '0');
    if (i < b.length())
      cur += (b[i] - '0');
    if (cur > 9) {
      flag = 1;
      cur -= 10;
    } else {
      flag = 0;
    }
    ans << cur;
  }
  if (flag)
    ans << flag;
  string number = ans.str();
  reverse(number.begin(), number.end());
  cout << number;
  return 0;
}
