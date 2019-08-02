package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InsertScoreView extends AbstractBasicView {

    private static final int SPACE_BETWEEN_ITEM = 25;
    private static final int DELTA = 80;
    private static final int INSERTID_X_COORDINATE = 200;
    private static final int INSERTID_Y_COORDINATE = 290;
    private static final int BOX_X_COORDINATE = 300;
    private static final int BOX_Y_COORDINATE = 500;
    private int currentItem = 0;
    private VBox menuBox;
    private VBox insertMenu;
    private final TextField insertID = new TextField();
    private final MenuItem itemScore = new MenuItem("INSERT SCORE");
    private final MenuItem itemMainMenu = new MenuItem("MAIN MENU");
    private final MenuItem itemRanking = new MenuItem("RANKING");

    public InsertScoreView(final Stage primaryStage, final PrincipalController controller,
                           final AudioPlayer audioPlayer, final Group group, final Scene scene) {
        super(primaryStage, controller, audioPlayer, group, scene);

        getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                if (currentItem > 0) {
                    playSwitchSound();
                    getMenuItem(currentItem).setActive(false);
                    getMenuItem(--currentItem).setActive(true);
                }
            }

            if (event.getCode() == KeyCode.DOWN) {
                if (currentItem < menuBox.getChildren().size() - 1) {
                    playSwitchSound();
                    getMenuItem(currentItem).setActive(false);
                    getMenuItem(++currentItem).setActive(true);
                }
            }

            if (event.getCode() == KeyCode.ENTER) {
                playPressSound();
                getMenuItem(currentItem).activate();
            }
        });

        menuBox = new VBox(SPACE_BETWEEN_ITEM, itemScore, itemMainMenu, itemRanking);
        insertMenu = new VBox(SPACE_BETWEEN_ITEM, insertID, menuBox);


        buttonActions();
        insertMenu.setAlignment(Pos.TOP_CENTER);

        if(Resolution.isFullScreen()){
            insertID.setLayoutX(INSERTID_X_COORDINATE*Resolution.getWidth()/Resolution.SMALL_WIDTH+DELTA);
            insertID.setLayoutY(INSERTID_Y_COORDINATE*Resolution.getHeight()/Resolution.SMALL_HEIGHT);
            menuBox.setLayoutX(BOX_X_COORDINATE*Resolution.getWidth()/Resolution.SMALL_WIDTH+DELTA);
            menuBox.setLayoutY(BOX_Y_COORDINATE*Resolution.getHeight()/Resolution.SMALL_HEIGHT);
        }
        else{
            insertID.setLayoutX(INSERTID_X_COORDINATE);
            insertID.setLayoutY(INSERTID_Y_COORDINATE);
            menuBox.setLayoutX(BOX_X_COORDINATE);
            menuBox.setLayoutY(BOX_Y_COORDINATE);
        }

        getMenuItem(0).setActive(true);
        
        Group root = getRoot();
        root.getChildren().clear();
        root.getChildren().add(menuBox);
        root.getChildren().add(insertMenu);

        root.setId("insertScore");
    }

    private MenuItem getMenuItem(int index){
        VBox menu = (VBox) insertMenu.getChildren().get(1);
        return (MenuItem) menu.getChildren().get(index);
    }

    @Override
    protected void buttonActions() {

        itemMainMenu.setOnActivate(() -> {
            /*getPrimaryStage().setScene(getViewFactory().createMainMenu());
            checkResolution();*/
        });

        itemRanking.setOnActivate(() -> {
            /*getPrimaryStage().setScene(getViewFactory().createRankingView());
            checkResolution();*/
        });

        itemScore.setOnActivate(() -> {
            /*String id = insertID.getText();
            if(!id.isEmpty()) {
                String result;
                result = id.replace(" ", "");
                getController().InsertNewScore(result, 10);
                getMenuItem(currentItem).activate();
            }*/
        });
    }
}
