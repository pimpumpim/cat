package example;

import com.skillbox.airport.Airport;
import com.skillbox.airport.Terminal;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class AirportDialogueController implements ControllerFXML {

    ParentController parent;

    private int summDepartureAircrafts;
    private int summArrivalAircrafts;
    private int summParkingAircrafts;

    @FXML
    private VBox setHBox;

    @FXML
    private HBox setVBoxTerminal;

    @FXML
    void ok(MouseEvent event) {

        parent.getStage().close();

    }

    @FXML
    void initialize() {

        Airport airport = Airport.getInstance();
        setVBoxTerminal.setStyle("-fx-spacing: 10;");
        for (Terminal terminal : airport.getTerminals()) {

            setTerminal(terminal);

        }

        setHBox.setStyle("-fx-spacing: 15;");

        VBox summTerminalInformationBox = new VBox();

        Label labelDepartureAircrafts = new Label();
        labelDepartureAircrafts.setText("Всего вылетающие самолетов: " + summDepartureAircrafts);
        labelDepartureAircrafts.setFont(Font.font(14));

        Label labelArrivalAircrafts = new Label();
        labelArrivalAircrafts.setText("Всего прибывающих самолетов: " + summArrivalAircrafts);
        labelArrivalAircrafts.setFont(Font.font(14));

        Label labelParkingAircrafts = new Label();
        labelParkingAircrafts.setText("Всего самолетов на стоянки: " + summParkingAircrafts);
        labelParkingAircrafts.setFont(Font.font(14));

        Label labelTotal = new Label();
        int summ = summDepartureAircrafts + summArrivalAircrafts + summParkingAircrafts;
        labelTotal.setText("Итого: " + summ);
        labelTotal.setFont(Font.font(14));

        summTerminalInformationBox.setStyle("-fx-spacing: 5;");
        summTerminalInformationBox.setAlignment(Pos.CENTER);


        summTerminalInformationBox.getChildren().addAll(labelDepartureAircrafts,
                labelArrivalAircrafts, labelParkingAircrafts, labelTotal);
        setHBox.getChildren().add(summTerminalInformationBox);
    }

    void setTerminal(Terminal terminal) {

        VBox terminalInformationBox = new VBox();
        Label labelDepartureAircrafts = new Label();
        int departureAircrafts = terminal.getDepartureAircrafts().size();
        summDepartureAircrafts += departureAircrafts;
        labelDepartureAircrafts.setText("Вылетающие самолеты: " + departureAircrafts);
        labelDepartureAircrafts.setFont(Font.font(14));

        int arrivalAircrafts = terminal.getArrivalAircrafts().size();
        summArrivalAircrafts += arrivalAircrafts;
        Label labelArrivalAircrafts = new Label();
        labelArrivalAircrafts.setText("Прибывающие самолеты: " + arrivalAircrafts);
        labelArrivalAircrafts.setFont(Font.font(14));

        int parkingAircrafts = terminal.getParkingAircrafts().size();
        summParkingAircrafts += parkingAircrafts;
        Label labelParkingAircrafts = new Label();
        labelParkingAircrafts.setText("Самолеты на стоянки: " + parkingAircrafts);
        labelParkingAircrafts.setFont(Font.font(14));

        Label labelTotal = new Label();
        int summ = departureAircrafts + arrivalAircrafts + parkingAircrafts;
        labelTotal.setText("Итого: " + summ);
        labelTotal.setFont(Font.font(14));

        terminalInformationBox.setStyle("-fx-spacing: 2;");
        terminalInformationBox.setAlignment(Pos.CENTER_LEFT);


        terminalInformationBox.getChildren().addAll(labelDepartureAircrafts,
                labelArrivalAircrafts, labelParkingAircrafts, labelTotal);
        setVBoxTerminal.getChildren().add(terminalInformationBox);


    }

    @Override
    public void setParentController(ParentController parentController) {
        this.parent = parentController;
    }
}
