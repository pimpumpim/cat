package example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

/********
 *
 * главный экран игры
 * второй экран
 * при смерте не меняется, просто разные фоны
 *
 * возможно добать:
 * активность животных, не только при включеном состоянии, но и при выключеном
 * для этого в листе свой адаптер
 * реактивное программирование??
 *
 * удалять животное из списка?
 *
 */

public class MainScreenController implements ControllerFXML, ParentController {

    @FXML
    private AnchorPane background;

    @FXML
    private ImageView animalPicture;

    @FXML
    private ListView<Animal> listCat;

    @FXML
    private ProgressBar progrssBarWeight;

    @FXML
    private BorderPane kontrolAnimal;

    @FXML
    private Text weightLabel;

    @FXML
    private ToolBar cycleLifeAnimal;

    @FXML
    private Text posthumousInscription;

    private Animal animal = null;

    ParentController parent;

    Stage dialogStage;


    @FXML
    void initialize() {
        insertList();


        listCat.setCellFactory(new Callback<ListView<Animal>, ListCell<Animal>>() {
            @Override
            public ListCell<Animal> call(ListView<Animal> listView) {
                return new CustomAdapter();
            }
        });

        listCat.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {

                    animal = newValue;
                    //проверка статуса, если животное мертво поменять фон,
                    //в противном случаи включить выключенное)
                    //надо допелить, для многопоточности
                    if (animal != null) {

                        if (animal.getStatus()) {
                            posthumousInscription.setText("");
                            StringBuffer url = new StringBuffer("../img/" +
                                    animal.getColor().name());
                            if (animal.getIsClone()) {
                                url.insert(url.length(), "_CLONE");
                            }
                            url.insert(url.length(), ".png");
                            animalPicture.setImage(new Image(getClass().getResourceAsStream(url.toString())));
                            animalPicture.setLayoutY(150.0);
                            kontrolAnimal.setOpacity(1.0);
                            kontrolAnimal.setDisable(false);
                            cycleLifeAnimal.setDisable(false);
                            background.setStyle("-fx-background-image:  url(/img/background.jpg);");
                            changeWeight();
                        } else {
                            changeWeight();
                        }
                    }

                });
        listCat.getSelectionModel().select(0);

    }

    public void insertList() {
        int index = listCat.getSelectionModel().getSelectedIndex();
        listCat.getItems().clear();
        ObservableList<Animal> animalsList = FXCollections.observableArrayList();
        animalsList.addAll(AbstractAnimal.getAnimalsList());
        listCat.setItems(animalsList);
        listCat.getSelectionModel().select(index);
    }

    private void animalDeath() {
        String url = "../img/grave.png";
        animalPicture.setImage(new Image(getClass().getResourceAsStream(url)));
        animalPicture.setLayoutY(210.0);
        kontrolAnimal.setOpacity(0.0);
        kontrolAnimal.setDisable(true);
        cycleLifeAnimal.setDisable(true);
        background.setStyle("-fx-background-image:  url(/img/cemetery5.jpg);");
        if (!animal.getIsClone()) {

            posthumousInscription.setText("Этот кот умер, потому что Вы заставляли\n" +
                    "его мяукать и не кормили.\n" +
                    "Александр Климов смотрел бы на Вас\n" +
                    "с осуждением и негодованием.");

        } else {
            posthumousInscription.setText("Этот кот умер, потому что Вы заставляли\n" +
                    "его мяукать и не кормили.\n" +
                    "Он был хорошим, несмотря на то,\n" +
                    "что всегда мазал мимо лотка.");
        }
    }

    private void explosionAnimal() {
        String url = "../img/explosionAnimal.png";
        animalPicture.setImage(new Image(getClass().getResourceAsStream(url)));
        kontrolAnimal.setOpacity(0.0);
        kontrolAnimal.setDisable(true);
        cycleLifeAnimal.setDisable(true);
        background.setStyle("-fx-background-image:  url(/img/background.jpg);");
        if (!animal.getIsClone()) {

            posthumousInscription.setText("Вы перекормили этого кота,\n" +
                    "из-за чего он взорвался.\n" +
                    "Как теперь это все отмывать?!\n");

        } else {
            posthumousInscription.setText("Штурмовик взорвался.\n" +
                    "Ну что ж, с штурмовиками бывает.\n");
        }

    }

    public void changeWeight() {
        double maxWeight = animal.getMaxWeight();
        double weight = animal.getWeight();
        double minWeight = animal.getMinWeight();
        if (weight >= maxWeight) {
            explosionAnimal();
            return;
        } else if (weight <= minWeight) {
            animalDeath();
            return;
        }
        double percentWeight = (weight - minWeight) / maxWeight;
        progrssBarWeight.setProgress(percentWeight);
        weightLabel.setText((int) weight + "/" + (int) maxWeight);
    }

    @FXML
    void add(MouseEvent event) {

        Stage addAnimal = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../xml/startMakingCat.fxml"));
        AddAnimalController aAC = new AddAnimalController();
        loader.setController(aAC);
        aAC.setParentController(this);
        addAnimal.setResizable(false);
        try {
            addAnimal.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        addAnimal.show();
        addAnimal.setX(addAnimal.getX() + 100.0);
        addAnimal.setY(addAnimal.getY() + 50.0);
        dialogStage = addAnimal;

    }

    @FXML
    void eat(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../xml/amountOfFood.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage dialog = new Stage();
        dialog.setResizable(false);
        dialog.setTitle("Amount");
        dialog.setScene(new Scene(root));

        dialog.initModality(Modality.WINDOW_MODAL); // перекрыть окно
        dialog.initOwner(parent.getStage()); //какое окно

        dialog.show();

        dialog.setX(dialog.getX() + 100.0);
        dialog.setY(dialog.getY() + 50.0);

        dialogStage = dialog;

        DialogAmountFoodConttroller dialogAmountFoodConttroller = (DialogAmountFoodConttroller) loader.getController();
        dialogAmountFoodConttroller.setParentController(this);

    }

    public void setFood(double food) {
        //закидываю ссылку в переменную, для удобства добавления функционала в будущем

        if (food != -1.0) {
            StrategyEat eat = animal.feed(food);
            eat.gedSound().play();
            changeWeight();
        }
    }

    @FXML
    void explosion(MouseEvent event) {

        (animal.feed(10000.0)).gedSound().play();
        changeWeight();

    }

    @FXML
    void fly(MouseEvent event) {

        Stage airportDialog = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../xml/airport.fxml"));
        AirportDialogueController aDC = new AirportDialogueController();
        loader.setController(aDC);
        aDC.setParentController(this);
        airportDialog.setResizable(false);
        try {
            airportDialog.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        airportDialog.show();
        airportDialog.setX(airportDialog.getX() + 200.0);
        airportDialog.setY(airportDialog.getY() + 50.0);
        dialogStage = airportDialog;

    }

    @FXML
    void voice(MouseEvent event) {
        animal.voice().getVoice().play();
        changeWeight();
    }

    // тупо, надо заменить, чисто по заданию
    @FXML
    void voiceDeath(MouseEvent event) {

        double weight = animal.getWeight();
        double minWeight = animal.getMinWeight();
        double deduct = weight - minWeight;
        for (int i = 0; i < deduct; i++) {

            animal.voice().getVoice();
        }
        animal.voice().getVoice().play();
        changeWeight();

    }

    @FXML
    void restroom(MouseEvent event) {
        StrategyRestroom restroom = animal.restroom();
        restroom.gedSound().play();
    }

    @FXML
    void clone(MouseEvent event) {
        animal.cloneAnimal();
        insertList();

    }

    @Override
    public void setParentController(ParentController parent) {
        this.parent = parent;
    }

    @Override
    public Stage getStage() {
        return dialogStage;
    }

    private class CustomAdapter extends ListCell<Animal> {
        private HBox container;
        private Label name;

        public CustomAdapter() {
            super();
            name = new Label();
            name.setTextFill(Color.web("#ffffff"));
            container = new HBox(name);
            container.setSpacing(10);
        }


        @Override
        protected void updateItem(Animal item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null && !empty) { // <== test for null item and empty parameter
                name.setText(item.getName());
                setGraphic(container);
            } else {
                setGraphic(null);
            }
        }
    }

}
