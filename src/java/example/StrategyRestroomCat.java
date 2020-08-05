package example;

import javafx.scene.media.AudioClip;

import java.nio.file.Paths;

public class StrategyRestroomCat implements StrategyRestroom {
    @Override
    public AudioClip gedSound() {
        return new AudioClip(Paths.get("src/sounds/cat_dig.mp3").toUri().toString());
    }
}
