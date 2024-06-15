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
ll total, cnt, M, sum;
ll arr[100001];
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  for (int i = 0; i < N; i++) {
    cin >> arr[i];
    total += arr[i];
  }
  sort(arr, arr + N);
  for (int i = N - 1; i >= 0; i--) {
    sum += arr[i];
    cnt++;
    if (total <= sum * 2)
      break;
  }
  cout << M / (cnt + 1);
  return 0;
}
