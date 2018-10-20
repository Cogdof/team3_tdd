package ac.knu.service;

import lombok.Data;

import java.util.*;

@Data
public class Database {

    private Map<String, Friend> friendDatabase;
    private Iterator<String> friendNameItr;

    public Database(){
        friendDatabase = new HashMap<>();
    }

    public boolean remove(String name){
        if(friendDatabase.containsKey(name)) {
            friendDatabase.remove(name);
            return true;
        }
        else{
            return false;
        }
    }

    public String friendList() {
        friendNameItr = getFriendDatabase().keySet().iterator();

        String result = "";

        if(friendDatabase.size() == 0){
            return "친구가 존재하지 않습니다.";
        }

        while(friendNameItr.hasNext()) {
            String curFriendName = friendNameItr.next();

            result += friendDatabase.get(curFriendName).getName() + "\n";

        }

        return result;
    }

    public void add(Friend newFriend) {
        friendDatabase.put(newFriend.getName(), newFriend);   }
}


