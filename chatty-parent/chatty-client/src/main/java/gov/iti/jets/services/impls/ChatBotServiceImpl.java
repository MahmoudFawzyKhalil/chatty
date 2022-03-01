package gov.iti.jets.services.impls;

import gov.iti.jets.commons.dtos.SingleMessageDto;
import gov.iti.jets.presentation.models.MessageModel;
import gov.iti.jets.services.ChatBotService;
import gov.iti.jets.services.SingleMessageDao;
import gov.iti.jets.services.util.DaoFactory;
import org.alicebot.ab.*;
import java.rmi.*;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatBotServiceImpl implements ChatBotService {

    private Chat chatSession;
    private SingleMessageDao singleMessageDao = DaoFactory.getInstance().getSingleMessageDao();

    @Override
    public MessageModel formulateAndSendChatBotReply( SingleMessageDto dto ) throws NotBoundException, RemoteException {

        chatSession = getChatSesssion();

        String response = generateResponse( dto.getMessageBody() );

        SingleMessageDto responseDto = new SingleMessageDto(dto.getSenderPhoneNumber(),
                dto.getReceiverPhoneNumber(),
                response,
                LocalDateTime.now(),
                "-fx-text-fill: white;",
                "-fx-background-color: grey;");

        responseDto.setSentByChatBot( true );

        singleMessageDao.sendMessage( responseDto );

        return new MessageModel( "ChatBot",
                LocalDateTime.now(),
                response,
                "-fx-text-fill: white;",
                "-fx-background-color: grey;",
                true );
    }

    private String generateResponse( String request ) {
        String response = chatSession.multisentenceRespond( request );

        Pattern removeTagsPattern = Pattern.compile( "\\<.*\\>" );
        Matcher removeTagsMatcher = removeTagsPattern.matcher( response );
        String firstResult = removeTagsMatcher.replaceAll( " " );

        Pattern removeHtmlPattern = Pattern.compile( "&.*;" );
        Matcher removeHtmlMatcher = removeHtmlPattern.matcher( firstResult );
        String secondResult = removeHtmlMatcher.replaceAll( " " );

        return secondResult;
    }

    private Chat getChatSesssion() {
        if (chatSession == null) {
            String botPath = getBotPath();
            MagicBooleans.trace_mode = false;
            Bot bot = new Bot( "super", botPath );
            return new Chat( bot );
        }
        return chatSession;
    }

    private static String getBotPath() {
        String botPath = ChatBotService.class.getResource( "/chatbot" ).toString();
        botPath = botPath.substring( 0, botPath.length() - 1 ).replace( "file:/", "" );

        // If in executable jar, use the external bots folder
        if (botPath.contains( "jar" )){
            botPath = System.getProperty( "user.dir" );
        }

        return botPath;
    }
}

