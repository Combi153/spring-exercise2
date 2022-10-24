package com.dao;
import com.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoFactory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;
    private UserDao userDao;
    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    void setUp() {
        this.userDao = context.getBean("awsUserDao", UserDao.class);
        this.user1 = new User("1", "Han", "1234");
        this.user2 = new User("2", "Lee", "1423");
        this.user3 = new User("3", "Kim", "1324");
    }

    @Test
    void addAndGet() throws SQLException, ClassNotFoundException {

        userDao.deleteAll();
        assertEquals(0, userDao.getCount());

        userDao.add(user1);
        assertEquals(1, userDao.getCount());

        User selectedUser = userDao.get(user1.getId());
        assertEquals(user1.getName(), selectedUser.getName());
        assertEquals(user1.getPassword(), selectedUser.getPassword());
    }

    @Test
    void count() throws SQLException, ClassNotFoundException {

        userDao.deleteAll();
        assertEquals(0, userDao.getCount());
        userDao.add(user1);
        assertEquals(1, userDao.getCount());
        userDao.add(user2);
        assertEquals(2, userDao.getCount());
        userDao.add(user3);
        assertEquals(3, userDao.getCount());
    }
}
