package infinitas.com.indochat.transfertesting.views;

import android.content.Context;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;
import android.util.Log;
import infinitas.com.indochat.transfertesting.helper.ConfigHelper;

public class TransferView {

    public static final String
            BANK_TRANSFER_OUT_TEXT = "银行账号转账",
            OTHER_BANK_TRANSFER_IN_TEXT = "他行卡转入",
            OTHRE_BANK_TRANSFER_OUT_TEXT = "他行卡转出";
    private UiDevice mDevice;
    private Context mContext;

    public TransferView(UiDevice mDevice) {
        this.mDevice = mDevice;
    }

    public boolean isInTransfer() {
        boolean hasBankOut = mDevice.hasObject(By.textContains(BANK_TRANSFER_OUT_TEXT));
        boolean hasOtherBankIn = mDevice.hasObject(By.textContains(OTHER_BANK_TRANSFER_IN_TEXT));
        boolean hasOtherBankOut = mDevice.hasObject(By.textContains(OTHRE_BANK_TRANSFER_OUT_TEXT));
        UiObject2 btnBankTransferOut = mDevice.wait(Until.findObject(By.textContains(BANK_TRANSFER_OUT_TEXT)), ConfigHelper.API_TIMEOUT);
        Log.d("msg", "isInTransfer: " + btnBankTransferOut);
        if(null != btnBankTransferOut) {
            return true;
        }
        return false;
    }

    public UiObject2 getBankOut() {
        UiObject2 btnBankOut = mDevice.wait(Until.findObject(By.textContains(BANK_TRANSFER_OUT_TEXT)), ConfigHelper.API_TIMEOUT);
        return btnBankOut;
    }
}
