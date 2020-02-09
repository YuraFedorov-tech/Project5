package ru.javamentor.service;

import ru.javamentor.DAO.CrudDAO;

/*
 *
 *@Data 08.02.2020
 *@autor Fedorov Yuri
 *@project UserAdmin
 *
 */
public interface ServiceCrudDao<T> extends CrudDAO<T> {
//String db="JDBC";
String db="Hibernate";
}
