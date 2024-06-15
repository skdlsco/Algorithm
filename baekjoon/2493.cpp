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

int N;
int arr[500001];
stack<int> st;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    int cur;
    cin >> cur;
    arr[i] = cur;
    while (!st.empty() && arr[st.top()] < cur) {
      st.pop();
    }
    if (st.empty())
      cout << "0 ";
    else
      cout << st.top() + 1 << " ";
    st.push(i);
  }
  return 0;
}
