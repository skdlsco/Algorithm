#include <algorithm>
#include <iostream>
#include <queue>
#include <stack>
#include <string>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int N;
int leadTime[2];
int score[2];
int prv;
int endTime = 48 * 60;

void applyRecord(int t, int team) {
  if (score[0] > score[1])
    leadTime[0] += t - prv;
  else if (score[1] > score[0])
    leadTime[1] += t - prv;
  score[team]++;
  prv = t;
}

void printNumber(int num) {
  if (num < 10)
    cout << "0";
  cout << num;
}

void print(int team) {
  int minute = leadTime[team] / 60;
  int second = leadTime[team] % 60;
  printNumber(minute);
  cout << ":";
  printNumber(second);
  cout << "\n";
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  while (N--) {
    int team, minute, second;
    char c;
    cin >> team;
    team--;
    cin >> minute >> c >> second;
    int t = minute * 60 + second;
    applyRecord(t, team);
  }
  applyRecord(endTime, 0);
  print(0);
  print(1);
  return 0;
}