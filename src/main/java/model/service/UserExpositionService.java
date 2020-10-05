package model.service;

import model.dao.DaoFactory;
import model.dao.ExpositionDao;
import model.entity.Exposition;

import java.sql.Timestamp;
import java.util.List;
public class UserExpositionService {

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Exposition> findByPage(int start, int total) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.findByPage(start, total);
        }
    }

    public int amount() {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.amount();
        }
    }

    public List<Exposition> filterByDateByPage(Timestamp startDate, Timestamp endDate, int start, int total) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.filterByDateByPage(startDate, endDate, start, total);
        }
    }

    public int dateAmount(Timestamp startDate, Timestamp endDate) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.dateAmount(startDate, endDate);
        }
    }

    public List<Exposition> sortByPriceAscByPage(int start, int total) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.sortByPriceAscByPage(start, total);
        }
    }

    public List<Exposition> sortByPriceDescByPage(int start, int total) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.sortByPriceDescByPage(start, total);
        }
    }

    public List<Exposition> filterByCategoryByPage(Exposition.Category category, int start, int total) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.filterByCategoryByPage(category, start, total);
        }
    }

    public int categoryAmount(Exposition.Category category) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.categoryAmount(category);
        }
    }

}
