package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.mapeditor.core.Tile;
import org.mapeditor.core.TileLayer;

/**
 * Implementation of the view of {@link it.unibo.oop.bbgmm.Entity.GameField}.
 */
public final class GameFieldViewImpl implements GameFieldView {
    private final Group fieldView = new Group();
    private final Group rootView = new Group(fieldView);
    private final AudioPlayer audioplayer;

    /**
     * Constructor of {@link GameFieldViewImpl}.
     * @param audioPlayer
     *      {@link AudioPlayer}
     */
    public GameFieldViewImpl(final AudioPlayer audioPlayer) {
        this.audioplayer = audioPlayer;
    }

    @Override
    public EntityViewFactory getEntityViewFactory() {
        return new EntityViewFactoryImpl(fieldView, audioplayer);
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
}
