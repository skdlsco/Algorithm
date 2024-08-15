#include <algorithm>
#include <cmath>
#include <deque>
#include <iostream>
#include <queue>
#include <string>
#include <vector>

using namespace std;
typedef long long ll;

string s;
vector<string> arr;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> s;
  for (int i = 1; i < s.length() - 1; i++) {
    for (int j = i + 1; j < s.length(); j++) {
      string a = s.substr(0, i);
      string b = s.substr(i, j - i);
      string c = s.substr(j, s.length() - j);
      reverse(a.begin(), a.end());
      reverse(b.begin(), b.end());
      reverse(c.begin(), c.end());
      arr.push_back(a + b + c);
    }
  }
  sort(arr.begin(), arr.end());
  cout << arr[0];
}
