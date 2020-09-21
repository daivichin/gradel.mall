package com.gradel.service.user.biz;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

public class GeneratorServiceEntity {

    @Test
    public void generateCode() {
        //实体类生成
        String packageName = "com.gradel.gradel.service.user.infrastructure";
        String suffix="entity";
        String name = "";

        generateByTables(packageName, suffix,name,
               new String[]{
                       "auth","role","auth_role"
               }
        );
    }

    private void generateByTables(String packageName, String suffix,String name,String[] tableNames) {
        GlobalConfig config = new GlobalConfig();
        config.setIdType(IdType.INPUT);
        String dbUrl = "jdbc:mysql://localhost:3306/t_order?autoReconnect=true&tinyInt1isBit=false&useUnicode=true&useSSL=false";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                //.setPassword("ihel0rdDlbWySzTx")
                .setPassword("123456")
                .setDriverName("com.mysql.cj.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(false)
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableNames);
        config.setActiveRecord(false)
                .setAuthor("sdeven.chen.dongwei")
                //输出磁盘目录
                .setOutputDir("/Users/sdevne/0_WORKPDCA/18_gradel/0_project/0_processing/gradel.tdast/gradel.service/service.user/src/test/java/com/gradel/service/user/biz/")
                .setEnableCache(false)
                .setBaseResultMap(true)
                .setFileOverride(true);

        config.setMapperName(name);
        config.setXmlName(name);

        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setEntity(suffix)
                                .setMapper(suffix)
                                .setXml(suffix)
                ).execute();
    }
}