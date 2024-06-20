package views;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainMenu extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        primaryStage.setTitle("TreeData");

        GridPane panelButtons = new GridPane();
        panelButtons.setPadding(new Insets(10));
        panelButtons.setVgap(10);
        panelButtons.setHgap(10);
        panelButtons.setAlignment(Pos.CENTER);
        panelButtons.setStyle("-fx-background-color: pink;");

        addButtons(panelButtons);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(panelButtons);

        Scene scene = new Scene(mainLayout, 300, 300); // Increased height to fit all buttons
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addButtons(GridPane panelButtons) {
        ButtonListener btnListener = new ButtonListener();

        Button binaryTreeButton = new Button("BinaryTree");
        configureButton(binaryTreeButton, btnListener);
        panelButtons.add(binaryTreeButton, 0, 0);

        Button genericTreeButton = new Button("GenericTree");
        configureButton(genericTreeButton, btnListener);
        panelButtons.add(genericTreeButton, 0, 1);

        Button balancedTreeButton = new Button("BalancedTree");
        configureButton(balancedTreeButton, btnListener);
        panelButtons.add(balancedTreeButton, 0, 2);

        Button balancedBinaryTreeButton = new Button("BalancedBinaryTree");
        configureButton(balancedBinaryTreeButton, btnListener);
        panelButtons.add(balancedBinaryTreeButton, 0, 3);

        Button quitButton = new Button("Quit");
        configureButton(quitButton, btnListener);
        panelButtons.add(quitButton, 0, 4);

        Button helpButton = new Button("Help");
        configureButton(helpButton, btnListener);
        panelButtons.add(helpButton, 0, 5);
    }

    private void configureButton(Button button, ButtonListener listener) {
        button.setPrefWidth(150);
        button.setStyle("-fx-background-color: blue; -fx-text-fill: white; -fx-font-size: 16;");
        button.setOnAction(listener);
    }

    private class ButtonListener implements javafx.event.EventHandler<javafx.event.ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Button source = (Button) event.getSource();
            String buttonText = source.getText();

            switch (buttonText) {
                case "BinaryTree":
                    switchToTreeVisualizer("BinaryTree");
                    break;
                case "GenericTree":
                    switchToTreeVisualizer("GenericTree");
                    break;
                case "BalancedTree":
                    switchToTreeVisualizer("BalancedTree");
                    break;
                case "BalancedBinaryTree":
                    switchToTreeVisualizer("BalancedBinaryTree");
                    break;
                case "Quit":
                    showQuitDialog();
                    break;
                case "Help":
                    showHelpDialog();
                    break;
            }
        }
    }

    private void switchToTreeVisualizer(String treeType) {
        TreeVisualizer treeVisualizer = new TreeVisualizer(treeType, primaryStage, primaryStage.getScene());
        Scene scene = new Scene(treeVisualizer.getView(), 1000, 600);
        primaryStage.setScene(scene);
    }

    private void showQuitDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to quit?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Quit Confirmation");
        alert.setHeaderText(null);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                primaryStage.close();
            }
        });
    }

    private void showHelpDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("Instructions");
        
        TextArea textArea = new TextArea("This application allows you to visualize different types of trees.\n\n"
                + "BinaryTree: A simple binary tree.\n"
                + "GenericTree: A tree where nodes can have any number of children.\n"
                + "BalancedTree: A tree that self-balances.\n"
                + "BalancedBinaryTree: A binary tree that self-balances.\n\n"
                + "Use the buttons on the right to interact with the tree: insert nodes, delete nodes, search for nodes, etc.");
        textArea.setWrapText(true);
        textArea.setEditable(false);
        
        alert.getDialogPane().setContent(textArea);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    
}
