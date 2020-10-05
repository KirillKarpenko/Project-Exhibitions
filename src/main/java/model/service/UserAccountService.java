package model.service;

import model.dao.AccountDao;
import model.dao.DaoFactory;
import model.entity.Account;

public class UserAccountService {

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public void update(Account account) {
        try (AccountDao accountDao = daoFactory.createAccountDao()) {
            accountDao.update(account);
        }
    }

    public boolean isExists(String login) {
        try (AccountDao accountDao = daoFactory.createAccountDao()) {
            Account account = accountDao.findByLogin(login);
            return account != null;
        }
    }

    public void deleteById(int id) {
        try (AccountDao accountDao = daoFactory.createAccountDao()) {
            accountDao.deleteById(id);
        }
    }

}
