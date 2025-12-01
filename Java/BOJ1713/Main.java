package Algorithm.Algorithm25.Java.BOJ1713;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/1713">1713. 후보 추천하기</a>
 * *
 * 처음 추천 받으면 자리에 등록
 * 이미 추천 받았었으면 추천 수만 ++
 * *
 * 자리가 없으면 추천 수가 가장 적은 학생의 사진 삭제(& 추천수=0) 후 새로운 학생 게시
 * 현재까지 추천 수가 가장 적은 학생이 2명 이상이면 게시된 지 더 오래된 것 삭제
 */
public class Main {

	private static Map<Integer, Integer> map = new HashMap<>();

	private static class Recommend implements Comparable<Recommend> {
		int idx, t;
		Recommend(int idx, int t) {
			this.idx = idx;	// 추천받은 학생 번호 idx
			this.t = t;		// 추천을 언제 받았는지 - t
		}

		@Override
		public int compareTo(Recommend o) {
			return Objects.equals(map.get(idx), map.get(o.idx)) ? t - o.t : map.get(idx) - map.get(o.idx);
		}
	}

	private static void WrongSol() throws IOException {
		// In
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 20
		int T = Integer.parseInt(br.readLine());	// 1000
		StringTokenizer st = new StringTokenizer(br.readLine());

		// Sol
		PriorityQueue<Recommend> pq = new PriorityQueue<>();	// idx
		for (int t = 0; t < T; t++) {
			int k = Integer.parseInt(st.nextToken());	// 100

			if (map.containsKey(k)) {
				// 기존 추천 받은 학생인 경우
				map.put(k, map.get(k) + 1);
			}
			else{
				// 처음 추천 받은 학생인 경우
				if (map.size() >= N) {
					// 비어있는 사진틀이 없는 경우: 추천 수가 적고 -> 오래된 것 제거
					map.remove(pq.poll().idx);
				}
				map.put(k, 1);
				pq.add(new Recommend(k, t));
			}
		}

		// Out
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= 100; i++){
			if (map.containsKey(i)) sb.append(i).append(" ");
		}
		System.out.println(sb.toString());
	}

	public static void main(String[] args) throws IOException {
	}

	private static class Student implements Comparable<Student> {
		int id;
		int count;
		int timestamp; // 게시된 시간(t)

		Student(int id, int count, int timestamp) {
			this.id = id;
			this.count = count;
			this.timestamp = timestamp;
		}

		// 정렬 기준: 1. 추천 수 오름차순, 2. 시간 오름차순
		@Override
		public int compareTo(Student o) {
			if (this.count == o.count) {
				return this.timestamp - o.timestamp;
			}
			return this.count - o.count;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null || getClass() != obj.getClass()) return false;
			Student student = (Student) obj;
			return id == student.id;
		}
	}

	public static void Solve() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 20
		int T = Integer.parseInt(br.readLine());	// 1000
		StringTokenizer st = new StringTokenizer(br.readLine());

		List<Student> frame = new ArrayList<>(); // 사진틀

		for (int t = 0; t < T; t++) {
			int k = Integer.parseInt(st.nextToken()); // 추천 학생 번호

			// 1. 이미 사진틀에 있는지 확인
			Student current = null;
			for (Student s : frame) {
				if (s.id == k) {
					current = s;
					break;
				}
			}

			if (current != null) {
				// Case 1: 이미 게시된 학생
				current.count++; // 추천수만 증가
			} else {
				// Case 2: 새로 추천받은 학생

				if (frame.size() >= N) {
					// Case 2a: 사진틀이 꽉 참 (-> 제거 대상 탐색)

					// 1. 정렬을 이용한 제거 (O(N log N))
					Collections.sort(frame); // compareTo 기준으로 정렬
					frame.remove(0); // 맨 앞(추천수 적고, 가장 오래된) 학생 제거

                /*
                // 2. 순회를 이용한 제거 (O(N)) - 더 효율적
                Student toRemove = frame.get(0);
                int removeIndex = 0;
                for (int i = 1; i < frame.size(); i++) {
                    Student s = frame.get(i);
                    if (s.count < toRemove.count || (s.count == toRemove.count && s.timestamp < toRemove.timestamp)) {
                        toRemove = s;
                        removeIndex = i;
                    }
                }
                frame.remove(removeIndex);
                */
				}

				// Case 2b: 새 학생 추가
				frame.add(new Student(k, 1, t));
			}
		}

		// Out
		List<Integer> result = new ArrayList<>();
		for (Student s : frame) {
			result.add(s.id);
		}
		Collections.sort(result); // 학생 번호 순으로 정렬

		StringBuilder sb = new StringBuilder();
		for (int id : result) {
			sb.append(id).append(" ");
		}
		System.out.println(sb);
	}
}
