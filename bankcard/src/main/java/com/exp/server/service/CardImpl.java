package com.exp.server.service;

import com.exp.server.dao.CardDao;
import com.exp.server.dao.CardDaoImpl;

public class CardImpl implements Card {
    public CardDao cd=null;

    public void setCd(CardDao cd) {
        this.cd = cd;
    }

    public int login(String id, String pwd) {

        return cd.loginDao(id,pwd);
    }

    public int query(String id) {
        return cd.queryDao(id);
    }

    public int withdraw(String id, int m) {
        return cd.withdrawDao(id,m);
    }

    public int topUp(String id, int m) {
        return cd.topUpDao(id,m);
    }

    @Override
    public int state(String id) {
        return cd.queryLockDao(id);
    }
}
