package com.qiaoyn.hashmap;

/**
 * @author yn.qiao
 * @version 1.0
 * @ClassName Test02
 * @create 2022-01-03 11:55
 **/
public class Test02 {

    public static void main(String[] args) {

        User user = new User("tom",12);
        User user2 = new User("tom",12);
        /**
         * 如果只重写equals方法而不重写hashcode方法，那么user.equal(user2)返回为true,
         * 而user.hashCode()与user2.hashCode()不一致，与两个对象的equals比较内容值相等，
         * hashcode值是一定相等相违背。
         */
        System.out.println(user.equals(user2));
        System.out.println(user.hashCode());
        System.out.println(user2.hashCode());

    }
}

class User{
    private String name;
    private Integer id;

    public User(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public   boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        User user = (User) obj;
        return user.id.equals(id) && user.name.equals(name);
    }

    public int hashCode(){
        return id.hashCode() + name.hashCode();
    }


}
