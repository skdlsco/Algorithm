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

struct Course {
  int group, C, S, E;
};

int N;
int ans, courseSum;
vector<Course> arr;

int comp1(Course &a, Course &b) { return a.S < b.S; }

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> N;
  for (int g = 1; g <= N; g++) {
    int M;
    cin >> M;
    courseSum += M;
    for (int i = 0; i < M; i++) {
      char c;
      int C, D, Sh, Sm, Eh, Em;
      cin >> C >> D >> Sh >> c >> Sm >> Eh >> c >> Em;
      D *= 1440;
      arr.push_back({g, C, D + Sh * 60 + Sm, D + Eh * 60 + Em});
    }
  }
  sort(arr.begin(), arr.end(), comp1);
  for (int comb = 0; comb < 1 << courseSum; comb++) {
    int C = 0;
    int isValid = 1;
    int selectedGroup[16];
    for (int i = 1; i <= N; i++) {
      selectedGroup[i] = 0;
    }
    int E = 0;
    for (int idx = 0; idx < courseSum; idx++) {
      if (comb & (1 << idx)) {
        if (E > arr[idx].S) {
          isValid = 0;
          break;
        }
        if (selectedGroup[arr[idx].group]) {
          isValid = 0;
          break;
        }
        C += arr[idx].C;
        E = arr[idx].E;
        selectedGroup[arr[idx].group] = 1;
      }
    }
    if (C != 22)
      isValid = 0;
    if (isValid)
      ans++;
  }
  cout << ans;
  return 0;
}
