package gov.iti.jets.presentation.util;

import javafx.scene.paint.Color;

public class StatusColors {
    public static final Color AVAILABLE_STATUS_COLOR = Color.web( "#4ade80" );
    public static final Color BUSY_STATUS_COLOR = Color.web( "#f87171" );
    public static final Color AWAY_STATUS_COLOR = Color.web( "#fbbf24" );

    private StatusColors(){

    }

    public static Color getColorFromStatusName( String statusName ) {
        Color color = null;

        switch (statusName) {
            case "Available":
                color = AVAILABLE_STATUS_COLOR;
                break;
            case "Away":
                color = BUSY_STATUS_COLOR;
                break;
            case "Busy":
                color = AWAY_STATUS_COLOR;
                break;
        }

        return color;
    }
}
