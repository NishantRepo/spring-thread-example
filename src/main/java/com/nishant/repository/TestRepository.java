package com.nishant.repository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Repository
//@EnableTransactionManagement
public class TestRepository {

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void saveData() {


    }


}
