package com.ctco.testSchool.tests;

import java.util.Arrays;
import java.util.List;

import com.ctco.testSchool.Member;
import com.ctco.testSchool.Story;
import com.ctco.testSchool.Team;
import org.junit.Assert;
import org.junit.Test;

public class CanDoTest {

    //  Team myTeam = new Team();  // Create new object of 'Team' class. It assigned to 'MyTeam' variable with type 'Team'

    @Test
    public void happyPathStory() {

        Team team = new Team();

        Assert.assertTrue(team.canDo());

        Story story = new Story();
        List<Story> stories = Arrays.asList(story);
        team.backlog = stories;
        Assert.assertTrue(team.canDo());
    }

    @Test
    public void oneStoryTest() {

        Team team = new Team();
        Assert.assertTrue(team.canDo());

        Story story = new Story();
        story.setStoryPoints(1);
        team.backlog = Arrays.asList(story);

        Member member = new Member();
        Assert.assertFalse(team.canDo());
        team.addMember(member);
        Assert.assertTrue(team.canDo());
    }

}
