#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

int N;
int checked[1001];
int ans;
vector<pair<int, int>> arr;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    int d, w;
    cin >> d >> w;
    arr.push_back({d, w});
  }
  for (int d = 1000; d >= 1; d--) {
    int maxIdx = -1;
    for (int i = 0; i < N; i++) {
      if (arr[i].first < d || checked[i])
        continue;
      if (maxIdx == -1 || arr[maxIdx].second < arr[i].second)
        maxIdx = i;
    }
    if (maxIdx != -1) {
      ans += arr[maxIdx].second;
      checked[maxIdx] = 1;
    }
  }
  cout << ans;
}