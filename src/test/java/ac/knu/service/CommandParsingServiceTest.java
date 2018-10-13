package ac.knu.service;


import org.junit.Before;
import org.junit.Test;

import java.util.Date;

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
    public void 봇은_list명령어를_요청받으면_명령어리스트를_반환해야한다(){
        String command = commandParsingService.parseCommand("ID list");
        assertTrue(command.equalsIgnoreCase("time,add"));
    }

    @Test
    public void 봇은_time명령어를_요청받으면_현재시간을_반환해야한다(){

        String command = commandParsingService.parseCommand("ID time");
        assertTrue(command.contains("Current Time is"));
    }


    @Test
    public void 봇은_정의되지않은_명령어를_요청받으면_정의되지않은명령어가요청되었다는_String을_반환해야한다() {
        String result = commandParsingService.parseCommand("ID hhhh");
        assertTrue(result.equalsIgnoreCase("undefined command requested"));
    }
}