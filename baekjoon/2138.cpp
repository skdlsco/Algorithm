#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N;
int ans = 123456789;

vector<int> src;
vector<int> target;

void push(vector<int> &v, int i) {
	if (i > 0)
		v[i - 1] ^= 1;
	if (i + 1 < v.size())
		v[i + 1] ^= 1;
	v[i] ^= 1;
}

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0);
	cin >> N;
	char temp;
	for (int i = 0; i < N; i++) {
		cin >> temp;
		src.push_back(temp - '0');
	}
	for (int i = 0; i < N; i++) {
		cin >> temp;
		target.push_back(temp - '0');
	}

	for (int i = 0; i <= 1; i++) {
		vector<int> arr(src);
		int cnt = i;
		if (i == 1)
			push(arr, 0);
		for (int j = 1; j < N; j++) {
			if (arr[j - 1] != target[j - 1]) {
				push(arr, j);
				cnt++;
			}
		}
		if (arr[N - 1] == target[N - 1]) {
			ans = min(ans, cnt);
		}
	}
	cout << (ans == 123456789 ? -1 : ans);
	return 0;
}
