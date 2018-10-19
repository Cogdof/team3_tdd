package ac.knu.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DatabaseTest {

    private Database database;

    @Before
    public void setUp(){
        database = new Database();

    }

    // find method tests..
    @Test
    public void find_메소드를_친구이름으로_호출할때_친구이름이_리스트에있으면_해당_친구의_객체를_반환해야한다(){

        Friend willBeAddedFriend = new Friend("근용", 16, Friend.Gender.MALE);
        database.add(willBeAddedFriend );
        Friend returnedFriend= database.find("근용");
        assertTrue(willBeFriend.equals(returnedFriend));




    }


    @Test
    public void 봇은_find_친구이름_명령어를_요청받았을때_친구이름이_리스트에없으면_NULL을_반환해야한다(){

    }

    @Test
    public void 봇은_list_명령어를_요청받았을때_친구이름들을_반환해야한다(){

    }

    @Test
    public void 봇은_list_명령어를_요청받았을때_친구가_0명이라면_대상이_존재하지않음을_반환해야한다(){

    }
}
