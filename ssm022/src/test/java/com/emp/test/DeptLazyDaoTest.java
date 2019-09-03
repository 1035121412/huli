package com.emp.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emp.dao.DeptLazyDao;
import com.emp.entity.Dept;
import com.emp.entity.Emp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class DeptLazyDaoTest{
	@Resource
	private DeptLazyDao deptLazyDao;
	
	@Test //��ѯ���еĲ���
	public void testQueryAll() {
		List<Dept> depts = deptLazyDao.queryAll();
		for(Dept d:depts){
			System.out.println("~~~~~~~~~~~~~~~~~~~~~");
			System.out.println(d.getDname());
			List<Emp> es=d.getEmps();
			for(Emp emp:es){
				System.out.println(emp.getEname());
			}
			System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		}
	}

	@Test // ���ݲ��ű�Ų�ѯ����
	public void testQueryById(){
		Dept dept = deptLazyDao.queryById("d001");
		System.out.println(dept.getDname());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		List<Emp> es=dept.getEmps();
		for(Emp emp:es){
			System.out.println(emp.getEname());
		}
	}
}
