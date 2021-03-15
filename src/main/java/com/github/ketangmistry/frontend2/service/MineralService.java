package com.github.ketangmistry.frontend2.service;

import com.github.ketangmistry.frontend2.model.Mineral;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MineralService implements IMineralService {
    private Logger logger = LoggerFactory.getLogger(MineralService.class);

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

        StackTraceElement[] stackTraceElements;
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

                    stackTraceElements = Thread.currentThread().getStackTrace();
                    this.printStackTrace(stackTraceElements);

                }
                else {
                    updated= false;
                    int i = 1 /0;
                }
            }
            catch(Exception exception) {
                exception.printStackTrace();
                logger.error(exception.getMessage());

            }

        }

        logger.info("Mineral updated is " + updated);

        return updated;

    }

    void printStackTrace(StackTraceElement[] stackTraceElements) {
        for (StackTraceElement element : stackTraceElements) {
            logger.info("{}:{}:{}", element.getClassName(), element.getMethodName(), element.getLineNumber());
        }
    }

}