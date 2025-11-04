package org.example.demo2;

import javafx.scene.media.AudioClip;

public class SoundManager {
    private final AudioClip brick_Hit_Sound;
    private final AudioClip add_life_sound;
    private final AudioClip ball_add_sound;
    private final AudioClip add_slowball_sound;
    private final AudioClip paddle_hit_sound;

    SoundManager() {
        brick_Hit_Sound = new AudioClip(SoundManager.class.getResource(Config.SOUND_PATH + "brick_hit_2.wav").toExternalForm());
        add_life_sound = new AudioClip(SoundManager.class.getResource(Config.SOUND_PATH + "ball_add.wav").toExternalForm());
        ball_add_sound = new AudioClip(SoundManager.class.getResource(Config.SOUND_PATH + "add_slowball.wav").toExternalForm());
        paddle_hit_sound = new AudioClip(SoundManager.class.getResource(Config.SOUND_PATH + "paddle_hit.wav").toExternalForm());
        add_slowball_sound = new AudioClip(SoundManager.class.getResource(Config.SOUND_PATH + "add_slowball.wav").toExternalForm());
    }

    public void get_brick_Hit_Sound(){
        brick_Hit_Sound.play(Config.Volume);
    }

    public void get_add_life_sound(){
        add_life_sound.play(Config.Volume);
    }
    public void get_ball_add_sound(){
        ball_add_sound.play(Config.Volume);
    }
    public void get_add_slowball_sound(){
        add_slowball_sound.play(Config.Volume);
    }

    public void get_paddle_hit_sound(){
        paddle_hit_sound.play(Config.Volume);
    }

}
