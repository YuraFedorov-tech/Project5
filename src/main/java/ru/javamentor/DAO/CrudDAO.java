package ru.javamentor.DAO;
/*
 *
 *@Data 08.02.2020
 *@autor Fedorov Yuri
 *@project UserAdmin
 *
 */

import java.util.List;

public interface CrudDAO <T> {
T find(Long id);
void save(T model);
void delete(Long id);
void update (T model);
List<T> findAll();

T findAtPasswordAndName(String name,String password);

}
