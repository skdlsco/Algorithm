#include<iostream>
#include<vector>
using namespace std;

int N, M, K;

vector<vector<int>> m1;
vector<vector<int>> m2;

void inputMatrix(int Y, int X, vector<vector<int>> &m) {
	int temp;

	for (int y = 0 ; y < Y; y++)  {
		m.push_back(vector<int>());
		for (int x = 0; x < X; x++) {
			cin >> temp;
			m[y].push_back(temp);
		}
	}
}

vector<vector<int>> multiply(vector<vector<int>> &m1, vector<vector<int>> &m2) {
	int n = m1.size();
	int m = m2[0].size();
	int in = m2.size();
	vector<vector<int>> result(n, vector<int>(m, 0));

	for (int i = 0; i < n; i++){
		for (int j = 0; j < m; j++) {
			for (int k = 0; k < in; k++) {
				result[i][j] += m1[i][k] * m2[k][j];
			}
		}
	}
	return result;
}

void printMatrix(vector<vector<int>> &m) {
	for (auto row: m) {
		for (auto num: row) {
			cout << num << " ";
		}
		cout << "\n";
	}
}

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0);
	cin >> N >> M;
	inputMatrix(N, M, m1);
	cin >> M >> K;
	inputMatrix(M, K, m2);
	vector<vector<int>> result = multiply(m1, m2);
	printMatrix(result);
	return 0;
}
