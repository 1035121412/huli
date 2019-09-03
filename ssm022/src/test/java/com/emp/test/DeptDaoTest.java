package com.emp.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emp.dao.DeptDao;
import com.emp.entity.Dept;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class DeptDaoTest {
	@Resource
	private DeptDao deptDao;

	@Test // ��ѯ���в���
	public void testQueryAll() {
		List<Dept> dept = deptDao.queryAll();
		for (Dept d : dept) {
			System.out.println(d);
		}
	}

	@Test // ���ݱ�Ų�ѯ������Ϣ
	public void testQueryById() {
		Dept dept = deptDao.queryById("d005");
		System.out.println(dept);
	}

	@Test // ���Ӳ���
	public void testAddDept() {
		Dept dept = new Dept();
		dept.setDeptno("d005");
		dept.setDname("���ϲ�");
		dept.setLocation("���޵�");
		deptDao.addDept(dept);
		System.out.println("OK");
	}

	@Test // ɾ������
	public void testDeleteDept() {
		deptDao.deleteDept("d005");
		System.out.println("OK");
	}

	@Test // ���ݱ���޸Ĳ�����Ϣ
	public void testUpdateDept() {
		Dept dept = deptDao.queryById("d005");
		dept.setDname("�з���");
		dept.setLocation("�ձ�");
		deptDao.updateDept(dept);
		System.out.println("OK");
	}
}
