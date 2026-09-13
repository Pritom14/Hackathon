package com.example.nishant.local_updater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final String URL_TO_LOAD = "https://c451f437.ngrok.io/table.php";
    private static final String LOCAL_RESOURCE = "https://c451f437.ngrok.io/table.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: activity starting");
        setContentView(R.layout.activity_main);
        WebView web_browser=(WebView)findViewById(R.id.web_browser);
        loadResource(web_browser, URL_TO_LOAD);
        Log.d(TAG, "onCreate: activity started successfully");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: activity becoming visible");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: activity in foreground");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: activity losing foreground");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: activity no longer visible");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: activity being destroyed");
    }

    private void loadResource(WebView web_browser, String resource) {
        Log.i(TAG, "loadResource: preparing to load resource: " + resource);

        Log.d(TAG, "loadResource: enabling JavaScript");
        web_browser.getSettings().setJavaScriptEnabled(true);

        Log.d(TAG, "loadResource: enabling DOM storage (local storage)");
        web_browser.getSettings().setDomStorageEnabled(true);

        web_browser.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.i(TAG, "Network: page load started for " + url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.i(TAG, "Network: page load finished for " + url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Log.e(TAG, "Network: error loading " + failingUrl + " code=" + errorCode + " description=" + description);
            }
        });

        Log.i(TAG, "loadResource: issuing network request to load URL: " + resource);
        web_browser.loadUrl(resource);
        Log.d(TAG, "loadResource: loadUrl call dispatched");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        Log.d(TAG, "onCreateOptionsMenu: inflating options menu");
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        Log.d(TAG, "onOptionsItemSelected: menu item selected, id=" + itemThatWasClickedId);
        WebView web_browser=(WebView)findViewById(R.id.web_browser);
        if (itemThatWasClickedId == R.id.action_refresh) {
             Log.i(TAG, "onOptionsItemSelected: refresh action triggered, reloading WebView");
             web_browser.reload();
             Log.d(TAG, "onOptionsItemSelected: reload dispatched");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
