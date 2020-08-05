package example;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/********
 *
 * диалог для еды
 *
 */

public class DialogAmountFoodConttroller {

    Stage stage;

    MainScreenController mainScreenController;

    @FXML
    private TextField amountFood;

    @FXML
    void cancellation(MouseEvent event) {
        mainScreenController.setFood(-1.0);
        mainScreenController.getStage().close();
    }

    @FXML
    void ok(MouseEvent event) {
        if (amountFood.getText().equals("")) {
            amountFood.setStyle("-fx-border-color: #ff1100;");
            return;
        } else if (Double.parseDouble(amountFood.getText()) > 100.0) {
            amountFood.setStyle("-fx-border-color: #ff1100;");
            amountFood.setText("");
            amountFood.setPromptText("Вы ввели больше 100 г.");
            return;

        } else {
            mainScreenController.setFood(Double.parseDouble(amountFood.getText()));
        }
        mainScreenController.getStage().close();
    }

    @FXML
    void initialize() {
        amountFood.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,3}([\\.]\\d{0,2})?")) {
                amountFood.setText(oldValue);
            }
        });
    }


    public void setParentController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

}
