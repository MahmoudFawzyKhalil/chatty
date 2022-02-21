package gov.iti.jets.services.util;

import gov.iti.jets.presentation.models.DashboardDataModel;
import gov.iti.jets.presentation.models.UserModel;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ModelFactory {
    private static ModelFactory modelFactory = new ModelFactory();

//    private UserModel userModel = new UserModel();
    private DashboardDataModel dashboardDataModel = new DashboardDataModel();


    private ModelFactory() {

    }

    public static ModelFactory getInstance() {
        return modelFactory;
    }

//    public UserModel getUserModel() {
//        return userModel;
//    }


    public DashboardDataModel getDashboardDataModel() {
        return dashboardDataModel;
    }
}
