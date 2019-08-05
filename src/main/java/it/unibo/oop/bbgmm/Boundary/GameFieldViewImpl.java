package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PlayerInputListener;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.mapeditor.core.Tile;
import org.mapeditor.core.TileLayer;

/**
 * Implementation of the view of {@link it.unibo.oop.bbgmm.Entity.GameField}.
 */
public final class GameFieldViewImpl implements GameFieldView {
    private static final int TOP_LEFT_POINT_BACKGROUND = -800;
    private static final String BACKGROUND_PATH = "/images/background.png";
    private final Group fieldView = new Group();
    private final Group rootView = new Group(fieldView);
    private final AudioPlayer audioplayer;
    private final PlayerInputHandler playerInputHandler;
    private final StatusBarScreen statusBar = new StatusBarImpl();
    private ImageView background;

    /**
     * Constructor of {@link GameFieldViewImpl}.
     * @param audioPlayer
     *      {@link AudioPlayer}
     * @param playerInputHandler
     *      {@link PlayerInputHandler} instance
     */
    public GameFieldViewImpl(final AudioPlayer audioPlayer, final PlayerInputHandler playerInputHandler) {
        this.audioplayer = audioPlayer;
        this.playerInputHandler = playerInputHandler;
        this.setBackground();
        fieldView.getChildren().add(this.background);
        rootView.getChildren().add(statusBar.getStatusBox());
    }

    @Override
    public EntityViewFactory getEntityViewFactory() {
        return new EntityViewFactoryImpl(this.fieldView, this.audioplayer, this.statusBar);
    }

    @Override
    public void showField(final TileLayer layer, final Point2D topLeft, final Dimension2D tileSize) {
        for (int x = 0; x < layer.getMap().getWidth(); x++) {
            for (int y = 0; y < layer.getMap().getHeight(); y++) {
                final Tile tile = layer.getTileAt(x, y);
                if (tile != null && tile.getImage() != null) {
                    final Image tileImage = SwingFXUtils.toFXImage(tile.getImage(), null);
                    final ImageView tileView = new ImageView(tileImage);
                    tileView.setFitWidth(ViewUtils.metersToPixels(tileSize.getWidth()));
                    tileView.setFitHeight(ViewUtils.metersToPixels(tileSize.getHeight()));
                    tileView.setTranslateX(x * ViewUtils.metersToPixels(tileSize.getWidth()) + topLeft.getX());
                    tileView.setTranslateY(y * ViewUtils.metersToPixels(tileSize.getHeight()) + topLeft.getY());
                    fieldView.getChildren().add(tileView);
                }
            }
        }
    }

    @Override
    public Group getGroup() {
        return this.rootView;
    }

    @Override
    public void setPlayerInputListener(final PlayerInputListener playerInputListener) {
        this.playerInputHandler.setListener(playerInputListener);
    }

    /**
     * Sets an image of background.
     * @param path
     *      Path of the image to set
     */
    private void setBackground() {
        this.background = new ImageView(new Image(BACKGROUND_PATH));
        this.background.setX(TOP_LEFT_POINT_BACKGROUND);
        this.background.setY(TOP_LEFT_POINT_BACKGROUND);
    }
}
