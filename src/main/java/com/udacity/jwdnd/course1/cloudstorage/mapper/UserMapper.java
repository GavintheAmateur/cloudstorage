package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.domain.AppUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {


    @Select("select * from app_user where username = #{username} ")
    AppUser findUserByUsername(String username);

    @Insert("insert into app_user(username,password,firstName,lastName,salt) values (#{username},#{password},#{firstname},#{lastname},#{salt})")
    @Options(useGeneratedKeys = true, keyProperty =  "id")
    Integer insert(AppUser appUser);

    @Delete("delete from app_user where id = #{id}")
    void delete(Integer id);

}
