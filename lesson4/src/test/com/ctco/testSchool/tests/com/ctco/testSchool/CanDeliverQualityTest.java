package com.ctco.testSchool;

import static com.ctco.testSchool.Member.type.DEV;
import static com.ctco.testSchool.Member.type.TEST;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class CanDeliverQualityTest {

    /**
     * - Happy Path: team can deliver stories. 1 tester and 2 devs work in parallel, which give tester a time to finish testing in time
     * - Negative case: team can not deliver stories. with one tester in team and 2 devs work in parallel
     * - Happy Path: team can deliver same stories from previous test but with two testers in team and 2 devs work in parallel. It's a border case
     */

    @Test
    public void happyPathCanDeliverQualityWithOneTester() {
            /* - team can deliver stories. 1 tester and 2 devs work in parallel, which give tester a time to finish testing in time
               - team = dev1 + dev2 + test1
               - story1(Dev/Test) = 6/3
               - story2(dev/Test) = 1/1
             */
        Team team = new Team();

        // Set sprint days
        team.sprintDays = 10;

        // Add Team members and add velocity
        Member developerOne = new Member(DEV);
        team.addMember(developerOne);
        developerOne.velocity = 1;

        Member developerTwo = new Member(DEV);
        team.addMember(developerOne);
        developerTwo.velocity = 1;

        Member testerOne = new Member(TEST);
        team.addMember(testerOne);
        testerOne.velocity = 1.5;                     // story points per day

        // set story points for Devs and Testers. Put stories into sprint backlog
        Story storyOne = new Story();
        storyOne.setStoryPoints(6);
        storyOne.setTestPoints(3);

        Story storyTwo = new Story();
        storyTwo.setStoryPoints(1);
        storyTwo.setTestPoints(1);

        team.backlog = Arrays.asList(storyOne, storyTwo);
        Assert.assertTrue("Team should have delivered all stories in time with one", team.canDeliverQuality());
    }

    @Test
    public void teamCanNotDeliverQualityWithOneTester() {
            /* - team can not deliver stories. with one tester in team and 2 devs work in parallel
               - team = dev1 + dev2 + test1
               - story1(Dev/Test) = 6/3
               - story2(dev/Test) = 1/1
               - story3(dev/test) = 6/2
               - story4(dev/test) = 1/1
             */
        Team team = new Team();

        // Set sprint days
        team.sprintDays = 10;

        // Add Team members and add velocity
        Member developerOne = new Member(DEV);
        team.addMember(developerOne);
        developerOne.velocity = 1;

        Member developerTwo = new Member(DEV);
        team.addMember(developerOne);
        developerTwo.velocity = 1;

        Member testerOne = new Member(TEST);
        team.addMember(testerOne);
        testerOne.velocity = 1;                     // story points per day

        // set story points for Devs and Testers. Put stories into sprint backlog
        Story storyOne = new Story();
        storyOne.setStoryPoints(6);
        storyOne.setTestPoints(3);

        Story storyTwo = new Story();
        storyTwo.setStoryPoints(1);
        storyTwo.setTestPoints(1);

        Story storyThree = new Story();
        storyThree.setStoryPoints(6);
        storyThree.setTestPoints(2);

        Story storyFour = new Story();
        storyFour.setStoryPoints(1);
        storyFour.setTestPoints(1);

        team.backlog = Arrays.asList(storyOne, storyTwo);
        Assert.assertFalse("Team can't deliver all stories in time with one tester", team.canDeliverQuality());
    }

    @Test
    public void teamCanDeliverQualityWithTwoTesters() {
            /* - team can deliver same stories from previous test but with two testers in team and 2 devs work in parallel. It's a border case
               - team = dev1 + dev2 + test1
               - story1(Dev/Test) = 6/3
               - story2(dev/Test) = 1/1
               - story3(dev/test) = 6/2
               - story4(dev/test) = 1/1
             */
        Team team = new Team();

        // Set sprint days
        team.sprintDays = 10;

        // Add Team members and add velocity
        Member developerOne = new Member(DEV);
        team.addMember(developerOne);
        developerOne.velocity = 1;

        Member developerTwo = new Member(DEV);
        team.addMember(developerOne);
        developerTwo.velocity = 1;

        Member testerOne = new Member(TEST);
        team.addMember(testerOne);
        testerOne.velocity = 1;

        Member testerTwo = new Member(TEST);
        team.addMember(testerTwo);
        testerTwo.velocity = 1;

        // set story points for Devs and Testers. Put stories into sprint backlog
        Story storyOne = new Story();
        storyOne.setStoryPoints(6);
        storyOne.setTestPoints(3);

        Story storyTwo = new Story();
        storyTwo.setStoryPoints(1);
        storyTwo.setTestPoints(1);

        Story storyThree = new Story();
        storyThree.setStoryPoints(6);
        storyThree.setTestPoints(2);

        Story storyFour = new Story();
        storyFour.setStoryPoints(1);
        storyFour.setTestPoints(1);

        team.backlog = Arrays.asList(storyOne, storyTwo);
        Assert.assertTrue("Team can deliver all stories in time with two testers", team.canDeliverQuality());
    }

    /**
     * Black-box testing:
     * - Happy Path: Can deliver stories If testing team velocity > all stories points sum
     * - Negative Case: Can't deliver stories if testing team velocity < all stories points sum
     * - Border Case: Can deliver stories if testing team velocity = all stories points sum
     */

    @Test
    public void happyPathCanDeliverQualityBB() {  // testing team can deliver stories If testing team velocity > all stories points sum
        Team team = new Team();
        team.sprintDays = 10;

        // Add Test Team members and add velocity
        Member testerOne = new Member(TEST);
        team.addMember(testerOne);
        testerOne.velocity = 1;

        Member testerTwo = new Member(TEST);
        team.addMember(testerOne);
        testerTwo.velocity = 1;

        // set story points for sprint backlog
        Story storyOne = new Story();
        Story storyTwo = new Story();
        storyOne.setTestPoints(10);
        storyTwo.setTestPoints(8);

        team.backlog = Arrays.asList(storyOne, storyTwo);
        Assert.assertTrue(team.canDeliverQuality());
    }

    @Test
    public void negativeCaseBB() {  // testing team can't deliver stories with set Test velocities. if testing team velocity < all stories points sum
        Team team = new Team();
        team.sprintDays = 10;

        // Add Test Team members and add velocity
        Member testerOne = new Member(TEST);
        team.addMember(testerOne);
        testerOne.velocity = 1;

        Member testerTwo = new Member(TEST);
        team.addMember(testerOne);
        testerTwo.velocity = 1;

        // set story points for sprint backlog
        Story storyOne = new Story();
        Story storyTwo = new Story();
        storyOne.setTestPoints(13);
        storyTwo.setTestPoints(8);

        team.backlog = Arrays.asList(storyOne, storyTwo);
        Assert.assertFalse(team.canDeliverQuality());
    }

    @Test
    public void borderCaseBB() {  // testing team can deliver stories with set Test velocities if team velocity = all stories points sum
        Team team = new Team();
        team.sprintDays = 10;

        // Add Test Team members and add velocity
        Member testerOne = new Member(TEST);
        team.addMember(testerOne);
        testerOne.velocity = 1;

        Member testerTwo = new Member(TEST);
        team.addMember(testerOne);
        testerTwo.velocity = 1;

        // set story points for sprint backlog
        Story storyOne = new Story();
        Story storyTwo = new Story();
        storyOne.setTestPoints(12);
        storyTwo.setTestPoints(8);

        team.backlog = Arrays.asList(storyOne, storyTwo);
        Assert.assertTrue(team.canDeliverQuality());
    }

}