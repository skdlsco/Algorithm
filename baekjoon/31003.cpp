#include <algorithm>
#include <iostream>
#include <queue>
#include <vector>

using namespace std;
typedef long long ll;

int N;
int arr[3001];
vector<int> graph[3001];
int inDegreeArr[3001];
priority_queue<pair<int, int>> pq;

int gcd(int a, int b) { return b ? gcd(b, a % b) : a; }

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> arr[i];
  }

  for (int i = 0; i < N; i++) {
    for (int j = i + 1; j < N; j++) {
      if (gcd(arr[i], arr[j]) != 1) {
        graph[i].push_back(j);
        inDegreeArr[j]++;
      }
    }
  }
  for (int i = 0; i < N; i++) {
    if (!inDegreeArr[i]) {
      pq.push({-arr[i], i});
    }
  }
  while (!pq.empty()) {
    pair<int, int> node = pq.top();
    pq.pop();
    cout << -node.first << " ";
    for (auto next : graph[node.second]) {
      inDegreeArr[next]--;
      if (!inDegreeArr[next]) {
        pq.push({-arr[next], next});
      }
    }
  }
  return 0;
}