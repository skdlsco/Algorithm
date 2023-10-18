#include <cstdio>

int main(void)
{
	int N;
	int result;
	int i;

	scanf ("%d", &N);

	if (N == 1)
		printf("1\n");
	else
	{
		N--;
		i = 1;
		result = 1;
		while (N > 0)
		{
			N -= i * 6;
			result++;
			i++;
		}
		printf("%d\n", result);
	}
}
