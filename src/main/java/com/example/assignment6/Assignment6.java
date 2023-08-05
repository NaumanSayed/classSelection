package com.example.assignment6;

// Assignment #: Arizona State University Spring 2023 CSE205 #6
//         Name: Nauman Ahmed Nazir Ahmed Sayed
//    StudentID: 1226754921
//      Lecture: TuThu 10:30am
//  Description: It creates a stackpane and adds the hbox created in CoursePane
//Note: when you submit on gradescope, you need to comment out the package line

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class Assignment6 extends Application
{
    public static final int WIDTH = 600, HEIGHT = 400;
    @Override
    public void start(Stage stage)
    {
        StackPane root = new StackPane();
        CoursePane coursePane = new CoursePane();
        root.getChildren().add(coursePane);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setTitle("ASU Course Enrollment System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}