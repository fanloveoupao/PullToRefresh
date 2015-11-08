package com.example.com.pulltorefresh;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {
    PullToRefreshListView pullToRefreshListView;
    ListView listView;
    ArrayList<BaseBena> data;
    baseAdapter baseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_to_refresh_listview);
        data = new ArrayList<BaseBena>();
        //数据初始化
//        initdatabase();
        refresh();
        //设置刷新的状态问题
        setLabel();

    }

    private void setLabel() {
        ILoadingLayout startLabel=pullToRefreshListView.getLoadingLayoutProxy(true,false);
        startLabel.setPullLabel("....下拉刷新....");
        startLabel.setRefreshingLabel("...开始刷新....");
        //下拉到一定距离后，提示释放刷新
        startLabel.setReleaseLabel("....释放刷新.....");
    }

    //模拟数据
    private void initdatabase() {
        BaseBena baseBena = new BaseBena();
        baseBena.setName("木工");
        baseBena.setPrice("12元/㎡");
        data.add(baseBena);
        baseAdapter = new baseAdapter(data, getApplicationContext());
        pullToRefreshListView.setAdapter(baseAdapter);
    }

    private void refresh() {
        //刷新的监听器
        //可以上下刷新
        //设置模式
//        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
//        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
//            @Override
//            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
//                new GetDataTask().execute();
//                initdatabase();
//                baseAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//                //上拉删除
//                new GetDataTask().execute();
////                initdatabase();
//                data.remove(data.size() - 1);
//                Toast.makeText(getApplicationContext(), "上拉", Toast.LENGTH_LONG).show();
//                baseAdapter.notifyDataSetChanged();
//            }
//        });
        //只能下拉刷新
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                // Do work to refresh the list here.
                new GetDataTask(MainActivity.this).execute();

            }
        });
    }

    private class GetDataTask extends AsyncTask<Void, Void, String> {
        private MainActivity activity;
        public GetDataTask(MainActivity activity) {
            this.activity = activity;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "success";
        }

        @Override
        protected void onPostExecute(String result) {
            // Call onRefreshComplete when the list has been refreshed.
            if (result.equals("success")) {
                activity.initdatabase();
                //刷新适配器
                baseAdapter.notifyDataSetChanged();
                //表示刷新完成
                pullToRefreshListView.onRefreshComplete();
            }
            super.onPostExecute(result);
        }
    }

}
