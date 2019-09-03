package com.emp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import com.emp.entity.Dept;

public interface DeptLazyDao {
	
	//��ѯ���еĲ���
	@Select(" select deptno,dname,location from t_dept ")
	@Results(id = "deptMapper", value = { 
			@Result(id = true, column = "deptno", property = "deptno"),
			@Result(column = "dname", property = "dname"), 
			@Result(column = "location", property = "location") ,
			@Result(column="deptno",property="emps",
			   many=@Many(select="com.emp.dao.EmpDao.queryEmpByDeptno",fetchType=FetchType.LAZY))
			})
	public List<Dept> queryAll();
	
	// ���ݲ��ű�Ų�ѯ����
	@Select(" select deptno,dname,location from t_dept where deptno=#{deptno} ")
	@ResultMap("deptMapper")
	public Dept queryById(@Param("deptno")String deptno);
	
	// ���Ӳ���
	@Insert(" insert into t_dept values(#{deptno},#{dname},#{location}) ")
	public void addDept(Dept dept);

	// ���ݱ��ɾ������
	@Delete(" delete from t_dept where deptno=#{deptno} ")
	public void deleteDept(@Param("deptno") String deptno);

	// ���ݱ���޸Ĳ�����Ϣ
	@Update(" update t_dept set dname=#{dname},location=#{location} where deptno=#{deptno}")
	public void updateDept(Dept dept);
}
