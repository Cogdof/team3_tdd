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

    public String add(Friend newFriend) {
        if(friendDatabase.size()>=10){
            return "친구를 더이상 추가할 수 없습니다.";
        }
        if(friendDatabase.containsKey(newFriend.getName())){
            return "이미 존재하는 사용자 입니다.";
        }
        else{
            friendDatabase.put(newFriend.getName(), newFriend);
            return "";
        }
    }

    public Friend find(String frinedNameToFind){
        return friendDatabase.get(frinedNameToFind);
    }
}


