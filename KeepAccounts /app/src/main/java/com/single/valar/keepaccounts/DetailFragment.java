package com.single.valar.keepaccounts;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.single.valar.keepaccounts.Adapter.RecordAdapter;

public class DetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView detailRV;
    private View view;
    private RecordAdapter adapter;
    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(" ", "onCreate: 😄😄");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("  ", "onCreateView:😄😄 ");
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_detail, null,false);
        detailRV=(RecyclerView)view.findViewById(R.id.detailRV);
        adapter=new RecordAdapter(Datatools.getInstance().getRecordList(),getContext().getApplicationContext());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext().getApplicationContext());
        detailRV.setLayoutManager(layoutManager);
            detailRV.setAdapter(adapter);
        return view;
    }


    public void init(){
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext().getApplicationContext());

        //detailRV.setLayoutManager(layoutManager);
        //detailRV.setAdapter(new RecordAdapter(Datatools.getInstance().getRecordList()));
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("  ", "onResume:😂😄😄");
        adapter.notifyDataSetChanged();

    }
}
