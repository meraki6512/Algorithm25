import java.util.Scanner;

/*
* gcd(x, K) == dist(x, K)인 1이상 N이하 정수 x의 개수를 구하라. (N: 10^6)
* 
* gcd(x, y): x, y의 최대 공약수
* gcd(x, y): 1이면(최대 공약수=1; 서로소), x<->y 간선 존재
* dist(x, y): x, y의 최단 경로
*
* 어떤 수든 1과 gcd(1)로 연결됨
* -> gcd(1)일 땐 dist(1), gdc(1외)일 땐 dist(2)
*
* K값을 기준으로 나머지 1~N과 계산했을 때 (N번)
* 답: gcd == 1인 개수 + gcd == 2인 개수
* */

public class Main {

    /*
    * 나눈 나머지로 divider를 계속 나누다가 보면 0이 되는 시점이 있음, 
    * 그 때의 divider가 gcd가 됨
    * 예를 들어
    * 7과 5의 gcd=1: 7%5=2-> 5%2=1-> 2%1=0
    * 6과 4의 gcd=2: 6%4=2-> 4%2=0
    * 9와 6의 gcd=3: 9%6=3-> 6%3=0
    * */
    public static int gcd(int x, int y){
        if (x % y == 0) return y;
        else return gcd(y, x % y);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int N = sc.nextInt();

        int ans = 0;
        for (int i=1; i<=N; i++){
            if (i == K) continue;
            int re = gcd(i, K);
            if (re == 1 || re == 2) ans ++;
        }

        System.out.println(ans);

    }
}
