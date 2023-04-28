package com.vibhunorby.totalpaisa;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import static com.vibhunorby.totalpaisa.MainActivity.adRemoved;

public class DetailsFragment extends Fragment {

    Prefs prefs;
    static TextView TotalAmount,TotalAmountInWords;
    TextView textViewRupeesSymbolFragment;

    public static DetailsFragment getInstance() {

        return new DetailsFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.row_details, container, false);
        setHasOptionsMenu(true);


        prefs = new Prefs(requireContext());
        textViewRupeesSymbolFragment = view.findViewById(R.id.textViewRupeesSymbolDetailsFragment);
        TotalAmount = view.findViewById(R.id.textViewTotalAmount);
        TotalAmountInWords = view.findViewById(R.id.textViewTotalAmountInWords);
        return view;

    }
    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {

        menu.findItem(R.id.RefreshButton).setVisible(false);
        menu.findItem(R.id.ShareButton).setVisible(false);

        if (prefs.isRemoveAd()) {
            menu.findItem(R.id.RemoveAd).setVisible(false);
        }

        menu.findItem(R.id.DeleteAll).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }
}
