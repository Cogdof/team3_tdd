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
    public void bot_should_return_command_list_when_list_command_request(){
        String command = commandParsingService.parseCommand("ID list");
        assertTrue(command.equalsIgnoreCase("time,add"));
    }

    @Test
    public void bot_should_return_current_time_when_time_command_request(){

        String command = commandParsingService.parseCommand("ID time");
        assertTrue(command.contains("Current Time is"));
    }

    @Test
    public void bot_should_return_null_when_undefined_command_request() {
        String result = commandParsingService.parseCommand("ID hhhh");
        assertTrue(result.equalsIgnoreCase("undefined command requested"));
    }
}