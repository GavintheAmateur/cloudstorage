package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.domain.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface NoteMapper {
    @Insert("insert into notes(title,description,created_dt,created_by,modified_dt,modified_by) values (#{title},#{description},#{createdDt},#{modifiedDt}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insertNote(Note note);
}
