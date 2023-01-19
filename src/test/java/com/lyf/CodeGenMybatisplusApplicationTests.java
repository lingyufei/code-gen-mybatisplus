package com.lyf;

import com.lyf.core.generator.FreeMarkerGenerator;
import com.lyf.core.model.to.GenerationRequestInfoTo;
import com.lyf.core.model.to.StringWriterResultTo;
import com.lyf.core.schema.SchemaBuilder;
import com.lyf.core.schema.TableSchema;
import com.lyf.dao.MySQLDatabaseInfoDao;
import com.lyf.model.dto.response.ColumnInfoResponse;
import com.lyf.model.dto.response.TableInfoResponse;
import com.lyf.model.entity.ColumnInfoEntity;
import com.lyf.model.entity.TableInfoEntity;
import com.lyf.service.MySQLDatabaseInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
class CodeGenMybatisplusApplicationTests {
    @Autowired
    MySQLDatabaseInfoService mySQLDatabaseInfoService;
    @Autowired
    MySQLDatabaseInfoDao mySQLDatabaseInfoDao;

    @Test
    void contextLoads() {
    }

    @Test
    void testTableService(){
//        TableInfoEntity tableInfoEntity = mySQLDatabaseInfoService.queryTable("user_info");
//        System.out.println(tableInfoEntity);

        List<TableInfoResponse> tableInfoResponses = mySQLDatabaseInfoService.queryAllTable(null);
        System.out.println(tableInfoResponses);
    }

    @Test
    void testColumnService(){
        List<ColumnInfoResponse> columnInfoResponses = mySQLDatabaseInfoService.queryColumnsOfTable("user_info");
        System.out.println(columnInfoResponses);
    }

    @Autowired
    FreeMarkerGenerator freeMarkerGenerator;
    @Test
    void testFreeMarker(){
        TableInfoEntity tableInfoEntity = mySQLDatabaseInfoDao.queryTable("user_info");
        List<ColumnInfoEntity> columnInfoEntities = mySQLDatabaseInfoDao.queryColumns("user_info");
        GenerationRequestInfoTo requestInfoTo = new GenerationRequestInfoTo("user_info", null, tableInfoEntity, columnInfoEntities);

        TableSchema tableSchema = SchemaBuilder.BuildDefaultSchema(requestInfoTo);
//        Optional<StringWriterResultTo> generate = freeMarkerGenerator.generate(tableSchema, "Entity.java.ftl");
//        Optional<StringWriterResultTo> generate = freeMarkerGenerator.generate(tableSchema, "Dao.java.ftl");
//        Optional<StringWriterResultTo> generate = freeMarkerGenerator.generate(tableSchema, "Dao.xml.ftl");
//        Optional<StringWriterResultTo> generate = freeMarkerGenerator.generate(tableSchema, "service.java.ftl");
//        Optional<StringWriterResultTo> generate = freeMarkerGenerator.generate(tableSchema, "ServiceImpl.java.ftl");
//        Optional<StringWriterResultTo> generate = freeMarkerGenerator.generate(tableSchema, "Controller.java.ftl");
//        Optional<StringWriterResultTo> generate = freeMarkerGenerator.generate(tableSchema, "Request.java.ftl");
//        Optional<StringWriterResultTo> generate = freeMarkerGenerator.generate(tableSchema, "Response.java.ftl");
//        Optional<StringWriterResultTo> generate = freeMarkerGenerator.generate(tableSchema, "Vo.java.ftl");
        Optional<StringWriterResultTo> generate1 = freeMarkerGenerator.generate(tableSchema, "common/application.yml.ftl");
        Optional<StringWriterResultTo> generate2 = freeMarkerGenerator.generate(tableSchema, "common/BusinessException.java.ftl");
        Optional<StringWriterResultTo> generate3 = freeMarkerGenerator.generate(tableSchema, "common/Dependency.xml.ftl");
        Optional<StringWriterResultTo> generate4 = freeMarkerGenerator.generate(tableSchema, "common/ExceptionCodeEnum.java.ftl");
        Optional<StringWriterResultTo> generate5 = freeMarkerGenerator.generate(tableSchema, "common/ExceptionControllerAdvice.java.ftl");
        Optional<StringWriterResultTo> generate6 = freeMarkerGenerator.generate(tableSchema, "common/R.java.ftl");

    }

}
