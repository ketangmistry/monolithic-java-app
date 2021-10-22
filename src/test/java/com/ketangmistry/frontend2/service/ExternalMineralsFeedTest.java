package com.ketangmistry.frontend2.service;

import static org.junit.Assert.assertEquals;

import com.github.ketangmistry.frontend2.service.ExternalMineralsFeed;

import org.junit.Test;

public class ExternalMineralsFeedTest {

    @Test
    public void testGet() {
        ExternalMineralsFeed emf = new ExternalMineralsFeed();
        assertEquals(emf.get("http://1.2.3.4"), "0");
    }
    
}
