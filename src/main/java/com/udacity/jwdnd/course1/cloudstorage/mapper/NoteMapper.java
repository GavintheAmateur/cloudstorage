package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.domain.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface NoteMapper {
    @Insert("insert into note (title,description,user_id) values (#{noteTitle},#{noteDescription},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insertNote(Note note);
}
