package com.spa.spa.links;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.spa.spa.R;

public class Links extends AppCompatActivity {

  private WebView webView;
  TextView mTextView;
  String s;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_links);
    getSupportActionBar().hide();
    mTextView = findViewById(R.id.textView19);

    webView = (WebView) findViewById(R.id.web);
    webView.loadUrl("https://kabinet.nsk.mts.ru/#!/Account/ServicesState/");
    WebSettings webSettings = webView.getSettings();

    SimpleWebViewClientImpl webViewClient = new SimpleWebViewClientImpl(this);
    webView.setWebViewClient(webViewClient);
    webSettings.setJavaScriptEnabled(true);
    webView.getSettings().setBuiltInZoomControls(true);
    webView.getSettings().setDomStorageEnabled(true);


  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()) {
      this.webView.goBack();
      return true;
    }
    return super.onKeyDown(keyCode, event);
  }

}
