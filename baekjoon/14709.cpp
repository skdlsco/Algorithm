#include <algorithm>
#include <cstring>
#include <iostream>
#include <stack>
#include <vector>
using namespace std;
typedef long long ll;

ll N, check[6][6];
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    int a, b;
    cin >> a >> b;
    check[a][b] = 1;
    check[b][a] = 1;
  }
  if (N == 3 && check[1][3] && check[1][4] && check[3][4])
    cout << "Wa-pa-pa-pa-pa-pa-pow!";
  else
    cout << "Woof-meow-tweet-squeek";
}
