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

    public List<Exposition> sortByPriceAscAndFilterByCategoryByPage(Exposition.Category category, int start, int total) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.sortByPriceAscAndFilterByCategoryByPage(category, start, total);
        }
    }

    public List<Exposition> sortByPriceDescAndFilterByCategoryByPage(Exposition.Category category, int start, int total) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.sortByPriceDescAndFilterByCategoryByPage(category, start, total);
        }
    }

    public List<Exposition> sortByPriceAscAndFilterByDateByPage(Timestamp startDate, Timestamp endDate, int start, int total) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.sortByPriceAscAndFilterByDateByPage(startDate, endDate, start, total);
        }
    }

    public List<Exposition> sortByPriceDescAndFilterByDateByPage(Timestamp startDate, Timestamp endDate, int start, int total) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.sortByPriceDescAndFilterByDateByPage(startDate, endDate, start, total);
        }
    }

    public List<Exposition> filterByDateAndCategoryByPage(Timestamp startDate, Timestamp endDate, Exposition.Category category, int start, int total) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.filterByDateAndCategoryByPage(startDate, endDate, category, start, total);
        }
    }

    public List<Exposition> sortByPriceAscAndFilterByCategoryAndDateByPage(Timestamp startDate, Timestamp endDate, Exposition.Category category, int start, int total) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.sortByPriceAscAndFilterByCategoryAndDateByPage(startDate, endDate, category, start, total);
        }
    }

    public List<Exposition> sortByPriceDescAndFilterByCategoryAndDateByPage(Timestamp startDate, Timestamp endDate, Exposition.Category category, int start, int total) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.sortByPriceDescAndFilterByCategoryAndDateByPage(startDate, endDate, category, start, total);
        }
    }

    public int categoryAndDateAmount(Timestamp startDate, Timestamp endDate, Exposition.Category category) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.categoryAndDateAmount(startDate, endDate, category);
        }
    }

}
