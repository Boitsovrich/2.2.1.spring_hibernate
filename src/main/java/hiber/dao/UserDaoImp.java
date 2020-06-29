package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   public User getOwnerUser(String model, String series) {
      TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car C where C.model = :model and C.series = :series", Car.class);
      query.setParameter("model", model);
      query.setParameter("series", series);

      int id = query.getSingleResult().getId();

      TypedQuery<User> queryUsr = sessionFactory.getCurrentSession().createQuery("from User U where U.id = :id", User.class);
      queryUsr.setParameter("id", id);
      return queryUsr.getSingleResult();
   }


}
