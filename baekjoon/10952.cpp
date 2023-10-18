#include <cstdio>

int main(void)
{
	int a, b = 1;

	while (1)
	{
		scanf("%d %d", &a, &b);
		if (a == 0 && b == 0)
			break ;
		printf("%d\n", a + b);
	}
	return (0);
}
