package uet.oop.bomberman.sound;

import uet.oop.bomberman.GameController;

import javax.sound.sampled.*;
import java.io.File;
import java.util.Stack;

public class Audio {
    Stack<Clip> buffer = new Stack<>();

    public Audio() {

    }

    public void playThemeSound() {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("res\\sound\\soundtrack.wav"));
            //clip = AudioSystem.getClip();
            Clip clip = AudioSystem.getClip();
            buffer.push(clip);
            clip.open(inputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void playEnemyDead() {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("res\\sound\\AA126_11.wav"));
            //clip = AudioSystem.getClip();
            Clip clip1 = AudioSystem.getClip();
            clip1.open(inputStream);
            ((FloatControl) clip1.getControl(FloatControl.Type.MASTER_GAIN)).setValue(20f * (float) Math.log10(GameController.volume));
            clip1.start();
            clip1.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip1.close();
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playBombDrop() {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("res\\sound\\BOM_SET.wav"));
            //clip = AudioSystem.getClip();
            Clip clip1 = AudioSystem.getClip();
            clip1.open(inputStream);
            ((FloatControl) clip1.getControl(FloatControl.Type.MASTER_GAIN)).setValue(20f * (float) Math.log10(GameController.volume));
            clip1.start();
            clip1.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip1.close();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playBombExplode() {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("res\\sound\\BOM_11_M.wav"));
            //clip = AudioSystem.getClip();
            Clip clip1 = AudioSystem.getClip();
            clip1.open(inputStream);
            ((FloatControl) clip1.getControl(FloatControl.Type.MASTER_GAIN)).setValue(20f * (float) Math.log10(GameController.volume));
            clip1.start();
            clip1.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip1.close();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playItemCollected() {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("res\\sound\\Item.wav"));
            //clip = AudioSystem.getClip();
            Clip clip1 = AudioSystem.getClip();
            clip1.open(inputStream);
            ((FloatControl) clip1.getControl(FloatControl.Type.MASTER_GAIN)).setValue(20f * (float) Math.log10(GameController.volume));
            clip1.start();
            clip1.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip1.close();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playMenuSound() {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("res\\sound\\MenuMusic.wav"));
            Clip clip = AudioSystem.getClip();
            buffer.push(clip);
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playMenuSelected() {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("res\\sound\\MenuSelect.wav"));
            //clip = AudioSystem.getClip();
            Clip clip1 = AudioSystem.getClip();
            clip1.open(inputStream);
            ((FloatControl) clip1.getControl(FloatControl.Type.MASTER_GAIN)).setValue(20f * (float) Math.log10(GameController.volume));
            clip1.start();
            clip1.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip1.close();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playGameOver() {
        try {

            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("res\\sound\\endgame3.wav"));
            //clip = AudioSystem.getClip();
            Clip clip1 = AudioSystem.getClip();
            clip1.open(inputStream);
            ((FloatControl) clip1.getControl(FloatControl.Type.MASTER_GAIN)).setValue(20f * (float) Math.log10(GameController.volume));
            clip1.start();
            clip1.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip1.close();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playVictory() {
        try {

            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("res\\sound\\Victory.wav"));
            //clip = AudioSystem.getClip();
            Clip clip1 = AudioSystem.getClip();
            clip1.open(inputStream);
            ((FloatControl) clip1.getControl(FloatControl.Type.MASTER_GAIN)).setValue(20f * (float) Math.log10(GameController.volume));
            clip1.start();
            clip1.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip1.close();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopSound() {
        buffer.peek().close();
    }

    public void stopAllSound() {

        while (buffer.size() != 0) {
            buffer.pop().close();
        }
    }



    public void setVolume(float volume) {
        for (Clip clip:buffer) {
            if (volume < 0f || volume > 1f)
                throw new IllegalArgumentException("Volume not valid: " + volume);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20f * (float) Math.log10(volume));
        }

    }
}