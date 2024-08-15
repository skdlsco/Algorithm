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
int N;
int arr[103];

ll f(int cur, int prev, int len, int hasL) {
  if (cur == N) {
    return hasL;
  }
  int nextLen;
  if (arr[cur] == 0) {
    ll ans = 0;
    if (len < 2 || prev != 1) {
      if (prev == 1)
        nextLen = len + 1;
      else
        nextLen = 1;
      ans += f(cur + 1, 1, nextLen, hasL) * 5;
    }
    if (len < 2 || prev <= 1) {
      if (prev != 1)
        nextLen = len + 1;
      else
        nextLen = 1;
      ans += f(cur + 1, 3, nextLen, 1);
      ans += f(cur + 1, 2, nextLen, hasL) * 20;
    }
    return ans;
  } else if (arr[cur] == 1) {
    if (prev == 1)
      nextLen = len + 1;
    else
      nextLen = 1;
    if (nextLen <= 2) {
      return f(cur + 1, 1, nextLen, hasL);
    }
  } else {
    if (prev > 1)
      nextLen = len + 1;
    else
      nextLen = 1;
    if (nextLen <= 2) {
      return f(cur + 1, 2 + (arr[cur] == 3), nextLen, hasL || (arr[cur] == 3));
    }
  }
  return 0;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> s;
  N = s.length();
  for (int i = 0; i < N; i++) {
    if (s[i] == 'A' || s[i] == 'E' || s[i] == 'I' || s[i] == 'O' || s[i] == 'U')
      arr[i] = 1;
    else if (s[i] == 'L') {
      arr[i] = 3;
    } else if (s[i] == '_') {
      arr[i] = 0;
    } else {
      arr[i] = 2;
    }
  }
  cout << f(0, 0, 0, 0);
}