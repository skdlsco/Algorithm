#include <algorithm>
#include <cmath>
#include <iostream>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int N;

int conv[10001];
int rev[10001];
int arr[10001];

vector<int> lis;
int lisIdx[10001];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 1; i <= N; i++) {
    int num;
    cin >> num;
    conv[num] = i;
    rev[i] = num;
  }
  for (int i = 1; i <= N; i++) {
    int num;
    cin >> num;
    arr[conv[num]] = i;
  }
  for (int i = 1; i <= N; i++) {
    if (lis.empty() || lis.back() < arr[i]) {
      lis.push_back(arr[i]);
      lisIdx[i] = lis.size() - 1;
    } else {
      int idx = lower_bound(lis.begin(), lis.end(), arr[i]) - lis.begin();
      lis[idx] = arr[i];
      lisIdx[i] = idx;
    }
  }
  vector<int> ans;
  int idx = lis.size() - 1;
  for (int i = N; i >= 1 && idx >= 0; i--) {
    if (idx == lisIdx[i]) {
      idx--;
      ans.push_back(rev[i]);
    }
  }
  sort(ans.begin(), ans.end());
  cout << ans.size() << "\n";
  for (int i : ans) {
    cout << i << " ";
  }
}