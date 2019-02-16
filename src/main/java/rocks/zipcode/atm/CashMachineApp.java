package rocks.zipcode.atm;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import rocks.zipcode.atm.bank.AccountData;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import sun.jvm.hotspot.debugger.win32.coff.TestDebugInfo;

import java.text.DecimalFormat;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {

    private TextField field = new TextField();
    private TextField idArea = new TextField();
    private TextField nameArea = new TextField();
    private TextField emailArea = new TextField();
    private TextField balanceArea = new TextField();
    private TextField depositAmount = new TextField();
    private TextField withdrawalAmount = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());

    private Parent createContent() {
//        VBox vbox = new VBox(10);
//        vbox.setPrefSize(600, 600);

        Button btnSubmit = new Button("Submit");
        Button btnDeposit = new Button("Deposit");
        Button btnWithdraw = new Button("Withdraw");

        btnDeposit.setDisable(true);
        btnWithdraw.setDisable(true);

        btnSubmit.setOnAction(e -> {
            int id = Integer.parseInt(field.getText());
            cashMachine.login(id);

            setTextAreas();
            field.setText("");
            if (cashMachine.accountActive() == true) {
                btnDeposit.setDisable(false);
                btnWithdraw.setDisable(false);
            }

        });

        btnDeposit.setOnAction(e -> {
            double amount = Double.parseDouble(depositAmount.getText());
            cashMachine.deposit(amount);

            setTextAreas();
            depositAmount.setText("");

        });

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ALERT!");
        alert.setHeaderText("Important Information Regarding Your Account");
        alert.setResizable(true);
        alert.setContentText("Your account is currently overdrawn. Please make a deposit as soon as possible.");


        btnWithdraw.setOnAction(event -> {
            if (cashMachine.accountActive() == true) {
                btnWithdraw.setDisable(false);
            } else {
                btnWithdraw.setDisable(true);
            }
        });
        btnWithdraw.setOnAction(e -> {
            double amount = Double.parseDouble(withdrawalAmount.getText());
            cashMachine.withdraw(amount);

            setTextAreas();
            withdrawalAmount.setText("");

            if (cashMachine.overdrawn()) {
                alert.showAndWait();
            }
        });

        Button btnExit = new Button("Log Off");
        btnExit.setOnAction(e -> {
            cashMachine.exit();

            setTextAreas();

            btnDeposit.setDisable(true);
            btnWithdraw.setDisable(true);
        });

//        FlowPane flowpane = new FlowPane();
//
//        flowpane.getChildren().add(btnSubmit);
//        flowpane.getChildren().add(btnDeposit);
//        flowpane.getChildren().add(btnWithdraw);
//        flowpane.getChildren().add(btnExit);
//        vbox.getChildren().addAll(field, flowpane, areaInfo);
//        return vbox;

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20,20,20,20));
        grid.setMinSize(300,300);
        grid.setVgap(5);
        grid.setHgap(5);

        Text login = new Text("Enter Account Number: ");
        grid.add(login,1, 0);

        grid.add(field, 2, 0);

        grid.add(btnSubmit, 3, 0);

        Label spacing = new Label("");
        grid.add(spacing, 4, 1);

        Text deposit = new Text("Make Deposit: ");
        grid.add(deposit, 0, 2);

        grid.add(depositAmount, 1, 2);

        grid.add(btnDeposit, 2, 2);

        Text withdrawal = new Text("Make Withdrawal: ");
        grid.add(withdrawal, 0, 3);

        grid.add(withdrawalAmount, 1, 3);

        grid.add(btnWithdraw, 2, 3);

        Label id = new Label("Account ID: ");
        grid.add(id, 5, 2);

        grid.add(idArea, 6, 2);

        Label name = new Label ("Name: ");
        grid.add(name, 5, 3);

        grid.add(nameArea, 6, 3);

        Label email = new Label("Email: ");
        grid.add(email, 5, 4);

        grid.add(emailArea, 6, 4);

        Label balance = new Label("Balance: ");
        grid.add(balance, 5, 5);

        grid.add(balanceArea, 6, 5);

        Label moreSpacing = new Label("");
        grid.add(moreSpacing, 2, 6);

        grid.add(btnExit, 2, 7);


//        BorderPane pane = new BorderPane();
//        pane.setTop(field);
//        pane.setLeft(depositOrWithdrawal);
//        pane.setRight(accountDetails);
        return grid;

    }

    public void setTextAreas() {
        idArea.setText(cashMachine.idToString());
        nameArea.setText(cashMachine.nameToString());
        emailArea.setText(cashMachine.emailToString());
        balanceArea.setText(cashMachine.balanceToString());
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
