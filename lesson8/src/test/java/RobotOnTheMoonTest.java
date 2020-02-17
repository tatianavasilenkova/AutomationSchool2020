import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class RobotOnTheMoonTest {

    @Test
    public void testZeroMoonArea() {                                            //Moon has no land
        Robot robot = new Robot(0, 0, "Roomba Z0");
        Moon moon = new Moon(0, 0);
        Satelite satelite = new Satelite(moon, robot);
        int newX = 0;
        int newY = 0;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            satelite.moveRobot(newX, newY);
        });
        assertEquals("Moon has no surface! You've set: " + moon.getWidth() + " " + moon.getHeight() +
                " for Moon surface! Robot '" + robot.getName() + "' can't land", exception.getMessage(), "Incorrect exception message!");
    }

    @Test
    public void testRobotCanLand() {                                //Robot Can land
        Robot robot = new Robot(0, 0, "BB8");
        int startPointX = robot.getX();
        int startPointY = robot.getY();

        Moon moon = new Moon(5, 5);
        Satelite satelite = new Satelite(moon, robot);
        int newX = 5;
        int newY = 5;

        satelite.moveRobot(newX, newY);
        //SoftAssertions softAssertions = new SoftAssertions();
        assertEquals(startPointX + newX, robot.getX(), "Robot" + robot.getName() + " didn't land. Incorrect X coordinate!");
        assertEquals(startPointY + newY, robot.getY(), "Robot" + robot.getName() + " didn't land! Incorrect Y coordinate");
    }

    @Test
    public void testRobotCanNotLandWithNegativeCoordinateSet() {                     //Happy pass for negative case. Make sure robot can't be landed outside (negative coordinates) the moon
        Robot robot = new Robot(0, 0, "R2D2");
        Moon moon = new Moon(10, 10);
        Satelite satelite = new Satelite(moon, robot);
        int newX = -1;
        int newY = -1;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            satelite.moveRobot(newX, newY);
        });
        assertEquals("New coordinates: " + (robot.getX() + newX) + " " + (robot.getY() + newY) + " brought Robot outside the Moon!", exception.getMessage(), "Incorrect exception message!");
    }

    @Test
    public void testRobotCanNotLandOutsideTheMoon() {                     //Happy pass for negative case. Make sure robot can't be landed outside the moon
        Robot robot = new Robot(2, 2, "R2D2");
        Moon moon = new Moon(10, 10);
        Satelite satelite = new Satelite(moon, robot);
        int newX = 9;
        int newY = 9;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            satelite.moveRobot(newX, newY);
        });
        assertEquals("New coordinates: " + (robot.getX() + newX) + " " + (robot.getY() + newY) + " brought Robot outside the Moon!", exception.getMessage(), "Incorrect exception message!");
    }
}
