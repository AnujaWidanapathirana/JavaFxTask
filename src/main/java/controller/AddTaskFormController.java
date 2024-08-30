package controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import db.DataBaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import model.Task;

import java.sql.*;
import java.time.LocalDate;

public class AddTaskFormController {
    private DisplayToDotaskForm displayToDotaskForm;
    private String id;

    @FXML
    private DatePicker taskdatepicker;

    @FXML
    private JFXTextField taskDescription;

    @FXML
    private JFXTextField taskName;

    @FXML
    private JFXCheckBox taskReminder;

    public void setDisplayToDotaskForm(DisplayToDotaskForm displayToDotaskForm) {
        this.displayToDotaskForm = displayToDotaskForm;
    }
    @FXML
    private void onSubmit() {
        if (taskName.getText().isEmpty() || taskdatepicker.getValue() == null) {
            showAlert(Alert.AlertType.WARNING, "Error: Please fill in all required fields.");
        } else {
            generateId();
            Task task = new Task(taskName.getText(), taskDescription.getText(), taskdatepicker.getValue(), id, taskReminder.isSelected());
            try (Connection connection = DataBaseConnection.getInstance().getConnection(); PreparedStatement psTm = connection.prepareStatement("INSERT INTO Task (TaskID, TaskName, DOB, Task_Descrption, State) VALUES (?, ?, ?, ?, ?)")) {
                psTm.setString(1, task.getId());
                psTm.setString(2, task.getTaskName());
                psTm.setDate(3, Date.valueOf(task.getDate()));
                psTm.setString(4, task.getDescription());
                psTm.setBoolean(5, task.getState());

                boolean isSuccess = psTm.executeUpdate() > 0;
                if (isSuccess) {
                    showAlert(Alert.AlertType.INFORMATION, "Task Added!");
                    if (displayToDotaskForm != null) {
                        displayToDotaskForm.addTaskCheckBox(task.getTaskName());
                    }
                    cleartxt();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Failed to add task.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Database error: " + e.getMessage());
            }
        }
    }
    private void generateId() {
        String query = "SELECT MAX(TaskID) FROM Task";
        try (Connection connection=DataBaseConnection.getInstance().getConnection();
             PreparedStatement ps=connection.prepareStatement(query);
             ResultSet rs=ps.executeQuery()) {
            if (rs.next()){
                String lastId=rs.getString(1);
                if (lastId!=null) {
                    int newId=Integer.parseInt(lastId.substring(1)) + 1;
                    id=String.format("C%03d", newId);
                }else{
                    id="C001";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void cleartxt() {
        taskName.clear();
        taskDescription.clear();
        taskdatepicker.setValue(LocalDate.now());
        taskReminder.setSelected(false);
    }
    @FXML
    private void btnClicked(javafx.event.ActionEvent actionEvent) {
        onSubmit();
    }
    private void showAlert(Alert.AlertType alertType, String content) {
        Alert alert = new Alert(alertType);
        alert.setContentText(content);
        alert.show();
    }
}
