package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Utilities.FontMaker;
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

import javax.swing.text.DocumentFilter;
import java.awt.*;

import static it.unibo.oop.bbgmm.Boundary.Music.MENU_TRACK;

public class GameOver extends AbstractBasicView {
    private static final int SPACE_BETWEEN_ITEM = 25;
    private static final int DELTA = 80;
    private static final int USERBOX_X_COORDINATE = 320;
    private static final int USERBOX_Y_COORDINATE = 450;
    private static final int BOX_X_COORDINATE = 350;
    private static final int BOX_Y_COORDINATE = 520;
    private int currentItem = 0;
    private VBox menuBox;
    private HBox  userBox;
    private Label label =  new Label ("USER NAME:");
    private MenuItem itemInsert = new MenuItem("INSERT");
    private final MenuItem itemMainMenu = new MenuItem("MAIN MENU");
    private final MenuItem itemExit = new MenuItem("EXIT");
    private TextField userName = new TextField() {
        @Override public void replaceText(int start, int end, String text) {
            super.replaceText(start, end, text.toUpperCase());
        }
    };
    private boolean insertActive = true;

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
                if(insertActive){
                    itemInsert.activate();
                }
                else{
                    getMenuItem(currentItem).activate();
                }
            }
        });

        getAudioPlayer().stopMusic();
        getAudioPlayer().playMusic(Music.GAMEOVER_TRACK.getPath());

        label.setTextFill(Color.web("#FFFF00"));
        label.setFont(FontMaker.getSizedFont(30));
        userName.setMaxHeight(30);
        userName.setFont(FontMaker.getSizedFont(15));
        userName.setPrefWidth(100);
        userName.setId("userName");
        itemInsert.setFont(30);
        userBox = new HBox(label, userName, itemInsert);
        userBox.setSpacing(20);
        userBox.setAlignment(Pos.TOP_CENTER);

        menuBox = new VBox(SPACE_BETWEEN_ITEM, itemMainMenu, itemExit);

        itemActions();

        menuBox.setAlignment(Pos.TOP_CENTER);

        itemInsert.setActive(true);
        
        //calculates the position of the boxes
        if(Resolution.isFullScreen()){
            menuBox.setLayoutX(BOX_X_COORDINATE*Resolution.getWidth()/Resolution.SMALL_WIDTH+DELTA);
            menuBox.setLayoutY(BOX_Y_COORDINATE*Resolution.getHeight()/Resolution.SMALL_HEIGHT);

            userBox.setTranslateX(USERBOX_X_COORDINATE*Resolution.getWidth()/Resolution.SMALL_WIDTH+DELTA);
            userBox.setTranslateY(USERBOX_Y_COORDINATE*Resolution.getHeight()/Resolution.SMALL_HEIGHT);
        }
        else{
            menuBox.setLayoutX(BOX_X_COORDINATE);
            menuBox.setLayoutY(BOX_Y_COORDINATE);

            userBox.setTranslateX(USERBOX_X_COORDINATE);
            userBox.setTranslateY(USERBOX_Y_COORDINATE);
        }

        AnchorPane root = getRoot();
        root.getChildren().clear();
        root.getChildren().add(userBox);
        root.getChildren().add(menuBox);

        root.setId("gameOverView");

        getScene().setRoot(root);
    }

    private MenuItem getMenuItem(int index){
        return (MenuItem)menuBox.getChildren().get(index);
    }

    @Override
    protected void itemActions(){
        itemMainMenu.setOnActivate(() -> {
            getAudioPlayer().stopMusic();
            getController().resetGame();
        });

        itemExit.setOnActivate(() -> {
            getController().stopGame();
            System.exit(0);

        });

        itemInsert.setOnActivate(() -> {
            if(!userName.getText().isEmpty()){
                //user name and stats passed to raking ...
                String result;
                // remove spaces
                result = userName.getText().replace(" ", "");
                getController().insertNewScore(result);
                userName.setDisable(true);
                userBox.setDisable(true);
                itemInsert.setActive(false);

                getMenuItem(currentItem).setActive(true);
                insertActive = false;
            }
        });

    }
}
