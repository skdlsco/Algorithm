#include <algorithm>
#include <cmath>
#include <iomanip>
#include <iostream>
#include <map>
#include <queue>
#include <set>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;

int N;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  if (N == 1) {
    char c1, c2;
    cin >> c1 >> c2;
    if (c1 == c2)
      cout << "0\n";
    else
      cout << "1\n";
    return 0;
  }
  int a, op, b;
  char c;
  for (int i = 1; i <= N; i++) {
    cin >> c;
    if (i % 2) {
      if (i == 1)
        a = c == 'T';
      else if (i != N) {
        if (op == '&')
          a &= c == 'T';
        else
          a |= c == 'T';
      } else
        b = c == 'T';
    } else {
      op = c;
    }
  }
  cin >> c;
  int require = c == 'T';
  if (require) {
    if (op == '&') {
      if (a & b)
        cout << "0\n";
      else if (a | b)
        cout << "1\n";
      else
        cout << "2\n";
    } else {
      if (a | b)
        cout << "0\n";
      else
        cout << "1\n";
    }
  } else {
    if (op == '&') {
      if (!(a & b))
        cout << "0\n";
      else
        cout << "1\n";
    } else {
      if (a & b)
        cout << "2\n";
      else if (a | b)
        cout << "1\n";
      else
        cout << "0\n";
    }
  }

  return 0;
}
