#include <algorithm>
#include <iostream>
#include <set>
using namespace std;
typedef long long int ll;

int N, Q, pos;
set<int> map;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> Q;
  for (int i = 0; i < N; i++) {
    int flag;
    cin >> flag;
    if (flag) map.insert(i);
  }

  while (Q--) {
    int command;
    cin >> command;
    if (command == 1) {
      int target;
      cin >> target;
      target--;
      if (map.find(target) == map.end()) {
        map.insert(target);
      } else {
        map.erase(target);
      }
    } else if (command == 2) {
      int move;
      cin >> move;
      pos = (pos + move) % N;
    } else {
      if (map.size() == 0)
        cout << "-1\n";
      else {
        auto startIter = map.upper_bound(-1);
        auto upperIter = map.upper_bound(pos - 1);
        int upper = N + 1;
        int start = N + 1;
        if (upperIter != map.end()) upper = abs(pos - *upperIter);
        if (startIter != map.end()) start = abs(N - pos + *startIter);
        cout << min(upper, start) << "\n";
      }
    }
  }
  return 0;
}
