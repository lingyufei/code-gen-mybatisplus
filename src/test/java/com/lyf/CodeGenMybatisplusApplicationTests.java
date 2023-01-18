package com.lyf;

import com.lyf.model.dto.response.TableInfoResponse;
import com.lyf.model.entity.TableInfoEntity;
import com.lyf.service.MySQLDatabaseInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class CodeGenMybatisplusApplicationTests {
    @Autowired
    MySQLDatabaseInfoService mySQLDatabaseInfoService;

    @Test
    void contextLoads() {
    }

    @Test
    void testMySQLDatabaseInfoService(){
//        TableInfoEntity tableInfoEntity = mySQLDatabaseInfoService.queryTable("user_info");
//        System.out.println(tableInfoEntity);

        List<TableInfoResponse> tableInfoResponses = mySQLDatabaseInfoService.queryAll(null);
        System.out.println(tableInfoResponses);
    }

}
