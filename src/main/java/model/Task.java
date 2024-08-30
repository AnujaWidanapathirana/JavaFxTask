package model;

import javafx.scene.control.DatePicker;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class Task {
    private String id;
    private String description;
    private String taskName;
    private LocalDate date;

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    private Boolean state;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTime(LocalDate time) {
        this.date = time;
    }

    public LocalDate getTime() {
        return date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getId() {
        return id;
    }

    public Task(String taskName, String description, LocalDate date,String id,Boolean state) {
        this.id = id;
        this.taskName = taskName;
        this.date = date;
        this.description = description;
        this.state=state;
    }

    public LocalDate getDate() {
        return date;
    }
}
