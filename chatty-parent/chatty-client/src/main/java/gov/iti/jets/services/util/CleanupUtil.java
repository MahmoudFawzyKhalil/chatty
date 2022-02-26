package gov.iti.jets.services.util;

import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;

public class CleanupUtil {
    private static CleanupUtil INSTANCE = new CleanupUtil();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private ModelFactory modelFactory = ModelFactory.getInstance();

    private CleanupUtil() {

    }

    public static CleanupUtil getInstance() {
        return INSTANCE;
    }

    public void handleServerErrorCleanup() {
        serviceFactory.shutdown();
        stageCoordinator.showErrorNotification( "Failed to connect to server. Please try again later." );
        modelFactory.clearUserModel();
        stageCoordinator.switchToConnectToServer();
    }
}
