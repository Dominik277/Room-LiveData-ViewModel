package room.database;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen config = new EasySplashScreen(SplashScreenActivity.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(5000)
                .withBackgroundResource(R.drawable.farm2)
                .withHeaderText("Dobrodošli na OPG Dorić,Ovdje možete dobiti osnovne informacije o nama!")
                .withFooterText("Pridrižite nam se u aplikaciji!")
                .withBeforeLogoText("OPG DORIĆ")
                .withAfterLogoText("OPG DORIĆ")
                .withLogo(R.mipmap.ic_launcher_round);

        config.getHeaderTextView().setTextColor(Color.BLACK);
        config.getFooterTextView().setTextColor(Color.BLACK);
        config.getBeforeLogoTextView().setTextColor(Color.BLACK);
        config.getAfterLogoTextView().setTextColor(Color.BLACK);
        config.getHeaderTextView().setGravity(25);
        config.getFooterTextView().setTextSize(25);
        config.getBeforeLogoTextView().setTextSize(30);
        config.getAfterLogoTextView().setTextSize(30);

        View easySplashScreen = config.create();
        setContentView(easySplashScreen);

    }
}
