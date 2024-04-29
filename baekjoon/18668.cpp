#include <algorithm>
#include <cmath>
#include <iomanip>
#include <iostream>
#include <map>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;

int N, M;
int arr[500001];
int onlyOne[500001];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  for (int i = 1; i <= N; i++) {
    cin >> arr[i];
    onlyOne[i] = 1;
  }
  for (int i = 1; i <= M; i++) {
    int a, b;
    cin >> a >> b;
    if (arr[a] != 1)
      onlyOne[b] = 0;
    if (arr[b] != 1)
      onlyOne[a] = 0;
  }
  for (int i = 1; i <= N; i++) {
    if (arr[i] == 0 && onlyOne[i]) {
      cout << i;
      break;
    }
  }
  return 0;
}
