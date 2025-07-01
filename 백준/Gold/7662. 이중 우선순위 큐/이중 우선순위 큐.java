///*
//* dual 우선순위 큐 - 데이터 삭제 시, 명령에 따라 가장 높은 or 낮은 우선순위 선택해 삭제함
//* D -1: 최솟값 삭제
//* D 1: 최댓값 삭제
//* I n: n 삽입
//* 빈 큐에서 D 연산이 명령으로 오면 연산 무시
//* 정수만 우선순위 값으로 저장하는 dual pq에 적용될 연산이 주어질 때,
//* 이를 처리하고 최종적으로 남은 데이터의 최댓값과 최솟값을 공백으로 구분해 출력하라.
//* 남은 데이터가 없다면 EMPTY를 출력.
//* */
//
//import java.util.Scanner;
//import java.util.TreeMap;
//import java.util.TreeSet;
//
//public class Main {
//    public static void main(String[] args) {
//
//        Scanner sc = new Scanner(System.in);
//
//        int T = sc.nextInt();                   // 테스트 케이스 수
//        for (int t = 0; t<T; t++){
//
//            int k = sc.nextInt();               // 연산 개수 1,000,000
//
//            // 전체 정렬이 되어있어야 최솟값과 최댓값에 모두 자유롭게 접근
//            // -> Tree Set (전체 정렬된 이진 탐색 트리)
//            // 근데 중복 허용하므로 Tree Map으로 개수를 세자.
//
//            TreeMap<Integer, Integer> treeMap = new TreeMap<>();
//
//            for (int i=0; i<k; i++){
//
//                char op = sc.next().charAt(0);
//                int num = sc.nextInt();
//
//                if (op=='I'){                   // num Insert
//                    if (treeMap.containsKey(num)) {
//                        treeMap.put(num, treeMap.get(num) + 1);
//                    }
//                    else {
//                        treeMap.put(num, 1);
//                    }
//                }
//                else if (treeMap.isEmpty()){    // 비어있을 때 Delete 시도하면 무시
//                    continue;
//                }
//                else if (num == -1){            // Delete min
//                    int v = treeMap.firstEntry().getValue();
//                    if (v == 1) {
//                        treeMap.pollFirstEntry();
//                    }
//                    else if (v > 1){
//                        treeMap.put(treeMap.firstKey(), v-1);
//                    }
//                }
//                else if (num == 1){             // Delete max
//                    int v = treeMap.lastEntry().getValue();
//                    if (v == 1) {
//                        treeMap.pollLastEntry();
//                    }
//                    else if (v > 1){
//                        treeMap.put(treeMap.lastKey(), v-1);
//                    }
//                }
//            }
//
//            // 연산이 끝나면 출력
//            if (treeMap.isEmpty()) System.out.println("EMPTY");
//            else System.out.println(String.valueOf(treeMap.lastKey()) + " " + String.valueOf(treeMap.firstKey()));
//
//        }
//
//    }
//}
