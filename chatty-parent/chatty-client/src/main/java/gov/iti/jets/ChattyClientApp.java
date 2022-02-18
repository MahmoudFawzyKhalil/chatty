package gov.iti.jets;

import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.application.Application;
import javafx.stage.Stage;


public class ChattyClientApp extends Application {
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();

    @Override
    public void start( Stage primaryStage ) {
        stageCoordinator.initStage( primaryStage );
        stageCoordinator.switchToLoginScene();

        primaryStage.setWidth( 960 );
        primaryStage.setHeight( 530 );
        primaryStage.setMinWidth( 960 );
        primaryStage.setMinHeight( 530 );
        primaryStage.show();

//        try {
//            ClientImpl.getInstance().loadUserModel( null );
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }


//      try {
//            var result = DaoFactory.getInstance().getInvitationDecisionDao().refuseInvite(
//                    new InvitationDecisionDto( "11111111111", "44444444444" ) );
//            System.err.println(result);
//            System.err.println("WTF BROOOOOOOOOOO");
//        } catch (NotBoundException e) {
//            e.printStackTrace();
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }

    }

    public static void main( String[] args ) {
        Application.launch();
    }
}