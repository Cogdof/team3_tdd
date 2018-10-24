package ac.knu.service;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CommandParsingServiceTest {

    private CommandParsingService commandParsingService;

    @Before
    public void setUp() {
        Database database = new Database();
        commandParsingService = new CommandParsingService(database);

        database.add(new Friend("신홍", 15, Friend.Gender.MALE));
        database.add(new Friend("호열", 16, Friend.Gender.MALE));
        database.add(new Friend("희수", 17, Friend.Gender.FEMALE));
        database.add(new Friend("근용", 18, Friend.Gender.MALE));
    }

    @Test
    public void 봇은_time명령어를_요청받으면_현재시간을_반환해야한다() {
        String command = commandParsingService.parseCommand("ID time");
        assertTrue(command.contains("Current Time is"));
    }

    @Test
    public void 봇은_remove명령어와_이름을_요청받으면_해당이름을_가진_친구를_삭제해야한다() {
        String command = commandParsingService.parseCommand("ID remove 희수");
        assertTrue(command.equalsIgnoreCase("remove success"));
    }

    @Test
    public void 봇은_list명령어를_요청받으면_친구목록을_보여주어야한다() {
        String listString = commandParsingService.parseCommand("ID list");
        assertTrue(listString.equalsIgnoreCase("신홍\n호열\n희수\n근용\n"));
    }

    @Test
    public void 봇은_find명령어와_이름을_요청받을때_친구리스트에_해당이름이_있으면_친구정보를_보여주어야한다() {
        String resultMessageForFindingExistMaleFriend = commandParsingService.parseCommand("ID find 신홍");
        assertTrue(resultMessageForFindingExistMaleFriend.equalsIgnoreCase("신홍 15 남자"));
        String resultMessageForFindingExistFemaleFriend = commandParsingService.parseCommand("ID find 희수");
        assertTrue(resultMessageForFindingExistFemaleFriend.equalsIgnoreCase("희수 17 여자"));
    }

    @Test
    public void 봇은_find명령어와_이름을_요청받을때_친구리스트에_해당이름이_없으면_친구정보가_없다는_메시지를_보여주어야한다() {
        String resultMessageForFindingNonExistFriend = commandParsingService.parseCommand("ID find 용근");
        assertTrue(resultMessageForFindingNonExistFriend.equalsIgnoreCase("The friend isn't in the friends list"));
    }

    @Test
    public void 봇은_정의되지않은_명령어를_요청받으면_정의되지_않은_명령어가_요청되었다는_String을_반환해야한다() {
        String resultMessageNotDefinedCommand = commandParsingService.parseCommand("ID wrongCommand");
        assertTrue(resultMessageNotDefinedCommand.equalsIgnoreCase("잘못된 입력이 들어왔습니다."));
    }

    @Test
    public void 봇은_명령어의_입력시_알맞은_길이만큼_받지_않으면_에러메세지를_출력한다() {
        // add = 5 remove,serach = 3 list,time = 2
        String resultMessageNotVaildLength = commandParsingService.parseCommand("ID add 호열 123 111 1111");
        assertTrue(resultMessageNotVaildLength.equalsIgnoreCase("잘못된 입력이 들어왔습니다."));
    }

    @Test
    public void 봇은_명령어의_입력이_알맞은_길이만큼_받으면_정상_작동한다() {
        String resultMessage = commandParsingService.parseCommand("ID add 안녕 123 남자");
        assertTrue(resultMessage.equalsIgnoreCase("Add complete"));
    }
    @Test
    public void Add_명령어를_입력했을때_이름String형_나이int형_성별String형으로_제대로입력받지않으면_특정메세지를_리턴한다(){
        String resultMessage = commandParsingService.parseCommand("ID add 안녕 열살 남자");
        String resultMessage2 = commandParsingService.parseCommand("ID add 안1녕 10 남자");
        String resultMessage3 = commandParsingService.parseCommand("ID add 안녕 10 ?자");
        assertTrue(resultMessage.equalsIgnoreCase("형식에 맞지않는 입력입니다! [add 이름 나이(숫자) 성별(남,여)] 형식으로 입력해주세요."));
        assertTrue(resultMessage2.equalsIgnoreCase("형식에 맞지않는 입력입니다! [add 이름 나이(숫자) 성별(남,여)] 형식으로 입력해주세요."));
        assertTrue(resultMessage3.equalsIgnoreCase("형식에 맞지않는 입력입니다! [add 이름 나이(숫자) 성별(남,여)] 형식으로 입력해주세요."));


    }
}