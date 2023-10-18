#include <iostream>

using namespace std;

int dx[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
int dy[] = { 0, 1, 1, 1, 0, -1, -1, -1};

char command[] =  "AQWEDCXZ";

int map[100][100];


int centerY = 50;
int centerX = 50;
int curY = 50;
int curX = 50;

void preMove(int len, int d) {
	for (int i = 0; i < len; i++) {
		curY -= dy[d];
		curX -= dx[d];
		map[curY][curX] = d;
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int N;
	cin >> N;
	map[centerY][centerX] = 3;
	for (int len = 1; len <= 32; len++) {
		if (len % 2 == 1) {
			preMove(len, 2);
			preMove(len, 4);
		} else {
			preMove(len, 6);
			preMove(len, 0);
		}
	}
	for (int i = 0; i < N; i++) {
		int x, y;
		cin >> x >> y;
		x += 50;
		y += 50;
		while (!(x == centerX && y == centerY)) {
			int d = map[y][x];
			cout << command[d];
			x += dx[d];
			y += dy[d];
		}
		cout << "\n";
	}
}
