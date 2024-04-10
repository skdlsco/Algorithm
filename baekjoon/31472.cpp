#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

int N, M;
vector<int> graph[10001];
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  N *= 2;
  int i = 2;
  while (N != i * i) {
    i++;
  }
  cout << i * 4;
  return 0;
}
