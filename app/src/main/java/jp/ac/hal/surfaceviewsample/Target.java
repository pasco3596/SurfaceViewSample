package jp.ac.hal.surfaceviewsample;



/**
 * Created by pasuco on 2017/03/06.
 */

public class Target {
    private int x;
    private int y;
    private int speedX;
    private int speedY;
    private int color;

    public Target(int x, int y, int speedX, int speedY, int color){
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.color = color;

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public int getColor() {
        return color;
    }
}
