package com.example.studentsgradessystem.presentation;

import com.example.studentsgradessystem.domain.Flags;
import com.example.studentsgradessystem.domain.events.TeacherObserver;
import com.example.studentsgradessystem.domain.pojo.Teacher;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.List;
import java.util.regex.Pattern;

public class LogInScreen extends Application {


    private Stage primaryStage;

    private final LogInViewModel logInViewModel = new LogInViewModel();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("成绩管理系统");

        // 创建登录界面

        Scene loginScene = new Scene(createLoginGrid(), 300, 250);
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private VBox createLoginGrid() {

        VBox verticalBox = new VBox(10);
        verticalBox.setPadding(new Insets(25, 25, 25, 25));

        Label usernameLabel = new Label("账号");
        TextField usernameField = new TextField();
        usernameField.setPromptText("myemail@qq.com");
        Label passwordLabel = new Label("密码");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("**************");
        Button loginButton = new Button("登录");
        Label registerLabel = new Label("没有账号？注册");

        registerLabel.setTextAlignment(TextAlignment.CENTER);

        registerLabel.setStyle("-fx-background-color: white; -fx-text-fill: black;");

        // Set event handlers for mouse hovering
        registerLabel.setOnMouseEntered(event -> {
            registerLabel.setStyle("-fx-background-color: white; -fx-text-fill: #4380e6;");
        });

        registerLabel.setOnMouseExited(event -> {
            registerLabel.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        });


        VBox buttonBox = new VBox(loginButton, registerLabel);
        buttonBox.setAlignment(Pos.CENTER);
        VBox.setMargin(loginButton, new Insets(20, 0, 12, 0));
        loginButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);


        verticalBox.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, buttonBox);

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // 在此处验证用户名和密码
            validateLogin(username, password);

        });


        registerLabel.setOnMouseClicked(mouseEvent -> showRegistrationUI());

        return verticalBox;
    }

    private void validateLogin(String username, String password) {


        logInViewModel.registerObserver(new TeacherObserver() {
            @Override
            public void update(List<Teacher> data) {

            }
            @Override
            public void update(Teacher data) {
                if (data != null) {

                    System.out.println(data.getEmail());
                    //save current session
                    //show main ui
//                    logInViewModel.unregisterObserver(this);
                } else {
                    showAlert("登录失败", "学号或身份证后六位不正确");
                }
            }

            @Override
            public void update(Flags flag) {

            }
        });
        logInViewModel.getTeacherByCredentials(username, password);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

//    private void showMainUI() {
//        primaryStage.close();
//        StudentManagementApp mainApp = new StudentManagementApp();
//        Stage mainStage = new Stage();
//        mainApp.start(mainStage);
//    }

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private void showRegistrationUI() {


        // 在注册界面中创建帐户
        Dialog<String> registrationDialog = new Dialog<>();

        VBox verticalBox = new VBox(10);
        verticalBox.setPadding(new Insets(25, 25, 25, 25));

        registrationDialog.setTitle("注册");


        TextField newUserField = new TextField();
        TextField newUserEmail = new TextField();
        PasswordField newPasswordField = new PasswordField();
        newUserField.setPromptText("蔡雅韵");
        newUserEmail.setPromptText("myemail@qq.com");
        newPasswordField.setPromptText("************");

        verticalBox.getChildren().addAll(new Label("姓名"), newUserField, new Label("Email"), newUserEmail, new Label("新密码"), newPasswordField);
        registrationDialog.getDialogPane().setContent(verticalBox);
        registrationDialog.getDialogPane().setPrefHeight(250);
        registrationDialog.getDialogPane().setPrefWidth(300);

        // 添加注册和取消按钮
        ButtonType registerButtonType = new ButtonType("注册", ButtonBar.ButtonData.OK_DONE);
        registrationDialog.getDialogPane().getButtonTypes().addAll(registerButtonType, ButtonType.CANCEL);

        registrationDialog.setResultConverter(dialogButton -> {
            if (dialogButton == registerButtonType) {
                String newUsername = newUserField.getText().trim();
                String newPassword = newPasswordField.getText().trim();
                String newEmail = newUserEmail.getText().trim();

                isValidEmail(newEmail);

                logInViewModel.registerObserver(new TeacherObserver() {
                    @Override
                    public void update(List<Teacher> data) {

                    }

                    @Override
                    public void update(Teacher data) {
                        //save current session
                        System.out.println(data.getEmail());
                    }

                    @Override
                    public void update(Flags flag) {
                        if (flag.equals(Flags.RegisterSuccess)) {
                            System.out.println("Registered");
                        } else {

                        }
                    }
                });
                logInViewModel.registerTeacher(newUsername, newEmail, newPassword);
            }
            return null;
        });

        registrationDialog.showAndWait();
    }
}
