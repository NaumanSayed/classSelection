package com.example.assignment6;

// Assignment #: Arizona State University Spring 2023 CSE205 #6
//         Name: Nauman Ahmed Nazir Ahmed Sayed
//    StudentID: 1226754921
//      Lecture: Tu Thu 10:30am
//  Description: This class makes a hbox and all the commands needed are written
//Note: when you submit on gradescope, you need to comment out the package line

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.ArrayList;


public class CoursePane extends HBox
{
    //GUI components
    private ArrayList<Course> courseList;
    private VBox checkboxContainer;
    //Step 1.1: declare any necessary instance variables here
    private ComboBox<String> subInitial;
    private TextField forCourseNum;
    private TextField forInstructor;
    private Button btAdd;
    private Button btDrop;
    private Label bottomLeft;
    private boolean forCheckBox;
    private CheckBox check;
    private int totalCourses;
    private Label totalEnrolledLabel;

    //constructor
    public CoursePane()
    {
        Label labelLeft = new Label("Add Course(s)");
        labelLeft.setTextFill(Color.BLUE);
        labelLeft.setFont(Font.font(null, 14));
        Label labelRight = new Label("Course(s) Enrolled");
        labelRight.setTextFill(Color.BLUE);
        labelRight.setFont(Font.font(null, 14));
        //set up the layout. Note: CoursePane is a HBox and contains
        //leftPane, centerPane and rightPane. Pick proper layout class
        //and use nested sub-pane if necessary, then add each GUI components inside.
        //step 1.3: create and set up the layout of the leftPane, leftPane contains
//                a top label, the center sub-pane
        //and a label show at the bottom


        VBox leftPane = new VBox();
        VBox centerPane = new VBox();
        VBox rightPane = new VBox();

        GridPane left = new GridPane();
        left.setAlignment(Pos.CENTER);
        left.setPadding(new Insets(12, 12, 12, 12));
        left.setHgap(10); //set horizontal gap between columns
        left.setVgap(10);

        Label sub = new Label("Subject");
        Label courseNum = new Label("Course Num");
        Label instructor = new Label("Instructor");
        sub.setTextFill(Color.BLACK);
        courseNum.setTextFill(Color.BLACK);
        instructor.setTextFill(Color.BLACK);

        forCourseNum = new TextField();
        forInstructor = new TextField();

        subInitial = new ComboBox<String>();
        subInitial.setStyle("-fx-color:beige");
        String[] course = {"ACC", "AME", "BME", "CHM", "CSE", "DAT", "EEE"};
        String defaultSub = "CSE";
        subInitial.getItems().addAll(course);
        subInitial.setValue(defaultSub);
        subInitial.setOnAction(new ComboBoxHandler());

        left.add(sub, 0, 0);
        left.add(subInitial, 1, 0);

        left.add(courseNum, 0, 1);
        left.add(forCourseNum, 1, 1);

        left.add(instructor, 0, 2);
        left.add(forInstructor, 1, 2);

        bottomLeft = new Label("No course Entered");

        leftPane.getChildren().addAll(labelLeft, left, bottomLeft);

        VBox.setMargin(labelLeft, new Insets(10, 20, 85, 10));
        VBox.setMargin(left, new Insets(10, 20, 105, 10));
        VBox.setMargin(bottomLeft, new Insets(0, 20, 0, 10));

        //step 1.4: create and set up the layout of the centerPane which holds the
//        two buttons
        GridPane center = new GridPane();
        center.setAlignment(Pos.CENTER);
        center.setVgap(20);

        btAdd = new Button("Add=>");
        btDrop = new Button("Drop<=");

        btAdd.setOnAction(new ButtonHandler());  //Step 3: Register the GUI component with its handler class
        btDrop.setOnAction(new ButtonHandler());

        center.add(btAdd, 0, 8);
        center.add(btDrop, 0, 9);

        centerPane.getChildren().add(center);
        centerPane.setPadding(new Insets(0, 7, 0, 7));
        //step 1.5: create and set up the layout of the rightPane, rightPane
//        contains a top label,
        //checkboxContainer and a label show at the bottom

        //already defined in GUI component
        checkboxContainer = new VBox();
        checkboxContainer.setPadding(new Insets(10, 0, 75, 0));
        checkboxContainer.setSpacing(5);

        totalCourses = 0;
        totalEnrolledLabel = new Label("Total course enrolled: " + totalCourses);

        rightPane.setPadding(new Insets(10));
        rightPane.setSpacing(10);
        rightPane.getChildren().addAll(labelRight, checkboxContainer, totalEnrolledLabel);


        courseList = new ArrayList<Course>();

        //----
        //CoursePane is a HBox. Add leftPane, centerPane and rightPane inside
        this.setPadding(new Insets(10, 10, 10, 10));


        leftPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))); //This creates a border around both left and right pane
        rightPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        this.getChildren().addAll(leftPane, centerPane, rightPane);
    } //end of constructor
    //step 2.1: Whenever a new Course is added or one or several courses are
//    dropped/removed, this method will
    public void updateCheckBoxContainer()
    {
        checkboxContainer.getChildren().clear(); //1) clear the original checkboxContainer;
        for(int i = 0; i < courseList.size(); i++) {
            CheckBox checkbox = new CheckBox(courseList.get(i).toString());  //2) create a CheckBox for each Course object inside the courseList, and also
//    add it inside the checkboxContainer;
            checkboxContainer.getChildren().addAll(checkbox);
            checkbox.setOnAction(new CheckBoxHandler(courseList.get(i)));    //3) register the CheckBox with the CheckBoxHandler.
        }
        totalCourses++;
        totalEnrolledLabel.setText("Total course enrolled: " + totalCourses);
    }
    //Step 2.2: Create a ButtonHandler class
    private class ButtonHandler implements EventHandler<ActionEvent>
    {
        public void handle(ActionEvent e)
        {

                try {
                    int cNum = 0;
                if(!forCourseNum.getText().isBlank()) {
                    cNum = Integer.parseInt(forCourseNum.getText().trim());
                }

                    String instr = forInstructor.getText().trim();
                    String boxValue = subInitial.getValue();

                    if ((e.getSource() == btAdd) && (instr.length() != 0) && (cNum > 0))//everything is entered correctly and the "Add =>" button is pressed
                    {
                        boolean tORf = true;
                        for (int j = 0; j < courseList.size(); ++j) {
                            if ((cNum == (courseList.get(j).getCourseNum())) && (boxValue == (courseList.get(j).getSubject()))) {
                                tORf = false;
                            } else {
                                tORf = true;
                            }
                        }
                        //need to check whether the course already exist inside the courseList or not
                        if (tORf)//it's a new course
                        {
                            Course newCourse = new Course(boxValue, cNum, instr);
                            courseList.add(newCourse);
                            updateCheckBoxContainer();
                            bottomLeft.setText("Course added successfully");
                            bottomLeft.setTextFill(Color.BLACK);
                            //----
                        } else //a duplicated one
                        {
                            //show error message
                            bottomLeft.setText("Duplicated course - Not added");
                            bottomLeft.setTextFill(Color.RED);
                        }
                        forInstructor.clear();
                        forCourseNum.clear();  //Clear all the text fields and prepare for the next entry
                    }

                    else if ((e.getSource() == btAdd) && (((forCourseNum.getText().isBlank()) || (forInstructor.getText().isBlank())) || ((forCourseNum.getText().isBlank()) && (forInstructor.getText().isBlank())))) { //"=>" button is pressed, but one of the text field is empty
                        bottomLeft.setText("At least one field is empty. Fill all fields");
                        bottomLeft.setTextFill(Color.RED);

                        forInstructor.clear();
                        forCourseNum.clear();
                    }

                    else if ((e.getSource() == btDrop))//when "Drop Course" button is pushed.)
                    {

                        System.out.println("dropped");
                        ArrayList<Course> coursesToRemove = new ArrayList<>();

                        int childrenCount = checkboxContainer.getChildren().size();
                        for (int i = 0; i < childrenCount; i++) { // The function of checkbox handler is done here
                            if (checkboxContainer.getChildren().get(i) instanceof CheckBox) {
                                CheckBox checkBox = (CheckBox) checkboxContainer.getChildren().get(i);
                                if (checkBox.isSelected()) {
                                    String[] courseInfo = new String[]{checkBox.getText()};
                                    for (int j = 0; j < courseList.size(); j++) {
                                        Course course = courseList.get(j);
                                        if (course.toString().equals(courseInfo[0])) {
                                            coursesToRemove.add(course);
                                            totalCourses--;
                                            break;
                                        }
                                    }
                                    checkboxContainer.getChildren().remove(checkBox);
                                    childrenCount--;
                                    i--;
                                }
                            }
                        }
                        totalEnrolledLabel.setText("Total course enrolled: " + totalCourses);
                        courseList.removeAll(coursesToRemove);

                    } else  //  for all other invalid actions, thrown an exception and it will be caught
                    {
                        throw new Exception("");
                    }
                } //end of try
                catch (NumberFormatException ex) {
                    System.err.println(ex.getMessage());
                    bottomLeft.setText("Error! Course number must be an integer");
                    bottomLeft.setTextFill(Color.RED);

                } catch (Exception exception) {
                    System.err.println(exception.getMessage());
                }
        } //end of handle() method
    } //end of ButtonHandler class
    ////Step 2.3: A ComboBoxHandler
    private class ComboBoxHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent actionEvent) {

        }
    }//end of ComboBoxHandler
    //Step 2.4: A CheckBoxHandler
    private class CheckBoxHandler implements EventHandler<ActionEvent> //this method is not being used but the function is performed the same
    {
        // Pass in a Course object so that we know which course is associated with which CheckBox
        private Course oneCourse;
        //constructor
        public CheckBoxHandler(Course oneCourse)
        {
            int course = oneCourse.getCourseNum();
            String subject = oneCourse.getSubject();
            //----
        }
        public void handle(ActionEvent e)
        {
            check = (CheckBox) e.getSource();
            if (check.isSelected() == true) {
                forCheckBox = true;
            } else {
                forCheckBox = false;
            }
        }
    }//end of CheckBoxHandler
} //end of CoursePane class