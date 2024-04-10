#include <algorithm>
#include <iostream>
#include <string>

using namespace std;

typedef long long int ll;

int N, P;
string s;
int A, C, G, T;
int a, c, g, t;
int ans;

int check() { return a >= A && c >= C && g >= G && t >= T; }

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> P;
  cin >> s;
  cin >> A >> C >> G >> T;
  for (int i = 0; i < P; i++) {
    a += s[i] == 'A';
    c += s[i] == 'C';
    g += s[i] == 'G';
    t += s[i] == 'T';
  }
  if (check())
    ans++;
  for (int i = P; i < N; i++) {
    a += s[i] == 'A';
    c += s[i] == 'C';
    g += s[i] == 'G';
    t += s[i] == 'T';
    a -= s[i - P] == 'A';
    c -= s[i - P] == 'C';
    g -= s[i - P] == 'G';
    t -= s[i - P] == 'T';
    if (check())
      ans++;
  }
  cout << ans;
  return 0;
}
