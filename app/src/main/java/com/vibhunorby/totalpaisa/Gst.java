// Development was started from  07/01/2021
//GSt merged with total paisa on 08/07/2021
//Ad remove purchase feature removed due to unsupported version of anjlab library
// Tried to implement new billing library by google but left coz was messy( haha.. see line no 15. achieved on that day)
// only 6 pro was sold in 4 months.
//Final version of total Paisa  with in app purchase to remove ad was released on 09/10/2021
// Total paisa took approximately 7 months constantly.
//present of total paisa on today 09/10/21 it has 2706 downloads on play store
// In future I vibhu has decided to add calculator and cash ledger like khata book with mysql database.
//I learnt one thing after developing total paisa Never stop ,you will face many errors and bug but even though keep trying keep tying....
// In total paisa assert background color changing toast were used which were creating issue of crash on Redmi and 1 plus decices it fixed after two yeas when i checked on my sister divice(12/03/2023)
// since all toast are normal and old one are removed.
// I made some changes on Data base, reduced data base lag (But it is not a proper way ) in Interest calculator I have done very welll(10/03/23).
// From 10/2022 downloads are decreased alot ( Reason is still not recognised).
//  App billing by google (IN app purchase added) latest version on 28/04/23
// Dialog box enhanced - 28/04/23
//G20 icon added - 28/04/23
// Native ads added - 28/04/23
// icon kitcken website is used to create icon and it is improved 28/04/23 it has a foreground color problem - https://icon.kitchen/i/H4sIAAAAAAAAA6tWKkvMKU0tVrKqVkpJLMoOyUjNTVWyKikqTa3VUcrNTynNAUlGKyXmpRTlZ6Yo6Shl5hcDyfLUJKXYWgA19PHYPwAAAA%3D%3D
//Calc lite added on 27/09/2023 totally designed by vibhu
// some ads are altered due to the decreament of Ecpm;
//G20 icon removed
//2000 currency removed
// gst easy clear button placed to top
//and some other featur altered on 27/09/23
//jay swami narayan :)


package com.vibhunorby.totalpaisa;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.os.VibrationEffect;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.os.Vibrator;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.navigation.NavigationBarView;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Gst extends AppCompatActivity {


    Double totalAmount;
    Double initialAmount;
    Double IgstAmount;
    Double CgstAmount;
    Double SgstAmount;
    Double netAmount;
    String roundNetAmount;
    String roundCgstAmount;
    String roundSgstAmount;
    String roundTotalAmount;
    String roundIgstAmount;
    Double stringToDouble;
    String trimString;
    long stringToLong;
    long realIntegerPart;
    long realDecimalPart;
    String LongToStringIntPart;
    String LongToStringDecPart;
    String IntegerToWord;
    String DecimalToWord;


    Prefs prefs;
    EditText editTextInitialValue;
    TextView totalAmountWord;
    LinearLayout adscontainergst;

    private static boolean keyboard_up_gst;
    private AdView mAdView;
    private Toolbar toolbar;

    TextView textViewclear;

    Chip chip3Percent;
    Chip chip5Percent;
    Chip chip12Percent;
    Chip chip18Percent;
    Chip chip28Percent;

    LinearLayout newLayoutNetAmount;
    LinearLayout newlayoutTotalAmount;
    LinearLayout newlayoutCgst;
    LinearLayout newlayoutSgst;
    LinearLayout newlayoutIgst;

    RadioButton radioExcluding, radioIncluding;
    RadioButton radioCgstSgst, radioIgst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gst);
        toolbar = findViewById(R.id.myToolBar);
        toolbar.setTitle("Gst Easy");
        setSupportActionBar(toolbar);
//
////        adscontainergst = findViewById(R.id.adsContainer_gst);
//
//        prefs = new Prefs(getApplicationContext());
//
//
//
//
//        mAdView = findViewById(R.id.adView);
//
//
////        if(!prefs.isRemoveAd()) {
////
////            MobileAds.initialize(this, new OnInitializationCompleteListener() {
////                @Override
////                public void onInitializationComplete(InitializationStatus initializationStatus) {
////                }
////            });
////
////            AdRequest adRequest = new AdRequest.Builder().build();
////            mAdView.loadAd(adRequest);
////
////
////        } else {
////
////            try {
////                adscontainergst.setVisibility(View.GONE);
////            }catch (NullPointerException ignored) {}
////
////        }
//
//
//
//
//
//
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setNavigationBarColor(Color.parseColor("#15202b"));
//        }
//
////        mAdView = findViewById(R.id.adView);
////        AdRequest adRequest = new AdRequest.Builder().build();
////        adscontainergst = findViewById(R.id.adsContainer_gst);
//
////        if(adRemoved){
////
////            adscontainergst.removeView(mAdView);
////        } else {
////            mAdView.loadAd(adRequest);
////        }
////
//
//
//
//
//        totalAmountWord = findViewById(R.id.textViewWords);
//        textViewclear = findViewById(R.id.textViewClear);
//        final BottomNavigationView bottomNavigationView = findViewById(R.id.botton_navigation);
//        editTextInitialValue = findViewById(R.id.editTextInitialValue);
//        chip3Percent = findViewById(R.id.chip3Percent);
//        chip5Percent = findViewById(R.id.chip5Percent);
//        chip12Percent = findViewById(R.id.chip12Percent);
//        chip18Percent = findViewById(R.id.chip18Percent);
//        chip28Percent = findViewById(R.id.chip28Percent);
//
//        newLayoutNetAmount = findViewById(R.id.layoutNetAmount);
//        newlayoutTotalAmount = findViewById(R.id.layoutTotalAmount);
//        newlayoutSgst = findViewById(R.id.layoutSgst);
//        newlayoutCgst = findViewById(R.id.layoutCgst);
//        newlayoutIgst = findViewById(R.id.layoutIgst);
//        radioCgstSgst = findViewById(R.id.radioCgstAndSgst);
//        radioIgst = findViewById(R.id.radioIgst);
//        radioExcluding = findViewById(R.id.radioExcluding);
//        radioIncluding = findViewById(R.id.radioIncluding);
//
//        ChipGroup chipGroup = findViewById(R.id.chipGroup);
//        RadioGroup radioExIN = findViewById(R.id.radioGroup);
//        RadioGroup radioCgstSgstIgst = findViewById(R.id.radioGroupForTaxType);
//
//
//
//
//        RelativeLayout relativeLayout = findViewById(R.id.rootViewGst);
//        relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                Rect r = new Rect();
//                relativeLayout.getWindowVisibleDisplayFrame(r);
//                int screenHeight = relativeLayout.getRootView().getHeight();
//                int keypadHeight = screenHeight - r.bottom;
//                if(keypadHeight > screenHeight * 0.15) {
////                    keyboard is up
//
//                    keyboard_up_gst = true;
//
//                } else {
////                    keyboard is down
//
//                    if (keyboard_up_gst) {
//
//                        try {
//                            editTextInitialValue.clearFocus();
//                        }catch (NullPointerException ignored){ }
//
//                        keyboard_up_gst = false;
//
//                    }
//
////                  Warning do not remove any of both important;
//                    keyboard_up_gst = false;
//
//
//                }
//            }
//        });
//
//
//
//
//
//
//
//
//
//
//
//
//        bottomNavigationView.setSelectedItemId(R.id.gst);
//
//
//
//        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int itemId = item.getItemId();
//
//                if (itemId == R.id.gst) {
//
//                    return true;
//                } else if (itemId == R.id.totalPaisa) {
//                    onBackPressed();
//                    overridePendingTransition(0, 0);
//                } else if (itemId == R.id.simpleCalc) {
//                    startActivity(new Intent(getApplicationContext(),CalcLite.class));
//                    overridePendingTransition(0,0);
//                    finish();
//                    return true;
//                }
//
//                return false;
//            }
//        });
//
//        final TextView textViewIgst = new TextView(Gst.this);
//        final TextView textViewIgstResult = new TextView(Gst.this);
//
//        final TextView textViewSgst = new TextView(Gst.this);
//        final TextView textViewSgstResult = new TextView(Gst.this);
//
//        final TextView textViewCgst = new TextView(Gst.this);
//        final TextView textViewCgstResult = new TextView(Gst.this);
//
//        final TextView textViewTotalAmount = new TextView(Gst.this);
//        final TextView textViewTotalAmountResult = new TextView(Gst.this);
//
//        final TextView textViewNetAmount = new TextView(Gst.this);
//        final TextView textViewForResultNetAmount = new TextView(Gst.this);
//
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
//                (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1F);
//        params.setMargins(10, 10, 10, 10);
//
//        textViewIgst.setLayoutParams(params);
//        textViewIgstResult.setLayoutParams(params);
//
//        textViewSgst.setLayoutParams(params);
//        textViewSgstResult.setLayoutParams(params);
//
//        textViewCgstResult.setLayoutParams(params);
//        textViewCgst.setLayoutParams(params);
//
//        textViewTotalAmount.setLayoutParams(params);
//        textViewTotalAmountResult.setLayoutParams(params);
//
//        textViewNetAmount.setLayoutParams(params);
//        textViewForResultNetAmount.setLayoutParams(params);
//
//        textViewNetAmount.setPadding(16, 0, 16, 0);
//        textViewNetAmount.setTextColor(getResources().getColor(R.color.chipcolor));
//        textViewNetAmount.setTextSize(16);
//
//        textViewForResultNetAmount.setPadding(16, 0, 16, 0);
//        textViewForResultNetAmount.setText("₹ 0");
//        textViewForResultNetAmount.setGravity(Gravity.RIGHT);
//        textViewForResultNetAmount.setTextColor(getResources().getColor(R.color.chipcolor));
//        textViewForResultNetAmount.setTextSize(16);
//
//        textViewTotalAmount.setPadding(16, 0, 16, 20);
//        textViewTotalAmount.setTextColor(getResources().getColor(R.color.chipcolor));
//        textViewTotalAmount.setTextSize(16);
//
//        textViewTotalAmountResult.setPadding(16, 0, 16, 20);
//        textViewTotalAmountResult.setText("₹ 0");
//        textViewTotalAmountResult.setGravity(Gravity.RIGHT);
//        textViewTotalAmountResult.setTextColor(getResources().getColor(R.color.chipcolor));
//        textViewTotalAmountResult.setTextSize(16);
//
//        textViewIgst.setText("IGST Amount");
//        textViewIgst.setPadding(16, 0, 16, 0);
//        textViewIgst.setTextColor(getResources().getColor(R.color.chipcolor));
//        textViewIgst.setTextSize(16);
//
//
//        textViewIgstResult.setText("₹ 0");
//        textViewIgstResult.setPadding(16, 0, 16, 0);
//        textViewIgstResult.setTextSize(16);
//        textViewIgstResult.setGravity(Gravity.RIGHT);
//        textViewIgstResult.setTextColor(getResources().getColor(R.color.chipcolor));
//        textViewIgstResult.setId(R.id.textViewIgstResult);
//
//        textViewCgst.setText("CGST Amount");
//        textViewCgst.setPadding(16, 0, 16, 0);
//        textViewCgst.setTextSize(16);
//        textViewCgst.setTextColor(getResources().getColor(R.color.chipcolor));
//
//        textViewCgstResult.setText("₹ 0");
//        textViewCgstResult.setPadding(16, 0, 16, 0);
//        textViewCgstResult.setGravity(Gravity.RIGHT);
//        textViewCgstResult.setTextSize(16);
//        textViewCgstResult.setTextColor(getResources().getColor(R.color.chipcolor));
//
//
//        textViewSgst.setText("SGST Amount");
//        textViewSgst.setPadding(16, 0, 16, 0);
//        textViewSgst.setTextSize(16);
//        textViewSgst.setTextColor(getResources().getColor(R.color.chipcolor));
//
//        textViewSgstResult.setText("₹ 0");
//        textViewSgstResult.setPadding(16, 0, 16, 0);
//        textViewSgstResult.setGravity(Gravity.RIGHT);
//        textViewSgstResult.setTextSize(16);
//        textViewSgstResult.setTextColor(getResources().getColor(R.color.chipcolor));
//
//
//        newlayoutIgst.removeAllViews();
//
//        newlayoutCgst.addView(textViewCgst);
//        newlayoutCgst.addView(textViewCgstResult);
//
//        newlayoutSgst.addView(textViewSgst);
//        newlayoutSgst.addView(textViewSgstResult);
//
//        newLayoutNetAmount.addView(textViewNetAmount);
//        newLayoutNetAmount.addView(textViewForResultNetAmount);
//
//
//        newlayoutTotalAmount.addView(textViewTotalAmount);
//        newlayoutTotalAmount.addView(textViewTotalAmountResult);
//
//
//        textViewclear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
////                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
////                    vibrator.vibrate(VibrationEffect.createOneShot(40, VibrationEffect.DEFAULT_AMPLITUDE));
////                } else {
////                    vibrator.vibrate(40);
////                }
//
//                textViewCgst.setText("CGST Amount");
//                textViewCgstResult.setText("₹ 0");
//                textViewSgst.setText("SGST Amount");
//                textViewSgstResult.setText("₹ 0");
//                textViewIgst.setText("IGST Amount");
//                textViewIgstResult.setText("₹ 0");
//                textViewForResultNetAmount.setText("₹ 0");
//                editTextInitialValue.getText().clear();
//
//            }
//        });
//
//        String stringNetAmount = "Net Amount (excluding GST)";
//        SpannableString ss1 = new SpannableString(stringNetAmount);
//        ss1.setSpan(new RelativeSizeSpan(0.8f), 11, 26, 0);
//        textViewNetAmount.setText(ss1);
//
//
//        String stringTotalAmount = "Total Amount (including GST)";
//        SpannableString ss2 = new SpannableString(stringTotalAmount);
//        ss2.setSpan(new RelativeSizeSpan(0.8f), 13, 28, 0);
//
//        StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
//        ss2.setSpan(bss, 0, 12, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//
//        textViewTotalAmountResult.setTypeface(null, Typeface.BOLD);
//        textViewTotalAmount.setText(ss2);
//
//        editTextInitialValue.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                Double totalAmount;
//                Double initialAmount;
//                Double IgstAmount;
//                Double CgstAmount;
//                Double SgstAmount;
//                Double netAmount;
//                String roundNetAmount;
//                String roundCgstAmount;
//                String roundSgstAmount;
//                String roundTotalAmount;
//                String roundIgstAmount;
//                Double stringToDouble;
//                String trimString;
//                long stringToLong;
//                long realIntegerPart;
//                long realDecimalPart;
//                String LongToStringIntPart;
//                String LongToStringDecPart;
//                String IntegerToWord;
//                String DecimalToWord;
//
//
//                if (!editTextInitialValue.getText().toString().equals("")) {
//                    if (radioExcluding.isChecked()) {
//                        if (radioIgst.isChecked()) {
//                            if (chip3Percent.isChecked()) {
//
//                                initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                netAmount = initialAmount;
//                                roundNetAmount = String.format("%.2f",netAmount);
//                                textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                IgstAmount = (initialAmount / 100 * 3);
//                                roundIgstAmount = String.format("%.2f",IgstAmount);
//                                textViewIgst.setText("IGST Amount (3%)");
//                                textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                totalAmount = initialAmount + IgstAmount;
//                                roundTotalAmount = String.format("%.2f",totalAmount);
//                                textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                stringToLong = Long.parseLong(trimString);
//                                realIntegerPart = stringToLong/100;
//                                realDecimalPart = stringToLong%100;
//                                StringBuilder wordFormat = new StringBuilder();
//                                if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                    wordFormat.append("Zero Rupees Only.");
//                                } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(DecimalToWord + " Paise Only.");
//                                } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    wordFormat.append(IntegerToWord + " Rupees Only.");
//                                } else {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                }
//
//                                totalAmountWord.setText(wordFormat);
//
//                            } else if (chip5Percent.isChecked()) {
//
//                                initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                netAmount = initialAmount;
//                                roundNetAmount = String.format("%.2f",netAmount);
//                                textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                IgstAmount = (initialAmount / 100 * 5);
//                                roundIgstAmount = String.format("%.2f",IgstAmount);
//                                textViewIgst.setText("IGST Amount (5%)");
//                                textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                totalAmount = initialAmount + IgstAmount;
//                                roundTotalAmount = String.format("%.2f",totalAmount);
//                                textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                stringToLong = Long.parseLong(trimString);
//                                realIntegerPart = stringToLong/100;
//                                realDecimalPart = stringToLong%100;
//                                StringBuilder wordFormat = new StringBuilder();
//                                if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                    wordFormat.append("Zero Rupees Only.");
//                                } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(DecimalToWord + " Paise Only.");
//                                } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    wordFormat.append(IntegerToWord + " Rupees Only.");
//                                } else {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                }
//
//                                totalAmountWord.setText(wordFormat);
//                            } else if (chip12Percent.isChecked()) {
//                                initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                netAmount = initialAmount;
//                                roundNetAmount = String.format("%.2f",netAmount);
//                                textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                IgstAmount = (initialAmount / 100 * 12);
//                                roundIgstAmount = String.format("%.2f",IgstAmount);
//                                textViewIgst.setText("IGST Amount (12%)");
//                                textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                totalAmount = initialAmount + IgstAmount;
//                                roundTotalAmount = String.format("%.2f",totalAmount);
//                                textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                stringToLong = Long.parseLong(trimString);
//                                realIntegerPart = stringToLong/100;
//                                realDecimalPart = stringToLong%100;
//                                StringBuilder wordFormat = new StringBuilder();
//                                if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                    wordFormat.append("Zero Rupees Only.");
//                                } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(DecimalToWord + " Paise Only.");
//                                } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    wordFormat.append(IntegerToWord + " Rupees Only.");
//                                } else {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                }
//
//                                totalAmountWord.setText(wordFormat);
//
//                            } else if (chip18Percent.isChecked()) {
//                                initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                netAmount = initialAmount;
//                                roundNetAmount = String.format("%.2f",netAmount);
//                                textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                IgstAmount = (initialAmount / 100 * 18);
//                                roundIgstAmount = String.format("%.2f",IgstAmount);
//                                textViewIgst.setText("IGST Amount (18%)");
//                                textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                totalAmount = initialAmount + IgstAmount;
//                                roundTotalAmount = String.format("%.2f",totalAmount);
//                                textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                stringToLong = Long.parseLong(trimString);
//                                realIntegerPart = stringToLong/100;
//                                realDecimalPart = stringToLong%100;
//                                StringBuilder wordFormat = new StringBuilder();
//                                if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                    wordFormat.append("Zero Rupees Only.");
//                                } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(DecimalToWord + " Paise Only.");
//                                } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    wordFormat.append(IntegerToWord + " Rupees Only.");
//                                } else {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                }
//
//                                totalAmountWord.setText(wordFormat);
//
//                            } else if (chip28Percent.isChecked()) {
//                                initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                netAmount = initialAmount;
//                                roundNetAmount = String.format("%.2f",netAmount);
//                                textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                IgstAmount = (initialAmount / 100 * 28);
//                                roundIgstAmount = String.format("%.2f",IgstAmount);
//                                textViewIgst.setText("IGST Amount (28%)");
//                                textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                totalAmount = initialAmount + IgstAmount;
//                                roundTotalAmount = String.format("%.2f",totalAmount);
//                                textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                stringToLong = Long.parseLong(trimString);
//                                realIntegerPart = stringToLong/100;
//                                realDecimalPart = stringToLong%100;
//                                StringBuilder wordFormat = new StringBuilder();
//                                if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                    wordFormat.append("Zero Rupees Only.");
//                                } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(DecimalToWord + " Paise Only.");
//                                } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    wordFormat.append(IntegerToWord + " Rupees Only.");
//                                } else {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                }
//
//                                totalAmountWord.setText(wordFormat);
//                            }
//
//
//                        } else if (radioCgstSgst.isChecked()) {
//                            if (chip3Percent.isChecked()) {
//
//                                initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                netAmount = initialAmount;
//                                roundNetAmount = String.format("%.2f",netAmount);
//                                textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                CgstAmount = ((initialAmount / 100 * 3)/2);
//                                roundCgstAmount = String.format("%.2f",CgstAmount);
//                                SgstAmount = ((initialAmount / 100 * 3)/2);
//                                roundSgstAmount = String.format("%.2f",SgstAmount);
//                                textViewCgst.setText("CGST Amount (1.5%)");
//                                textViewSgst.setText("SGST Amount (1.5%)");
//                                textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                roundTotalAmount = String.format("%.2f",totalAmount);
//                                textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                stringToLong = Long.parseLong(trimString);
//                                realIntegerPart = stringToLong/100;
//                                realDecimalPart = stringToLong%100;
//                                StringBuilder wordFormat = new StringBuilder();
//                                if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                    wordFormat.append("Zero Rupees Only.");
//                                } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(DecimalToWord + " Paise Only.");
//                                } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    wordFormat.append(IntegerToWord + " Rupees Only.");
//                                } else {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                }
//
//                                totalAmountWord.setText(wordFormat);
//
//
//                            } else if (chip5Percent.isChecked()) {
//
//                                initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                netAmount = initialAmount;
//                                roundNetAmount = String.format("%.2f",netAmount);
//                                textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                CgstAmount = ((initialAmount / 100 * 5)/2);
//                                roundCgstAmount = String.format("%.2f",CgstAmount);
//                                SgstAmount = ((initialAmount / 100 * 5)/2);
//                                roundSgstAmount = String.format("%.2f",SgstAmount);
//                                textViewCgst.setText("CGST Amount (2.5%)");
//                                textViewSgst.setText("SGST Amount (2.5%)");
//                                textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                roundTotalAmount = String.format("%.2f",totalAmount);
//                                textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                stringToLong = Long.parseLong(trimString);
//                                realIntegerPart = stringToLong/100;
//                                realDecimalPart = stringToLong%100;
//                                StringBuilder wordFormat = new StringBuilder();
//                                if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                    wordFormat.append("Zero Rupees Only.");
//                                } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(DecimalToWord + " Paise Only.");
//                                } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    wordFormat.append(IntegerToWord + " Rupees Only.");
//                                } else {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                }
//
//                                totalAmountWord.setText(wordFormat);
//
//                            } else if (chip12Percent.isChecked()) {
//
//                                initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                netAmount = initialAmount;
//                                roundNetAmount = String.format("%.2f",netAmount);
//                                textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                CgstAmount = ((initialAmount / 100 * 12)/2);
//                                roundCgstAmount = String.format("%.2f",CgstAmount);
//                                SgstAmount = ((initialAmount / 100 * 12)/2);
//                                roundSgstAmount = String.format("%.2f",SgstAmount);
//                                textViewCgst.setText("CGST Amount (6%)");
//                                textViewSgst.setText("SGST Amount (6%)");
//                                textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                roundTotalAmount = String.format("%.2f",totalAmount);
//                                textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                stringToLong = Long.parseLong(trimString);
//                                realIntegerPart = stringToLong/100;
//                                realDecimalPart = stringToLong%100;
//                                StringBuilder wordFormat = new StringBuilder();
//                                if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                    wordFormat.append("Zero Rupees Only.");
//                                } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(DecimalToWord + " Paise Only.");
//                                } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    wordFormat.append(IntegerToWord + " Rupees Only.");
//                                } else {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                }
//
//                                totalAmountWord.setText(wordFormat);
//
//
//                            } else if (chip18Percent.isChecked()) {
//
//                                initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                netAmount = initialAmount;
//                                roundNetAmount = String.format("%.2f",netAmount);
//                                textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                CgstAmount = ((initialAmount / 100 * 18)/2);
//                                roundCgstAmount = String.format("%.2f",CgstAmount);
//                                SgstAmount = ((initialAmount / 100 * 18)/2);
//                                roundSgstAmount = String.format("%.2f",SgstAmount);
//                                textViewCgst.setText("CGST Amount (9%)");
//                                textViewSgst.setText("SGST Amount (9%)");
//                                textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                roundTotalAmount = String.format("%.2f",totalAmount);
//                                textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                stringToLong = Long.parseLong(trimString);
//                                realIntegerPart = stringToLong/100;
//                                realDecimalPart = stringToLong%100;
//                                StringBuilder wordFormat = new StringBuilder();
//                                if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                    wordFormat.append("Zero Rupees Only.");
//                                } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(DecimalToWord + " Paise Only.");
//                                } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    wordFormat.append(IntegerToWord + " Rupees Only.");
//                                } else {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                }
//
//                                totalAmountWord.setText(wordFormat);
//
//                            } else if (chip28Percent.isChecked()) {
//                                initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                netAmount = initialAmount;
//                                roundNetAmount = String.format("%.2f",netAmount);
//                                textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                CgstAmount = ((initialAmount / 100 * 28)/2);
//                                roundCgstAmount = String.format("%.2f",CgstAmount);
//                                SgstAmount = ((initialAmount / 100 * 28)/2);
//                                roundSgstAmount = String.format("%.2f",SgstAmount);
//                                textViewCgst.setText("CGST Amount (14%)");
//                                textViewSgst.setText("SGST Amount (14%)");
//                                textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                roundTotalAmount = String.format("%.2f",totalAmount);
//                                textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                stringToLong = Long.parseLong(trimString);
//                                realIntegerPart = stringToLong/100;
//                                realDecimalPart = stringToLong%100;
//                                StringBuilder wordFormat = new StringBuilder();
//                                if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                    wordFormat.append("Zero Rupees Only.");
//                                } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(DecimalToWord + " Paise Only.");
//                                } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    wordFormat.append(IntegerToWord + " Rupees Only.");
//                                } else {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                }
//
//                                totalAmountWord.setText(wordFormat);
//
//                            }
//                        }
//                    } else if (radioIncluding.isChecked()) {
//                        if (radioCgstSgst.isChecked()) {
//                            if (chip3Percent.isChecked()) {
//
//                                initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                netAmount = (initialAmount/1.03);
//                                roundNetAmount = String.format("%.2f",netAmount);
//                                textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                CgstAmount = ((netAmount / 100 * 3)/2);
//                                roundCgstAmount = String.format("%.2f",CgstAmount);
//                                SgstAmount = ((netAmount / 100 * 3)/2);
//                                roundSgstAmount = String.format("%.2f",SgstAmount);
//                                textViewCgst.setText("CGST Amount (1.5%)");
//                                textViewSgst.setText("SGST Amount (1.5%)");
//                                textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                totalAmount = initialAmount;
//                                roundTotalAmount = String.format("%.2f",totalAmount);
//                                textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                stringToLong = Long.parseLong(trimString);
//                                realIntegerPart = stringToLong/100;
//                                realDecimalPart = stringToLong%100;
//                                StringBuilder wordFormat = new StringBuilder();
//                                if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                    wordFormat.append("Zero Rupees Only.");
//                                } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(DecimalToWord + " Paise Only.");
//                                } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    wordFormat.append(IntegerToWord + " Rupees Only.");
//                                } else {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                }
//
//                                totalAmountWord.setText(wordFormat);
//
//                            } else if (chip5Percent.isChecked()) {
//                                initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                netAmount = (initialAmount/1.05);
//                                roundNetAmount = String.format("%.2f",netAmount);
//                                textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                CgstAmount = ((netAmount / 100 * 5)/2);
//                                roundCgstAmount = String.format("%.2f",CgstAmount);
//                                SgstAmount = ((netAmount / 100 * 5)/2);
//                                roundSgstAmount = String.format("%.2f",SgstAmount);
//                                textViewCgst.setText("CGST Amount (2.5%)");
//                                textViewSgst.setText("SGST Amount (2.5%)");
//                                textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                totalAmount = initialAmount;
//                                roundTotalAmount = String.format("%.2f",totalAmount);
//                                textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                stringToLong = Long.parseLong(trimString);
//                                realIntegerPart = stringToLong/100;
//                                realDecimalPart = stringToLong%100;
//                                StringBuilder wordFormat = new StringBuilder();
//                                if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                    wordFormat.append("Zero Rupees Only.");
//                                } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(DecimalToWord + " Paise Only.");
//                                } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    wordFormat.append(IntegerToWord + " Rupees Only.");
//                                } else {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                }
//
//                                totalAmountWord.setText(wordFormat);
//
//                            } else if (chip12Percent.isChecked()) {
//                                initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                netAmount = (initialAmount/1.12);
//                                roundNetAmount = String.format("%.2f",netAmount);
//                                textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                CgstAmount = ((netAmount / 100 * 12)/2);
//                                roundCgstAmount = String.format("%.2f",CgstAmount);
//                                SgstAmount = ((netAmount / 100 * 12)/2);
//                                roundSgstAmount = String.format("%.2f",SgstAmount);
//                                textViewCgst.setText("CGST Amount (6%)");
//                                textViewSgst.setText("SGST Amount (6%)");
//                                textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                totalAmount = initialAmount;
//                                roundTotalAmount = String.format("%.2f",totalAmount);
//                                textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                stringToLong = Long.parseLong(trimString);
//                                realIntegerPart = stringToLong/100;
//                                realDecimalPart = stringToLong%100;
//                                StringBuilder wordFormat = new StringBuilder();
//                                if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                    wordFormat.append("Zero Rupees Only.");
//                                } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(DecimalToWord + " Paise Only.");
//                                } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    wordFormat.append(IntegerToWord + " Rupees Only.");
//                                } else {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                }
//
//                                totalAmountWord.setText(wordFormat);
//
//
//                            } else if (chip18Percent.isChecked()) {
//
//                                initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                netAmount = (initialAmount/1.18);
//                                roundNetAmount = String.format("%.2f",netAmount);
//                                textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                CgstAmount = ((netAmount / 100 * 18)/2);
//                                roundCgstAmount = String.format("%.2f",CgstAmount);
//                                SgstAmount = ((netAmount / 100 * 18)/2);
//                                roundSgstAmount = String.format("%.2f",SgstAmount);
//                                textViewCgst.setText("CGST Amount (9%)");
//                                textViewSgst.setText("SGST Amount (9%)");
//                                textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                totalAmount = initialAmount;
//                                roundTotalAmount = String.format("%.2f",totalAmount);
//                                textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                stringToLong = Long.parseLong(trimString);
//                                realIntegerPart = stringToLong/100;
//                                realDecimalPart = stringToLong%100;
//                                StringBuilder wordFormat = new StringBuilder();
//                                if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                    wordFormat.append("Zero Rupees Only.");
//                                } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(DecimalToWord + " Paise Only.");
//                                } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    wordFormat.append(IntegerToWord + " Rupees Only.");
//                                } else {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                }
//
//                                totalAmountWord.setText(wordFormat);
//
//
//                            } else if (chip28Percent.isChecked()) {
//
//                                initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                netAmount = (initialAmount/1.28);
//                                roundNetAmount = String.format("%.2f",netAmount);
//                                textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                CgstAmount = ((netAmount / 100 * 28)/2);
//                                roundCgstAmount = String.format("%.2f",CgstAmount);
//                                SgstAmount = ((netAmount / 100 * 28)/2);
//                                roundSgstAmount = String.format("%.2f",SgstAmount);
//                                textViewCgst.setText("CGST Amount (14%)");
//                                textViewSgst.setText("SGST Amount (14%)");
//                                textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                totalAmount = initialAmount;
//                                roundTotalAmount = String.format("%.2f",totalAmount);
//                                textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                stringToLong = Long.parseLong(trimString);
//                                realIntegerPart = stringToLong/100;
//                                realDecimalPart = stringToLong%100;
//                                StringBuilder wordFormat = new StringBuilder();
//                                if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                    wordFormat.append("Zero Rupees Only.");
//                                } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(DecimalToWord + " Paise Only.");
//                                } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    wordFormat.append(IntegerToWord + " Rupees Only.");
//                                } else {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                }
//
//                                totalAmountWord.setText(wordFormat);
//
//
//                            }
//
//
//                        } else if (radioIgst.isChecked()) {
//                            if (chip3Percent.isChecked()) {
//
//
//                                initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                netAmount = (initialAmount/1.03);
//                                roundNetAmount = String.format("%.2f",netAmount);
//                                textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                IgstAmount = (netAmount / 100 * 3);
//                                roundIgstAmount = String.format("%.2f",IgstAmount);
//                                textViewIgst.setText("IGST Amount (3%)");
//                                textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                totalAmount = initialAmount;
//                                roundTotalAmount = String.format("%.2f",totalAmount);
//                                textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                stringToLong = Long.parseLong(trimString);
//                                realIntegerPart = stringToLong/100;
//                                realDecimalPart = stringToLong%100;
//                                StringBuilder wordFormat = new StringBuilder();
//                                if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                    wordFormat.append("Zero Rupees Only.");
//                                } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(DecimalToWord + " Paise Only.");
//                                } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    wordFormat.append(IntegerToWord + " Rupees Only.");
//                                } else {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                }
//
//                                totalAmountWord.setText(wordFormat);
//
//
//                            } else if (chip5Percent.isChecked()) {
//
//
//                                initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                netAmount = (initialAmount/1.05);
//                                roundNetAmount = String.format("%.2f",netAmount);
//                                textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                IgstAmount = (netAmount / 100 * 5);
//                                roundIgstAmount = String.format("%.2f",IgstAmount);
//                                textViewIgst.setText("IGST Amount (5%)");
//                                textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                totalAmount = initialAmount;
//                                roundTotalAmount = String.format("%.2f",totalAmount);
//                                textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                stringToLong = Long.parseLong(trimString);
//                                realIntegerPart = stringToLong/100;
//                                realDecimalPart = stringToLong%100;
//                                StringBuilder wordFormat = new StringBuilder();
//                                if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                    wordFormat.append("Zero Rupees Only.");
//                                } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(DecimalToWord + " Paise Only.");
//                                } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    wordFormat.append(IntegerToWord + " Rupees Only.");
//                                } else {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                }
//
//                                totalAmountWord.setText(wordFormat);
//
//                            } else if (chip12Percent.isChecked()) {
////
//
//                                initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                netAmount = (initialAmount/1.12);
//                                roundNetAmount = String.format("%.2f",netAmount);
//                                textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                IgstAmount = (netAmount / 100 * 12);
//                                roundIgstAmount = String.format("%.2f",IgstAmount);
//                                textViewIgst.setText("IGST Amount (12%)");
//                                textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                totalAmount = initialAmount;
//                                roundTotalAmount = String.format("%.2f",totalAmount);
//                                textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                stringToLong = Long.parseLong(trimString);
//                                realIntegerPart = stringToLong/100;
//                                realDecimalPart = stringToLong%100;
//                                StringBuilder wordFormat = new StringBuilder();
//                                if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                    wordFormat.append("Zero Rupees Only.");
//                                } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(DecimalToWord + " Paise Only.");
//                                } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    wordFormat.append(IntegerToWord + " Rupees Only.");
//                                } else {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                }
//
//                                totalAmountWord.setText(wordFormat);
//
//                            } else if (chip18Percent.isChecked()) {
//
//
//                                initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                netAmount = (initialAmount/1.18);
//                                roundNetAmount = String.format("%.2f",netAmount);
//                                textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                IgstAmount = (netAmount / 100 * 18);
//                                roundIgstAmount = String.format("%.2f",IgstAmount);
//                                textViewIgst.setText("IGST Amount (18%)");
//                                textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                totalAmount = initialAmount;
//                                roundTotalAmount = String.format("%.2f",totalAmount);
//                                textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                stringToLong = Long.parseLong(trimString);
//                                realIntegerPart = stringToLong/100;
//                                realDecimalPart = stringToLong%100;
//                                StringBuilder wordFormat = new StringBuilder();
//                                if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                    wordFormat.append("Zero Rupees Only.");
//                                } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(DecimalToWord + " Paise Only.");
//                                } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    wordFormat.append(IntegerToWord + " Rupees Only.");
//                                } else {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                }
//
//                                totalAmountWord.setText(wordFormat);
//
//                            } else if (chip28Percent.isChecked()) {
//
//                                initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                netAmount = (initialAmount/1.28);
//                                roundNetAmount = String.format("%.2f",netAmount);
//                                textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                IgstAmount = (netAmount / 100 * 28);
//                                roundIgstAmount = String.format("%.2f",IgstAmount);
//                                textViewIgst.setText("IGST Amount (28%)");
//                                textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                totalAmount = initialAmount;
//                                roundTotalAmount = String.format("%.2f",totalAmount);
//                                textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                stringToLong = Long.parseLong(trimString);
//                                realIntegerPart = stringToLong/100;
//                                realDecimalPart = stringToLong%100;
//                                StringBuilder wordFormat = new StringBuilder();
//                                if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                    wordFormat.append("Zero Rupees Only.");
//                                } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(DecimalToWord + " Paise Only.");
//                                } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    wordFormat.append(IntegerToWord + " Rupees Only.");
//                                } else {
//                                    LongToStringIntPart = String.valueOf(realIntegerPart);
//                                    LongToStringDecPart = String.valueOf(realDecimalPart);
//                                    IntegerToWord = Words.convert(LongToStringIntPart);
//                                    DecimalToWord = Words.convert(LongToStringDecPart);
//                                    wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                }
//
//                                totalAmountWord.setText(wordFormat);
//
//                            }
//                        }
//                    }
//                } else {
//                    textViewCgstResult.setText("₹ 0");
//                    textViewSgstResult.setText("₹ 0");
//                    textViewIgstResult.setText("₹ 0");
//                    textViewTotalAmountResult.setText("₹ 0");
//                    textViewForResultNetAmount.setText("₹ 0");
//                    textViewTotalAmountResult.setText("₹ 0");
//                    totalAmountWord.setText("");
//                }
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//        radioCgstSgstIgst.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//
//                if(i == R.id.radioCgstAndSgst) {
////                    case R.id.radioCgstAndSgst:
//
//                    newlayoutIgst.removeAllViews();
//
//                    newlayoutCgst.addView(textViewCgst);
//                    newlayoutCgst.addView(textViewCgstResult);
//
//                    newlayoutSgst.addView(textViewSgst);
//                    newlayoutSgst.addView(textViewSgstResult);
//
//
////                    Double totalAmount;
////                    Double initialAmount;
////                    Double IgstAmount;
////                    Double CgstAmount;
////                    Double SgstAmount;
////                    Double netAmount;
////                    String roundNetAmount;
////                    String roundCgstAmount;
////                    String roundSgstAmount;
////                    String roundTotalAmount;
////                    String roundIgstAmount;
////                    Double stringToDouble;
////                    String trimString;
////                    long stringToLong;
////                    long realIntegerPart;
////                    long realDecimalPart;
////                    String LongToStringIntPart;
////                    String LongToStringDecPart;
////                    String IntegerToWord;
////                    String DecimalToWord;
//
//
//                    if (!editTextInitialValue.getText().toString().equals("")) {
//                        if (radioExcluding.isChecked()) {
//                            if (radioIgst.isChecked()) {
//                                if (chip3Percent.isChecked()) {
//
//                                    initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                    netAmount = initialAmount;
//                                    roundNetAmount = String.format("%.2f", netAmount);
//                                    textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                    IgstAmount = (initialAmount / 100 * 3);
//                                    roundIgstAmount = String.format("%.2f", IgstAmount);
//                                    textViewIgst.setText("IGST Amount (3%)");
//                                    textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                    totalAmount = initialAmount + IgstAmount;
//                                    roundTotalAmount = String.format("%.2f", totalAmount);
//                                    textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                    stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                    String doubleToStringFormat = String.format("%.2f", stringToDouble);
//                                    trimString = doubleToStringFormat.substring(0, doubleToStringFormat.length() - 3);
//                                    stringToLong = Long.parseLong(trimString);
//                                    realIntegerPart = stringToLong / 100;
//                                    realDecimalPart = stringToLong % 100;
//                                    StringBuilder wordFormat = new StringBuilder();
//                                    if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                        wordFormat.append("Zero Rupees Only.");
//                                    } else if (realIntegerPart == 0 && realDecimalPart != 0) {
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(DecimalToWord + " Paise Only.");
//                                    } else if (realIntegerPart != 0 && realDecimalPart == 0) {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        wordFormat.append(IntegerToWord + " Rupees Only.");
//                                    } else {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord + " Paise Only. ");
//                                    }
//
//                                    totalAmountWord.setText(wordFormat);
//
//                                } else if (chip5Percent.isChecked()) {
//
//                                    initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                    netAmount = initialAmount;
//                                    roundNetAmount = String.format("%.2f", netAmount);
//                                    textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                    IgstAmount = (initialAmount / 100 * 5);
//                                    roundIgstAmount = String.format("%.2f", IgstAmount);
//                                    textViewIgst.setText("IGST Amount (5%)");
//                                    textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                    totalAmount = initialAmount + IgstAmount;
//                                    roundTotalAmount = String.format("%.2f", totalAmount);
//                                    textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                    stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                    String doubleToStringFormat = String.format("%.2f", stringToDouble);
//                                    trimString = doubleToStringFormat.substring(0, doubleToStringFormat.length() - 3);
//                                    stringToLong = Long.parseLong(trimString);
//                                    realIntegerPart = stringToLong / 100;
//                                    realDecimalPart = stringToLong % 100;
//                                    StringBuilder wordFormat = new StringBuilder();
//                                    if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                        wordFormat.append("Zero Rupees Only.");
//                                    } else if (realIntegerPart == 0 && realDecimalPart != 0) {
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(DecimalToWord + " Paise Only.");
//                                    } else if (realIntegerPart != 0 && realDecimalPart == 0) {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        wordFormat.append(IntegerToWord + " Rupees Only.");
//                                    } else {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord + " Paise Only. ");
//                                    }
//
//                                    totalAmountWord.setText(wordFormat);
//                                } else if (chip12Percent.isChecked()) {
//                                    initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                    netAmount = initialAmount;
//                                    roundNetAmount = String.format("%.2f", netAmount);
//                                    textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                    IgstAmount = (initialAmount / 100 * 12);
//                                    roundIgstAmount = String.format("%.2f", IgstAmount);
//                                    textViewIgst.setText("IGST Amount (12%)");
//                                    textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                    totalAmount = initialAmount + IgstAmount;
//                                    roundTotalAmount = String.format("%.2f", totalAmount);
//                                    textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                    stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                    String doubleToStringFormat = String.format("%.2f", stringToDouble);
//                                    trimString = doubleToStringFormat.substring(0, doubleToStringFormat.length() - 3);
//                                    stringToLong = Long.parseLong(trimString);
//                                    realIntegerPart = stringToLong / 100;
//                                    realDecimalPart = stringToLong % 100;
//                                    StringBuilder wordFormat = new StringBuilder();
//                                    if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                        wordFormat.append("Zero Rupees Only.");
//                                    } else if (realIntegerPart == 0 && realDecimalPart != 0) {
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(DecimalToWord + " Paise Only.");
//                                    } else if (realIntegerPart != 0 && realDecimalPart == 0) {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        wordFormat.append(IntegerToWord + " Rupees Only.");
//                                    } else {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord + " Paise Only. ");
//                                    }
//
//                                    totalAmountWord.setText(wordFormat);
//
//                                } else if (chip18Percent.isChecked()) {
//                                    initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                    netAmount = initialAmount;
//                                    roundNetAmount = String.format("%.2f", netAmount);
//                                    textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                    IgstAmount = (initialAmount / 100 * 18);
//                                    roundIgstAmount = String.format("%.2f", IgstAmount);
//                                    textViewIgst.setText("IGST Amount (18%)");
//                                    textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                    totalAmount = initialAmount + IgstAmount;
//                                    roundTotalAmount = String.format("%.2f", totalAmount);
//                                    textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                    stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                    String doubleToStringFormat = String.format("%.2f", stringToDouble);
//                                    trimString = doubleToStringFormat.substring(0, doubleToStringFormat.length() - 3);
//                                    stringToLong = Long.parseLong(trimString);
//                                    realIntegerPart = stringToLong / 100;
//                                    realDecimalPart = stringToLong % 100;
//                                    StringBuilder wordFormat = new StringBuilder();
//                                    if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                        wordFormat.append("Zero Rupees Only.");
//                                    } else if (realIntegerPart == 0 && realDecimalPart != 0) {
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(DecimalToWord + " Paise Only.");
//                                    } else if (realIntegerPart != 0 && realDecimalPart == 0) {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        wordFormat.append(IntegerToWord + " Rupees Only.");
//                                    } else {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord + " Paise Only. ");
//                                    }
//
//                                    totalAmountWord.setText(wordFormat);
//
//                                } else if (chip28Percent.isChecked()) {
//                                    initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                    netAmount = initialAmount;
//                                    roundNetAmount = String.format("%.2f", netAmount);
//                                    textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                    IgstAmount = (initialAmount / 100 * 28);
//                                    roundIgstAmount = String.format("%.2f", IgstAmount);
//                                    textViewIgst.setText("IGST Amount (28%)");
//                                    textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                    totalAmount = initialAmount + IgstAmount;
//                                    roundTotalAmount = String.format("%.2f", totalAmount);
//                                    textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                    stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                    String doubleToStringFormat = String.format("%.2f", stringToDouble);
//                                    trimString = doubleToStringFormat.substring(0, doubleToStringFormat.length() - 3);
//                                    stringToLong = Long.parseLong(trimString);
//                                    realIntegerPart = stringToLong / 100;
//                                    realDecimalPart = stringToLong % 100;
//                                    StringBuilder wordFormat = new StringBuilder();
//                                    if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                        wordFormat.append("Zero Rupees Only.");
//                                    } else if (realIntegerPart == 0 && realDecimalPart != 0) {
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(DecimalToWord + " Paise Only.");
//                                    } else if (realIntegerPart != 0 && realDecimalPart == 0) {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        wordFormat.append(IntegerToWord + " Rupees Only.");
//                                    } else {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord + " Paise Only. ");
//                                    }
//
//                                    totalAmountWord.setText(wordFormat);
//                                }
//
//
//                            } else if (radioCgstSgst.isChecked()) {
//                                if (chip3Percent.isChecked()) {
//
//                                    initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                    netAmount = initialAmount;
//                                    roundNetAmount = String.format("%.2f", netAmount);
//                                    textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                    CgstAmount = ((initialAmount / 100 * 3) / 2);
//                                    roundCgstAmount = String.format("%.2f", CgstAmount);
//                                    SgstAmount = ((initialAmount / 100 * 3) / 2);
//                                    roundSgstAmount = String.format("%.2f", SgstAmount);
//                                    textViewCgst.setText("CGST Amount (1.5%)");
//                                    textViewSgst.setText("SGST Amount (1.5%)");
//                                    textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                    textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                    totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                    roundTotalAmount = String.format("%.2f", totalAmount);
//                                    textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                    stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                    String doubleToStringFormat = String.format("%.2f", stringToDouble);
//                                    trimString = doubleToStringFormat.substring(0, doubleToStringFormat.length() - 3);
//                                    stringToLong = Long.parseLong(trimString);
//                                    realIntegerPart = stringToLong / 100;
//                                    realDecimalPart = stringToLong % 100;
//                                    StringBuilder wordFormat = new StringBuilder();
//                                    if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                        wordFormat.append("Zero Rupees Only.");
//                                    } else if (realIntegerPart == 0 && realDecimalPart != 0) {
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(DecimalToWord + " Paise Only.");
//                                    } else if (realIntegerPart != 0 && realDecimalPart == 0) {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        wordFormat.append(IntegerToWord + " Rupees Only.");
//                                    } else {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord + " Paise Only. ");
//                                    }
//
//                                    totalAmountWord.setText(wordFormat);
//
//
//                                } else if (chip5Percent.isChecked()) {
//
//                                    initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                    netAmount = initialAmount;
//                                    roundNetAmount = String.format("%.2f", netAmount);
//                                    textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                    CgstAmount = ((initialAmount / 100 * 5) / 2);
//                                    roundCgstAmount = String.format("%.2f", CgstAmount);
//                                    SgstAmount = ((initialAmount / 100 * 5) / 2);
//                                    roundSgstAmount = String.format("%.2f", SgstAmount);
//                                    textViewCgst.setText("CGST Amount (2.5%)");
//                                    textViewSgst.setText("SGST Amount (2.5%)");
//                                    textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                    textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                    totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                    roundTotalAmount = String.format("%.2f", totalAmount);
//                                    textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                    stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                    String doubleToStringFormat = String.format("%.2f", stringToDouble);
//                                    trimString = doubleToStringFormat.substring(0, doubleToStringFormat.length() - 3);
//                                    stringToLong = Long.parseLong(trimString);
//                                    realIntegerPart = stringToLong / 100;
//                                    realDecimalPart = stringToLong % 100;
//                                    StringBuilder wordFormat = new StringBuilder();
//                                    if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                        wordFormat.append("Zero Rupees Only.");
//                                    } else if (realIntegerPart == 0 && realDecimalPart != 0) {
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(DecimalToWord + " Paise Only.");
//                                    } else if (realIntegerPart != 0 && realDecimalPart == 0) {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        wordFormat.append(IntegerToWord + " Rupees Only.");
//                                    } else {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord + " Paise Only. ");
//                                    }
//
//                                    totalAmountWord.setText(wordFormat);
//
//
//                                } else if (chip12Percent.isChecked()) {
//
//                                    initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                    netAmount = initialAmount;
//                                    roundNetAmount = String.format("%.2f", netAmount);
//                                    textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                    CgstAmount = ((initialAmount / 100 * 12) / 2);
//                                    roundCgstAmount = String.format("%.2f", CgstAmount);
//                                    SgstAmount = ((initialAmount / 100 * 12) / 2);
//                                    roundSgstAmount = String.format("%.2f", SgstAmount);
//                                    textViewCgst.setText("CGST Amount (6%)");
//                                    textViewSgst.setText("SGST Amount (6%)");
//                                    textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                    textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                    totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                    roundTotalAmount = String.format("%.2f", totalAmount);
//                                    textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                    stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                    String doubleToStringFormat = String.format("%.2f", stringToDouble);
//                                    trimString = doubleToStringFormat.substring(0, doubleToStringFormat.length() - 3);
//                                    stringToLong = Long.parseLong(trimString);
//                                    realIntegerPart = stringToLong / 100;
//                                    realDecimalPart = stringToLong % 100;
//                                    StringBuilder wordFormat = new StringBuilder();
//                                    if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                        wordFormat.append("Zero Rupees Only.");
//                                    } else if (realIntegerPart == 0 && realDecimalPart != 0) {
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(DecimalToWord + " Paise Only.");
//                                    } else if (realIntegerPart != 0 && realDecimalPart == 0) {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        wordFormat.append(IntegerToWord + " Rupees Only.");
//                                    } else {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord + " Paise Only. ");
//                                    }
//
//                                    totalAmountWord.setText(wordFormat);
//
//
//                                } else if (chip18Percent.isChecked()) {
//
//                                    initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                    netAmount = initialAmount;
//                                    roundNetAmount = String.format("%.2f", netAmount);
//                                    textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                    CgstAmount = ((initialAmount / 100 * 18) / 2);
//                                    roundCgstAmount = String.format("%.2f", CgstAmount);
//                                    SgstAmount = ((initialAmount / 100 * 18) / 2);
//                                    roundSgstAmount = String.format("%.2f", SgstAmount);
//                                    textViewCgst.setText("CGST Amount (9%)");
//                                    textViewSgst.setText("SGST Amount (9%)");
//                                    textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                    textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                    totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                    roundTotalAmount = String.format("%.2f", totalAmount);
//                                    textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                    stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                    String doubleToStringFormat = String.format("%.2f", stringToDouble);
//                                    trimString = doubleToStringFormat.substring(0, doubleToStringFormat.length() - 3);
//                                    stringToLong = Long.parseLong(trimString);
//                                    realIntegerPart = stringToLong / 100;
//                                    realDecimalPart = stringToLong % 100;
//                                    StringBuilder wordFormat = new StringBuilder();
//                                    if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                        wordFormat.append("Zero Rupees Only.");
//                                    } else if (realIntegerPart == 0 && realDecimalPart != 0) {
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(DecimalToWord + " Paise Only.");
//                                    } else if (realIntegerPart != 0 && realDecimalPart == 0) {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        wordFormat.append(IntegerToWord + " Rupees Only.");
//                                    } else {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord + " Paise Only. ");
//                                    }
//
//                                    totalAmountWord.setText(wordFormat);
//
//
//                                } else if (chip28Percent.isChecked()) {
//                                    initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                    netAmount = initialAmount;
//                                    roundNetAmount = String.format("%.2f", netAmount);
//                                    textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                    CgstAmount = ((initialAmount / 100 * 28) / 2);
//                                    roundCgstAmount = String.format("%.2f", CgstAmount);
//                                    SgstAmount = ((initialAmount / 100 * 28) / 2);
//                                    roundSgstAmount = String.format("%.2f", SgstAmount);
//                                    textViewCgst.setText("CGST Amount (14%)");
//                                    textViewSgst.setText("SGST Amount (14%)");
//                                    textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                    textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                    totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                    roundTotalAmount = String.format("%.2f", totalAmount);
//                                    textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                    stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                    String doubleToStringFormat = String.format("%.2f", stringToDouble);
//                                    trimString = doubleToStringFormat.substring(0, doubleToStringFormat.length() - 3);
//                                    stringToLong = Long.parseLong(trimString);
//                                    realIntegerPart = stringToLong / 100;
//                                    realDecimalPart = stringToLong % 100;
//                                    StringBuilder wordFormat = new StringBuilder();
//                                    if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                        wordFormat.append("Zero Rupees Only.");
//                                    } else if (realIntegerPart == 0 && realDecimalPart != 0) {
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(DecimalToWord + " Paise Only.");
//                                    } else if (realIntegerPart != 0 && realDecimalPart == 0) {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        wordFormat.append(IntegerToWord + " Rupees Only.");
//                                    } else {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord + " Paise Only. ");
//                                    }
//
//                                    totalAmountWord.setText(wordFormat);
//
//
//                                }
//                            }
//                        } else if (radioIncluding.isChecked()) {
//                            if (radioCgstSgst.isChecked()) {
//                                if (chip3Percent.isChecked()) {
//
//                                    initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                    netAmount = (initialAmount / 1.03);
//                                    roundNetAmount = String.format("%.2f", netAmount);
//                                    textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                    CgstAmount = ((netAmount / 100 * 3) / 2);
//                                    roundCgstAmount = String.format("%.2f", CgstAmount);
//                                    SgstAmount = ((netAmount / 100 * 3) / 2);
//                                    roundSgstAmount = String.format("%.2f", SgstAmount);
//                                    textViewCgst.setText("CGST Amount (1.5%)");
//                                    textViewSgst.setText("SGST Amount (1.5%)");
//                                    textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                    textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                    totalAmount = initialAmount;
//                                    roundTotalAmount = String.format("%.2f", totalAmount);
//                                    textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                    stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                    String doubleToStringFormat = String.format("%.2f", stringToDouble);
//                                    trimString = doubleToStringFormat.substring(0, doubleToStringFormat.length() - 3);
//                                    stringToLong = Long.parseLong(trimString);
//                                    realIntegerPart = stringToLong / 100;
//                                    realDecimalPart = stringToLong % 100;
//                                    StringBuilder wordFormat = new StringBuilder();
//                                    if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                        wordFormat.append("Zero Rupees Only.");
//                                    } else if (realIntegerPart == 0 && realDecimalPart != 0) {
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(DecimalToWord + " Paise Only.");
//                                    } else if (realIntegerPart != 0 && realDecimalPart == 0) {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        wordFormat.append(IntegerToWord + " Rupees Only.");
//                                    } else {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord + " Paise Only. ");
//                                    }
//
//                                    totalAmountWord.setText(wordFormat);
//
//                                } else if (chip5Percent.isChecked()) {
//                                    initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                    netAmount = (initialAmount / 1.05);
//                                    roundNetAmount = String.format("%.2f", netAmount);
//                                    textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                    CgstAmount = ((netAmount / 100 * 5) / 2);
//                                    roundCgstAmount = String.format("%.2f", CgstAmount);
//                                    SgstAmount = ((netAmount / 100 * 5) / 2);
//                                    roundSgstAmount = String.format("%.2f", SgstAmount);
//                                    textViewCgst.setText("CGST Amount (2.5%)");
//                                    textViewSgst.setText("SGST Amount (2.5%)");
//                                    textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                    textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                    totalAmount = initialAmount;
//                                    roundTotalAmount = String.format("%.2f", totalAmount);
//                                    textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                    stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                    String doubleToStringFormat = String.format("%.2f", stringToDouble);
//                                    trimString = doubleToStringFormat.substring(0, doubleToStringFormat.length() - 3);
//                                    stringToLong = Long.parseLong(trimString);
//                                    realIntegerPart = stringToLong / 100;
//                                    realDecimalPart = stringToLong % 100;
//                                    StringBuilder wordFormat = new StringBuilder();
//                                    if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                        wordFormat.append("Zero Rupees Only.");
//                                    } else if (realIntegerPart == 0 && realDecimalPart != 0) {
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(DecimalToWord + " Paise Only.");
//                                    } else if (realIntegerPart != 0 && realDecimalPart == 0) {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        wordFormat.append(IntegerToWord + " Rupees Only.");
//                                    } else {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord + " Paise Only. ");
//                                    }
//
//                                    totalAmountWord.setText(wordFormat);
//
//                                } else if (chip12Percent.isChecked()) {
//                                    initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                    netAmount = (initialAmount / 1.12);
//                                    roundNetAmount = String.format("%.2f", netAmount);
//                                    textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                    CgstAmount = ((netAmount / 100 * 12) / 2);
//                                    roundCgstAmount = String.format("%.2f", CgstAmount);
//                                    SgstAmount = ((netAmount / 100 * 12) / 2);
//                                    roundSgstAmount = String.format("%.2f", SgstAmount);
//                                    textViewCgst.setText("CGST Amount (6%)");
//                                    textViewSgst.setText("SGST Amount (6%)");
//                                    textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                    textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                    totalAmount = initialAmount;
//                                    roundTotalAmount = String.format("%.2f", totalAmount);
//                                    textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                    stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                    String doubleToStringFormat = String.format("%.2f", stringToDouble);
//                                    trimString = doubleToStringFormat.substring(0, doubleToStringFormat.length() - 3);
//                                    stringToLong = Long.parseLong(trimString);
//                                    realIntegerPart = stringToLong / 100;
//                                    realDecimalPart = stringToLong % 100;
//                                    StringBuilder wordFormat = new StringBuilder();
//                                    if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                        wordFormat.append("Zero Rupees Only.");
//                                    } else if (realIntegerPart == 0 && realDecimalPart != 0) {
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(DecimalToWord + " Paise Only.");
//                                    } else if (realIntegerPart != 0 && realDecimalPart == 0) {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        wordFormat.append(IntegerToWord + " Rupees Only.");
//                                    } else {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord + " Paise Only. ");
//                                    }
//
//                                    totalAmountWord.setText(wordFormat);
//
//
//                                } else if (chip18Percent.isChecked()) {
//
//                                    initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                    netAmount = (initialAmount / 1.18);
//                                    roundNetAmount = String.format("%.2f", netAmount);
//                                    textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                    CgstAmount = ((netAmount / 100 * 18) / 2);
//                                    roundCgstAmount = String.format("%.2f", CgstAmount);
//                                    SgstAmount = ((netAmount / 100 * 18) / 2);
//                                    roundSgstAmount = String.format("%.2f", SgstAmount);
//                                    textViewCgst.setText("CGST Amount (9%)");
//                                    textViewSgst.setText("SGST Amount (9%)");
//                                    textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                    textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                    totalAmount = initialAmount;
//                                    roundTotalAmount = String.format("%.2f", totalAmount);
//                                    textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                    stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                    String doubleToStringFormat = String.format("%.2f", stringToDouble);
//                                    trimString = doubleToStringFormat.substring(0, doubleToStringFormat.length() - 3);
//                                    stringToLong = Long.parseLong(trimString);
//                                    realIntegerPart = stringToLong / 100;
//                                    realDecimalPart = stringToLong % 100;
//                                    StringBuilder wordFormat = new StringBuilder();
//                                    if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                        wordFormat.append("Zero Rupees Only.");
//                                    } else if (realIntegerPart == 0 && realDecimalPart != 0) {
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(DecimalToWord + " Paise Only.");
//                                    } else if (realIntegerPart != 0 && realDecimalPart == 0) {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        wordFormat.append(IntegerToWord + " Rupees Only.");
//                                    } else {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord + " Paise Only. ");
//                                    }
//
//                                    totalAmountWord.setText(wordFormat);
//
//
//                                } else if (chip28Percent.isChecked()) {
//
//                                    initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                    netAmount = (initialAmount / 1.28);
//                                    roundNetAmount = String.format("%.2f", netAmount);
//                                    textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                    CgstAmount = ((netAmount / 100 * 28) / 2);
//                                    roundCgstAmount = String.format("%.2f", CgstAmount);
//                                    SgstAmount = ((netAmount / 100 * 28) / 2);
//                                    roundSgstAmount = String.format("%.2f", SgstAmount);
//                                    textViewCgst.setText("CGST Amount (14%)");
//                                    textViewSgst.setText("SGST Amount (14%)");
//                                    textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                    textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                    totalAmount = initialAmount;
//                                    roundTotalAmount = String.format("%.2f", totalAmount);
//                                    textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                    stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                    String doubleToStringFormat = String.format("%.2f", stringToDouble);
//                                    trimString = doubleToStringFormat.substring(0, doubleToStringFormat.length() - 3);
//                                    stringToLong = Long.parseLong(trimString);
//                                    realIntegerPart = stringToLong / 100;
//                                    realDecimalPart = stringToLong % 100;
//                                    StringBuilder wordFormat = new StringBuilder();
//                                    if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                        wordFormat.append("Zero Rupees Only.");
//                                    } else if (realIntegerPart == 0 && realDecimalPart != 0) {
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(DecimalToWord + " Paise Only.");
//                                    } else if (realIntegerPart != 0 && realDecimalPart == 0) {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        wordFormat.append(IntegerToWord + " Rupees Only.");
//                                    } else {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord + " Paise Only. ");
//                                    }
//
//                                    totalAmountWord.setText(wordFormat);
//
//
//                                }
//
//
//                            } else if (radioIgst.isChecked()) {
//                                if (chip3Percent.isChecked()) {
//
//
//                                    initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                    netAmount = (initialAmount / 1.03);
//                                    roundNetAmount = String.format("%.2f", netAmount);
//                                    textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                    IgstAmount = (netAmount / 100 * 3);
//                                    roundIgstAmount = String.format("%.2f", IgstAmount);
//                                    textViewIgst.setText("IGST Amount (3%)");
//                                    textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                    totalAmount = initialAmount;
//                                    roundTotalAmount = String.format("%.2f", totalAmount);
//                                    textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                    stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                    String doubleToStringFormat = String.format("%.2f", stringToDouble);
//                                    trimString = doubleToStringFormat.substring(0, doubleToStringFormat.length() - 3);
//                                    stringToLong = Long.parseLong(trimString);
//                                    realIntegerPart = stringToLong / 100;
//                                    realDecimalPart = stringToLong % 100;
//                                    StringBuilder wordFormat = new StringBuilder();
//                                    if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                        wordFormat.append("Zero Rupees Only.");
//                                    } else if (realIntegerPart == 0 && realDecimalPart != 0) {
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(DecimalToWord + " Paise Only.");
//                                    } else if (realIntegerPart != 0 && realDecimalPart == 0) {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        wordFormat.append(IntegerToWord + " Rupees Only.");
//                                    } else {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord + " Paise Only. ");
//                                    }
//
//                                    totalAmountWord.setText(wordFormat);
//
//
//                                } else if (chip5Percent.isChecked()) {
//
//
//                                    initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                    netAmount = (initialAmount / 1.05);
//                                    roundNetAmount = String.format("%.2f", netAmount);
//                                    textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                    IgstAmount = (netAmount / 100 * 5);
//                                    roundIgstAmount = String.format("%.2f", IgstAmount);
//                                    textViewIgst.setText("IGST Amount (5%)");
//                                    textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                    totalAmount = initialAmount;
//                                    roundTotalAmount = String.format("%.2f", totalAmount);
//                                    textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                    stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                    String doubleToStringFormat = String.format("%.2f", stringToDouble);
//                                    trimString = doubleToStringFormat.substring(0, doubleToStringFormat.length() - 3);
//                                    stringToLong = Long.parseLong(trimString);
//                                    realIntegerPart = stringToLong / 100;
//                                    realDecimalPart = stringToLong % 100;
//                                    StringBuilder wordFormat = new StringBuilder();
//                                    if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                        wordFormat.append("Zero Rupees Only.");
//                                    } else if (realIntegerPart == 0 && realDecimalPart != 0) {
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(DecimalToWord + " Paise Only.");
//                                    } else if (realIntegerPart != 0 && realDecimalPart == 0) {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        wordFormat.append(IntegerToWord + " Rupees Only.");
//                                    } else {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord + " Paise Only. ");
//                                    }
//
//                                    totalAmountWord.setText(wordFormat);
//
//                                } else if (chip12Percent.isChecked()) {
////
//
//                                    initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                    netAmount = (initialAmount / 1.12);
//                                    roundNetAmount = String.format("%.2f", netAmount);
//                                    textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                    IgstAmount = (netAmount / 100 * 12);
//                                    roundIgstAmount = String.format("%.2f", IgstAmount);
//                                    textViewIgst.setText("IGST Amount (12%)");
//                                    textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                    totalAmount = initialAmount;
//                                    roundTotalAmount = String.format("%.2f", totalAmount);
//                                    textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                    stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                    String doubleToStringFormat = String.format("%.2f", stringToDouble);
//                                    trimString = doubleToStringFormat.substring(0, doubleToStringFormat.length() - 3);
//                                    stringToLong = Long.parseLong(trimString);
//                                    realIntegerPart = stringToLong / 100;
//                                    realDecimalPart = stringToLong % 100;
//                                    StringBuilder wordFormat = new StringBuilder();
//                                    if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                        wordFormat.append("Zero Rupees Only.");
//                                    } else if (realIntegerPart == 0 && realDecimalPart != 0) {
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(DecimalToWord + " Paise Only.");
//                                    } else if (realIntegerPart != 0 && realDecimalPart == 0) {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        wordFormat.append(IntegerToWord + " Rupees Only.");
//                                    } else {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord + " Paise Only. ");
//                                    }
//
//                                    totalAmountWord.setText(wordFormat);
//
//                                } else if (chip18Percent.isChecked()) {
//
//
//                                    initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                    netAmount = (initialAmount / 1.18);
//                                    roundNetAmount = String.format("%.2f", netAmount);
//                                    textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                    IgstAmount = (netAmount / 100 * 18);
//                                    roundIgstAmount = String.format("%.2f", IgstAmount);
//                                    textViewIgst.setText("IGST Amount (18%)");
//                                    textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                    totalAmount = initialAmount;
//                                    roundTotalAmount = String.format("%.2f", totalAmount);
//                                    textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                    stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                    String doubleToStringFormat = String.format("%.2f", stringToDouble);
//                                    trimString = doubleToStringFormat.substring(0, doubleToStringFormat.length() - 3);
//                                    stringToLong = Long.parseLong(trimString);
//                                    realIntegerPart = stringToLong / 100;
//                                    realDecimalPart = stringToLong % 100;
//                                    StringBuilder wordFormat = new StringBuilder();
//                                    if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                        wordFormat.append("Zero Rupees Only.");
//                                    } else if (realIntegerPart == 0 && realDecimalPart != 0) {
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(DecimalToWord + " Paise Only.");
//                                    } else if (realIntegerPart != 0 && realDecimalPart == 0) {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        wordFormat.append(IntegerToWord + " Rupees Only.");
//                                    } else {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord + " Paise Only. ");
//                                    }
//
//                                    totalAmountWord.setText(wordFormat);
//
//                                } else if (chip28Percent.isChecked()) {
//
//                                    initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                    netAmount = (initialAmount / 1.28);
//                                    roundNetAmount = String.format("%.2f", netAmount);
//                                    textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                    IgstAmount = (netAmount / 100 * 28);
//                                    roundIgstAmount = String.format("%.2f", IgstAmount);
//                                    textViewIgst.setText("IGST Amount (28%)");
//                                    textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                    totalAmount = initialAmount;
//                                    roundTotalAmount = String.format("%.2f", totalAmount);
//                                    textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                    stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                    String doubleToStringFormat = String.format("%.2f", stringToDouble);
//                                    trimString = doubleToStringFormat.substring(0, doubleToStringFormat.length() - 3);
//                                    stringToLong = Long.parseLong(trimString);
//                                    realIntegerPart = stringToLong / 100;
//                                    realDecimalPart = stringToLong % 100;
//                                    StringBuilder wordFormat = new StringBuilder();
//                                    if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                        wordFormat.append("Zero Rupees Only.");
//                                    } else if (realIntegerPart == 0 && realDecimalPart != 0) {
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(DecimalToWord + " Paise Only.");
//                                    } else if (realIntegerPart != 0 && realDecimalPart == 0) {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        wordFormat.append(IntegerToWord + " Rupees Only.");
//                                    } else {
//                                        LongToStringIntPart = String.valueOf(realIntegerPart);
//                                        LongToStringDecPart = String.valueOf(realDecimalPart);
//                                        IntegerToWord = Words.convert(LongToStringIntPart);
//                                        DecimalToWord = Words.convert(LongToStringDecPart);
//                                        wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord + " Paise Only. ");
//                                    }
//
//                                    totalAmountWord.setText(wordFormat);
//
//                                }
//                            }
//                        }
//                    } else {
//                        textViewCgstResult.setText("₹ 0");
//                        textViewSgstResult.setText("₹ 0");
//                        textViewIgstResult.setText("₹ 0");
//                        textViewTotalAmountResult.setText("₹ 0");
//                        textViewForResultNetAmount.setText("₹ 0");
//                        textViewTotalAmountResult.setText("₹ 0");
//                        totalAmountWord.setText("");
//                    }
//
//
////                        break;
////                    case R.id.radioIgst:
//
//                } else if(i == R.id.radioIgst) {
//
//
//                        newlayoutCgst.removeAllViews();
//                        newlayoutSgst.removeAllViews();
//
//                        newlayoutIgst.addView(textViewIgst);
//                        newlayoutIgst.addView(textViewIgstResult);
//
//
//
//
//
//                        if (!editTextInitialValue.getText().toString().equals("")) {
//                            if (radioExcluding.isChecked()) {
//                                if (radioIgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 3);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (3%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip5Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 5);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (5%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//                                    } else if (chip12Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 12);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (12%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip18Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 18);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (18%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip28Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 28);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (28%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//                                    }
//
//
//                                } else if (radioCgstSgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 3)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 3)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (1.5%)");
//                                        textViewSgst.setText("SGST Amount (1.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip5Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 5)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 5)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (2.5%)");
//                                        textViewSgst.setText("SGST Amount (2.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//
//                                    } else if (chip12Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 12)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 12)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (6%)");
//                                        textViewSgst.setText("SGST Amount (6%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip18Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 18)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 18)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (9%)");
//                                        textViewSgst.setText("SGST Amount (9%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//
//                                    } else if (chip28Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 28)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 28)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (14%)");
//                                        textViewSgst.setText("SGST Amount (14%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    }
//                                }
//                            } else if (radioIncluding.isChecked()) {
//                                if (radioCgstSgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.03);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 3)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 3)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (1.5%)");
//                                        textViewSgst.setText("SGST Amount (1.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip5Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.05);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 5)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 5)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (2.5%)");
//                                        textViewSgst.setText("SGST Amount (2.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip12Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.12);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 12)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 12)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (6%)");
//                                        textViewSgst.setText("SGST Amount (6%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip18Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.18);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 18)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 18)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (9%)");
//                                        textViewSgst.setText("SGST Amount (9%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip28Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.28);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 28)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 28)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (14%)");
//                                        textViewSgst.setText("SGST Amount (14%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    }
//
//
//                                } else if (radioIgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.03);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 3);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (3%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip5Percent.isChecked()) {
//
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.05);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 5);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (5%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip12Percent.isChecked()) {
////
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.12);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 12);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (12%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip18Percent.isChecked()) {
//
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.18);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 18);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (18%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip28Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.28);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 28);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (28%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    }
//                                }
//                            }
//                        } else {
//                            textViewCgstResult.setText("₹ 0");
//                            textViewSgstResult.setText("₹ 0");
//                            textViewIgstResult.setText("₹ 0");
//                            textViewTotalAmountResult.setText("₹ 0");
//                            textViewForResultNetAmount.setText("₹ 0");
//                            textViewTotalAmountResult.setText("₹ 0");
//                            totalAmountWord.setText("");
//                        }
//
//
////                        break;
//
////                    default:
////                        break;
//
//
//
//
//
//
//                }
//            }
//        });
//
//
//        radioExIN.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//
////                switch (i) {
////                    case R.id.radioExcluding:
//
//                if(i == R.id.radioExcluding){
//
//                        Double totalAmount;
//                        Double initialAmount;
//                        Double IgstAmount;
//                        Double CgstAmount;
//                        Double SgstAmount;
//                        Double netAmount;
//                        String roundNetAmount;
//                        String roundCgstAmount;
//                        String roundSgstAmount;
//                        String roundTotalAmount;
//                        String roundIgstAmount;
//                        Double stringToDouble;
//                        String trimString;
//                        long stringToLong;
//                        long realIntegerPart;
//                        long realDecimalPart;
//                        String LongToStringIntPart;
//                        String LongToStringDecPart;
//                        String IntegerToWord;
//                        String DecimalToWord;
//
//                        if (!editTextInitialValue.getText().toString().equals("")) {
//                            if (radioExcluding.isChecked()) {
//                                if (radioIgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 3);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (3%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip5Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 5);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (5%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//                                    } else if (chip12Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 12);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (12%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip18Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 18);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (18%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip28Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 28);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (28%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//                                    }
//
//
//                                } else if (radioCgstSgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 3)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 3)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (1.5%)");
//                                        textViewSgst.setText("SGST Amount (1.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip5Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 5)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 5)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (2.5%)");
//                                        textViewSgst.setText("SGST Amount (2.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//
//                                    } else if (chip12Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 12)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 12)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (6%)");
//                                        textViewSgst.setText("SGST Amount (6%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip18Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 18)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 18)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (9%)");
//                                        textViewSgst.setText("SGST Amount (9%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//
//                                    } else if (chip28Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 28)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 28)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (14%)");
//                                        textViewSgst.setText("SGST Amount (14%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    }
//                                }
//                            } else if (radioIncluding.isChecked()) {
//                                if (radioCgstSgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.03);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 3)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 3)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (1.5%)");
//                                        textViewSgst.setText("SGST Amount (1.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip5Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.05);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 5)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 5)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (2.5%)");
//                                        textViewSgst.setText("SGST Amount (2.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip12Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.12);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 12)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 12)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (6%)");
//                                        textViewSgst.setText("SGST Amount (6%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip18Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.18);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 18)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 18)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (9%)");
//                                        textViewSgst.setText("SGST Amount (9%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip28Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.28);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 28)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 28)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (14%)");
//                                        textViewSgst.setText("SGST Amount (14%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    }
//
//
//                                } else if (radioIgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.03);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 3);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (3%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip5Percent.isChecked()) {
//
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.05);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 5);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (5%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip12Percent.isChecked()) {
////
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.12);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 12);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (12%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip18Percent.isChecked()) {
//
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.18);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 18);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (18%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip28Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.28);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 28);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (28%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    }
//                                }
//                            }
//                        } else {
//                            textViewCgstResult.setText("₹ 0");
//                            textViewSgstResult.setText("₹ 0");
//                            textViewIgstResult.setText("₹ 0");
//                            textViewTotalAmountResult.setText("₹ 0");
//                            textViewForResultNetAmount.setText("₹ 0");
//                            textViewTotalAmountResult.setText("₹ 0");
//                            totalAmountWord.setText("");
//                        }
//
//
////                        break;
//
////                    case R.id.radioIncluding:
//                            }else if(i == R.id.radioIncluding){
//
//                        if (!editTextInitialValue.getText().toString().equals("")) {
//                            if (radioExcluding.isChecked()) {
//                                if (radioIgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 3);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (3%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip5Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 5);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (5%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//                                    } else if (chip12Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 12);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (12%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip18Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 18);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (18%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip28Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 28);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (28%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//                                    }
//
//
//                                } else if (radioCgstSgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 3)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 3)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (1.5%)");
//                                        textViewSgst.setText("SGST Amount (1.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip5Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 5)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 5)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (2.5%)");
//                                        textViewSgst.setText("SGST Amount (2.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//
//                                    } else if (chip12Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 12)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 12)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (6%)");
//                                        textViewSgst.setText("SGST Amount (6%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip18Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 18)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 18)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (9%)");
//                                        textViewSgst.setText("SGST Amount (9%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//
//                                    } else if (chip28Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 28)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 28)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (14%)");
//                                        textViewSgst.setText("SGST Amount (14%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    }
//                                }
//                            } else if (radioIncluding.isChecked()) {
//                                if (radioCgstSgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.03);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 3)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 3)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (1.5%)");
//                                        textViewSgst.setText("SGST Amount (1.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip5Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.05);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 5)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 5)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (2.5%)");
//                                        textViewSgst.setText("SGST Amount (2.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip12Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.12);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 12)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 12)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (6%)");
//                                        textViewSgst.setText("SGST Amount (6%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip18Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.18);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 18)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 18)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (9%)");
//                                        textViewSgst.setText("SGST Amount (9%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip28Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.28);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 28)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 28)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (14%)");
//                                        textViewSgst.setText("SGST Amount (14%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    }
//
//
//                                } else if (radioIgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.03);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 3);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (3%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip5Percent.isChecked()) {
//
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.05);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 5);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (5%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip12Percent.isChecked()) {
////
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.12);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 12);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (12%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip18Percent.isChecked()) {
//
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.18);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 18);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (18%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip28Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.28);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 28);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (28%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    }
//                                }
//                            }
//                        } else {
//                            textViewCgstResult.setText("₹ 0");
//                            textViewSgstResult.setText("₹ 0");
//                            textViewIgstResult.setText("₹ 0");
//                            textViewTotalAmountResult.setText("₹ 0");
//                            textViewForResultNetAmount.setText("₹ 0");
//                            textViewTotalAmountResult.setText("₹ 0");
//                            totalAmountWord.setText("");
//                        }
//
//
//
//                }
//
//
//
//            }
//        });
//
//        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(ChipGroup group, int checkedId) {
//
////                switch (checkedId) {
////                    case R.id.chip3Percent:
//
//                                        if(checkedId == R.id.chip3Percent){
//
//
////                        Double totalAmount;
////                        Double initialAmount;
////                        Double IgstAmount;
////                        Double CgstAmount;
////                        Double SgstAmount;
////                        Double netAmount;
////                        String roundNetAmount;
////                        String roundCgstAmount;
////                        String roundSgstAmount;
////                        String roundTotalAmount;
////                        String roundIgstAmount;
////                        Double stringToDouble;
////                        String trimString;
////                        long stringToLong;
////                        long realIntegerPart;
////                        long realDecimalPart;
////                        String LongToStringIntPart;
////                        String LongToStringDecPart;
////                        String IntegerToWord;
////                        String DecimalToWord;
//
//
//                        if (!editTextInitialValue.getText().toString().equals("")) {
//                            if (radioExcluding.isChecked()) {
//                                if (radioIgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 3);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (3%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip5Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 5);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (5%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//                                    } else if (chip12Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 12);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (12%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip18Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 18);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (18%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip28Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 28);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (28%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//                                    }
//
//
//                                } else if (radioCgstSgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 3)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 3)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (1.5%)");
//                                        textViewSgst.setText("SGST Amount (1.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip5Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 5)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 5)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (2.5%)");
//                                        textViewSgst.setText("SGST Amount (2.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//
//                                    } else if (chip12Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 12)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 12)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (6%)");
//                                        textViewSgst.setText("SGST Amount (6%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip18Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 18)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 18)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (9%)");
//                                        textViewSgst.setText("SGST Amount (9%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//
//                                    } else if (chip28Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 28)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 28)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (14%)");
//                                        textViewSgst.setText("SGST Amount (14%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    }
//                                }
//                            } else if (radioIncluding.isChecked()) {
//                                if (radioCgstSgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.03);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 3)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 3)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (1.5%)");
//                                        textViewSgst.setText("SGST Amount (1.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip5Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.05);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 5)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 5)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (2.5%)");
//                                        textViewSgst.setText("SGST Amount (2.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip12Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.12);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 12)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 12)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (6%)");
//                                        textViewSgst.setText("SGST Amount (6%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip18Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.18);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 18)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 18)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (9%)");
//                                        textViewSgst.setText("SGST Amount (9%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip28Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.28);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 28)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 28)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (14%)");
//                                        textViewSgst.setText("SGST Amount (14%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    }
//
//
//                                } else if (radioIgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.03);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 3);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (3%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip5Percent.isChecked()) {
//
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.05);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 5);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (5%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip12Percent.isChecked()) {
////
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.12);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 12);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (12%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip18Percent.isChecked()) {
//
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.18);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 18);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (18%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip28Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.28);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 28);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (28%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    }
//                                }
//                            }
//                        } else {
//                            textViewCgstResult.setText("₹ 0");
//                            textViewSgstResult.setText("₹ 0");
//                            textViewIgstResult.setText("₹ 0");
//                            textViewTotalAmountResult.setText("₹ 0");
//                            textViewForResultNetAmount.setText("₹ 0");
//                            textViewTotalAmountResult.setText("₹ 0");
//                            totalAmountWord.setText("");
//                        }
//
//
//
////                        break;
////                    case R.id.chip5Percent:
//
//                        }else if(checkedId == R.id.chip5Percent){
//
//                        if (!editTextInitialValue.getText().toString().equals("")) {
//                            if (radioExcluding.isChecked()) {
//                                if (radioIgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 3);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (3%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip5Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 5);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (5%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//                                    } else if (chip12Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 12);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (12%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip18Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 18);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (18%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip28Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 28);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (28%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//                                    }
//
//
//                                } else if (radioCgstSgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 3)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 3)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (1.5%)");
//                                        textViewSgst.setText("SGST Amount (1.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip5Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 5)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 5)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (2.5%)");
//                                        textViewSgst.setText("SGST Amount (2.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//
//                                    } else if (chip12Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 12)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 12)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (6%)");
//                                        textViewSgst.setText("SGST Amount (6%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip18Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 18)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 18)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (9%)");
//                                        textViewSgst.setText("SGST Amount (9%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//
//                                    } else if (chip28Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 28)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 28)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (14%)");
//                                        textViewSgst.setText("SGST Amount (14%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    }
//                                }
//                            } else if (radioIncluding.isChecked()) {
//                                if (radioCgstSgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.03);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 3)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 3)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (1.5%)");
//                                        textViewSgst.setText("SGST Amount (1.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip5Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.05);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 5)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 5)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (2.5%)");
//                                        textViewSgst.setText("SGST Amount (2.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip12Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.12);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 12)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 12)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (6%)");
//                                        textViewSgst.setText("SGST Amount (6%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip18Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.18);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 18)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 18)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (9%)");
//                                        textViewSgst.setText("SGST Amount (9%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip28Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.28);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 28)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 28)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (14%)");
//                                        textViewSgst.setText("SGST Amount (14%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    }
//
//
//                                } else if (radioIgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.03);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 3);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (3%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip5Percent.isChecked()) {
//
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.05);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 5);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (5%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip12Percent.isChecked()) {
////
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.12);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 12);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (12%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip18Percent.isChecked()) {
//
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.18);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 18);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (18%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip28Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.28);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 28);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (28%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    }
//                                }
//                            }
//                        } else {
//                            textViewCgstResult.setText("₹ 0");
//                            textViewSgstResult.setText("₹ 0");
//                            textViewIgstResult.setText("₹ 0");
//                            textViewTotalAmountResult.setText("₹ 0");
//                            textViewForResultNetAmount.setText("₹ 0");
//                            textViewTotalAmountResult.setText("₹ 0");
//                            totalAmountWord.setText("");
//                        }
//
////                        break;
////                    case R.id.chip12Percent:
//
//                                                }else if(checkedId == R.id.chip12Percent){
//
//
//
//                        if (!editTextInitialValue.getText().toString().equals("")) {
//                            if (radioExcluding.isChecked()) {
//                                if (radioIgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 3);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (3%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip5Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 5);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (5%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//                                    } else if (chip12Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 12);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (12%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip18Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 18);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (18%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip28Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 28);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (28%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//                                    }
//
//
//                                } else if (radioCgstSgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 3)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 3)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (1.5%)");
//                                        textViewSgst.setText("SGST Amount (1.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip5Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 5)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 5)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (2.5%)");
//                                        textViewSgst.setText("SGST Amount (2.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//
//                                    } else if (chip12Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 12)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 12)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (6%)");
//                                        textViewSgst.setText("SGST Amount (6%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip18Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 18)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 18)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (9%)");
//                                        textViewSgst.setText("SGST Amount (9%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//
//                                    } else if (chip28Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 28)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 28)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (14%)");
//                                        textViewSgst.setText("SGST Amount (14%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    }
//                                }
//                            } else if (radioIncluding.isChecked()) {
//                                if (radioCgstSgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.03);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 3)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 3)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (1.5%)");
//                                        textViewSgst.setText("SGST Amount (1.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip5Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.05);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 5)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 5)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (2.5%)");
//                                        textViewSgst.setText("SGST Amount (2.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip12Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.12);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 12)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 12)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (6%)");
//                                        textViewSgst.setText("SGST Amount (6%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip18Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.18);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 18)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 18)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (9%)");
//                                        textViewSgst.setText("SGST Amount (9%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip28Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.28);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 28)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 28)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (14%)");
//                                        textViewSgst.setText("SGST Amount (14%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    }
//
//
//                                } else if (radioIgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.03);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 3);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (3%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip5Percent.isChecked()) {
//
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.05);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 5);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (5%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip12Percent.isChecked()) {
////
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.12);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 12);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (12%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip18Percent.isChecked()) {
//
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.18);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 18);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (18%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip28Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.28);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 28);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (28%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    }
//                                }
//                            }
//                        } else {
//                            textViewCgstResult.setText("₹ 0");
//                            textViewSgstResult.setText("₹ 0");
//                            textViewIgstResult.setText("₹ 0");
//                            textViewTotalAmountResult.setText("₹ 0");
//                            textViewForResultNetAmount.setText("₹ 0");
//                            textViewTotalAmountResult.setText("₹ 0");
//                            totalAmountWord.setText("");
//                        }
//
//
//
////                        break;
////                    case R.id.chip18Percent:
//                        }else if(checkedId == R.id.chip18Percent){
//
//
//
//
//
//
//                        if (!editTextInitialValue.getText().toString().equals("")) {
//                            if (radioExcluding.isChecked()) {
//                                if (radioIgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 3);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (3%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip5Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 5);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (5%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//                                    } else if (chip12Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 12);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (12%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip18Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 18);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (18%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip28Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 28);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (28%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//                                    }
//
//
//                                } else if (radioCgstSgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 3)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 3)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (1.5%)");
//                                        textViewSgst.setText("SGST Amount (1.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip5Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 5)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 5)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (2.5%)");
//                                        textViewSgst.setText("SGST Amount (2.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//
//                                    } else if (chip12Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 12)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 12)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (6%)");
//                                        textViewSgst.setText("SGST Amount (6%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip18Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 18)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 18)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (9%)");
//                                        textViewSgst.setText("SGST Amount (9%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//
//                                    } else if (chip28Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 28)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 28)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (14%)");
//                                        textViewSgst.setText("SGST Amount (14%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    }
//                                }
//                            } else if (radioIncluding.isChecked()) {
//                                if (radioCgstSgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.03);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 3)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 3)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (1.5%)");
//                                        textViewSgst.setText("SGST Amount (1.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip5Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.05);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 5)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 5)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (2.5%)");
//                                        textViewSgst.setText("SGST Amount (2.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip12Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.12);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 12)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 12)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (6%)");
//                                        textViewSgst.setText("SGST Amount (6%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip18Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.18);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 18)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 18)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (9%)");
//                                        textViewSgst.setText("SGST Amount (9%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip28Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.28);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 28)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 28)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (14%)");
//                                        textViewSgst.setText("SGST Amount (14%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    }
//
//
//                                } else if (radioIgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.03);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 3);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (3%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip5Percent.isChecked()) {
//
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.05);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 5);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (5%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip12Percent.isChecked()) {
////
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.12);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 12);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (12%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip18Percent.isChecked()) {
//
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.18);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 18);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (18%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip28Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.28);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 28);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (28%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    }
//                                }
//                            }
//                        } else {
//                            textViewCgstResult.setText("₹ 0");
//                            textViewSgstResult.setText("₹ 0");
//                            textViewIgstResult.setText("₹ 0");
//                            textViewTotalAmountResult.setText("₹ 0");
//                            textViewForResultNetAmount.setText("₹ 0");
//                            textViewTotalAmountResult.setText("₹ 0");
//                            totalAmountWord.setText("");
//                        }
//
////                        break;
////                    case R.id.chip28Percent:
//                            }else if(checkedId == R.id.chip28Percent){
//
//
//                        if (!editTextInitialValue.getText().toString().equals("")) {
//                            if (radioExcluding.isChecked()) {
//                                if (radioIgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 3);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (3%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip5Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 5);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (5%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//                                    } else if (chip12Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 12);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (12%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip18Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 18);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (18%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip28Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (initialAmount / 100 * 28);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (28%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount + IgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//                                    }
//
//
//                                } else if (radioCgstSgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 3)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 3)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (1.5%)");
//                                        textViewSgst.setText("SGST Amount (1.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip5Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 5)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 5)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (2.5%)");
//                                        textViewSgst.setText("SGST Amount (2.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//
//                                    } else if (chip12Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 12)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 12)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (6%)");
//                                        textViewSgst.setText("SGST Amount (6%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip18Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 18)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 18)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (9%)");
//                                        textViewSgst.setText("SGST Amount (9%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//
//                                    } else if (chip28Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = initialAmount;
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((initialAmount / 100 * 28)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((initialAmount / 100 * 28)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (14%)");
//                                        textViewSgst.setText("SGST Amount (14%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount + CgstAmount + SgstAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    }
//                                }
//                            } else if (radioIncluding.isChecked()) {
//                                if (radioCgstSgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.03);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 3)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 3)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (1.5%)");
//                                        textViewSgst.setText("SGST Amount (1.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip5Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.05);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 5)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 5)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (2.5%)");
//                                        textViewSgst.setText("SGST Amount (2.5%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip12Percent.isChecked()) {
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.12);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 12)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 12)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (6%)");
//                                        textViewSgst.setText("SGST Amount (6%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip18Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.18);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 18)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 18)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (9%)");
//                                        textViewSgst.setText("SGST Amount (9%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip28Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.28);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        CgstAmount = ((netAmount / 100 * 28)/2);
//                                        roundCgstAmount = String.format("%.2f",CgstAmount);
//                                        SgstAmount = ((netAmount / 100 * 28)/2);
//                                        roundSgstAmount = String.format("%.2f",SgstAmount);
//                                        textViewCgst.setText("CGST Amount (14%)");
//                                        textViewSgst.setText("SGST Amount (14%)");
//                                        textViewCgstResult.setText("₹ " + roundCgstAmount);
//                                        textViewSgstResult.setText("₹ " + roundSgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    }
//
//
//                                } else if (radioIgst.isChecked()) {
//                                    if (chip3Percent.isChecked()) {
//
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.03);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 3);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (3%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//
//                                    } else if (chip5Percent.isChecked()) {
//
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.05);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 5);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (5%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip12Percent.isChecked()) {
////
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.12);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 12);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (12%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip18Percent.isChecked()) {
//
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.18);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 18);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (18%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    } else if (chip28Percent.isChecked()) {
//
//                                        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                                        netAmount = (initialAmount/1.28);
//                                        roundNetAmount = String.format("%.2f",netAmount);
//                                        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                                        IgstAmount = (netAmount / 100 * 28);
//                                        roundIgstAmount = String.format("%.2f",IgstAmount);
//                                        textViewIgst.setText("IGST Amount (28%)");
//                                        textViewIgstResult.setText("₹ " + roundIgstAmount);
//                                        totalAmount = initialAmount;
//                                        roundTotalAmount = String.format("%.2f",totalAmount);
//                                        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                                        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                                        String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                                        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                                        stringToLong = Long.parseLong(trimString);
//                                        realIntegerPart = stringToLong/100;
//                                        realDecimalPart = stringToLong%100;
//                                        StringBuilder wordFormat = new StringBuilder();
//                                        if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                            wordFormat.append("Zero Rupees Only.");
//                                        } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(DecimalToWord + " Paise Only.");
//                                        } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            wordFormat.append(IntegerToWord + " Rupees Only.");
//                                        } else {
//                                            LongToStringIntPart = String.valueOf(realIntegerPart);
//                                            LongToStringDecPart = String.valueOf(realDecimalPart);
//                                            IntegerToWord = Words.convert(LongToStringIntPart);
//                                            DecimalToWord = Words.convert(LongToStringDecPart);
//                                            wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                                        }
//
//                                        totalAmountWord.setText(wordFormat);
//
//                                    }
//                                }
//                            }
//                        } else {
//                            textViewCgstResult.setText("₹ 0");
//                            textViewSgstResult.setText("₹ 0");
//                            textViewIgstResult.setText("₹ 0");
//                            textViewTotalAmountResult.setText("₹ 0");
//                            textViewForResultNetAmount.setText("₹ 0");
//                            textViewTotalAmountResult.setText("₹ 0");
//                            totalAmountWord.setText("");
//                        }
//
////                        break;
////                    default:
////                        break;
//                }
//            }
//        });
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_gst, menu);
//        return true;
//    }
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        View v = getCurrentFocus();
//
//        if (v != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
//                v instanceof EditText &&
//                !v.getClass().getName().startsWith("android.webkit.")) {
//            int[] sourceCoordinates = new int[2];
//            v.getLocationOnScreen(sourceCoordinates);
//            float x = ev.getRawX() + v.getLeft() - sourceCoordinates[0];
//            float y = ev.getRawY() + v.getTop() - sourceCoordinates[1];
//
//            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom()) {
//                hideKeyboard(this);
//            }
//
//        }
//        return super.dispatchTouchEvent(ev);
//    }
//
//    private void hideKeyboard(Activity activity) {
//        if (activity != null && activity.getWindow() != null) {
//            activity.getWindow().getDecorView();
//            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
//            if (imm != null) {
//                imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
//            }
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        this.finish();
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//        if (id == R.id.ShareButton) {
//            final TextView textViewIgst = new TextView(Gst.this);
//            final TextView textViewIgstResult = new TextView(Gst.this);
//
//            final TextView textViewSgst = new TextView(Gst.this);
//            final TextView textViewSgstResult = new TextView(Gst.this);
//
//            final TextView textViewCgst = new TextView(Gst.this);
//            final TextView textViewCgstResult = new TextView(Gst.this);
//            final TextView textViewTotalAmountResult = new TextView(Gst.this);
//            final TextView textViewForResultNetAmount = new TextView(Gst.this);
//
//
//            Double totalAmount;
//            Double initialAmount;
//            Double IgstAmount;
//            Double CgstAmount;
//            Double SgstAmount;
//            Double netAmount;
//            String roundNetAmount;
//            String roundCgstAmount;
//            String roundSgstAmount;
//            String roundTotalAmount;
//            String roundIgstAmount;
//            Double stringToDouble;
//            String trimString;
//            long stringToLong;
//            long realIntegerPart;
//            long realDecimalPart;
//            String LongToStringIntPart;
//            String LongToStringDecPart;
//            String IntegerToWord;
//            String DecimalToWord;
//
//            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//            DateFormat timeFormat = new SimpleDateFormat("hh:mm a");
//            Date date = new Date();
//            StringBuilder forShare = new StringBuilder();
//
//
//            forShare.append("Date :  ");
//            forShare.append(dateFormat.format(date));
//            forShare.append('\n');
//            forShare.append("Time :  ");
//            forShare.append(timeFormat.format(date));
//            forShare.append('\n');
//            forShare.append('\n');
//
//
//
//            if (!editTextInitialValue.getText().toString().equals("")) {
//                if (radioExcluding.isChecked()) {
//                    if (radioIgst.isChecked()) {
//                        if (chip3Percent.isChecked()) {
//
//                            initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                            netAmount = initialAmount;
//                            roundNetAmount = String.format("%.2f",netAmount);
//                            textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                            IgstAmount = (initialAmount / 100 * 3);
//                            roundIgstAmount = String.format("%.2f",IgstAmount);
//                            textViewIgst.setText("IGST Amount (3%)");
//                            textViewIgstResult.setText("₹ " + roundIgstAmount);
//                            totalAmount = initialAmount + IgstAmount;
//                            roundTotalAmount = String.format("%.2f",totalAmount);
//                            textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                            stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                            String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                            trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                            stringToLong = Long.parseLong(trimString);
//                            realIntegerPart = stringToLong/100;
//                            realDecimalPart = stringToLong%100;
//                            StringBuilder wordFormat = new StringBuilder();
//                            if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                wordFormat.append("Zero Rupees Only.");
//                            } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(DecimalToWord + " Paise Only.");
//                            } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                wordFormat.append(IntegerToWord + " Rupees Only.");
//                            } else {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                            }
//
////                            totalAmountWord.setText(wordFormat);
//                            forShare.append("• Net Amount = ");
//                            forShare.append(textViewForResultNetAmount.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• IGST 3% = ");
//                            forShare.append(textViewIgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount = ");
//                            forShare.append(textViewTotalAmountResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount In Words = ");
//                            forShare.append(wordFormat);
//                            forShare.append('\n');
//                            forShare.append('\n');
//                            forShare.append("• Shared via Total Paisa App : Cash counter and  Gst Calculator");
//                            forShare.append('\n');
//                            forShare.append("• Download from Play Store: https://play.google.com/store/apps/details?id=com.vibhunorby.totalpaisa");
//
//
//
//                        } else if (chip5Percent.isChecked()) {
//
//                            initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                            netAmount = initialAmount;
//                            roundNetAmount = String.format("%.2f",netAmount);
//                            textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                            IgstAmount = (initialAmount / 100 * 5);
//                            roundIgstAmount = String.format("%.2f",IgstAmount);
//                            textViewIgst.setText("IGST Amount (5%)");
//                            textViewIgstResult.setText("₹ " + roundIgstAmount);
//                            totalAmount = initialAmount + IgstAmount;
//                            roundTotalAmount = String.format("%.2f",totalAmount);
//                            textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                            stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                            String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                            trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                            stringToLong = Long.parseLong(trimString);
//                            realIntegerPart = stringToLong/100;
//                            realDecimalPart = stringToLong%100;
//                            StringBuilder wordFormat = new StringBuilder();
//                            if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                wordFormat.append("Zero Rupees Only.");
//                            } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(DecimalToWord + " Paise Only.");
//                            } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                wordFormat.append(IntegerToWord + " Rupees Only.");
//                            } else {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                            }
//
////                            totalAmountWord.setText(wordFormat);
//
//                            forShare.append("• Net Amount = ");
//                            forShare.append(textViewForResultNetAmount.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• IGST 5% = ");
//                            forShare.append(textViewIgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount = ");
//                            forShare.append(textViewTotalAmountResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount In Words = ");
//                            forShare.append(wordFormat);
//                            forShare.append('\n');
//                            forShare.append('\n');
//                            forShare.append("• Shared via Total Paisa App : Cash counter and  Gst Calculator");
//                            forShare.append('\n');
//                            forShare.append("•  Download from Play Store: https://play.google.com/store/apps/details?id=com.vibhunorby.totalpaisa");
//
//                        } else if (chip12Percent.isChecked()) {
//                            initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                            netAmount = initialAmount;
//                            roundNetAmount = String.format("%.2f",netAmount);
//                            textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                            IgstAmount = (initialAmount / 100 * 12);
//                            roundIgstAmount = String.format("%.2f",IgstAmount);
//                            textViewIgst.setText("IGST Amount (12%)");
//                            textViewIgstResult.setText("₹ " + roundIgstAmount);
//                            totalAmount = initialAmount + IgstAmount;
//                            roundTotalAmount = String.format("%.2f",totalAmount);
//                            textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                            stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                            String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                            trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                            stringToLong = Long.parseLong(trimString);
//                            realIntegerPart = stringToLong/100;
//                            realDecimalPart = stringToLong%100;
//                            StringBuilder wordFormat = new StringBuilder();
//                            if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                wordFormat.append("Zero Rupees Only.");
//                            } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(DecimalToWord + " Paise Only.");
//                            } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                wordFormat.append(IntegerToWord + " Rupees Only.");
//                            } else {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                            }
//
////                            totalAmountWord.setText(wordFormat);
//
//
//                            forShare.append("• Net Amount = ");
//                            forShare.append(textViewForResultNetAmount.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• IGST 12% = ");
//                            forShare.append(textViewIgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount = ");
//                            forShare.append(textViewTotalAmountResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount In Words = ");
//                            forShare.append(wordFormat);
//                            forShare.append('\n');
//                            forShare.append('\n');
//                            forShare.append("• Shared via Total Paisa App : Cash counter and  Gst Calculator");
//                            forShare.append('\n');
//                            forShare.append("•  Download from Play Store: https://play.google.com/store/apps/details?id=com.vibhunorby.totalpaisa");
//
//                        } else if (chip18Percent.isChecked()) {
//                            initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                            netAmount = initialAmount;
//                            roundNetAmount = String.format("%.2f",netAmount);
//                            textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                            IgstAmount = (initialAmount / 100 * 18);
//                            roundIgstAmount = String.format("%.2f",IgstAmount);
//                            textViewIgst.setText("IGST Amount (18%)");
//                            textViewIgstResult.setText("₹ " + roundIgstAmount);
//                            totalAmount = initialAmount + IgstAmount;
//                            roundTotalAmount = String.format("%.2f",totalAmount);
//                            textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                            stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                            String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                            trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                            stringToLong = Long.parseLong(trimString);
//                            realIntegerPart = stringToLong/100;
//                            realDecimalPart = stringToLong%100;
//                            StringBuilder wordFormat = new StringBuilder();
//                            if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                wordFormat.append("Zero Rupees Only.");
//                            } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(DecimalToWord + " Paise Only.");
//                            } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                wordFormat.append(IntegerToWord + " Rupees Only.");
//                            } else {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                            }
//
////                            totalAmountWord.setText(wordFormat);
//
//                            forShare.append("• Net Amount = ");
//                            forShare.append(textViewForResultNetAmount.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• IGST 18% = ");
//                            forShare.append(textViewIgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount = ");
//                            forShare.append(textViewTotalAmountResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount In Words = ");
//                            forShare.append(wordFormat);
//                            forShare.append('\n');
//                            forShare.append('\n');
//                            forShare.append("• Shared via Total Paisa App : Cash counter and  Gst Calculator");
//                            forShare.append('\n');
//                            forShare.append("• Download from Play Store: https://play.google.com/store/apps/details?id=com.vibhunorby.totalpaisa");
//
//                        } else if (chip28Percent.isChecked()) {
//                            initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                            netAmount = initialAmount;
//                            roundNetAmount = String.format("%.2f",netAmount);
//                            textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                            IgstAmount = (initialAmount / 100 * 28);
//                            roundIgstAmount = String.format("%.2f",IgstAmount);
//                            textViewIgst.setText("IGST Amount (28%)");
//                            textViewIgstResult.setText("₹ " + roundIgstAmount);
//                            totalAmount = initialAmount + IgstAmount;
//                            roundTotalAmount = String.format("%.2f",totalAmount);
//                            textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                            stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                            String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                            trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                            stringToLong = Long.parseLong(trimString);
//                            realIntegerPart = stringToLong/100;
//                            realDecimalPart = stringToLong%100;
//                            StringBuilder wordFormat = new StringBuilder();
//                            if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                wordFormat.append("Zero Rupees Only.");
//                            } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(DecimalToWord + " Paise Only.");
//                            } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                wordFormat.append(IntegerToWord + " Rupees Only.");
//                            } else {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                            }
//
////                            totalAmountWord.setText(wordFormat);
//                            forShare.append("• Net Amount = ");
//                            forShare.append(textViewForResultNetAmount.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• IGST 28% = ");
//                            forShare.append(textViewIgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount = ");
//                            forShare.append(textViewTotalAmountResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount In Words = ");
//                            forShare.append(wordFormat);
//                            forShare.append('\n');
//                            forShare.append('\n');
//                            forShare.append("• Shared via Total Paisa App : Cash counter and  Gst Calculator");
//                            forShare.append('\n');
//                            forShare.append("• Download from Play Store: https://play.google.com/store/apps/details?id=com.vibhunorby.totalpaisa");
//
//                        }
//
//
//                    } else if (radioCgstSgst.isChecked()) {
//                        if (chip3Percent.isChecked()) {
//
//                            initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                            netAmount = initialAmount;
//                            roundNetAmount = String.format("%.2f",netAmount);
//                            textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                            CgstAmount = ((initialAmount / 100 * 3)/2);
//                            roundCgstAmount = String.format("%.2f",CgstAmount);
//                            SgstAmount = ((initialAmount / 100 * 3)/2);
//                            roundSgstAmount = String.format("%.2f",SgstAmount);
//                            textViewCgst.setText("CGST Amount (1.5%)");
//                            textViewSgst.setText("SGST Amount (1.5%)");
//                            textViewCgstResult.setText("₹ " + roundCgstAmount);
//                            textViewSgstResult.setText("₹ " + roundSgstAmount);
//                            totalAmount = initialAmount + CgstAmount + SgstAmount;
//                            roundTotalAmount = String.format("%.2f",totalAmount);
//                            textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                            stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                            String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                            trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                            stringToLong = Long.parseLong(trimString);
//                            realIntegerPart = stringToLong/100;
//                            realDecimalPart = stringToLong%100;
//                            StringBuilder wordFormat = new StringBuilder();
//                            if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                wordFormat.append("Zero Rupees Only.");
//                            } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(DecimalToWord + " Paise Only.");
//                            } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                wordFormat.append(IntegerToWord + " Rupees Only.");
//                            } else {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                            }
//
////                            totalAmountWord.setText(wordFormat);
//
//
//                            forShare.append("• Net Amount = ");
//                            forShare.append(textViewForResultNetAmount.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• CGST 1.5% = ");
//                            forShare.append(textViewCgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• SGST 1.5% = ");
//                            forShare.append(textViewSgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount = ");
//                            forShare.append(textViewTotalAmountResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount In Words = ");
//                            forShare.append(wordFormat);
//                            forShare.append('\n');
//                            forShare.append('\n');
//                            forShare.append("• Shared via Total Paisa App : Cash counter and  Gst Calculator");
//                            forShare.append('\n');
//                            forShare.append("•  Download from Play Store: https://play.google.com/store/apps/details?id=com.vibhunorby.totalpaisa");
//
//
//                        } else if (chip5Percent.isChecked()) {
//
//                            initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                            netAmount = initialAmount;
//                            roundNetAmount = String.format("%.2f",netAmount);
//                            textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                            CgstAmount = ((initialAmount / 100 * 5)/2);
//                            roundCgstAmount = String.format("%.2f",CgstAmount);
//                            SgstAmount = ((initialAmount / 100 * 5)/2);
//                            roundSgstAmount = String.format("%.2f",SgstAmount);
//                            textViewCgst.setText("CGST Amount (2.5%)");
//                            textViewSgst.setText("SGST Amount (2.5%)");
//                            textViewCgstResult.setText("₹ " + roundCgstAmount);
//                            textViewSgstResult.setText("₹ " + roundSgstAmount);
//                            totalAmount = initialAmount + CgstAmount + SgstAmount;
//                            roundTotalAmount = String.format("%.2f",totalAmount);
//                            textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                            stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                            String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                            trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                            stringToLong = Long.parseLong(trimString);
//                            realIntegerPart = stringToLong/100;
//                            realDecimalPart = stringToLong%100;
//                            StringBuilder wordFormat = new StringBuilder();
//                            if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                wordFormat.append("Zero Rupees Only.");
//                            } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(DecimalToWord + " Paise Only.");
//                            } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                wordFormat.append(IntegerToWord + " Rupees Only.");
//                            } else {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                            }
//
////                            totalAmountWord.setText(wordFormat);
//                            forShare.append("• Net Amount = ");
//                            forShare.append(textViewForResultNetAmount.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• CGST 2.5% = ");
//                            forShare.append(textViewCgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• SGST 2.5% = ");
//                            forShare.append(textViewSgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount = ");
//                            forShare.append(textViewTotalAmountResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount In Words = ");
//                            forShare.append(wordFormat);
//                            forShare.append('\n');
//                            forShare.append('\n');
//                            forShare.append("• Shared via Total Paisa App : Cash counter and  Gst Calculator");
//                            forShare.append('\n');
//                            forShare.append("•  Download from Play Store: https://play.google.com/store/apps/details?id=com.vibhunorby.totalpaisa");
//
//
//
//                        } else if (chip12Percent.isChecked()) {
//
//                            initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                            netAmount = initialAmount;
//                            roundNetAmount = String.format("%.2f",netAmount);
//                            textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                            CgstAmount = ((initialAmount / 100 * 12)/2);
//                            roundCgstAmount = String.format("%.2f",CgstAmount);
//                            SgstAmount = ((initialAmount / 100 * 12)/2);
//                            roundSgstAmount = String.format("%.2f",SgstAmount);
//                            textViewCgst.setText("CGST Amount (6%)");
//                            textViewSgst.setText("SGST Amount (6%)");
//                            textViewCgstResult.setText("₹ " + roundCgstAmount);
//                            textViewSgstResult.setText("₹ " + roundSgstAmount);
//                            totalAmount = initialAmount + CgstAmount + SgstAmount;
//                            roundTotalAmount = String.format("%.2f",totalAmount);
//                            textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                            stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                            String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                            trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                            stringToLong = Long.parseLong(trimString);
//                            realIntegerPart = stringToLong/100;
//                            realDecimalPart = stringToLong%100;
//                            StringBuilder wordFormat = new StringBuilder();
//                            if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                wordFormat.append("Zero Rupees Only.");
//                            } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(DecimalToWord + " Paise Only.");
//                            } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                wordFormat.append(IntegerToWord + " Rupees Only.");
//                            } else {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                            }
//
////                            totalAmountWord.setText(wordFormat);
//
//                            forShare.append("• Net Amount = ");
//                            forShare.append(textViewForResultNetAmount.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• CGST 6% = ");
//                            forShare.append(textViewCgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• SGST 6% = ");
//                            forShare.append(textViewSgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount = ");
//                            forShare.append(textViewTotalAmountResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount In Words = ");
//                            forShare.append(wordFormat);
//                            forShare.append('\n');
//                            forShare.append('\n');
//                            forShare.append("• Shared via Total Paisa App : Cash counter and  Gst Calculator");
//                            forShare.append('\n');
//                            forShare.append("•  Download from Play Store: https://play.google.com/store/apps/details?id=com.vibhunorby.totalpaisa");
//
//
//                        } else if (chip18Percent.isChecked()) {
//
//                            initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                            netAmount = initialAmount;
//                            roundNetAmount = String.format("%.2f",netAmount);
//                            textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                            CgstAmount = ((initialAmount / 100 * 18)/2);
//                            roundCgstAmount = String.format("%.2f",CgstAmount);
//                            SgstAmount = ((initialAmount / 100 * 18)/2);
//                            roundSgstAmount = String.format("%.2f",SgstAmount);
//                            textViewCgst.setText("CGST Amount (9%)");
//                            textViewSgst.setText("SGST Amount (9%)");
//                            textViewCgstResult.setText("₹ " + roundCgstAmount);
//                            textViewSgstResult.setText("₹ " + roundSgstAmount);
//                            totalAmount = initialAmount + CgstAmount + SgstAmount;
//                            roundTotalAmount = String.format("%.2f",totalAmount);
//                            textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                            stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                            String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                            trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                            stringToLong = Long.parseLong(trimString);
//                            realIntegerPart = stringToLong/100;
//                            realDecimalPart = stringToLong%100;
//                            StringBuilder wordFormat = new StringBuilder();
//                            if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                wordFormat.append("Zero Rupees Only.");
//                            } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(DecimalToWord + " Paise Only.");
//                            } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                wordFormat.append(IntegerToWord + " Rupees Only.");
//                            } else {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                            }
//
////                            totalAmountWord.setText(wordFormat);
////                            totalAmountWord.setText(wordFormat);
//                            forShare.append("• Net Amount = ");
//                            forShare.append(textViewForResultNetAmount.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• CGST 9% = ");
//                            forShare.append(textViewCgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• SGST 9% = ");
//                            forShare.append(textViewSgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount = ");
//                            forShare.append(textViewTotalAmountResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount In Words = ");
//                            forShare.append(wordFormat);
//                            forShare.append('\n');
//                            forShare.append('\n');
//                            forShare.append("• Shared via Total Paisa App : Cash counter and  Gst Calculator");
//                            forShare.append('\n');
//                            forShare.append("•  Download from Play Store: https://play.google.com/store/apps/details?id=com.vibhunorby.totalpaisa");
//
//
//
//                        } else if (chip28Percent.isChecked()) {
//                            initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                            netAmount = initialAmount;
//                            roundNetAmount = String.format("%.2f",netAmount);
//                            textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                            CgstAmount = ((initialAmount / 100 * 28)/2);
//                            roundCgstAmount = String.format("%.2f",CgstAmount);
//                            SgstAmount = ((initialAmount / 100 * 28)/2);
//                            roundSgstAmount = String.format("%.2f",SgstAmount);
//                            textViewCgst.setText("CGST Amount (14%)");
//                            textViewSgst.setText("SGST Amount (14%)");
//                            textViewCgstResult.setText("₹ " + roundCgstAmount);
//                            textViewSgstResult.setText("₹ " + roundSgstAmount);
//                            totalAmount = initialAmount + CgstAmount + SgstAmount;
//                            roundTotalAmount = String.format("%.2f",totalAmount);
//                            textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                            stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                            String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                            trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                            stringToLong = Long.parseLong(trimString);
//                            realIntegerPart = stringToLong/100;
//                            realDecimalPart = stringToLong%100;
//                            StringBuilder wordFormat = new StringBuilder();
//                            if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                wordFormat.append("Zero Rupees Only.");
//                            } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(DecimalToWord + " Paise Only.");
//                            } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                wordFormat.append(IntegerToWord + " Rupees Only.");
//                            } else {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                            }
//
////                            totalAmountWord.setText(wordFormat);
//
//
////                            totalAmountWord.setText(wordFormat);
//                            forShare.append("• Net Amount = ");
//                            forShare.append(textViewForResultNetAmount.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• CGST 14% = ");
//                            forShare.append(textViewCgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• SGST 14% = ");
//                            forShare.append(textViewSgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount = ");
//                            forShare.append(textViewTotalAmountResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount In Words = ");
//                            forShare.append(wordFormat);
//                            forShare.append('\n');
//                            forShare.append('\n');
//                            forShare.append("•  Shared via Total Paisa App : Cash counter and  Gst Calculator");
//                            forShare.append('\n');
//                            forShare.append("• Download from Play Store: https://play.google.com/store/apps/details?id=com.vibhunorby.totalpaisa");
//
//
//                        }
//                    }
//                } else if (radioIncluding.isChecked()) {
//                    if (radioCgstSgst.isChecked()) {
//                        if (chip3Percent.isChecked()) {
//
//                            initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                            netAmount = (initialAmount/1.03);
//                            roundNetAmount = String.format("%.2f",netAmount);
//                            textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                            CgstAmount = ((netAmount / 100 * 3)/2);
//                            roundCgstAmount = String.format("%.2f",CgstAmount);
//                            SgstAmount = ((netAmount / 100 * 3)/2);
//                            roundSgstAmount = String.format("%.2f",SgstAmount);
//                            textViewCgst.setText("CGST Amount (1.5%)");
//                            textViewSgst.setText("SGST Amount (1.5%)");
//                            textViewCgstResult.setText("₹ " + roundCgstAmount);
//                            textViewSgstResult.setText("₹ " + roundSgstAmount);
//                            totalAmount = initialAmount;
//                            roundTotalAmount = String.format("%.2f",totalAmount);
//                            textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                            stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                            String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                            trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                            stringToLong = Long.parseLong(trimString);
//                            realIntegerPart = stringToLong/100;
//                            realDecimalPart = stringToLong%100;
//                            StringBuilder wordFormat = new StringBuilder();
//                            if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                wordFormat.append("Zero Rupees Only.");
//                            } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(DecimalToWord + " Paise Only.");
//                            } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                wordFormat.append(IntegerToWord + " Rupees Only.");
//                            } else {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                            }
//
////                            totalAmountWord.setText(wordFormat);
//                            forShare.append("• Net Amount = ");
//                            forShare.append(textViewForResultNetAmount.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• CGST 1.5% = ");
//                            forShare.append(textViewCgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• SGST 1.5% = ");
//                            forShare.append(textViewSgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount = ");
//                            forShare.append(textViewTotalAmountResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount In Words = ");
//                            forShare.append(wordFormat);
//                            forShare.append('\n');
//                            forShare.append('\n');
//                            forShare.append("• Shared via Total Paisa App : Cash counter and  Gst Calculator");
//                            forShare.append('\n');
//                            forShare.append("• Download from Play Store: https://play.google.com/store/apps/details?id=com.vibhunorby.totalpaisa");
//
//                        } else if (chip5Percent.isChecked()) {
//                            initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                            netAmount = (initialAmount/1.05);
//                            roundNetAmount = String.format("%.2f",netAmount);
//                            textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                            CgstAmount = ((netAmount / 100 * 5)/2);
//                            roundCgstAmount = String.format("%.2f",CgstAmount);
//                            SgstAmount = ((netAmount / 100 * 5)/2);
//                            roundSgstAmount = String.format("%.2f",SgstAmount);
//                            textViewCgst.setText("CGST Amount (2.5%)");
//                            textViewSgst.setText("SGST Amount (2.5%)");
//                            textViewCgstResult.setText("₹ " + roundCgstAmount);
//                            textViewSgstResult.setText("₹ " + roundSgstAmount);
//                            totalAmount = initialAmount;
//                            roundTotalAmount = String.format("%.2f",totalAmount);
//                            textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                            stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                            String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                            trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                            stringToLong = Long.parseLong(trimString);
//                            realIntegerPart = stringToLong/100;
//                            realDecimalPart = stringToLong%100;
//                            StringBuilder wordFormat = new StringBuilder();
//                            if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                wordFormat.append("Zero Rupees Only.");
//                            } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(DecimalToWord + " Paise Only.");
//                            } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                wordFormat.append(IntegerToWord + " Rupees Only.");
//                            } else {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                            }
//
////                            totalAmountWord.setText(wordFormat);
//                            forShare.append("• Net Amount = ");
//                            forShare.append(textViewForResultNetAmount.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• CGST 2.5% = ");
//                            forShare.append(textViewCgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• SGST 2.5% = ");
//                            forShare.append(textViewSgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount = ");
//                            forShare.append(textViewTotalAmountResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount In Words = ");
//                            forShare.append(wordFormat);
//                            forShare.append('\n');
//                            forShare.append('\n');
//                            forShare.append("• Shared via Total Paisa App : Cash counter and  Gst Calculator");
//                            forShare.append('\n');
//                            forShare.append("•  Download from Play Store: https://play.google.com/store/apps/details?id=com.vibhunorby.totalpaisa");
//
//                        } else if (chip12Percent.isChecked()) {
//                            initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                            netAmount = (initialAmount/1.12);
//                            roundNetAmount = String.format("%.2f",netAmount);
//                            textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                            CgstAmount = ((netAmount / 100 * 12)/2);
//                            roundCgstAmount = String.format("%.2f",CgstAmount);
//                            SgstAmount = ((netAmount / 100 * 12)/2);
//                            roundSgstAmount = String.format("%.2f",SgstAmount);
//                            textViewCgst.setText("CGST Amount (6%)");
//                            textViewSgst.setText("SGST Amount (6%)");
//                            textViewCgstResult.setText("₹ " + roundCgstAmount);
//                            textViewSgstResult.setText("₹ " + roundSgstAmount);
//                            totalAmount = initialAmount;
//                            roundTotalAmount = String.format("%.2f",totalAmount);
//                            textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                            stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                            String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                            trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                            stringToLong = Long.parseLong(trimString);
//                            realIntegerPart = stringToLong/100;
//                            realDecimalPart = stringToLong%100;
//                            StringBuilder wordFormat = new StringBuilder();
//                            if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                wordFormat.append("Zero Rupees Only.");
//                            } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(DecimalToWord + " Paise Only.");
//                            } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                wordFormat.append(IntegerToWord + " Rupees Only.");
//                            } else {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                            }
//
////                            totalAmountWord.setText(wordFormat);
//                            forShare.append("• Net Amount = ");
//                            forShare.append(textViewForResultNetAmount.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• CGST 6% = ");
//                            forShare.append(textViewCgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• SGST 6% = ");
//                            forShare.append(textViewSgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount = ");
//                            forShare.append(textViewTotalAmountResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount In Words = ");
//                            forShare.append(wordFormat);
//                            forShare.append('\n');
//                            forShare.append('\n');
//                            forShare.append("• Shared via Total Paisa App : Cash counter and  Gst Calculator");
//                            forShare.append('\n');
//                            forShare.append("• Download from Play Store: https://play.google.com/store/apps/details?id=com.vibhunorby.totalpaisa");
//
//
//                        } else if (chip18Percent.isChecked()) {
//
//                            initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                            netAmount = (initialAmount/1.18);
//                            roundNetAmount = String.format("%.2f",netAmount);
//                            textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                            CgstAmount = ((netAmount / 100 * 18)/2);
//                            roundCgstAmount = String.format("%.2f",CgstAmount);
//                            SgstAmount = ((netAmount / 100 * 18)/2);
//                            roundSgstAmount = String.format("%.2f",SgstAmount);
//                            textViewCgst.setText("CGST Amount (9%)");
//                            textViewSgst.setText("SGST Amount (9%)");
//                            textViewCgstResult.setText("₹ " + roundCgstAmount);
//                            textViewSgstResult.setText("₹ " + roundSgstAmount);
//                            totalAmount = initialAmount;
//                            roundTotalAmount = String.format("%.2f",totalAmount);
//                            textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                            stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                            String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                            trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                            stringToLong = Long.parseLong(trimString);
//                            realIntegerPart = stringToLong/100;
//                            realDecimalPart = stringToLong%100;
//                            StringBuilder wordFormat = new StringBuilder();
//                            if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                wordFormat.append("Zero Rupees Only.");
//                            } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(DecimalToWord + " Paise Only.");
//                            } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                wordFormat.append(IntegerToWord + " Rupees Only.");
//                            } else {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                            }
//
////                            totalAmountWord.setText(wordFormat);
//                            forShare.append("• Net Amount = ");
//                            forShare.append(textViewForResultNetAmount.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• CGST 9% = ");
//                            forShare.append(textViewCgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• SGST 9% = ");
//                            forShare.append(textViewSgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount = ");
//                            forShare.append(textViewTotalAmountResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount In Words = ");
//                            forShare.append(wordFormat);
//                            forShare.append('\n');
//                            forShare.append('\n');
//                            forShare.append("• Shared via Total Paisa App : Cash counter and  Gst Calculator");
//                            forShare.append('\n');
//                            forShare.append("• Download from Play Store: https://play.google.com/store/apps/details?id=com.vibhunorby.totalpaisa");
//
//
//                        } else if (chip28Percent.isChecked()) {
//
//                            initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                            netAmount = (initialAmount/1.28);
//                            roundNetAmount = String.format("%.2f",netAmount);
//                            textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                            CgstAmount = ((netAmount / 100 * 28)/2);
//                            roundCgstAmount = String.format("%.2f",CgstAmount);
//                            SgstAmount = ((netAmount / 100 * 28)/2);
//                            roundSgstAmount = String.format("%.2f",SgstAmount);
//                            textViewCgst.setText("CGST Amount (14%)");
//                            textViewSgst.setText("SGST Amount (14%)");
//                            textViewCgstResult.setText("₹ " + roundCgstAmount);
//                            textViewSgstResult.setText("₹ " + roundSgstAmount);
//                            totalAmount = initialAmount;
//                            roundTotalAmount = String.format("%.2f",totalAmount);
//                            textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                            stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                            String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                            trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                            stringToLong = Long.parseLong(trimString);
//                            realIntegerPart = stringToLong/100;
//                            realDecimalPart = stringToLong%100;
//                            StringBuilder wordFormat = new StringBuilder();
//                            if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                wordFormat.append("Zero Rupees Only.");
//                            } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(DecimalToWord + " Paise Only.");
//                            } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                wordFormat.append(IntegerToWord + " Rupees Only.");
//                            } else {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                            }
//
////                            totalAmountWord.setText(wordFormat);
//                            forShare.append("• Net Amount = ");
//                            forShare.append(textViewForResultNetAmount.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• CGST 14% = ");
//                            forShare.append(textViewCgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• SGST 14% = ");
//                            forShare.append(textViewSgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount = ");
//                            forShare.append(textViewTotalAmountResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount In Words = ");
//                            forShare.append(wordFormat);
//                            forShare.append('\n');
//                            forShare.append('\n');
//                            forShare.append("• Shared via Total Paisa App : Cash counter and  Gst Calculator");
//                            forShare.append('\n');
//                            forShare.append("•Download from Play Store: https://play.google.com/store/apps/details?id=com.vibhunorby.totalpaisa");
//
//
//                        }
//
//
//                    } else if (radioIgst.isChecked()) {
//                        if (chip3Percent.isChecked()) {
//
//
//                            initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                            netAmount = (initialAmount/1.03);
//                            roundNetAmount = String.format("%.2f",netAmount);
//                            textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                            IgstAmount = (netAmount / 100 * 3);
//                            roundIgstAmount = String.format("%.2f",IgstAmount);
//                            textViewIgst.setText("IGST Amount (3%)");
//                            textViewIgstResult.setText("₹ " + roundIgstAmount);
//                            totalAmount = initialAmount;
//                            roundTotalAmount = String.format("%.2f",totalAmount);
//                            textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                            stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                            String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                            trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                            stringToLong = Long.parseLong(trimString);
//                            realIntegerPart = stringToLong/100;
//                            realDecimalPart = stringToLong%100;
//                            StringBuilder wordFormat = new StringBuilder();
//                            if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                wordFormat.append("Zero Rupees Only.");
//                            } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(DecimalToWord + " Paise Only.");
//                            } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                wordFormat.append(IntegerToWord + " Rupees Only.");
//                            } else {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                            }
//
////                            totalAmountWord.setText(wordFormat);
//                            forShare.append("• Net Amount = ");
//                            forShare.append(textViewForResultNetAmount.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• IGST 3% = ");
//                            forShare.append(textViewIgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount = ");
//                            forShare.append(textViewTotalAmountResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount In Words = ");
//                            forShare.append(wordFormat);
//                            forShare.append('\n');
//                            forShare.append('\n');
//                            forShare.append("• Shared via Total Paisa App : Cash counter and  Gst Calculator");
//                            forShare.append('\n');
//                            forShare.append("• Download from Play Store: https://play.google.com/store/apps/details?id=com.vibhunorby.totalpaisa");
//
//
//                        } else if (chip5Percent.isChecked()) {
//
//
//                            initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                            netAmount = (initialAmount/1.05);
//                            roundNetAmount = String.format("%.2f",netAmount);
//                            textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                            IgstAmount = (netAmount / 100 * 5);
//                            roundIgstAmount = String.format("%.2f",IgstAmount);
//                            textViewIgst.setText("IGST Amount (5%)");
//                            textViewIgstResult.setText("₹ " + roundIgstAmount);
//                            totalAmount = initialAmount;
//                            roundTotalAmount = String.format("%.2f",totalAmount);
//                            textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                            stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                            String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                            trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                            stringToLong = Long.parseLong(trimString);
//                            realIntegerPart = stringToLong/100;
//                            realDecimalPart = stringToLong%100;
//                            StringBuilder wordFormat = new StringBuilder();
//                            if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                wordFormat.append("Zero Rupees Only.");
//                            } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(DecimalToWord + " Paise Only.");
//                            } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                wordFormat.append(IntegerToWord + " Rupees Only.");
//                            } else {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                            }
//
////                            totalAmountWord.setText(wordFormat);
//                            forShare.append("• Net Amount = ");
//                            forShare.append(textViewForResultNetAmount.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• IGST 5% = ");
//                            forShare.append(textViewIgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount = ");
//                            forShare.append(textViewTotalAmountResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount In Words = ");
//                            forShare.append(wordFormat);
//                            forShare.append('\n');
//                            forShare.append('\n');
//                            forShare.append("• Shared via Total Paisa App : Cash counter and  Gst Calculator");
//                            forShare.append('\n');
//                            forShare.append("•Download from Play Store: https://play.google.com/store/apps/details?id=com.vibhunorby.totalpaisa");
//
//
//                        } else if (chip12Percent.isChecked()) {
////
//
//                            initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                            netAmount = (initialAmount/1.12);
//                            roundNetAmount = String.format("%.2f",netAmount);
//                            textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                            IgstAmount = (netAmount / 100 * 12);
//                            roundIgstAmount = String.format("%.2f",IgstAmount);
//                            textViewIgst.setText("IGST Amount (12%)");
//                            textViewIgstResult.setText("₹ " + roundIgstAmount);
//                            totalAmount = initialAmount;
//                            roundTotalAmount = String.format("%.2f",totalAmount);
//                            textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                            stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                            String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                            trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                            stringToLong = Long.parseLong(trimString);
//                            realIntegerPart = stringToLong/100;
//                            realDecimalPart = stringToLong%100;
//                            StringBuilder wordFormat = new StringBuilder();
//                            if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                wordFormat.append("Zero Rupees Only.");
//                            } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(DecimalToWord + " Paise Only.");
//                            } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                wordFormat.append(IntegerToWord + " Rupees Only.");
//                            } else {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                            }
//
////                            totalAmountWord.setText(wordFormat);
//
//                            forShare.append("• Net Amount = ");
//                            forShare.append(textViewForResultNetAmount.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• IGST 12% = ");
//                            forShare.append(textViewIgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount = ");
//                            forShare.append(textViewTotalAmountResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount In Words = ");
//                            forShare.append(wordFormat);
//                            forShare.append('\n');
//                            forShare.append('\n');
//                            forShare.append("• Shared via Total Paisa App : Cash counter and  Gst Calculator");
//                            forShare.append('\n');
//                            forShare.append("• Download from Play Store: https://play.google.com/store/apps/details?id=com.vibhunorby.totalpaisa");
//
//                        } else if (chip18Percent.isChecked()) {
//
//
//                            initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                            netAmount = (initialAmount/1.18);
//                            roundNetAmount = String.format("%.2f",netAmount);
//                            textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                            IgstAmount = (netAmount / 100 * 18);
//                            roundIgstAmount = String.format("%.2f",IgstAmount);
//                            textViewIgst.setText("IGST Amount (18%)");
//                            textViewIgstResult.setText("₹ " + roundIgstAmount);
//                            totalAmount = initialAmount;
//                            roundTotalAmount = String.format("%.2f",totalAmount);
//                            textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                            stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                            String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                            trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                            stringToLong = Long.parseLong(trimString);
//                            realIntegerPart = stringToLong/100;
//                            realDecimalPart = stringToLong%100;
//                            StringBuilder wordFormat = new StringBuilder();
//                            if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                wordFormat.append("Zero Rupees Only.");
//                            } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(DecimalToWord + " Paise Only.");
//                            } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                wordFormat.append(IntegerToWord + " Rupees Only.");
//                            } else {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                            }
//
//                            forShare.append("• Net Amount = ");
//                            forShare.append(textViewForResultNetAmount.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• IGST 18% = ");
//                            forShare.append(textViewIgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount = ");
//                            forShare.append(textViewTotalAmountResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount In Words = ");
//                            forShare.append(wordFormat);
//                            forShare.append('\n');
//                            forShare.append('\n');
//                            forShare.append("•  Shared via Total Paisa App : Cash counter and  Gst Calculator");
//                            forShare.append('\n');
//                            forShare.append("• Download from Play Store: https://play.google.com/store/apps/details?id=com.vibhunorby.totalpaisa");
//
//
//                        } else if (chip28Percent.isChecked()) {
//
//                            initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
//                            netAmount = (initialAmount/1.28);
//                            roundNetAmount = String.format("%.2f",netAmount);
//                            textViewForResultNetAmount.setText("₹ " + roundNetAmount);
//                            IgstAmount = (netAmount / 100 * 28);
//                            roundIgstAmount = String.format("%.2f",IgstAmount);
//                            textViewIgst.setText("IGST Amount (28%)");
//                            textViewIgstResult.setText("₹ " + roundIgstAmount);
//                            totalAmount = initialAmount;
//                            roundTotalAmount = String.format("%.2f",totalAmount);
//                            textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
//                            stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
//                            String doubleToStringFormat = String.format("%.2f",stringToDouble);
//                            trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
//                            stringToLong = Long.parseLong(trimString);
//                            realIntegerPart = stringToLong/100;
//                            realDecimalPart = stringToLong%100;
//                            StringBuilder wordFormat = new StringBuilder();
//                            if (realIntegerPart == 0 && realDecimalPart == 0) {
//                                wordFormat.append("Zero Rupees Only.");
//                            } else if(realIntegerPart == 0 && realDecimalPart != 0) {
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(DecimalToWord + " Paise Only.");
//                            } else if(realIntegerPart != 0 && realDecimalPart == 0) {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                wordFormat.append(IntegerToWord + " Rupees Only.");
//                            } else {
//                                LongToStringIntPart = String.valueOf(realIntegerPart);
//                                LongToStringDecPart = String.valueOf(realDecimalPart);
//                                IntegerToWord = Words.convert(LongToStringIntPart);
//                                DecimalToWord = Words.convert(LongToStringDecPart);
//                                wordFormat.append(IntegerToWord + " Rupees And " + DecimalToWord +  " Paise Only. ");
//                            }
//
//                            forShare.append("• Net Amount = ");
//                            forShare.append(textViewForResultNetAmount.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• IGST 28% = ");
//                            forShare.append(textViewIgstResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount = ");
//                            forShare.append(textViewTotalAmountResult.getText().toString());
//                            forShare.append('\n');
//                            forShare.append("• Total Amount In Words = ");
//                            forShare.append(wordFormat);
//                            forShare.append('\n');
//                            forShare.append('\n');
//                            forShare.append("• Shared via Total Paisa App : Cash counter and  Gst Calculator");
//                            forShare.append('\n');
//                            forShare.append("• Download from Play Store: https://play.google.com/store/apps/details?id=com.vibhunorby.totalpaisa");
//
//                        }
//                    }
//                }
//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.putExtra(Intent.EXTRA_TEXT, (Serializable) forShare);
//                sendIntent.setType("text/plain");
//                startActivity(sendIntent);
//            } else {
//
//                Toast.makeText(getApplicationContext(), "Zero amount can't be shared.", Toast.LENGTH_SHORT).show();
//
//            }
//
//        } else if (id == R.id.ResetButtonGst){
//
//            textViewclear.performClick();
//            Toast.makeText(this,"Data has been Reset", Toast.LENGTH_SHORT).show();
//
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

}