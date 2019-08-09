package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public final class StatusBarImpl implements StatusBarScreen {

    private static final int WIDTHBAR = 180;
    private static final int HIGHTBAR = 40;
    private static final int PADDING = 20;
    private final HBox statusBox = new HBox();
    private static final HBox coinsBox = new HBox();
    private static final HBox root = new HBox();
    private final ProgressBar pb = new ProgressBar(1);
    private double maxLifePoints = 0;
    private final List<Image> imageNum;
    private List<ImageView> coinsList = new LinkedList<>();


    public StatusBarImpl(){
        pb.setStyle("-fx-accent: green");
        pb.setPrefWidth((WIDTHBAR * Resolution.getWidth()) / Resolution.SMALL_WIDTH);
        pb.setPrefHeight((HIGHTBAR * Resolution.getHeight())/Resolution.SMALL_HEIGHT);
        coinsBox.getChildren().add(new ImageView(new Image("images/coinSilver.png")));
        coinsBox.getChildren().add(new ImageView(new Image("images/X.png")));
        coinsBox.setPrefWidth((WIDTHBAR * Resolution.getWidth()) / Resolution.SMALL_WIDTH);
        coinsBox.setPrefHeight((HIGHTBAR * Resolution.getHeight())/Resolution.SMALL_HEIGHT);
        statusBox.setAlignment(Pos.CENTER_LEFT);
        coinsBox.setAlignment(Pos.CENTER_LEFT);
        root.setPadding(new Insets(PADDING));
        imageNum = IntStream.range(0,10).
                mapToObj(value -> "images/number_"+value+".png").
                map(Image::new).
                collect(Collectors.toList());
        statusBox.getChildren().add(pb);
        statusBox.getChildren().add(coinsBox);
        root.getChildren().add(statusBox);
    }

    @Override
    public Node getStatusBox() {
        return this.statusBox;
    }


    @Override
    public void setCurrentLifePoints(int currentLifePoints) {
        if(maxLifePoints != 0) {
            double newPercentage = currentLifePoints/maxLifePoints;
            pb.setProgress(newPercentage);
        }else {
            maxLifePoints = currentLifePoints;
        }
    }

    @Override
    public void setCoins(int coins) {
        coinsList.forEach(view -> coinsBox.getChildren().remove(view));
        coinsList.clear();
        for (final String ch : Integer.toString(coins).split("")) {
            coinsList.add(new ImageView(imageNum.get(Integer.parseInt(ch))));
        }
        coinsList.forEach(view -> coinsBox.getChildren().add(view));
    }



}
