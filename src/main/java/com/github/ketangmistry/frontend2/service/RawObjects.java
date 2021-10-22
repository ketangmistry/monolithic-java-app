package com.github.ketangmistry.frontend2.service;

import com.github.ketangmistry.frontend2.model.Mineral;
import java.util.List;

public interface RawObjects {

    List<Mineral> findAll();

    boolean update(String name, int ammount);
}