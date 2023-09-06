package com.example.studentsgradessystem.presentation;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LogInScreen extends Application {
    private Stage primaryStage;
    private static final String VALID_USERNAME = "admin"; // 预定义的有效用户名
    private static final String VALID_PASSWORD = "password"; // 预定义的有效密码
    private String registeredUsername = ""; // 用于存储已注册的用户名
    private String registeredPassword = ""; // 用于存储已注册的密码

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("登录");

        // 创建登录界面
        GridPane loginGrid = createLoginGrid();

        Scene loginScene = new Scene(loginGrid, 300, 200);
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private GridPane createLoginGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label usernameLabel = new Label("学号:");
        TextField usernameField = new TextField();
        usernameField.setPromptText("请输入学号");
        Label passwordLabel = new Label("身份证后六位:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("请输入身份证后六位");
        Button loginButton = new Button("登录");
        Button registerButton = new Button("注册账号");

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);
        grid.add(registerButton, 1, 3);

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // 在此处验证用户名和密码
            if (validateLogin(username, password)) {
//                 showMainUI();
            } else {
                showAlert("登录失败", "学号或身份证后六位不正确");
            }
        });

        registerButton.setOnAction(e -> {
            showRegistrationUI();
        });

        return grid;
    }

    private boolean validateLogin(String username, String password) {
        // 这里可以添加更强化的用户名和密码验证逻辑
        // 例如，检查用户名和密码是否与已注册的帐户匹配
        return username.equals(registeredUsername) && password.equals(registeredPassword);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

//    private void showMainUI() {
//        primaryStage.close();
//        StudentManagementApp mainApp = new StudentManagementApp();
//        Stage mainStage = new Stage();
//        mainApp.start(mainStage);
//    }

    private void showRegistrationUI() {
        // 在注册界面中创建帐户
        Dialog<String> registrationDialog = new Dialog<>();
        registrationDialog.setTitle("注册");
        registrationDialog.setHeaderText("请输入新的学号和身份证后六位:");

        // 创建学号和身份证输入字段
        TextField newUserField = new TextField();
        PasswordField newPasswordField = new PasswordField();
        newUserField.setPromptText("请输入学号");
        newPasswordField.setPromptText("请输入身份证后六位");
        GridPane registrationGrid = new GridPane();
        registrationGrid.add(new Label("新学号:"), 0, 0);
        registrationGrid.add(newUserField, 1, 0);
        registrationGrid.add(new Label("新密码:"), 0, 1);
        registrationGrid.add(newPasswordField, 1, 1);
        registrationDialog.getDialogPane().setContent(registrationGrid);

        // 添加注册和取消按钮
        ButtonType registerButtonType = new ButtonType("注册", ButtonBar.ButtonData.OK_DONE);
        registrationDialog.getDialogPane().getButtonTypes().addAll(registerButtonType, ButtonType.CANCEL);

        registrationDialog.setResultConverter(dialogButton -> {
            if (dialogButton == registerButtonType) {
                String newUsername = newUserField.getText();
                String newPassword = newPasswordField.getText();

                // 在此处创建新的帐户
                registeredUsername = newUsername;
                registeredPassword = newPassword;
                return "registered";
            }
            return null;
        });

        registrationDialog.showAndWait();
    }
}
