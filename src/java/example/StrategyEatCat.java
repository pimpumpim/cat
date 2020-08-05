package example;

import javafx.scene.media.AudioClip;

import java.nio.file.Paths;

public class StrategyEatCat implements StrategyEat {
    @Override
    public AudioClip gedSound() {
        return new AudioClip(Paths.get("src/sounds/cat_eat.mp3").toUri().toString());
    }
}
