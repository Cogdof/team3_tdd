package ac.knu.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommenParsingService {

    List<String> commandList = new ArrayList<String>();

    public CommenParsingService(){

        commandList.add("time");
        commandList.add("add");


    }

    public String parsingCommand() {

        String result="";

       for(int i=0; i<commandList.size(); i++){
           result += commandList.get(i);

           result += ", ";
       }

       result = result.substring(0, result.lastIndexOf( ", "));

        return result;

    }
    public String parsingCommand(String command) {


        if (command.equalsIgnoreCase("time")){
            return "Current time is :"+ new Date();
        }
        String result="";

        for(int i=0; i<commandList.size(); i++){
            result += commandList.get(i);

            result += ", ";
        }

        result = result.substring(0, result.lastIndexOf( ", "));

        return result;

    }

}
