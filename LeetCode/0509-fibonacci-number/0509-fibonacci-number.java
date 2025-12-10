class Solution {
    private int[] fibo;

    public int fib(int n) {
        fibo = new int[n+1];
        return f(n);
    }

    private int f(int n) {
        if (n == 0) return 0;
        else if (n == 1) return 1;

        int x = fibo[n-1] == 0 ? f(n-1) : fibo[n-1];
        int y = fibo[n-2] == 0 ? f(n-2) : fibo[n-2];
        return (fibo[n] = (x + y));
    }
}