#include <algorithm>
#include <cmath>
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
int v[] = {4, 6, 4, 10};
map<string, int> m;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);

  cin >> N;
  while (N--) {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 7; j++) {
        string s;
        cin >> s;
        if (s == "-")
          continue;
        m[s] += v[i];
      }
    }
  }
  int minV = 1234567890;
  int maxV = 0;
  for (auto item : m) {
    minV = min(minV, item.second);
    maxV = max(maxV, item.second);
  }
  if (maxV - minV <= 12)
    cout << "Yes";
  else
    cout << "No";
}