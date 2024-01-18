#include <algorithm>
#include <iostream>
#include <string>

using namespace std;

string A, B;

int cnt, ans = 1000;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> A >> B;

  if (A.length() < B.length()) {
    string temp = A;
    A = B;
    B = temp;
  }

  for (int i = 0; i < A.length() - B.length() + 1; i++) {
    cnt = 0;
    for (int j = 0; j < B.length(); j++) {
      if (i + j >= A.length())
        break;
      if (A[i + j] == B[j])
        cnt++;
    }
    cnt += A.length() - B.length();
    cnt = A.length() - cnt;
    ans = min(ans, cnt);
  }
  cout << ans;
}