package com.shohrab.webviewtest;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


/**
 * In this tutorial I will extract this particular html code from wikipedia page
 *
 * <html>
 * <body>
 * <div id = "mp-tfy">
 * <p>
 * The <i><b><a href="/wiki/Requiem_(Reger)" title="Requiem (Reger)">Requiem</a></b>
 * </i> by <a href="/wiki/Max_Reger" title="Max Reger">Max Reger</a> is a
 * <a href="/wiki/20th-century_classical_music#Romantic_style" title="20th-century classical music">
 * late Romantic</a> setting of <a href="/wiki/Christian_Friedrich_Hebbel" title="Christian
 * Friedrich Hebbel">Friedrich Hebbel's</a> poem "Requiem" for <a href="/wiki/Alto"
 * title="Alto">alto</a> or <a href="/wiki/Baritone" title="Baritone">baritone</a> solo,
 * chorus and orchestra. The text begins with a plea not to forget the dead. Composed in 1915,
 * Reger dedicated it "to the memory of the German heroes" who died in the <a href="/wiki/World_War_I"
 * title="World War I">World War</a>. He had composed Requiem settings before: in
 * 1912 a <a href="/wiki/Motet" title="Motet">motet</a> for <a href="/wiki/M%C3%A4nnerchor"
 * class="mw-redirect" title="Männerchor">male chorus</a>, set to the same poem, and in 1914 an
 * unfinished setting of the Latin <a href="/wiki/Requiem" title="Requiem">Requiem</a>, in memory of
 * <a href="/wiki/World_War_I_casualties" title="World War I casualties">victims of the war</a>.
 * The 1915 <i>Requiem</i>, Reger's last completed work for chorus and orchestra, was published by
 * <a href="/wiki/Fritz_Simrock" title="Fritz Simrock">N.&nbsp;Simrock</a> in 1916, after the
 * composer's death. It was paired with another choral composition, <i><span xml:lang="de"
 * lang="de"><a href="/wiki/Der_Einsiedler" title="Der Einsiedler">Der Einsiedler</a></span></i>
 * (<i>The Hermit</i>), set to a poem by <a href="/wiki/Joseph_Freiherr_von_Eichendorff"
 * title="Joseph Freiherr von Eichendorff">Joseph von Eichendorff</a>, titled <i>
 * <span xml:lang="de" lang="de">Zwei Gesänge für gemischten Chor mit Orchester</span>
 * </i> (Two songs for mixed chorus with orchestra), <span class="nowrap">
 * <a href="/wiki/Opus_number" title="Opus number">Op</a>. 144</span>.
 * Both works were first performed in <a href="/wiki/Heidelberg" title="Heidelberg">
 * Heidelberg</a> on 16<span class="nowrap">&nbsp;</span>July 1916 as part
 * of a memorial concert for Reger, conducted by <a href="/wiki/Philipp_Wolfrum"
 * title="Philipp Wolfrum">Philipp Wolfrum</a>. Reger thought that <i>The Hermit</i>
 * and the <i>Requiem</i> were "among the most beautiful things" he had ever written.
 * (<a href="/wiki/Requiem_(Reger)" title="Requiem (Reger)"><b>Full&nbsp;article...</b></a>)
 * </p>
 * </div>
 * </body>
 * </html>
 */
public class MainActivity extends AppCompatActivity implements MainActivityContract {

    private WebView mWebView;
    private ProgressBar mPb;
    private String mHtmlString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.activity_main_webView);
        mPb = (ProgressBar) findViewById(R.id.activity_main_pb);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        // Enable responsive layout
        mWebView.getSettings().setUseWideViewPort(true);
        // Zoom out if the content width is greater than the width of the veiwport
        mWebView.getSettings().setLoadWithOverviewMode(true);

        mWebView.setWebViewClient(new MyWebViewClient(this));
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);


        new MyAsyncTask().execute();


    }

    class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            extractDataFromURL();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //load extracted data into webview
            mWebView.loadData("<html>" + mHtmlString + "</html>", "text/html; charset=utf-8", "UTF-8");

            super.onPostExecute(aVoid);
        }
    }


    //extract some data from wikipedia website
    //Jsoup API is used extract the data
    private void extractDataFromURL() {
        try {
            Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
            Element content = doc.getElementById("mp-tfa"); //this is one of the divisions id (<div id=mp-tfa>...</div>)
            Elements links = content.getElementsByTag("p"); //this is a para inside divisino <div id=mp-tfa><p>...</p>/div>)
            mHtmlString = links.toString();//now mHtmlString contains the text inside the para
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) { //if going back is possible in webview then go back within webview
            mWebView.goBack();
        } else {
            super.onBackPressed(); //otherwise go back within the app
        }
    }

    @Override
    public void showProgressBar(int visibilityMode) {

        mPb.setVisibility(visibilityMode);

    }
}
