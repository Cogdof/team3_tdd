package ac.knu.service;


import ac.knu.service.CommenParsingService;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertTrue;

public class CommenParsingServiceTest {


    CommenParsingService commenParsingService;
    @Before
    public void setUp(){
        commenParsingService = new CommenParsingService();

    }

    @Test
    public void 봇은_list_커맨드를이해해야한다_(){
        String command = commenParsingService.parsingCommand();
        assertTrue(command.equalsIgnoreCase("time, add"));

    }

    @Test
    public void ifKeywordIsTime_returnNowTime(){

        String command = commenParsingService.parsingCommand("time");
        Date nowTime = new Date();
        String showTime = "Current time is :"+nowTime;
        assertTrue(command.equals(showTime));


    }

}
