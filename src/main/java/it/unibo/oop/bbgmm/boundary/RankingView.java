package it.unibo.oop.bbgmm.boundary;
import it.unibo.oop.bbgmm.control.PrincipalController;
import it.unibo.oop.bbgmm.utilities.FontMakerUtil;
import it.unibo.oop.bbgmm.utilities.ResolutionUtil;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Models the ranking view.
 */
public final class RankingView extends AbstractBasicView {
    private static final int SPACE_BETWEEN_ITEM = 40;
    private static final int BOX_X_COORDINATE = 280;
    private static final int BOX_Y_COORDINATE = 70;
    private final VBox menuBox;
    private final MenuItem itemBack = new MenuItem("BACK");

    /**
     * @param primaryStage
     *          The principal stage
     * @param controller
     *          The principal controller
     * @param pane
     *          The root for the scene
     * @param scene
     *          The scene displayed in the stage
     */
    public RankingView(final Stage primaryStage, final PrincipalController controller,
                       final AnchorPane pane, final Scene scene) {
        super(primaryStage, controller, pane, scene);

        getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                playPressSound();
                itemBack.activate();
            }
        });

        menuBox = new VBox(SPACE_BETWEEN_ITEM);

        if (!controller.getRankingList().isEmpty()) {
            final List<Text> rankList = controller.getRankingList().stream()
                    .map(l -> new Text(l.getFst() + " " + l.getSnd()))
                    .collect(Collectors.toList());
            rankList.forEach(this::fontUtil);
            rankList.get(0).setFont(FontMakerUtil.getFontWinner());
            rankList.forEach(t -> menuBox.getChildren().add(t));
        } else {
            menuBox.getChildren().add(fontUtil(new Text("NO RANKING")));
        }

        if (ResolutionUtil.isFullScreen()) {
            menuBox.setLayoutX(BOX_X_COORDINATE * ResolutionUtil.getWidth() / ResolutionUtil.getSmallWidth());
            menuBox.setLayoutY(BOX_Y_COORDINATE * ResolutionUtil.getHeight() / ResolutionUtil.getSmallHeight());
        } else {
            menuBox.setLayoutX(BOX_X_COORDINATE);
            menuBox.setLayoutY(BOX_Y_COORDINATE);
        }

        itemActions();
        menuBox.getChildren().addAll(itemBack);
        menuBox.setAlignment(Pos.TOP_CENTER);
        itemBack.setActive(true);

        final AnchorPane root = getRoot();
        root.getChildren().clear();
        root.getChildren().add(menuBox);


        root.setId("rankingView");
    }

    @Override
    protected void itemActions() {
        itemBack.setOnActivate(() -> {
            getController().showMainMenu(getViewFactory());
        });
    }

    /**
     * Util to set the font for the ranking list.
     * @param text
     *      The text that has to be modified.
     * @return
     *      The text modified.
     */
    private Text fontUtil(final Text text) {
        text.setFont(FontMakerUtil.getFont());
        text.setEffect(new GaussianBlur(2));
        text.setFill(Color.BLUE);
        return text;
    }
}
