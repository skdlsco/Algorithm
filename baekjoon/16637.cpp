#include <iostream>
#include <string>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int N;
string s;

int calc(int a, char op, int b) {
  switch (op) {
  case '-':
    return a - b;
  case '+':
    return a + b;
  default:
    return a * b;
  }
}

int f(int a, int i) {
  if (i >= N)
    return a;
  int curOp = s[i];
  int num = s[i + 1] - '0';
  int ans = f(calc(a, curOp, num), i + 2);
  if (i + 2 <= N - 1) {
    int nextOp = s[i + 2];
    num = calc(num, nextOp, s[i + 3] - '0');
    ans = max(ans, f(calc(a, curOp, num), i + 4));
  }
  return ans;
}
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  cin >> s;
  cout << f(s[0] - '0', 1);
  return 0;
}
