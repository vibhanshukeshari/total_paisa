package com.vibhunorby.totalpaisa;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static com.vibhunorby.totalpaisa.MainActivity.toggleStatusIncrementDecrement;

public class CalculationFragment extends Fragment {

    boolean btnPlusMinusWasPressed;
    boolean lengthFive = true;

    boolean lengthSix = true;

    Prefs prefs;
    private SoundPool soundPool;
    private int soundTalliedCash;
    private boolean edit = true;
    private  int  cursorPosition;
    private TextView textViewTellerBalance;
    private EditText tallyAmount;
    private TextView textViewCalender;
    private TextView textViewDayOfTheWeek;
    private Button calender;
    EditText editTextPayeeName;
    private int badgeAddition;
    private int badge2000,badge500,badge200,badge100,badge50,badge20,badge10,badge5,badge20coin,badge10coin,badge5coin,badge2coin,badge1coin,badgeExtracoin;
    static TextView textViewRes2000, textViewRes500, textViewRes200, textViewRes100, textViewRes50, textViewRes20, textViewRes10, textViewRes5;
    static TextView textViewRes20Coin,textViewRes10Coin, textViewRes5Coin, textViewRes2Coin, textViewRes1Coin, textViewResCoinExtra;
    static EditText editTextNumber2000, editTextNumber500, editTextNumber200, editTextNumber100, editTextNumber50, editTextNumber20, editTextNumber10, editTextNumber5;
    static EditText editTextNumber20Coin, editTextNumber10Coin, editTextNumber5Coin, editTextNumber2Coin, editTextNumber1Coin, editTextNumberCoinExtra;

    static ImageButton minus500,plus500,minus200,plus200,minus100,plus100,minus50,plus50,minus20,plus20,minus10,plus10,minus5,plus5,
    minus20coin,plus20coin,minus10coin,plus10coin,minus5coin,plus5coin,minus2coin,plus2coin,minus1coin,plus1coin,minusExtraCoin,plusExtraCoin;
     static NestedScrollView nestedScrollView;


     static boolean isCalculationFragmentReady = false;

    public static CalculationFragment getInstance() {

        return new CalculationFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.row_calculation, container, false);
        setHasOptionsMenu(true);

        prefs = new Prefs(requireContext());

        textViewDayOfTheWeek = view.findViewById(R.id.textViewDayOfTheWeek);
        textViewTellerBalance = view.findViewById(R.id.textViewTellerBalance);
        tallyAmount = view.findViewById(R.id.tallyAmount);
        textViewCalender = view.findViewById(R.id.textViewCalender);
        calender = view.findViewById(R.id.calender);
        editTextPayeeName = view.findViewById(R.id.editTextPayeeName);
        nestedScrollView = view.findViewById(R.id.nested);
        RelativeLayout parentLayout = view.findViewById(R.id.parent);
        textViewRes2000 = view.findViewById(R.id.textViewRes2000);
        textViewRes500 = view.findViewById(R.id.textViewRes500);
        textViewRes200 = view.findViewById(R.id.textViewRes200);
        textViewRes100 = view.findViewById(R.id.textViewRes100);
        textViewRes50 = view.findViewById(R.id.textViewRes50);
        textViewRes20 = view.findViewById(R.id.textViewRes20);
        textViewRes10 = view.findViewById(R.id.textViewRes10);
        textViewRes5 = view.findViewById(R.id.textViewRes5);
        textViewRes20Coin = view.findViewById(R.id.textViewRes20Coin);
        textViewRes10Coin = view.findViewById(R.id.textViewRes10Coin);
        textViewRes5Coin = view.findViewById(R.id.textViewRes5Coin);
        textViewRes2Coin = view.findViewById(R.id.textViewRes2Coin);
        textViewRes1Coin = view.findViewById(R.id.textViewRes1Coin);
        textViewResCoinExtra = view.findViewById(R.id.textViewResCoinExtra);
        editTextNumber2000 = view.findViewById(R.id.editTextNumber2000);
        editTextNumber500 = view.findViewById(R.id.editTextNumber500);
        editTextNumber200 = view.findViewById(R.id.editTextNumber200);
        editTextNumber100 = view.findViewById(R.id.editTextNumber100);
        editTextNumber50 = view.findViewById(R.id.editTextNumber50);
        editTextNumber20 = view.findViewById(R.id.editTextNumber20);
        editTextNumber10 = view.findViewById(R.id.editTextNumber10);
        editTextNumber5 = view.findViewById(R.id.editTextNumber5);
        editTextNumber20Coin = view.findViewById(R.id.editTextNumber20Coin);
        editTextNumber10Coin = view.findViewById(R.id.editTextNumber10Coin);
        editTextNumber5Coin = view.findViewById(R.id.editTextNumber5Coin);
        editTextNumber2Coin = view.findViewById(R.id.editTextNumber2Coin);
        editTextNumber1Coin = view.findViewById(R.id.editTextNumber1Coin);
        editTextNumberCoinExtra = view.findViewById(R.id.editTextNumberCoinExtra);

        minus500 = view.findViewById(R.id.minus_500);
        minus200 = view.findViewById(R.id.minus_200);
        minus100 = view.findViewById(R.id.minus_100);
        minus50 = view.findViewById(R.id.minus_50);
        minus20 = view.findViewById(R.id.minus_20);
        minus10 = view.findViewById(R.id.minus_10);
        minus5 = view.findViewById(R.id.minus_5);
        minus20coin = view.findViewById(R.id.minus_20_coin);
        minus10coin = view.findViewById(R.id.minus_10_coin);
        minus5coin = view.findViewById(R.id.minus_5_coin);
        minus2coin = view.findViewById(R.id.minus_2_coin);
        minus1coin = view.findViewById(R.id.minus_1_coin);
        minusExtraCoin = view.findViewById(R.id.minus_extra_coin);

        plus500 = view.findViewById(R.id.plus_500);
        plus200 = view.findViewById(R.id.plus_200);
        plus100 = view.findViewById(R.id.plus_100);
        plus50 = view.findViewById(R.id.plus_50);
        plus20 = view.findViewById(R.id.plus_20);
        plus10 = view.findViewById(R.id.plus_10);
        plus5 = view.findViewById(R.id.plus_5);
        plus20coin = view.findViewById(R.id.plus_20_coin);
        plus10coin = view.findViewById(R.id.plus_10_coin);
        plus5coin = view.findViewById(R.id.plus_5_coin);
        plus2coin = view.findViewById(R.id.plus_2_coin);
        plus1coin = view.findViewById(R.id.plus_1_coin);
        plusExtraCoin = view.findViewById(R.id.plus_extra_coin);

        btnPlusMinusWasPressed = false;



//        This is used to close soft keyboard on outside click of the edit text
//        one method(fun) is used in the last of the programme and parent layout has mentioned clickable and focusable
//        To remove particular layout pass that layout in setupUI as below. By vibhu norby.
        setupUI(parentLayout);

        textViewRes2000.setText("0");
        textViewRes500.setText("0");
        textViewRes200.setText("0");
        textViewRes100.setText("0");
        textViewRes50.setText("0");
        textViewRes20.setText("0");
        textViewRes10.setText("0");
        textViewRes5.setText("0");
        textViewRes20Coin.setText("0");
        textViewRes10Coin.setText("0");
        textViewRes5Coin.setText("0");
        textViewRes2Coin.setText("0");
        textViewRes1Coin.setText("0");
        textViewResCoinExtra.setText("0");
        MainActivity.textViewResult.setText("0");

                //It is used for null pointer exception throw;
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_GAME)
                .build();
        soundPool = new SoundPool.Builder()
                .setMaxStreams(4)
                .setAudioAttributes(audioAttributes)
                .build();

        soundTalliedCash = soundPool.load(getActivity(), R.raw.cashsound, 1);


//  Took one day to solve it before was messing with  layout_behavior bottom sheet.

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {


                if((scrollY > oldScrollY)) {

                    try {

                        MainActivity.bottomNavigationView.setVisibility(View.GONE);

                    } catch (NullPointerException ignored) {

                    }

                }else {

                    try {

                        MainActivity.bottomNavigationView.setVisibility(View.VISIBLE);

                    } catch (NullPointerException ignored) {

                    }

                }
            }

        });


        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getFragmentManager(), "DatePicker");


            }
        });


//        setting current date for textViewCalender

        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        textViewCalender.setText(date);

//      setting current day of the week
        Calendar date1 = Calendar.getInstance();
        String  today = android.text.format.DateFormat.format("EEEE",date1).toString();
        textViewDayOfTheWeek.setText(today);


        ///////////plus minus button start/////07/10/23/Amrit hostel//////

        int colorLightWhite = ContextCompat.getColor(requireContext(), R.color.light_white);
        int colorPlusMinus = ContextCompat.getColor(requireContext(),R.color.plusMinus);






        minus500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editText500 = editTextNumber500.getText().toString() + "";

                btnPlusMinusWasPressed = true;

        if(!editText500.equals("")){
                    int fiveHundred = Integer.parseInt(editText500);

                    if(fiveHundred == 1 || fiveHundred == 0){
                        editTextNumber500.setText("");
//                        minus500.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                        return;
                    }
                        fiveHundred--;

                    editTextNumber500.setText(String.valueOf(fiveHundred));
                    editTextNumber500.setSelection(editTextNumber500.getText().length());
                    editTextNumber500.requestFocus();

                }

            }
        });

// this is used because compact switch doesn't work on off when app starts means don't invoke switchCompatIncrementDecrement.setOnCheckedChangeListener will not invoke when switch is off
        if(!toggleStatusIncrementDecrement){
            plus500.setVisibility(View.GONE);
            plus200.setVisibility(View.GONE);
            plus100.setVisibility(View.GONE);
            plus50.setVisibility(View.GONE);
            plus20.setVisibility(View.GONE);
            plus10.setVisibility(View.GONE);
            plus5.setVisibility(View.GONE);
            plus20coin.setVisibility(View.GONE);
            plus10coin.setVisibility(View.GONE);
            plus5coin.setVisibility(View.GONE);
            plus2coin.setVisibility(View.GONE);
            plus1coin.setVisibility(View.GONE);
            plusExtraCoin.setVisibility(View.GONE);

            minus500.setVisibility(View.GONE);
            minus200.setVisibility(View.GONE);
            minus100.setVisibility(View.GONE);
            minus50.setVisibility(View.GONE);
            minus20.setVisibility(View.GONE);
            minus10.setVisibility(View.GONE);
            minus5.setVisibility(View.GONE);
            minus20coin.setVisibility(View.GONE);
            minus10coin.setVisibility(View.GONE);
            minus5coin.setVisibility(View.GONE);
            minus2coin.setVisibility(View.GONE);
            minus1coin.setVisibility(View.GONE);
            minusExtraCoin.setVisibility(View.GONE);
        }


        plus500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editText500 = editTextNumber500.getText().toString() + "";

                btnPlusMinusWasPressed = true;

                if(!editText500.equals("")) {
                    int fiveHundred = Integer.parseInt(editText500);

                    if (fiveHundred == 99999) {
                        return;
                    }


                    fiveHundred++;

                    editTextNumber500.setText(String.valueOf(fiveHundred));
                    editTextNumber500.setSelection(editTextNumber500.getText().length());
                } else{

                    editTextNumber500.setText("1");
                    editTextNumber500.setSelection(editTextNumber500.getText().length());

                }


                editTextNumber500.requestFocus();


            }

        });


        minus200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editText200 = editTextNumber200.getText().toString() + "";

                btnPlusMinusWasPressed = true;

                if(!editText200.equals("")){
                    int twoHundred = Integer.parseInt(editText200);

                    if(twoHundred == 1 || twoHundred == 0){
                        editTextNumber200.setText("");
//                        minus500.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                        return;
                    }
                    twoHundred--;

                    editTextNumber200.setText(String.valueOf(twoHundred));
                    editTextNumber200.setSelection(editTextNumber200.getText().length());
                    editTextNumber200.requestFocus();

                }

            }
        });


        plus200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editText200 = editTextNumber200.getText().toString() + "";

                btnPlusMinusWasPressed = true;

                if(!editText200.equals("")) {
                    int twoHundred = Integer.parseInt(editText200);

                    if (twoHundred == 99999) {
                        return;
                    }
                    twoHundred++;

                    editTextNumber200.setText(String.valueOf(twoHundred));
                    editTextNumber200.setSelection(editTextNumber200.getText().length());
                } else{

                    editTextNumber200.setText("1");
                    editTextNumber200.setSelection(editTextNumber200.getText().length());

                }

                editTextNumber200.requestFocus();

            }
        });

        minus100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editText100 = editTextNumber100.getText().toString() + "";

                btnPlusMinusWasPressed = true;

                if(!editText100.equals("")){
                    int oneHundred = Integer.parseInt(editText100);

                    if(oneHundred == 1 || oneHundred == 0){
                        editTextNumber100.setText("");
//                        minus500.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                        return;
                    }
                    oneHundred--;

                    editTextNumber100.setText(String.valueOf(oneHundred));
                    editTextNumber100.setSelection(editTextNumber100.getText().length());
                    editTextNumber100.requestFocus();

                }

            }
        });


        plus100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editText100 = editTextNumber100.getText().toString() + "";

                 btnPlusMinusWasPressed = true;


                if(!editText100.equals("")) {
                    int oneHundred = Integer.parseInt(editText100);

                    if (oneHundred == 99999) {
                        return;
                    }
                    oneHundred++;

                    editTextNumber100.setText(String.valueOf(oneHundred));
                    editTextNumber100.setSelection(editTextNumber100.getText().length());
                } else{

                    editTextNumber100.setText("1");
                    editTextNumber100.setSelection(editTextNumber100.getText().length());

                }

                editTextNumber100.requestFocus();

            }
        });

        minus50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editText50 = editTextNumber50.getText().toString() + "";

                btnPlusMinusWasPressed = true;

                if(!editText50.equals("")){
                    int fifty = Integer.parseInt(editText50);

                    if(fifty == 1 || fifty == 0){
                        editTextNumber50.setText("");
//                        minus500.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                        return;
                    }
                    fifty--;

                    editTextNumber50.setText(String.valueOf(fifty));
                    editTextNumber50.setSelection(editTextNumber50.getText().length());
                    editTextNumber50.requestFocus();

                }

            }
        });


        plus50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editText50 = editTextNumber50.getText().toString() + "";


                btnPlusMinusWasPressed = true;

                if(!editText50.equals("")) {
                    int fifty = Integer.parseInt(editText50);

                    if (fifty == 99999) {
                        return;
                    }
                    fifty++;

                    editTextNumber50.setText(String.valueOf(fifty));
                    editTextNumber50.setSelection(editTextNumber50.getText().length());
                } else{

                    editTextNumber50.setText("1");
                    editTextNumber50.setSelection(editTextNumber50.getText().length());

                }

                editTextNumber50.requestFocus();

            }
        });

        minus20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editText20 = editTextNumber20.getText().toString() + "";

                btnPlusMinusWasPressed = true;

                if(!editText20.equals("")){
                    int twenty = Integer.parseInt(editText20);

                    if(twenty == 1 || twenty == 0){
                        editTextNumber20.setText("");
//                        minus500.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                        return;
                    }
                    twenty--;

                    editTextNumber20.setText(String.valueOf(twenty));
                    editTextNumber20.setSelection(editTextNumber20.getText().length());
                    editTextNumber20.requestFocus();

                }

            }
        });


        plus20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editText20 = editTextNumber20.getText().toString() + "";


                 btnPlusMinusWasPressed = true;

                if(!editText20.equals("")) {
                    int twenty = Integer.parseInt(editText20);

                    if (twenty == 99999) {
                        return;
                    }
                    twenty++;

                    editTextNumber20.setText(String.valueOf(twenty));
                    editTextNumber20.setSelection(editTextNumber20.getText().length());
                } else{

                    editTextNumber20.setText("1");
                    editTextNumber20.setSelection(editTextNumber20.getText().length());

                }

                editTextNumber20.requestFocus();

            }
        });

        minus10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editText10 = editTextNumber10.getText().toString() + "";

                btnPlusMinusWasPressed = true;

                if(!editText10.equals("")){
                    int ten = Integer.parseInt(editText10);

                    if(ten == 1 || ten == 0){
                        editTextNumber10.setText("");
//                        minus500.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                        return;
                    }
                    ten--;

                    editTextNumber10.setText(String.valueOf(ten));
                    editTextNumber10.setSelection(editTextNumber10.getText().length());
                    editTextNumber10.requestFocus();

                }

            }
        });


        plus10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editText10 = editTextNumber10.getText().toString() + "";

                 btnPlusMinusWasPressed = true;


                if(!editText10.equals("")) {
                    int ten = Integer.parseInt(editText10);

                    if (ten == 99999) {
                        return;
                    }
                    ten++;

                    editTextNumber10.setText(String.valueOf(ten));
                    editTextNumber10.setSelection(editTextNumber10.getText().length());
                } else{

                    editTextNumber10.setText("1");
                    editTextNumber10.setSelection(editTextNumber10.getText().length());

                }

                editTextNumber10.requestFocus();

            }
        });

        minus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editText5 = editTextNumber5.getText().toString() + "";

                btnPlusMinusWasPressed = true;

                if(!editText5.equals("")){
                    int five = Integer.parseInt(editText5);

                    if(five == 1 || five == 0){
                        editTextNumber5.setText("");
//                        minus500.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                        return;
                    }
                    five--;

                    editTextNumber5.setText(String.valueOf(five));
                    editTextNumber5.setSelection(editTextNumber5.getText().length());
                    editTextNumber5.requestFocus();

                }

            }
        });


        plus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editText5 = editTextNumber5.getText().toString() + "";

                btnPlusMinusWasPressed = true;


                if(!editText5.equals("")) {
                    int five = Integer.parseInt(editText5);

                    if (five == 99999) {
                        return;
                    }
                    five++;

                    editTextNumber5.setText(String.valueOf(five));
                    editTextNumber5.setSelection(editTextNumber5.getText().length());
                } else{

                    editTextNumber5.setText("1");
                    editTextNumber5.setSelection(editTextNumber5.getText().length());

                }

                editTextNumber5.requestFocus();

            }
        });

        minus20coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editText20Coin = editTextNumber20Coin.getText().toString() + "";

                btnPlusMinusWasPressed = true;

                if(!editText20Coin.equals("")){
                    int twentyCoin = Integer.parseInt(editText20Coin);

                    if(twentyCoin == 1 || twentyCoin == 0){
                        editTextNumber20Coin.setText("");
//                        minus500.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                        return;
                    }
                    twentyCoin--;

                    editTextNumber20Coin.setText(String.valueOf(twentyCoin));
                    editTextNumber20Coin.setSelection(editTextNumber20Coin.getText().length());
                    editTextNumber20Coin.requestFocus();

                }

            }
        });


        plus20coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editText20Coin = editTextNumber20Coin.getText().toString() + "";

                btnPlusMinusWasPressed = true;


                if(!editText20Coin.equals("")) {
                    int twentyCoin = Integer.parseInt(editText20Coin);

                    if (twentyCoin == 999999) {
                        return;
                    }
                    twentyCoin++;

                    editTextNumber20Coin.setText(String.valueOf(twentyCoin));
                    editTextNumber20Coin.setSelection(editTextNumber20Coin.getText().length());
                } else{

                    editTextNumber20Coin.setText("1");
                    editTextNumber20Coin.setSelection(editTextNumber20Coin.getText().length());

                }

                editTextNumber20Coin.requestFocus();

            }
        });

        minus10coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editText10Coin = editTextNumber10Coin.getText().toString() + "";

                btnPlusMinusWasPressed = true;

                if(!editText10Coin.equals("")){
                    int tenCoin = Integer.parseInt(editText10Coin);

                    if(tenCoin == 1 || tenCoin == 0){
                        editTextNumber10Coin.setText("");
//                        minus500.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                        return;
                    }
                    tenCoin--;

                    editTextNumber10Coin.setText(String.valueOf(tenCoin));
                    editTextNumber10Coin.setSelection(editTextNumber10Coin.getText().length());
                    editTextNumber10Coin.requestFocus();

                }

            }
        });


        plus10coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editText10Coin = editTextNumber10Coin.getText().toString() + "";

                 btnPlusMinusWasPressed = true;


                if(!editText10Coin.equals("")) {
                    int tenCoin = Integer.parseInt(editText10Coin);

                    if (tenCoin == 999999) {
                        return;
                    }
                    tenCoin++;

                    editTextNumber10Coin.setText(String.valueOf(tenCoin));
                    editTextNumber10Coin.setSelection(editTextNumber10Coin.getText().length());
                } else{

                    editTextNumber10Coin.setText("1");
                    editTextNumber10Coin.setSelection(editTextNumber10Coin.getText().length());

                }

                editTextNumber10Coin.requestFocus();

            }
        });

        minus5coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editText5Coin = editTextNumber5Coin.getText().toString() + "";

                btnPlusMinusWasPressed = true;

                if(!editText5Coin.equals("")){
                    int fiveCoin = Integer.parseInt(editText5Coin);

                    if(fiveCoin == 1 || fiveCoin == 0){
                        editTextNumber5Coin.setText("");
//                        minus500.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                        return;
                    }
                    fiveCoin--;

                    editTextNumber5Coin.setText(String.valueOf(fiveCoin));
                    editTextNumber5Coin.setSelection(editTextNumber5Coin.getText().length());
                    editTextNumber5Coin.requestFocus();

                }

            }
        });


        plus5coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editText5Coin = editTextNumber5Coin.getText().toString() + "";

                 btnPlusMinusWasPressed = true;


                if(!editText5Coin.equals("")) {
                    int fiveCoin = Integer.parseInt(editText5Coin);

                    if (fiveCoin == 999999) {
                        return;
                    }
                    fiveCoin++;

                    editTextNumber5Coin.setText(String.valueOf(fiveCoin));
                    editTextNumber5Coin.setSelection(editTextNumber5Coin.getText().length());
                } else{

                    editTextNumber5Coin.setText("1");
                    editTextNumber5Coin.setSelection(editTextNumber5Coin.getText().length());

                }

                editTextNumber5Coin.requestFocus();

            }
        });

        minus2coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editText2Coin = editTextNumber2Coin.getText().toString() + "";

                btnPlusMinusWasPressed = true;

                if(!editText2Coin.equals("")){
                    int twoCoin = Integer.parseInt(editText2Coin);

                    if(twoCoin == 1 || twoCoin == 0){
                        editTextNumber2Coin.setText("");
//                        minus500.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                        return;
                    }
                    twoCoin--;

                    editTextNumber2Coin.setText(String.valueOf(twoCoin));
                    editTextNumber2Coin.setSelection(editTextNumber2Coin.getText().length());
                    editTextNumber2Coin.requestFocus();

                }

            }
        });


        plus2coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editText2Coin = editTextNumber2Coin.getText().toString() + "";


                btnPlusMinusWasPressed = true;

                if(!editText2Coin.equals("")) {
                    int twoCoin = Integer.parseInt(editText2Coin);

                    if (twoCoin == 999999) {
                        return;
                    }
                    twoCoin++;

                    editTextNumber2Coin.setText(String.valueOf(twoCoin));
                    editTextNumber2Coin.setSelection(editTextNumber2Coin.getText().length());
                } else{

                    editTextNumber2Coin.setText("1");
                    editTextNumber2Coin.setSelection(editTextNumber2Coin.getText().length());

                }

                editTextNumber2Coin.requestFocus();

            }
        });

        minus1coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editText1Coin = editTextNumber1Coin.getText().toString() + "";

                btnPlusMinusWasPressed = true;

                if(!editText1Coin.equals("")){
                    int oneCoin = Integer.parseInt(editText1Coin);

                    if(oneCoin == 1 || oneCoin == 0){
                        editTextNumber1Coin.setText("");
//                        minus500.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                        return;
                    }
                    oneCoin--;

                    editTextNumber1Coin.setText(String.valueOf(oneCoin));
                    editTextNumber1Coin.setSelection(editTextNumber1Coin.getText().length());
                    editTextNumber1Coin.requestFocus();

                }

            }
        });


        plus1coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editText1Coin = editTextNumber1Coin.getText().toString() + "";


                  btnPlusMinusWasPressed = true;

                if(!editText1Coin.equals("")) {
                    int oneCoin = Integer.parseInt(editText1Coin);

                    if (oneCoin == 999999) {
                        return;
                    }
                    oneCoin++;

                    editTextNumber1Coin.setText(String.valueOf(oneCoin));
                    editTextNumber1Coin.setSelection(editTextNumber1Coin.getText().length());
                } else{

                    editTextNumber1Coin.setText("1");
                    editTextNumber1Coin.setSelection(editTextNumber1Coin.getText().length());

                }

                editTextNumber1Coin.requestFocus();

            }
        });

        minusExtraCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editTextExtraCoin = editTextNumberCoinExtra.getText().toString() + "";

                btnPlusMinusWasPressed = true;

                if(!editTextExtraCoin.equals("")){
                    int extraCoin = Integer.parseInt(editTextExtraCoin);

                    if(extraCoin == 1 || extraCoin == 0){
                        editTextNumberCoinExtra.setText("");
//                        minus500.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                        return;
                    }
                    extraCoin--;

                    editTextNumberCoinExtra.setText(String.valueOf(extraCoin));
                    editTextNumberCoinExtra.setSelection(editTextNumberCoinExtra.getText().length());
                    editTextNumberCoinExtra.requestFocus();

                }

            }
        });


        plusExtraCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editTextExtraCoin = editTextNumberCoinExtra.getText().toString() + "";

                 btnPlusMinusWasPressed = true;  


                if(!editTextExtraCoin.equals("")) {
                    int extraCoin = Integer.parseInt(editTextExtraCoin);

                    if (extraCoin == 999999) {
                        return;
                    }
                    extraCoin++;

                    editTextNumberCoinExtra.setText(String.valueOf(extraCoin));
                    editTextNumberCoinExtra.setSelection(editTextNumberCoinExtra.getText().length());
                } else{

                    editTextNumberCoinExtra.setText("1");
                    editTextNumberCoinExtra.setSelection(editTextNumberCoinExtra.getText().length());

                }

                editTextNumberCoinExtra.requestFocus();

            }
        });



        ///////////////////////plus minus end ////////////////////



















//        It took one week to get the idea about using set on click listener with focus listener before i was using only focus listener.

//        This is used to hide bottom navigation for the second click because setOnClickListener doesn't listen first.
//
//        editTextNumber2000.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if(!MainActivity.keyboard_up) {
//
//                    try {
//                        ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
//                        ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
//                        ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
//                    }catch (NullPointerException ignored){ }
//
//                }
//
//            }
//        });

//        This is used to hide bottom navigation for the first click because only focus listen first.
//                editTextNumber2000.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//
//            @Override
//            public void onFocusChange(View view, boolean focus) {
//
//                if(focus) {
//
//                    if(!MainActivity.keyboard_up) {
//
//                        try {
//                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
//                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
//                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
//                        }catch (NullPointerException ignored){ }
//
//                    }
//                }
//            }
//        });

        editTextNumber500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!MainActivity.keyboard_up) {


                    try {
                        ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                    }catch (NullPointerException ignored){ }

                }
            }
//
        });

//        This is used to hide bottom navigation for the first click because only focus listen first.
        editTextNumber500.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focus) {




                if(focus) {


                    String editText500 = editTextNumber500.getText().toString() + "";
                    if(!editText500.equals("")){
                        minus500.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);

                    }


                    plus500.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);


                    if(!MainActivity.keyboard_up && !btnPlusMinusWasPressed) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);

                        }catch (NullPointerException ignored){ }

                    }


                    btnPlusMinusWasPressed = false;

                }else {

                    minus500.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                    plus500.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);




                }


            }
        });

        editTextNumber200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!MainActivity.keyboard_up) {

                    try {
                        ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                    }catch (NullPointerException ignored){ }

                }
            }
        });
//
//        This is used to hide bottom navigation for the first click because only focus listen first.
        editTextNumber200.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focus) {

                if(focus) {


                    String editText200 = editTextNumber200.getText().toString() + "";
                    if(!editText200.equals("")){
                        minus200.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);

                    }


                    plus200.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);


                    if(!MainActivity.keyboard_up && !btnPlusMinusWasPressed) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                        }catch (NullPointerException ignored){ }

                    }
                    btnPlusMinusWasPressed = false;
                } else {

                    minus200.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                    plus200.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);

                }
            }
        });

        editTextNumber100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!MainActivity.keyboard_up) {

                    try {
                        ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                    }catch (NullPointerException ignored){ }

                }
            }
        });

//        This is used to hide bottom navigation for the first click because only focus listen first.
        editTextNumber100.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focus) {

                if(focus) {


                    String editText100 = editTextNumber100.getText().toString() + "";
                    if(!editText100.equals("")){
                        minus100.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);

                    }


                    plus100.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);


                    if(!MainActivity.keyboard_up && !btnPlusMinusWasPressed) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                        }catch (NullPointerException ignored){ }

                    }
                    btnPlusMinusWasPressed = false;

                } else {

                    minus100.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                    plus100.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);

                }
            }
        });

        editTextNumber50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!MainActivity.keyboard_up) {

                    try {
                        ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                    }catch (NullPointerException ignored){ }

                }

            }
        });

//        This is used to hide bottom navigation for the first click because only focus listen first.
        editTextNumber50.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focus) {

                if(focus) {


                    String editText50 = editTextNumber50.getText().toString() + "";
                    if(!editText50.equals("")){
                        minus50.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);

                    }


                    plus50.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);

                    if(!MainActivity.keyboard_up && !btnPlusMinusWasPressed) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                        }catch (NullPointerException ignored){ }

                    }

                    btnPlusMinusWasPressed = false;

                }else {

                    minus50.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                    plus50.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);

                }
            }
        });

        editTextNumber20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!MainActivity.keyboard_up) {

                    try {
                        ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                    }catch (NullPointerException ignored){ }

                }
            }
        });
//
//        This is used to hide bottom navigation for the first click because only focus listen first.
        editTextNumber20.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focus) {

                if(focus) {

                    String editText20 = editTextNumber20.getText().toString() + "";
                    if(!editText20.equals("")){
                        minus20.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);

                    }


                    plus20.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);

                    if(!MainActivity.keyboard_up && !btnPlusMinusWasPressed) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                        }catch (NullPointerException ignored){ }

                    }
                    btnPlusMinusWasPressed = false;
                }else {

                    minus20.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                    plus20.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);

                }
            }
        });

        editTextNumber10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!MainActivity.keyboard_up) {

                    try {
                        ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                    }catch (NullPointerException ignored){ }

                }
            }
        });

//        This is used to hide bottom navigation for the first click because only focus listen first.
        editTextNumber10.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focus) {

                if(focus) {

                    String editText10 = editTextNumber10.getText().toString() + "";
                    if(!editText10.equals("")){
                        minus10.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);

                    }

                    plus10.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);

                    if(!MainActivity.keyboard_up && !btnPlusMinusWasPressed) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                        }catch (NullPointerException ignored){ }

                    }
                    btnPlusMinusWasPressed = false;

                }else {

                    minus10.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                    plus10.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);

                }
            }
        });

        editTextNumber5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!MainActivity.keyboard_up) {

                    try {
                        ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                    }catch (NullPointerException ignored){ }

                }

            }
        });

//        This is used to hide bottom navigation for the first click because only focus listen first.
        editTextNumber5.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focus) {

                if(focus) {

                    String editText5 = editTextNumber5.getText().toString() + "";
                    if(!editText5.equals("")){
                        minus5.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);

                    }


                    plus5.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);

                    if(!MainActivity.keyboard_up  && !btnPlusMinusWasPressed) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                        }catch (NullPointerException ignored){ }

                    }

                    btnPlusMinusWasPressed =false;

                }else {

                    minus5.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                    plus5.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);

                }
            }
        });

        editTextNumber20Coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!MainActivity.keyboard_up) {

                    try {
                        ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                    }catch (NullPointerException ignored){ }

                }
            }
        });



//        This is used to hide bottom navigation for the first click because only focus listen first.
        editTextNumber20Coin.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focus) {

                if(focus) {

                    String editText20Coin = editTextNumber20Coin.getText().toString() + "";
                    if(!editText20Coin.equals("")){
                        minus20coin.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);

                    }


                    plus20coin.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);

                    if(!MainActivity.keyboard_up && !btnPlusMinusWasPressed) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                        }catch (NullPointerException ignored){ }

                    }

                    btnPlusMinusWasPressed = false;

                }else{
                    minus20coin.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                    plus20coin.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);

                }
            }
        });

        editTextNumber10Coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!MainActivity.keyboard_up) {

                    try {
                        ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                    }catch (NullPointerException ignored){ }

                }
            }
        });

        editTextNumber10Coin.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focus) {

                if(focus) {

                    String editText10Coin = editTextNumber10Coin.getText().toString() + "";
                    if(!editText10Coin.equals("")){
                        minus10coin.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);

                    }


                    plus10coin.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);

                    if(!MainActivity.keyboard_up && !btnPlusMinusWasPressed) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                        }catch (NullPointerException ignored){ }

                    }

                    btnPlusMinusWasPressed = false;

                }else{
                    minus10coin.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                    plus10coin.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);

                }
            }
        });


//        This is used to hide bottom navigation for the first click because only focus listen first.

        editTextNumber5Coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!MainActivity.keyboard_up) {

                    try {
                        ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                    }catch (NullPointerException ignored){ }

                }
            }
        });

//        This is used to hide bottom navigation for the first click because only focus listen first.
        editTextNumber5Coin.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focus) {

                if(focus) {

                    String editText5Coin = editTextNumber5Coin.getText().toString() + "";
                    if(!editText5Coin.equals("")){
                        minus5coin.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);

                    }


                    plus5coin.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);

                    if(!MainActivity.keyboard_up && !btnPlusMinusWasPressed) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                        }catch (NullPointerException ignored){ }

                    }

                    btnPlusMinusWasPressed = false;

                } else {

                    minus5coin.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                    plus5coin.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);

                }
            }
        });

        editTextNumber2Coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!MainActivity.keyboard_up) {

                    try {
                        ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                    }catch (NullPointerException ignored){ }

                }

            }
        });

//        This is used to hide bottom navigation for the first click because only focus listen first.
        editTextNumber2Coin.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focus) {

                if(focus) {

                    String editText2Coin = editTextNumber2Coin.getText().toString() + "";
                    if(!editText2Coin.equals("")){
                        minus2coin.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);

                    }


                    plus2coin.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);

                    if(!MainActivity.keyboard_up && !btnPlusMinusWasPressed) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                        }catch (NullPointerException ignored){ }

                    }

                    btnPlusMinusWasPressed = false;

                } else {
                    minus2coin.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                    plus2coin.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);

                }
            }
        });

        editTextNumber1Coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!MainActivity.keyboard_up) {

                    try {
                        ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                    }catch (NullPointerException ignored){ }

                }

            }
        });

//        This is used to hide bottom navigation for the first click because only focus listen first.
        editTextNumber1Coin.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focus) {

                if(focus) {

                    String editText1Coin = editTextNumber1Coin.getText().toString() + "";
                    if(!editText1Coin.equals("")){
                        minus1coin.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);

                    }


                    plus1coin.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);

                    if(!MainActivity.keyboard_up && !btnPlusMinusWasPressed) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                        }catch (NullPointerException ignored){ }

                    }

                    btnPlusMinusWasPressed = false;

                }else{
                    minus1coin.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                    plus1coin.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);

                }
            }
        });

        editTextNumberCoinExtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!MainActivity.keyboard_up) {

                    try {
                        ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                    }catch (NullPointerException ignored){ }

                }
            }
        });

//        This is used to hide bottom navigation for the first click because only focus listen first.
        editTextNumberCoinExtra.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focus) {

                if(focus) {

                    String editTextExtraCoin = editTextNumber1Coin.getText().toString() + "";
                    if(!editTextExtraCoin.equals("")){
                        minusExtraCoin.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);

                    }


                    plusExtraCoin.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);


                    if(!MainActivity.keyboard_up && !btnPlusMinusWasPressed) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                        }catch (NullPointerException ignored){ }

                    }

                    btnPlusMinusWasPressed = false;

                } else {

                    minusExtraCoin.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                    plusExtraCoin.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);

                }
            }
        });



        editTextPayeeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editTextPayeeName.setCursorVisible(true);
                if(!MainActivity.keyboard_up) {

                    MainActivity.bottomNavigationLayout.setVisibility(View.GONE);

                }

            }
        });

        editTextPayeeName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {

                if(focus){
                    if(!MainActivity.keyboard_up) {

                        try {

                            MainActivity.bottomNavigationLayout.setVisibility(View.GONE);

                        }catch (NullPointerException ignored){}
                    }

                }


            }
        });

        tallyAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!MainActivity.keyboard_up) {

                    try {

                        MainActivity.bottomNavigationLayout.setVisibility(View.GONE);

                    }catch (NullPointerException ignored){}
                }

            }
        });


        tallyAmount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {

                if(focus) {

                    if(!MainActivity.keyboard_up) {

                        try {

                            MainActivity.bottomNavigationLayout.setVisibility(View.GONE);

                        }catch (NullPointerException ignored){}
                    }

                }
            }
        });


//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++




        MainActivity.textViewResult.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String totalResult = MainActivity.textViewResult.getText().toString().replaceAll(",","");
                long myLongResultAmount = Long.parseLong(totalResult);
                Long myLongTallyAmount = Long.parseLong("0" + tallyAmount.getText().toString().replaceAll(",",""));


                if(myLongResultAmount == 0){
                    ClearBadge();
                    HideBadge();
                    MainActivity.textViewResult.setTextColor(Color.parseColor("#3bd16f"));
                    ((MainActivity) getActivity()).textViewRupeesSymbol.setTextColor(Color.parseColor("#3bd16f"));

                    try {
                        TextView textViewTotalNumberOfNotes = getActivity().findViewById(R.id.textViewTotalNumberOfNotes);
                        textViewTotalNumberOfNotes.setText("0" + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalNumberOfCoins = getActivity().findViewById(R.id.textViewTotalNumberOfCoins);
                        textViewTotalNumberOfCoins.setText("0" + " Pcs");
                    } catch (NullPointerException ignored){ }


                    try {
                        TextView textViewTotalValueOfNotes = getActivity().findViewById(R.id.textViewTotalValueOfNotes);
                        textViewTotalValueOfNotes.setText("₹ 0");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfCoins = getActivity().findViewById(R.id.textViewTotalValueOfCoins);
                        textViewTotalValueOfCoins.setText("₹ 0" );
                    } catch (NullPointerException Ignore){ }





                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable(){
                        @Override
                        public void run() {

                            try {

                                ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText("Zero Rupees Only.");
                                DetailsFragment.TotalAmountInWords.setText("Zero Rupees Only.");
                                ((MainActivity)getActivity()).textviewWordsCalculationFragment.setTextColor(Color.parseColor("#8899a6"));
                                DetailsFragment.TotalAmountInWords.setTextColor(Color.parseColor("#8899a6"));

                            }catch (NullPointerException ignored){}

                        }
                    },100);

                } else {
                    ShowBadge();

                    try {

                        ((MainActivity)getActivity()).textviewWordsCalculationFragment.setTextColor(Color.parseColor("#ccffffff"));
                        DetailsFragment.TotalAmountInWords.setTextColor(Color.parseColor("#ffffff"));

                    }catch (NullPointerException ignored){}
                }


                if(myLongResultAmount == 0 && myLongTallyAmount == 0) {

                    try {

                        textViewTellerBalance.setText("");
                        TextView textViewTellerDetailsFragment = getActivity().findViewById(R.id.textViewTellerDetailsFragment);
                        textViewTellerDetailsFragment.setText("");
                    } catch (NullPointerException ignored){}


                } else {
                    if(myLongTallyAmount != 0)  {

                        try {

                            if(myLongTallyAmount > myLongResultAmount) {

                                try {
                                    textViewTellerBalance.setText("Lesser by -> ₹ " + AddComma.getIndianCurrencyFormat(String.valueOf(myLongTallyAmount - myLongResultAmount)));
                                    textViewTellerBalance.setTextColor(Color.parseColor("#ff0000"));

                                    TextView textViewTellerDetailsFragment = getActivity().findViewById(R.id.textViewTellerDetailsFragment);
                                    textViewTellerDetailsFragment.setText("Cash is lesser by -> ₹ " + AddComma.getIndianCurrencyFormat(String.valueOf(myLongTallyAmount - myLongResultAmount)));
                                    textViewTellerDetailsFragment.setTextColor(Color.parseColor("#ff0000"));

                                    MainActivity.textViewResult.setTextColor(Color.parseColor("#ffffff"));
                                    TextView textViewTotalAmount = getActivity().findViewById(R.id.textViewTotalAmount);
                                    textViewTotalAmount.setTextColor(Color.parseColor("#ffffff"));
                                    ((MainActivity) getActivity()).textViewRupeesSymbol.setTextColor(Color.parseColor("#ff0000"));
                                    TextView textViewRupeesSymbolDetailsFragment = getActivity().findViewById(R.id.textViewRupeesSymbolDetailsFragment);
                                    textViewRupeesSymbolDetailsFragment.setTextColor(Color.parseColor("#ff0000"));
                                }catch (NullPointerException ignored) {

                                }

                            } else if(myLongTallyAmount < myLongResultAmount) {

                                try {

                                    textViewTellerBalance.setText("Greater by -> ₹ " + AddComma.getIndianCurrencyFormat(String.valueOf(myLongResultAmount - myLongTallyAmount)));
                                    textViewTellerBalance.setTextColor(Color.parseColor("#ffff00"));

                                    TextView textViewTellerDetailsFragment = getActivity().findViewById(R.id.textViewTellerDetailsFragment);
                                    textViewTellerDetailsFragment.setText("Cash is greater by -> ₹ " + AddComma.getIndianCurrencyFormat(String.valueOf(myLongResultAmount - myLongTallyAmount)));
                                    textViewTellerDetailsFragment.setTextColor(Color.parseColor("#ffff00"));


                                    MainActivity.textViewResult.setTextColor(Color.parseColor("#ffffff"));
                                    TextView textViewTotalAmount = getActivity().findViewById(R.id.textViewTotalAmount);
                                    textViewTotalAmount.setTextColor(Color.parseColor("#ffffff"));
                                    ((MainActivity) getActivity()).textViewRupeesSymbol.setTextColor(Color.parseColor("#ffff00"));

                                    TextView textViewRupeesSymbolDetailsFragment = getActivity().findViewById(R.id.textViewRupeesSymbolDetailsFragment);
                                    textViewRupeesSymbolDetailsFragment.setTextColor(Color.parseColor("#ffff00"));

                                }catch (NullPointerException ignored) { }
                            } else {

                                try{
                                    textViewTellerBalance.setText("Cash Tallied Successfully!");
                                    textViewTellerBalance.setTextColor(Color.parseColor("#3bd16f"));

                                    TextView textViewTellerDetailsFragment = getActivity().findViewById(R.id.textViewTellerDetailsFragment);
                                    textViewTellerDetailsFragment.setText("Cash Tallied Successfully!" );
                                    textViewTellerDetailsFragment.setTextColor(Color.parseColor("#3bd16f"));


                                    MainActivity.textViewResult.setTextColor(Color.parseColor("#3bd16f"));
                                    TextView textViewTotalAmount = getActivity().findViewById(R.id.textViewTotalAmount);
                                    textViewTotalAmount.setTextColor(Color.parseColor("#3bd16f"));
                                    ((MainActivity) getActivity()).textViewRupeesSymbol.setTextColor(Color.parseColor("#3bd16f"));
                                    TextView textViewRupeesSymbolDetailsFragment = getActivity().findViewById(R.id.textViewRupeesSymbolDetailsFragment);
                                    textViewRupeesSymbolDetailsFragment.setTextColor(Color.parseColor("#3bd16f"));

                                }catch (NullPointerException ignored) { }

                                try {

                                    AudioManager audio = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);

                                    if(audio.getRingerMode() == AudioManager.RINGER_MODE_NORMAL){

                                        if (MainActivity.toggleStatusTellerSound){
                                            soundPool.play(soundTalliedCash, 1, 1, 1, 0, 1);
                                        }

                                    }

                                }catch (NullPointerException ignored){ }

                                try {

                                    InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                                    inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(),0);
                                } catch (NullPointerException ignored) {

                                }


                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable(){
                                    @Override
                                    public void run() {

                                        NestedScrollView nestedScrollView = view.findViewById(R.id.nested);
                                        nestedScrollView.scrollTo(0,0);
                                        ((MainActivity) getActivity()).appBarLayout.setExpanded(true,true);


                                    }
                                },1000);

                            }

                        }catch (NumberFormatException e) {

                        }

                    }
                }

                if(MainActivity.save_button_pressed) {
                    String recordTotalResult = MainActivity.textViewResult.getText().toString().replaceAll(",","");
                    if(!recordTotalResult.equals("0")){

                        BlinkBadge();
                    } else {

                        MainActivity.save_button_pressed = false;

                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        tallyAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                cursorPosition = tallyAmount.getText().toString().length() - tallyAmount.getSelectionStart();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {



                try {

                if((tallyAmount.getText().toString().length() == 12)  && (Long.parseLong("0"+tallyAmount.getText().toString().replaceAll(",","")) <= 307497096 ) ) {

                    Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();


                }



                    if(Long.parseLong("0"+tallyAmount.getText().toString().replaceAll(",","")) >327497076 ) {
                         Toast.makeText(getContext(), "Limit exceeded can't be tallied.", Toast.LENGTH_SHORT).show();

                        tallyAmount.getText().clear();
                    }
                }catch (NumberFormatException e) {

                }

                try {

                    if(Long.parseLong("0"+tallyAmount.getText().toString().replaceAll(",","")) != 0){

                        Long myLongTallyAmount = Long.parseLong(tallyAmount.getText().toString().replaceAll(",",""));

                        Long myLongResultAmount = Long.parseLong(MainActivity.textViewResult.getText().toString().replaceAll(",",""));


                        try {

                            TextView textViewTellerNumberDetailsFragment = getActivity().findViewById(R.id.textViewTellerNumberDetailsFragment);
                            textViewTellerNumberDetailsFragment.setText(tallyAmount.getText().toString());

                        } catch (NullPointerException ignored){ }


                        if(myLongTallyAmount > myLongResultAmount) {


                            try {
                                textViewTellerBalance.setText("Lesser by -> ₹ " + AddComma.getIndianCurrencyFormat(String.valueOf(myLongTallyAmount - myLongResultAmount)));
                                textViewTellerBalance.setTextColor(Color.parseColor("#ff0000"));

                                TextView textViewTellerDetailsFragment = getActivity().findViewById(R.id.textViewTellerDetailsFragment);
                                textViewTellerDetailsFragment.setText("Cash is lesser by -> ₹ " + AddComma.getIndianCurrencyFormat(String.valueOf(myLongTallyAmount - myLongResultAmount)));
                                textViewTellerDetailsFragment.setTextColor(Color.parseColor("#ff0000"));

                                MainActivity.textViewResult.setTextColor(Color.parseColor("#ffffff"));
                                TextView textViewTotalAmount = getActivity().findViewById(R.id.textViewTotalAmount);
                                textViewTotalAmount.setTextColor(Color.parseColor("#ffffff"));
                                ((MainActivity) getActivity()).textViewRupeesSymbol.setTextColor(Color.parseColor("#ff0000"));
                                TextView textViewRupeesSymbolDetailsFragment = getActivity().findViewById(R.id.textViewRupeesSymbolDetailsFragment);
                                textViewRupeesSymbolDetailsFragment.setTextColor(Color.parseColor("#ff0000"));
                            }catch (NullPointerException ignored) {

                            }

                        } else if(myLongTallyAmount < myLongResultAmount) {

                            try {

                                textViewTellerBalance.setText("Greater by -> ₹ " + AddComma.getIndianCurrencyFormat(String.valueOf(myLongResultAmount - myLongTallyAmount)));
                                textViewTellerBalance.setTextColor(Color.parseColor("#ffff00"));

                                TextView textViewTellerDetailsFragment = getActivity().findViewById(R.id.textViewTellerDetailsFragment);
                                textViewTellerDetailsFragment.setText("Cash is greater by -> ₹ " + AddComma.getIndianCurrencyFormat(String.valueOf(myLongResultAmount - myLongTallyAmount)));
                                textViewTellerDetailsFragment.setTextColor(Color.parseColor("#ffff00"));


                                MainActivity.textViewResult.setTextColor(Color.parseColor("#ffffff"));
                                TextView textViewTotalAmount = getActivity().findViewById(R.id.textViewTotalAmount);
                                textViewTotalAmount.setTextColor(Color.parseColor("#ffffff"));
                                ((MainActivity) getActivity()).textViewRupeesSymbol.setTextColor(Color.parseColor("#ffff00"));

                                TextView textViewRupeesSymbolDetailsFragment = getActivity().findViewById(R.id.textViewRupeesSymbolDetailsFragment);
                                textViewRupeesSymbolDetailsFragment.setTextColor(Color.parseColor("#ffff00"));

                            }catch (NullPointerException ignored) { }
                        } else {

                            try{
                                textViewTellerBalance.setText("Cash Tallied Successfully!");
                                textViewTellerBalance.setTextColor(Color.parseColor("#3bd16f"));

                                TextView textViewTellerDetailsFragment = getActivity().findViewById(R.id.textViewTellerDetailsFragment);
                                textViewTellerDetailsFragment.setText("Cash Tallied Successfully!" );
                                textViewTellerDetailsFragment.setTextColor(Color.parseColor("#3bd16f"));


                                MainActivity.textViewResult.setTextColor(Color.parseColor("#3bd16f"));
                                TextView textViewTotalAmount = getActivity().findViewById(R.id.textViewTotalAmount);
                                textViewTotalAmount.setTextColor(Color.parseColor("#3bd16f"));
                                ((MainActivity) getActivity()).textViewRupeesSymbol.setTextColor(Color.parseColor("#3bd16f"));
                                TextView textViewRupeesSymbolDetailsFragment = getActivity().findViewById(R.id.textViewRupeesSymbolDetailsFragment);
                                textViewRupeesSymbolDetailsFragment.setTextColor(Color.parseColor("#3bd16f"));

                            }catch (NullPointerException ignored) { }

                            try {

                                AudioManager audio = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);

                                if(audio.getRingerMode() == AudioManager.RINGER_MODE_NORMAL){

                                    if(MainActivity.toggleStatusTellerSound) {
                                        soundPool.play(soundTalliedCash, 1, 1, 1, 0, 1);
                                    }
                                }

                            }catch (NullPointerException ignored){ }


                            try {

                                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                                inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(),0);
                            } catch (NullPointerException ignored) {}

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable(){
                                @Override
                                public void run() {

                                    NestedScrollView nestedScrollView = view.findViewById(R.id.nested);
                                    nestedScrollView.scrollTo(0,0);

                                    ((MainActivity) getActivity()).appBarLayout.setExpanded(true,true);


                                }
                            },1000);

                        }

                    } else {

                        try {


                            textViewTellerBalance.setText("");

                            TextView textViewTellerDetailsFragment = getActivity().findViewById(R.id.textViewTellerDetailsFragment);
                            textViewTellerDetailsFragment.setText("");


                            TextView textViewTellerNumberDetailsFragment = getActivity().findViewById(R.id.textViewTellerNumberDetailsFragment);
                            textViewTellerNumberDetailsFragment.setText("0");

                        } catch (NullPointerException ignored){ }



                        MainActivity.textViewResult.setTextColor(Color.parseColor("#3bd16f"));
                        ((MainActivity) getActivity()).textViewRupeesSymbol.setTextColor(Color.parseColor("#3bd16f"));
                        TextView textViewRupeesSymbolDetailsFragment = getActivity().findViewById(R.id.textViewRupeesSymbolDetailsFragment);

                        try {

                            textViewRupeesSymbolDetailsFragment.setTextColor(Color.parseColor("#3bd16f"));
                            TextView textViewTotalAmount = getActivity().findViewById(R.id.textViewTotalAmount);
                            textViewTotalAmount.setTextColor(Color.parseColor("#3bd16f"));

                        } catch (NullPointerException ignored) { }
                    }
                }catch (NumberFormatException ignored) { }

            }

            @Override
            public void afterTextChanged(Editable editable) {




                if(!tallyAmount.getText().toString().equals("")){

                    String myTellerWithoutComma;

                    if(edit) {
                        edit = false;
                        myTellerWithoutComma = tallyAmount.getText().toString().replaceAll(",","");
                        tallyAmount.setText(AddComma.getIndianCurrencyFormat(myTellerWithoutComma));
                        edit = true;

                    }

                    tallyAmount.setSelection(tallyAmount.getText().toString().length() - cursorPosition);


                }


            }
        });





//--------------------------------------------------------------------------------------------------



        editTextNumber2000.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                int TwoThousand;
                if(editTextNumber2000.getText().toString().length() == 5) {

                    Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();

                }
                if (!editTextNumber2000.getText().toString().equals("")) {
                    TwoThousand = Integer.parseInt(editTextNumber2000.getText().toString());

                    textViewRes2000.setText(AddComma.getIndianCurrencyFormat(String.valueOf(2000 * TwoThousand)));

                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin +  my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfNotes = ((my2000/2000) + (my500/500) + (my200/200) + (my100/100) + (my50/50) + (my20/20) + (my10/10) + (my5/5));
                    long myLongTotalValueOfNotes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5;
                    String myStringTotalValueOfNotes = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfNotes));

                    try {
                        TextView textViewTotalNumberOfNotes = getActivity().findViewById(R.id.textViewTotalNumberOfNotes);
                        textViewTotalNumberOfNotes.setText(totalNumberOfNotes + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfNotes = getActivity().findViewById(R.id.textViewTotalValueOfNotes);
                        textViewTotalValueOfNotes.setText("₹ " + myStringTotalValueOfNotes);
                    } catch (NullPointerException ignored){ }



                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));

                    try {

                        MainActivity.textViewResult.setText(myIntResNew);
                        DetailsFragment.TotalAmount.setText(myIntResNew);

                    }catch (NullPointerException ignored){}

                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);

                    String returnz = WordsTp.convert(numberz);

                    try {

                        DetailsFragment.TotalAmountInWords.setText(returnz);
                        ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                    }catch(NullPointerException ignored){}

                    badge2000 = 1;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                    badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);


                } else {


                    badge2000 = 0;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);


                    textViewRes2000.setText(String.valueOf(0));

                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin +  my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfNotes = ((my2000/2000) + (my500/500) + (my200/200) + (my100/100) + (my50/50) + (my20/20) + (my10/10) + (my5/5));
                    long myLongTotalValueOfNotes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5;
                    String myStringTotalValueOfNotes = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfNotes));

                    try {
                        TextView textViewTotalNumberOfNotes = getActivity().findViewById(R.id.textViewTotalNumberOfNotes);
                        textViewTotalNumberOfNotes.setText(totalNumberOfNotes + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfNotes = getActivity().findViewById(R.id.textViewTotalValueOfNotes);
                        textViewTotalValueOfNotes.setText("₹ " + myStringTotalValueOfNotes);
                    } catch (NullPointerException ignored){ }



                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));



                    try {
                        MainActivity.textViewResult.setText(myIntResNew);
                        DetailsFragment.TotalAmount.setText(myIntResNew);
                    } catch (NullPointerException e) {
//                        it saved from crash
                    }



                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);
                    String returnz = WordsTp.convertWithoutRupeesOnly(numberz);

                    if(!editTextNumber2000.getText().toString().equals("")
                    || !editTextNumber500.getText().toString().equals("")
                            || !editTextNumber200.getText().toString().equals("")
                            || !editTextNumber100.getText().toString().equals("")
                            || !editTextNumber50.getText().toString().equals("")
                            || !editTextNumber20.getText().toString().equals("")
                            || !editTextNumber10.getText().toString().equals("")
                            || !editTextNumber5.getText().toString().equals("")
                            || !editTextNumber20Coin.getText().toString().equals("")
                            || !editTextNumber10Coin.getText().toString().equals("")
                            || !editTextNumber5Coin.getText().toString().equals("")
                            || !editTextNumber2Coin.getText().toString().equals("")
                            || !editTextNumber1Coin.getText().toString().equals("")
                            || !editTextNumberCoinExtra.getText().toString().equals("")) {

                        try {

                            DetailsFragment.TotalAmountInWords.setText(returnz + " Rupees Only.");
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz + " Rupees Only.");

                        }catch(NullPointerException ignored){}

                    } else {

                        try {

                            DetailsFragment.TotalAmountInWords.setText(returnz);
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                        } catch (NullPointerException ignored) {}
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editTextNumber500.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
                String editText500 = editTextNumber500.getText().toString() + "";

                if(editText500.equals("")){
                    minus500.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
//                    plus500.setColorFilter(colorPlusMinus,PorterDuff.Mode.SRC_IN);



                }else{
                    minus500.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);
                }

                if(editText500.equals("99999")){
                    plus500.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                    Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();

                } else {
                    if(!editText500.equals("")){

                    plus500.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);
                    }
                }









                if(editTextNumber500.getText().toString().length() == 5) {


                    if(lengthFive){
                        Toast.makeText(getContext(), "Maximum length reached.", Toast.LENGTH_SHORT).show();
                        lengthFive = false;
                    }

                } else {
                    lengthFive = true;
                }

                if (!editTextNumber500.getText().toString().equals("")) {
                    int FiveHundred = Integer.parseInt(editTextNumber500.getText().toString());

                    textViewRes500.setText(AddComma.getIndianCurrencyFormat(String.valueOf(500 * FiveHundred)));
                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin +  my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfNotes = ((my2000/2000) + (my500/500) + (my200/200) + (my100/100) + (my50/50) + (my20/20) + (my10/10) + (my5/5));
                    long myLongTotalValueOfNotes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5;
                    String myStringTotalValueOfNotes = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfNotes));

                    try {
                        TextView textViewTotalNumberOfNotes = getActivity().findViewById(R.id.textViewTotalNumberOfNotes);
                        textViewTotalNumberOfNotes.setText(totalNumberOfNotes + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfNotes = getActivity().findViewById(R.id.textViewTotalValueOfNotes);
                        textViewTotalValueOfNotes.setText("₹ " + myStringTotalValueOfNotes);
                    } catch (NullPointerException ignored){ }



                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));

                    try {

                        MainActivity.textViewResult.setText(myIntResNew);
                        DetailsFragment.TotalAmount.setText(myIntResNew);

                    }catch(NullPointerException ignored){}

                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);
                    String returnz = WordsTp.convert(numberz);

                    try {

                        DetailsFragment.TotalAmountInWords.setText(returnz);
                        ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                    }catch (NullPointerException ignored){}

                    badge500 = 1;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);


                } else {


                    badge500 = 0;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);


                    textViewRes500.setText(String.valueOf(0));
                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfNotes = ((my2000/2000) + (my500/500) + (my200/200) + (my100/100) + (my50/50) + (my20/20) + (my10/10) + (my5/5));
                    long myLongTotalValueOfNotes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5;
                    String myStringTotalValueOfNotes = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfNotes));

                    try {
                        TextView textViewTotalNumberOfNotes = getActivity().findViewById(R.id.textViewTotalNumberOfNotes);
                        textViewTotalNumberOfNotes.setText(totalNumberOfNotes + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfNotes = getActivity().findViewById(R.id.textViewTotalValueOfNotes);
                        textViewTotalValueOfNotes.setText("₹ " + myStringTotalValueOfNotes);
                    } catch (NullPointerException ignored){ }


                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));

                    try {

                        MainActivity.textViewResult.setText(myIntResNew);
                        DetailsFragment.TotalAmount.setText(myIntResNew);

                    }catch (NullPointerException ignored) {}

                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);
                    String returnz = WordsTp.convertWithoutRupeesOnly(numberz);

                    if(!editTextNumber2000.getText().toString().equals("")
                            || !editTextNumber500.getText().toString().equals("")
                            || !editTextNumber200.getText().toString().equals("")
                            || !editTextNumber100.getText().toString().equals("")
                            || !editTextNumber50.getText().toString().equals("")
                            || !editTextNumber20.getText().toString().equals("")
                            || !editTextNumber10.getText().toString().equals("")
                            || !editTextNumber5.getText().toString().equals("")
                            || !editTextNumber20Coin.getText().toString().equals("")
                            || !editTextNumber10Coin.getText().toString().equals("")
                            || !editTextNumber5Coin.getText().toString().equals("")
                            || !editTextNumber2Coin.getText().toString().equals("")
                            || !editTextNumber1Coin.getText().toString().equals("")
                            || !editTextNumberCoinExtra.getText().toString().equals("")) {

                        try {

                            DetailsFragment.TotalAmountInWords.setText(returnz + " Rupees Only.");
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz + " Rupees Only.");

                        }catch (NullPointerException ignored){}
                    } else {

                        try {
                            DetailsFragment.TotalAmountInWords.setText(returnz);
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                        } catch (NullPointerException ignored) {}
                    }


                }


            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        editTextNumber200.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                String editText200 = editTextNumber200.getText().toString() + "";

                if(editText200.equals("")){
                    minus200.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
//                    plus500.setColorFilter(colorPlusMinus,PorterDuff.Mode.SRC_IN);



                }else{
                    minus200.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);
                }

                if(editText200.equals("99999")){
                    plus200.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                    Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();
                } else {
                    if(!editText200.equals("")){

                        plus200.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);
                    }
                }


                if(editTextNumber200.getText().toString().length() == 5) {
                    if(lengthFive){
                        Toast.makeText(getContext(), "Maximum length reached.", Toast.LENGTH_SHORT).show();
                        lengthFive = false;
                    }

                } else {
                    lengthFive = true;
                }

                if (!editTextNumber200.getText().toString().equals("")) {
                    int TwoHundred = Integer.parseInt(editTextNumber200.getText().toString());

                    textViewRes200.setText(AddComma.getIndianCurrencyFormat(String.valueOf(200 * TwoHundred)));
                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin +  my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfNotes = ((my2000/2000) + (my500/500) + (my200/200) + (my100/100) + (my50/50) + (my20/20) + (my10/10) + (my5/5));
                    long myLongTotalValueOfNotes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5;
                    String myStringTotalValueOfNotes = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfNotes));

                    try {
                        TextView textViewTotalNumberOfNotes = getActivity().findViewById(R.id.textViewTotalNumberOfNotes);
                        textViewTotalNumberOfNotes.setText(totalNumberOfNotes + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfNotes = getActivity().findViewById(R.id.textViewTotalValueOfNotes);
                        textViewTotalValueOfNotes.setText("₹ " + myStringTotalValueOfNotes);
                    } catch (NullPointerException ignored){ }

                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));

                    try {

                        MainActivity.textViewResult.setText(myIntResNew);
                        DetailsFragment.TotalAmount.setText(myIntResNew);

                    }catch (NullPointerException ignored){}

                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);
                    String returnz = WordsTp.convert(numberz);

                    try {

                        DetailsFragment.TotalAmountInWords.setText(returnz);
                        ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                    }catch (NullPointerException ignored){}

                    badge200 = 1;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);


                } else {

                    badge200 = 0;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);

                    textViewRes200.setText(String.valueOf(0));
                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin +  my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfNotes = ((my2000/2000) + (my500/500) + (my200/200) + (my100/100) + (my50/50) + (my20/20) + (my10/10) + (my5/5));
                    long myLongTotalValueOfNotes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5;
                    String myStringTotalValueOfNotes = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfNotes));

                    try {
                        TextView textViewTotalNumberOfNotes = getActivity().findViewById(R.id.textViewTotalNumberOfNotes);
                        textViewTotalNumberOfNotes.setText(totalNumberOfNotes + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfNotes = getActivity().findViewById(R.id.textViewTotalValueOfNotes);
                        textViewTotalValueOfNotes.setText("₹ " + myStringTotalValueOfNotes);
                    } catch (NullPointerException ignored){ }

                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));

                    try {
                        MainActivity.textViewResult.setText(myIntResNew);
                        DetailsFragment.TotalAmount.setText(myIntResNew);
                    }catch (NullPointerException ignored){}

                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);
                    String returnz = WordsTp.convertWithoutRupeesOnly(numberz);
                    if(!editTextNumber2000.getText().toString().equals("")
                            || !editTextNumber500.getText().toString().equals("")
                            || !editTextNumber200.getText().toString().equals("")
                            || !editTextNumber100.getText().toString().equals("")
                            || !editTextNumber50.getText().toString().equals("")
                            || !editTextNumber20.getText().toString().equals("")
                            || !editTextNumber10.getText().toString().equals("")
                            || !editTextNumber5.getText().toString().equals("")
                            || !editTextNumber20Coin.getText().toString().equals("")
                            || !editTextNumber10Coin.getText().toString().equals("")
                            || !editTextNumber5Coin.getText().toString().equals("")
                            || !editTextNumber2Coin.getText().toString().equals("")
                            || !editTextNumber1Coin.getText().toString().equals("")
                            || !editTextNumberCoinExtra.getText().toString().equals("")) {

                        try {

                            DetailsFragment.TotalAmountInWords.setText(returnz + " Rupees Only.");
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz + " Rupees Only.");

                        }catch (NullPointerException ignored){}

                    } else {

                        try {
                            DetailsFragment.TotalAmountInWords.setText(returnz);
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);
                        }catch(NullPointerException ignored){}
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        editTextNumber100.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                String editText100 = editTextNumber100.getText().toString() + "";

                if(editText100.equals("")){
                    minus100.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
//                    plus500.setColorFilter(colorPlusMinus,PorterDuff.Mode.SRC_IN);



                }else{
                    minus100.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);
                }

                if(editText100.equals("99999")){
                    plus100.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                    Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();
                } else {
                    if (!editText100.equals("")) {

                        plus100.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);
                    }
                }

                if(editTextNumber100.getText().toString().length() == 5) {
                    if(lengthFive){
                        Toast.makeText(getContext(), "Maximum length reached.", Toast.LENGTH_SHORT).show();
                        lengthFive = false;
                    }

                } else {
                    lengthFive = true;
                }

                if (!editTextNumber100.getText().toString().equals("")) {
                    int OneHundred = Integer.parseInt(editTextNumber100.getText().toString());

                    textViewRes100.setText(AddComma.getIndianCurrencyFormat(String.valueOf(100 * OneHundred)));
                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin +  my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfNotes = ((my2000/2000) + (my500/500) + (my200/200) + (my100/100) + (my50/50) + (my20/20) + (my10/10) + (my5/5));
                    long myLongTotalValueOfNotes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5;
                    String myStringTotalValueOfNotes = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfNotes));

                    try {
                        TextView textViewTotalNumberOfNotes = getActivity().findViewById(R.id.textViewTotalNumberOfNotes);
                        textViewTotalNumberOfNotes.setText(totalNumberOfNotes + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfNotes = getActivity().findViewById(R.id.textViewTotalValueOfNotes);
                        textViewTotalValueOfNotes.setText("₹ " + myStringTotalValueOfNotes);
                    } catch (NullPointerException ignored){ }

                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));

                    try {

                        MainActivity.textViewResult.setText(myIntResNew);
                        DetailsFragment.TotalAmount.setText(myIntResNew);

                    }catch(NullPointerException ignored){}


                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);
                    String returnz = WordsTp.convert(numberz);

                    try {

                        DetailsFragment.TotalAmountInWords.setText(returnz);
                        ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                    }catch (NullPointerException ignored){}

                    badge100 = 1;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);

                } else {

                    badge100 = 0;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);

                    textViewRes100.setText(String.valueOf(0));
                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfNotes = ((my2000/2000) + (my500/500) + (my200/200) + (my100/100) + (my50/50) + (my20/20) + (my10/10) + (my5/5));
                    long myLongTotalValueOfNotes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5;
                    String myStringTotalValueOfNotes = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfNotes));

                    try {
                        TextView textViewTotalNumberOfNotes = getActivity().findViewById(R.id.textViewTotalNumberOfNotes);
                        textViewTotalNumberOfNotes.setText(totalNumberOfNotes + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfNotes = getActivity().findViewById(R.id.textViewTotalValueOfNotes);
                        textViewTotalValueOfNotes.setText("₹ " + myStringTotalValueOfNotes);
                    } catch (NullPointerException ignored){ }

                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));

                    try {

                        MainActivity.textViewResult.setText(myIntResNew);
                        DetailsFragment.TotalAmount.setText(myIntResNew);

                    }catch (NullPointerException ignored){}

                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);
                    String returnz = WordsTp.convertWithoutRupeesOnly(numberz);
                    if(!editTextNumber2000.getText().toString().equals("")
                            || !editTextNumber500.getText().toString().equals("")
                            || !editTextNumber200.getText().toString().equals("")
                            || !editTextNumber100.getText().toString().equals("")
                            || !editTextNumber50.getText().toString().equals("")
                            || !editTextNumber20.getText().toString().equals("")
                            || !editTextNumber10.getText().toString().equals("")
                            || !editTextNumber5.getText().toString().equals("")
                            || !editTextNumber20Coin.getText().toString().equals("")
                            || !editTextNumber10Coin.getText().toString().equals("")
                            || !editTextNumber5Coin.getText().toString().equals("")
                            || !editTextNumber2Coin.getText().toString().equals("")
                            || !editTextNumber1Coin.getText().toString().equals("")
                            || !editTextNumberCoinExtra.getText().toString().equals("")) {

                        try {

                            DetailsFragment.TotalAmountInWords.setText(returnz + " Rupees Only.");
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz + " Rupees Only.");

                        }catch (NullPointerException ignored){}

                    } else {

                        try {

                            DetailsFragment.TotalAmountInWords.setText(returnz);
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                        }catch (NullPointerException ignored){}

                    }
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        editTextNumber50.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                String editText50 = editTextNumber50.getText().toString() + "";

                if(editText50.equals("")){
                    minus50.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
//                    plus500.setColorFilter(colorPlusMinus,PorterDuff.Mode.SRC_IN);



                }else{
                    minus50.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);
                }

                if(editText50.equals("99999")){
                    plus50.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                    Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();
                } else {
                    if (!editText50.equals("")) {

                        plus50.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);
                    }
                }

                if(editTextNumber50.getText().toString().length() == 5) {
                    if(lengthFive){
                        Toast.makeText(getContext(), "Maximum length reached.", Toast.LENGTH_SHORT).show();
                        lengthFive = false;
                    }

                } else {
                    lengthFive = true;
                }

                if (!editTextNumber50.getText().toString().equals("")) {
                    int Fifty = Integer.parseInt(editTextNumber50.getText().toString());

                    textViewRes50.setText(AddComma.getIndianCurrencyFormat(String.valueOf(50 * Fifty)));
                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfNotes = ((my2000/2000) + (my500/500) + (my200/200) + (my100/100) + (my50/50) + (my20/20) + (my10/10) + (my5/5));
                    long myLongTotalValueOfNotes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5;
                    String myStringTotalValueOfNotes = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfNotes));

                    try {
                        TextView textViewTotalNumberOfNotes = getActivity().findViewById(R.id.textViewTotalNumberOfNotes);
                        textViewTotalNumberOfNotes.setText(totalNumberOfNotes + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfNotes = getActivity().findViewById(R.id.textViewTotalValueOfNotes);
                        textViewTotalValueOfNotes.setText("₹ " + myStringTotalValueOfNotes);
                    } catch (NullPointerException ignored){ }


                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));

                    try {

                        MainActivity.textViewResult.setText(myIntResNew);
                        DetailsFragment.TotalAmount.setText(myIntResNew);

                    }catch (NullPointerException ignored){}

                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);
                    String returnz = WordsTp.convert(numberz);

                    try {

                        DetailsFragment.TotalAmountInWords.setText(returnz);
                        ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                    }catch (NullPointerException ignored){}

                    badge50 = 1;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);

                } else {

                    badge50 = 0;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);


                    textViewRes50.setText(String.valueOf(0));
                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfNotes = ((my2000/2000) + (my500/500) + (my200/200) + (my100/100) + (my50/50) + (my20/20) + (my10/10) + (my5/5));
                    long myLongTotalValueOfNotes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5;
                    String myStringTotalValueOfNotes = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfNotes));

                    try {
                        TextView textViewTotalNumberOfNotes = getActivity().findViewById(R.id.textViewTotalNumberOfNotes);
                        textViewTotalNumberOfNotes.setText(totalNumberOfNotes + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfNotes = getActivity().findViewById(R.id.textViewTotalValueOfNotes);
                        textViewTotalValueOfNotes.setText("₹ " + myStringTotalValueOfNotes);
                    } catch (NullPointerException ignored){ }


                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));

                    try {

                        MainActivity.textViewResult.setText(myIntResNew);
                        DetailsFragment.TotalAmount.setText(myIntResNew);

                    }catch (NullPointerException ignored){}

                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);
                    String returnz = WordsTp.convertWithoutRupeesOnly(numberz);
                    if(!editTextNumber2000.getText().toString().equals("")
                            || !editTextNumber500.getText().toString().equals("")
                            || !editTextNumber200.getText().toString().equals("")
                            || !editTextNumber100.getText().toString().equals("")
                            || !editTextNumber50.getText().toString().equals("")
                            || !editTextNumber20.getText().toString().equals("")
                            || !editTextNumber10.getText().toString().equals("")
                            || !editTextNumber5.getText().toString().equals("")
                            || !editTextNumber20Coin.getText().toString().equals("")
                            || !editTextNumber10Coin.getText().toString().equals("")
                            || !editTextNumber5Coin.getText().toString().equals("")
                            || !editTextNumber2Coin.getText().toString().equals("")
                            || !editTextNumber1Coin.getText().toString().equals("")
                            || !editTextNumberCoinExtra.getText().toString().equals("")) {

                        try {

                            DetailsFragment.TotalAmountInWords.setText(returnz + " Rupees Only.");
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz + " Rupees Only.");

                        }catch (NullPointerException ignroed){}


                    } else {

                        try {

                            DetailsFragment.TotalAmountInWords.setText(returnz);
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                        }catch (NullPointerException ignored){}
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        editTextNumber20.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String editText20 = editTextNumber20.getText().toString() + "";

                if(editText20.equals("")){
                    minus20.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
//                    plus500.setColorFilter(colorPlusMinus,PorterDuff.Mode.SRC_IN);



                }else{
                    minus20.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);
                }

                if(editText20.equals("99999")){
                    plus20.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                    Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();
                } else {
                    if (!editText20.equals("")) {

                        plus20.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);
                    }
                }

                if(editTextNumber20.getText().toString().length() == 5) {

                    if(lengthFive){
                        Toast.makeText(getContext(), "Maximum length reached.", Toast.LENGTH_SHORT).show();
                        lengthFive = false;
                    }

                } else {
                    lengthFive = true;
                }

                if (!editTextNumber20.getText().toString().equals("")) {
                    int Twenty = Integer.parseInt(editTextNumber20.getText().toString());
                    textViewRes20.setText(AddComma.getIndianCurrencyFormat(String.valueOf(20 * Twenty)));
                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfNotes = ((my2000/2000) + (my500/500) + (my200/200) + (my100/100) + (my50/50) + (my20/20) + (my10/10) + (my5/5));
                    long myLongTotalValueOfNotes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5;
                    String myStringTotalValueOfNotes = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfNotes));

                    try {
                        TextView textViewTotalNumberOfNotes = getActivity().findViewById(R.id.textViewTotalNumberOfNotes);
                        textViewTotalNumberOfNotes.setText(totalNumberOfNotes + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfNotes = getActivity().findViewById(R.id.textViewTotalValueOfNotes);
                        textViewTotalValueOfNotes.setText("₹ " + myStringTotalValueOfNotes);
                    } catch (NullPointerException ignored){ }


                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));

                    try {

                        MainActivity.textViewResult.setText(myIntResNew);
                        DetailsFragment.TotalAmount.setText(myIntResNew);

                    }catch (NullPointerException ignored){}

                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);
                    String returnz = WordsTp.convert(numberz);

                    try {

                        DetailsFragment.TotalAmountInWords.setText(returnz);
                        ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                    }catch (NullPointerException ignored){}

                    badge20 = 1;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);

                } else {

                    badge20 = 0;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);

                    textViewRes20.setText(String.valueOf(0));
                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfNotes = ((my2000/2000) + (my500/500) + (my200/200) + (my100/100) + (my50/50) + (my20/20) + (my10/10) + (my5/5));
                    long myLongTotalValueOfNotes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5;
                    String myStringTotalValueOfNotes = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfNotes));

                    try {
                        TextView textViewTotalNumberOfNotes = getActivity().findViewById(R.id.textViewTotalNumberOfNotes);
                        textViewTotalNumberOfNotes.setText(totalNumberOfNotes + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfNotes = getActivity().findViewById(R.id.textViewTotalValueOfNotes);
                        textViewTotalValueOfNotes.setText("₹ " + myStringTotalValueOfNotes);
                    } catch (NullPointerException ignored){ }


                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));

                    try {

                        MainActivity.textViewResult.setText(myIntResNew);
                        DetailsFragment.TotalAmount.setText(myIntResNew);

                    }catch (NullPointerException ignored){}

                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);
                    String returnz = WordsTp.convertWithoutRupeesOnly(numberz);
                    if(!editTextNumber2000.getText().toString().equals("")
                            || !editTextNumber500.getText().toString().equals("")
                            || !editTextNumber200.getText().toString().equals("")
                            || !editTextNumber100.getText().toString().equals("")
                            || !editTextNumber50.getText().toString().equals("")
                            || !editTextNumber20.getText().toString().equals("")
                            || !editTextNumber10.getText().toString().equals("")
                            || !editTextNumber5.getText().toString().equals("")
                            || !editTextNumber20Coin.getText().toString().equals("")
                            || !editTextNumber10Coin.getText().toString().equals("")
                            || !editTextNumber5Coin.getText().toString().equals("")
                            || !editTextNumber2Coin.getText().toString().equals("")
                            || !editTextNumber1Coin.getText().toString().equals("")
                            || !editTextNumberCoinExtra.getText().toString().equals("")) {

                        try {

                            DetailsFragment.TotalAmountInWords.setText(returnz + " Rupees Only.");
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz + " Rupees Only.");

                        }catch (NullPointerException ignored){}


                    } else {

                        try {

                            DetailsFragment.TotalAmountInWords.setText(returnz);
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                        }catch (NullPointerException ignored){}
                    }

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        editTextNumber10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                String editText10 = editTextNumber10.getText().toString() + "";

                if(editText10.equals("")){
                    minus10.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
//                    plus500.setColorFilter(colorPlusMinus,PorterDuff.Mode.SRC_IN);



                }else{
                    minus10.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);
                }

                if(editText10.equals("99999")){
                    plus10.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                    Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();
                } else {
                    if (!editText10.equals("")) {

                        plus10.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);
                    }
                }

                if(editTextNumber10.getText().toString().length() == 5) {

                    if(lengthFive){
                        Toast.makeText(getContext(), "Maximum length reached.", Toast.LENGTH_SHORT).show();
                        lengthFive = false;
                    }

                } else {
                    lengthFive = true;
                }

                if (!editTextNumber10.getText().toString().equals("")) {
                    int Ten = Integer.parseInt(editTextNumber10.getText().toString());

                    textViewRes10.setText(AddComma.getIndianCurrencyFormat(String.valueOf(10 * Ten)));
                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfNotes = ((my2000/2000) + (my500/500) + (my200/200) + (my100/100) + (my50/50) + (my20/20) + (my10/10) + (my5/5));
                    long myLongTotalValueOfNotes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5;
                    String myStringTotalValueOfNotes = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfNotes));

                    try {
                        TextView textViewTotalNumberOfNotes = getActivity().findViewById(R.id.textViewTotalNumberOfNotes);
                        textViewTotalNumberOfNotes.setText(totalNumberOfNotes + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfNotes = getActivity().findViewById(R.id.textViewTotalValueOfNotes);
                        textViewTotalValueOfNotes.setText("₹ " + myStringTotalValueOfNotes);
                    } catch (NullPointerException ignored){ }

                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));

                    try {

                        MainActivity.textViewResult.setText(myIntResNew);
                        DetailsFragment.TotalAmount.setText(myIntResNew);

                    }catch (NullPointerException ignored){}

                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);
                    String returnz = WordsTp.convert(numberz);

                    try {

                        DetailsFragment.TotalAmountInWords.setText(returnz);
                        ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                    }catch (NullPointerException ignored){}

                    badge10 = 1;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);


                } else {


                    badge10 = 0;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);

                    textViewRes10.setText(String.valueOf(0));
                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfNotes = ((my2000/2000) + (my500/500) + (my200/200) + (my100/100) + (my50/50) + (my20/20) + (my10/10) + (my5/5));
                    long myLongTotalValueOfNotes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5;
                    String myStringTotalValueOfNotes = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfNotes));

                    try {
                        TextView textViewTotalNumberOfNotes = getActivity().findViewById(R.id.textViewTotalNumberOfNotes);
                        textViewTotalNumberOfNotes.setText(totalNumberOfNotes + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfNotes = getActivity().findViewById(R.id.textViewTotalValueOfNotes);
                        textViewTotalValueOfNotes.setText("₹ " + myStringTotalValueOfNotes);
                    } catch (NullPointerException ignored){ }

                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));

                    try {

                        MainActivity.textViewResult.setText(myIntResNew);
                        DetailsFragment.TotalAmount.setText(myIntResNew);

                    }catch (NullPointerException ignored){}

                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);
                    String returnz = WordsTp.convertWithoutRupeesOnly(numberz);
                    if(!editTextNumber2000.getText().toString().equals("")
                            || !editTextNumber500.getText().toString().equals("")
                            || !editTextNumber200.getText().toString().equals("")
                            || !editTextNumber100.getText().toString().equals("")
                            || !editTextNumber50.getText().toString().equals("")
                            || !editTextNumber20.getText().toString().equals("")
                            || !editTextNumber10.getText().toString().equals("")
                            || !editTextNumber5.getText().toString().equals("")
                            || !editTextNumber20Coin.getText().toString().equals("")
                            || !editTextNumber10Coin.getText().toString().equals("")
                            || !editTextNumber5Coin.getText().toString().equals("")
                            || !editTextNumber2Coin.getText().toString().equals("")
                            || !editTextNumber1Coin.getText().toString().equals("")
                            || !editTextNumberCoinExtra.getText().toString().equals("")) {

                        try {

                            DetailsFragment.TotalAmountInWords.setText(returnz + " Rupees Only.");
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz + " Rupees Only.");

                        }catch (NullPointerException ignored){}


                    } else {

                        try {

                            DetailsFragment.TotalAmountInWords.setText(returnz);
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                        }catch (NullPointerException ignored){}
                    }

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        editTextNumber5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                String editText5 = editTextNumber5.getText().toString() + "";

                if(editText5.equals("")){
                    minus5.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
//                    plus500.setColorFilter(colorPlusMinus,PorterDuff.Mode.SRC_IN);



                }else{
                    minus5.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);
                }

                if(editText5.equals("99999")){
                    plus5.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                    Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();
                } else {
                    if (!editText5.equals("")) {

                        plus5.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);
                    }
                }

                if(editTextNumber5.getText().toString().length() == 5) {

                    if(lengthFive){
                        Toast.makeText(getContext(), "Maximum length reached.", Toast.LENGTH_SHORT).show();
                        lengthFive = false;
                    }

                } else {
                    lengthFive = true;
                }

                if (!editTextNumber5.getText().toString().equals("")) {
                    int Five = Integer.parseInt(editTextNumber5.getText().toString());

                    textViewRes5.setText(AddComma.getIndianCurrencyFormat(String.valueOf(5 * Five)));
                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfNotes = ((my2000/2000) + (my500/500) + (my200/200) + (my100/100) + (my50/50) + (my20/20) + (my10/10) + (my5/5));
                    long myLongTotalValueOfNotes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5;
                    String myStringTotalValueOfNotes = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfNotes));

                    try {
                        TextView textViewTotalNumberOfNotes = getActivity().findViewById(R.id.textViewTotalNumberOfNotes);
                        textViewTotalNumberOfNotes.setText(totalNumberOfNotes + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfNotes = getActivity().findViewById(R.id.textViewTotalValueOfNotes);
                        textViewTotalValueOfNotes.setText("₹ " + myStringTotalValueOfNotes);
                    } catch (NullPointerException ignored){ }

                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));

                    MainActivity.textViewResult.setText(myIntResNew);


                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);

                    String returnz = WordsTp.convert(numberz);

                    try {

                        DetailsFragment.TotalAmount.setText(myIntResNew);
                        DetailsFragment.TotalAmountInWords.setText(returnz);
                        ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                    }catch (NullPointerException ignored){}

                    badge5 = 1;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);


                } else {

                    badge5 = 0;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);

                    textViewRes5.setText(String.valueOf(0));
                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfNotes = ((my2000/2000) + (my500/500) + (my200/200) + (my100/100) + (my50/50) + (my20/20) + (my10/10) + (my5/5));
                    long myLongTotalValueOfNotes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5;
                    String myStringTotalValueOfNotes = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfNotes));

                    try {
                        TextView textViewTotalNumberOfNotes = getActivity().findViewById(R.id.textViewTotalNumberOfNotes);
                        textViewTotalNumberOfNotes.setText(totalNumberOfNotes + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfNotes = getActivity().findViewById(R.id.textViewTotalValueOfNotes);
                        textViewTotalValueOfNotes.setText("₹ " + myStringTotalValueOfNotes);
                    } catch (NullPointerException ignore){ }

                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));

                    try {

                        MainActivity.textViewResult.setText(myIntResNew);
                        DetailsFragment.TotalAmount.setText(myIntResNew);

                    }catch (NullPointerException ignored){}

                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);
                    String returnz = WordsTp.convertWithoutRupeesOnly(numberz);
                    if(!editTextNumber2000.getText().toString().equals("")
                            || !editTextNumber500.getText().toString().equals("")
                            || !editTextNumber200.getText().toString().equals("")
                            || !editTextNumber100.getText().toString().equals("")
                            || !editTextNumber50.getText().toString().equals("")
                            || !editTextNumber20.getText().toString().equals("")
                            || !editTextNumber10.getText().toString().equals("")
                            || !editTextNumber5.getText().toString().equals("")
                            || !editTextNumber20Coin.getText().toString().equals("")
                            || !editTextNumber10Coin.getText().toString().equals("")
                            || !editTextNumber5Coin.getText().toString().equals("")
                            || !editTextNumber2Coin.getText().toString().equals("")
                            || !editTextNumber1Coin.getText().toString().equals("")
                            || !editTextNumberCoinExtra.getText().toString().equals("")) {

                        try {

                            DetailsFragment.TotalAmountInWords.setText(returnz + " Rupees Only.");
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz + " Rupees Only.");

                        }catch (NullPointerException ignored){}


                    } else {

                        try {

                            DetailsFragment.TotalAmountInWords.setText(returnz);
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                        }catch (NullPointerException ignored){}

                    }

                }


            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });





        editTextNumber20Coin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String editText20coin = editTextNumber20Coin.getText().toString() + "";

                if(editText20coin.equals("")){
                    minus20coin.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
//                    plus500.setColorFilter(colorPlusMinus,PorterDuff.Mode.SRC_IN);



                }else{
                    minus20coin.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);
                }

                if(editText20coin.equals("999999")){
                    plus20coin.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                    Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();
                } else {
                    if (!editText20coin.equals("")) {

                        plus20coin.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);
                    }
                }
                if(editTextNumber20Coin.getText().toString().length() == 6) {

                    if(lengthSix){
                        Toast.makeText(getContext(), "Maximum length reached.", Toast.LENGTH_SHORT).show();
                        lengthSix = false;
                    }

                } else {
                    lengthSix = true;
                }

                if (!editTextNumber20Coin.getText().toString().equals("")) {
                    int TwentyCoin = Integer.parseInt(editTextNumber20Coin.getText().toString());

                    textViewRes20Coin.setText(AddComma.getIndianCurrencyFormat(String.valueOf(20 * TwentyCoin)));

                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfCoins = ((my20Coin/20) + (my10Coin/10) + (my5Coin/5) + (my2Coin/2) + (my1Coin));
                    long myLongTotalValueOfCoins =my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;
                    String myStringTotalValueOfCoins = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfCoins));

                    try {
                        TextView textViewTotalNumberOfCoins = getActivity().findViewById(R.id.textViewTotalNumberOfCoins);
                        textViewTotalNumberOfCoins.setText(totalNumberOfCoins + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfCoins = getActivity().findViewById(R.id.textViewTotalValueOfCoins);
                        textViewTotalValueOfCoins.setText("₹ " + myStringTotalValueOfCoins);
                    } catch (NullPointerException ignored){ }




                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));

                    MainActivity.textViewResult.setText(myIntResNew);

                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);
                    String returnz = WordsTp.convert(numberz);
                    try {

                        DetailsFragment.TotalAmount.setText(myIntResNew);
                        DetailsFragment.TotalAmountInWords.setText(returnz);
                        ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                    }catch (NullPointerException ignored){}

                    badge20coin = 1;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);



                } else {

                    badge20coin = 0;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);

                    textViewRes20Coin.setText(String.valueOf(0));
                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin +  my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfCoins = ((my20Coin/20) + (my10Coin/10) + (my5Coin/5) + (my2Coin/2) + (my1Coin));
                    long myLongTotalValueOfCoins = my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;
                    String myStringTotalValueOfCoins = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfCoins));

                    try {
                        TextView textViewTotalNumberOfCoins = getActivity().findViewById(R.id.textViewTotalNumberOfCoins);
                        textViewTotalNumberOfCoins.setText(totalNumberOfCoins + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfCoins = getActivity().findViewById(R.id.textViewTotalValueOfCoins);
                        textViewTotalValueOfCoins.setText("₹ " + myStringTotalValueOfCoins);
                    } catch (NullPointerException ignored){ }


                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));

                    try {

                        MainActivity.textViewResult.setText(myIntResNew);
                        DetailsFragment.TotalAmount.setText(myIntResNew);

                    }catch (NullPointerException ignored){}

                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);
                    String returnz = WordsTp.convertWithoutRupeesOnly(numberz);
                    if(!editTextNumber2000.getText().toString().equals("")
                            || !editTextNumber500.getText().toString().equals("")
                            || !editTextNumber200.getText().toString().equals("")
                            || !editTextNumber100.getText().toString().equals("")
                            || !editTextNumber50.getText().toString().equals("")
                            || !editTextNumber20.getText().toString().equals("")
                            || !editTextNumber10.getText().toString().equals("")
                            || !editTextNumber5.getText().toString().equals("")
                            || !editTextNumber20Coin.getText().toString().equals("")
                            || !editTextNumber10Coin.getText().toString().equals("")
                            || !editTextNumber5Coin.getText().toString().equals("")
                            || !editTextNumber2Coin.getText().toString().equals("")
                            || !editTextNumber1Coin.getText().toString().equals("")
                            || !editTextNumberCoinExtra.getText().toString().equals("")) {

                        try {

                            DetailsFragment.TotalAmountInWords.setText(returnz + " Rupees Only.");
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz + " Rupees Only.");

                        }catch (NullPointerException ignored){}


                    } else {

                        try {

                            DetailsFragment.TotalAmountInWords.setText(returnz);
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                        }catch (NullPointerException ignored){}
                    }


                }


            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        editTextNumber10Coin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                String editText10coin = editTextNumber10Coin.getText().toString() + "";

                if(editText10coin.equals("")){
                    minus10coin.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
//                    plus500.setColorFilter(colorPlusMinus,PorterDuff.Mode.SRC_IN);



                }else{
                    minus10coin.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);
                }

                if(editText10coin.equals("999999")){
                    plus10coin.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                    Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();
                } else {
                    if (!editText10coin.equals("")) {

                        plus10coin.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);
                    }
                }

                if(editTextNumber10Coin.getText().toString().length() == 6) {

                    if(lengthSix){
                        Toast.makeText(getContext(), "Maximum length reached.", Toast.LENGTH_SHORT).show();
                        lengthSix = false;
                    }

                } else {
                    lengthSix = true;
                }

                if (!editTextNumber10Coin.getText().toString().equals("")) {
                    int TenCoin = Integer.parseInt(editTextNumber10Coin.getText().toString());

                    textViewRes10Coin.setText(AddComma.getIndianCurrencyFormat(String.valueOf(10 * TenCoin)));

                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfCoins = ((my20Coin/20) + (my10Coin/10) + (my5Coin/5) + (my2Coin/2) + (my1Coin));
                    long myLongTotalValueOfCoins = my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;
                    String myStringTotalValueOfCoins = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfCoins));

                    try {
                        TextView textViewTotalNumberOfCoins = getActivity().findViewById(R.id.textViewTotalNumberOfCoins);
                        textViewTotalNumberOfCoins.setText(totalNumberOfCoins + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfCoins = getActivity().findViewById(R.id.textViewTotalValueOfCoins);
                        textViewTotalValueOfCoins.setText("₹ " + myStringTotalValueOfCoins);
                    } catch (NullPointerException ignored){ }




                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));

                    MainActivity.textViewResult.setText(myIntResNew);

                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);
                    String returnz = WordsTp.convert(numberz);
                    try {

                        DetailsFragment.TotalAmount.setText(myIntResNew);
                        DetailsFragment.TotalAmountInWords.setText(returnz);
                        ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                    }catch (NullPointerException ignored){}

                    badge10coin = 1;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);



                } else {

                    badge10coin = 0;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);

                    textViewRes10Coin.setText(String.valueOf(0));
                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin +  my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfCoins = ((my20Coin/20) + (my10Coin/10) + (my5Coin/5) + (my2Coin/2) + (my1Coin));
                    long myLongTotalValueOfCoins = my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;
                    String myStringTotalValueOfCoins = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfCoins));

                    try {
                        TextView textViewTotalNumberOfCoins = getActivity().findViewById(R.id.textViewTotalNumberOfCoins);
                        textViewTotalNumberOfCoins.setText(totalNumberOfCoins + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfCoins = getActivity().findViewById(R.id.textViewTotalValueOfCoins);
                        textViewTotalValueOfCoins.setText("₹ " + myStringTotalValueOfCoins);
                    } catch (NullPointerException ignored){ }


                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));

                    try {

                        MainActivity.textViewResult.setText(myIntResNew);
                        DetailsFragment.TotalAmount.setText(myIntResNew);

                    }catch (NullPointerException ignored){}

                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);
                    String returnz = WordsTp.convertWithoutRupeesOnly(numberz);
                    if(!editTextNumber2000.getText().toString().equals("")
                            || !editTextNumber500.getText().toString().equals("")
                            || !editTextNumber200.getText().toString().equals("")
                            || !editTextNumber100.getText().toString().equals("")
                            || !editTextNumber50.getText().toString().equals("")
                            || !editTextNumber20.getText().toString().equals("")
                            || !editTextNumber10.getText().toString().equals("")
                            || !editTextNumber5.getText().toString().equals("")
                            || !editTextNumber20Coin.getText().toString().equals("")
                            || !editTextNumber10Coin.getText().toString().equals("")
                            || !editTextNumber5Coin.getText().toString().equals("")
                            || !editTextNumber2Coin.getText().toString().equals("")
                            || !editTextNumber1Coin.getText().toString().equals("")
                            || !editTextNumberCoinExtra.getText().toString().equals("")) {

                        try {

                            DetailsFragment.TotalAmountInWords.setText(returnz + " Rupees Only.");
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz + " Rupees Only.");

                        }catch (NullPointerException ignored){}


                    } else {

                        try {

                            DetailsFragment.TotalAmountInWords.setText(returnz);
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                        }catch (NullPointerException ignored){}
                    }


                }


            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        editTextNumber5Coin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String editText5coin = editTextNumber5Coin.getText().toString() + "";

                if(editText5coin.equals("")){
                    minus5coin.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
//                    plus500.setColorFilter(colorPlusMinus,PorterDuff.Mode.SRC_IN);



                }else{
                    minus5coin.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);
                }

                if(editText5coin.equals("999999")){
                    plus5coin.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                    Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();
                } else {
                    if (!editText5coin.equals("")) {

                        plus5coin.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);
                    }
                }

                if(editTextNumber5Coin.getText().toString().length() == 6) {

                    if(lengthSix){
                        Toast.makeText(getContext(), "Maximum length reached.", Toast.LENGTH_SHORT).show();
                        lengthSix = false;
                    }

                } else {
                    lengthSix = true;
                }

                if (!editTextNumber5Coin.getText().toString().equals("")) {
                    int FiveCoin = Integer.parseInt(editTextNumber5Coin.getText().toString());
                    textViewRes5Coin.setText(AddComma.getIndianCurrencyFormat(String.valueOf(5 * FiveCoin)));
                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfCoins = ((my20Coin/20) + (my10Coin/10) + (my5Coin/5) + (my2Coin/2) + (my1Coin));
                    long myLongTotalValueOfCoins = my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;
                    String myStringTotalValueOfCoins = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfCoins));

                    try {
                        TextView textViewTotalNumberOfCoins = getActivity().findViewById(R.id.textViewTotalNumberOfCoins);
                        textViewTotalNumberOfCoins.setText(totalNumberOfCoins + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfCoins = getActivity().findViewById(R.id.textViewTotalValueOfCoins);
                        textViewTotalValueOfCoins.setText("₹ " + myStringTotalValueOfCoins);
                    } catch (NullPointerException ignored){ }

                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));
                    MainActivity.textViewResult.setText(myIntResNew);

                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);
                    String returnz = WordsTp.convert(numberz);

                    try {

                        DetailsFragment.TotalAmount.setText(myIntResNew);
                        DetailsFragment.TotalAmountInWords.setText(returnz);
                        ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                    }catch (NullPointerException ignored){}

                    badge5coin = 1;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);

                } else {

                    badge5coin = 0;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);

                    textViewRes5Coin.setText(String.valueOf(0));
                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfCoins = ((my20Coin/20) + (my10Coin/10) + (my5Coin/5) + (my2Coin/2) + (my1Coin));
                    long myLongTotalValueOfCoins =my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;
                    String myStringTotalValueOfCoins = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfCoins));

                    try {
                        TextView textViewTotalNumberOfCoins = getActivity().findViewById(R.id.textViewTotalNumberOfCoins);
                        textViewTotalNumberOfCoins.setText(totalNumberOfCoins + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfCoins = getActivity().findViewById(R.id.textViewTotalValueOfCoins);
                        textViewTotalValueOfCoins.setText("₹ " + myStringTotalValueOfCoins);
                    } catch (NullPointerException ignored){ }

                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));

                    try {

                        MainActivity.textViewResult.setText(myIntResNew);
                        DetailsFragment.TotalAmount.setText(myIntResNew);

                    }catch (NullPointerException ignored){}

                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);
                    String returnz = WordsTp.convertWithoutRupeesOnly(numberz);
                    if(!editTextNumber2000.getText().toString().equals("")
                            || !editTextNumber500.getText().toString().equals("")
                            || !editTextNumber200.getText().toString().equals("")
                            || !editTextNumber100.getText().toString().equals("")
                            || !editTextNumber50.getText().toString().equals("")
                            || !editTextNumber20.getText().toString().equals("")
                            || !editTextNumber10.getText().toString().equals("")
                            || !editTextNumber5.getText().toString().equals("")
                            || !editTextNumber20Coin.getText().toString().equals("")
                            || !editTextNumber10Coin.getText().toString().equals("")
                            || !editTextNumber5Coin.getText().toString().equals("")
                            || !editTextNumber2Coin.getText().toString().equals("")
                            || !editTextNumber1Coin.getText().toString().equals("")
                            || !editTextNumberCoinExtra.getText().toString().equals("")) {

                        try {

                            DetailsFragment.TotalAmountInWords.setText(returnz + " Rupees Only.");
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz + " Rupees Only.");

                        }catch (NullPointerException ignored){}

                    } else {

                        try {

                            DetailsFragment.TotalAmountInWords.setText(returnz);
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                        }catch (NullPointerException ignored){}
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        editTextNumber2Coin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                String editText2coin = editTextNumber2Coin.getText().toString() + "";

                if(editText2coin.equals("")){
                    minus2coin.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
//                    plus500.setColorFilter(colorPlusMinus,PorterDuff.Mode.SRC_IN);



                }else{
                    minus2coin.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);
                }

                if(editText2coin.equals("999999")){
                    plus2coin.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                    Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();
                } else {
                    if (!editText2coin.equals("")) {

                        plus2coin.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);
                    }
                }

                if(editTextNumber2Coin.getText().toString().length() == 6) {

                    if(lengthSix){
                        Toast.makeText(getContext(), "Maximum length reached.", Toast.LENGTH_SHORT).show();
                        lengthSix = false;
                    }

                } else {
                    lengthSix = true;
                }

                if (!editTextNumber2Coin.getText().toString().equals("")) {
                    int TwoCoin = Integer.parseInt(editTextNumber2Coin.getText().toString());
                    textViewRes2Coin.setText(AddComma.getIndianCurrencyFormat(String.valueOf(2 * TwoCoin)));
                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfCoins = ((my20Coin/20) + (my10Coin/10) + (my5Coin/5) + (my2Coin/2) + (my1Coin));
                    long myLongTotalValueOfCoins = my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;
                    String myStringTotalValueOfCoins = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfCoins));

                    try {
                        TextView textViewTotalNumberOfCoins = getActivity().findViewById(R.id.textViewTotalNumberOfCoins);
                        textViewTotalNumberOfCoins.setText(totalNumberOfCoins + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfCoins = getActivity().findViewById(R.id.textViewTotalValueOfCoins);
                        textViewTotalValueOfCoins.setText("₹ " + myStringTotalValueOfCoins);
                    } catch (NullPointerException ignored){ }

                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));
                    MainActivity.textViewResult.setText(myIntResNew);

                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);

                    String returnz = WordsTp.convert(numberz);

                    try {

                        DetailsFragment.TotalAmount.setText(myIntResNew);
                        DetailsFragment.TotalAmountInWords.setText(returnz);
                        ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                    }catch (NullPointerException ignored){}

                    badge2coin = 1;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);


                } else {

                    badge2coin = 0;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);

                    textViewRes2Coin.setText(String.valueOf(0));
                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin +  my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfCoins = ((my20Coin/20) + (my10Coin/10) + (my5Coin/5) + (my2Coin/2) + (my1Coin));
                    long myLongTotalValueOfCoins = my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;
                    String myStringTotalValueOfCoins = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfCoins));

                    try {
                        TextView textViewTotalNumberOfCoins = getActivity().findViewById(R.id.textViewTotalNumberOfCoins);
                        textViewTotalNumberOfCoins.setText(totalNumberOfCoins + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfCoins = getActivity().findViewById(R.id.textViewTotalValueOfCoins);
                        textViewTotalValueOfCoins.setText("₹ " + myStringTotalValueOfCoins);
                    } catch (NullPointerException ignored){ }

                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));

                    try {

                        MainActivity.textViewResult.setText(myIntResNew);
                        DetailsFragment.TotalAmount.setText(myIntResNew);

                    }catch (NullPointerException ignored){}

                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);
                    String returnz = WordsTp.convertWithoutRupeesOnly(numberz);
                    if(!editTextNumber2000.getText().toString().equals("")
                            || !editTextNumber500.getText().toString().equals("")
                            || !editTextNumber200.getText().toString().equals("")
                            || !editTextNumber100.getText().toString().equals("")
                            || !editTextNumber50.getText().toString().equals("")
                            || !editTextNumber20.getText().toString().equals("")
                            || !editTextNumber10.getText().toString().equals("")
                            || !editTextNumber5.getText().toString().equals("")
                            || !editTextNumber20Coin.getText().toString().equals("")
                            || !editTextNumber10Coin.getText().toString().equals("")
                            || !editTextNumber5Coin.getText().toString().equals("")
                            || !editTextNumber2Coin.getText().toString().equals("")
                            || !editTextNumber1Coin.getText().toString().equals("")
                            || !editTextNumberCoinExtra.getText().toString().equals("")) {

                        try {

                            DetailsFragment.TotalAmountInWords.setText(returnz + " Rupees Only.");
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz + " Rupees Only.");

                        }catch (NullPointerException ignored){}


                    } else {

                        try {

                            DetailsFragment.TotalAmountInWords.setText(returnz);
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                        }catch (NullPointerException ignored){}
                    }

                }


            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        editTextNumber1Coin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                String editText1coin = editTextNumber1Coin.getText().toString() + "";

                if(editText1coin.equals("")){
                    minus1coin.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
//                    plus500.setColorFilter(colorPlusMinus,PorterDuff.Mode.SRC_IN);



                }else{
                    minus1coin.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);
                }

                if(editText1coin.equals("999999")){
                    plus1coin.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                    Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();
                } else {
                    if (!editText1coin.equals("")) {

                        plus1coin.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);
                    }
                }

                if(editTextNumber1Coin.getText().toString().length() == 6) {

                    if(lengthSix){
                        Toast.makeText(getContext(), "Maximum length reached.", Toast.LENGTH_SHORT).show();
                        lengthSix = false;
                    }

                } else {
                    lengthSix = true;
                }
                if (!editTextNumber1Coin.getText().toString().equals("")) {
                    int OneCoin = Integer.parseInt(editTextNumber1Coin.getText().toString());
                    textViewRes1Coin.setText(AddComma.getIndianCurrencyFormat(String.valueOf(1 * OneCoin)));
                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin +  my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfCoins = ((my20Coin/20) + (my10Coin/10) + (my5Coin/5) + (my2Coin/2) + (my1Coin));
                    long myLongTotalValueOfCoins = my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;
                    String myStringTotalValueOfCoins = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfCoins));

                    try {
                        TextView textViewTotalNumberOfCoins = getActivity().findViewById(R.id.textViewTotalNumberOfCoins);
                        textViewTotalNumberOfCoins.setText(totalNumberOfCoins + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfCoins = getActivity().findViewById(R.id.textViewTotalValueOfCoins);
                        textViewTotalValueOfCoins.setText("₹ " + myStringTotalValueOfCoins);
                    } catch (NullPointerException ignored){ }

                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));
                    MainActivity.textViewResult.setText(myIntResNew);

                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);
                    String returnz = WordsTp.convert(numberz);

                    try {

                        DetailsFragment.TotalAmount.setText(myIntResNew);
                        DetailsFragment.TotalAmountInWords.setText(returnz);
                        ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                    }catch (NullPointerException ignored){}

                    badge1coin = 1;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);


                } else {

                    badge1coin = 0;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);

                    textViewRes1Coin.setText(String.valueOf(0));
                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfCoins = ((my20Coin/20) + (my10Coin/10) + (my5Coin/5) + (my2Coin/2) + (my1Coin));
                    long myLongTotalValueOfCoins = my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;
                    String myStringTotalValueOfCoins = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfCoins));

                    try {
                        TextView textViewTotalNumberOfCoins = getActivity().findViewById(R.id.textViewTotalNumberOfCoins);
                        textViewTotalNumberOfCoins.setText(totalNumberOfCoins + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfCoins = getActivity().findViewById(R.id.textViewTotalValueOfCoins);
                        textViewTotalValueOfCoins.setText("₹ " + myStringTotalValueOfCoins);
                    } catch (NullPointerException ignored){ }

                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));

                    try {

                        MainActivity.textViewResult.setText(myIntResNew);
                        DetailsFragment.TotalAmount.setText(myIntResNew);

                    }catch (NullPointerException ignored){}

                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);
                    String returnz = WordsTp.convertWithoutRupeesOnly(numberz);
                    if(!editTextNumber2000.getText().toString().equals("")
                            || !editTextNumber500.getText().toString().equals("")
                            || !editTextNumber200.getText().toString().equals("")
                            || !editTextNumber100.getText().toString().equals("")
                            || !editTextNumber50.getText().toString().equals("")
                            || !editTextNumber20.getText().toString().equals("")
                            || !editTextNumber10.getText().toString().equals("")
                            || !editTextNumber5.getText().toString().equals("")
                            || !editTextNumber20Coin.getText().toString().equals("")
                            || !editTextNumber10Coin.getText().toString().equals("")
                            || !editTextNumber5Coin.getText().toString().equals("")
                            || !editTextNumber2Coin.getText().toString().equals("")
                            || !editTextNumber1Coin.getText().toString().equals("")
                            || !editTextNumberCoinExtra.getText().toString().equals("")) {

                        try {

                            DetailsFragment.TotalAmountInWords.setText(returnz + " Rupees Only.");
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz + " Rupees Only.");

                        }catch (NullPointerException ignored){}


                    } else {

                        try {

                            DetailsFragment.TotalAmountInWords.setText(returnz);
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                        }catch (NullPointerException ignored){}
                    }

                }


            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        editTextNumberCoinExtra.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                String editTextExtraCoin = editTextNumberCoinExtra.getText().toString() + "";

                if(editTextExtraCoin.equals("")){
                    minusExtraCoin.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
//                    plus500.setColorFilter(colorPlusMinus,PorterDuff.Mode.SRC_IN);



                }else{
                    minusExtraCoin.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);
                }

                if(editTextExtraCoin.equals("999999")){
                    plusExtraCoin.setColorFilter(colorPlusMinus, PorterDuff.Mode.SRC_IN);
                    Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();
                } else {
                    if (!editTextExtraCoin.equals("")) {

                        plusExtraCoin.setColorFilter(colorLightWhite, PorterDuff.Mode.SRC_IN);
                    }
                }

                if(editTextNumberCoinExtra.getText().toString().length() == 6) {

                    if(lengthSix){
                        Toast.makeText(getContext(), "Maximum length reached.", Toast.LENGTH_SHORT).show();
                        lengthSix = false;
                    }

                } else {
                    lengthSix = true;
                }

                if (!editTextNumberCoinExtra.getText().toString().equals("")) {
                    int CoinExtra = Integer.parseInt(editTextNumberCoinExtra.getText().toString());
                    textViewResCoinExtra.setText(AddComma.getIndianCurrencyFormat(String.valueOf(1 * CoinExtra)));
                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",",""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfCoins = ((my20Coin/20) + (my10Coin/10) + (my5Coin/5) + (my2Coin/2) + (my1Coin));
                    long myLongTotalValueOfCoins = my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;
                    String myStringTotalValueOfCoins = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfCoins));

                    try {
                        TextView textViewTotalNumberOfCoins = getActivity().findViewById(R.id.textViewTotalNumberOfCoins);
                        textViewTotalNumberOfCoins.setText(totalNumberOfCoins + " Pcs");
                    } catch (NullPointerException Ignore){ }

                    try {
                        TextView textViewTotalValueOfCoins = getActivity().findViewById(R.id.textViewTotalValueOfCoins);
                        textViewTotalValueOfCoins.setText("₹ " + myStringTotalValueOfCoins);
                    } catch (NullPointerException Ignore){ }

                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));
                    MainActivity.textViewResult.setText(myIntResNew);

                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);
                    String returnz = WordsTp.convert(numberz);
                    try {

                        DetailsFragment.TotalAmount.setText(myIntResNew);
                        DetailsFragment.TotalAmountInWords.setText(returnz);
                        ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                    }catch (NullPointerException ignored){}

                    badgeExtracoin = 1;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);



                } else {


                    badgeExtracoin = 0;
                    badgeAddition =  badge2000 + badge500 + badge200 + badge100 + badge50 + badge20 + badge10 + badge5 + badge20coin +
                            badge10coin + badge5coin + badge2coin + badge1coin + badgeExtracoin;
                    Update(badgeAddition);

                    textViewResCoinExtra.setText(String.valueOf(0));
                    int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));
                    int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
                    int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));
                    long myIntRes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5 + my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;

                    int totalNumberOfCoins = ((my20Coin/20) + (my10Coin/10) + (my5Coin/5) + (my2Coin/2) + (my1Coin));
                    long myLongTotalValueOfCoins = my20Coin +  my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;
                    String myStringTotalValueOfCoins = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfCoins));

                    try {
                        TextView textViewTotalNumberOfCoins = getActivity().findViewById(R.id.textViewTotalNumberOfCoins);
                        textViewTotalNumberOfCoins.setText(totalNumberOfCoins + " Pcs");
                    } catch (NullPointerException ignored){ }

                    try {
                        TextView textViewTotalValueOfCoins = getActivity().findViewById(R.id.textViewTotalValueOfCoins);
                        textViewTotalValueOfCoins.setText("₹ " + myStringTotalValueOfCoins);
                    } catch (NullPointerException ignored){ }

                    String myIntResNew = AddComma.getIndianCurrencyFormat(String.valueOf(myIntRes));

                    try {

                        MainActivity.textViewResult.setText(myIntResNew);
                        DetailsFragment.TotalAmount.setText(myIntResNew);

                    }catch (NullPointerException ignored){}

                    int myIntNumber = Integer.parseInt(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));
                    String numberz = String.valueOf(myIntNumber);
                    String returnz = WordsTp.convertWithoutRupeesOnly(numberz);
                    if(!editTextNumber2000.getText().toString().equals("")
                            || !editTextNumber500.getText().toString().equals("")
                            || !editTextNumber200.getText().toString().equals("")
                            || !editTextNumber100.getText().toString().equals("")
                            || !editTextNumber50.getText().toString().equals("")
                            || !editTextNumber20.getText().toString().equals("")
                            || !editTextNumber10.getText().toString().equals("")
                            || !editTextNumber5.getText().toString().equals("")
                            || !editTextNumber20Coin.getText().toString().equals("")
                            || !editTextNumber10Coin.getText().toString().equals("")
                            || !editTextNumber5Coin.getText().toString().equals("")
                            || !editTextNumber2Coin.getText().toString().equals("")
                            || !editTextNumber1Coin.getText().toString().equals("")
                            || !editTextNumberCoinExtra.getText().toString().equals("")) {

                        try {

                            DetailsFragment.TotalAmountInWords.setText(returnz + " Rupees Only.");
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz + " Rupees Only.");

                        }catch (NullPointerException ignored){}
                    } else {

                        try {

                            DetailsFragment.TotalAmountInWords.setText(returnz);
                            ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(returnz);

                        }catch (NullPointerException ignored){}
                    }

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }


    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        INPUT_METHOD_SERVICE);
        if (inputMethodManager.isAcceptingText()) {

            try{
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),0);
            }catch(NullPointerException ignored){

            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        try {

            if (Long.parseLong("0" + tallyAmount.getText().toString().replaceAll(",", "")) != 0) {

                Long myLongTallyAmount = Long.parseLong(tallyAmount.getText().toString().replaceAll(",", ""));

                Long myLongResultAmount = Long.parseLong(MainActivity.textViewResult.getText().toString().replaceAll(",", ""));


                try {

                    TextView textViewTellerNumberDetailsFragment = getActivity().findViewById(R.id.textViewTellerNumberDetailsFragment);
                    textViewTellerNumberDetailsFragment.setText(tallyAmount.getText().toString());

                } catch (NullPointerException ignored) {}


                if (myLongTallyAmount > myLongResultAmount) {


                    try {
                        textViewTellerBalance.setText("Lesser by -> ₹ " + AddComma.getIndianCurrencyFormat(String.valueOf(myLongTallyAmount - myLongResultAmount)));
                        textViewTellerBalance.setTextColor(Color.parseColor("#ff0000"));

                        TextView textViewTellerDetailsFragment = getActivity().findViewById(R.id.textViewTellerDetailsFragment);
                        textViewTellerDetailsFragment.setText("Cash is lesser by -> ₹ " + AddComma.getIndianCurrencyFormat(String.valueOf(myLongTallyAmount - myLongResultAmount)));
                        textViewTellerDetailsFragment.setTextColor(Color.parseColor("#ff0000"));

                        MainActivity.textViewResult.setTextColor(Color.parseColor("#ffffff"));
                        TextView textViewTotalAmount = getActivity().findViewById(R.id.textViewTotalAmount);
                        textViewTotalAmount.setTextColor(Color.parseColor("#ffffff"));
                        ((MainActivity) getActivity()).textViewRupeesSymbol.setTextColor(Color.parseColor("#ff0000"));
                        TextView textViewRupeesSymbolDetailsFragment = getActivity().findViewById(R.id.textViewRupeesSymbolDetailsFragment);
                        textViewRupeesSymbolDetailsFragment.setTextColor(Color.parseColor("#ff0000"));
                    } catch (NullPointerException ignored) {
                    }

                } else if (myLongTallyAmount < myLongResultAmount) {

                    try {

                        textViewTellerBalance.setText("Greater by -> ₹ " + AddComma.getIndianCurrencyFormat(String.valueOf(myLongResultAmount - myLongTallyAmount)));
                        textViewTellerBalance.setTextColor(Color.parseColor("#ffff00"));

                        TextView textViewTellerDetailsFragment = getActivity().findViewById(R.id.textViewTellerDetailsFragment);
                        textViewTellerDetailsFragment.setText("Cash is greater by -> ₹ " + AddComma.getIndianCurrencyFormat(String.valueOf(myLongResultAmount - myLongTallyAmount)));
                        textViewTellerDetailsFragment.setTextColor(Color.parseColor("#ffff00"));

                        MainActivity.textViewResult.setTextColor(Color.parseColor("#ffffff"));
                        TextView textViewTotalAmount = getActivity().findViewById(R.id.textViewTotalAmount);
                        textViewTotalAmount.setTextColor(Color.parseColor("#ffffff"));
                        ((MainActivity) getActivity()).textViewRupeesSymbol.setTextColor(Color.parseColor("#ffff00"));

                        TextView textViewRupeesSymbolDetailsFragment = getActivity().findViewById(R.id.textViewRupeesSymbolDetailsFragment);
                        textViewRupeesSymbolDetailsFragment.setTextColor(Color.parseColor("#ffff00"));

                    } catch (NullPointerException ignored) {}

                } else {

                    try {
                        textViewTellerBalance.setText("Cash Tallied Successfully!");
                        textViewTellerBalance.setTextColor(Color.parseColor("#3bd16f"));

                        TextView textViewTellerDetailsFragment = getActivity().findViewById(R.id.textViewTellerDetailsFragment);
                        textViewTellerDetailsFragment.setText("Cash Tallied Successfully!");
                        textViewTellerDetailsFragment.setTextColor(Color.parseColor("#3bd16f"));


                        MainActivity.textViewResult.setTextColor(Color.parseColor("#3bd16f"));
                        TextView textViewTotalAmount = getActivity().findViewById(R.id.textViewTotalAmount);
                        textViewTotalAmount.setTextColor(Color.parseColor("#3bd16f"));
                        ((MainActivity) getActivity()).textViewRupeesSymbol.setTextColor(Color.parseColor("#3bd16f"));
                        TextView textViewRupeesSymbolDetailsFragment = getActivity().findViewById(R.id.textViewRupeesSymbolDetailsFragment);
                        textViewRupeesSymbolDetailsFragment.setTextColor(Color.parseColor("#3bd16f"));

                    } catch (NullPointerException ignored) {
                    }

                }

            }
        } catch (NullPointerException ignored){}


        try {
            DetailsFragment.TotalAmount.setText(MainActivity.textViewResult.getText().toString());
        }catch (NullPointerException ignored){}



        if((MainActivity.textViewResult.getText().toString().equals("0"))){
            try {
                DetailsFragment.TotalAmountInWords.setText("Zero" + WordsTp.convert( MainActivity.textViewResult.getText().toString().replaceAll(",","")));
            }catch (NullPointerException ignored){}

        }else {

            try {
                DetailsFragment.TotalAmountInWords.setText( WordsTp.convert( MainActivity.textViewResult.getText().toString().replaceAll(",","")));
            }catch (NullPointerException ignored){}

        }


        try {

            if((MainActivity.textViewResult.getText().toString().equals("0"))){

                ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText("Zero" + WordsTp.convert( MainActivity.textViewResult.getText().toString().replaceAll(",","")));

            } else {
                ((MainActivity)getActivity()).textviewWordsCalculationFragment.setText(WordsTp.convert( MainActivity.textViewResult.getText().toString().replaceAll(",","")));

            }

              }catch (NullPointerException ignored){}




        try {

            int my2000 = Integer.parseInt(textViewRes2000.getText().toString().replaceAll(",", ""));
            int my500 = Integer.parseInt(textViewRes500.getText().toString().replaceAll(",", ""));
            int my200 = Integer.parseInt(textViewRes200.getText().toString().replaceAll(",", ""));
            int my100 = Integer.parseInt(textViewRes100.getText().toString().replaceAll(",", ""));
            int my50 = Integer.parseInt(textViewRes50.getText().toString().replaceAll(",", ""));
            int my20 = Integer.parseInt(textViewRes20.getText().toString().replaceAll(",", ""));
            int my10 = Integer.parseInt(textViewRes10.getText().toString().replaceAll(",", ""));
            int my5 = Integer.parseInt(textViewRes5.getText().toString().replaceAll(",", ""));

            int totalNumberOfNotes = ((my2000/2000) + (my500/500) + (my200/200) + (my100/100) + (my50/50) + (my20/20) + (my10/10) + (my5/5));
            long myLongTotalValueOfNotes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5;
            String myStringTotalValueOfNotes = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfNotes));

            try {
                TextView textViewTotalNumberOfNotes = getActivity().findViewById(R.id.textViewTotalNumberOfNotes);
                textViewTotalNumberOfNotes.setText(totalNumberOfNotes + " Pcs");
            } catch (NullPointerException ignored){ }

            try {
                TextView textViewTotalValueOfNotes = getActivity().findViewById(R.id.textViewTotalValueOfNotes);
                textViewTotalValueOfNotes.setText("₹ " + myStringTotalValueOfNotes);
            } catch (NullPointerException ignored){ }


        }catch (NullPointerException ignored){}



        try {

            int my20Coin = Integer.parseInt(textViewRes20Coin.getText().toString().replaceAll(",", ""));
            int my10Coin = Integer.parseInt(textViewRes10Coin.getText().toString().replaceAll(",", ""));
            int my5Coin = Integer.parseInt(textViewRes5Coin.getText().toString().replaceAll(",", ""));
            int my2Coin = Integer.parseInt(textViewRes2Coin.getText().toString().replaceAll(",", ""));
            int my1Coin = Integer.parseInt(textViewRes1Coin.getText().toString().replaceAll(",", ""));
            int myCoinExtra = Integer.parseInt(textViewResCoinExtra.getText().toString().replaceAll(",", ""));

            int totalNumberOfCoins = ((my20Coin/20) + (my10Coin/10) + (my5Coin/5) + (my2Coin/2) + (my1Coin));
            long myLongTotalValueOfCoins = my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;
            String myStringTotalValueOfCoins = AddComma.getIndianCurrencyFormat(String.valueOf(myLongTotalValueOfCoins));

            try {
                TextView textViewTotalNumberOfCoins = getActivity().findViewById(R.id.textViewTotalNumberOfCoins);
                textViewTotalNumberOfCoins.setText(totalNumberOfCoins + " Pcs");
            } catch (NullPointerException ignored){ }

            try {
                TextView textViewTotalValueOfCoins = getActivity().findViewById(R.id.textViewTotalValueOfCoins);
                textViewTotalValueOfCoins.setText("₹ " + myStringTotalValueOfCoins);
            } catch (NullPointerException ignored){ }

        }catch (NullPointerException ignored){}

        isCalculationFragmentReady = true;

    }

    public void setupUI(final View view) {

//         Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(getActivity());

                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);


            }

        }
    }

    public void Update(int number) {

        try {

            ( (MainActivity) getActivity()).tabLayout.getTabAt(0).getOrCreateBadge().setNumber(number);
            ( (MainActivity) getActivity()).tabLayout.getTabAt(0).getOrCreateBadge().setBackgroundColor(Color.parseColor("#1da1f3"));
        } catch (NullPointerException ignored) { }
    }

    public void ClearBadge() {

        try {

            ( (MainActivity) getActivity()).tabLayout.getTabAt(0).getOrCreateBadge().clearNumber();

        } catch (NullPointerException ignored) { }
    }

    public void HideBadge() {

        try {

            ( (MainActivity) getActivity()).tabLayout.getTabAt(0).getOrCreateBadge().setVisible(false);

        } catch (NullPointerException ignored ) { }
    }


    public void ShowBadge() {

        try {

            ( (MainActivity) getActivity()).tabLayout.getTabAt(0).getOrCreateBadge().setVisible(true);

        } catch (NullPointerException ignored ) { }
    }


    public void BlinkBadge() {


            final Handler handler = new Handler();

            handler.postDelayed(new Runnable(){
                @Override
                public void run() {

                    try {

                        ( (MainActivity) getActivity()).tabLayout.getTabAt(0).getOrCreateBadge().setBackgroundColor(Color.parseColor("#ffff00"));

                    } catch (NullPointerException ignored ) { }
            }
            },100);

    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {

        if (prefs.isRemoveAd()) {
            menu.findItem(R.id.RemoveAd).setVisible(false);
            menu.findItem(R.id.vip).setVisible(true);
        }

        menu.findItem(R.id.DeleteAll).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }
}