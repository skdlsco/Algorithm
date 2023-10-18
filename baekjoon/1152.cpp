#include <cstdio>

int main(void)
{
	int cnt;
	int prev;
	char c;

	cnt = 0;
	prev = ' ';
	while(scanf("%c", &c) > 0)
	{
		if (c != ' ' && c != '\n' && (prev == ' ' || prev == '\n'))
			cnt++;
		prev = c;
	}
	printf("%d", cnt);
	return (0);
}
