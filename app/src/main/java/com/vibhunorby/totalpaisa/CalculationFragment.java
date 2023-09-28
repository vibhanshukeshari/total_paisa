package com.vibhunorby.totalpaisa;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import static android.content.Context.INPUT_METHOD_SERVICE;

public class CalculationFragment extends Fragment {

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

     static NestedScrollView nestedScrollView;

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


//        It took one week to get the idea about using set on click listener with focus listener before i was using only focus listener.

//        This is used to hide bottom navigation for the second click because setOnClickListener doesn't listen first.
//
        editTextNumber2000.setOnClickListener(new View.OnClickListener() {
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
                editTextNumber2000.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focus) {

                if(focus) {

                    if(!MainActivity.keyboard_up) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                        }catch (NullPointerException ignored){ }

                    }
                }
            }
        });

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
        });

//        This is used to hide bottom navigation for the first click because only focus listen first.
        editTextNumber500.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focus) {

                if(focus) {

                    if(!MainActivity.keyboard_up) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                        }catch (NullPointerException ignored){ }

                    }
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

//        This is used to hide bottom navigation for the first click because only focus listen first.
        editTextNumber200.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focus) {

                if(focus) {


                    if(!MainActivity.keyboard_up) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                        }catch (NullPointerException ignored){ }

                    }
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

                    if(!MainActivity.keyboard_up) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                        }catch (NullPointerException ignored){ }

                    }

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

                    if(!MainActivity.keyboard_up) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                        }catch (NullPointerException ignored){ }

                    }

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

//        This is used to hide bottom navigation for the first click because only focus listen first.
        editTextNumber20.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focus) {

                if(focus) {

                    if(!MainActivity.keyboard_up) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                        }catch (NullPointerException ignored){ }

                    }
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

                    if(!MainActivity.keyboard_up) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                        }catch (NullPointerException ignored){ }

                    }

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

                    if(!MainActivity.keyboard_up) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                        }catch (NullPointerException ignored){ }

                    }

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
        editTextNumber10Coin.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focus) {

                if(focus) {

                    if(!MainActivity.keyboard_up) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                        }catch (NullPointerException ignored){ }

                    }

                }
            }
        });


//        This is used to hide bottom navigation for the first click because only focus listen first.
        editTextNumber10Coin.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean focus) {

                if(focus) {

                    if(!MainActivity.keyboard_up) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                        }catch (NullPointerException ignored){ }

                    }

                }
            }
        });

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

                    if(!MainActivity.keyboard_up) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                        }catch (NullPointerException ignored){ }

                    }

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

                    if(!MainActivity.keyboard_up) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                        }catch (NullPointerException ignored){ }

                    }

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

                    if(!MainActivity.keyboard_up) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                        }catch (NullPointerException ignored){ }

                    }
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


                    if(!MainActivity.keyboard_up) {

                        try {
                            ((MainActivity) getActivity()).textViewResultAmountLayout.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).underLine.setVisibility(View.GONE);
                            ((MainActivity) getActivity()).textViewResultTopBar.setVisibility(View.GONE);
                        }catch (NullPointerException ignored){ }

                    }
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

                if(editTextNumber500.getText().toString().length() == 5) {

                    Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();

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

                if(editTextNumber200.getText().toString().length() == 5) {

                    Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();

//
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


                if(editTextNumber100.getText().toString().length() == 5) {

                    Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();

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


                if(editTextNumber50.getText().toString().length() == 5) {

                     Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT);


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


                if(editTextNumber20.getText().toString().length() == 5) {

                    Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();

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


                if(editTextNumber10.getText().toString().length() == 5) {

                    Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();


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


                if(editTextNumber5.getText().toString().length() == 5) {

                    Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();

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


                if(editTextNumber20Coin.getText().toString().length() == 6) {

                    Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();

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


                if(editTextNumber10Coin.getText().toString().length() == 6) {

                     Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();

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


                if(editTextNumber5Coin.getText().toString().length() == 6) {

                     Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();


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


                if(editTextNumber2Coin.getText().toString().length() == 6) {

                    Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();


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


                if(editTextNumber1Coin.getText().toString().length() == 6) {

                    Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();

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


                if(editTextNumberCoinExtra.getText().toString().length() == 6) {

                    Toast.makeText(getContext(), "Maximum limit reached.", Toast.LENGTH_SHORT).show();

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