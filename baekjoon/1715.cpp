#include <algorithm>
#include <iostream>
#include <queue>
#include <stack>
#include <vector>

using namespace std;

int N;
int ans;
priority_queue<int> pq;

int main() {
  cin >> N;

  int cur;
  while (N--) {
    cin >> cur;
    pq.push(-cur);
  }
  while (pq.size() >= 2) {
    int a = -pq.top();
    pq.pop();
    int b = -pq.top();
    pq.pop();
    ans += a + b;
    pq.push(-(a + b));
  }
  cout << ans;
  return 0;
}