package gov.iti.jets;

import gov.iti.jets.commons.dtos.CountryDto;
import gov.iti.jets.network.RmiManager;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.repository.entities.CountryEntity;
import gov.iti.jets.repository.util.RepositoryFactory;
import gov.iti.jets.repository.util.mappers.CountryMapper;
import gov.iti.jets.services.util.ServiceFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Optional;


public class ChattyServerApp extends Application {
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
    private RmiManager rmiManager = RmiManager.getInstance();

    @Override
    public void start(Stage primaryStage){
        // stageCoordinator.initStage(primaryStage);
        // stageCoordinator.switchToLoginScene();
        primaryStage.setMinWidth(940);
        primaryStage.setMinHeight(500);
        primaryStage.show();

        Optional<CountryEntity> e=repositoryFactory.getCountryRepository().getById(1);
        CountryDto dto = CountryMapper.INSTANCE.countryEntityToDto(e.get());
        System.out.println(dto.getCountryName());

    }

    @Override
    public void stop() throws Exception {
        rmiManager.close();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}