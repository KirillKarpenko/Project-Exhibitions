package model.dao;

import model.entity.Account;

public interface AccountDao extends GenericDao<Account> {
    Account findByLogin(String login);
    Account findByLoginAndPassword(String login, String password);
}
