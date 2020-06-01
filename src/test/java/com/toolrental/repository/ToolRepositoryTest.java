package com.toolrental.repository;

import com.toolrental.model.Tool;
import com.toolrental.model.ToolType;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ToolRepositoryTest {
    private ToolRepository repo;

    @Before
    public void setUp(){
        repo = new ToolRepository();
    }

    @Test
    public void givenLadderToolCode_whenGetToolIsCalled_thenLadderMatchingCodeIsReturned(){
        Tool expected = new Tool("LADW", new ToolType("Ladder", new BigDecimal("1.99"), true, true, false), "Werner");
        Tool actual = repo.getTool("LADW");
        assertEquals(expected, actual);
    }

    @Test
    public void givenChainsawToolCode_whenGetToolIsCalled_thenChainsawMatchingCodeIsReturned(){
        Tool expected = new Tool("CHNS", new ToolType("Chainsaw", new BigDecimal("1.49"), true, false, true), "Stihl");
        Tool actual = repo.getTool("CHNS");
        assertEquals(expected, actual);
    }

    @Test
    public void givenRidgidJackhammerToolCode_whenGetToolIsCalled_thenJackhammerMatchingCodeIsReturned(){
        Tool expected = new Tool("JAKR", new ToolType("Jackhammer", new BigDecimal("2.99"), true, false, false), "Ridgid");
        Tool actual = repo.getTool("JAKR");
        assertEquals(expected, actual);
    }

    @Test
    public void givenDeWaltJackhammerToolCode_whenGetToolIsCalled_thenJackhammerMatchingCodeIsReturned(){
        Tool expected = new Tool("JAKD", new ToolType("Jackhammer", new BigDecimal("2.99"), true, false, false), "DeWalt");
        Tool actual = repo.getTool("JAKD");
        assertEquals(expected, actual);
    }
}
