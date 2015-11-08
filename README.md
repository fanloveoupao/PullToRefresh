# PullToRefresh
下拉刷新 
//核心代码
 private class GetDataTask extends AsyncTask<Void, Void, String> {
        private MainActivity activity;
        public GetDataTask(MainActivity activity) {
            this.activity = activity;
        }
//异步任务
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



//刷新状态栏的设置

 private void setLabel() {
        ILoadingLayout startLabel=pullToRefreshListView.getLoadingLayoutProxy(true,false);
        startLabel.setPullLabel("....下拉刷新....");
        startLabel.setRefreshingLabel("...开始刷新....");
        //下拉到一定距离后，提示释放刷新
        startLabel.setReleaseLabel("....释放刷新.....");
    }
    
    //执行的调用 
    
      pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                // Do work to refresh the list here.
                new GetDataTask(MainActivity.this).execute();

            }
        });
        //一定要有
           xmlns:ptr="http://schemas.android.com/apk/res-auto"
        //布局文件
        
         <com.handmark.pulltorefresh.library.PullToRefreshListView
        android:id="@+id/pull_to_refresh_listview"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        //刷新图标
        ptr:ptrDrawable="@drawable/default_ptr_flip"
        //刷新动画
        ptr:ptrAnimationStyle="flip"
        //背景
        ptr:ptrHeaderBackground="@android:color/transparent"
        //文字
        ptr:ptrHeaderTextColor="#919191"
        />
