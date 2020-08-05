package example;

import javafx.scene.media.AudioClip;

import java.nio.file.Paths;

public class StrategyVoiceMeow implements StrategyVoice {
    @Override
    public AudioClip getVoice() {

        return new AudioClip(Paths.get("src/sounds/meow.mp3").toUri().toString());

    }
}
