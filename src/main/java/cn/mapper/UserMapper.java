package cn.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.pojo.User;

public interface UserMapper {
	@Select(" select * from user where tel = #{tel} and password =#{password} ")
	User login(User user);

	@Insert(" insert into user (tel,password,name) values (#{tel},#{password},#{name}) ")
	int regist(User user);

	int update(User user);

	@Select(" select * from user where tel = #{tel} ")
	User checkUser(@Param("tel") String tel);
}
