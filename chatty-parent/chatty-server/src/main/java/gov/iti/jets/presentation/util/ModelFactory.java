package gov.iti.jets.presentation.util;

import gov.iti.jets.presentation.models.ServerModel;

public class ModelFactory {

    private static ModelFactory modelFactory = new ModelFactory();

    private ServerModel serverModel = new ServerModel();

    private ModelFactory(){
        
    }

    public static ModelFactory getInstance() {
        return modelFactory;
    }

    public ServerModel getServerModel(){
        return serverModel;
    }
}
