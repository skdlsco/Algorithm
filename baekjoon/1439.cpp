#include <algorithm>
#include <iostream>
#include <string>

using namespace std;

typedef long long int ll;

string str;

int zero;
int one;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> str;
  char prev = ' ';
  for (char c : str) {
    if (c != prev) {
      if (c == '0')
        zero++;
      else if (c == '1')
        one++;
    }
    prev = c;
  }
  cout << min(zero, one);
  return 0;
}
