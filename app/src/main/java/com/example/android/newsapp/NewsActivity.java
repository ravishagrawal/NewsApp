package com.example.android.newsapp;

/**
 * Created by hp on 07-06-2017.
 */

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class NewsActivity  extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<List<News>>,
        SwipeRefreshLayout.OnRefreshListener {

    private NewsAdapter adapter;
    private static int LOADER_ID = 0;
    SwipeRefreshLayout swipeDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        swipeDown = (SwipeRefreshLayout) findViewById(R.id.swipeDown);
        swipeDown.setOnRefreshListener(this);
        swipeDown.setColorSchemeColors(getResources().getColor(R.color.colorAccent));

        if( isNetworkStatusAvailable(getApplicationContext())) {

            ListView listView = (ListView) findViewById(R.id.list_view);
            adapter = new NewsAdapter(this);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                News news = adapter.getItem(i);
                String url = news.mUrl;
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
        }
        else {
            Toast.makeText(NewsActivity.this, "internet is not available", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        swipeDown.setRefreshing(false);
        if (data != null) {
            adapter.setNotifyOnChange(false);
            adapter.clear();
            adapter.setNotifyOnChange(true);
            adapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {

    }

    @Override
    public void onRefresh() {
        getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
    }

    public static boolean isNetworkStatusAvailable (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if(netInfos != null)
                if(netInfos.isConnected())
                    return true;
        }
        return false;
    }

}
