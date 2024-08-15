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
 
string X, Y;
 
long double x, y;
 
void norm(string &s) {
  if (s.length() == 1)
    return;
  if (s[s.length() - 2] == 'N' && s[s.length() - 1] == 'E')
    return;
  if (s[s.length() - 2] == 'S' && s[s.length() - 1] == 'W')
    return;
  char temp = s[s.length() - 1];
  s[s.length() - 1] = s[s.length() - 2];
  s[s.length() - 2] = temp;
}
 
long double calc(string &s) {
  if (s.length() == 1)
    return 0;
  char first = s[s.length() - 1];
  long double temp = 45.0 / 2;
  long double sum = 45.0;
  for (int i = s.length() - 3; i >= 0; i--) {
    char c = s[i];
    if (first == c) {
      sum += temp;
    } else {
      sum -= temp;
    }
    temp /= 2;
  }
  return sum;
}
 
long double getBaseV(string &s) {
  char c;
  if (s.length() > 1)
    c = s[s.length() - 2];
  else
    c = s[s.length() - 1];
  if (c == 'N')
    return 0;
  if (c == 'E')
    return 90;
  if (c == 'S')
    return 180;
  return 270;
}
 
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> X >> Y;
 
  norm(X);
  norm(Y);
 
  x = getBaseV(X);
  y = getBaseV(Y);
 
  x += calc(X);
  y += calc(Y);
  if (x > y)
    swap(x, y);
  long double a = y - x;
  long double b = 360 - a;
  cout.precision(10);
  cout << fixed;
  if (a > b)
    cout << b << "\n";
  else
    cout << a << "\n";
}