package it.unibo.oop.bbgmm.Boundary;
import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Utilities.FontMaker;
import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RankingView extends Scene {
    private static final int SPACE_BETWEEN_ITEM = 40;
    private static final int DELTA = 80;
    private static final int BOX_X_COORDINATE = 280;
    private static final int BOX_Y_COORDINATE = 70;
    private static final int CROWN_Y_COORDINATE = -180;
    private static final int CROWN_WIDTH = 500;
    private final PrincipalController controller;
    private static Stage primaryStage;
    private final AnchorPane pane;
    private static final ImageView crown = new ImageView(new Image("images/crown.png"));
    private static final Font FONT_WINNER = Font.font("MS Gothic", FontWeight.BOLD, 100);
    private static List<Text> rankList=new LinkedList<>();
    private VBox menuBox;
    private VBox boximage;
    private final MenuItem itemBack = new MenuItem("BACK");

    public RankingView(final PrincipalController controller){
        super(new AnchorPane(),Resolution.getWidth(),Resolution.getHeight());
        this.controller = controller;
        this.setOnKeyPressed(event->{
            if(event.getCode() == KeyCode.ENTER ){
                itemBack.activate();
            }
        });
        pane = new AnchorPane();


        menuBox = new VBox(SPACE_BETWEEN_ITEM);
        boximage = new VBox(crown);
        if(!controller.getRankingList().isEmpty()) {
            rankList = controller.getRankingList().stream()
                    .map(l -> new Text(l.getFst() + " " + l.getSnd()))
                    .collect(Collectors.toList());

            rankList.forEach(l -> l.setFont(FontMaker.getFont()));
            rankList.forEach(l -> l.setEffect(new GaussianBlur(2)));
            rankList.forEach(l -> l.setFill(Color.BLUE));
            rankList.get(0).setFont(FontMaker.getFontWinner());
            rankList.forEach(t -> menuBox.getChildren().add(t));

            pane.getChildren().add(boximage);
        }

        buttonActions();

        boximage.setAlignment(Pos.TOP_CENTER);
        menuBox.setAlignment(Pos.TOP_CENTER);

        menuBox.getChildren().addAll(itemBack);

        //calculates the position of the box
        if(Resolution.isFullScreen()){
            menuBox.setLayoutX(BOX_X_COORDINATE*Resolution.getWidth()/Resolution.SMALL_WIDTH+DELTA);
            menuBox.setLayoutY(BOX_Y_COORDINATE*Resolution.getHeight()/Resolution.SMALL_HEIGzHT);
            boximage.setLayoutX(BOX_X_COORDINATE*Resolution.getWidth()/Resolution.SMALL_WIDTH);
            boximage.setLayoutY(CROWN_Y_COORDINATE*Resolution.getHeight()/Resolution.SMALL_HEIGHT);
        }
        else{
            menuBox.setLayoutX(BOX_X_COORDINATE);
            menuBox.setLayoutY(BOX_Y_COORDINATE);
            crown.setFitWidth(CROWN_WIDTH);
            boximage.setTranslateX(BOX_X_COORDINATE);
            boximage.setTranslateY(CROWN_Y_COORDINATE);
        }




        itemBack.setActive(true);
        pane.getChildren().add(menuBox);

        pane.setId("rankingView");
        this.getStylesheets().add("Style.css");

        this.setRoot(pane);
    }



    private void buttonActions() {
        itemBack.setOnActivate(() -> {
            this.primaryStage.setScene(MainMenu.getMainMenu(this.primaryStage, controller));
            checkResolution();
        });
    }

    /**
     * Getter for the Scene.
     * @param stage
     * @return SettingsMenu
     */
    public static RankingView getRankingView(final Stage stage, final PrincipalController controller) {
        primaryStage = stage;
        primaryStage.centerOnScreen();
        return new RankingView(controller);
    }


    /**
     * Method used to set or not the stage to FuLLScreen
     */
    private void checkResolution(){
        if(Resolution.isFullScreen()){
            this.primaryStage.setFullScreen(true);
        }
        else{
            this.primaryStage.setFullScreen(false);
        }
    }




}
