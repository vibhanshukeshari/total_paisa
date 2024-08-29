// Development was started from  07/01/2021
//GSt merged with total paisa on 08/07/2021
//Ad remove purchase feature removed due to unsupported version of unknown library
// Tried to implement new billing library by google but left coz was messy( haha.. see line no 15. achieved on that day)
// only 6 pro was sold in 4 months.
//Final version of total Paisa  with in app purchase to remove ad was released on 09/10/2021
// Total paisa took approximately 7 months constantly.
//present of total paisa on today 09/10/21 it has 2706 downloads on play store
// In future I vibhu has decided to add calculator and cash ledger like khata book with mysql database.
//I learnt one thing after developing total paisa Never stop ,you will face many errors and bug but even though keep trying keep tying....
// In total paisa assert background color changing toast were used which were creating issue of crash on Redmi and 1 plus decides it fixed after two yeas when i checked on my sister device(12/03/2023)
// since all toast are normal and old one are removed.
// I made some changes on Data base, reduced data base lag (But it is not a proper way ) in Interest calculator I have done very well(10/03/23).
// From 10/2022 downloads are decreased a lot ( Reason is still not recognised).
//  App billing by google (IN app purchase added) latest version on 28/04/23
// Dialog box enhanced - 28/04/23
//G20 icon added - 28/04/23
// Native ads added - 28/04/23
// icon kitchen website is used to create icon and it is improved 28/04/23 it has a foreground color problem - https://icon.kitchen/i/H4sIAAAAAAAAA6tWKkvMKU0tVrKqVkpJLMoOyUjNTVWyKikqTa3VUcrNTynNAUlGKyXmpRTlZ6Yo6Shl5hcDyfLUJKXYWgA19PHYPwAAAA%3D%3D
//Calc lite added on 27/09/2023 totally designed by vibhu
// some ads are altered due to the decrement of E cpm;
//G20 icon removed
//2000 currency removed
// gst easy clear button placed to top
//and some other feature altered on 27/09/23
//major code upgraded it had code redundancy i revoked more than 8000 lines of code of that was duplicate on 29/08/24
//jai swami narayan :)


package com.vibhunorby.totalpaisa;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;
import android.view.MotionEvent;
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
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class Gst extends AppCompatActivity {

    private boolean edit = true;
    private  int  cursorPosition;

    private Double totalAmount;
    private Double initialAmount;
    private Double IgstAmount;
    private Double CgstAmount;
    private Double SgstAmount;
    private Double netAmount;
    private String roundNetAmount;
    private String roundCgstAmount;
    private String roundSgstAmount;
    private String roundTotalAmount;
    private String roundIgstAmount;
    private Double stringToDouble;
    private String trimString;
    private long stringToLong;
    private long realIntegerPart;
    private long realDecimalPart;
    private String LongToStringIntPart;
    private String LongToStringDecPart;
    private String IntegerToWord;
    private String DecimalToWord;

    private TextView textViewIgst;
    private TextView textViewIgstResult;
    private TextView textViewSgst;
    private TextView textViewSgstResult;
    private TextView textViewCgst;
    private TextView textViewCgstResult;
    private TextView textViewTotalAmountResult;
    private TextView textViewForResultNetAmount;

    Prefs prefs;
    EditText editTextInitialValue;
    TextView totalAmountWord;

    private static boolean keyboard_up_gst;

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


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int nightModeFlags = getResources().getConfiguration().uiMode &
                Configuration.UI_MODE_NIGHT_MASK;

        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                setTheme(R.style.AppTheme_Dark);
                break;

            case Configuration.UI_MODE_NIGHT_NO:
                setTheme(R.style.AppTheme_Light);
                break;

            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                // Handle the case when the system theme is undefined
                setTheme(R.style.AppTheme_Light); // Default theme
                break;
        }


        setContentView(R.layout.activity_gst);
        Toolbar toolbar = findViewById(R.id.myToolBar);
        toolbar.setTitle("Gst Easy");
        setSupportActionBar(toolbar);
        prefs = new Prefs(getApplicationContext());

        getWindow().setNavigationBarColor(Color.parseColor("#15202b"));

        totalAmountWord = findViewById(R.id.textViewWords);
        textViewclear = findViewById(R.id.textViewClear);
        final BottomNavigationView bottomNavigationView = findViewById(R.id.botton_navigation);
        editTextInitialValue = findViewById(R.id.editTextInitialValue);
        chip3Percent = findViewById(R.id.chip3Percent);
        chip5Percent = findViewById(R.id.chip5Percent);
        chip12Percent = findViewById(R.id.chip12Percent);
        chip18Percent = findViewById(R.id.chip18Percent);
        chip28Percent = findViewById(R.id.chip28Percent);

        newLayoutNetAmount = findViewById(R.id.layoutNetAmount);
        newlayoutTotalAmount = findViewById(R.id.layoutTotalAmount);
        newlayoutSgst = findViewById(R.id.layoutSgst);
        newlayoutCgst = findViewById(R.id.layoutCgst);
        newlayoutIgst = findViewById(R.id.layoutIgst);
        radioCgstSgst = findViewById(R.id.radioCgstAndSgst);
        radioIgst = findViewById(R.id.radioIgst);
        radioExcluding = findViewById(R.id.radioExcluding);
        radioIncluding = findViewById(R.id.radioIncluding);

        ChipGroup chipGroup = findViewById(R.id.chipGroup);
        RadioGroup radioExIN = findViewById(R.id.radioGroup);
        RadioGroup radioCgstSgstIgst = findViewById(R.id.radioGroupForTaxType);


        RelativeLayout relativeLayout = findViewById(R.id.rootViewGst);
        relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            Rect r = new Rect();
            int screenHeight = relativeLayout.getRootView().getHeight();
            int keypadHeight = screenHeight - r.bottom;
            if(keypadHeight > screenHeight * 0.15) {
//                    keyboard is up

                keyboard_up_gst = true;

            } else {
//                    keyboard is down

                if (keyboard_up_gst) {

                    try {
                        editTextInitialValue.clearFocus();
                    }catch (NullPointerException ignored){ }

                    keyboard_up_gst = false;

                }

            }
        });



        bottomNavigationView.setSelectedItemId(R.id.gst);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.gst) {
                // Handle GST action
                return true;
            } else if (itemId == R.id.totalPaisa) {
                // Handle TotalPaisa action

                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (itemId == R.id.simpleCalc) {
                // Handle SimpleCalc action
                startActivity(new Intent(getApplicationContext(), CalcLite.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            }

            return false;
        });



          textViewIgst = new TextView(Gst.this);
          textViewIgstResult = new TextView(Gst.this);

          textViewSgst = new TextView(Gst.this);
          textViewSgstResult = new TextView(Gst.this);

          textViewCgst = new TextView(Gst.this);
          textViewCgstResult = new TextView(Gst.this);

        TextView textViewTotalAmount = new TextView(Gst.this);
          textViewTotalAmountResult = new TextView(Gst.this);

        TextView textViewNetAmount = new TextView(Gst.this);
          textViewForResultNetAmount = new TextView(Gst.this);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1F);
        params.setMargins(10, 10, 10, 10);

        textViewIgst.setLayoutParams(params);
        textViewIgstResult.setLayoutParams(params);

        textViewSgst.setLayoutParams(params);
        textViewSgstResult.setLayoutParams(params);

        textViewCgstResult.setLayoutParams(params);
        textViewCgst.setLayoutParams(params);

        textViewTotalAmount.setLayoutParams(params);
        textViewTotalAmountResult.setLayoutParams(params);

        textViewNetAmount.setLayoutParams(params);
        textViewForResultNetAmount.setLayoutParams(params);

        textViewNetAmount.setPadding(16, 0, 16, 0);
        textViewNetAmount.setTextColor(ContextCompat.getColor(this, R.color.chip_color));
        textViewNetAmount.setTextSize(16);

        textViewForResultNetAmount.setPadding(16, 0, 16, 0);
        textViewForResultNetAmount.setText("₹ 0");
        textViewForResultNetAmount.setGravity(Gravity.END);
        textViewForResultNetAmount.setTextColor(ContextCompat.getColor(this, R.color.chip_color));
        textViewForResultNetAmount.setTextSize(16);

        textViewTotalAmount.setPadding(16, 0, 16, 20);
        textViewTotalAmount.setTextColor(ContextCompat.getColor(this, R.color.chip_color));
        textViewTotalAmount.setTextSize(16);

        textViewTotalAmountResult.setPadding(16, 0, 16, 20);
        textViewTotalAmountResult.setText("₹ 0");
        textViewTotalAmountResult.setGravity(Gravity.END);
        textViewTotalAmountResult.setTextColor(ContextCompat.getColor(this, R.color.chip_color));
        textViewTotalAmountResult.setTextSize(16);

        textViewIgst.setText("IGST Amount");
        textViewIgst.setPadding(16, 0, 16, 0);
        textViewIgst.setTextColor(ContextCompat.getColor(this, R.color.chip_color));
        textViewIgst.setTextSize(16);


        textViewIgstResult.setText("₹ 0");
        textViewIgstResult.setPadding(16, 0, 16, 0);
        textViewIgstResult.setTextSize(16);
        textViewIgstResult.setGravity(Gravity.END);
        textViewIgstResult.setTextColor(ContextCompat.getColor(this, R.color.chip_color));
        textViewIgstResult.setId(R.id.textViewIgstResult);

        textViewCgst.setText("CGST Amount");
        textViewCgst.setPadding(16, 0, 16, 0);
        textViewCgst.setTextSize(16);
        textViewCgst.setTextColor(ContextCompat.getColor(this, R.color.chip_color));

        textViewCgstResult.setText("₹ 0");
        textViewCgstResult.setPadding(16, 0, 16, 0);
        textViewCgstResult.setGravity(Gravity.END);
        textViewCgstResult.setTextSize(16);
        textViewCgstResult.setTextColor(ContextCompat.getColor(this, R.color.chip_color));


        textViewSgst.setText("SGST Amount");
        textViewSgst.setPadding(16, 0, 16, 0);
        textViewSgst.setTextSize(16);
        textViewSgst.setTextColor(ContextCompat.getColor(this, R.color.chip_color));

        textViewSgstResult.setText("₹ 0");
        textViewSgstResult.setPadding(16, 0, 16, 0);
        textViewSgstResult.setGravity(Gravity.END);
        textViewSgstResult.setTextSize(16);
        textViewSgstResult.setTextColor(ContextCompat.getColor(this, R.color.chip_color));


        newlayoutIgst.removeAllViews();

        newlayoutCgst.addView(textViewCgst);
        newlayoutCgst.addView(textViewCgstResult);

        newlayoutSgst.addView(textViewSgst);
        newlayoutSgst.addView(textViewSgstResult);

        newLayoutNetAmount.addView(textViewNetAmount);
        newLayoutNetAmount.addView(textViewForResultNetAmount);


        newlayoutTotalAmount.addView(textViewTotalAmount);
        newlayoutTotalAmount.addView(textViewTotalAmountResult);


        textViewclear.setOnClickListener(_ -> {

            textViewCgst.setText("CGST Amount");
            textViewCgstResult.setText("₹ 0");
            textViewSgst.setText("SGST Amount");
            textViewSgstResult.setText("₹ 0");
            textViewIgst.setText("IGST Amount");
            textViewIgstResult.setText("₹ 0");
            textViewForResultNetAmount.setText("₹ 0");
            editTextInitialValue.getText().clear();

        });

        String stringNetAmount = "Net Amount (excluding GST)";
        SpannableString ss1 = new SpannableString(stringNetAmount);
        ss1.setSpan(new RelativeSizeSpan(0.8f), 11, 26, 0);
        textViewNetAmount.setText(ss1);

        String stringTotalAmount = "Total Amount (including GST)";
        SpannableString ss2 = new SpannableString(stringTotalAmount);
        ss2.setSpan(new RelativeSizeSpan(0.8f), 13, 28, 0);

        StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
        ss2.setSpan(bss, 0, 12, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        textViewTotalAmountResult.setTypeface(null, Typeface.BOLD);
        textViewTotalAmount.setText(ss2);


        editTextInitialValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                cursorPosition = editTextInitialValue.getText().toString().length() - editTextInitialValue.getSelectionStart();
            }


            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//
//                if (!editTextInitialValue.getText().toString().isEmpty()) {
//                    if (radioExcluding.isChecked()) {
//                        if (radioIgst.isChecked()) {
//                            if (chip3Percent.isChecked()) {
//
//                                excludingIgstChip(3);
//
//                            } else if (chip5Percent.isChecked()) {
//
//                                excludingIgstChip(5);
//
//                            } else if (chip12Percent.isChecked()) {
//
//                                excludingIgstChip(12);
//
//                            } else if (chip18Percent.isChecked()) {
//
//                                excludingIgstChip(18);
//
//                            } else if (chip28Percent.isChecked()) {
//
//                                excludingIgstChip(28);
//
//                            }
//
//
//                        } else if (radioCgstSgst.isChecked()) {
//                            if (chip3Percent.isChecked()) {
//
//                                excludingCgstSgstChip(3);
//
//                            } else if (chip5Percent.isChecked()) {
//
//                                excludingCgstSgstChip(5);
//
//                            } else if (chip12Percent.isChecked()) {
//
//                                excludingCgstSgstChip(12);
//
//
//                            } else if (chip18Percent.isChecked()) {
//
//                                excludingCgstSgstChip(12);
//
//                            } else if (chip28Percent.isChecked()) {
//
//                                excludingCgstSgstChip(28);
//
//                            }
//                        }
//                    } else if (radioIncluding.isChecked()) {
//                        if (radioCgstSgst.isChecked()) {
//                            if (chip3Percent.isChecked()) {
//
//
//                                includingCgstSgstChip(3);
//
//
//                            } else if (chip5Percent.isChecked()) {
//
//
//                                includingCgstSgstChip(5);
//
//
//                            } else if (chip12Percent.isChecked()) {
//
//                                includingCgstSgstChip(12);
//
//                            } else if (chip18Percent.isChecked()) {
//
//                                includingCgstSgstChip(18);
//
//                            } else if (chip28Percent.isChecked()) {
//
//                                includingCgstSgstChip(28);
//
//                            }
//
//
//                        } else if (radioIgst.isChecked()) {
//                            if (chip3Percent.isChecked()) {
//
//                                includingIgstChip(3);
//
//                            } else if (chip5Percent.isChecked()) {
//
//                                includingIgstChip(5);
//
//                            } else if (chip12Percent.isChecked()) {
//
//                                includingIgstChip(12);
//
//                            } else if (chip18Percent.isChecked()) {
//
//                                includingIgstChip(18);
//
//                            } else if (chip28Percent.isChecked()) {
//
//                                includingIgstChip(28);
//
//                            }
//                        }
//                    }
//
//
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
            }


            @Override
            public void afterTextChanged(Editable editable) {


                if(!editTextInitialValue.getText().toString().isEmpty()) {

                    String myTellerWithoutComma;

                    if (edit) {
                        edit = false;
                        myTellerWithoutComma = editTextInitialValue.getText().toString().replaceAll(",", "");
                        editTextInitialValue.setText(AddComma.getIndianCurrencyFormat(myTellerWithoutComma));
                        edit = true;

                    }

                    editTextInitialValue.setSelection(editTextInitialValue.getText().toString().length() - cursorPosition);


                }
            }
        });



        radioCgstSgstIgst.setOnCheckedChangeListener((_, i) -> {


              if(i == R.id.radioCgstAndSgst) {

                                    newlayoutIgst.removeAllViews();

                                    newlayoutCgst.addView(textViewCgst);
                                    newlayoutCgst.addView(textViewCgstResult);

                                    newlayoutSgst.addView(textViewSgst);
                                    newlayoutSgst.addView(textViewSgstResult);


                                    if (!editTextInitialValue.getText().toString().isEmpty()) {
                                        if (radioExcluding.isChecked()) {
                                            if (chip3Percent.isChecked()) {

                                                    excludingCgstSgstChip(3);

                                                } else if (chip5Percent.isChecked()) {

                                                    excludingCgstSgstChip(5);

                                                } else if (chip12Percent.isChecked()) {

                                                    excludingCgstSgstChip(12);

                                                } else if (chip18Percent.isChecked()) {

                                                    excludingCgstSgstChip(18);

                                                } else if (chip28Percent.isChecked()) {

                                                    excludingCgstSgstChip(28);

                                                }

                                        } else if (radioIncluding.isChecked()) {
                                            if (radioCgstSgst.isChecked()) {
                                                if (chip3Percent.isChecked()) {

                                                    includingCgstSgstChip(3);

                                                } else if (chip5Percent.isChecked()) {

                                                    includingCgstSgstChip(5);

                                                } else if (chip12Percent.isChecked()) {

                                                    includingCgstSgstChip(12);

                                                } else if (chip18Percent.isChecked()) {


                                                    includingCgstSgstChip(18);


                                                } else if (chip28Percent.isChecked()) {

                                                    includingCgstSgstChip(28);

                                                }


                                            }
                                        }

                                    } else {
                                        textViewCgstResult.setText("₹ 0");
                                        textViewSgstResult.setText("₹ 0");
                                        textViewIgstResult.setText("₹ 0");
                                        textViewTotalAmountResult.setText("₹ 0");
                                        textViewForResultNetAmount.setText("₹ 0");
                                        textViewTotalAmountResult.setText("₹ 0");
                                        totalAmountWord.setText("");
                                    }


              } else if(i == R.id.radioIgst){

                    newlayoutCgst.removeAllViews();
                    newlayoutSgst.removeAllViews();

                    newlayoutIgst.addView(textViewIgst);
                    newlayoutIgst.addView(textViewIgstResult);

                    if (!editTextInitialValue.getText().toString().isEmpty()) {
                        if (radioExcluding.isChecked()) {

                                if (chip3Percent.isChecked()) {

                                    excludingIgstChip(3);

                                } else if (chip5Percent.isChecked()) {

                                    excludingIgstChip(5);

                                } else if (chip12Percent.isChecked()) {

                                    excludingIgstChip(12);

                                } else if (chip18Percent.isChecked()) {

                                    excludingIgstChip(18);

                                } else if (chip28Percent.isChecked()) {

                                    excludingIgstChip(28);
                                }

                        } else if (radioIncluding.isChecked()) {
                                if (chip3Percent.isChecked()) {

                                    includingIgstChip(3);

                                } else if (chip5Percent.isChecked()) {

                                    includingIgstChip(5);

                                } else if (chip12Percent.isChecked()) {

                                    includingIgstChip(12);

                                } else if (chip18Percent.isChecked()) {

                                    includingIgstChip(18);

                                } else if (chip28Percent.isChecked()) {

                                    includingIgstChip(28);

                                }
                        }
                    } else {
                        textViewCgstResult.setText("₹ 0");
                        textViewSgstResult.setText("₹ 0");
                        textViewIgstResult.setText("₹ 0");
                        textViewTotalAmountResult.setText("₹ 0");
                        textViewForResultNetAmount.setText("₹ 0");
                        textViewTotalAmountResult.setText("₹ 0");
                        totalAmountWord.setText("");
                    }
             }
        });


        radioExIN.setOnCheckedChangeListener((_, i) -> {


            if (i == R.id.radioExcluding){

                    if (!editTextInitialValue.getText().toString().isEmpty()) {
                            if (radioIgst.isChecked()) {
                                if (chip3Percent.isChecked()) {

                                    excludingIgstChip(3);

                                } else if (chip5Percent.isChecked()) {

                                    excludingIgstChip(5);

                                } else if (chip12Percent.isChecked()) {

                                    excludingIgstChip(12);

                                } else if (chip18Percent.isChecked()) {

                                    excludingIgstChip(18);

                                } else if (chip28Percent.isChecked()) {

                                    excludingIgstChip(28);

                                }

                            } else if (radioCgstSgst.isChecked()) {
                                if (chip3Percent.isChecked()) {

                                    excludingCgstSgstChip(3);


                                } else if (chip5Percent.isChecked()) {

                                    excludingCgstSgstChip(5);

                                } else if (chip12Percent.isChecked()) {

                                    excludingCgstSgstChip(12);

                                } else if (chip18Percent.isChecked()) {

                                    excludingCgstSgstChip(18);

                                } else if (chip28Percent.isChecked()) {

                                    excludingCgstSgstChip(28);


                                }
                            }

                    } else {
                        textViewCgstResult.setText("₹ 0");
                        textViewSgstResult.setText("₹ 0");
                        textViewIgstResult.setText("₹ 0");
                        textViewTotalAmountResult.setText("₹ 0");
                        textViewForResultNetAmount.setText("₹ 0");
                        textViewTotalAmountResult.setText("₹ 0");
                        totalAmountWord.setText("");
                    }


            }else if(i == R.id.radioIncluding){

                    if (!editTextInitialValue.getText().toString().isEmpty()) {
                            if (radioCgstSgst.isChecked()) {
                                if (chip3Percent.isChecked()) {

                                  includingCgstSgstChip(3);

                                } else if (chip5Percent.isChecked()) {

                                    includingCgstSgstChip(5);

                                } else if (chip12Percent.isChecked()) {

                                    includingCgstSgstChip(12);


                                } else if (chip18Percent.isChecked()) {

                                    includingCgstSgstChip(18);


                                } else if (chip28Percent.isChecked()) {

                                    includingCgstSgstChip(28);


                                }


                            } else if (radioIgst.isChecked()) {
                                if (chip3Percent.isChecked()) {


                                  includingIgstChip(3);


                                } else if (chip5Percent.isChecked()) {


                                    includingIgstChip(5);

                                } else if (chip12Percent.isChecked()) {

                                    includingIgstChip(12);

                                } else if (chip18Percent.isChecked()) {


                                    includingIgstChip(18);

                                } else if (chip28Percent.isChecked()) {

                                    includingIgstChip(28);

                                }
                            }

                    } else {
                        textViewCgstResult.setText("₹ 0");
                        textViewSgstResult.setText("₹ 0");
                        textViewIgstResult.setText("₹ 0");
                        textViewTotalAmountResult.setText("₹ 0");
                        textViewForResultNetAmount.setText("₹ 0");
                        textViewTotalAmountResult.setText("₹ 0");
                        totalAmountWord.setText("");
                    }


            }



        });



        chipGroup.setOnCheckedStateChangeListener((_, checkedIds) -> {

            if(checkedIds.contains(R.id.chip3Percent)){




                if (!editTextInitialValue.getText().toString().isEmpty()) {
                    if (radioExcluding.isChecked()) {
                        if (radioIgst.isChecked()) {

                            excludingIgstChip(3);


                        } else if (radioCgstSgst.isChecked()) {


                            excludingCgstSgstChip(3);

                        }
                    } else if (radioIncluding.isChecked()) {
                        if (radioCgstSgst.isChecked()) {

                            includingCgstSgstChip(3);

                        } else if (radioIgst.isChecked()) {

                            includingIgstChip(3);

                        }
                    }
                } else {
                    textViewCgstResult.setText("₹ 0");
                    textViewSgstResult.setText("₹ 0");
                    textViewIgstResult.setText("₹ 0");
                    textViewTotalAmountResult.setText("₹ 0");
                    textViewForResultNetAmount.setText("₹ 0");
                    textViewTotalAmountResult.setText("₹ 0");
                    totalAmountWord.setText("");
                }



            } else if (checkedIds.contains(R.id.chip5Percent)) {
                if (!editTextInitialValue.getText().toString().isEmpty()) {
                    if (radioExcluding.isChecked()) {
                        if (radioIgst.isChecked()) {

                            excludingIgstChip(5);

                        } else if (radioCgstSgst.isChecked()) {

                            excludingCgstSgstChip(5);

                        }
                    } else if (radioIncluding.isChecked()) {
                        if (radioCgstSgst.isChecked()) {

                            includingCgstSgstChip(5);

                        } else if (radioIgst.isChecked()) {

                            includingIgstChip(5);

                        }
                    }
                } else {
                    textViewCgstResult.setText("₹ 0");
                    textViewSgstResult.setText("₹ 0");
                    textViewIgstResult.setText("₹ 0");
                    textViewTotalAmountResult.setText("₹ 0");
                    textViewForResultNetAmount.setText("₹ 0");
                    textViewTotalAmountResult.setText("₹ 0");
                    totalAmountWord.setText("");
                }

            } else if (checkedIds.contains(R.id.chip12Percent)) {

                if (!editTextInitialValue.getText().toString().isEmpty()) {
                    if (radioExcluding.isChecked()) {
                        if (radioIgst.isChecked()) {

                            excludingIgstChip(12);

                        } else if (radioCgstSgst.isChecked()) {

                            excludingCgstSgstChip(12);

                        }
                    } else if (radioIncluding.isChecked()) {
                        if (radioCgstSgst.isChecked()) {

                            includingCgstSgstChip(12);

                        } else if (radioIgst.isChecked()) {

                            includingIgstChip(12);

                        }
                    }
                } else {
                    textViewCgstResult.setText("₹ 0");
                    textViewSgstResult.setText("₹ 0");
                    textViewIgstResult.setText("₹ 0");
                    textViewTotalAmountResult.setText("₹ 0");
                    textViewForResultNetAmount.setText("₹ 0");
                    textViewTotalAmountResult.setText("₹ 0");
                    totalAmountWord.setText("");
                }

            } else if (checkedIds.contains(R.id.chip18Percent)) {



                if (!editTextInitialValue.getText().toString().isEmpty()) {
                    if (radioExcluding.isChecked()) {
                        if (radioIgst.isChecked()) {

                            excludingIgstChip(18);

                        } else if (radioCgstSgst.isChecked()) {

                            excludingCgstSgstChip(18);

                        }
                    } else if (radioIncluding.isChecked()) {
                        if (radioCgstSgst.isChecked()) {

                            includingCgstSgstChip(18);


                        } else if (radioIgst.isChecked()) {

                            includingIgstChip(18);

                        }
                    }
                } else {
                    textViewCgstResult.setText("₹ 0");
                    textViewSgstResult.setText("₹ 0");
                    textViewIgstResult.setText("₹ 0");
                    textViewTotalAmountResult.setText("₹ 0");
                    textViewForResultNetAmount.setText("₹ 0");
                    textViewTotalAmountResult.setText("₹ 0");
                    totalAmountWord.setText("");
                }

            } else if (checkedIds.contains(R.id.chip28Percent)) {


                if (!editTextInitialValue.getText().toString().isEmpty()) {
                    if (radioExcluding.isChecked()) {
                        if (radioIgst.isChecked()) {

                            excludingIgstChip(28);

                        } else if (radioCgstSgst.isChecked()) {

                            excludingCgstSgstChip(28);



                        }
                    } else if (radioIncluding.isChecked()) {
                        if (radioCgstSgst.isChecked()) {

                            includingCgstSgstChip(28);


                        } else if (radioIgst.isChecked()) {

                            includingIgstChip(28);

                        }
                    }
                } else {
                    textViewCgstResult.setText("₹ 0");
                    textViewSgstResult.setText("₹ 0");
                    textViewIgstResult.setText("₹ 0");
                    textViewTotalAmountResult.setText("₹ 0");
                    textViewForResultNetAmount.setText("₹ 0");
                    textViewTotalAmountResult.setText("₹ 0");
                    totalAmountWord.setText("");
                }

            }


        });


//BackButton
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

              finish();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_gst, menu);
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int[] sourceCoordinates = new int[2];
            v.getLocationOnScreen(sourceCoordinates);
            float x = ev.getRawX() + v.getLeft() - sourceCoordinates[0];
            float y = ev.getRawY() + v.getTop() - sourceCoordinates[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom()) {
                hideKeyboard(this);
            }

        }
        return super.dispatchTouchEvent(ev);
    }

    private void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null) {
            activity.getWindow().getDecorView();
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.ShareButton) {


            if (!editTextInitialValue.getText().toString().isEmpty()) {
                if (radioExcluding.isChecked()) {
                    if (radioIgst.isChecked()) {
                        if (chip3Percent.isChecked()) {

                            igstChipShare(3);

                        } else if (chip5Percent.isChecked()) {

                            igstChipShare(5);

                        } else if (chip12Percent.isChecked()) {

                            igstChipShare(12);

                        } else if (chip18Percent.isChecked()) {

                            igstChipShare(18);

                        } else if (chip28Percent.isChecked()) {

                            igstChipShare(28);
                        }


                    } else if (radioCgstSgst.isChecked()) {
                        if (chip3Percent.isChecked()) {


                            cgstSgstChipShare(3);
                            
                        } else if (chip5Percent.isChecked()) {

                            cgstSgstChipShare(5);

                        } else if (chip12Percent.isChecked()) {

                            cgstSgstChipShare(12);
                            
                        } else if (chip18Percent.isChecked()) {

                            cgstSgstChipShare(18);


                        } else if (chip28Percent.isChecked()) {
                            cgstSgstChipShare(28);
                        }
                    }
                } else if (radioIncluding.isChecked()) {
                    if (radioCgstSgst.isChecked()) {
                        if (chip3Percent.isChecked()) {

                            cgstSgstChipShare(3);

                        } else if (chip5Percent.isChecked()) {

                            cgstSgstChipShare(5);

                        } else if (chip12Percent.isChecked()) {

                            cgstSgstChipShare(12);

                        } else if (chip18Percent.isChecked()) {

                            cgstSgstChipShare(18);

                        } else if (chip28Percent.isChecked()) {


                            cgstSgstChipShare(28);

                        }


                    } else if (radioIgst.isChecked()) {
                        if (chip3Percent.isChecked()) {


                            igstChipShare(3);

                        } else if (chip5Percent.isChecked()) {

                            igstChipShare(5);

                        } else if (chip12Percent.isChecked()) {

                            igstChipShare(12);

                        } else if (chip18Percent.isChecked()) {


                            igstChipShare(18);

                        } else if (chip28Percent.isChecked()) {

                            igstChipShare(28);

                        }
                    }
                }

            } else {

                Toast.makeText(getApplicationContext(), "Zero amount can't be shared.", Toast.LENGTH_SHORT).show();

            }

        } else if (id == R.id.ResetButtonGst){

            textViewclear.performClick();
            Toast.makeText(this,"Data has been Reset", Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressLint("SetTextI18n")
    private void excludingIgstChip(int percentage){

        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
        netAmount = initialAmount;
        roundNetAmount = String.format(Locale.getDefault(), "%.2f", netAmount);
        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
        IgstAmount = (initialAmount / 100 * percentage);
        roundIgstAmount = String.format(Locale.getDefault(),"%.2f",IgstAmount);
        textViewIgst.setText(String.format(Locale.getDefault(),"IGST Amount (%d%%)", percentage));
        textViewIgstResult.setText("₹ " + roundIgstAmount);
        totalAmount = initialAmount + IgstAmount;
        roundTotalAmount = String.format(Locale.getDefault(),"%.2f",totalAmount);
        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
        String doubleToStringFormat = String.format(Locale.getDefault(),"%.2f",stringToDouble);
        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
        stringToLong = Long.parseLong(trimString);
        realIntegerPart = stringToLong/100;
        realDecimalPart = stringToLong%100;
        StringBuilder wordFormat = new StringBuilder();
        if (realIntegerPart == 0 && realDecimalPart == 0) {
            wordFormat.append("Zero Rupees Only.");
        } else if(realIntegerPart == 0) {
            LongToStringDecPart = String.valueOf(realDecimalPart);
            DecimalToWord = Words.convert(LongToStringDecPart);
            wordFormat.append(DecimalToWord).append(" Paise Only.");
        } else if(realDecimalPart == 0) {
            LongToStringIntPart = String.valueOf(realIntegerPart);
            IntegerToWord = Words.convert(LongToStringIntPart);
            wordFormat.append(IntegerToWord).append(" Rupees Only.");
        } else {
            LongToStringIntPart = String.valueOf(realIntegerPart);
            LongToStringDecPart = String.valueOf(realDecimalPart);
            IntegerToWord = Words.convert(LongToStringIntPart);
            DecimalToWord = Words.convert(LongToStringDecPart);
            wordFormat.append(IntegerToWord).append(" Rupees And ").append(DecimalToWord).append(" Paise Only. ");
        }

        totalAmountWord.setText(wordFormat);

    }

    @SuppressLint("SetTextI18n")
    private void excludingCgstSgstChip(int percentage){

        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
        netAmount = initialAmount;
        roundNetAmount = String.format(Locale.getDefault(),"%.2f",netAmount);
        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
        CgstAmount = ((initialAmount / 100 * percentage)/2);
        roundCgstAmount = String.format(Locale.getDefault(),"%.2f",CgstAmount);
        SgstAmount = ((initialAmount / 100 * percentage)/2);
        roundSgstAmount = String.format(Locale.getDefault(),"%.2f",SgstAmount);
        textViewCgst.setText(String.format(Locale.getDefault(),"CGST Amount (%.1f%%)", percentage / 2.0));
        textViewSgst.setText(String.format(Locale.getDefault(),"SGST Amount (%.1f%%)", percentage / 2.0));
        textViewCgstResult.setText("₹ " + roundCgstAmount);
        textViewSgstResult.setText("₹ " + roundSgstAmount);
        totalAmount = initialAmount + CgstAmount + SgstAmount;
        roundTotalAmount = String.format(Locale.getDefault(),"%.2f",totalAmount);
        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
        String doubleToStringFormat = String.format(Locale.getDefault(),"%.2f",stringToDouble);
        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
        stringToLong = Long.parseLong(trimString);
        realIntegerPart = stringToLong/100;
        realDecimalPart = stringToLong%100;
        StringBuilder wordFormat = new StringBuilder();
        if (realIntegerPart == 0 && realDecimalPart == 0) {
            wordFormat.append("Zero Rupees Only.");
        } else if(realIntegerPart == 0) {
            LongToStringDecPart = String.valueOf(realDecimalPart);
            DecimalToWord = Words.convert(LongToStringDecPart);
            wordFormat.append(DecimalToWord).append(" Paise Only.");
        } else if(realDecimalPart == 0) {
            LongToStringIntPart = String.valueOf(realIntegerPart);
            IntegerToWord = Words.convert(LongToStringIntPart);
            wordFormat.append(IntegerToWord).append(" Rupees Only.");
        } else {
            LongToStringIntPart = String.valueOf(realIntegerPart);
            LongToStringDecPart = String.valueOf(realDecimalPart);
            IntegerToWord = Words.convert(LongToStringIntPart);
            DecimalToWord = Words.convert(LongToStringDecPart);
            wordFormat.append(IntegerToWord).append(" Rupees And ").append(DecimalToWord).append(" Paise Only. ");
        }

        totalAmountWord.setText(wordFormat);

    }

    @SuppressLint("SetTextI18n")
    private void includingCgstSgstChip(int percentage){

        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
        netAmount = (initialAmount/(1 + (percentage/100.0)));
        roundNetAmount = String.format(Locale.getDefault(),"%.2f",netAmount);
        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
        CgstAmount = ((netAmount / 100 * percentage)/2);
        roundCgstAmount = String.format(Locale.getDefault(),"%.2f",CgstAmount);
        SgstAmount = ((netAmount / 100 * percentage)/2);
        roundSgstAmount = String.format(Locale.getDefault(),"%.2f",SgstAmount);
        textViewCgst.setText(String.format(Locale.getDefault(),"CGST Amount (%.1f%%)", percentage / 2.0));
        textViewSgst.setText(String.format(Locale.getDefault(),"SGST Amount (%.1f%%)", percentage / 2.0));
        textViewCgstResult.setText("₹ " + roundCgstAmount);
        textViewSgstResult.setText("₹ " + roundSgstAmount);
        totalAmount = initialAmount;
        roundTotalAmount = String.format(Locale.getDefault(),"%.2f",totalAmount);
        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
        String doubleToStringFormat = String.format(Locale.getDefault(),"%.2f",stringToDouble);
        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
        stringToLong = Long.parseLong(trimString);
        realIntegerPart = stringToLong/100;
        realDecimalPart = stringToLong%100;
        StringBuilder wordFormat = new StringBuilder();
        if (realIntegerPart == 0 && realDecimalPart == 0) {
            wordFormat.append("Zero Rupees Only.");
        } else if(realIntegerPart == 0) {
            LongToStringDecPart = String.valueOf(realDecimalPart);
            DecimalToWord = Words.convert(LongToStringDecPart);
            wordFormat.append(DecimalToWord).append(" Paise Only.");
        } else if(realDecimalPart == 0) {
            LongToStringIntPart = String.valueOf(realIntegerPart);
            IntegerToWord = Words.convert(LongToStringIntPart);
            wordFormat.append(IntegerToWord).append(" Rupees Only.");
        } else {
            LongToStringIntPart = String.valueOf(realIntegerPart);
            LongToStringDecPart = String.valueOf(realDecimalPart);
            IntegerToWord = Words.convert(LongToStringIntPart);
            DecimalToWord = Words.convert(LongToStringDecPart);
            wordFormat.append(IntegerToWord).append(" Rupees And ").append(DecimalToWord).append(" Paise Only. ");
        }

        totalAmountWord.setText(wordFormat);

    }

    @SuppressLint("SetTextI18n")
    private void includingIgstChip(int percentage){

        initialAmount = Double.parseDouble("0" + editTextInitialValue.getText().toString());
        netAmount = (initialAmount/(1 + (percentage/100.0)));
        roundNetAmount = String.format(Locale.getDefault(),"%.2f",netAmount);
        textViewForResultNetAmount.setText("₹ " + roundNetAmount);
        IgstAmount = (netAmount / 100 * percentage);
        roundIgstAmount = String.format(Locale.getDefault(),"%.2f",IgstAmount);
        textViewIgst.setText(String.format(Locale.getDefault(),"IGST Amount (%d%%)", percentage));
        textViewIgstResult.setText("₹ " + roundIgstAmount);
        totalAmount = initialAmount;
        roundTotalAmount = String.format(Locale.getDefault(),"%.2f",totalAmount);
        textViewTotalAmountResult.setText("₹ " + roundTotalAmount);
        stringToDouble = Double.parseDouble(roundTotalAmount) * 100;
        String doubleToStringFormat = String.format(Locale.getDefault(),"%.2f",stringToDouble);
        trimString = doubleToStringFormat.substring(0,doubleToStringFormat.length()-3);
        stringToLong = Long.parseLong(trimString);
        realIntegerPart = stringToLong/100;
        realDecimalPart = stringToLong%100;
        StringBuilder wordFormat = new StringBuilder();
        if (realIntegerPart == 0 && realDecimalPart == 0) {
            wordFormat.append("Zero Rupees Only.");
        } else if(realIntegerPart == 0) {
            LongToStringDecPart = String.valueOf(realDecimalPart);
            DecimalToWord = Words.convert(LongToStringDecPart);
            wordFormat.append(DecimalToWord).append(" Paise Only.");
        } else if(realDecimalPart == 0) {
            LongToStringIntPart = String.valueOf(realIntegerPart);
            IntegerToWord = Words.convert(LongToStringIntPart);
            wordFormat.append(IntegerToWord).append(" Rupees Only.");
        } else {
            LongToStringIntPart = String.valueOf(realIntegerPart);
            LongToStringDecPart = String.valueOf(realDecimalPart);
            IntegerToWord = Words.convert(LongToStringIntPart);
            DecimalToWord = Words.convert(LongToStringDecPart);
            wordFormat.append(IntegerToWord).append(" Rupees And ").append(DecimalToWord).append(" Paise Only. ");
        }

        totalAmountWord.setText(wordFormat);


    }

    private void igstChipShare(int percentage){


        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
        DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
        StringBuilder forShare = new StringBuilder();

        forShare.append("Date :  ");
        forShare.append(dateFormat.format(date));
        forShare.append('\n');
        forShare.append("Time :  ");
        forShare.append(timeFormat.format(date));
        forShare.append('\n');
        forShare.append('\n');

        StringBuilder wordFormat = new StringBuilder();
        if (realIntegerPart == 0 && realDecimalPart == 0) {
            wordFormat.append("Zero Rupees Only.");
        } else if(realIntegerPart == 0) {
            LongToStringDecPart = String.valueOf(realDecimalPart);
            DecimalToWord = Words.convert(LongToStringDecPart);
            wordFormat.append(DecimalToWord).append(" Paise Only.");
        } else if(realDecimalPart == 0) {
            LongToStringIntPart = String.valueOf(realIntegerPart);
            IntegerToWord = Words.convert(LongToStringIntPart);
            wordFormat.append(IntegerToWord).append(" Rupees Only.");
        } else {
            LongToStringIntPart = String.valueOf(realIntegerPart);
            LongToStringDecPart = String.valueOf(realDecimalPart);
            IntegerToWord = Words.convert(LongToStringIntPart);
            DecimalToWord = Words.convert(LongToStringDecPart);
            wordFormat.append(IntegerToWord).append(" Rupees And ").append(DecimalToWord).append(" Paise Only. ");
        }

        forShare.append("• Net Amount = ");
        forShare.append(textViewForResultNetAmount.getText().toString());
        forShare.append('\n');
        forShare.append(String.format(Locale.getDefault(), "• IGST %d%% = ", percentage));
        forShare.append(textViewIgstResult.getText().toString());
        forShare.append('\n');
        forShare.append("• Total Amount = ");
        forShare.append(textViewTotalAmountResult.getText().toString());
        forShare.append('\n');
        forShare.append("• Total Amount In Words = ");
        forShare.append(wordFormat);
        forShare.append('\n');
        forShare.append('\n');
        forShare.append("• Shared via Total Paisa App : Cash counter and  Gst Calculator");
        forShare.append('\n');
        forShare.append("• Download from Play Store: https://play.google.com/store/apps/details?id=com.vibhunorby.totalpaisa");

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, (Serializable) forShare);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);

    }

    private void cgstSgstChipShare(int percentage){

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
        DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
        StringBuilder forShare = new StringBuilder();

        forShare.append("Date :  ");
        forShare.append(dateFormat.format(date));
        forShare.append('\n');
        forShare.append("Time :  ");
        forShare.append(timeFormat.format(date));
        forShare.append('\n');
        forShare.append('\n');


        StringBuilder wordFormat = new StringBuilder();
        if (realIntegerPart == 0 && realDecimalPart == 0) {
            wordFormat.append("Zero Rupees Only.");
        } else if(realIntegerPart == 0) {
            LongToStringDecPart = String.valueOf(realDecimalPart);
            DecimalToWord = Words.convert(LongToStringDecPart);
            wordFormat.append(DecimalToWord).append(" Paise Only.");
        } else if(realDecimalPart == 0) {
            LongToStringIntPart = String.valueOf(realIntegerPart);
            IntegerToWord = Words.convert(LongToStringIntPart);
            wordFormat.append(IntegerToWord).append(" Rupees Only.");
        } else {
            LongToStringIntPart = String.valueOf(realIntegerPart);
            LongToStringDecPart = String.valueOf(realDecimalPart);
            IntegerToWord = Words.convert(LongToStringIntPart);
            DecimalToWord = Words.convert(LongToStringDecPart);
            wordFormat.append(IntegerToWord).append(" Rupees And ").append(DecimalToWord).append(" Paise Only. ");
        }

        forShare.append("• Net Amount = ");
        forShare.append(textViewForResultNetAmount.getText().toString());
        forShare.append('\n');
        forShare.append(String.format(Locale.getDefault(), "• CGST %.1f%% = ", percentage / 2.0));
        forShare.append(textViewCgstResult.getText().toString());
        forShare.append('\n');
        forShare.append(String.format(Locale.getDefault(), "• SGST %.1f%% = ", percentage / 2.0));
        forShare.append(textViewSgstResult.getText().toString());
        forShare.append('\n');
        forShare.append("• Total Amount = ");
        forShare.append(textViewTotalAmountResult.getText().toString());
        forShare.append('\n');
        forShare.append("• Total Amount In Words = ");
        forShare.append(wordFormat);
        forShare.append('\n');
        forShare.append('\n');
        forShare.append("• Shared via Total Paisa App : Cash counter and  Gst Calculator");
        forShare.append('\n');
        forShare.append("•  Download from Play Store: https://play.google.com/store/apps/details?id=com.vibhunorby.totalpaisa");


        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, (Serializable) forShare);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);

    }

}