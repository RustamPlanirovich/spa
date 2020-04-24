package com.spa.spa.links;

import android.app.Activity;
import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SimpleWebViewClientImpl extends WebViewClient {

  private Activity activity = null;

  public SimpleWebViewClientImpl(Activity activity) {
    this.activity = activity;
  }

  @Override
  public boolean shouldOverrideUrlLoading(WebView webView, String url) {
    // все ссылки, в которых содержится 'javadevblog.com'
    // будут открываться внутри приложения
//    if (url.contains("javadevblog.com")) {
//      return false;
//    }
    // все остальные ссылки будут спрашивать какой браузер открывать
//    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//    activity.startActivity(intent);
    return false;
  }
// Подставка пароля для автовхода
  @Override
  public void onReceivedHttpAuthRequest(WebView view,
                                        HttpAuthHandler handler, String host, String realm) {
    handler.proceed("6-091-0399826", "Israr1990");
  }

}