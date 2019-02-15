package rocks.zipcode.atm;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
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

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {

    private TextField field = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());

    private Parent createContent() {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 600);

        TextArea areaInfo = new TextArea();

        Button btnSubmit = new Button("Set Account ID");
        btnSubmit.setOnAction(e -> {
            int id = Integer.parseInt(field.getText());
            cashMachine.login(id);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnDeposit = new Button("Deposit");
        btnDeposit.setOnAction(e -> {
            int amount = Integer.parseInt(field.getText());
            cashMachine.deposit(amount);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setOnAction(e -> {
            int amount = Integer.parseInt(field.getText());
            cashMachine.withdraw(amount);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnExit = new Button("Exit");
        btnExit.setOnAction(e -> {
            cashMachine.exit();

            areaInfo.setText(cashMachine.toString());
        });

        FlowPane flowpane = new FlowPane();

        flowpane.getChildren().add(btnSubmit);
        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnExit);
//        vbox.getChildren().addAll(field, flowpane, areaInfo);
//        return vbox;

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setMinSize(300,300);
        grid.setVgap(5);
        grid.setHgap(5);

        Text accountNumber = new Text("Account Number: ");
        grid.add(accountNumber,0,0);

        TextField text1 = new TextField();
        text1.setPrefColumnCount(10);
        grid.add(text1, 1, 0);

        Text name = new Text("Name: ");
        grid.add(name, 0, 1);

        TextField text2 = new TextField();
        grid.add(text2, 1, 1);

        Text email = new Text("Email: ");
        grid.add(email, 0, 2);

        TextField text3 = new TextField();
        grid.add(text3, 1, 2);

        Text balance = new Text("Balance: ");
        grid.add(balance, 0, 3);

        TextField text4 = new TextField();
        grid.add(text4, 1, 3);


        BorderPane pane = new BorderPane();
        pane.setTop(field);
        pane.setLeft(grid);
        pane.setRight(flowpane);
        return pane;

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
