package ac.knu.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommandParsingService {

    private List<String> commandList = new ArrayList<>();
    private Database database;

    public CommandParsingService(Database database) {
        commandList.add("time");
        commandList.add("add");
        this.database = database;
    }

    public String parseCommand(String command) {

        String[] commandSplitList = command.split(" ");
        command = commandSplitList[1];

        String result;

        if(command.equalsIgnoreCase("list")) {
            result = "";
            for(int i=0; i<commandList.size(); i++){
                result = result + commandList.get(i) + ",";
            }
            result = result.substring(0, result.lastIndexOf(","));
        }
        else if(command.equalsIgnoreCase("time")) {
            result = "Current Time is :" + new Date().toString();
        } else {
            result = "undefined command requested";
        }

        return result;
    }
}
