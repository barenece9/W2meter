package com.app.w2meter.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.w2meter.MainActivity;
import com.app.w2meter.R;
import com.rilixtech.Country;
import com.rilixtech.CountryCodePicker;

import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends AppCompatActivity {
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    Button tv_countinue;
    EditText et_number;
    CountryCodePicker ccp;
    ProgressBar pbci_progressBar;

    public static String usrCountryCode, usrMobileNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImageButton ib_back=findViewById(R.id.ib_back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SplashActivity.class));
                finish();
            }
        });
        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        et_number = (EditText)findViewById(R.id.et_number);
        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected(Country selectedCountry) {
                usrCountryCode = ccp.getFullNumberWithPlus();
                System.out.println("usrCountryCode "+usrCountryCode);
                //Toast.makeText(SignupActivity.this, "Updated " + selectedCountry.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        /*et_number.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            startActivity(new Intent(LoginActivity.this,OtpActivity.class));
                            finish();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });*/

        et_number.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    startActivity(new Intent(LoginActivity.this,OtpActivity.class));
                    finish();
                    return true;
                }
                return true;
            }

        });

        //et_number.setOnEditorActionListener(new );

        if (checkAndRequestPermissions()) {
            // carry on the normal flow, as the case of  permissions  granted.
        }
        GetCountryZipCode();
    }
    public String GetCountryZipCode(){
        String CountryID="";
        String CountryIDNet="";
        String CountryZipCode="1";

        TelephonyManager manager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        int simState = manager.getSimState();
        switch (simState) {

            case (TelephonyManager.SIM_STATE_ABSENT): break;
            case (TelephonyManager.SIM_STATE_NETWORK_LOCKED): break;
            case (TelephonyManager.SIM_STATE_PIN_REQUIRED): break;
            case (TelephonyManager.SIM_STATE_PUK_REQUIRED): break;
            case (TelephonyManager.SIM_STATE_UNKNOWN): break;
            case (TelephonyManager.SIM_STATE_READY): {
                //getNetworkCountryIso
                CountryID= manager.getSimCountryIso().toUpperCase();
                ccp.setDefaultCountryUsingNameCode(CountryID);
                ccp.resetToDefaultCountry();
                CountryIDNet= manager.getNetworkCountryIso().toUpperCase();
                System.out.println("CountryID "+CountryID+"\n");
                System.out.println("CountryIDNet "+CountryIDNet+"\n");
                String[] rl=this.getResources().getStringArray(R.array.CountryCodes);
                for(int i=0;i<rl.length;i++){
                    String[] g=rl[i].split(",");
                    if(g[1].trim().equals(CountryID.trim())){
                        CountryZipCode=g[0];
                        break;
                    }
                }
            }
        }
        return CountryZipCode;
    }
    private  boolean checkAndRequestPermissions() {
        int permissionSendMessage = ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);

        int receiveSMS = ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS);

        int readSMS = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_SMS);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (receiveSMS != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.RECEIVE_MMS);
        }
        if (readSMS != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_SMS);
        }
        if (permissionSendMessage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.SEND_SMS);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }
}
