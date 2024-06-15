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
int N;
ll arr[1000001];
ll B, C;
ll ans;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 1; i <= N; i++) {
    cin >> arr[i];
  }
  cin >> B >> C;
  for (int i = 1; i <= N; i++) {
    arr[i] -= B;
    ans++;
    if (arr[i] > 0) {
      ans += arr[i] / C + (arr[i] % C > 0);
    }
  }
  cout << ans;
  return 0;
}
