#include <cstdio>

int main(void)
{
	int dial[] = {3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,8,8,8,8,9,9,9,10,10,10,10};
	int sum = 0;
	int c;

	while ((c = getchar()) != -1)
	{
		sum += dial[c - 'A'];
	}
	printf("%d\n", sum);
	return (0);
}
