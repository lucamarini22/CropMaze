package it.unibo.oop.bbgmm.Boundary;
import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Utilities.FontMaker;
import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RankingView extends AbstractBasicView {
    private static final int SPACE_BETWEEN_ITEM = 40;
    private static final int DELTA = 140;
    private static final int BOX_X_COORDINATE = 280;
    private static final int BOX_Y_COORDINATE = 70;
    //private static final ImageView crown = new ImageView(new Image("images/crown.png"));
    private VBox menuBox;
    //private VBox boxImage;
    private final MenuItem itemBack = new MenuItem("BACK");

    public RankingView(final Stage primaryStage, final PrincipalController controller,
                       final AnchorPane pane, final Scene scene){
        super(primaryStage, controller, pane, scene);

        getScene().setOnKeyPressed(event->{
            if(event.getCode() == KeyCode.ENTER ){
                playPressSound();
                itemBack.activate();
            }
        });

        menuBox = new VBox(SPACE_BETWEEN_ITEM);

        if(!controller.getRankingList().isEmpty()) {
            //boxImage = new VBox(crown);
            List<Text> rankList = controller.getRankingList().stream()
                    .map(l -> new Text(l.getFst() + " " + l.getSnd()))
                    .collect(Collectors.toList());
            rankList.forEach(this::fontUtil);
            rankList.get(0).setFont(FontMaker.getFontWinner());
            rankList.forEach(t -> menuBox.getChildren().add(t));
            //boxImage.setAlignment(Pos.TOP_CENTER);
            //pane.getChildren().add(boxImage);
            if(Resolution.isFullScreen()){
                menuBox.setLayoutX(BOX_X_COORDINATE*Resolution.getWidth()/Resolution.SMALL_WIDTH+DELTA);
                menuBox.setLayoutY(BOX_Y_COORDINATE*Resolution.getHeight()/Resolution.SMALL_HEIGHT);
            }
            else{
                menuBox.setLayoutX(BOX_X_COORDINATE);
                menuBox.setLayoutY(BOX_Y_COORDINATE);
            }
        }else
        {
            menuBox.getChildren().add(fontUtil(new Text("NO RANKING")));
        }
        buttonActions();
        menuBox.getChildren().addAll(itemBack);
        menuBox.setAlignment(Pos.TOP_CENTER);
        itemBack.setActive(true);

        AnchorPane root = getRoot();
        root.getChildren().clear();
        root.getChildren().add(menuBox);


        root.setId("rankingView");
    }

    @Override
    protected void buttonActions() {
        itemBack.setOnActivate(() -> {
            getController().showMainMenu(getViewFactory());
        });
    }

    /**
     * Util to set the font for the ranking list
     * @param text
     *      The text that has to be modified
     * @return
     *      The text modified
     */
    private Text fontUtil(final Text text){
        text.setFont(FontMaker.getFont());
        text.setEffect(new GaussianBlur(2));
        text.setFill(Color.BLUE);
        return text;
    }
}
