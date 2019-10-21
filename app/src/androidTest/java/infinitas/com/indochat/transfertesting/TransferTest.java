package infinitas.com.indochat.transfertesting;


import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.Until;
import android.util.Log;
import infinitas.com.indochat.transfertesting.helper.ConfigHelper;
import infinitas.com.indochat.transfertesting.views.GraphicalPw;
import infinitas.com.indochat.transfertesting.views.HomeView;
import infinitas.com.indochat.transfertesting.views.TransferView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TransferTest extends BaseTest {

    public static final String PACKAGE_NAME = "com.pingan.paces.ccms";


    @Before
    public void initial() {
        super.initial();

    }

    @Test
    public void transferSimulation() {
        boolean isSimulateion = openApp(PACKAGE_NAME);
        Log.d("msg", "isSimulateion: " + isSimulateion);

        HomeView homeView = new HomeView(mDevice);
        GraphicalPw graphicalPw = new GraphicalPw(mDevice);
        TransferView transferView = new TransferView(mDevice);

        if(homeView.isInHomeView()) {
            Log.d("msg", "homeView: true");
            homeView.getTransfer().clickAndWait(Until.newWindow(), ConfigHelper.CLICK_TIMEOUT);
            // 點選轉帳要等待，因為可能出現圖形密碼頁面
            boolean isUpdate = mDevice.waitForWindowUpdate(PACKAGE_NAME, ConfigHelper.API_TIMEOUT);
            Log.d("msg", "等待轉帳頁面");
            // 沒有進入轉帳頁面，可能進入圖形密碼頁面
            if (graphicalPw.isInGraphicalPw()) {
                // 進入圖形密碼頁面
                Log.d("msg", "enter graphical pw");
                graphicalPw.reTryGraphicalPw();
            }
            if(transferView.isInTransfer()) {
                // 圖形頁面解鎖完，進入轉帳頁面
                transferView.getBankOut().clickAndWait(Until.newWindow(), ConfigHelper.CLICK_TIMEOUT);
            }
        }
    }
}
