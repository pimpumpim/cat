package example;

import javafx.scene.input.MouseEvent;

public class AddAnimalController extends AddAnimalAbstract {

    @Override
    void addCat(MouseEvent event) {
        super.addCat(event);

       ((MainScreenController) parentController).insertList();
       parentController.getStage().close();
    }


    @Override
    void randomCats(MouseEvent event) {
        super.randomCats(event);

        ((MainScreenController) parentController).insertList();
        parentController.getStage().close();
    }

}
