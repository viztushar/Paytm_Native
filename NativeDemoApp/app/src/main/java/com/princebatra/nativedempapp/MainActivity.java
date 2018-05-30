package com.princebatra.nativedempapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import net.one97.paytm.nativesdk.PaytmSDK;
import net.one97.paytm.nativesdk.Utils.Server;
import net.one97.paytm.nativesdk.app.PaytmSDKCallbackListener;

public class MainActivity extends AppCompatActivity implements PaytmSDKCallbackListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    public void startTransactionClicked(View view) {
        try {
            String orderId = "OrderTest00000078";
            String merchantId = "tAMcoi53684180041762";
            String intiateTransactionResponse = "{\"head\":{\"responseTimestamp\":\"1523967528063\",\"version\":\"v1\",\"clientId\":\"C11\",\"signature\":\"rpF9oTDqrOYPgM8Ew/V1W4Cxy9kFPI7DHDNdMPiV+ABJfCRW5EiNNFKCJj1JVOwYb3T6C9JAL4K51xbVnqnfBnQdsRaB56ayUnYRJ0Dr9HQ=\"},\"body\":{\"resultInfo\":{\"resultStatus\":\"S\",\"resultCode\":\"0000\",\"resultMsg\":\"Success\"},\"txnToken\":\"f48bddbdb80c4f15b3b4afc49583a12c1523967528058\",\"authenticated\":true}}";
            Double amount = 1.0;

            try {
                PaytmSDK paytmSDK = new PaytmSDK(this, intiateTransactionResponse, this, amount,
                        merchantId, orderId, "test", R.drawable.paytm_wallet, Server.PRODUCTION);
                paytmSDK.startTransaction();
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void someUIErrorOccurred(String inErrorMessage) {
        Toast.makeText(this, inErrorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTransactionResponse(Bundle inResponse) {
        Toast.makeText(this, inResponse.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void networkError() {
        Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clientAuthenticationFailed(String inErrorMessage) {
        Toast.makeText(this, inErrorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {
        Toast.makeText(this, inErrorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressedCancelTransaction() {
        Toast.makeText(this, "Back press cancel", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
        Toast.makeText(this, inErrorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void unknownError(String inErrorMessage) {
        Toast.makeText(this, inErrorMessage, Toast.LENGTH_SHORT).show();
    }
}
