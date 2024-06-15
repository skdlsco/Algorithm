#include <algorithm>
#include <iostream>
#include <queue>
#include <stack>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;

int N;
stack<int> st;
int ans[11];

void f(int n, int cur, int idx) {
  if (idx == N) {
    for (int i = 0; i < N; i++) {
      cout << ans[i] << " ";
    }
    cout << "\n";
    return;
  }
  // pop
  if (cur > 0) {
    ans[idx] = st.top();
    st.pop();
    f(n, cur - 1, idx + 1);
    st.push(ans[idx]);
  }
  // push
  if (n <= N) {
    st.push(n);
    f(n + 1, cur + 1, idx);
    st.pop();
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  f(1, 0, 0);
  return 0;
}