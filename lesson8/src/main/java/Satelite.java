public class Satelite {
    private Moon moon;
    private Robot robot;


    public Satelite(Moon moon, Robot robot) {
        this.moon = moon;
        this.robot = robot;
    }

    public void moveRobot(int moveX, int moveY)  {

        int currentPositionX = robot.getX();
        int currentPositionY = robot.getY();

        int newX = currentPositionX + moveX;
        int newY = currentPositionY + moveY;


        if (newX < 0 || newX > moon.getWidth() || newY < 0 || newY > moon.getHeight()) {
            throw new IllegalArgumentException("New coordinates: " + newX + " " + newY + " brought Robot outside the Moon!");
        } else if (moon.getWidth() == 0 || moon.getHeight() == 0) {
            throw new IllegalArgumentException("Moon has no surface! You've set: " + moon.getWidth() + " " + moon.getHeight() +
                    " for Moon surface! Robot '" + robot.getName() + "' can't land");
        }
        robot.setX(newX);
        robot.setY(newY);
        System.out.println(robot.name + " has landed! Mission Completed!");
    }
}
