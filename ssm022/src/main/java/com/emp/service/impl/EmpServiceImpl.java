package com.emp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.emp.dao.EmpDao;
import com.emp.entity.Emp;
import com.emp.service.EmpService;
import com.emp.utils.PageBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class EmpServiceImpl implements EmpService {
	//ע��Ա��Dao
	@Resource
	private EmpDao empDao;

	// ��ҳ��ѯ ---���÷�ҳ����
	public PageBean<Emp> queryByPage(Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo,pageSize);
		List<Emp> list=empDao.queryAll();
		PageInfo<Emp> pageInfo = new PageInfo<Emp>(list);
		//����PageBean����
		PageBean<Emp> pageBean=new PageBean<Emp>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setList(pageInfo.getList());
		pageBean.setTotalCount((int)(pageInfo.getTotal()));
		return pageBean;
	}

	//������ҳ��ѯ
	public PageBean<Emp> queryByCondition(Integer pageNo, Integer pageSize, String ename) {
		PageHelper.startPage(pageNo,pageSize);
		List<Emp> list = empDao.queryLikeName(ename);
		PageInfo<Emp> pageInfo = new PageInfo<Emp>(list);
		//����PageBean����
		PageBean<Emp> pageBean=new PageBean<Emp>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		pageBean.setList(pageInfo.getList());
		pageBean.setTotalCount((int)(pageInfo.getTotal()));
		return pageBean;
	}

	// ���ݱ�Ų�ѯ
	public Emp queryEmpById(String empno) {
		Emp emp = empDao.queryById(empno);
		return emp;
	}

	// ���Ա��
	public void addEmp(Emp emp) {
		empDao.addEmp(emp);
	}

	// �޸�Ա��
	public void updateEmp(Emp emp) {
		empDao.updateEmp(emp);
	}

	// ɾ��Ա��
	public void deleteEmp(String empno) {
		empDao.deleteEmp(empno);
	}

	// ��ѯ���еľ���
	public List<Emp> queryMgrs() {
		List<Emp> mgrs = empDao.queryMgrs();
		return mgrs;
	}

}
