package com.ctco.testSchool;

public class Story {
    int value;
    int storyPoints;
    int testPoints;
    type type=null;
    public enum type {DEV, TEST, DB, BA}

    public int getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(int storyPoints) {
        this.storyPoints = storyPoints;
    }

    public int getTestPoints() {
        return testPoints;
    }

    public void setTestPoints(int testPoints) {
        this.testPoints = testPoints;
    }

public Story(int storyPoints, int test){
        this.storyPoints = storyPoints; // eto obra6enije ne k parametru a peremennoj klassa
        testPoints = test;
}


}
