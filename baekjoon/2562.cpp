#include <cstdio>

int main(void)
{
	int max_idx, max = 0;
	int x;

	for (int i = 1; i <= 9; i++)
	{
		scanf("%d", &x);
		if (x > max)
		{
			max = x;
			max_idx = i;
		}
	}
	printf("%d\n%d", max, max_idx);
	return (0);
}
