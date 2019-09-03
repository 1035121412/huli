package com.emp.service;

import java.util.List;

import com.emp.entity.Emp;
import com.emp.utils.PageBean;

public interface EmpService {
	// ��ҳ��ѯ
	public PageBean<Emp> queryByPage(Integer pageNo,Integer pageSize);
	
	//������ҳ��ѯ
	public PageBean<Emp> queryByCondition(Integer pageNo,Integer pageSize,String ename);
	
	//���ݱ�Ų�ѯԱ��
	public Emp queryEmpById(String empno);
	
	//���Ա��
	public void addEmp(Emp emp);
	
	//�޸�Ա��
	public void updateEmp(Emp emp);
	
	//ɾ��Ա��
	public void deleteEmp(String empno);
	
	//��ѯ�����о���
	public List<Emp> queryMgrs();
}
