package com.ctco.testSchool;

import static com.ctco.testSchool.Member.type.DEV;
import static com.ctco.testSchool.Member.type.TEST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class CanDeliverQualityTest {

    /**
     * Try-catch loop:
     *  1. test_SprintDaysNotSet_canDeliverQuality()
     *  2. test_MemberVelocityIsMoreThanOne_canDeliverQuality()
     *  3. test_MemberVelocityPositiveness_canDeliverQuality()
     *  4. test_CanNotDeliver_canDeliverQuality()
     */
    @Test
    public void test_SprintDaysNotSet_canDeliverQuality() { //If BA forgot to set sprint days
        Team team = new Team();
        team.sprintDays = 0;

        Story story = new Story();
        story.setStoryPoints(2);
        story.setTestPoints(1);

        // Creating & setting Sprint backlog
        team.backlog = new ArrayList<>();
        team.backlog.add(story);

        try {
            assertEquals( false, team.canDeliverQuality(), "Team  could deliver when they should not");
            fail("Check sprint is more that 2 days test fails");

        } catch (IllegalArgumentException e) {
            assertEquals("Sprint should be at least two days long", e.getMessage(), "Exception not caught");
        }
    }


    @Test
    public void test_MemberVelocityIsMoreThanOne_canDeliverQuality() { // If one of team members has velocity set > 1
        Team team = new Team();
        team.sprintDays = 10;

        Member devOne = new Member(DEV);   // set Dev velocity 0.5
        team.addMember(devOne);
        devOne.velocity = 0.5;

        Member qaOne = new Member(TEST);   // set QA velocity 1.5
        team.addMember(qaOne);
        qaOne.velocity = 1.5;

        Story story = new Story();
        story.setStoryPoints(5);
        story.setTestPoints(5);

        team.backlog = new ArrayList<>(); //creating list of backlog and adding there a story
        team.backlog.add(story);

        try {
            assertEquals(true, team.canDeliverQuality(), "Team  could deliver when they should not");   // test should have been failed, but passed instead

        } catch (IllegalArgumentException e) {
            assertEquals("Team member velocity should be less than 1 but more than 0", e.getMessage(), "Exception not caught");
        }
    }


    @Test
    public void test_MemberVelocityPositiveness_canDeliverQuality() { // If one of team members has velocity set > 0 (meaning, he's not available OR got sick)
        Team team = new Team();
        team.sprintDays = 10;

        Member devOne = new Member(DEV);   // set Dev velocity 0.5
        team.addMember(devOne);
        devOne.velocity = 0.5;

        Member qaOne = new Member(TEST);   // set QA velocity 1.5
        team.addMember(qaOne);
        qaOne.velocity = 0;

        Story story = new Story();
        story.setStoryPoints(5);
        story.setTestPoints(5);

        team.backlog = new ArrayList<>(); //creating list of backlog and adding there a story
        team.backlog.add(story);

        try {
            assertEquals(true, team.canDeliverQuality(), "Team  could deliver when they should not");
            fail("team member velocity should be positive, team member got sick OR run out of time");

        } catch (IllegalArgumentException e) {
            assertEquals("Team member velocity should be less than 1 but more than 0", e.getMessage(), "Exception not caught");
        }
    }


    @Test
    public void test_CanNotDeliver_canDeliverQuality() { // If two stories have per 5 points for each team member, then test expected to be failed
        Team team = new Team();
        team.sprintDays = 10;

        Member devOne = new Member(DEV);   // set Dev velocity 0.5
        team.addMember(devOne);
        devOne.velocity = 1;

        Member qaOne = new Member(TEST);   // set QA velocity 1.5
        team.addMember(qaOne);
        qaOne.velocity = 1;

        Story storyOne = new Story();
        storyOne.setStoryPoints(5);
        storyOne.setTestPoints(5);

        Story storyTwo = new Story();
        storyTwo.setStoryPoints(5);
        storyTwo.setTestPoints(5);

        team.backlog = Arrays.asList(storyOne, storyTwo);
        assertEquals(false, team.canDeliverQuality(), "Team can deliver when they should not with two equal stories");
    }




    /**
     * Black-box tests:
     * - Happy Path: team can deliver stories. 1 tester and 2 devs work in parallel, which give tester a time to finish testing in time;
     * - Negative case: team can not deliver stories. with one tester in team and 2 devs work in parallel;
     * - Happy Path: team can deliver same stories from previous test but with two testers in team and 2 devs work in parallel. It's a border case
     * - Negative Path: team can not deliver stories if testers velocity = 0 and dev's not
     * - Negative Path: team can not deliver stories if dev's velocity = 0 and tester's not
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

        team.backlog = Arrays.asList(storyTwo, storyOne);
        Assert.assertTrue("Team can't deliver all stories in time with one tester", team.canDeliverQuality());
    }

    @Test
    public void teamCanDeliverQualityWithTwoTesters() {
            /* - team can deliver same stories from previous test but with two testers in team and 2 devs work in parallel. It's a border case
               - team = dev1 + dev2 + test1 + tester2
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

    @Test
    public void zeroSprintDaysWithExeption() { //.. Ask for code
        Team team = new Team();
        team.backlog = new ArrayList<>();
        // Set sprint days
        team.sprintDays = 0;
        try {
            team.canDeliverQuality();
            Assert.fail("Exception not thrown");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Sprint should be at least two days long", e.getMessage());
           // System.out.println(e);
        }
    }

    @Test
    public void teamCanNotDeliverQualityWithTestersZeroVelocity() {
            /* - team can not deliver stories if testers velocity = 0 and dev's not
               - team = dev1 + dev2 + test1 + test2
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
        testerOne.velocity = 0;

        Member testerTwo = new Member(TEST);
        team.addMember(testerTwo);
        testerTwo.velocity = 0;

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
        Assert.assertFalse("Team can deliver all stories in time with two testers velocity=0", team.canDeliverQuality());
    }

    @Test
    public void teamCanNotDeliverQualityWithDevZeroVelocity() {
            /* - team can not deliver stories if dev's velocity = 0 and tester's not
               - team = dev1 + dev2 + test1 + test2
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
        developerOne.velocity = 0;

        Member developerTwo = new Member(DEV);
        team.addMember(developerOne);
        developerTwo.velocity = 0;

        Member testerOne = new Member(TEST);
        team.addMember(testerOne);
        testerOne.velocity = 1;

        Member testerTwo = new Member(TEST);
        team.addMember(testerTwo);
        testerTwo.velocity = 0.5;

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
        Assert.assertFalse("Team can deliver all stories in time with two developers velocity=0", team.canDeliverQuality());
    }

    /**
     * White-box testing:
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