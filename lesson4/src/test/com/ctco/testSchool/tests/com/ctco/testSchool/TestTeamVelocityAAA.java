package com.ctco.testSchool;

import com.ctco.testSchool.Member;
import com.ctco.testSchool.Team;
import org.junit.Assert;
import org.junit.Test;

public class TestTeamVelocityAAA {
    Team myTeam = new Team();

    @Test
    public void emptyTeamTest() {
        Assert.assertEquals("Empty team has zero velicity", 0.0, myTeam.getTeamVelocity(), 0.1);
    }

    @Test
    public void happyPathTest() {
        Member member = new Member();
        myTeam.addMember(member);
        Assert.assertEquals("By default my velocity is 1 per 10 days of sprint", 10.0, myTeam.getTeamVelocity(), 0.1);
    }

    @Test
    public void customSprintLength() {
        Member member = new Member();
        myTeam.addMember(member);
        myTeam.sprintDays = 9;
        Assert.assertEquals("Sprint is 9 days long", 9.0, myTeam.getTeamVelocity(), 0.1);
    }

    @Test
    public void ThreeMembersTest() {
        Member member_1 = new Member();
        Member member_2 = new Member();
        Member member_3 = new Member();
        myTeam.addMember(member_1);
        myTeam.addMember(member_2);
        myTeam.addMember(member_3);
        member_3.velocity = 0.5;
        Assert.assertEquals("By default my velocity is 1 per 10 days of sprint", 25.0, myTeam.getTeamVelocity(), 0.1);
    }
}
