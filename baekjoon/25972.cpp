#include <algorithm>
#include <iostream>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;

int N;
int ans;
int leftV;
pii arr[500001];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    int a, l;
    cin >> a >> l;
    arr[i] = {a, l};
  }
  sort(arr, arr + N);
  for (int i = 0; i < N; i++) {
    int a = arr[i].first;
    int l = arr[i].second;
    if (leftV < a)
      ans++;
    leftV = l + a;
  }
  cout << ans;
  return 0;
}