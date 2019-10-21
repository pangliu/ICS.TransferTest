package infinitas.com.indochat.transfertesting.views;

import android.content.Context;
import android.graphics.Point;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;
import android.util.Log;
import infinitas.com.indochat.transfertesting.helper.ConfigHelper;

import static infinitas.com.indochat.transfertesting.TransferTest.PACKAGE_NAME;

public class GraphicalPw {

    public static final String
            SET_GRAPHICAL = "设置手势密码",
            SET_AGAIN = "请再次绘制手势密码图形",
            OTHER_LOGIN_TEXT = "其他方式登录",
            NETWORK_BUSY_TEXT = "网络繁忙";
    private UiDevice mDevice;
    private Context mContext;

    public GraphicalPw(UiDevice mDevice) {
        this.mDevice = mDevice;
    }

    public boolean isInGraphicalPw() {
        boolean isGraph = mDevice.hasObject(By.textContains(OTHER_LOGIN_TEXT));
        UiObject2 tvOtherLogin = mDevice.wait(Until.findObject(By.textContains(OTHER_LOGIN_TEXT)), ConfigHelper.API_TIMEOUT);
//        if(isGraph) {
//            return true;
//        }
        if(null != tvOtherLogin) {
            return true;
        }
        return false;
    }

    private void enterGraphicalPw() {
        Log.d("msg", "enterGraphicalPw");
        int height = mDevice.getDisplayHeight();
        int width = mDevice.getDisplayWidth();
//        Log.d("msg", "height: " + height);
//        Log.d("msg", "width: " + width);
        // x 軸起點
        int x = width/4;
        // y 軸起點
        int y = (height/2) - 50;
        Point point1 = new Point(x, y);
        Point point2 = new Point(x, y + x);
        Point point3 = new Point(x, y + 2*x);
        Point point4 = new Point(2*x, y + 2*x);
        Point point5 = new Point(3*x, y + 2*x);
        Point[] p = {point1, point2, point3, point4, point5};
        mDevice.swipe(p, 20);
        boolean isUpdate = mDevice.waitForWindowUpdate(PACKAGE_NAME, ConfigHelper.WEB_VIEW_TIME);
        Log.d("msg", "graphicalPw isUpdate: " + isUpdate);
    }

    private void initalGraphicalPw() {
        Log.d("msg", "initalGraphicalPw");
        int height = mDevice.getDisplayHeight();
        int width = mDevice.getDisplayWidth();
//        Log.d("msg", "height: " + height);
//        Log.d("msg", "width: " + width);
        // x 軸起點
        int x = width/4;
        // y 軸起點
        int y = (height/2) - 100;
        Point point1 = new Point(x, y);
        Point point2 = new Point(x, y + x);
        Point point3 = new Point(x, y + 2*x);
        Point point4 = new Point(2*x, y + 2*x);
        Point point5 = new Point(3*x, y + 2*x);
        Point[] p = {point1, point2, point3, point4, point5};
        mDevice.swipe(p, 20);
        boolean isUpdate = mDevice.waitForWindowUpdate(PACKAGE_NAME, ConfigHelper.WEB_VIEW_TIME);
        Log.d("msg", "graphicalPw isUpdate: " + isUpdate);
    }

    public void reTryGraphicalPw() {
        enterGraphicalPw();
        for(int i=0; i<3; i++) {
            UiObject2 networkBusyText = mDevice.wait(Until.findObject(By.textContains(NETWORK_BUSY_TEXT)), ConfigHelper.API_TIMEOUT);
            if (null != networkBusyText) {
                enterGraphicalPw();
            } else {
                return;
            }
        }
    }

    public boolean isSetGraphicalPw() {
        UiObject2 tvTitle = mDevice.wait(Until.findObject(By.text(SET_GRAPHICAL)), ConfigHelper.API_TIMEOUT);
        Log.d("msg", "isSetGraphicalPw: " + tvTitle);
        if(null != tvTitle) {
            return true;
        }
        return false;
    }

    public void setGraphicalPw() {
        initalGraphicalPw();
        UiObject2 tvTryAgain = mDevice.wait(Until.findObject(By.text(SET_AGAIN)), ConfigHelper.API_TIMEOUT);
        if(null != tvTryAgain) {
            initalGraphicalPw();
        } else {
            Log.d("msg", "setGraphicalPw failed");
        }

    }
}
