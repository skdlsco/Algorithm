#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;

typedef long long int ll;

int N, M, K;
vector<int> inGraph[100001];
int cnt[100001];
int inDegree[100001];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M >> K;
  while (M--) {
    int a, b;
    cin >> a >> b;
    inGraph[a].push_back(b);
    inDegree[b]++;
  }

  while (K--) {
    int c, a;
    cin >> c >> a;

    if (c == 1) {
      if (inDegree[a]) {
        cout << "Lier!";
        return 0;
      }
      if (!cnt[a]) {
        for (int next : inGraph[a]) {
          inDegree[next]--;
        }
      }
      cnt[a]++;
    } else {
      if (!cnt[a]) {
        cout << "Lier!";
        return 0;
      }
      if (cnt[a] == 1) {
        for (int next : inGraph[a]) {
          inDegree[next]++;
        }
      }
      cnt[a]--;
    }
  }
  cout << "King-God-Emperor";
  return 0;
}
