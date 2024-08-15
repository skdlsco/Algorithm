#include <algorithm>
#include <iostream>
#include <map>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int N;
ll ans;
map<string, int> s;
vector<string> arr;
void solve() {
  ans = 0;
  s.clear();
  arr.clear();
  while (N--) {
    string str;
    cin >> str;
    arr.push_back(str);
    if (str.length() <= 2) {
      continue;
    }
    for (int len = str.length() - 2; len >= 1; len--) {
      for (int left = 1; left < str.length() - len; left++) {
        string zip = str.substr(0, left) + to_string(len) + str.substr(left + len);
        if (s.find(zip) == s.end())
          s.insert({zip, 0});
        s[zip] += 1;
      }
    }
  }
  for (string &str : arr) {
    int inserted = 0;
    for (int len = str.length() - 2; len >= 1 && !inserted; len--) {
      for (int left = 1; left < str.length() - len && !inserted; left++) {
        string zip = str.substr(0, left) + to_string(len) + str.substr(left + len);
        int cnt = s.find(zip)->second;
        if (cnt == 1) {
          inserted = 1;
          ans += len;
        }
      }
    }
  }
  cout << ans << "\n";
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  while (true) {
    cin >> N;
    if (!N)
      break;
    solve();
  }
  return 0;
}