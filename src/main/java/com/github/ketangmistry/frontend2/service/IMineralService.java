package com.github.ketangmistry.frontend2.service;

import com.github.ketangmistry.frontend2.model.Mineral;
import java.util.List;

public interface IMineralService {

    List<Mineral> findAll();

    boolean updateMineral(String name, int ammount);
}