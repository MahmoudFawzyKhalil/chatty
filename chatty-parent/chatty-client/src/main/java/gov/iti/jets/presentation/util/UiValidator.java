package gov.iti.jets.presentation.util;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.synedra.validatorfx.DefaultDecoration;
import net.synedra.validatorfx.GraphicDecoration;
import net.synedra.validatorfx.ValidationMessage;
import net.synedra.validatorfx.Validator;

import java.util.regex.Pattern;

public class UiValidator {

    private static final UiValidator INSTANCE = new UiValidator();

    public static Pattern EGY_PHONE_NUMBER_PATTERN = Pattern.compile( "^(01)[1250][0-9]{8}$" );
    public static Pattern USER_NAME_PATTERN = Pattern.compile( "[a-zA-Z0-9]+" );
    public static Pattern GROUP_CHAT_NAME_PATTERN = Pattern.compile( "[a-zA-Z0-9]+" );
    public static Pattern EMAIL_PATTERN = Pattern.compile( "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$" );
    public static Pattern IP_PATTERN = Pattern.compile( "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$" );




    private UiValidator() {
        DefaultDecoration.setFactory( this::createDecorationNode );
    }

    public static UiValidator getInstance() {
        return INSTANCE;
    }

    public Validator createValidator() {
        return new Validator();
    }

    private GraphicDecoration createDecorationNode( ValidationMessage message ) {

        Image errorImage = new Image( getClass().getResource( "/images/error.png" ).toString() );
        Node graphic = new ImageView( errorImage );

        Label label = new Label();
        label.setGraphic( graphic );

        Tooltip tooltip = new Tooltip( message.getText() );
        tooltip.setStyle( "-fx-background-color: rgba(100, 100, 100, 0.2)" );

        label.setTooltip( tooltip );
        label.setAlignment( Pos.CENTER );
        return new GraphicDecoration( label, Pos.TOP_RIGHT );
    }
}
