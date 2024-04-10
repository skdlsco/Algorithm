#include <algorithm>
#include <iostream>

using namespace std;

typedef long long int ll;

int N, M, K, ans;

int quest[101];

int countOne(int x) {
  int cnt = 0;
  while (x) {
    x &= x - 1;
    cnt++;
  }
  return cnt;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M >> K;
  for (int i = 0; i < M; i++) {
    for (int j = 0; j < K; j++) {
      int skill;
      cin >> skill;
      quest[i] |= (1 << skill);
    }
  }
  int MAX = 1 << (N * 2 + 1);
  for (int i = 0; i < MAX; i++) {
    if (countOne(i) != N)
      continue;
    int cnt = 0;
    for (int j = 0; j < M; j++) {
      cnt += (i & quest[j]) == quest[j];
    }
    ans = max(cnt, ans);
  }
  cout << ans;
  return 0;
}
