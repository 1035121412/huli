package com.emp.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emp.dao.EmpLazyDao;
import com.emp.entity.Emp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class EmpLazyDaoTest {

	@Resource
	private EmpLazyDao empLazyDao;

	@Test // ���ݱ�������ز�ѯ
	public void testLazy() {
		Emp e = empLazyDao.queryById("e002");
		System.out.println(e.getEname() + "," + e.getDept().getDname() + "," + e.getMgr().getEname());
	}

	@Test // �����ز�ѯ����Ա��
	public void testLazy2() {
		List<Emp> emps = empLazyDao.queryAll();
		for (Emp e : emps) {
			System.out.println(e.getEname() + "," + e.getDept().getDname() + "," + e.getMgr().getEname());
		}
	}

	@Test // ������ģ����ѯԱ��
	public void testLazy3() {
		List<Emp> emps = empLazyDao.queryLikeName("��");
		for (Emp e : emps) {
			System.out.println(e.getEname() + "," + e.getDept().getDname());
		}
	}

	@Test // ���ݲ��ű�Ų�ѯ���������е�Ա��
	public void testLazy4() {
		List<Emp> emps = empLazyDao.queryEmpByDeptno("d001");
		for (Emp e : emps) {
			System.out.println(e.getEname() + "," + e.getDept().getDname() + "," + e.getDept().getLocation());
		}
	}
	
	@Test // ��ѯ�����еľ���
	public void testLazy5() {
		List<Emp> emps = empLazyDao.queryMgrs();
		for (Emp e : emps) {
			System.out.println(e);
		}
	}
}
