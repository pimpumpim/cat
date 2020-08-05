package example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.Random;

/**************
 * Стартовый экран создания животного(ых)
 */

public class StartMakingCatController extends AddAnimalAbstract {


    @Override
    void addCat(MouseEvent event) {
        super.addCat(event);

        ((Main) parentController).createMainScreen();//не очень красиво, нид поменять
    }


    @Override
    void randomCats(MouseEvent event) {
        super.randomCats(event);
        ((Main) parentController).createMainScreen();//не очень красиво, нид поменять
    }


}
