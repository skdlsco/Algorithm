def f(s, e, h):
    m = 1 ^ 2 ^ 3 ^ s ^ e
    if h == 1:
        print(s, e)
        return
    f(s, m, h - 1)
    f(s, e, 1)
    f(m, e, h - 1)

N=int(input())
print(pow(2, N) - 1)
if N <= 20:
    f(1, 3, N)