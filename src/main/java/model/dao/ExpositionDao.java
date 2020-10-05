package model.dao;

import model.entity.Exposition;

import java.sql.Timestamp;
import java.util.List;

public interface ExpositionDao extends GenericDao<Exposition> {
    Exposition findById(int id);
    Exposition findByName(String name);
    List<Exposition> findByPage(int start, int total);
    List<String> findExpositionsNames();
    int amount();
    List<Exposition> filterByDateByPage(Timestamp startDate, Timestamp endDate, int start, int total);
    int dateAmount(Timestamp startDate, Timestamp endDate);
    List<Exposition> sortByPriceAscByPage(int start, int total);
    List<Exposition> sortByPriceDescByPage(int start, int total);
    List<Exposition> filterByCategoryByPage(Exposition.Category category, int start, int total);
    int categoryAmount(Exposition.Category category);
}
