#include <algorithm>
#include <iostream>
#include <string>

using namespace std;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  string docs;
  string target;
  getline(cin, docs);
  getline(cin, target);
  int i = 0;
  if (docs.length() < target.length()) {
    cout << 0;
    return 0;
  }
  int cnt = 0;
  while (i <= docs.length() - target.length()) {
    if (docs.substr(i, target.length()) == target) {
      cnt++;
      i += target.length();
    } else
      i++;
  }
  cout << cnt;
  return 0;
}