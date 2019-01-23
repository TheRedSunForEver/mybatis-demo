package org.x.mybatisdemo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.x.mybatisdemo.mapper.UserMapper;
import org.x.mybatisdemo.model.Country;
import org.x.mybatisdemo.model.SysRole;
import org.x.mybatisdemo.model.SysUser;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Application {
    public static void main(String[] args) throws IOException {
        // 通过Resources工具类将mybatis-config.xml配置文件读入Reader
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        // 通过SqlSessionFactoryBulder建造类使用的Reader创建SqlSessionFactory工厂对象。
        // 在创建SqlSessionFactory对象的过程中，首先解析mybatis-config.xml配置文件，在读取配置文件中的mapper配置后会读取全部的Mapper.xml进行具体方法解析，这些解析完成后，SqlSessionFactory就包含了所有的属性配置和执行sql信息。
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        reader.close();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            demo2(sqlSession);
        } finally {
            if (null != sqlSession) {
                sqlSession.close();
            }
        }

    }

    private static void demo2(SqlSession sqlSession) {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        SysUser user = userMapper.selectById(1L);
        if (user == null) {
            System.out.println("id=1 is null.");
        } else {
            System.out.println(user.getUserName());
        }

        List<SysUser> userList = userMapper.selectAll();
        if (userList == null) {
            System.out.println("userList is null.");
            return;
        }

        for (SysUser u : userList) {
            System.out.println(u.getUserName() + ", " + u.getUserPassword());
        }

        List<SysRole> roleList = userMapper.selectRoleByUserId(1L);
        if (roleList == null) {
            System.out.println("roleList is null.");
            return;
        }

        for (SysRole r : roleList) {
            System.out.println(r.getRoleName() + ", " + r.getUser().getUserName());
        }

    }

    private static void demo1(SqlSession sqlSession) {
        List<Country> countryList = sqlSession.selectList("selectAll");
        for (Country country : countryList) {
            System.out.println("id: " + country.getId() + ", name: " + country.getCountryname() + ", code:" + country.getCountrycode());
        }
    }
}
