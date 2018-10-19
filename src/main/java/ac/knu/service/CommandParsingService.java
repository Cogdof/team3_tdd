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

        String result = "";

        switch(command) {
            case "add" :
//                result = database.add();
                break;

            case "search" :
//                result = database.search(name);
                break;

            case "remove" :
                if(database.remove(name)) {
                    result = "remove success";
                }
                else{
                    result = "Not found name, remove fail";
                }
                break;

            case "list" :
                break;

            case "time" :
                result = "Current Time is :" + new Date().toString();
                break;

            default :
                result = "undefined command requested";
                break;
            }

        return result;
    }
}
