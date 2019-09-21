package MainWork;

public class MyArrayDataException extends NumberFormatException {
    int x,y;

    public MyArrayDataException(String message, int x, int y) {
        super(message);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
