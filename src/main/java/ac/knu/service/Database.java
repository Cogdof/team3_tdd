package ac.knu.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Database {

    private Map<String, Friend> database;
    private List<String> friendNameList;

    public Database(){
        database = new HashMap<>();
        friendNameList = new ArrayList<>();
    }
    // 메소드 추가
}


