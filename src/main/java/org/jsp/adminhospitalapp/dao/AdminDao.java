package org.jsp.adminhospitalapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.adminhospitalapp.dto.Admin;

public class AdminDao {
	private EntityManager manager=Persistence.createEntityManagerFactory("dev").createEntityManager();
	
	public Admin saveAdmin(Admin admin) {
		EntityTransaction t=manager.getTransaction();
		manager.persist(admin);
		t.begin();
		t.commit();
		return admin;
	}
	
	public Admin updateAdmin(Admin admin) {
		Admin dbAdmin=findAdminById(admin.getId());
		if(dbAdmin!=null) {
			if(admin.getName()!=null) {
				dbAdmin.setName(admin.getName());	
			}
			if(admin.getEmail()!=null) {
				dbAdmin.setEmail(admin.getEmail());
			}
			if(admin.getPassword()!=null) {
				dbAdmin.setPassword(admin.getPassword());
			}
			if(admin.getPhone()!=0l) {
				dbAdmin.setPhone(admin.getPhone());
			}
			EntityTransaction transaction =manager.getTransaction();
			transaction.begin();
			transaction.commit();
			return dbAdmin;
		}
		return null;
	}
	
	public Admin findAdminById(int id) {
		return manager.find(Admin.class, id);
	}
	
	public Admin verifyAdmin(long phone,String password) {
		Query q=manager.createQuery("select a from Admin a where a.phone=?1 and a.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return (Admin)q.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}
	
	public Admin verifyAdmin(String email, String password) {
		Query q = manager.createQuery("select a from Admin a where a.email=?1 and a.password=?2");
				q.setParameter(1, email);
				q.setParameter(2, password);
				try {
					return (Admin) q.getSingleResult();
				} catch (NoResultException e) {
					return null;
				}
			}
}
