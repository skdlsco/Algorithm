#include <algorithm>
#include <cmath>
#include <iomanip>
#include <iostream>
#include <map>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;

int N;
string str;
map<string, int> m;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  while (N--) {
    cin >> str;
    sort(str.begin(), str.end());
    auto iter = m.find(str);
    if (iter == m.end()) {
      m.insert({str, 1});
    }
  }
  cout << m.size();
  return 0;
}
