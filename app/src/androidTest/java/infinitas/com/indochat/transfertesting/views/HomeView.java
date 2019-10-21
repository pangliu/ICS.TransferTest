package infinitas.com.indochat.transfertesting.views;

import android.content.Context;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;
import android.util.Log;
import infinitas.com.indochat.transfertesting.helper.ConfigHelper;

public class HomeView {

    private static final String
            CREDIT_CARD_ACTIVE_TEXT = "卡片激活",
            HOME_TEXT = "首页",
            ACCOUNT_BALANCE_TEXT = "账户余额",
            TRANSFER_TEXT = "转账",
//            CREDIT_CARD_REGIST_TEXT = "信用卡申请",
//            CREDIT_CARD_APPLY_TEXT = "信用卡激活",
            UPDATE_DIALOG_RES = "com.pingan.paces.ccms:id/close_wifi",
            UPDATE2_DIALOG_RES = "com.pingan.paces.ccms:id/btn_left";

    private UiDevice mDevice;
    private Context mContext;

    public HomeView(UiDevice mDevice) {
        this.mDevice = mDevice;
    }

    public boolean isInHomeView() {
        /**
         * 新增可能在信用卡頁面判斷
         */
        UiObject2 btnCancelUpdate2 = mDevice.wait(Until.findObject(By.res(UPDATE2_DIALOG_RES)), ConfigHelper.API_TIMEOUT);
        Log.d("msg", "btnCancelUpdate2: " + btnCancelUpdate2);
        if(null != btnCancelUpdate2) {
            Log.d("msg", "btnCancelUpdate2: " + btnCancelUpdate2);
            btnCancelUpdate2.clickAndWait(Until.newWindow(), ConfigHelper.CLICK_TIMEOUT);
        }

        UiObject2 btnCancelUpdate3 = mDevice.wait(Until.findObject(By.res(UPDATE2_DIALOG_RES)), ConfigHelper.API_TIMEOUT);
        Log.d("msg", "try again btnCancelUpdate: " + btnCancelUpdate3);
        if(null != btnCancelUpdate3) {
            btnCancelUpdate3.clickAndWait(Until.newWindow(), ConfigHelper.CLICK_TIMEOUT);
        }

        boolean hasCreditCardActive = mDevice.hasObject(By.text(CREDIT_CARD_ACTIVE_TEXT));
        UiObject2 btnCreditCardActive = mDevice.wait(Until.findObject(By.textContains(CREDIT_CARD_ACTIVE_TEXT)), ConfigHelper.CLICK_TIMEOUT);
        Log.d("msg", "信用卡頁面 hasCreditCardActive: " + hasCreditCardActive);
        if(null != btnCreditCardActive) {
            // 目前正在信用卡頁面
            UiObject2 btnHome = mDevice.wait(Until.findObject(By.textContains(HOME_TEXT)), ConfigHelper.CLICK_TIMEOUT);
            btnHome.clickAndWait(Until.newWindow(), ConfigHelper.CLICK_TIMEOUT);
        }



        boolean hasAccountBalance = mDevice.hasObject(By.textContains(ACCOUNT_BALANCE_TEXT));
        boolean hasTransfer = mDevice.hasObject(By.textContains(TRANSFER_TEXT));
//        boolean hasCreditCardRegist = mDevice.hasObject(By.textContains(CREDIT_CARD_REGIST_TEXT));
//        boolean hasCreditCardApply = mDevice.hasObject(By.textContains(CREDIT_CARD_APPLY_TEXT));
        UiObject2 accountBalance = mDevice.wait(Until.findObject(By.textContains(ACCOUNT_BALANCE_TEXT)), ConfigHelper.API_TIMEOUT);
        UiObject2 btnBalance = mDevice.wait(Until.findObject(By.textContains(ACCOUNT_BALANCE_TEXT)), ConfigHelper.API_TIMEOUT);
        UiObject2 btnTransfer = mDevice.wait(Until.findObject(By.textContains(TRANSFER_TEXT)), ConfigHelper.API_TIMEOUT);
//        UiObject2 btnCreditRegist = mDevice.wait(Until.findObject(By.textContains(CREDIT_CARD_REGIST_TEXT)), ConfigHelper.API_TIMEOUT);
        Log.d("msg", "isInHomeView: " + hasAccountBalance + " + " + hasTransfer + " + " + " + ");

        if(null!=accountBalance && hasTransfer) {
            return true;
        }
//        if(null != btnBalance && null != btnTransfer && null != btnCreditRegist) {
//            return true;
//        }
        // 增加更新 dialog 判斷
        UiObject2 btnCancelUpdate = mDevice.findObject(By.res(UPDATE_DIALOG_RES));
        if(null != btnCancelUpdate) {
            Log.d("msg", "btnCancelUpdate: " + btnCancelUpdate);
            btnCancelUpdate.clickAndWait(Until.newWindow(), ConfigHelper.CLICK_TIMEOUT);
            return true;
        }
        UiObject2 btnCancelUpdateNew = mDevice.findObject(By.res(UPDATE2_DIALOG_RES));
        if(null != btnCancelUpdate2) {
            Log.d("msg", "btnCancelUpdate2: " + btnCancelUpdate2);
            btnCancelUpdate2.clickAndWait(Until.newWindow(), ConfigHelper.CLICK_TIMEOUT);
            return true;
        }
        return false;
    }

    public UiObject2 getTransfer() {
        return mDevice.findObject(By.textContains(TRANSFER_TEXT));
    }

    public UiObject2 getBalance() {
        return mDevice.findObject(By.textContains(ACCOUNT_BALANCE_TEXT));
    }
}
