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

string s;
int idx;
stack<int> st;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> s;
  reverse(s.begin(), s.end());
  for (char c : s) {
    if (c == 'x')
      st.push(0);
    else if (c == 'g') {
      if (st.size() < 1) {
        cout << -1;
        return 0;
      }
      int cur = st.top() + 1;
      st.pop();
      st.push(cur);
    } else if (c == 'f') {
      if (st.size() < 2) {
        cout << -1;
        return 0;
      }
      int left = st.top();
      st.pop();
      int right = st.top();
      st.pop();
      st.push(min(left, right));
    }
  }
  if (st.size() != 1)
    cout << -1;
  else
    cout << st.top();
}
