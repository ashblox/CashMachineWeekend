package rocks.zipcode.atm;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import rocks.zipcode.atm.bank.AccountData;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import sun.jvm.hotspot.debugger.win32.coff.TestDebugInfo;

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
        btnSubmit.setOnAction(e -> {
            int id = Integer.parseInt(field.getText());
            cashMachine.login(id);

            setTextAreas();
            field.setText("");
        });

        Button btnDeposit = new Button("Deposit");
        btnDeposit.setOnAction(e -> {
            int amount = Integer.valueOf(depositAmount.getText());
            cashMachine.deposit(amount);

            setTextAreas();
            depositAmount.setText("");
        });

        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setOnAction(e -> {
            int amount = Integer.parseInt(withdrawalAmount.getText());
            cashMachine.withdraw(amount);

            setTextAreas();
            withdrawalAmount.setText("");
        });

        Button btnExit = new Button("Log Off");
        btnExit.setOnAction(e -> {
            cashMachine.exit();

            setTextAreas();
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
