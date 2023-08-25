package com.GestorUsuarios.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;

import com.GestorUsuarios.pojo.UserData;


public interface UserMapper {

    @Results(id = "AllActiveUser",
    		value = { 
    		@Result(property = "idUser", column = "id_user"),
    		@Result(property = "name", column = "user_name"),
            @Result(property = "male", column = "user_male"),
            @Result(property = "email", column = "user_email"),
            @Result(property = "status", column = "user_status"),
            @Result(property = "profilePicture", column = "user_profile_pic")
    		})
    
    @Select("select * from user_data_global")
    List<UserData> getAllActiveUser();
    
    @Insert("INSERT INTO user_data_global (user_name, user_male, user_status, user_profile_pic,user_email) VALUES (#{nombre},#{male}, #{status},#{profilePhoto}, #{email})")
    void addNewUSer(String nombre,String male,boolean status,String profilePhoto,String email);
    
    @Delete("DELETE FROM user_data_global WHERE id_user = #{idUser}")
    void deleteUser(int idUser);
    
    @ResultMap("AllActiveUser")
    @Select("select * from user_data_global where id_user = #{idUser}")
    UserData getActiveUserId(int idUser);
    
    @Update("UPDATE user_data_global SET user_name = #{name}, user_male = #{male}, user_status = #{status}, user_profile_pic = #{profilePicture}, user_email = #{email} WHERE id_user = #{idUser}")
    void updateActiveUser(UserData userData);
    
    @ResultMap("AllActiveUser")
    @Select("select * from user_data_global where user_email = #{email}")
    UserData loginUser(String email);

}
