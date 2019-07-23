package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PrincipalController;
import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.stage.Stage;

public class ViewSwitcher {

    public ViewSwitcher() {
    }

    public static void showMainMenu(Stage primaryStage, PrincipalController controller){
        primaryStage.setScene(new MainMenu(primaryStage, controller));
        checkResolution(primaryStage);
    }

    public static void showSettings(Stage primaryStage, PrincipalController controller){
        primaryStage.setScene(new SettingsMenu(primaryStage, controller));
        checkResolution(primaryStage);
    }

    public static void showRankingView(Stage primaryStage, PrincipalController controller){
        primaryStage.setScene(new RankingView(primaryStage, controller));
        checkResolution(primaryStage);
    }

    public static void showGameOverView(Stage primaryStage, PrincipalController controller){
        primaryStage.setScene(new GameOver(primaryStage, controller));
        checkResolution(primaryStage);
    }

    /**
     * Method used to set or not the stage to FullScreen
     */
    private static void checkResolution(Stage primaryStage){
        if(Resolution.isFullScreen()){
            primaryStage.setFullScreen(true);
        }
        else{
            primaryStage.setFullScreen(false);
            primaryStage.centerOnScreen();
        }
    }
}
