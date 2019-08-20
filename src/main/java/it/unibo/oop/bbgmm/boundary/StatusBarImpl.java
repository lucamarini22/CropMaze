package it.unibo.oop.bbgmm.boundary;

import it.unibo.oop.bbgmm.utilities.ResolutionUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Manage a status bar.
 */
public final class StatusBarImpl implements StatusBarScreen {

    private static final int WIDTHBAR = 180;
    private static final int HIGHTBAR = 40;
    private static final int PADDING = 20;
    private final HBox statusBox = new HBox();
    private final HBox coinsBox = new HBox();
    private final HBox root = new HBox();
    private final ProgressBar pb = new ProgressBar(1);
    private double maxLifePoints;
    private final List<Image> imageNum;
    private final List<ImageView> coinsList = new LinkedList<>();


    /**
     * Status bar constructor.
     */
    public StatusBarImpl() {
        pb.setStyle("-fx-accent: green");
        pb.setPrefWidth((WIDTHBAR * ResolutionUtil.getWidth()) / ResolutionUtil.getSmallWidth());
        pb.setPrefHeight((HIGHTBAR * ResolutionUtil.getHeight()) / ResolutionUtil.getSmallHeight());
        coinsBox.getChildren().add(new ImageView(new Image("images/upgrades/coinSilver.png")));
        coinsBox.getChildren().add(new ImageView(new Image("images/numbers/X.png")));
        coinsBox.setPrefWidth((WIDTHBAR * ResolutionUtil.getWidth()) / ResolutionUtil.getSmallWidth());
        coinsBox.setPrefHeight((HIGHTBAR * ResolutionUtil.getHeight()) / ResolutionUtil.getSmallHeight());
        statusBox.setAlignment(Pos.CENTER_LEFT);
        coinsBox.setAlignment(Pos.CENTER_LEFT);
        root.setPadding(new Insets(PADDING));
        imageNum = IntStream.range(0, 10).
                mapToObj(value -> "images/numbers/number_" + value + ".png").
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
    public void setCurrentLifePoints(final int currentLifePoints) {
        if (maxLifePoints != 0) {
            final double newPercentage = currentLifePoints / maxLifePoints;
            pb.setProgress(newPercentage);
        } else {
            maxLifePoints = currentLifePoints;
        }
    }

    @Override
    public void setCoins(final int coins) {
        coinsList.forEach(view -> coinsBox.getChildren().remove(view));
        coinsList.clear();
        for (final String ch : Integer.toString(coins).split("")) {
            coinsList.add(new ImageView(imageNum.get(Integer.parseInt(ch))));
        }
        coinsList.forEach(view -> coinsBox.getChildren().add(view));
    }



}
