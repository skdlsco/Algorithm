#include <cstdio>

int main(void)
{
	int t;

	scanf("%d", &t);
	for (int i = 0; i < t; i++)
	{
		int a, b;

		scanf("%d %d", &a, &b);
		printf("Case #%d: %d\n", i + 1, a + b);
	}
	return (0);
}
