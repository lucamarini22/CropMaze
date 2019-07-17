package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Utilities.Pair;
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

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class RankingView extends Scene {
    private static final int SPACE_BETWEEN_ITEM = 40;
    private static final int BOX_X_COORDINATE = 315;
    private static final int BOX_Y_COORDINATE = 50;
    private static Stage primaryStage;
    private final AnchorPane pane;
    private static final Font FONT = Font.font("MS Gothic", FontWeight.BOLD, 70);
    private static final List<Text> rankList=new LinkedList<>();
    private VBox menuBox;
    private final MenuItem itemBack = new MenuItem("BACK");

    public RankingView(){
        super(new AnchorPane(),Resolution.getSmallWidth(),Resolution.getSmallHeight());
        this.setOnKeyPressed(event->{
            if(event.getCode() == KeyCode.ENTER ){
                itemBack.activate();
            }
        });
        pane = new AnchorPane();
        this.fillList();
        rankList.forEach(l -> l.setFont(FONT));
        rankList.forEach(l -> l.setEffect(new GaussianBlur(2)));
        rankList.forEach(l->l.setFill(Color.BLUE));




        menuBox = new VBox(SPACE_BETWEEN_ITEM);

        rankList.stream().forEach(t -> menuBox.getChildren().add(t));
        menuBox.getChildren().addAll(itemBack);
        buttonActions();




        menuBox.setAlignment(Pos.TOP_CENTER);

        menuBox.setTranslateX(BOX_X_COORDINATE);
        menuBox.setTranslateY(BOX_Y_COORDINATE);

        itemBack.setActive(true);
        pane.getChildren().add(menuBox);

        pane.setId("rankingView");
        this.getStylesheets().add("Style.css");

        this.setRoot(pane);
    }



    private void buttonActions() {
        itemBack.setOnActivate(() -> this.primaryStage.setScene(MainMenu.getMainMenu(this.primaryStage)));
    }

    //Metodo fittizio per riepire la lista
    private void fillList(){
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
    public static RankingView getRankingView(final Stage stage) {
        primaryStage = stage;
        primaryStage.setHeight(Resolution.getSmallHeight());
        primaryStage.setWidth(Resolution.getSmallWidth());
        return new RankingView();
    }






}