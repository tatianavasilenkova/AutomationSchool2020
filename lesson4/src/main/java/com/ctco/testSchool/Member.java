package com.ctco.testSchool;

public class Member {
    public double velocity = 1.0;
    public Boolean codinSkills;
    public Boolean testingSkills;

    public enum type {DEV, TEST}

    public Member() {
    }

    public Member(type type) {
        codinSkills = type == Member.type.DEV;
        testingSkills = type == Member.type.TEST;
    }
}
