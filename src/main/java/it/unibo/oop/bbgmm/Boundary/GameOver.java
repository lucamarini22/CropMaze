package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameOver extends AbstractBasicView {
    private static final int SPACE_BETWEEN_ITEM = 25;
    private static final int DELTA = 80;
    private static final int BOX_X_COORDINATE = 365;
    private static final int BOX_Y_COORDINATE = 350;
    private static final int GAMEOVER_Y_COORDINATE=200;
    private final AnchorPane pane;
    private static final ImageView gameOVer = new ImageView(new Image("images/gameOver.png"));
    private int currentItem = 0;
    private VBox menuBox;
    private VBox boxImage;
    private final MenuItem itemMainMenu = new MenuItem("MAIN MENU");
    private final MenuItem itemExit = new MenuItem("EXIT");

    public GameOver(final Stage primaryStage, final PrincipalController controller, final AudioPlayer audioPlayer){
        super(primaryStage, controller, audioPlayer);

        this.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.UP) {
                if(currentItem > 0) {
                    playSwitchSound();
                    getMenuItem(currentItem).setActive(false);
                    getMenuItem(--currentItem).setActive(true);
                }
            }

            if(event.getCode() == KeyCode.DOWN) {
                if(currentItem < menuBox.getChildren().size() - 1 ){
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

        boxImage = new VBox(gameOVer);
        boxImage.setAlignment(Pos.TOP_CENTER);
        boxImage.setTranslateX(BOX_X_COORDINATE);
        boxImage.setTranslateY(GAMEOVER_Y_COORDINATE);

        pane = new AnchorPane();
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

        pane.getChildren().add(boxImage);
        pane.getChildren().add(menuBox);

        pane.setId("gameOverView");
        this.getStylesheets().add("Style.css");

        this.setRoot(pane);
    }

    private MenuItem getMenuItem(int index){
        return (MenuItem)menuBox.getChildren().get(index);
    }

    @Override
    protected void buttonActions(){
        itemMainMenu.setOnActivate(() -> {
            getPrimaryStage().setScene(getViewFactory().createMainMenu());
            checkResolution();
        });

        itemExit.setOnActivate(() -> {
            getController().stopGame();
            System.exit(0);
        });
    }
}
