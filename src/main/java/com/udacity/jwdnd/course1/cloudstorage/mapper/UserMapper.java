package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {


    @Select("select * from users where username = #{username} ")
    User findUserByUsername(String username);

    @Insert("insert into users(username,password,firstName,lastName,salt) values (#{username},#{password},#{firstname},#{lastname},#{salt})")
    @Options(useGeneratedKeys = true, keyProperty =  "id")
    Integer insert(User user);

    @Delete("delete from users where id = #{id}")
    void delete(Integer id);

}
