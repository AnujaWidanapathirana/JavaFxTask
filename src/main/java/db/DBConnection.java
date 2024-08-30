package db;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Task;

import java.util.ArrayList;

public class DBConnection {
    private static DBConnection instance;
    private ObservableList<Task> taskList;

    private DBConnection(){
        taskList = FXCollections.observableArrayList();
    }


    public ObservableList<Task> gettaskArrayList(){
        return taskList;
    }

    public static DBConnection getInstance(){
        if(instance==null){
            instance =  new DBConnection();
        }
        return instance;
    }
}
