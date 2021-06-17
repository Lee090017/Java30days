package atlishu.com.exer;

import org.junit.Test;

import java.util.*;

/**
 * @author lishustart
 * @create 2021-04-02-20:36
 */
public class DAOTest {
    @Test
    public void test1(){
        DAO<User> dao = new DAO<>();
        dao.save("123",new User(110,25,"lishu"));
        dao.save("456",new User(120,26,"tangyige"));
        dao.save("789",new User(140,24,"pengdaom"));

        System.out.println(dao.list());

        System.out.println(dao.get("456"));

        dao.update("123",new User(119,21,"xiangzijian"));
        System.out.println(dao.list());

        dao.delete("789");
        System.out.println(dao.list());
    }
}

class DAO<T> {

    Map<String,T> map;

    public DAO() {
        map = new HashMap<>();
    }

    public DAO(Map<String, T> map) {
        this.map = map;
    }

    //保存T类型的对象到Map成员变量中
    public void save(String id,T entity){
        map.put(id,entity);
    }

    //从map中获取id对应的对象
    public T get(String id){
        return map.get(id);
    }

    //替换map中key为id的内容，改为entity对象
    public void update(String id,T entity){
        if(map.containsKey(id)) {
            map.put(id, entity);
        }
    }

    //返回map中存放的所有T对象
    public List<T> list(){
        Collection<T> values = map.values();
        List<T> list = new ArrayList<>();
        list.addAll(values);
        return list;
        //错误的写法
//        return (List<T>)values;
    }

    //删除指定id对象
    public void delete(String id){
        map.remove(id);
    }
}

class User{
    private int id;
    private int age;
    private String name;

    public User(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

