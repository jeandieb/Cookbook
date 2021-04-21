package csulb.cecs323.app;
import csulb.cecs323.model.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Date;
import java.util.logging.Logger;




public class App {

    private EntityManager entityManager;

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public App(EntityManager manager)
    {
        this.entityManager = manager;
    }


    public static void main(String[] args){
        LOGGER.fine("Creating user entity");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("starter_unit");
        EntityManager manager = factory.createEntityManager();
        App application = new App(manager);

        LOGGER.fine("Begin of Transaction");
        EntityTransaction tx = manager.getTransaction();

        tx.begin();
        application.userEntity();
        tx.commit();

        LOGGER.fine("End of Transaction");

    }


    private void userEntity(){
        String str = "2020-01-10";
        Date date = Date.valueOf(str);
        User john = new User("John", "Quach", "JQ", "1234", "jq123@gmail.com", date);
        this.entityManager.persist(john);
        LOGGER.info("Persisting object to DB: " + john);
        this.entityManager.flush();
        LOGGER.info("Persisting Object after flush (non- null id) " + john);
    }
}
