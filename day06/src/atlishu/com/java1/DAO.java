package atlishu.com.java1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lishustart
 * @create 2021-04-02-15:49
 *
 * DAO：data(base) access object
 *
 * 当数据库中多个表（多个类）时，不确定操作哪个表,用泛型操作
 */
public class DAO<T> {//表的共性操作的DAO
    //添加一条记录
    public void add(T t){

    }
    //删除一条记录
    public void remove(T t){

    }
    //修改一条记录
    public void update(int index,T t){

    }
    //查询一条记录
    public T getIndex(int index){

        return null;
    }
    //查询多条记录
    public List<T> getForList(int index){
        return null;
    }

    //复制一个表
    public <E> List<E> copyForList(E[] arr){
        List<E> list = new ArrayList<>();
        list = Arrays.asList(arr);
        return list;
    }
}
