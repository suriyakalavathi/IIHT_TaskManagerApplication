package com.taskmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("TaskApplicationDao")
public class TaskApplicationDaoImpl implements TaskApplicationDao{

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public void addOrUpdateTask(TaskApplicationPojo taskData) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(taskData);
        }catch (RuntimeException e) {
            session.close();
            throw new RuntimeException("Runtime Error occured in addOrUpdate Task",e);
        }finally{
            tx.commit();
        }
    }

    public List<TaskApplicationPojo> getAllTask() {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        List<TaskApplicationPojo> taskList = null;
        try{
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<TaskApplicationPojo> query = criteriaBuilder.createQuery(TaskApplicationPojo.class);
            Root<TaskApplicationPojo> root = query.from(TaskApplicationPojo.class);
            query.select(root);
            taskList = session.createQuery(query).getResultList();

        }catch (Exception e) {
            throw new RuntimeException("Exception occured in getAllTask",e);
        }
        return taskList;
    }

}
