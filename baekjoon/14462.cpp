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

vector<int> arr[1001];
int A[1001];
vector<int> lis;
int N;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 1; i <= N; i++) {
    cin >> A[i];
  }
  for (int i = 1; i <= N; i++) {
    int a;
    cin >> a;
    for (int j = 1; j <= N; j++) {
      if (abs(A[j] - a) <= 4)
        arr[j].push_back(i);
    }
  }
  for (int i = 1; i <= N; i++) {
    sort(arr[i].rbegin(), arr[i].rend());
    for (int cur : arr[i]) {
      int idx = lower_bound(lis.begin(), lis.end(), cur) - lis.begin();
      if (idx == lis.size())
        lis.push_back(cur);
      else
        lis[idx] = cur;
    }
  }
  cout << lis.size();
}
