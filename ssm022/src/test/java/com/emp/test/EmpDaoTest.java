package com.emp.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emp.dao.EmpDao;
import com.emp.entity.Dept;
import com.emp.entity.Emp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class EmpDaoTest {
	@Resource
	private EmpDao empDao;

	@Test  // ��ѯ����Ա��
	public void testQueryAll() {
		List<Emp> emps = empDao.queryAll();
		for (Emp e : emps) {
			System.out.println(e);
		}
	}

	@Test  // ����Ա����Ų�ѯԱ��
	public void testQueryById(){
		Emp emp = empDao.queryById("e001");
		System.out.println(emp);
	}
	
	@Test  // ��������ģ����ѯ
	public void testQueryLikeName() {
		List<Emp> emps = empDao.queryLikeName("��");
		for (Emp e : emps) {
			System.out.println(e);
		}
	}
	
	@Test  // ���Ա��
	public void testAddEmp(){
		Emp emp=new Emp();
		emp.setEmpno("e109");
		emp.setEname("��ʴ��");
		emp.setEsex("��");
		emp.setEage(500);
		emp.setEsalary(30000F);
		Dept dept = new Dept();
		dept.setDeptno("d001");
		emp.setDept(dept);
		Emp mgr=new Emp();
		mgr.setEmpno("e001");
		emp.setMgr(mgr);
		empDao.addEmp(emp);
		System.out.println("OK");
	}
	
	@Test  // ɾ��Ա��
	public void testDelete(){
		empDao.deleteEmp("e109");
		System.out.println("OK");
	}
	
	@Test  // �޸�Ա��
	public void testUpdateEmp(){
		Emp e = empDao.queryById("e109");
		System.out.println(e);
		e.setEsalary(60000F);
		e.getDept().setDeptno("d002");
		e.getMgr().setEmpno("e004");
		empDao.updateEmp(e);
		System.out.println(empDao.queryById("e109"));
	}
	
	@Test  // ���ݲ��ű�Ų�ѯ���������е�Ա��
	public void testQueryByDeptnoEmp(){
		List<Emp> emps = empDao.queryEmpByDeptno("d001");
		for(Emp e:emps){
			System.out.println(e);
		}
	}
	
	@Test  // ��ѯ�����еľ���
	public void testQueryMgr(){
		List<Emp> mgrs = empDao.queryMgrs();
		for(Emp e:mgrs){
			System.out.println(e);
		}
	}
	
}
