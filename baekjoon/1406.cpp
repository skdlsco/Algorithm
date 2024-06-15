#include <algorithm>
#include <cmath>
#include <iomanip>
#include <iostream>
#include <map>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;

int N;
stack<char> le;
stack<char> ri;
string str;
char ans[1000001];
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> str;
  for (char c : str) {
    le.push(c);
  }
  cin >> N;
  while (N--) {
    char command;
    cin >> command;
    if (command == 'P') {
      char input;
      cin >> input;
      le.push(input);
    } else if (command == 'L') {
      if (le.empty())
        continue;
      ri.push(le.top());
      le.pop();
    } else if (command == 'D') {
      if (ri.empty())
        continue;
      le.push(ri.top());
      ri.pop();
    } else {
      if (!le.empty())
        le.pop();
    }
  }
  for (int i = le.size() - 1; !le.empty(); le.pop(), i--) {
    ans[i] = le.top();
  }
  cout << ans;
  while (!ri.empty()) {
    cout << ri.top();
    ri.pop();
  }
  return 0;
}
