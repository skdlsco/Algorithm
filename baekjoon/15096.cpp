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
int sum;
int cnt;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  while (N--) {
    int num;
    cin >> num;
    if (num == -1)
      continue;
    cnt++;
    sum += num;
  }
  cout << (double)sum / cnt << "\n";
}