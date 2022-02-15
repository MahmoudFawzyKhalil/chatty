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

    public static Pattern PHONE_NUMBER_PATTERN = Pattern.compile( "[0-9]{11}" );

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
