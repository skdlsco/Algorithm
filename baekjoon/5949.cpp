#include <iostream>
#include <string>

using namespace std;

string s;
int main() {
  cin >> s;
  int pad = 3 - s.length() % 3;
  for (int i = 1; i <= s.length(); i++) {
    cout << s[i - 1];
    if ((i + pad) % 3 == 0 && i != s.length())
      cout << ",";
  }
}
