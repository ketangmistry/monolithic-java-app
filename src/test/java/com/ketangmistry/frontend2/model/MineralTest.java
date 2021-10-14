package com.ketangmistry.frontend2.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.ketangmistry.frontend2.model.Mineral;

import org.junit.Test;

public class MineralTest {

    @Test
    public void createMineral() {
        Mineral mineral = new Mineral();
        assertTrue(mineral.getPurchases() == 0);        
    }

    
}
