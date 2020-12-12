package com.example.repo.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.repo.Adapter.DataAdapter;
import com.example.repo.R;
import com.example.repo.ViewModel.MainViewModel;
import com.example.repo.model.DataModel;
import com.example.repo.network.ServiceGenerator;
import com.example.repo.utils.MyReceiver;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;
    private MainViewModel mainViewModel;
    DataAdapter dataAdapter;
    List<DataModel> filterLists = new ArrayList<>();
    List<DataModel> searchList = new ArrayList<>();
    private BroadcastReceiver MyReceiver = null;
    ProgressBar progressBar;
    private ServiceGenerator serviceGenerator = ServiceGenerator.getInstance();
    String data ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress_bar);


        MyReceiver = new MyReceiver();
        broadcastIntent();
        swipeRefresh = findViewById(R.id.swipeRefreshLayout);
        recyclerView = findViewById(R.id.recyclerview);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        getUserList();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(true);
                getUserList();

            }
        });
    }

    private void broadcastIntent() {
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(MyReceiver);
    }
    protected void onStop() {
        super.onStop();
        unregisterReceiver(MyReceiver);
    }


    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;

    }

    public void getUserList() {
//        swipeRefresh.setRefreshing(true);
        mainViewModel.getAllData().observe(this, new Observer<List<DataModel>>() {
            @Override
            public void onChanged(@Nullable List<DataModel> dataList) {
                swipeRefresh.setRefreshing(false);
                searchList = dataList;
                setRecyclerView(dataList);
            }
        });

    }

    private void setRecyclerView(List<DataModel> dataList) {
//        progressBar.setIndeterminate(false);
        progressBar.setVisibility(View.GONE);
        dataAdapter = new DataAdapter(dataList);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        DividerItemDecoration myDivider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        myDivider.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        recyclerView.addItemDecoration(myDivider);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(dataAdapter);
        dataAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
searchApi(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String st_filterText) {

                filterLists.clear();
                final List<DataModel> filteredModelList = filter(searchList, st_filterText);


                for (int c = 0; c < searchList.size(); c++) {


                    if ((searchList.get(c).getTitle().toLowerCase().trim()).contains(st_filterText.toLowerCase().trim()))

                        filterLists.add(searchList.get(c));

                }

                dataAdapter.notifyDataSetChanged();
                dataAdapter = new DataAdapter(filterLists);


                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(dataAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void searchApi(String query) {



            if(query.contains("/")){
                String id = query.substring(query.indexOf("/") + 1);
                serviceGenerator.getApi().searchData(id)
                        .enqueue(new Callback<DataModel>() {


                            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                                Log.e(TAG, "log: -----------------------------");
                                Log.d(TAG, "onResponse: " + response.body());

                                if(response.raw().networkResponse() != null){
                                    Log.d(TAG, "onResponse: response is from NETWORK...");
                                }
                                else if(response.raw().cacheResponse() != null
                                        && response.raw().networkResponse() == null){
                                    Log.d(TAG, "onResponse: response is from CACHE...");
                                }

                                List<DataModel> photos = new ArrayList<>();
                                photos.add(response.body());
                                dataAdapter.setData((ArrayList<DataModel>) photos);
                            }

                            @Override
                            public void onFailure(Call<DataModel> call, Throwable t) {
                                Log.e(TAG, "onFailure: ", t);
                                dataAdapter.setData(new ArrayList<DataModel>());
                            }
                        });
            }
            else{
                serviceGenerator.getApi().getList(query).enqueue(new Callback<List<DataModel>>() {
                public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
                    Log.d(TAG, "onResponse: " + response.body());
                    if(response.raw().networkResponse() != null){
                        Log.d(TAG, "onResponse: response is from NETWORK...");
                    }
                    else if(response.raw().cacheResponse() != null
                            && response.raw().networkResponse() == null){
                        Log.d(TAG, "onResponse: response is from CACHE...");
                    }

                    if(response.body() == null){
                        dataAdapter.setData(new ArrayList<DataModel>());
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(dataAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    }
                    else{
                        dataAdapter.setData((ArrayList<DataModel>) response.body());
                    }
                }
                            @Override
                            public void onFailure(Call<List<DataModel>> call, Throwable t) {
                                Log.e(TAG, "onFailure: ", t);
                               dataAdapter .setData(new ArrayList<DataModel>());
                            }
                        });
            }
        }



    private List<DataModel> filter(List<DataModel> searchList, String st_filterText) {
        st_filterText = st_filterText.toLowerCase();
        final List<DataModel> filterModeList = new ArrayList<>();
        for (DataModel modal : searchList) {
            final String text = modal.getTitle().toLowerCase();

            if (text.startsWith(st_filterText)) {



                filterModeList.add(modal);


            }
        }
        return searchList;
    }
}