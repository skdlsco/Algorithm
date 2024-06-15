#include <algorithm>
#include <cmath>
#include <iomanip>
#include <iostream>
#include <map>
#include <queue>
#include <set>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;

struct Log {
  int t, a, b;
};

int N, M, K;

int infected[1001];
int temp[1001];

vector<Log> logV;
bool logComp(Log a, Log b) { return a.t < b.t; }

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M >> K;

  for (int i = 0; i < K; i++) {
    int n;
    cin >> n;
    infected[n] = 1;
  }
  for (int i = 0; i < M; i++) {
    int t, a, b;
    cin >> t >> a >> b;
    logV.push_back({t, a, b});
  }
  sort(logV.begin(), logV.end(), logComp);
  for (int s = 1; s <= N; s++) {
    if (!infected[s])
      continue;
    for (int i = 1; i <= N; i++) {
      temp[i] = 0;
    }
    temp[s] = 1;
    for (Log l : logV) {
      if (temp[l.a])
        temp[l.b] = 1;
    }
    int valid = 1;
    for (int i = 1; i <= N; i++) {
      if (infected[i] != temp[i]) {
        valid = 0;
        break;
      }
    }
    if (valid) {
      cout << s;
      break;
    }
  }
  return 0;
}
