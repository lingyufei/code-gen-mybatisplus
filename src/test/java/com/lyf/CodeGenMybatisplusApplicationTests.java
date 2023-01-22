package com.lyf;

import com.lyf.constant.Constant;
import com.lyf.core.generator.file.FreeMarkerGenerator;
import com.lyf.core.model.bo.TableGenerationInfoBo;
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
        TableGenerationInfoBo requestInfoTo = new TableGenerationInfoBo("user_info", null, tableInfoEntity, columnInfoEntities, Constant.DEFAULT_PACKAGE, Constant.DEFAULT_AUTHOR);

        TableSchema tableSchema = SchemaBuilder.BuildDefaultSchema(requestInfoTo);
//        Optional<StringWriterResultTo> generate = freeMarkerGenerator.generate(tableSchema, "${entity}.java.ftl");
//        Optional<StringWriterResultTo> generate = freeMarkerGenerator.generate(tableSchema, "${entity}Dao.java.ftl");
//        Optional<StringWriterResultTo> generate = freeMarkerGenerator.generate(tableSchema, "${entity}Dao.xml.ftl");
//        Optional<StringWriterResultTo> generate = freeMarkerGenerator.generate(tableSchema, "service.java.ftl");
//        Optional<StringWriterResultTo> generate = freeMarkerGenerator.generate(tableSchema, "${entity}ServiceImpl.java.ftl");
//        Optional<StringWriterResultTo> generate = freeMarkerGenerator.generate(tableSchema, "${entity}Controller.java.ftl");
//        Optional<StringWriterResultTo> generate = freeMarkerGenerator.generate(tableSchema, "${entity}Request.java.ftl");
//        Optional<StringWriterResultTo> generate = freeMarkerGenerator.generate(tableSchema, "${entity}Response.java.ftl");
        freeMarkerGenerator.generate(tableSchema, "${entity}Bo.java.ftl");
        freeMarkerGenerator.generate(tableSchema, "common/application.yml.ftl");
        freeMarkerGenerator.generate(tableSchema, "common/BusinessException.java.ftl");
        freeMarkerGenerator.generate(tableSchema, "common/Dependency.xml.ftl");
        freeMarkerGenerator.generate(tableSchema, "common/ExceptionCodeEnum.java.ftl");
        freeMarkerGenerator.generate(tableSchema, "common/ExceptionControllerAdvice.java.ftl");
        freeMarkerGenerator.generate(tableSchema, "common/R.java.ftl");

    }

    @Test
    public void testDefaultGen(){
        mySQLDatabaseInfoService.generateByDefault(null);
    }

}
