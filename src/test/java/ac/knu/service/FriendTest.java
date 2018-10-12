package ac.knu.service;

import ac.knu.service.Friend;
import org.junit.Test;

import static ac.knu.service.Friend.Gender.MALE;
import static org.junit.Assert.assertTrue;

public class FriendTest {
    @Test
    public void Friend객체_생성_및_접근_테스트() {
        Friend friend = new Friend("근용", 26, MALE);

        assertTrue("근용".equalsIgnoreCase(friend.getName()));
        assertTrue(26 == friend.getAge());
        assertTrue(MALE == friend.getGender());
    }
}
