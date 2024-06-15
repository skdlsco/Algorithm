#include <algorithm>
#include <iostream>
#include <map>
#include <queue>
#include <vector>

using namespace std;

typedef long long int ll;

int K;
vector<int> arr[11];

void dfs(int depth, int node) {
  if (depth == K) {
    int num;
    cin >> num;
    arr[depth].push_back(num);
    return;
  }
  dfs(depth + 1, node * 2);
  int num;
  cin >> num;
  arr[depth].push_back(num);
  dfs(depth + 1, node * 2 + 1);
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> K;
  dfs(1, 1);
  for (int i = 1; i <= K; i++) {
    for (int num : arr[i]) {
      cout << num << " ";
    }
    cout << "\n";
  }
  return 0;
}
