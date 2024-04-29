#include <algorithm>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;

string str;

bool isPalindrome(string &s) {
  for (int i = 0; i < s.length() / 2; i++) {
    if (s[i] != s[s.length() - 1 - i])
      return false;
  }
  return true;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> str;
  for (int i = 0; i < str.length(); i++) {
    string sub = str.substr(0, i);
    reverse(sub.begin(), sub.end());
    string target = str + sub;
    if (isPalindrome(target)) {
      cout << str.size() + i;
      break;
    }
  }
  return 0;
}
