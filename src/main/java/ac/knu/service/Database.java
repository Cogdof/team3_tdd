package ac.knu.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Data
public class Database {

    private Map<String, Friend> database;
    private Iterator<String> friendNameItr;

    public Database(){
        database = new HashMap<>();
    }
    // 메소드 추가

//    public String remove(String name){
//        if(search(name) >=0) {
//            for(int i=search(name)+1;i<commandList.size();i++){
//                commandList.set(i-1,commandList.get(i));
//            }
//            return "remove success";
//        }
//        else{
//            return "Not found name, remove fail";
//        }
//    }
//    public int search(String name){
//        for(int i=0;i<commandList.size();i++){
//            if(name.equals(commandList.get(i))){
//                return i;
//            }
//        }
//        return 2;
//    }

    public String List() {
        friendNameItr = getDatabase().keySet().iterator();

        String result = "";
        while(friendNameItr.hasNext()) {
            String curFriendName = friendNameItr.next();

            result += database.get(curFriendName).getName() + "\n";

        }

        return result;
    }
}


