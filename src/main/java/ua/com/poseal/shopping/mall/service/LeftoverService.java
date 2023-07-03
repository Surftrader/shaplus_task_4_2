package ua.com.poseal.shopping.mall.service;

import ua.com.poseal.shopping.mall.dao.LeftoverDAO;
import ua.com.poseal.shopping.mall.dao.LeftoverDAOImpl;
import ua.com.poseal.shopping.mall.dto.LeftoverDTO;

import java.util.List;
import java.util.Properties;

public class LeftoverService {
    private final LeftoverDAO leftoverDAO;
    public LeftoverService(Properties properties) {
        this.leftoverDAO = new LeftoverDAOImpl(properties);
    }

    public void saveLeftover() {
        leftoverDAO.insertLeftover();
    }

    public LeftoverDTO findMaxLeftover() {
        return leftoverDAO.getMaxLeftover();
    }

    public List<LeftoverDTO> findMaxLeftoverWithTies() {
        return leftoverDAO.getMaxLeftoverWithTies();
    }
}
