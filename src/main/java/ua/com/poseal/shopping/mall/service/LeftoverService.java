package ua.com.poseal.shopping.mall.service;

import ua.com.poseal.shopping.mall.dao.LeftoverDAO;
import ua.com.poseal.shopping.mall.dto.LeftoverDTO;

import java.util.List;
import java.util.Properties;

public class LeftoverService {
    private final LeftoverDAO leftoverDAO;
    public LeftoverService(Properties properties) {
        this.leftoverDAO = new LeftoverDAO(properties);
    }

    public void saveDataIntoLeftover() {
        leftoverDAO.insertDataIntoLeftover();
    }

    public List<LeftoverDTO> findMaxLeftover() {
        return leftoverDAO.getMaxLeftover();
    }

    public List<LeftoverDTO> findMaxLeftoverWithTies() {
        return leftoverDAO.getMaxLeftoverWithTies();
    }
}
