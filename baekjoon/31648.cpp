#include <iostream>
#include <string>
#include <vector>

using namespace std;

int T;
string S;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  while (T--) {
    cin >> S;

    if (S.back() == '0')
      cout << "E\n";
    else
      cout << "B\n";
  }
}