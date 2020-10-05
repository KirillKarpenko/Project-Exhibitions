package model.service;

import model.dao.AccountDao;
import model.dao.DaoFactory;
import model.entity.Account;

public class LoginService {

    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public Account validateAccount(String login, String password) {
        try (AccountDao accountDao = daoFactory.createAccountDao()) {
            return accountDao.findByLoginAndPassword(login, password);
        }
    }

}
