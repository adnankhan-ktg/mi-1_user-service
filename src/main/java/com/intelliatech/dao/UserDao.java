package com.intelliatech.dao;

import com.intelliatech.dtos.UserLogin;
import com.intelliatech.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    User findByUserId(int id);
    User findByUsername(String name);

    @Query(value = "SELECT count(*) from user u where u.username= ?1",nativeQuery = true)
    public int getUserStatusByUsername(String username);

//    @Query(value = "SELECT password from user u where u.username = :username",nativeQuery = true)
//    public String getPasswordOfUsername(@Param("username") String username);
//    E e.empNumber = '" + empNumber + "'"

//    @Query(value="delete from author a where a.last_name= :lastName", nativeQuery = true)
//    void deleteAuthorByLastName(@Param("lastName") String lastName);
}
