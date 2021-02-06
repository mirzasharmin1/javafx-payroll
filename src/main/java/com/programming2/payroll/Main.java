package com.programming2.payroll;

import com.programming2.payroll.controllers.*;
import com.programming2.payroll.models.Employee;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        loginWindow();
    }

    public void loginWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/views/LoginWindowView.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            LoginWindowController controller = loader.getController();
            controller.setMain(this);

            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void employeesWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/views/EmployeesWindowView.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            EmployeesWindowController controller = loader.getController();
            controller.setMain(this);

            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createEmployeeWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/views/EmployeeFormWindowView.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            EmployeeFormWindowController controller = loader.getController();
            controller.setMain(this);

            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEmployeeWindow(Employee selectedEmployee) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/views/EmployeeFormWindowView.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            EmployeeFormWindowController controller = loader.getController();

            controller.setMain(this);

            controller.setSelectedEmployee(selectedEmployee);

            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void leavesWindow(Employee selectedEmployee) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/views/LeavesWindowView.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            LeavesWindowController controller = loader.getController();
            controller.setMain(this);
            controller.setSelectedEmployee(selectedEmployee);

            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void payrollsWindow(Employee selectedEmployee) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/views/PayrollsWindowView.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            PayrollsWindowController controller = loader.getController();
            controller.setMain(this);
            controller.setSelectedEmployee(selectedEmployee);

            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
