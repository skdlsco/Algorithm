#include <algorithm>
#include <iostream>
#include <queue>

using namespace std;

int N;
int command[1000001];
deque<int> dq;

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> command[i];
  }
  int card = 1;
  for (int i = N - 1; i >= 0; i--) {
    int c = command[i];
    if (c == 1) {
      dq.push_front(card);
    } else if (c == 2) {
      int front = dq.front();
      dq.pop_front();
      dq.push_front(card);
      dq.push_front(front);
    } else {
      int back = dq.back();
      dq.pop_back();
      dq.push_back(back);
      dq.push_back(card);
    }
    card++;
  }
  for (int card : dq) {
    cout << card << " ";
  }
  return 0;
}