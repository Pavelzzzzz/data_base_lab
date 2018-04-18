package com.pavelzzzzz.data_base_lab.service;

import com.pavelzzzzz.data_base_lab.HibernateUtil;
import com.pavelzzzzz.data_base_lab.dto.UserEntity;
import java.util.LinkedList;
import org.hibernate.Query;
import org.hibernate.Session;
import java.util.Iterator;
import java.util.List;

public class UserServise {

  public static List<UserEntity> getAll() {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();

    List <UserEntity> entityList = new LinkedList<UserEntity>();

    Iterator iterator = session.createCriteria(UserEntity.class).list().iterator();
    while (iterator.hasNext()) {
      entityList.add((UserEntity) iterator.next());
    }
      session.getTransaction().commit();
      HibernateUtil.shutdown();
    return entityList;
  }

  public static UserEntity add(String email, String firstName, String lastName) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();

    Query query = session.getNamedQuery("callAddNewUserProcedure");
    query.setString("email", email);
    query.setString("firstName",firstName);
    query.setString("lastName",lastName);
    UserEntity result = (UserEntity) query.list().get(0);

    session.getTransaction().commit();
    HibernateUtil.shutdown();

    return result;
  }
}
