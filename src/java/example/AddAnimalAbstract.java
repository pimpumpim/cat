package example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.Random;

public abstract class AddAnimalAbstract implements ControllerFXML {
    ParentController parentController;


    @FXML
    TextField catName;

    @FXML
    TextField startWeight;

    @FXML
    Button addCat;

    @FXML
    Button randomCats;

    @FXML
    ComboBox<Colors> choiceBox;

    @FXML
    void initialize() {
        //максимум 4 символа, точка, два символа, до точки хотябы один символ(шайтан забери)
        startWeight.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,4}([\\.]\\d{0,2})?")) {
                startWeight.setText(oldValue);
            }
        });

        for (Colors c : Colors.values()) {
            choiceBox.getItems().add(c);
        }

        choiceBox.setCellFactory(p -> new ListCell<Colors>() {

            @Override
            protected void updateItem(Colors item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    setText(item.getTranslation());
                } else {
                    setText(null);
                }
            }
        });
    }

    @FXML
    void addCat(MouseEvent event) {
        String catNameStr = checkField(catName) ? catName.getText() : null;
        double catWeight = -1.0;

        if (checkField(startWeight)) {
            double buf = Double.parseDouble(startWeight.getText());
            if (buf >= 1000.0) {
                catWeight = buf;
            } else {
                startWeight.setStyle("-fx-border-color: #ff1100;");
                startWeight.setText("");
                startWeight.setPromptText("Вы ввели вес кота меньше 1000 г.");
            }
        }

        Colors colors = choiceBox.getValue();


        if (catNameStr != null && catWeight != -1.0 && colors != null) {
            AbstractAnimal.createCat(catNameStr, catWeight, colors);
        } else {
            return;
        }


    }

    //проверка полей
    private boolean checkField(TextField textField) {
        if (textField.getText().equals("")) {
            textField.setStyle("-fx-border-color: #ff1100;");
            return false;
        }
        textField.setStyle("-fx-border-color:  #dc7633;");


        return true;

    }

    @FXML
    void randomCats(MouseEvent event) {
        Random random = new Random();

        //псевдорандом количества котов
        int createCountCat = random.nextInt(2) + 5;
        for (int i = 0; i <= createCountCat; i++) {
            AbstractAnimal.createRandomAnimal();
        }
    }

    public void setParentController(ParentController parentController) {
        this.parentController = parentController;
    }
}
