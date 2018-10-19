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

    public boolean remove(String name){

    }
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


