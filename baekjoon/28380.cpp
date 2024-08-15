#include <algorithm>
#include <iostream>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;

string origin;
string temp;
string prefix;
string result;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);

  int state = 0;
  cin >> origin;
  for (char c : origin) {
    if (c == '(')
      state++;
    else if (c == ')')
      state--;
    temp.push_back(c);
    if (state == 0) {
      if (prefix.length() == 0)
        prefix.append(temp);
      else
        result.append(temp);
      temp.clear();
    }
  }
  result.append(prefix);
  if (result == origin)
    cout << "no";
  else
    cout << result;
  return 0;
}