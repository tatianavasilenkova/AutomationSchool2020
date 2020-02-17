public class Robot {

    String name;

    private int x;
    private int y;

    public Robot(int x, int y, String name) {
        this.name = name;
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }


    /**
    public void moveX(int x) throws Exception {
            this.x = this.x + x;
        }

    public void moveY(int y) throws Exception {
            this.y = this.y + y;
        }
*/
}
