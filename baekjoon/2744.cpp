#include <iostream>
#include <string>

using namespace std;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  string str;

  cin >> str;
  for (auto c : str) {
    if ('a' <= c && c <= 'z')
      cout << (char)(c - 32);
    else
      cout << (char)(c + 32);
  }
  return 0;
}