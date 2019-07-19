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

public class RankingView extends Scene {
    private static final int SPACE_BETWEEN_ITEM = 40;
    private static final int BOX_X_COORDINATE = 315;
    private static final int BOX_Y_COORDINATE = 70;
    private static final int CROWN_Y_COORDINATE = -190;
    private final PrincipalController controller;
    private static Stage primaryStage;
    private final AnchorPane pane;
    private static final ImageView crown = new ImageView(new Image("images/crown.png"));
    private static final Font FONT_WINNER = Font.font("MS Gothic", FontWeight.BOLD, 100);
    private static final List<Text> rankList=new LinkedList<>();
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

        this.fillList();

        rankList.forEach(l -> l.setFont(FontMaker.getFont()));
        rankList.forEach(l -> l.setEffect(new GaussianBlur(2)));
        rankList.forEach(l->l.setFill(Color.BLUE));
        rankList.get(0).setFont(FONT_WINNER);


        boximage = new VBox(crown);
        boximage.setAlignment(Pos.TOP_CENTER);
        boximage.setTranslateX(BOX_X_COORDINATE);
        boximage.setTranslateY(CROWN_Y_COORDINATE);
        menuBox = new VBox(SPACE_BETWEEN_ITEM);

        rankList.forEach(t -> menuBox.getChildren().add(t));
        menuBox.getChildren().addAll(itemBack);
        buttonActions();




        menuBox.setAlignment(Pos.TOP_CENTER);

        menuBox.setTranslateX(BOX_X_COORDINATE);
        menuBox.setTranslateY(BOX_Y_COORDINATE);

        itemBack.setActive(true);
        pane.getChildren().add(boximage);
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

    //Metodo fittizio per riepire la lista
    private void fillList(){
        rankList.clear();
        rankList.add(new Text("Simo 10"));
        rankList.add(new Text("Pier 9"));
        rankList.add(new Text("Lory 8"));
        rankList.add(new Text("Giannolo 6"));
        rankList.add(new Text("Maren 1"));
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
