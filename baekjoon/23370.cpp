#include <algorithm>
#include <iostream>
#include <map>
#include <queue>
#include <vector>

using namespace std;

typedef long long int ll;
typedef pair<int, int> pii;

string src, dest;
vector<string> srcArr, destArr;
int bcIdx, srcDIdx, destDIdx;

vector<string> split(string &str) {
  vector<string> result;
  int pos = 0;
  int prev = 0;
  string temp = "";
  while ((pos = str.find('/', pos)) != string::npos) {
    temp = str.substr(prev, pos - prev);
    result.push_back(temp);
    pos++;
    prev = pos;
  }
  result.push_back(str.substr(prev));
  return result;
}

int main() {
  cin.tie(0);
  cout.tie(0);
  ios_base::sync_with_stdio(false);
  cin >> src >> dest;
  srcArr = split(src);
  destArr = split(dest);

  while (bcIdx < srcArr.size() && bcIdx < destArr.size() && srcArr[bcIdx] == destArr[bcIdx]) {
    bcIdx++;
  }
  srcDIdx = srcArr.size() - 1;
  destDIdx = destArr.size() - 1;
  while (bcIdx <= srcDIdx && bcIdx <= destDIdx && srcArr[srcDIdx] == destArr[destDIdx]) {
    srcDIdx--;
    destDIdx--;
  }
  srcDIdx++;
  destDIdx++;
  for (int i = 0; i < bcIdx; i++) {
    cout << srcArr[i] << "/";
  }
  cout << "{";
  for (int i = bcIdx; i < srcDIdx; i++) {
    cout << srcArr[i];
    if (i < srcDIdx - 1)
      cout << "/";
  }
  cout << " => ";
  for (int i = bcIdx; i < destDIdx; i++) {
    cout << destArr[i];
    if (i < destDIdx - 1)
      cout << "/";
  }
  cout << "}";
  for (int i = srcDIdx; i < srcArr.size(); i++) {
    cout << "/" << srcArr[i];
  }
  return 0;
}
