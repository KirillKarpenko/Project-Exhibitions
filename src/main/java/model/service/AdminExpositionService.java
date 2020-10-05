package model.service;

import model.dao.DaoFactory;
import model.dao.ExpositionDao;
import model.dao.ShowroomDao;
import model.entity.Exposition;

import java.util.List;

public class AdminExpositionService {

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public void create(Exposition exposition) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            expositionDao.create(exposition);
        }
    }

    public boolean isExists(String name) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            Exposition exposition = expositionDao.findByName(name);
            return exposition != null;
        }
    }

    public List<Exposition> findByPage(int start, int total) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.findByPage(start, total);
        }
    }

    public List<String> findExpositionsNames() {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.findExpositionsNames();
        }
    }

    public Exposition findById(int id) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.findById(id);
        }
    }

    public Exposition findByName(String name) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.findByName(name);
        }
    }

    public void update(Exposition exposition) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            expositionDao.update(exposition);
        }
    }

    public void deleteById(int id) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            expositionDao.deleteById(id);
        }
    }

    public int amount() {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.amount();
        }
    }

}
