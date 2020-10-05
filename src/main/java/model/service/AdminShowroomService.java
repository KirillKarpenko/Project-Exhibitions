package model.service;

import model.dao.DaoFactory;
import model.dao.ShowroomDao;
import model.entity.Showroom;

import java.util.List;

public class AdminShowroomService {

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public void create(Showroom showroom) {
        try (ShowroomDao showroomDao = daoFactory.createShowroomDao()) {
            showroomDao.create(showroom);
        }
    }

    public void createWithoutExposition(Showroom showroom) {
        try (ShowroomDao showroomDao = daoFactory.createShowroomDao()) {
            showroomDao.createWithoutExposition(showroom);
        }
    }

    public boolean isExists(String name) {
        try (ShowroomDao showroomDao = daoFactory.createShowroomDao()) {
            Showroom showroom = showroomDao.findByName(name);
            return showroom != null;
        }
    }

    public List<Showroom> findByPage(int start, int total) {
        try (ShowroomDao showroomDao = daoFactory.createShowroomDao()) {
            return showroomDao.findByPage(start, total);
        }
    }

    public void updateAndSetExpositionNull(Showroom showroom) {
        try (ShowroomDao showroomDao = daoFactory.createShowroomDao()) {
            showroomDao.updateAndSetExpositionNull(showroom);
        }
    }

    public void update(Showroom showroom) {
        try (ShowroomDao showroomDao = daoFactory.createShowroomDao()) {
            showroomDao.update(showroom);
        }
    }

    public void deleteById(int id) {
        try (ShowroomDao showroomDao = daoFactory.createShowroomDao()) {
            showroomDao.deleteById(id);
        }
    }

    public int amount() {
        try (ShowroomDao showroomDao = daoFactory.createShowroomDao()) {
            return showroomDao.amount();
        }
    }

}
