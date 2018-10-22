package ac.knu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

        String name = "";
        String result = "";
        if (commandSplitList.length >= 3) {
            name = commandSplitList[2];
        }

        switch(command) {
            case "add" :
                int age = Integer.parseInt(commandSplitList[3]);
                String Male[]= {"남자", "남" ,"Male", "Men", "male", "man"};
                String female[] = {"여자", "여", "Female","Woman", "women", "female"};
               
                Friend.Gender gender = null;
                
                if(Arrays.asList(Male).contains(commandSplitList[4])){
                    gender = Friend.Gender.MALE;
                }
                else if(Arrays.asList(female).contains(commandSplitList[4])){
                    gender = Friend.Gender.FEMALE;
                }

                Friend newFriend = new Friend(name,age,gender);
                database.add(newFriend);

                result = "Add complete";
                break;

            case "remove" :
                if(database.remove(name)) {
                    result = "remove success";
                }
                else{
                    result = "Not found name, remove fail";
                }
                break;

            case "find" :

                Friend findedFriend = database.find(name);

                if(findedFriend == null){
                    result = "The friend isn't in the friends list";
                } else {
                    result = findedFriend.getName() + " " + findedFriend.getAge();
                    if(findedFriend.getGender() == Friend.Gender.MALE){
                        result += " 남자";
                    } else {
                        result += " 여자";
                    }
                }
                break;


            case "list" :
                result = database.friendList();
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
