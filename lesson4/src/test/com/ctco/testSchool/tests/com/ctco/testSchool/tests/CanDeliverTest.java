package com.ctco.testSchool.tests;

import static com.ctco.testSchool.Member.type.DEV;
import static com.ctco.testSchool.Member.type.TEST;

import java.util.Arrays;
import java.util.List;

import com.ctco.testSchool.Member;
import com.ctco.testSchool.Story;
import com.ctco.testSchool.Team;
import org.junit.Assert;
import org.junit.Test;

public class CanDeliverTest {

    @Test
    public void happyPathCanDeliver() {  // team can deliver stories with set devs velocities
        Team team = new Team();

        // Add Team members and add velocity
        Member developerOne = new Member(DEV);
        team.addMember(developerOne);
        developerOne.velocity = 1;

        Member developerTwo = new Member(DEV);
        team.addMember(developerTwo);
        developerTwo.velocity = 2;

        Member testerOne = new Member(TEST);
        team.addMember(testerOne);
        testerOne.velocity = 5;

        // set story points for sprint backlog
        Story storyOne = new Story();
        Story storyTwo = new Story();
        storyOne.setStoryPoints(1);
        storyTwo.setStoryPoints(1);

        team.backlog = Arrays.asList(storyOne, storyTwo);

        Assert.assertTrue(team.canDeliver());
    }

    @Test
    public void negativePathCanDeliver() {  // team can't deliver stories with set DEVS velocities
        Team team = new Team();

        // Add Team members and add velocity
        Member developerOne = new Member(DEV);
        team.addMember(developerOne);
        developerOne.velocity = 1;

        Member developerTwo = new Member(DEV);
        team.addMember(developerTwo);
        developerTwo.velocity = 2.5;

        Member testerOne = new Member(TEST);
        team.addMember(testerOne);
        testerOne.velocity = 5;

        // set story points for sprint backlog
        Story storyOne = new Story();
        Story storyTwo = new Story();
        storyOne.setStoryPoints(1);
        storyTwo.setStoryPoints(4);

        team.backlog = Arrays.asList(storyOne, storyTwo);

        Assert.assertFalse(team.canDeliver());
    }

    @Test
    public void devsZeroVelocity() {  // team can't deliver stories with set DEVS velocities
        Team team = new Team();

        // Add Team members and add velocity
        Member developerOne = new Member(DEV);
        team.addMember(developerOne);
        developerOne.velocity = 0;

        Member developerTwo = new Member(DEV);
        team.addMember(developerTwo);
        developerTwo.velocity = 0;

        Member testerOne = new Member(TEST);
        team.addMember(testerOne);
        testerOne.velocity = 5;

        // set story points for sprint backlog
        Story storyOne = new Story();
        Story storyTwo = new Story();
        storyOne.setStoryPoints(1);
        storyTwo.setStoryPoints(1);

        team.backlog = Arrays.asList(storyOne, storyTwo);

        Assert.assertFalse(team.canDeliver());
    }

    @Test
    public void AllDevsNegativeVelocity() {  // team can't deliver stories with set all DEVS negative velocities
        Team team = new Team();

        // Add Team members and add velocity
        Member developerOne = new Member(DEV);
        team.addMember(developerOne);
        developerOne.velocity = -1;

        Member developerTwo = new Member(DEV);
        team.addMember(developerTwo);
        developerTwo.velocity = -2;

        Member testerOne = new Member(TEST);
        team.addMember(testerOne);
        testerOne.velocity = 5;

        // set story points for sprint backlog
        Story storyOne = new Story();
        Story storyTwo = new Story();
        storyOne.setStoryPoints(1);
        storyTwo.setStoryPoints(1);

        team.backlog = Arrays.asList(storyOne, storyTwo);

        Assert.assertFalse(team.canDeliver());
    }

    @Test
    public void OneDevNegativeOnePositiveVelocity() {  // team can deliver stories if one developer has negative velocity and another has positive velosity that >= backlog storyPoints
        Team team = new Team();

        // Add Team members and add velocity
        Member developerOne = new Member(DEV);
        team.addMember(developerOne);
        developerOne.velocity = 3;

        Member developerTwo = new Member(DEV);
        team.addMember(developerTwo);
        developerTwo.velocity = -2;

        Member testerOne = new Member(TEST);
        team.addMember(testerOne);
        testerOne.velocity = 5;

        // set story points for sprint backlog
        Story storyOne = new Story();
        Story storyTwo = new Story();
        storyOne.setStoryPoints(1);
        storyTwo.setStoryPoints(1);    // NOTE: Exeption error in CanDeliver() method if setStoryPoint has negative int number

        team.backlog = Arrays.asList(storyOne, storyTwo);

        Assert.assertTrue(team.canDeliver());
    }
}
