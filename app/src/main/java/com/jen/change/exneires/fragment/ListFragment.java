package com.jen.change.exneires.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jen.change.exneires.R;
import com.jen.change.exneires.adapter.ResAdapter;

/**
 * Created by Administrator on 2015/12/17.
 */
public class ListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ResAdapter resAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        assert view != null;
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.news_list);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        resAdapter = new ResAdapter();
        mRecyclerView.setAdapter(resAdapter);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onRefresh() {

//        mSwipeRefreshLayout.setRefreshing(true);
//
//        mSwipeRefreshLayout.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mSwipeRefreshLayout.setRefreshing(false);
//            }
//        },1000);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        ProgressUtils.showProgress(getActivity());
//        BmobUtils.queryRes(getActivity(), new FindListener<Res>() {
//            @Override
//            public void onSuccess(List<Res> list) {
//                ProgressUtils.hideProgress();
//                Log.e("bmob", "success:");
//                resAdapter.addRes(list);
//            }
//
//            @Override
//            public void onError(int i, String s) {
//                ProgressUtils.hideProgress();
//                Log.e("bmob", "error:" + i + "," + s);
//            }
//        });
    }
}
