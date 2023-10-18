#include <cstdio>

int main(void)
{
	int x, y;
	int result = 1;

	scanf("%d %d", &x, &y);
	result += x < 0;
	if (y < 0)
		result = 5 - result;
	printf("%d", result);
}
