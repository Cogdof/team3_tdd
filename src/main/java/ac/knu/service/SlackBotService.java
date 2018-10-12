package ac.knu.service;

import lombok.extern.slf4j.Slf4j;
import me.ramswaroop.jbot.core.common.Controller;
import me.ramswaroop.jbot.core.common.EventType;
import me.ramswaroop.jbot.core.common.JBot;
import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import javax.print.DocFlavor;
import java.util.Date;

@Profile("slack")
@Service
@JBot
@Slf4j
public class SlackBotService extends Bot {

    @Autowired
    private CommenParsingService commenParsingService;

    @Controller(events = {EventType.DIRECT_MENTION})
    public void onReceiveDM(WebSocketSession session, Event event) {
        String text = event.getText();
        log.info(text);
        String commandList =  commenParsingService.parsingCommand(text);

        reply(session,event,"=======호열봇=====");

        reply(session,event,"============");
    }

    @Value("${slackBotToken}")
    private String slackToken;

    @PostConstruct
    public void init() {
        System.out.println(slackToken);
    }

    @Override
    public String getSlackToken() {
        return this.slackToken;
    }

    @Override
    public Bot getSlackBot() {
        return this;
    }
}