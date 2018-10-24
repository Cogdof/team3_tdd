package ac.knu.service;

import ac.knu.service.exception.InputFormLengthMismatchException;

import java.util.Arrays;
import java.util.Date;

class CommandParsingService {

    private final Database database;

    public CommandParsingService(Database database) {
        this.database = database;
    }

    private boolean isValidInputForm(int commandLength, String command) {

        int limitLength;

        switch (command) {
            case "add":
                limitLength = 5;
                break;

            case "time":
            case "list":
                limitLength = 2;
                break;

            case "remove":
            case "find":
                limitLength = 3;
                break;

            default:
                return false;
        }

        return commandLength == limitLength;
    }

    public String parseCommand(String command) {

        String[] commandSplitList = command.split(" ");

        command = commandSplitList[1];

        String name = "";
        String result;
        if (commandSplitList.length >= 3) {
            name = commandSplitList[2];
        }

        try {
            if (!isValidInputForm(commandSplitList.length, command)) {
                throw new InputFormLengthMismatchException();
            }
        } catch (InputFormLengthMismatchException e) {
            return "잘못된 입력이 들어왔습니다.";
        }

        switch (command) {
            case "add":
                if(commandSplitList[2].matches(".*[0-9].*"))
                    return "형식에 맞지않는 입력입니다! [add 이름 나이(숫자) 성별(남,여)] 형식으로 입력해주세요.";

                //age 부분에 살같은 문자열이 와도 안됨
                if(commandSplitList[3].matches(".*[A-z].*" ) || commandSplitList[3].matches(".*[ㄱ-ㅎ ㅏ-ㅣ 가-힣]+.*" ))
                    return "형식에 맞지않는 입력입니다! [add 이름 나이(숫자) 성별(남,여)] 형식으로 입력해주세요.";

                int age = Integer.parseInt(commandSplitList[3]);

                String Male[] = {"남자", "남", "Male", "Men", "male", "man"};
                String female[] = {"여자", "여", "Female", "Woman", "women", "female"};

                Friend.Gender gender = null;

                if (Arrays.asList(Male).contains(commandSplitList[4])) {
                    gender = Friend.Gender.MALE;
                } else if (Arrays.asList(female).contains(commandSplitList[4])) {
                    gender = Friend.Gender.FEMALE;
                }
                else{
                    return "형식에 맞지않는 입력입니다! [add 이름 나이(숫자) 성별(남,여)] 형식으로 입력해주세요.";
                }

                Friend newFriend = new Friend(name, age, gender);


                result =  database.add(newFriend);
                break;

            case "remove":
                if (database.remove(name)) {
                    result = "remove success";
                } else {
                    result = "Not found name, remove fail";
                }
                break;

            case "find":

                Friend findedFriend = database.find(name);

                if (findedFriend == null) {
                    result = "The friend isn't in the friends list";
                } else {
                    result = findedFriend.getName() + " " + findedFriend.getAge();
                    if (findedFriend.getGender() == Friend.Gender.MALE) {
                        result += " 남자";
                    } else {
                        result += " 여자";
                    }
                }
                break;


            case "list":
                result = database.friendList();
                break;

            case "time":
                result = "Current Time is :" + new Date().toString();
                break;

            default:
                result = "undefined command requested";
                break;
        }

        return result;
    }
}
