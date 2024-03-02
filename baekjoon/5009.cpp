#include <algorithm>
#include <iostream>
#include <stack>
#include <vector>
using namespace std;

const int MAX = 20200;
int N, M, A, B;
int num = 1;
vector<int> graph[MAX];
int node[MAX];
int low[MAX];
int visited[MAX];

int groupNum = 1;
int group[MAX];
stack<int> st;

int teacher[MAX];
vector<int> input[MAX];

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

int oppo(int x) { return (x > N * 3) ? (x - (N * 3)) : (x + (N * 3)); }

void init() {
  num = 1;
  groupNum = 1;
  for (int i = 1; i <= N * 6; i++) {
    node[i] = 0;
    group[i] = 0;
    low[i] = 0;
    visited[i] = 0;
    graph[i].clear();
  }
}

int check(int T) {
  init();
  // 선생 * n + 학생id
  for (int i = 1; i <= N; i++) {
    int originTeacher = teacher[i];
    // 같은 선생이 배정되면 안된다.
    graph[originTeacher * N + i].push_back(oppo(originTeacher * N + i));

    // 이외에 한곳에 배정되어야 한다.
    if (originTeacher == 0) {
      graph[1 * N + i].push_back(oppo(2 * N + i));
      graph[oppo(2 * N + i)].push_back(1 * N + i);
      graph[2 * N + i].push_back(oppo(1 * N + i));
      graph[oppo(1 * N + i)].push_back(2 * N + i);
    } else if (originTeacher == 1) {
      graph[0 * N + i].push_back(oppo(2 * N + i));
      graph[oppo(2 * N + i)].push_back(0 * N + i);
      graph[2 * N + i].push_back(oppo(0 * N + i));
      graph[oppo(0 * N + i)].push_back(2 * N + i);
    } else {
      graph[0 * N + i].push_back(oppo(1 * N + i));
      graph[oppo(1 * N + i)].push_back(0 * N + i);
      graph[1 * N + i].push_back(oppo(0 * N + i));
      graph[oppo(0 * N + i)].push_back(1 * N + i);
    }

    for (int j = T; j < N - 1; j++) {
      for (int k = 0; k <= 2; k++) {
        if (originTeacher == k)
          continue;
        int student = k * N + i;
        int other = k * N + input[i][j];
        // 서로 같은 반이 되어서는 안된다
        graph[student].push_back(oppo(other));
        graph[other].push_back(oppo(student));
      }
    }
  }

  for (int i = 1; i <= N * 6; i++) {
    if (!node[i])
      scc(i);
  }

  for (int i = 1; i <= N * 3; i++) {
    if (group[i] == group[oppo(i)]) {
      return 0;
    }
  }
  return 1;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int i = 1; i <= N; i++) {
    cin >> teacher[i];
    for (int j = 1; j < N; j++) {
      int num;
      cin >> num;
      input[i].push_back(num);
    }
  }
  int l = 0;
  int r = N - 1;
  while (l < r) {
    int mid = (l + r) / 2;
    int ans = check(mid);
    if (ans) {
      r = mid;
    } else {
      l = mid + 1;
    }
  }
  cout << l << "\n";
  return 0;
}