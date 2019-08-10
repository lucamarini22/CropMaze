package it.unibo.oop.bbgmm.boundary;

import it.unibo.oop.bbgmm.control.PrincipalController;
import it.unibo.oop.bbgmm.utilities.FontMakerUtil;
import it.unibo.oop.bbgmm.utilities.ResolutionUtil;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameOver extends AbstractBasicView {
    private static final int SPACE_BETWEEN_ITEM = 20;
    private static final int USERBOX_X_COORDINATE = 320;
    private static final int USERBOX_Y_COORDINATE = 450;
    private static final int BOX_X_COORDINATE = 350;
    private static final int BOX_Y_COORDINATE = 520;
    private static final int LABEL_PROPORTION = 34;
    private static final int TEXT_PROPORTION = 68;
    private static final int WIDTH_PROPORTION = 10;
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
        double width = ResolutionUtil.getWidth();
        //label.setFont(FontMakerUtil.getSizedFont(width/LABEL_PROPORTION));
        userName.setFont(FontMakerUtil.getSizedFont(width/TEXT_PROPORTION));
        userName.setPrefWidth(width/WIDTH_PROPORTION);
        userName.setId("userName");
        itemInsert.setFont(width/LABEL_PROPORTION);
        userBox = new HBox(label, userName, itemInsert);
        userBox.setSpacing(20);
        userBox.setAlignment(Pos.TOP_CENTER);

        menuBox = new VBox(SPACE_BETWEEN_ITEM, itemMainMenu, itemExit);

        itemActions();

        menuBox.setAlignment(Pos.TOP_CENTER);

        itemInsert.setActive(true);
        
        //calculates the position of the boxes
        if(ResolutionUtil.isFullScreen()){
            menuBox.setLayoutX(BOX_X_COORDINATE* ResolutionUtil.getWidth()/ ResolutionUtil.SMALL_WIDTH);
            menuBox.setLayoutY(BOX_Y_COORDINATE* ResolutionUtil.getHeight()/ ResolutionUtil.SMALL_HEIGHT);

            userBox.setTranslateX(USERBOX_X_COORDINATE* ResolutionUtil.getWidth()/ ResolutionUtil.SMALL_WIDTH);
            userBox.setTranslateY(USERBOX_Y_COORDINATE* ResolutionUtil.getHeight()/ ResolutionUtil.SMALL_HEIGHT);
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
