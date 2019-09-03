package com.emp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import com.emp.entity.Emp;

/**
 * 
 * @author Administrator
 * ʹ�������ط�ʽ��ѯ
 */
public interface EmpLazyDao {
	//mybatis�������� (��ѯ)
	//ʹ�������ط�ʽ��ѯ
	//�����صľ����ԣ������ǰ��̨����Ŀ���ģʽ
	//�����ݷ�װ��JSON���󷵻ظ�ǰ����������Ͳ��ܽ���������
	
	//����Ա����Ų�ѯԱ��
	@Select(" select empno,ename,esex,eage,esalary,deptno,mgrno from t_emp where empno=#{empno}")
	@Results(id="empMapper",value={
		 @Result(id=true,column="empno",property="empno"),
		 @Result(column="ename",property="ename"),
		 @Result(column="esex",property="esex"),
		 @Result(column="eage",property="eage"),
		 @Result(column="esalary",property="esalary"),
		 @Result(column="deptno",property="dept",
		    one=@One(select="com.emp.dao.DeptDao.queryById",fetchType=FetchType.LAZY)),
		 @Result(column="mgrno",property="mgr",
		    one=@One(select="com.emp.dao.EmpLazyDao.queryById",fetchType=FetchType.LAZY))
	})
	public Emp queryById(@Param("empno")String empno);
	
	//��ѯ����Ա��
	@Select(" select empno,ename,esex,eage,esalary,deptno,mgrno from t_emp")
	@ResultMap("empMapper")
	public List<Emp> queryAll();
	
	//��������ģ����ѯ
	@Select("select empno,ename,esex,eage,esalary,deptno,mgrno from t_emp where INSTR(ename,#{ename})")
	@ResultMap("empMapper")
	public List<Emp> queryLikeName(@Param("ename")String ename);
	
	// ���Ա��
	@Insert("insert into t_emp values(#{empno},#{ename},#{esex},#{eage},#{esalary},"
			+ "#{dept.deptno},#{mgr.empno})")
	public void addEmp(Emp emp);
	
	// �޸�Ա��
	@Update(" update t_emp set ename=#{ename},esex=#{esex},eage=#{eage}, "
		+ " esalary=#{esalary},deptno=#{dept.deptno},mgrno=#{mgr.empno} "
		+ " where empno=#{empno}")
	public void updateEmp(Emp emp);
	
	// ���ݱ��ɾ��Ա��
	@Delete(" delete from t_emp where empno=#{empno} ")
	public void deleteEmp(@Param("empno")String empno);
	
	//��ѯ�����еľ���
	@Select(" SELECT DISTINCT m.empno,m.ename "
			+ " FROM t_emp m inner JOIN "
			+ " t_emp e on m.empno = e.mgrno "
			+ " where m.deptno IS NOT NULL ")
	public List<Emp> queryMgrs();
	
	// ���ݲ��ű�Ų�ѯ���������е�Ա��
	@Select(" select empno,ename,esex,eage,esalary,deptno,mgrno from t_emp where deptno=#{deptno} ")
	@ResultMap(value="empMapper")
	public List<Emp> queryEmpByDeptno(@Param("deptno")String deptno);
}
