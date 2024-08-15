#include <algorithm>
#include <cmath>
#include <deque>
#include <iostream>
#include <queue>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int T;
int isMachula(string s) {
  return s.find("machula") != -1;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> T;
  while (T--) {
    string num1String, operString, num2String, temp, resultString;
    cin >> num1String;
    cin >> operString;
    cin >> num2String;
    cin >> temp;
    cin >> resultString;
    int num1, num2, result;
    if (isMachula(resultString)) {
      num1 = stoi(num1String);
      num2 = stoi(num2String);
      result = num1 + num2;
    } else if (isMachula(num1String)) {
      result = stoi(resultString);
      num2 = stoi(num2String);
      num1 = result - num2;
    } else {
      result = stoi(resultString);
      num1 = stoi(num1String);
      num2 = result - num1;
    }
    cout << num1 << " + " << num2 << " = " << result << "\n";
  }
}
