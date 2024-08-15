#include <algorithm>
#include <iostream>
#include <map>
#include <string>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;
typedef pair<ll, ll> pll;

int length;
int arr[10001];
int N;
map<int, int> primeMap;

void insert(int num) {
  for (int i = 2; i * i <= num; i++) {
    if (num % i == 0) {
      primeMap[num] = 0;
      return;
    }
  }
  primeMap[num] = 1;
}

void f(int num, int len = 1) {
  if (len == N) {
    cout << num << "\n";
    return;
  }
  for (int i = 1; i <= 9; i += 2) {
    int next = num * 10 + i;
    map<int, int>::iterator iter = primeMap.find(next);
    if (iter == primeMap.end())
      insert(next);
    if (primeMap[next])
      f(next, len + 1);
  }
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  f(2);
  f(3);
  f(5);
  f(7);
}
