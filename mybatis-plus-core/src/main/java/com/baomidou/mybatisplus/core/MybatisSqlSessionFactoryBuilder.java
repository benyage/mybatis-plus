package com.baomidou.mybatisplus.core;

import org.apache.ibatis.exceptions.ExceptionFactory;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

/**
 * 重写SqlSessionFactoryBuilder
 *
 * @author nieqiurong 2019/2/23.
 */
public class MybatisSqlSessionFactoryBuilder extends SqlSessionFactoryBuilder {
    
    @Override
    @SuppressWarnings("Duplicates")
    public SqlSessionFactory build(Reader reader, String environment, Properties properties) {
        try {
            //todo 这里替换成自己的了
            MybatisXMLConfigBuilder parser = new MybatisXMLConfigBuilder(reader, environment, properties);
            return build(parser.parse());
        } catch (Exception e) {
            throw ExceptionFactory.wrapException("Error building SqlSession.", e);
        } finally {
            ErrorContext.instance().reset();
            try {
                reader.close();
            } catch (IOException e) {
                // Intentionally ignore. Prefer previous error.
            }
        }
    }
    
    @SuppressWarnings("Duplicates")
    @Override
    public SqlSessionFactory build(InputStream inputStream, String environment, Properties properties) {
        try {
            //todo 这里替换成自己的了
            MybatisXMLConfigBuilder parser = new MybatisXMLConfigBuilder(inputStream, environment, properties);
            return build(parser.parse());
        } catch (Exception e) {
            throw ExceptionFactory.wrapException("Error building SqlSession.", e);
        } finally {
            ErrorContext.instance().reset();
            try {
                inputStream.close();
            } catch (IOException e) {
                // Intentionally ignore. Prefer previous error.
            }
        }
    }
}
