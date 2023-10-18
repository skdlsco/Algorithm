#include <cstdio>

int main(void)
{
	int min, max;
	int n, x;

	scanf("%d", &n);
	scanf("%d", &min);
	max = min;
	for (int i = 1; i < n; i++)
	{
		scanf("%d", &x);
		if (x > max)
			max = x;
		if (x < min)
			min = x;
	}
	printf("%d %d", min, max);
	return (0);
}
