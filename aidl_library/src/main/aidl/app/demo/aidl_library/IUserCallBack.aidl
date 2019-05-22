// IUserCallBack.aidl
package app.demo.aidl_library;

// 导入User的路径
import app.demo.aidl_library.User;

// Declare any non-default types here with import statements

interface IUserCallBack {

    /**
     * 申明为 inout 时需要手动在User.class添加readFromParcel()方法
     */
     void addUser(inout User user);

     User getUser();

}
