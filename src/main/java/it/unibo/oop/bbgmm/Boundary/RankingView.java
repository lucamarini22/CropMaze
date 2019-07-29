package it.unibo.oop.bbgmm.Boundary;
import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Utilities.FontMaker;
import javafx.geometry.Pos;
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
    private static final int BOX_X_COORDINATE = 315;
    private static final int BOX_Y_COORDINATE = 70;
    private static final int CROWN_Y_COORDINATE = -190;
    private final AnchorPane pane;
    private static final ImageView crown = new ImageView(new Image("images/crown.png"));
    private static List<Text> rankList=new LinkedList<>();
    private VBox menuBox;
    private VBox boximage;
    private final MenuItem itemBack = new MenuItem("BACK");

    public RankingView(final Stage primaryStage, final PrincipalController controller, final AudioPlayer audioPlayer){
        super(primaryStage, controller, audioPlayer);

        this.setOnKeyPressed(event->{
            if(event.getCode() == KeyCode.ENTER ){
                playPressSound();
                itemBack.activate();
            }
        });

        pane = new AnchorPane();


        rankList = controller.getRankingList().stream()
                .map(l -> new Text(l.getFst()+" "+l.getSnd()))
                .collect(Collectors.toList());

        rankList.forEach(l -> l.setFont(FontMaker.getFont()));
        rankList.forEach(l -> l.setEffect(new GaussianBlur(2)));
        rankList.forEach(l->l.setFill(Color.BLUE));
        rankList.get(0).setFont(FontMaker.getFontWinner());


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

    @Override
    protected void buttonActions() {
        itemBack.setOnActivate(() -> {
            getPrimaryStage().setScene(getViewFactory().createMainMenu());
            checkResolution();
        });
    }
}
