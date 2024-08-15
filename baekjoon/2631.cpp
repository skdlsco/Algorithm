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
vector<int> lis;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    int cur;
    cin >> cur;
    int idx = 0;
    for (; idx < lis.size() && lis[idx] < cur; idx++)
      ;
    if (idx == lis.size())
      lis.push_back(cur);
    else
      lis[idx] = cur;
  }
  cout << N - lis.size();
}