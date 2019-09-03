package com.emp.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.emp.entity.User;

public interface UserDao {
	/**ͨ���û��������û�*/
	@Select(" select id,username,password "
			+ " from sh_user where username=#{username} ")
	public User queryByUserName(@Param("username")String username);
	
	/**ͨ���û������Ҹ��û����еĽ�ɫ��������Set������*/
	/*����ֵ������Shiro��ܶ����*/
	@Select(" select r.rolename "
			+ " from sh_user u inner join sh_user_role sur "
			+ " on u.id = sur.user_id inner join sh_role r "
			+ " on sur.role_id = r.id "
			+ " where u.username = #{username} ")
	public Set<String> queryRoles(@Param("username")String username);
	
	/**ͨ���û������Ҹ��û����е�Ȩ�޲�������Set������*/
	@Select(" select distinct p.permission_name "
			+ " from sh_user u inner join sh_user_role sur "
			+ " on u.id = sur.user_id inner join sh_role r "
			+ " on sur.role_id = r.id inner join sh_role_permission srp "
			+ " on r.id = srp.role_id inner join sh_permission p "
			+ " on srp.permission_id = p.id "
			+ " where u.username = #{username} ")
	public Set<String> queryPermissions(@Param("username")String username);
	
	//ע������û�
	@Insert(" insert into sh_user(username,password) values(#{username},#{password}) ")
	public void save(User user);

}
