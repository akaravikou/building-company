package com.solvd.buildingcompany.persistence;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisConfig {

    private static final String CONFIG_FILE = "mybatis.xml";
    private static final SqlSessionFactory SQL_SESSION;

    static {
        SQL_SESSION = buildSession();
    }

    private static SqlSessionFactory buildSession() {
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream(CONFIG_FILE);
        } catch (IOException e) {
            throw new RuntimeException("File not available", e);
        }
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        return sqlSessionFactoryBuilder.build(inputStream);
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return SQL_SESSION;
    }
}
