package com.example.brayany.airbnb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.brayany.airbnb.interfaces.OnClickItem;
import com.example.brayany.airbnb.interfaces.RetrofitCallback;
import com.example.brayany.airbnb.recycler.MyRecyclerViewAdapter;
import com.example.brayany.airbnb.retrofit.AirBnBObject;
import com.example.brayany.airbnb.retrofit.RetrofitHelper;
import com.example.brayany.airbnb.storage.database;


public class Fragment_list extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView myRecycler;
    MyRecyclerViewAdapter adapter;
    private OnClickItem listener;

    // TODO: Rename and change types of parameters
    private Boolean mParam1;

    public Fragment_list() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment_list newInstance(boolean param1) {
        Fragment_list fragment = new Fragment_list();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getBoolean(ARG_PARAM1,false);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myRecycler =(RecyclerView) getView().findViewById(R.id.recycler);
        myRecycler.setHasFixedSize(true);
        myRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        initView();
    }

    public void setListener(OnClickItem listener){
        this.listener = listener;
    }

    public void initView(){
        if(MainActivity.longitudeGPS<0 && MainActivity.latitudeGPS<0 && !MainActivity.isMyRealData){
            MainActivity.latitudeGPS=34.046170784751794;
            MainActivity.longitudeGPS = -118.24772816403042;
        }else{
            MainActivity.isMyRealData=true;
        }
        if(mParam1==true) {
            RetrofitHelper.getObjects(String.valueOf(MainActivity.longitudeGPS), String.valueOf(MainActivity.latitudeGPS), "30", new RetrofitCallback() {
                @Override
                public void onSuccess(AirBnBObject object) {
                    adapter = new MyRecyclerViewAdapter(getActivity(), object.getSearchResults(), listener);
                    myRecycler.setAdapter(adapter);
                }

                @Override
                public void onError(Throwable error) {
                    Log.d("Retrofit", "error: " + error.toString());
                }
            });
        }else{
            AirBnBObject object= database.getFromDataBase(getActivity());
            if(object!=null) {
                adapter = new MyRecyclerViewAdapter(getActivity(), object.getSearchResults(), listener);
                myRecycler.setAdapter(adapter);
            }
        }
    }
}
