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
typedef pair<int, int> pii;

string input[7];
char ans[7][100];
pii stroke[7][5] = {
    {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}},
    {{0, 3}, {1, 3}, {2, 3}, {3, 3}, {4, 3}},
    {{0, 6}, {1, 6}, {2, 6}, {3, 6}, {4, 6}},
    {{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 3}},
    {{4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 3}},
    {{0, 3}, {0, 4}, {0, 5}, {0, 6}, {0, 6}},
    {{4, 3}, {4, 4}, {4, 5}, {4, 6}, {4, 6}},
};
int numToState[10] = {0b1111101, 0b1010000, 0b0110111, 0b1010111, 0b1011010, 0b1001111, 0b1101111, 0b1010001, 0b1111111, 0b1011111};

ll target[2];
ll sum;

bool checkStroke(int sx, int sy, int strokeNum) {
  for (int i = 0; i < 5; i++) {
    int cx = sx + stroke[strokeNum][i].first;
    int cy = sy + stroke[strokeNum][i].second;
    if (input[cy][cx] != 'x')
      return false;
  }
  return true;
}

int getLength(ll num) {
  int len = 0;
  while (num) {
    len++;
    num /= 10;
  }
  return len;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  for (int y = 0; y < 7; y++) {
    cin >> input[y];
  }
  ll targetIdx = 0;
  ll sx = 0;
  for (; sx < input[0].length(); sx += 6) {
    int state = 0;
    for (int i = 0; i < 7; i++) {
      if (checkStroke(sx, 0, i))
        state |= 1 << i;
    }
    int num = -1;
    for (int i = 0; i < 10; i++) {
      if (state == numToState[i]) {
        num = i;
        break;
      }
    }
    if (num == -1)
      targetIdx++;
    else
      target[targetIdx] = target[targetIdx] * 10 + num;
  }
  sum = target[0] + target[1];
  int len = getLength(sum);
  for (int sx = len * 6 - 5; sx >= 0; sx -= 6) {
    int num = sum % 10;
    sum /= 10;
    int state = numToState[num];
    for (int i = 0; i < 7; i++) {
      if (state & (1 << i)) {
        for (int j = 0; j < 5; j++) {
          int cx = sx + stroke[i][j].first;
          int cy = stroke[i][j].second;
          ans[cy][cx] = 1;
        }
      }
    }
  }
  for (int y = 0; y < 7; y++) {
    for (int x = 1; x < len * 6; x++) {
      if (ans[y][x])
        cout << "x";
      else
        cout << ".";
    }
    cout << "\n";
  }
  return 0;
}
