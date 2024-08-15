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

int N, M;
int K;

int arr[201][201];
vector<int> friends;

int sum[201];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= N; j++) {
      if (i == j)
        arr[i][j] = 0;
      else
        arr[i][j] = -1;
    }
  }
  while (M--) {
    int a, b, t;
    cin >> a >> b >> t;
    arr[a][b] = t;
  }
  cin >> K;
  for (int i = 0; i < K; i++) {
    int p;
    cin >> p;
    friends.push_back(p);
  }

  for (int m = 1; m <= N; m++) {
    for (int s = 1; s <= N; s++) {
      for (int e = 1; e <= N; e++) {
        if (arr[s][m] != -1 && arr[m][e] != -1) {
          if (arr[s][e] == -1)
            arr[s][e] = arr[s][m] + arr[m][e];
          arr[s][e] = min(arr[s][e], arr[s][m] + arr[m][e]);
        }
      }
    }
  }
  for (int m = 1; m <= N; m++) {
    sum[m] = -1;
    for (int p : friends) {
      if (arr[p][m] == -1 || arr[m][p] == -1) {
        sum[m] = -1;
        break;
      }
      sum[m] = max(sum[m], arr[p][m] + arr[m][p]);
    }
  }
  vector<int> ans;
  int minV = 1234567890;
  for (int i = 1; i <= N; i++) {
    if (sum[i] == -1)
      continue;
    if (minV > sum[i]) {
      ans.clear();
      minV = sum[i];
    }
    if (minV >= sum[i])
      ans.push_back(i);
  }
  for (int p : ans) {
    cout << p << " ";
  }
  cout << "\n";
}
