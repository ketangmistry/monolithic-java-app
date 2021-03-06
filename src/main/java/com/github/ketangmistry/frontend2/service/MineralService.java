package com.github.ketangmistry.frontend2.service;

import com.github.ketangmistry.frontend2.model.Mineral;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MineralService implements IMineralService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Mineral> findAll() {

        String sql = "SELECT * FROM minerals";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Mineral.class));
    }

    @Override
    public boolean updateMineral(String name, int ammount) {
        boolean updated = false;

        String sql = "SELECT * FROM minerals WHERE name = '" + name + "'";

        List<Mineral> minerals = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Mineral.class));

        if(minerals.size() > 0) {
            int newAmount = ammount + minerals.get(0).getPurchases();


            try {
                Date now = new Date();
                if((now.getTime() % 3)==0) {
                    updated = true;

                    sql = "UPDATE minerals SET purchases = ? WHERE name = ?";

                    // sleep to slow stuff down
                    try{Thread.sleep((long)(Math.random() * 7000));}catch(InterruptedException e){System.out.println(e);}  

                    jdbcTemplate.update(sql, newAmount, name);

                }
                else {
                    updated= false;
                    int i = 1 /0;
                }
            }
            catch(Exception exception) {
                    System.out.println("Exception is " + exception);
            }

        }

        System.out.println("Update is " + updated);

        return updated;

    }
}