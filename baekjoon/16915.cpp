#include <algorithm>
#include <iostream>
#include <stack>
#include <vector>
using namespace std;

int N, M, A, B;
int num = 1;
vector<int> graph[200001];
vector<int> room[100001];
int arr[100001];

int node[200001];
int low[200001];
int visited[200001];

int groupNum = 1;
int group[200001];
stack<int> st;

void scc(int cur) {
  low[cur] = node[cur] = num++;
  visited[cur] = 1;
  st.push(cur);
  for (int next : graph[cur]) {
    if (!node[next]) {
      scc(next);
      low[cur] = min(low[cur], low[next]);
    } else if (visited[next]) {
      low[cur] = min(low[cur], node[next]);
    }
  }
  if (node[cur] == low[cur]) {
    while (!st.empty()) {
      int v = st.top();
      st.pop();
      group[v] = groupNum;
      visited[v] = 0;
      if (v == cur)
        break;
    }
    groupNum++;
  }
}

int oppo(int x) { return (x > M) ? x - M : x + M; }
int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  for (int i = 1; i <= N; i++) {
    cin >> arr[i];
  }
  for (int i = 1; i <= M; i++) {
    int K;
    cin >> K;
    for (int j = 1; j <= K; j++) {
      int roomNum;
      cin >> roomNum;
      room[roomNum].push_back(i);
    }
  }
  for (int i = 1; i <= N; i++) {
    int A = room[i][0];
    int B = room[i][1];

    if (arr[i]) {
      graph[A].push_back(B);
      graph[B].push_back(A);
      graph[oppo(A)].push_back(oppo(B));
      graph[oppo(B)].push_back(oppo(A));
    } else {
      graph[A].push_back(oppo(B));
      graph[B].push_back(oppo(A));
      graph[oppo(A)].push_back(B);
      graph[oppo(B)].push_back(A);
    }
  }
  for (int i = 1; i <= M * 2; i++) {
    if (!node[i])
      scc(i);
  }
  int ans = 1;
  for (int i = 1; i <= M; i++) {
    if (group[i] == group[oppo(i)]) {
      ans = 0;
      break;
    }
  }
  cout << ans << "\n";
  return 0;
}