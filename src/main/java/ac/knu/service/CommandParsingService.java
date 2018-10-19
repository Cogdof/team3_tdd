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

        String name = commandSplitList[2];
        String result;

        if(command.equalsIgnoreCase("time")) {
            result = "Current Time is :" + new Date().toString();
        }
        else if(command.equalsIgnoreCase("remove")) {
            result = remove(name);
        }
        else{
            result = "undefined command requested";
        }
        return result;
    }

    public String remove(String name){
        if(search(name) >=0) {
            for(int i=search(name)+1;i<commandList.size();i++){
                commandList.set(i-1,commandList.get(i));
            }
            return "remove success";
        }
        else{
            return "Not found name, remove fail";
        }
    }
    public int search(String name){
        for(int i=0;i<commandList.size();i++){
            if(name.equals(commandList.get(i))){
                return i;
            }
        }
        return -1;
    }
}
