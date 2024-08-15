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
 
int N, x;
int l;
string s;
 
bool input() {
  cin >> s;
  return s[0] == 'a';
}
 
void query(int a, int b) {
  cout << "? " << a << " " << b << endl;
}
 
int main() {
  // cin.tie(0);
  // cout.tie(0);
  // ios_base::sync_with_stdio(false);
  cin >> N;
  x = 0;
  for (int i = 0; i < N - 1; i++) {
    if (i < l)
      continue;
    query(l, i + 1);
    bool a = input();
    query(i + 1, N);
    bool b = input();
    if (a && b) {
      x++;
      l = i + 1;
    }
  }
  if (l == 0) {
    query(0, N);
    bool a = input();
    if (a)
      cout << "! " << 1 << endl;
    else
      cout << "! " << 0 << endl;
  } else
    cout << "! " << x + 1 << endl;
}