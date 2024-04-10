#include <algorithm>
#include <iostream>
#include <set>

using namespace std;

typedef long long int ll;

int N, Q;
set<int> arr[500001];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> Q;
  for (int i = 1; i <= N; i++) {
    int M;
    cin >> M;
    while (M--) {
      int num;
      cin >> num;
      arr[i].insert(num);
    }
  }
  while (Q--) {
    int a, b, c;
    cin >> c;
    if (c == 1) {
      cin >> a >> b;
      if (arr[a].size() < arr[b].size())
        swap(arr[a], arr[b]);
      arr[a].insert(arr[b].begin(), arr[b].end());
      arr[b] = set<int>();
    } else {
      cin >> a;
      cout << arr[a].size() << "\n";
    }
  }
  return 0;
}
