package com.example.testonetrust;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.onetrust.otpublishers.headless.Public.DataModel.OTProfileSyncParams;
import com.onetrust.otpublishers.headless.Public.DataModel.OTSdkParams;
import com.onetrust.otpublishers.headless.Public.OTBannerHeightRatio;
import com.onetrust.otpublishers.headless.Public.OTCallback;
import com.onetrust.otpublishers.headless.Public.OTPublishersHeadlessSDK;
import com.onetrust.otpublishers.headless.Public.Response.OTResponse;

public class MainActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
        OTSdkParams sdkParams = OTSdkParams.SdkParamsBuilder.newInstance()
                .setOTCountryCode("US")
                .setOTRegionCode("CA")
                .shouldCreateProfile("false")
                .build();

        OTPublishersHeadlessSDK otPublishersHeadlessSDK = new OTPublishersHeadlessSDK(this);
        otPublishersHeadlessSDK.startSDK("cdn.cookielaw.org", "a388ab5c-75ce-4027-b218-983c3ff896a6-test", "tr", sdkParams, new OTCallback() {
            @Override
            public void onSuccess(@NonNull OTResponse otSuccessResponse) {
                // do logic to render UI
                String otData = otSuccessResponse.getResponseData();
            }

            @Override
            public void onFailure(@NonNull OTResponse otErrorResponse) {
                // Use below method to get errorCode and errorMessage.
                int errorCode = otErrorResponse.getResponseCode();
                String errorDetails = otErrorResponse.getResponseMessage();
                // Use toString() to log complete OT response
                Log.i("ONE_TRUST--->", otErrorResponse.toString());
            }
        });

        new OTPublishersHeadlessSDK(this).showPreferenceCenterUI(MainActivity.this);


    }
}
