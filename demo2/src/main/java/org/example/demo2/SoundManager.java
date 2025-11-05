package org.example.demo2;

import javafx.scene.media.AudioClip;

public class SoundManager {
    private static SoundManager instance;

    private final AudioClip brick_Hit_Sound;
    private final AudioClip add_life_sound;
    private final AudioClip ball_add_sound;
    private final AudioClip add_slowball_sound;
    private final AudioClip paddle_hit_sound;

    // Private constructor để ngăn tạo mới bên ngoài
    private SoundManager() {
        brick_Hit_Sound = new AudioClip(SoundManager.class.getResource(Config.SOUND_PATH + "brick_hit_2.wav").toExternalForm());
        add_life_sound = new AudioClip(SoundManager.class.getResource(Config.SOUND_PATH + "ball_add.wav").toExternalForm());
        ball_add_sound = new AudioClip(SoundManager.class.getResource(Config.SOUND_PATH + "add_slowball.wav").toExternalForm());
        paddle_hit_sound = new AudioClip(SoundManager.class.getResource(Config.SOUND_PATH + "paddle_hit.wav").toExternalForm());
        add_slowball_sound = new AudioClip(SoundManager.class.getResource(Config.SOUND_PATH + "add_slowball.wav").toExternalForm());
    }

    // Phương thức lấy instance duy nhất của SoundManager
    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    public void playBrickHitSound(){
        brick_Hit_Sound.play(Config.getInstance().getVolume());
    }

    public void playAddLifeSound(){
        add_life_sound.play(Config.getInstance().getVolume());
    }

    public void playBallAddSound(){
        ball_add_sound.play(Config.getInstance().getVolume());
    }

    public void playAddSlowBallSound(){
        add_slowball_sound.play(Config.getInstance().getVolume());
    }

    public void playPaddleHitSound(){
        paddle_hit_sound.play(Config.getInstance().getVolume());
    }

}

