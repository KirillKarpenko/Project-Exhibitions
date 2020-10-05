package model.service;

import model.dao.AccountDao;
import model.dao.DaoFactory;
import model.entity.Account;

public class RegisterService {

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public boolean isExists(String login) {
        try (AccountDao accountDao = daoFactory.createAccountDao()) {
            Account account = accountDao.findByLogin(login);
            return account != null;
        }
    }

    public void registerAccount(Account account) {
        try (AccountDao accountDao = daoFactory.createAccountDao()) {
            accountDao.create(account);
        }
    }

}
