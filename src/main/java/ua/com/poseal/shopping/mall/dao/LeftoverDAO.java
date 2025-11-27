package ua.com.poseal.shopping.mall.dao;

import ua.com.poseal.shopping.mall.dto.LeftoverDTO;

import java.util.List;

public interface LeftoverDAO {

    void insertLeftover();

    LeftoverDTO getMaxLeftover();

    List<LeftoverDTO> getMaxLeftoverWithTies();
}
