package model.dao;

import model.entity.Showroom;

import java.util.List;

public interface ShowroomDao extends GenericDao<Showroom> {
    void createWithoutExposition(Showroom showroom);
    Showroom findByName(String name);
    List<Showroom> findByPage(int start, int total);
    int amount();
    void updateAndSetExpositionNull(Showroom showroom);
}
