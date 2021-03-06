package ac.knu.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DatabaseTest {

    private Database database;

    @Before
    public void setUp() {
        database = new Database();
    }

    @Test
    public void Add_명령어를_입력했을때__데이터베이스에_정상적으로_입력되었다면_key값이_존재한다() {
        Friend newFriend = new Friend("김씨", 16, Friend.Gender.MALE);
        database.add(newFriend);
        assertTrue(database.getFriendDatabase().containsKey(newFriend.getName()));
    }

    @Test
    public void Friend가_10명_즉_MAX일때_더이상_추가되지_않아야하고_특정메세지를_리턴한다() {
        Friend newFriend;
        for (int i = 0; i < 10; i++) {
            newFriend = new Friend("a" + i, 10, Friend.Gender.MALE);
            database.add(newFriend);
        }
        newFriend = new Friend("11명째", 11, Friend.Gender.MALE);
        String result = database.add(newFriend);
        assertEquals("친구를 더이상 추가할 수 없습니다.", result);
    }

    @Test
    public void Add_명령어를_입력했을떄_같은이름이_존재하면_입력받지않고_특정메세지를_리턴한다() {
        Friend newFriend = new Friend("김씨", 16, Friend.Gender.MALE);
        database.add(newFriend);
        String result = database.add(new Friend("김씨", 16, Friend.Gender.MALE));
        String result2 = database.add(new Friend("박씨", 16, Friend.Gender.MALE));

        assertEquals("이미 존재하는 사용자 입니다.", result);
        assertNotEquals("이미 존재하는 사용자 입니다.", result2);
    }

    @Test
    public void 봇은_list명령어를_요청받으면_친구목록을_반환해야한다() {
        database.add(new Friend("신홍", 15, Friend.Gender.MALE));
        database.add(new Friend("호열", 16, Friend.Gender.MALE));
        database.add(new Friend("희수", 17, Friend.Gender.FEMALE));
        database.add(new Friend("근용", 18, Friend.Gender.MALE));
        String listString = database.friendList();

        assertTrue(listString.equalsIgnoreCase("친구목록\n--------\n신홍\n호열\n희수\n근용\n--------\n"));
    }

    @Test
    public void 봇은_친구가_0명이면_checkFriendNumberZero_에서_True_를_반환한다(){
        assertTrue(database.checkFriendNumberZero());
    }

    @Test
    public void 봇은_친구가_0명이_아니면_checkFriendNumberZero_에서_False_를_반환한다(){
        database.getFriendDatabase().put("신홍", new Friend("신홍", 15, Friend.Gender.MALE));
        assertFalse(database.checkFriendNumberZero());
    }

    @Test
    public void 봇은_list_명령어를_요청받았을때_친구가_0명이라면_대상이_존재하지않음을_반환해야한다() {
        String listString = database.friendList();
        assertTrue(listString.equalsIgnoreCase("친구가 존재하지 않습니다."));
    }

    @Test
    public void 봇은_remove명령어를_요청받으면_친구가_있는지_찾고_있으면_삭제해야한다() {
        database.getFriendDatabase().put("신홍", new Friend("신홍", 15, Friend.Gender.MALE));
        database.getFriendDatabase().put("호열", new Friend("호열", 15, Friend.Gender.MALE));
        database.getFriendDatabase().put("희수", new Friend("희수", 15, Friend.Gender.MALE));
        database.getFriendDatabase().put("근용", new Friend("근용", 15, Friend.Gender.MALE));
        boolean removeSuccess = database.remove("신홍");
        boolean removeFail = database.remove("신영");

        assertTrue(removeSuccess);
        assertFalse(removeFail);
    }

    @Test
    public void find_메소드를_친구이름으로_호출할때_해당친구이름이_리스트에있으면_해당_친구의_Friend객체를_반환해야한다() {
        Friend willBeAddedFriend = new Friend("박씨", 21, Friend.Gender.MALE);
        database.add(willBeAddedFriend);

        Friend findFriend = database.find("박씨");
        assertTrue(findFriend.equals(willBeAddedFriend));
    }

    @Test
    public void find_메소드를_친구이름으로_호출할때_해당친구이름이_리스트에없으면_NULL을_반환해야한다() {
        Friend findFriend = database.find("송씨");
        assertNull(findFriend);
    }

}
