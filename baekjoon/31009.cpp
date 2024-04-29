#include <algorithm>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;

int N;
int cnt[1001];
int jinju, sum;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    string str;
    int cost;
    cin >> str;
    cin >> cost;
    cnt[cost]++;
    if (str.compare("jinju") == 0) {
      jinju = cost;
    }
  }
  cout << jinju << "\n";
  for (int i = jinju + 1; i < 1001; i++) {
    sum += cnt[i];
  }
  cout << sum << "\n";
  return 0;
}
