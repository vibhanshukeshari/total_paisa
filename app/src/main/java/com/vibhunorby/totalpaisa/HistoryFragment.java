package com.vibhunorby.totalpaisa;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.vibhunorby.totalpaisa.MainActivity.adRemoved;
import static com.vibhunorby.totalpaisa.MainActivity.refresh_first_time_only;
import static com.vibhunorby.totalpaisa.MainActivity.save_button_for_refreshing_sqlite;
import static com.vibhunorby.totalpaisa.MainActivity.save_button_pressed;

public class HistoryFragment extends Fragment {

    Prefs prefs;
    private ArrayList<CurrencyModal> currencyModalArrayList;
    private DBHandler dbHandler;
    private CurrencyRVAdapter currencyRVAdapter;
    private RecyclerView currencyRV;
    private LinearLayout textViewResultAmountLayout;
    static ImageView imageViewNotFound;
    static boolean isRecordVisible = false;



    public static HistoryFragment getInstance() {

        return new HistoryFragment();
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
    @Nullable
    @Override
    public  View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.row_history, container, false);
        setHasOptionsMenu(true);

        prefs = new Prefs(requireContext());
        currencyRV = view.findViewById(R.id.RVCurrencies);
        imageViewNotFound = view.findViewById(R.id.image_view_not_found);

        return view;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//
//
//    }

    public void setUserVisibleHint(boolean isVisibleToUser) {

        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {

            isRecordVisible = true;

//            I am using this condition to not to refresh history fragment each and everytime user visit
//                    refresh only first time the app starts and when any data saved.
//            if(save_button_for_refreshing_sqlite || refresh_first_time_only) {
                // initializing our all variables.
                currencyModalArrayList = new ArrayList<>();
                dbHandler = new DBHandler(getContext());

                // getting our currency array
                // list from db handler class.
                currencyModalArrayList = dbHandler.readCurrencies();

                // on below line passing our array lost to our adapter class.
                currencyRVAdapter = new CurrencyRVAdapter(currencyModalArrayList, getContext());
                currencyRVAdapter.setHasStableIds(true);



                // setting layout manager for our recycler view.
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                currencyRV.setLayoutManager(linearLayoutManager);

                currencyRV.setHasFixedSize(true);
                // setting our adapter to recycler view.



            // this delay is used to slow down the refresh lag (switching tab fragments lag)
//            this lag is not found in Easy loan interest calculator, Reason is not found
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {


                    currencyRV.setAdapter(currencyRVAdapter);

                }
            }, 300);


        }

    }

//    It has taken hours to hide menu item for history fragment. before i was working in pageAdapter checking position is == 2 then hide but failed.
    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.RefreshButton).setVisible(false);
        menu.findItem(R.id.ShareButton).setVisible(false);

        if (prefs.isRemoveAd()) {
            menu.findItem(R.id.RemoveAd).setVisible(false);
            menu.findItem(R.id.vip).setVisible(true);
        }


        super.onPrepareOptionsMenu(menu);
    }
}
