package Algorithm.Algorithm25.Java;

public class Test {

	private static final String LAST_MSG_AT_KEY_PATTERN = "chatroom:last_msg_at:*";

	public static void main(String[] args) {
		System.out.println(parseRoomId("chatroom:last_msg_at:123"));
	}

	private static String parseRoomId(String key) {
		if (key.startsWith(LAST_MSG_AT_KEY_PATTERN.replace("*", ""))) {
			return key.substring(LAST_MSG_AT_KEY_PATTERN.length() - 1);
		}
		return null; // merge 로직에서 필터링됨
	}
}
