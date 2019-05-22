package app.demo.aidl_library;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.concurrent.CopyOnWriteArrayList;

public class UserService extends Service {

    // 支持并发读写
    private CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mStub;
    }

    IUserCallBack.Stub mStub = new IUserCallBack.Stub() {
        @Override
        public void addUser(User user) throws RemoteException {
            users.clear();
            users.add(user);
        }

        @Override
        public User getUser() throws RemoteException {
            return users.get(0);
        }
    };

}
