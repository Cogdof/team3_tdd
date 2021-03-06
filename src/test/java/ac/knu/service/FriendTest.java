package ac.knu.service;

import org.junit.Test;

import static ac.knu.service.Friend.Gender.MALE;
import static org.junit.Assert.*;

public class FriendTest {
    @Test
    public void Friend객체_생성_및_접근_테스트() {
        Friend friend = new Friend("근용", 26, MALE);

        assertTrue("근용".equalsIgnoreCase(friend.getName()));
        assertEquals(26, friend.getAge());
        assertSame(MALE, friend.getGender());
    }
}