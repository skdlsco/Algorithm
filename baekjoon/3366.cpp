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

ll sum;
int N;
stack<ll> st;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  if (N == 1) {
    cout << "0\n";
    return 0;
  }
  for (int i = 0; i < N; i++) {
    ll cur;
    cin >> cur;
    while (!st.empty() && st.top() < cur) {
      st.pop();
      if (st.empty()) {
        sum += cur;
      } else {
        sum += min(cur, st.top());
      }
    }
    st.push(cur);
  }
  while (st.size() > 1) {
    st.pop();
    sum += st.top();
  }
  cout << sum << "\n";
}