package com.xiaoshan.common;

import com.xiaoshan.service.InitializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by gukc007 on 2017-05-04.
 */
@Component
public class Startup implements CommandLineRunner{

    @Autowired
    private InitializationService initializationService;

    @Override
    public void run(String... strings) throws Exception {
        initializationService.initializeData();
    }
}
