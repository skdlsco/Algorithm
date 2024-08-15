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

struct Data {
  int t1;
  int t2;
  int id;
};

bool compare(const Data &a, const Data &b) {
  if (a.t2 == b.t2)
    return a.t1 < b.t1;
  return a.t2 < b.t2;
}

int N;

queue<int> line;
int inLine[100001];
int reservation[400001];
int finish[100001];
vector<Data> arr;
vector<Data> timeline[400001];

int ans;
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  arr.push_back({0, 0, 0});
  for (int i = 1; i <= N; i++) {
    int t1, t2;
    cin >> t1 >> t2;
    reservation[t1] = i;
    arr.push_back({t1, t2, i});
    timeline[t2].push_back({t1, t2, i});
  }
  for (int i = 1; i <= 200000; i++)
    sort(timeline[i].begin(), timeline[i].end(), compare);
  for (int i = 1; i <= 400000; i++) {
    for (Data &d : timeline[i]) {
      inLine[d.id] = 1;
      line.push(d.id);
    }
    int target = reservation[i];
    if (target && inLine[target] && !finish[target]) {
      finish[target] = 1;
      ans = max(ans, i - arr[target].t2);
    } else {
      while (!line.empty() && finish[line.front()]) {
        inLine[line.front()] = 0;
        line.pop();
      }
      if (!line.empty()) {
        int target = line.front();
        line.pop();
        finish[target] = 1;
        inLine[target] = 0;
        ans = max(ans, i - arr[target].t2);
      }
    }
  }
  cout << ans;
  return 0;
}