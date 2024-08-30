package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class DisplayToDotaskForm{

    @FXML
    private JFXButton popaddtask;

    @FXML
    private VBox vBloxLeft;

    @FXML
    private VBox vBloxRight;

    @FXML
    private void initialize() {
        // Initialization code if needed
        if (vBloxLeft == null) {
            System.out.println("vBloxLeft is not initialized properly!");
        }
    }
    public void addTaskCheckBox(String taskName) {
        if (vBloxLeft == null) {
            throw new IllegalStateException("vBloxLeft is null. FXML not properly loaded.");
        }
        System.out.println("Adding task:"+taskName);
        CheckBox newCheckbox = new CheckBox(taskName!=null && !taskName.isEmpty() ? taskName : "Unnamed Task");
        newCheckbox.setOnAction(event -> toggleCheckBox(newCheckbox));
        vBloxLeft.getChildren().add(newCheckbox);
        System.out.println("Task added to vBloxLeft");
    }
    private void toggleCheckBox(CheckBox checkBox) {
        if (checkBox.isSelected()) {
            if (vBloxLeft.getChildren().contains(checkBox)) {
                vBloxLeft.getChildren().remove(checkBox);
                Label taskLabel=new Label(checkBox.getText());
                taskLabel.setPrefWidth(vBloxRight.getPrefWidth());
                taskLabel.setMaxWidth(Double.MAX_VALUE);
                taskLabel.setStyle("-fx-padding: 5;");
                vBloxRight.getChildren().add(taskLabel);
            }
        } else {
            if (vBloxRight.getChildren().contains(checkBox)) {
                vBloxRight.getChildren().remove(checkBox);
                CheckBox taskCheckBox=new CheckBox(checkBox.getText());
                taskCheckBox.setPrefWidth(vBloxLeft.getPrefWidth());
                taskCheckBox.setMaxWidth(Double.MAX_VALUE);
                taskCheckBox.setStyle("-fx-padding:5;");
                taskCheckBox.setOnAction(event->toggleCheckBox(taskCheckBox));
                vBloxLeft.getChildren().add(taskCheckBox);
            }
        }
        if (checkBox.isSelected()) {
            checkBox.setStyle("-fx-padding: 5; -fx-background-color: lightgray;");
        } else {
            checkBox.setStyle("-fx-padding: 5;");
        }
    }
    public void poptaskWindow(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/add_task_form.fxml"));
            Parent root = fxmlLoader.load();
            Stage newStage = new Stage();
            Scene newScene = new Scene(root);
            newStage.setScene(newScene);
            newStage.setTitle("Add Task");
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
