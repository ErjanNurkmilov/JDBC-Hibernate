package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

//        UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();
//
//        userDao.createUsersTable();
//
//        userDao.saveUser("Скала","Джонсон",(byte) 48);
//        userDao.saveUser("Джеки","Чан",(byte) 80);
//        userDao.saveUser("Люк","Скайвокер",(byte) 36);
//        userDao.saveUser("Артур","Пендрагон",(byte) 24);
//        userDao.saveUser("Тайлер","Дарден",(byte) 30);
        UserService userService = new UserServiceImpl();
//
        userService.createUsersTable();
//
//
        userService.saveUser("Конг", "Кинг", (byte) 120);
        userService.saveUser("Джозеф", "Джостар", (byte) 20);
        userService.saveUser("Питер", "Паркер", (byte) 23);
        userService.saveUser("Алла", "Пугачева", (byte) 50);
        userService.saveUser("Джейсон", "Стетхэм", (byte) 46);


        userService.getAllUsers().forEach(System.out::println);
        //userService.cleanUsersTable();
        userService.removeUserById(4);
        // userService.dropUsersTable();
    }
}
