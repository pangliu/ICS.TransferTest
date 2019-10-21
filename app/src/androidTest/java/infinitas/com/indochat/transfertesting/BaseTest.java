package infinitas.com.indochat.transfertesting;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.Until;
import android.util.Log;
import infinitas.com.indochat.transfertesting.helper.ConfigHelper;
import org.junit.Before;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

public class BaseTest {

    public UiDevice mDevice;
    public Context mContext;

    @Before
    public void initial() {
//        Log.d("msg", "base initial");
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mContext = InstrumentationRegistry.getContext();
    }

    public boolean openApp(String packageName) {
        // Wait for launcher
        Log.d("msg", "openApp");
        final String launcherPackage = packageName;
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until
                .hasObject(By.pkg(launcherPackage).depth(0)), ConfigHelper.LAUNCH_TIMEOUT);

        // Launcher app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(packageName);

        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Wait for the app to appear
        boolean isWait = mDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), ConfigHelper.LAUNCH_TIMEOUT);
        Log.d("msg", "openApp isWait: " + isWait);
        return isWait;
    }

    public boolean checkNetwork() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        // 判斷有無網路
        if(null!=mNetworkInfo && mNetworkInfo.isConnected()) {
            return true;
        }
        return false;
    }
}
