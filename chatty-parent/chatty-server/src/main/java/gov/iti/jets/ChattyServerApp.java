package gov.iti.jets;

import gov.iti.jets.commons.dtos.SingleMessageDto;
import gov.iti.jets.network.RmiManager;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.repository.entities.SingleMessageEntity;
import gov.iti.jets.repository.util.ConnectionPool;
import gov.iti.jets.repository.util.RepositoryFactory;
import gov.iti.jets.repository.util.mappers.SingleMessageMapper;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChattyServerApp extends Application {
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private RmiManager rmiManager = RmiManager.getInstance();

    @Override
    public void start(Stage primaryStage) {
        // stageCoordinator.initStage(primaryStage);
        // stageCoordinator.switchToLoginScene();
        primaryStage.setMinWidth(940);
        primaryStage.setMinHeight(500);
        primaryStage.show();

        Map<String, List<SingleMessageEntity>> map = RepositoryFactory.getInstance().getSingleMessageRepository().getMessage("22222222222");
        map.forEach((k, v) -> {
                    System.out.println("key= " +k);
                    for (SingleMessageEntity m : v) {
                        System.out.println(m.getSenderPhoneNumber()+ " "+ m.getReceiverPhoneNumber()+" "+m.getMessageBody());
                    }
                }
        );
        Map<String,List<SingleMessageDto>> messagesMapDto = new HashMap<>();
        map.forEach((k, v) -> {
            List<SingleMessageDto> messageDto = SingleMessageMapper.INSTANCE.entityListToDtoList(v);
            messagesMapDto.put(k,messageDto);
        });
        messagesMapDto.forEach((k, v) -> {
                    System.out.println("key= " +k);
                    for (SingleMessageDto m : v) {
                        System.out.println(m.getSenderPhoneNumber()+ " "+ m.getReceiverPhoneNumber()+" "+m.getMessageBody());
                    }
                }
        );

    }
    @Override
    public void stop() throws Exception {
        ConnectionPool.cleanup();
        rmiManager.close();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}