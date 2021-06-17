package atlishu.com.java1;

import org.junit.Test;

import java.util.List;

/**
 * @author lishustart
 * @create 2021-04-02-15:58
 */
public class DAOTest {
    @Test
    public void test1(){
        CustomerDAO dao1 = new CustomerDAO();

        dao1.add(new Customer());
        List<Customer> list = dao1.getForList(10);

        StudentsDAO dao2 = new StudentsDAO();
        dao2.add(new Students());
        List<Customer> customers = dao2.copyForList(new Customer[10]);
    }

}
