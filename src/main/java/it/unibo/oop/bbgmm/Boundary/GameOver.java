package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;

import static it.unibo.oop.bbgmm.Boundary.Music.MENU_TRACK;

public class GameOver extends AbstractBasicView {
    private static final int SPACE_BETWEEN_ITEM = 25;
    private static final int DELTA = 80;
    private static final int GAMEOVER_X_COORDINATE = 250;
    private static final int GAMEOVER_Y_COORDINATE = 50;
    private static final int USERBOX_X_COORDINATE = 320;
    private static final int USERBOX_Y_COORDINATE = 450;
    private static final int BOX_X_COORDINATE = 350;
    private static final int BOX_Y_COORDINATE = 520;
    private final static double SOUND_VOLUME = 1;
    private final static double MUSIC_VOLUME = 0.4;
    private static final ImageView gameOVer = new ImageView(new Image("images/gameOver.png"));
    private int currentItem = 0;
    private VBox menuBox;
    private VBox boxImage;
    private HBox  userBox;
    private Label label =  new Label ("USER NAME:");
    private TextField userName = new TextField();
    private Button insert = new Button ("INSERT");
    private final MenuItem itemMainMenu = new MenuItem("MAIN MENU");
    private final MenuItem itemExit = new MenuItem("EXIT");

    public GameOver(final Stage primaryStage, final PrincipalController controller,
                    final AnchorPane pane, final Scene scene){
        super(primaryStage, controller, pane, scene);

        getScene().setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.UP) {
                if(currentItem > 0) {
                    playSwitchSound();
                    getMenuItem(currentItem).setActive(false);
                    getMenuItem(--currentItem).setActive(true);
                }
            }

            if(event.getCode() == KeyCode.DOWN) {
                if(currentItem < (menuBox.getChildren().size()) - 1 ){
                    playSwitchSound();
                    getMenuItem(currentItem).setActive(false);
                    getMenuItem(++currentItem).setActive(true);
                }
            }

            if(event.getCode() == KeyCode.ENTER){
                playPressSound();
                getMenuItem(currentItem).activate();
            }
        });

        getAudioPlayer().playMusic(Music.GAMEOVER_TRACK.getPath());

        boxImage = new VBox(gameOVer);
        boxImage.setAlignment(Pos.TOP_CENTER);
        boxImage.setTranslateX(GAMEOVER_X_COORDINATE);
        boxImage.setTranslateY(GAMEOVER_Y_COORDINATE);


        label.setTextFill(Color.web("#FFFF00"));
        label.setFont(new Font("MS Gothic", 30));
        userName.setMaxHeight(30);
        userName.setFont(new Font("MS Gothic", 15));
        userName.setPrefWidth(100);
        userName.setStyle("-fx-background-color:LIGHTSLATEGREY");
        insert.setMaxHeight(30);
        insert.setFont(new Font("MS Gothic",15));
        userBox = new HBox(label, userName, insert);
        userBox.setSpacing(20);
        userBox.setAlignment(Pos.TOP_CENTER);
        userBox.setTranslateX(USERBOX_X_COORDINATE);
        userBox.setTranslateY(USERBOX_Y_COORDINATE);

        menuBox = new VBox(SPACE_BETWEEN_ITEM, itemMainMenu, itemExit);

        buttonActions();

        menuBox.setAlignment(Pos.TOP_CENTER);

        //calculates the position of the box
        if(Resolution.isFullScreen()){
            menuBox.setLayoutX(BOX_X_COORDINATE*Resolution.getWidth()/Resolution.SMALL_WIDTH+DELTA);
            menuBox.setLayoutY(BOX_Y_COORDINATE*Resolution.getHeight()/Resolution.SMALL_HEIGHT);
        }
        else{
            menuBox.setLayoutX(BOX_X_COORDINATE);
            menuBox.setLayoutY(BOX_Y_COORDINATE);
        }

        getMenuItem(0).setActive(true);

        AnchorPane root = getRoot();
        root.getChildren().clear();
        root.getChildren().add(boxImage);
        root.getChildren().add(userBox);
        root.getChildren().add(menuBox);

        root.setId("gameOverView");
    }

    private MenuItem getMenuItem(int index){
        return (MenuItem)menuBox.getChildren().get(index);
    }

    @Override
    protected void buttonActions(){
        itemMainMenu.setOnActivate(() -> {
            getController().showMainMenu(getViewFactory());
            getAudioPlayer().stopMusic();
            getAudioPlayer().playMusic(MENU_TRACK.getPath());
        });

        itemExit.setOnActivate(() -> {
            getController().stopGame();
            System.exit(0);

        });

        insert.setOnAction( e ->{
            if(userName.getText().isEmpty()){
                final Alert alert = new Alert(Alert.AlertType.ERROR, "Please insert user name", new ButtonType("OK"));
                alert.show();
            }
            else{
                //user name and stats passed to raking ...
                String result;
                // remove spaces
                result = userName.getText().replace(" ", "");
                getController().InsertNewScore(result, 15 );
                //insert.setVisible(false);
                userName.setDisable(true);
                userBox.setDisable(true);
            }
        });

    }
}
