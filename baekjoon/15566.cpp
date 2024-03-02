#include <algorithm>
#include <iostream>
#include <stack>
#include <vector>
using namespace std;

int N, M;
int num = 1;
vector<int> graph[200001];

int node[200001];
int low[200001];
int visited[200001];

int groupNum = 1;
int group[200001];
int ans[200001];
vector<int> seq;
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
      seq.push_back(v);
      if (v == cur)
        break;
    }
    groupNum++;
  }
}

int oppo(int x) { return (x > N * 2) ? x - N * 2 : x + N * 2; }

int interest[3001][5];
vector<int> lotus[3001];
int frog[3001][2];
int answer[3001];

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N >> M;
  for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= 4; j++) {
      cin >> interest[i][j];
    }
  }
  for (int i = 1; i <= N; i++) {
    int A, B;
    cin >> A >> B;
    if (A > B)
      swap(A, B);
    frog[i][0] = A;
    frog[i][1] = B;
    lotus[A].push_back(i * 2 - 1);
    lotus[B].push_back(i * 2);
    graph[i * 2 - 1].push_back(oppo(i * 2));
    graph[i * 2].push_back(oppo(i * 2 - 1));

    graph[oppo(i * 2 - 1)].push_back(i * 2);
    graph[oppo(i * 2)].push_back(i * 2 - 1);
  }
  for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= N; j++) {
      if (i == j)
        continue;
      for (int k = 0; k < 2; k++) {
        for (int h = 0; h < 2; h++) {
          if (frog[i][k] == frog[j][h]) {
            graph[i * 2 - !k].push_back(oppo(j * 2 - !h));
            graph[j * 2 - !h].push_back(oppo(i * 2 - !k));
          }
        }
      }
    }
  }
  while (M--) {
    int A, B, T;
    cin >> A >> B >> T;
    for (int start : lotus[A]) {
      for (int end : lotus[B]) {
        if (interest[(start + 1) / 2][T] != interest[(end + 1) / 2][T]) {
          graph[start].push_back(oppo(end));
          graph[end].push_back(oppo(start));
        }
      }
    }
  }
  for (int i = 1; i <= N * 4; i++) {
    if (!node[i])
      scc(i);
  }
  int able = 1;
  for (int i = 1; i <= N * 2; i++) {
    if (group[i] == group[oppo(i)]) {
      able = 0;
      break;
    }
  }
  cout << (able ? "YES\n" : "NO\n");
  if (!able)
    return 0;
  fill(ans, ans + N * 4 + 1, -1);
  for (auto s = seq.rbegin(); s != seq.rend(); s++) {
    int cur = *s;
    if (ans[cur] == -1) {
      ans[cur] = 0;
      ans[oppo(cur)] = 1;
    }
  }
  for (int i = 1; i <= N * 2; i++) {
    if (ans[i]) {
      answer[frog[(i + 1) / 2][!(i % 2)]] = (i + 1) / 2;
    }
  }
  for (int i = 1; i <= N; i++) {
    cout << answer[i] << " ";
  }
  return 0;
}