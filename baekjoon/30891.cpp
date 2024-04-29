#include <algorithm>
#include <iostream>
#include <math.h>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;

int N, R;
int arr[101][2];
int ans;
int ansX;
int ansY;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> R;
  for (int i = 0; i < N; i++) {
    cin >> arr[i][0] >> arr[i][1];
  }
  for (int x = -100; x <= 100; x++) {
    for (int y = -100; y <= 100; y++) {
      int cnt = 0;
      for (int i = 0; i < N; i++) {
        cnt += (x - arr[i][0]) * (x - arr[i][0]) +
                   (y - arr[i][1]) * (y - arr[i][1]) <=
               R * R;
      }
      if (ans < cnt) {
        ans = cnt;
        ansX = x;
        ansY = y;
      }
    }
  }
  cout << ansX << " " << ansY;
  return 0;
}
