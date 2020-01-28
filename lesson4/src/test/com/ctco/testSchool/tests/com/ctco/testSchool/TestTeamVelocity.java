package com.ctco.testSchool;

import com.ctco.testSchool.Member;
import com.ctco.testSchool.Team;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TestTeamVelocity {

    @Test
    public void complexTest() {
        Team myTeam = new Team();
        Assert.assertEquals("Empty team has zero velicity", 0.0, myTeam.getTeamVelocity(), 0.1);

        Member member = new Member();
        myTeam.addMember(member);
        Assert.assertEquals("By default my velocity is 1 per 10 days of sprint", 10.0, myTeam.getTeamVelocity(), 0.1);

        myTeam.sprintDays = 9;
        Assert.assertEquals("Sprint is 9 days long", 9.0, myTeam.getTeamVelocity(), 0.1);
    }
}
