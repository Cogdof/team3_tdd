package ac.knu.service;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CommandParsingServiceTest {

    private CommandParsingService commandParsingService;
    private Database database;
    @Before
    public void setUp(){
        database = new Database();
        commandParsingService = new CommandParsingService(database);
    }

    @Test
    public void 봇은_time명령어를_요청받으면_현재시간을_반환해야한다(){
        String command = commandParsingService.parseCommand("ID time");
        assertTrue(command.contains("Current Time is"));
    }

    @Test
    public void 봇은_remove명령어와_이름을_요청받으면_해당이름을_가진_친구를_삭제해야한다(){
        database.getFriendDatabase().put("신홍",new Friend("신홍",15, Friend.Gender.MALE));
        database.getFriendDatabase().put("호열",new Friend("호열",15, Friend.Gender.MALE));
        database.getFriendDatabase().put("희수",new Friend("희수",15, Friend.Gender.MALE));
        database.getFriendDatabase().put("근용",new Friend("근용",15, Friend.Gender.MALE));
        String command = commandParsingService.parseCommand("ID remove 희수");
        assertTrue(command.equalsIgnoreCase("remove success"));
    }

    @Test
    public void 봇은_list명령어를_요청받으면_친구목록을_보여주어야한다(){
        database.add(new Friend("신홍",15, Friend.Gender.MALE));
        database.add(new Friend("호열",16, Friend.Gender.MALE));
        database.add(new Friend("희수",17, Friend.Gender.FEMALE));
        database.add(new Friend("근용",18, Friend.Gender.MALE));
        String listString = database.friendList();

        assertTrue(listString.equalsIgnoreCase("신홍\n호열\n희수\n근용\n"));
    }

    @Test
    public void 봇은_find명령어와_이름을_요청받을때_친구리스트에_해당이름이_있으면_친구정보를_보여주어야한다(){
        database.add(new Friend("상근", 25, Friend.Gender.MALE));
        String resultMessageForFindingExistMaleFriend = commandParsingService.parseCommand("ID find 상근");
        assertTrue(resultMessageForFindingExistMaleFriend.equalsIgnoreCase("상근 25 남자"));

        database.add(new Friend("성희", 25, Friend.Gender.FEMALE));
        String resultMessageForFindingExistFemaleFriend = commandParsingService.parseCommand("ID find 성희");
        assertTrue(resultMessageForFindingExistFemaleFriend.equalsIgnoreCase("성희 25 여자"));
    }

    @Test
    public void 봇은_find명령어와_이름을_요청받을때_친구리스트에_해당이름이_없으면_친구정보가_없다는_메시지를_보여주어야한다(){
        String resultMessageForFindingNonExistFriend = commandParsingService.parseCommand("ID find 용근");
        assertTrue(resultMessageForFindingNonExistFriend.equalsIgnoreCase("The friend isn't in the friends list"));
    }

    @Test
    public void 봇은_정의되지않은_명령어를_요청받으면_정의되지_않은_명령어가_요청되었다는_String을_반환해야한다() {
        String result = commandParsingService.parseCommand("ID hhhh");
        assertTrue(result.equalsIgnoreCase("undefined command requested"));
    }


}