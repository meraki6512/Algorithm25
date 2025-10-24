import java.util.Arrays;

/**
 * 더미 노드 수를 포함한 전체 노드 수를 미리 계산하고
 * 바이너리 트리를 탐색하면서
 * 가능한 지 아닌지를 체크하는 방식
 * (부모 노드가 더미인데, 자식 노드가 존재하면 안됨)
 *
 * 10000개의 숫자에 대해
 * 10^15 (...= 2^45~47) -> 45~47회의 탐색
 */
class 표현가능한이진트리 {
	public int[] solution(long[] numbers) {
		int[] answer = new int[numbers.length];

		String binaryNumber;
		StringBuilder sb;

		for (int k = 0; k < numbers.length; k++) {
			binaryNumber = Long.toBinaryString(numbers[k]);

			sb = new StringBuilder();
			int x = 1;
			while (binaryNumber.length() > Math.pow(2, x) - 1) x++;
			sb.append("0".repeat((int)((Math.pow(2, x) - 1) - binaryNumber.length()))).append(binaryNumber);

			answer[k] = checkIfPossible(sb.toString(), 0, sb.length()-1) ? 1 : 0;
		}

		return answer;
	}

	private boolean checkIfPossible(String binaryNumber, int st, int end) {

		if (st == end) return true;

		int mid = (st + end) / 2;
		int nxtLeftMid = (st + (mid - 1)) / 2;
		int nxtRightMid = ((mid + 1) + end) / 2;

		if (binaryNumber.charAt(mid) == '0'){
			if (binaryNumber.charAt(nxtLeftMid) == '1' ||
				binaryNumber.charAt(nxtRightMid) == '1'){
				return false;
			}
		}

		return checkIfPossible(binaryNumber, st, mid-1) && checkIfPossible(binaryNumber, mid+1, end);

	}
}