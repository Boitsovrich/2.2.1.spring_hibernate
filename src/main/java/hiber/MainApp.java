package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {

   public static void main(String[] args) throws SQLException {

      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      Car julka = new Car("Lada", "1");
      Car lada = new Car("rols royals", "Cullinan");
      Car zpr = new Car("Mers", "SLK");
      Car kmz = new Car("Geleg", "505");

      User user01 = new User("Pasha", "Bikov", "bik@mail.ru");
      User user02 = new User("Sasha", "Boitsov", "user2@mail.ru");
      User user03 = new User("Nikolay", "Zvyagincev", "zvon@mail.ru");
      User user04 = new User("Nikolay", "Chehov", "Cheh@gmail.ru");

      julka.setId(user01.getId());
      lada.setId(user02.getId());
      zpr.setId(user03.getId());
      kmz.setId(user04.getId());

      user01.setCar(julka);
      user02.setCar(lada);
      user03.setCar(zpr);
      user04.setCar(kmz);

      userService.add(user01);
      userService.add(user02);
      userService.add(user03);
      userService.add(user04);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar().toString());
         System.out.println();
      }

      System.out.println(userService.getOwnerUser("Zpr", "4") );

      context.close();
   }
}
