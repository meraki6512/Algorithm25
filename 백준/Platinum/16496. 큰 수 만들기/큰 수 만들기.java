import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/16496">16496. 큰 수 만들기</a>
 * <p>N개의 수를 나열해 만들 수 있는 가장 큰 수를 구하라.</p>
 * <ul><li>1 <= N <= 1000</li><li>0 <= num <= 1_000_000_000</li></ul>
 */
public class Main {

	// -> 그냥 문자열 정렬 문제

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		String[] nums = new String[N];
		for(int i=0; i<N; i++) nums[i] = st.nextToken();

		Arrays.sort(nums, (s1, s2) -> (s2 + s1).compareTo(s1 + s2));

		if (nums[0].equals("0")) System.out.println("0");
		else System.out.println(String.join("", nums));

	}
}