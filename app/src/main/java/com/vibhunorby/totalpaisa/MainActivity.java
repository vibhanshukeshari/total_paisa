package com.vibhunorby.totalpaisa;

import androidx.annotation.Nullable;
import androidx.biometric.BiometricPrompt;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.hardware.biometrics.BiometricManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.OnSuccessListener;
import java.io.Serializable;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executor;
import five.star.me.FiveStarMe;
import static android.hardware.fingerprint.FingerprintManager.FINGERPRINT_ERROR_CANCELED;
import static android.hardware.fingerprint.FingerprintManager.FINGERPRINT_ERROR_HW_NOT_PRESENT;
import static android.hardware.fingerprint.FingerprintManager.FINGERPRINT_ERROR_HW_UNAVAILABLE;
import static android.hardware.fingerprint.FingerprintManager.FINGERPRINT_ERROR_LOCKOUT;
import static android.hardware.fingerprint.FingerprintManager.FINGERPRINT_ERROR_LOCKOUT_PERMANENT;
import static android.hardware.fingerprint.FingerprintManager.FINGERPRINT_ERROR_NO_FINGERPRINTS;
import static android.hardware.fingerprint.FingerprintManager.FINGERPRINT_ERROR_USER_CANCELED;
import static com.vibhunorby.totalpaisa.CalculationFragment.editTextNumber10;
import static com.vibhunorby.totalpaisa.CalculationFragment.editTextNumber100;
import static com.vibhunorby.totalpaisa.CalculationFragment.editTextNumber10Coin;
import static com.vibhunorby.totalpaisa.CalculationFragment.editTextNumber1Coin;
import static com.vibhunorby.totalpaisa.CalculationFragment.editTextNumber20;
import static com.vibhunorby.totalpaisa.CalculationFragment.editTextNumber200;
import static com.vibhunorby.totalpaisa.CalculationFragment.editTextNumber2000;
import static com.vibhunorby.totalpaisa.CalculationFragment.editTextNumber20Coin;
import static com.vibhunorby.totalpaisa.CalculationFragment.editTextNumber2Coin;
import static com.vibhunorby.totalpaisa.CalculationFragment.editTextNumber5;
import static com.vibhunorby.totalpaisa.CalculationFragment.editTextNumber50;
import static com.vibhunorby.totalpaisa.CalculationFragment.editTextNumber500;
import static com.vibhunorby.totalpaisa.CalculationFragment.editTextNumber5Coin;
import static com.vibhunorby.totalpaisa.CalculationFragment.editTextNumberCoinExtra;
import static com.vibhunorby.totalpaisa.CalculationFragment.textViewResCoinExtra;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener/*,BillingProcessor.IBillingHandler*/ {


    private Button fakeButton;
    public static boolean adRemoved;
    LinearLayout adscontainermain;
//    private TransactionDetails purchaseTransactionDetails = null;
    public static boolean share_was_pressed = false;
    private CountDownTimer countDownTimer;
    private static final int RC_APP_UPDATE = 100;
    private AppUpdateManager mAppUpdateManager;
    static boolean toggleStatusFingerPrint;
    static boolean toggleStatusTellerSound;
    public static boolean refresh_first_time_only = true;
    public static boolean save_button_for_refreshing_sqlite = false;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Snackbar snackbar;
    private boolean save_button_was_pressed = true;
    private TextView textViewnotes;
    private TextView textViewcoins;
    private TextView textViewday;
    private TextView textViewtime;
    private TextView textViewdate;
    private EditText editTextPayeeName;
    private Button saveButtonDetails;
    private Button shareButtonDetails;
    private Button copyButtonDetails;
    private Button resetButtonDetails;
    TextToSpeech tts;
    private Button speakButton;
    public LinearLayout detailsFooterButtons;
    public TextView textviewWordsCalculationFragment;
    public Thread thread;
    public AppBarLayout appBarLayout;
    TextView textViewRupeesSymbol;
    static boolean save_button_pressed;
    static RelativeLayout footer_main;
    private LinearLayout footer_layout;
    private DBHandler dbHandler;
    private Button saveButton;
    public LinearLayout underLine;
    public LinearLayout textViewResultAmountLayout;
    static TextView textViewResult;
    TabLayout tabLayout;
    ViewPager viewPager;
    private Toolbar toolbar;
    static BottomNavigationView bottomNavigationView;
    private CoordinatorLayout parentLayout;
    static LinearLayout bottomNavigationLayout;
    private AdView adView;
    static boolean keyboard_up;
    SwitchCompat switchCompatFingerPrint;
    SwitchCompat switchCompatTellerSound;
    Menu menu;
    public LinearLayout textViewResultTopBar;

    private AlertDialog.Builder alertDialoBuider;
    private AlertDialog alertDialog;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    BillingProcessor bp;
//    BillingClient bc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

//        bp = new BillingProcessor(this, getResources().getString(R.string.play_console_license), this);
//        bp.initialize();

//        bc = BillingClient.newBuilder(this)
//    .enablePendingPurchases().setListener(new PurchasesUpdatedListener() {
//                    @Override
//                    public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> list) {
//                        if(billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && list != null){
//                            for(Purchase purchase: list){
//                                if(purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED &&
//                                !purchase.isAcknowledged()){
//
//                                }
//                            }
//                        }
//
//                    }
//                }).build();


//        connectToGooglePlayBilling();


        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_view);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();

        menu = navigationView.getMenu();

        switchCompatFingerPrint = MenuItemCompat.getActionView(menu.findItem(R.id.finger_print)).findViewById(R.id.drawer_switch);

        switchCompatTellerSound = MenuItemCompat.getActionView(menu.findItem(R.id.sound)).findViewById(R.id.drawer_switch);


        mAppUpdateManager = AppUpdateManagerFactory.create(this);
        mAppUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo result) {

                if (result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && result.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {

                    try {
                        mAppUpdateManager.startUpdateFlowForResult(result, AppUpdateType.FLEXIBLE, MainActivity.this,
                                RC_APP_UPDATE);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        mAppUpdateManager.registerListener(installStateUpdatedListener);


//     I was noticed this after 5 months and then searched about this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.parseColor("#15202b"));
        }

        fakeButton = findViewById(R.id.fake_button);
        saveButtonDetails = findViewById(R.id.save_button_details);
        shareButtonDetails = findViewById(R.id.share_button_details);
        resetButtonDetails = findViewById(R.id.reset_button_details);
        speakButton = findViewById(R.id.speak_button_details);
        textviewWordsCalculationFragment = findViewById(R.id.textViewWordsCalculationFragment);
        appBarLayout = findViewById(R.id.appBar_layout);
        textViewRupeesSymbol = findViewById(R.id.textViewRupeesSymbol);
        footer_main = findViewById(R.id.footerMain);
        footer_layout = findViewById(R.id.footer_layout);
        saveButton = findViewById(R.id.save_button);
        underLine = findViewById(R.id.underLine);
        textViewResultTopBar = findViewById(R.id.textViewResultTopBar);

        copyButtonDetails = findViewById(R.id.copy_button_details);
        detailsFooterButtons = findViewById(R.id.detailsFooterButton);
        textViewResultAmountLayout = findViewById(R.id.textViewResultAmountLayout);
        bottomNavigationLayout = findViewById(R.id.botton_navigation_layout);
        bottomNavigationView = findViewById(R.id.botton_navigation);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        parentLayout = findViewById(R.id.parent);
        textViewResult = findViewById(R.id.textViewResult);

        getTabs();


//        For Admob Below
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });


      SharedPreferences  getShared = getSharedPreferences("adRemoved", MODE_PRIVATE);
        adRemoved = getShared.getBoolean("boolean", false);


        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();

        adscontainermain = findViewById(R.id.adsContainer_main);

        if(adRemoved){
            try{
                adscontainermain.removeView(adView);
            }catch (NullPointerException ignored){}

            try {
                toolbar.setTitle("Total Paisa Pro");
            }catch(NullPointerException ignored){}

        }else {
            adView.loadAd(adRequest);
        }

        dbHandler = new DBHandler(MainActivity.this);

// In  app ratings api , I coudn't use google api because it has a cons it asks at the day first but i wanted at day three of install of the app.
        FiveStarMe.with(this)
                .setInstallDays(3)
                .setLaunchTimes(3)
                .setDebug(false)
                .monitor();
        FiveStarMe.showRateDialogIfMeetsConditions(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String currency2000 = CalculationFragment.textViewRes2000.getText().toString();
                final String currency500 = CalculationFragment.textViewRes500.getText().toString();
                final String currency200 = CalculationFragment.textViewRes200.getText().toString();
                final String currency100 = CalculationFragment.textViewRes100.getText().toString();
                final String currency50 = CalculationFragment.textViewRes50.getText().toString();
                final String currency20 = CalculationFragment.textViewRes20.getText().toString();
                final String currency10 = CalculationFragment.textViewRes10.getText().toString();
                final String currency5 = CalculationFragment.textViewRes5.getText().toString();
                final String currency20_coin = CalculationFragment.textViewRes20Coin.getText().toString();
                final String currency10_coin = CalculationFragment.textViewRes10Coin.getText().toString();
                final String currency5_coin = CalculationFragment.textViewRes5Coin.getText().toString();
                final String currency2_coin = CalculationFragment.textViewRes2Coin.getText().toString();
                final String currency1_coin = CalculationFragment.textViewRes1Coin.getText().toString();
                final String currency_extra_coin = CalculationFragment.textViewResCoinExtra.getText().toString();
                final String result = textViewResult.getText().toString();

                textViewnotes = findViewById(R.id.textViewTotalValueOfNotes);
                textViewcoins = findViewById(R.id.textViewTotalValueOfCoins);
                textViewday = findViewById(R.id.textViewDayOfTheWeek);
                textViewtime = findViewById(R.id.textViewTime);
                textViewdate = findViewById(R.id.textViewCalender);
                editTextPayeeName = findViewById(R.id.editTextPayeeName);

                TextView textViewTellerBalance = findViewById(R.id.tallyAmount);
                TextView textViewNumberOfNotes = findViewById(R.id.textViewTotalNumberOfNotes);
                TextView textViewNumberOfCoins = findViewById(R.id.textViewTotalNumberOfCoins);

                // validating if the text fields are empty or not.
                if (Integer.parseInt(result.replaceAll(",", "")) == 0) {
                    Toast toast = Toast.makeText(MainActivity.this, "Zero amount can't be saved.", Toast.LENGTH_SHORT);
                    View view1 = toast.getView();

                    try {

                        TextView textView = toast.getView().findViewById(android.R.id.message);
                        textView.setTextColor(Color.parseColor("#ffffff"));

                    } catch (NullPointerException ignored) {
                    }


                    try {
                        view1.getBackground().setColorFilter(Color.parseColor("#10171f"), PorterDuff.Mode.SRC_IN);
                    } catch (NullPointerException ignored) {
                    }
                    toast.show();

                    return;
                }
                save_button_pressed = true;
                save_button_for_refreshing_sqlite = true;

                String tellerBalanceForCompare = textViewTellerBalance.getText().toString();
                String payeenameForCompare = editTextPayeeName.getText().toString();
                String resultForCompare = textViewResult.getText().toString();
                String dateForCompare = textViewdate.getText().toString();
                String textViewNumberOfnotes = textViewNumberOfNotes.getText().toString();
                String textViewNumberOfcoins = textViewNumberOfCoins.getText().toString();
                String extraCoins = textViewResCoinExtra.getText().toString();

                if (save_button_was_pressed) {
                    SharedPreferences.Editor editor = getSharedPreferences("verification", MODE_PRIVATE).edit();
                    editor.putString("tellerBalance", tellerBalanceForCompare);
                    editor.putString("payeeName", payeenameForCompare);
                    editor.putString("result", resultForCompare);
                    editor.putString("date", dateForCompare);
                    editor.putString("notes", textViewNumberOfnotes);
                    editor.putString("coins", textViewNumberOfcoins);
                    editor.putString("extraCoins", extraCoins);
                    editor.apply();

                    String mypayee;

                    if (editTextPayeeName.getText().toString().trim().matches("")) {
                        mypayee = "Unknown";
                    } else {
                        mypayee = editTextPayeeName.getText().toString();
                    }

                    String mydate = textViewdate.getText().toString();
                    String mytime = new SimpleDateFormat("hh:mm:ss a", Locale.getDefault()).format(new Date());
                    String myday = textViewday.getText().toString();

                    String notes;

                    if(textViewnotes.getText().toString().equals("")){
                        notes = "₹ 0";
                    } else {
                        notes = textViewnotes.getText().toString();
                    }

                    String coins;

                    if (textViewcoins.getText().toString().equals("")) {
                        coins = "₹ 0";
                    } else {
                        coins = textViewcoins.getText().toString();
                    }

                    String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                    textViewdate.setText(date);

                    TextView textViewTodayTomorrowYesterday = findViewById(R.id.textViewTodayTomorrowYesterday);
                    textViewTodayTomorrowYesterday.setText("Today :");

                    DateFormat sourceFormat = new SimpleDateFormat("dd-MM-yyyy");
                    try {
                        Date date1 = sourceFormat.parse(date);
                        String newDay = android.text.format.DateFormat.format("EEEE", date1).toString();
                        textViewday.setText(newDay);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    // on below line we are calling a method to add new
                    // course to sqlite data and pass all our values to it.
                    dbHandler.addNewCurrency(currency2000, currency500, currency200, currency100, currency50, currency20, currency10, currency5,
                            currency20_coin, currency10_coin, currency5_coin, currency2_coin, currency1_coin, currency_extra_coin, "₹ " + result, mypayee, mydate,
                            mytime, myday, notes, coins);
                    dbHandler.close();

                    Toast toast = Toast.makeText(MainActivity.this, "Data has been saved check History.", Toast.LENGTH_SHORT);
                    View view1 = toast.getView();


                    try {

                        TextView textView = toast.getView().findViewById(android.R.id.message);
                        textView.setTextColor(Color.parseColor("#ffffff"));

                    } catch (NullPointerException ignored) {
                    }


                    try {
                        assert view1 != null;
                        view1.getBackground().setColorFilter(Color.parseColor("#10171f"), PorterDuff.Mode.SRC_IN);
                    } catch (NullPointerException ignored) {
                    }
                    toast.show();

                    try {
                        tabLayout.getTabAt(2).getOrCreateBadge().setVisible(true);
                        tabLayout.getTabAt(2).getOrCreateBadge().setBackgroundColor(Color.parseColor("#1da1f3"));
                    } catch (NullPointerException ignored) {
                    }

                    save_button_was_pressed = false;

                } else {

                    String tellerBalanceForCompareNew = textViewTellerBalance.getText().toString();
                    String payeenameForCompareNew = editTextPayeeName.getText().toString();
                    String resultForCompareNew = textViewResult.getText().toString();
                    String dateForCompareNew = textViewdate.getText().toString();
                    String textViewNumberOfnotesNew = textViewNumberOfNotes.getText().toString();
                    String textViewNumberOfcoinsNew = textViewNumberOfCoins.getText().toString();
                    String extraCoinsNew = textViewResCoinExtra.getText().toString();

                    SharedPreferences sharedPreferences = getSharedPreferences("verification", MODE_PRIVATE);
                    String tellerBalanceOld = sharedPreferences.getString("tellerBalance", "No name defined");
                    String payeenameOld = sharedPreferences.getString("payeeName", "No name defined");
                    String resultOld = sharedPreferences.getString("result", "No name defined");
                    String dateOld = sharedPreferences.getString("date", "No name defined");
                    String notesOld = sharedPreferences.getString("notes", "No name defined");
                    String coinsBalanceOld = sharedPreferences.getString("coins", "No name defined");
                    String extraCoinOld = sharedPreferences.getString("extraCoins", "No name defined");

                    if (tellerBalanceOld.equals(tellerBalanceForCompareNew)
                            && payeenameOld.equals(payeenameForCompareNew)
                            && resultOld.equals(resultForCompareNew)
                            && dateOld.equals(dateForCompareNew)
                            && notesOld.equals(textViewNumberOfnotesNew)
                            && coinsBalanceOld.equals(textViewNumberOfcoinsNew)
                            && extraCoinOld.equals(extraCoinsNew)) {

                        AlertDialog.Builder alertDialoBuider = new AlertDialog.Builder(MainActivity.this, R.style.alertDialog);
                        alertDialoBuider.setTitle("Save Again ?");
                        alertDialoBuider.setIcon(R.drawable.warning);
                        alertDialoBuider.setMessage("Data is already saved, Do you want to save Again?");

                        alertDialoBuider.setPositiveButton("Save Again", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String mypayee;

                                if (editTextPayeeName.getText().toString().trim().matches("")) {
                                    mypayee = "Unknown";
                                } else {
                                    mypayee = editTextPayeeName.getText().toString();
                                }

                                String mydate = textViewdate.getText().toString();
                                String mytime = new SimpleDateFormat("hh:mm:ss a", Locale.getDefault()).format(new Date());
                                String myday = textViewday.getText().toString();
                                String notes = textViewnotes.getText().toString();

                                String coins;

                                if (textViewcoins.getText().toString().equals("")) {
                                    coins = "₹ 0";
                                } else {
                                    coins = textViewcoins.getText().toString();
                                }

                                String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                                textViewdate.setText(date);

                                TextView textViewTodayTomorrowYesterday = findViewById(R.id.textViewTodayTomorrowYesterday);
                                textViewTodayTomorrowYesterday.setText("Today :");

                                DateFormat sourceFormat = new SimpleDateFormat("dd-MM-yyyy");
                                try {
                                    Date date1 = sourceFormat.parse(date);
                                    String newDay = android.text.format.DateFormat.format("EEEE", date1).toString();
                                    textViewday.setText(newDay);

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                // on below line we are calling a method to add new
                                // course to sqlite data and pass all our values to it.
                                dbHandler.addNewCurrency(currency2000, currency500, currency200, currency100, currency50, currency20, currency10, currency5,
                                        currency20_coin, currency10_coin, currency5_coin, currency2_coin, currency1_coin, currency_extra_coin, "₹ " + result, mypayee, mydate,
                                        mytime, myday, notes, coins);
                                dbHandler.close();

                                Toast toast = Toast.makeText(MainActivity.this, "Data has been saved again, check History.", Toast.LENGTH_SHORT);
                                View view1 = toast.getView();


                                try {

                                    TextView textView = toast.getView().findViewById(android.R.id.message);
                                    textView.setTextColor(Color.parseColor("#ffffff"));

                                } catch (NullPointerException ignored) {
                                }


                                try {
                                    assert view1 != null;
                                    view1.getBackground().setColorFilter(Color.parseColor("#10171f"), PorterDuff.Mode.SRC_IN);
                                } catch (NullPointerException ignored) {
                                }
                                toast.show();


                                try {
                                    tabLayout.getTabAt(2).getOrCreateBadge().setVisible(true);
                                    tabLayout.getTabAt(2).getOrCreateBadge().setBackgroundColor(Color.parseColor("#1da1f3"));

                                } catch (NullPointerException ignored) {
                                }


                            }

                        });


                        alertDialoBuider.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();

                            }


                        });
                        AlertDialog alertDialog = alertDialoBuider.create();
                        alertDialog.show();

                    } else {

                        SharedPreferences.Editor editor = getSharedPreferences("verification", MODE_PRIVATE).edit();
                        editor.putString("tellerBalance", tellerBalanceForCompare);
                        editor.putString("payeeName", payeenameForCompare);
                        editor.putString("result", resultForCompare);
                        editor.putString("date", dateForCompare);
                        editor.putString("notes", textViewNumberOfnotes);
                        editor.putString("coins", textViewNumberOfcoins);
                        editor.putString("extraCoins", extraCoins);
                        editor.apply();

                        String mypayee;

                        if (editTextPayeeName.getText().toString().trim().matches("")) {
                            mypayee = "Unknown";
                        } else {
                            mypayee = editTextPayeeName.getText().toString();
                        }

                        String mydate = textViewdate.getText().toString();
                        String mytime = new SimpleDateFormat("hh:mm:ss a", Locale.getDefault()).format(new Date());
                        String myday = textViewday.getText().toString();
                        String notes = textViewnotes.getText().toString();

                        String coins;

                        if (textViewcoins.getText().toString().equals("")) {
                            coins = "₹ 0";
                        } else {
                            coins = textViewcoins.getText().toString();
                        }

                        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                        textViewdate.setText(date);

                        TextView textViewTodayTomorrowYesterday = findViewById(R.id.textViewTodayTomorrowYesterday);
                        textViewTodayTomorrowYesterday.setText("Today :");

                        DateFormat sourceFormat = new SimpleDateFormat("dd-MM-yyyy");
                        try {
                            Date date1 = sourceFormat.parse(date);
                            String newDay = android.text.format.DateFormat.format("EEEE", date1).toString();
                            textViewday.setText(newDay);

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        // on below line we are calling a method to add new
                        // course to sqlite data and pass all our values to it.
                        dbHandler.addNewCurrency(currency2000, currency500, currency200, currency100, currency50, currency20, currency10, currency5,
                                currency20_coin, currency10_coin, currency5_coin, currency2_coin, currency1_coin, currency_extra_coin, "₹ " + result, mypayee, mydate,
                                mytime, myday, notes, coins);
                        dbHandler.close();

                        Toast toast = Toast.makeText(MainActivity.this, "Data has been saved check History.", Toast.LENGTH_SHORT);
                        View view1 = toast.getView();

                        try {

                            TextView textView = toast.getView().findViewById(android.R.id.message);
                            textView.setTextColor(Color.parseColor("#ffffff"));

                        } catch (NullPointerException ignored) {
                        }


                        try {
                            tabLayout.getTabAt(2).getOrCreateBadge().setVisible(true);
                            tabLayout.getTabAt(2).getOrCreateBadge().setBackgroundColor(Color.parseColor("#1da1f3"));
                        } catch (NullPointerException ignored) {
                        }


                        try {
                            assert view1 != null;
                            view1.getBackground().setColorFilter(Color.parseColor("#10171f"), PorterDuff.Mode.SRC_IN);
                        } catch (NullPointerException ignored) {
                        }
                        toast.show();

                    }
                }
            }
        });


        saveButtonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveButton.performClick();

            }
        });


        speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                TextView totalAmountInWords = findViewById(R.id.textViewTotalAmountInWords);


                final String toSpeak;


                if (totalAmountInWords.getText().toString().equals("")) {
                    toSpeak = "Zero Rupees Only.";
                } else {
                    toSpeak = totalAmountInWords.getText().toString();
                }


                AudioManager audio = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
                switch (audio.getRingerMode()) {
                    case AudioManager.RINGER_MODE_NORMAL:

                        Toast toast = Toast.makeText(MainActivity.this, "Playing total amount.", Toast.LENGTH_SHORT);
                        View view1 = toast.getView();

                        try {

                            TextView textView = toast.getView().findViewById(android.R.id.message);
                            textView.setTextColor(Color.parseColor("#ffffff"));

                        } catch (NullPointerException ignored) {
                        }

                        try {
                            assert view1 != null;
                            view1.getBackground().setColorFilter(Color.parseColor("#10171f"), PorterDuff.Mode.SRC_IN);
                        } catch (NullPointerException ignored) {
                        }
                        toast.show();


                        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int i) {
                                try {
                                    tts.setLanguage(new Locale("hi"));
                                } catch (NullPointerException ignored) {
                                }


                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    try {

                                        tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, null);
                                    } catch (NullPointerException ignored) {
                                    }
                                } else {


                                    try {
                                        tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                                    } catch (NullPointerException ignored) {
                                    }

                                }
                            }
                        });

                        break;

                    case AudioManager.RINGER_MODE_SILENT:

                        toast = Toast.makeText(MainActivity.this, "Can't play in silent mode.", Toast.LENGTH_SHORT);
                        view1 = toast.getView();

                        try {

                            TextView textView = toast.getView().findViewById(android.R.id.message);
                            textView.setTextColor(Color.parseColor("#ffffff"));

                        } catch (NullPointerException ignored) {
                        }


                        try {
                            assert view1 != null;
                            view1.getBackground().setColorFilter(Color.parseColor("#10171f"), PorterDuff.Mode.SRC_IN);
                        } catch (NullPointerException ignored) {
                        }
                        toast.show();


                        break;

                    case AudioManager.RINGER_MODE_VIBRATE:

                        toast = Toast.makeText(MainActivity.this, "Can't play in Vibration mode.", Toast.LENGTH_SHORT);
                        view1 = toast.getView();

                        try {

                            TextView textView = toast.getView().findViewById(android.R.id.message);
                            textView.setTextColor(Color.parseColor("#ffffff"));

                        } catch (NullPointerException ignored) {
                        }


                        try {
                            assert view1 != null;
                            view1.getBackground().setColorFilter(Color.parseColor("#10171f"), PorterDuff.Mode.SRC_IN);
                        } catch (NullPointerException ignored) {
                        }
                        toast.show();

                        break;


                }
            }
        });


        shareButtonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!textViewResult.getText().toString().replaceAll(",", "").equals("0")) {

                    share_was_pressed = true;

                    String myTextViewWord = DetailsFragment.TotalAmountInWords.getText().toString();
                    String myTextView2000 = CalculationFragment.textViewRes2000.getText().toString().replaceAll(",", "");
                    String myTextView500 = CalculationFragment.textViewRes500.getText().toString().replaceAll(",", "");
                    String myTextView200 = CalculationFragment.textViewRes200.getText().toString().replaceAll(",", "");
                    String myTextView100 = CalculationFragment.textViewRes100.getText().toString().replaceAll(",", "");
                    String myTextView50 = CalculationFragment.textViewRes50.getText().toString().replaceAll(",", "");
                    String myTextView20 = CalculationFragment.textViewRes20.getText().toString().replaceAll(",", "");
                    String myTextView10 = CalculationFragment.textViewRes10.getText().toString().replaceAll(",", "");
                    String myTextView5 = CalculationFragment.textViewRes5.getText().toString().replaceAll(",", "");
                    String myTextViewCoin20 = CalculationFragment.textViewRes20Coin.getText().toString().replaceAll(",","");
                    String myTextViewCoin10 = CalculationFragment.textViewRes10Coin.getText().toString().replaceAll(",", "");
                    String myTextViewCoin5 = CalculationFragment.textViewRes5Coin.getText().toString().replaceAll(",", "");
                    String myTextViewCoin2 = CalculationFragment.textViewRes2Coin.getText().toString().replaceAll(",", "");
                    String myTextViewCoin1 = CalculationFragment.textViewRes1Coin.getText().toString().replaceAll(",", "");
                    String myTextViewCoins = CalculationFragment.textViewResCoinExtra.getText().toString().replaceAll(",", "");
                    String myTextViewInNumbers = textViewResult.getText().toString().replaceAll(",", "");

                    String myEditText2000 = CalculationFragment.editTextNumber2000.getText().toString();
                    String myEditText500 = CalculationFragment.editTextNumber500.getText().toString();
                    String myEditText200 = CalculationFragment.editTextNumber200.getText().toString();
                    String myEditText100 = CalculationFragment.editTextNumber100.getText().toString();
                    String myEditText50 = CalculationFragment.editTextNumber50.getText().toString();
                    String myEditText20 = editTextNumber20.getText().toString();
                    String myEditText10 = editTextNumber10.getText().toString();
                    String myEditText5 = CalculationFragment.editTextNumber5.getText().toString();
                    String myEditTextCoin20 = editTextNumber20Coin.getText().toString();
                    String myEditTextCoin10 = CalculationFragment.editTextNumber10Coin.getText().toString();
                    String myEditTextCoin5 = CalculationFragment.editTextNumber5Coin.getText().toString();
                    String myEditTextCoin2 = CalculationFragment.editTextNumber2Coin.getText().toString();
                    String myEditTextCoin1 = CalculationFragment.editTextNumber1Coin.getText().toString();
                    String myEditTextExtraCoin = CalculationFragment.editTextNumberCoinExtra.getText().toString();

                    int my2000 = Integer.parseInt(CalculationFragment.textViewRes2000.getText().toString().replaceAll(",", ""));
                    int my500 = Integer.parseInt(CalculationFragment.textViewRes500.getText().toString().replaceAll(",", ""));
                    int my200 = Integer.parseInt(CalculationFragment.textViewRes200.getText().toString().replaceAll(",", ""));
                    int my100 = Integer.parseInt(CalculationFragment.textViewRes100.getText().toString().replaceAll(",", ""));
                    int my50 = Integer.parseInt(CalculationFragment.textViewRes50.getText().toString().replaceAll(",", ""));
                    int my20 = Integer.parseInt(CalculationFragment.textViewRes20.getText().toString().replaceAll(",", ""));
                    int my10 = Integer.parseInt(CalculationFragment.textViewRes10.getText().toString().replaceAll(",", ""));
                    int my5 = Integer.parseInt(CalculationFragment.textViewRes5.getText().toString().replaceAll(",", ""));

                    int totalNotes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5;
                    String totalNotesInString = String.valueOf(totalNotes);

                    int my20Coin = Integer.parseInt(CalculationFragment.textViewRes20Coin.getText().toString().replaceAll(",",""));
                    int my10Coin = Integer.parseInt(CalculationFragment.textViewRes10Coin.getText().toString().replaceAll(",", ""));
                    int my5Coin = Integer.parseInt(CalculationFragment.textViewRes5Coin.getText().toString().replaceAll(",", ""));
                    int my2Coin = Integer.parseInt(CalculationFragment.textViewRes2Coin.getText().toString().replaceAll(",", ""));
                    int my1Coin = Integer.parseInt(CalculationFragment.textViewRes1Coin.getText().toString().replaceAll(",", ""));
                    int myCoinExtra = Integer.parseInt(CalculationFragment.textViewResCoinExtra.getText().toString().replaceAll(",", ""));

                    int totalCoins = my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;
                    String totalCoinsInString = String.valueOf(totalCoins);


                    TextView textViewDate = findViewById(R.id.textViewCalender);
                    TextView textViewTime = findViewById(R.id.textViewTime);
                    TextView textViewDay = findViewById(R.id.textViewDayOfTheWeek);
                    EditText payeeName = findViewById(R.id.editTextPayeeName);


                    StringBuilder forShare = new StringBuilder();

                    forShare.append("Date :  ");
                    forShare.append(textViewDate.getText().toString());
                    forShare.append('\n');
                    forShare.append("Day :  ");
                    forShare.append(textViewDay.getText().toString());
                    forShare.append('\n');
                    forShare.append(textViewTime.getText().toString());
                    forShare.append('\n');


                    if (payeeName.getText().toString().trim().matches("")) {
                        forShare.append("Payee Name :  Unknown ");
                    } else {

                        forShare.append("Payee Name :  ");
                        forShare.append(payeeName.getText().toString());

                    }

                    forShare.append('\n');
                    forShare.append('\n');


                    forShare.append("◕  Notes :  ");
                    forShare.append('\n');

                    if (myEditText2000.length() != 0) {
                        forShare.append("•  2000  x  ");
                        forShare.append(myEditText2000);
                        forShare.append("  =  ₹ ");
                        forShare.append(AddComma.getIndianCurrencyFormat(myTextView2000));
                        forShare.append('\n');
                    }


                    if (myEditText500.length() != 0) {
                        forShare.append("•  500    x  ");
                        forShare.append(myEditText500);
                        forShare.append("  =  ₹ ");
                        forShare.append(AddComma.getIndianCurrencyFormat(myTextView500));
                        forShare.append('\n');
                    }


                    if (myEditText200.length() != 0) {
                        forShare.append("•  200    x  ");
                        forShare.append(myEditText200);
                        forShare.append("  =  ₹ ");
                        forShare.append(AddComma.getIndianCurrencyFormat(myTextView200));
                        forShare.append('\n');
                    }


                    if (myEditText100.length() != 0) {
                        forShare.append("•  100    x  ");
                        forShare.append(myEditText100);
                        forShare.append("  =  ₹ ");
                        forShare.append(AddComma.getIndianCurrencyFormat(myTextView100));
                        forShare.append('\n');
                    }


                    if (myEditText50.length() != 0) {
                        forShare.append("•  50      x  ");
                        forShare.append(myEditText50);
                        forShare.append("  =  ₹ ");
                        forShare.append(AddComma.getIndianCurrencyFormat(myTextView50));
                        forShare.append('\n');
                    }


                    if (myEditText20.length() != 0) {
                        forShare.append("•  20      x  ");
                        forShare.append(myEditText20);
                        forShare.append("  =  ₹ ");
                        forShare.append(AddComma.getIndianCurrencyFormat(myTextView20));
                        forShare.append('\n');
                    }


                    if (myEditText10.length() != 0) {
                        forShare.append("•  10      x  ");
                        forShare.append(myEditText10);
                        forShare.append("  =  ₹ ");
                        forShare.append(AddComma.getIndianCurrencyFormat(myTextView10));
                        forShare.append('\n');
                    }


                    if (myEditText5.length() != 0) {
                        forShare.append("•  5        x  ");
                        forShare.append(myEditText5);
                        forShare.append("  =  ₹ ");
                        forShare.append(AddComma.getIndianCurrencyFormat(myTextView5));
                        forShare.append('\n');
                    }


                    forShare.append("•  Total value of Notes  =>  ₹ ");
                    forShare.append(AddComma.getIndianCurrencyFormat(totalNotesInString));
                    forShare.append('\n');
                    forShare.append('\n');
                    forShare.append('\n');
                    forShare.append("◕  Coins :  ");
                    forShare.append('\n');


                    if (myEditTextCoin20.length() != 0) {
                        forShare.append("•  20 Coin  x  ");
                        forShare.append(myEditTextCoin20);
                        forShare.append("  =  ₹ ");
                        forShare.append(AddComma.getIndianCurrencyFormat(myTextViewCoin20));
                        forShare.append('\n');
                    }

                    if (myEditTextCoin10.length() != 0) {
                        forShare.append("•  10 Coin  x  ");
                        forShare.append(myEditTextCoin10);
                        forShare.append("  =  ₹ ");
                        forShare.append(AddComma.getIndianCurrencyFormat(myTextViewCoin10));
                        forShare.append('\n');
                    }


                    if (myEditTextCoin5.length() != 0) {
                        forShare.append("•  5 Coin    x  ");
                        forShare.append(myEditTextCoin5);
                        forShare.append("  =  ₹ ");
                        forShare.append(AddComma.getIndianCurrencyFormat(myTextViewCoin5));
                        forShare.append('\n');
                    }


                    if (myEditTextCoin2.length() != 0) {
                        forShare.append("•  2 Coin    x  ");
                        forShare.append(myEditTextCoin2);
                        forShare.append("  =  ₹ ");
                        forShare.append(AddComma.getIndianCurrencyFormat(myTextViewCoin2));
                        forShare.append('\n');
                    }


                    if (myEditTextCoin1.length() != 0) {
                        forShare.append("•  1 Coin    x  ");
                        forShare.append(myEditTextCoin1);
                        forShare.append("  =  ₹ ");
                        forShare.append(AddComma.getIndianCurrencyFormat(myTextViewCoin1));
                        forShare.append('\n');
                    }


                    if (myEditTextExtraCoin.length() != 0) {
                        forShare.append("•  Extra Coins  ");
                        forShare.append("  =  ₹ ");
                        forShare.append(AddComma.getIndianCurrencyFormat(myTextViewCoins));
                        forShare.append('\n');
                    }


                    forShare.append("•  Total value of Coins =>  ₹ ");
                    forShare.append(AddComma.getIndianCurrencyFormat(totalCoinsInString));
                    forShare.append('\n');
                    forShare.append('\n');
                    forShare.append('\n');
                    forShare.append("•  Total Amount ");
                    forShare.append("  =>  ₹ ");
                    forShare.append(AddComma.getIndianCurrencyFormat(myTextViewInNumbers));

                    forShare.append('\n');

                    forShare.append("•  Total Amount In Words ");
                    forShare.append("  =>  ");
                    forShare.append('\n');
                    forShare.append(myTextViewWord);
                    forShare.append('\n');
                    forShare.append('\n');
                    forShare.append('\n');

                    forShare.append(" Shared via Total Paisa App:");
                    forShare.append('\n');
                    forShare.append("https://play.google.com/store/apps/details?id=com.vibhunorby.totalpaisa");
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, (Serializable) forShare);
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);

                } else {
                    Toast toast = Toast.makeText(MainActivity.this, "Zero amount can't be shared.", Toast.LENGTH_SHORT);
                    View view1 = toast.getView();

                    try {

                        TextView textView = toast.getView().findViewById(android.R.id.message);
                        textView.setTextColor(Color.parseColor("#ffffff"));

                    } catch (NullPointerException ignored) {
                    }


                    try {
                        assert view1 != null;
                        view1.getBackground().setColorFilter(Color.parseColor("#10171f"), PorterDuff.Mode.SRC_IN);
                    } catch (NullPointerException ignored) {
                    }
                    toast.show();

                }

            }
        });


        resetButtonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder alertDialoBuider = new AlertDialog.Builder(MainActivity.this, R.style.alertDialog);
                alertDialoBuider.setTitle("Confirm Reset !");
                alertDialoBuider.setIcon(R.drawable.warning);
                alertDialoBuider.setMessage("Are you sure want to reset ?");

                alertDialoBuider.setPositiveButton("Reset", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                NestedScrollView nestedScrollView = findViewById(R.id.nested);
                                nestedScrollView.scrollTo(0, 0);
                                NestedScrollView nestedScrollViewDetails = findViewById(R.id.nested_details);
                                nestedScrollViewDetails.scrollTo(0, 0);
                                AppBarLayout appBarLayout = findViewById(R.id.appBar_layout);
                                appBarLayout.setExpanded(true, true);
                            }
                        }, 100);

                        CalculationFragment.editTextNumber2000.getText().clear();
                        CalculationFragment.editTextNumber500.getText().clear();
                        CalculationFragment.editTextNumber200.getText().clear();
                        CalculationFragment.editTextNumber100.getText().clear();
                        CalculationFragment.editTextNumber50.getText().clear();
                        editTextNumber20.getText().clear();
                        editTextNumber10.getText().clear();
                        CalculationFragment.editTextNumber5.getText().clear();
                        editTextNumber20Coin.getText().clear();
                        CalculationFragment.editTextNumber10Coin.getText().clear();
                        CalculationFragment.editTextNumber5Coin.getText().clear();
                        CalculationFragment.editTextNumber2Coin.getText().clear();
                        CalculationFragment.editTextNumber1Coin.getText().clear();
                        editTextNumberCoinExtra.getText().clear();

                        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                        TextView textViewCalender = findViewById(R.id.textViewCalender);
                        textViewCalender.setText(date);


                        TextView textViewTodayTomorrowYesterday = findViewById(R.id.textViewTodayTomorrowYesterday);
                        textViewTodayTomorrowYesterday.setText("Today :");


                        TextView textViewDayOfTheWeek = findViewById(R.id.textViewDayOfTheWeek);
                        DateFormat sourceFormat = new SimpleDateFormat("dd-MM-yyyy");
                        try {
                            Date date1 = sourceFormat.parse(date);
                            String newDay = android.text.format.DateFormat.format("EEEE", date1).toString();
                            textViewDayOfTheWeek.setText(newDay);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                        EditText editTextPayeeName = findViewById(R.id.editTextPayeeName);
                        editTextPayeeName.setText("");

                        EditText editTextTellerBalance = findViewById(R.id.tallyAmount);
                        editTextTellerBalance.setText("");

                    }

                });


                alertDialoBuider.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                    }
                });
                AlertDialog alertDialog = alertDialoBuider.create();
                alertDialog.show();

            }
        });


        copyButtonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!textViewResult.getText().toString().replaceAll(",", "").equals("0")) {

                    String myTextViewInNumbers = textViewResult.getText().toString().replaceAll(",", "");

                    String textViewNumbersToAddComa = AddComma.getIndianCurrencyFormat(myTextViewInNumbers);
                    ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Currency_info", "₹ " + textViewNumbersToAddComa);
                    clipboardManager.setPrimaryClip(clip);
                    Toast toast = Toast.makeText(MainActivity.this, "Amount copied to clipboard.", Toast.LENGTH_SHORT);
                    View view1 = toast.getView();

                    try {

                        TextView textView = toast.getView().findViewById(android.R.id.message);
                        textView.setTextColor(Color.parseColor("#ffffff"));

                    } catch (NullPointerException ignored) {
                    }


                    try {
                        assert view1 != null;
                        view1.getBackground().setColorFilter(Color.parseColor("#10171f"), PorterDuff.Mode.SRC_IN);
                    } catch (NullPointerException ignored) {
                    }
                    toast.show();

                } else {
                    Toast toast = Toast.makeText(MainActivity.this, "Zero amount can't be copied.", Toast.LENGTH_SHORT);
                    View view1 = toast.getView();

                    try {

                        TextView textView = toast.getView().findViewById(android.R.id.message);
                        textView.setTextColor(Color.parseColor("#ffffff"));

                    } catch (NullPointerException ignored) {
                    }


                    try {
                        assert view1 != null;
                        view1.getBackground().setColorFilter(Color.parseColor("#10171f"), PorterDuff.Mode.SRC_IN);
                    } catch (NullPointerException ignored) {
                    }
                    toast.show();

                }

            }
        });


//        This is used to close soft keyboard on click screen except edit text click.
        setupUI(parentLayout);
        setupUI(footer_layout);


        bottomNavigationView.setSelectedItemId(R.id.totalPaisa);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.gst:
                        startActivity(new Intent(getApplicationContext(), Gst.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.totalPaisa:
                        return true;
                }

                return false;
            }
        });

        RelativeLayout relativeLayout = findViewById(R.id.rootView);
        relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                relativeLayout.getWindowVisibleDisplayFrame(r);
                int screenHeight = relativeLayout.getRootView().getHeight();
                int keypadHeight = screenHeight - r.bottom;
                if (keypadHeight > screenHeight * 0.15) {
//                    keyboard is up

                    bottomNavigationLayout.setVisibility((View.GONE));

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (!keyboard_up) {

                                textViewResultAmountLayout.setVisibility(View.VISIBLE);
                                underLine.setVisibility(View.VISIBLE);
                                textViewResultTopBar.setVisibility(View.VISIBLE);
                                keyboard_up = true;
                            }
                        }
                    }, 1);


                } else {
//                    keyboard is down

                    if (keyboard_up) {
//
                        bottomNavigationLayout.setVisibility(View.VISIBLE);

                        keyboard_up = false;
//
                    }

//                  Warning do not remove any of both important;
                    keyboard_up = false;

                }
            }
        });


        thread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                             setting current time
                                TextView textViewTime = findViewById(R.id.textViewTime);
                                DateFormat timeFormat = new SimpleDateFormat("hh:mm a");
                                Date time = new Date();
                                try {
                                    textViewTime.setText("Time :  " + timeFormat.format(time));
                                } catch (NullPointerException ignored) {

                                }
                            }
                        });
                    }
                } catch (InterruptedException ignored) {
                }
            }
        };
        thread.start();


        switchCompatTellerSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {

                    toggleStatusTellerSound = true;

                } else {

                    toggleStatusTellerSound = false;

                }
            }
        });


    }

    public void getTabs() {
        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        new Handler().post(new Runnable() {
            @Override
            public void run() {

                viewPagerAdapter.addFragment(CalculationFragment.getInstance(), "Calculation");
                viewPagerAdapter.addFragment(DetailsFragment.getInstance(), "Details");
                viewPagerAdapter.addFragment(HistoryFragment.getInstance(), "History");

                viewPager.setAdapter(viewPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);


                try {
                    detailsFooterButtons.setVisibility(View.GONE);
                } catch (NullPointerException ignored) {
                }

//                Time taken 2 days to got it. Before I was trying to hide keyboard through imm in a fragment but fragment is not refreshable
//                on changing fragments through pressing tabs.
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {

                        if (position == 0 || position == 1) {
                            RecyclerView rvCurrency = findViewById(R.id.RVCurrencies);
                            if (rvCurrency.getVisibility() != View.VISIBLE) {
                                rvCurrency.setVisibility(View.VISIBLE);
                            }
                        }


                        if (position == 0 || position == 2) {
                            detailsFooterButtons.setVisibility(View.GONE);
                        } else {
                            detailsFooterButtons.setVisibility(View.VISIBLE);
                        }


                        if (position == 2) {
                            try {
                                tabLayout.getTabAt(2).getOrCreateBadge().setVisible(false);
                            } catch (NullPointerException ignored) {
                            }
                        }


                        if (position == 2 || position == 1) {

                            AppBarLayout appBarLayout = findViewById(R.id.appBar_layout);
                            appBarLayout.setExpanded(true, true);

                            NestedScrollView nestedScrollViewDetails = findViewById(R.id.nested_details);
                            nestedScrollViewDetails.scrollTo(0, 0);


                            try {

                                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            bottomNavigationView.setVisibility(View.VISIBLE);
                            textViewResultAmountLayout.setVisibility(View.GONE);

                            try {
                                int badgeNumber = tabLayout.getTabAt(0).getOrCreateBadge().getNumber();
                                if (badgeNumber == 1 || badgeNumber == 2 || badgeNumber == 3 || badgeNumber == 4
                                        || badgeNumber == 5 || badgeNumber == 6 || badgeNumber == 7 || badgeNumber == 8
                                        || badgeNumber == 9 || badgeNumber == 10 || badgeNumber == 11 || badgeNumber == 12
                                        || badgeNumber == 13) {
                                    tabLayout.getTabAt(0).getOrCreateBadge().setBackgroundColor(Color.parseColor("#8899a6"));
                                } else {
                                    tabLayout.getTabAt(0).getOrCreateBadge().setVisible(false);
                                }

                            } catch (NullPointerException Ignore) {
                            }

                        } else {

                            AppBarLayout appBarLayout = findViewById(R.id.appBar_layout);
                            appBarLayout.setExpanded(true, true);

                            NestedScrollView nestedScrollView = findViewById(R.id.nested);
                            nestedScrollView.scrollTo(0, 0);

                            NestedScrollView nestedScrollViewDetails = findViewById(R.id.nested_details);
                            nestedScrollViewDetails.scrollTo(0, 0);


                            textViewResultAmountLayout.setVisibility(View.VISIBLE);

                            try {

                                int badgeNumber = tabLayout.getTabAt(0).getOrCreateBadge().getNumber();
                                tabLayout.getTabAt(0).getOrCreateBadge().setBackgroundColor(Color.parseColor("#1da1f3"));
                                if (badgeNumber == 1 || badgeNumber == 2 || badgeNumber == 3 || badgeNumber == 4
                                        || badgeNumber == 5 || badgeNumber == 6 || badgeNumber == 7 || badgeNumber == 8
                                        || badgeNumber == 9 || badgeNumber == 10 || badgeNumber == 11 || badgeNumber == 12
                                        || badgeNumber == 13 || badgeNumber == 14) {
                                    tabLayout.getTabAt(0).getOrCreateBadge().setBackgroundColor(Color.parseColor("#1da1f3"));
                                } else {
                                    tabLayout.getTabAt(0).getOrCreateBadge().setVisible(false);
                                }


                                if (save_button_pressed) {
                                    tabLayout.getTabAt(0).getOrCreateBadge().setBackgroundColor(Color.parseColor("#ffff00"));
                                }


                            } catch (NullPointerException ignored) {
                            }
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                });


//                Took one day to  get it. It is used to stop refreshing all the fragment each and every time while changing any one of them.
                viewPager.setOffscreenPageLimit(2);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        switchCompatFingerPrint.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {

                    toggleStatusFingerPrint = true;

                    androidx.biometric.BiometricManager biometricManager = androidx.biometric.BiometricManager.from(getApplicationContext());
                    switch (biometricManager.canAuthenticate()) {


                        case BiometricManager.BIOMETRIC_SUCCESS:

                            break;

                        case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:

                            Toast toast = Toast.makeText(MainActivity.this, "This device doesn't have a fingerprint sensor.", Toast.LENGTH_SHORT);
                            View view1 = toast.getView();
                            switchCompatFingerPrint.setChecked(false);


                            try {

                                TextView textView = toast.getView().findViewById(android.R.id.message);
                                textView.setTextColor(Color.parseColor("#ffffff"));

                            } catch (NullPointerException ignored) {
                            }


                            try {
                                assert view1 != null;
                                view1.getBackground().setColorFilter(Color.parseColor("#10171f"), PorterDuff.Mode.SRC_IN);
                            } catch (NullPointerException ignored) {
                            }
                            toast.show();
                            break;


                        case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                            toast = Toast.makeText(MainActivity.this, "The biometric sensor is currently unavailable.", Toast.LENGTH_SHORT);
                            view1 = toast.getView();
                            switchCompatFingerPrint.setChecked(false);

                            try {

                                TextView textView = toast.getView().findViewById(android.R.id.message);
                                textView.setTextColor(Color.parseColor("#ffffff"));

                            } catch (NullPointerException ignored) {
                            }


                            try {
                                assert view1 != null;
                                view1.getBackground().setColorFilter(Color.parseColor("#10171f"), PorterDuff.Mode.SRC_IN);
                            } catch (NullPointerException ignored) {
                            }
                            toast.show();
                            break;

                        case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                            toast = Toast.makeText(MainActivity.this, "Your device doesn't have fingerprint saved, please check your security settings", Toast.LENGTH_SHORT);
                            view1 = toast.getView();
                            switchCompatFingerPrint.setChecked(false);

                            try {

                                TextView textView = toast.getView().findViewById(android.R.id.message);
                                textView.setTextColor(Color.parseColor("#ffffff"));

                            } catch (NullPointerException ignored) {
                            }


                            try {
                                assert view1 != null;
                                view1.getBackground().setColorFilter(Color.parseColor("#10171f"), PorterDuff.Mode.SRC_IN);
                            } catch (NullPointerException ignored) {
                            }
                            toast.show();
                            break;


                        default:
                            switchCompatFingerPrint.setChecked(false);
                            break;
                    }

                    Executor executor = ContextCompat.getMainExecutor(getApplicationContext());


                    biometricPrompt = new BiometricPrompt(MainActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
                        @Override
                        public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                            super.onAuthenticationError(errorCode, errString);

                            switch (errorCode) {
                                case FINGERPRINT_ERROR_LOCKOUT:

                                    alertDialoBuider = new AlertDialog.Builder(MainActivity.this, R.style.alertDialog);
                                    alertDialoBuider.setTitle("Access denied");
                                    alertDialoBuider.setIcon(R.drawable.warning);
                                    alertDialoBuider.setMessage("30 seconds");

                                    alertDialog = alertDialoBuider.create();
                                    alertDialog.setCanceledOnTouchOutside(false);
                                    alertDialog.setCancelable(false);


                                    new CountDownTimer(30000, 1000) {
                                        public void onTick(long millisUntilFinished) {

                                            alertDialog.setMessage("Too many failed attempts, Try again in " + millisUntilFinished / 1000 + " seconds.");

                                        }

                                        @Override
                                        public void onFinish() {

                                            promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Biometric Authentication")
                                                    .setDescription("Unlock Total Paisa").setNegativeButtonText("Cancel").build();

                                            biometricPrompt.authenticate(promptInfo);


                                            alertDialog.dismiss();

                                        }

                                    }.start();

                                    alertDialog.show();
                                    break;

                                case FINGERPRINT_ERROR_CANCELED:
                                    finish();
                                    break;

                                case FINGERPRINT_ERROR_LOCKOUT_PERMANENT:

                                    alertDialoBuider = new AlertDialog.Builder(MainActivity.this, R.style.alertDialog);
                                    alertDialoBuider.setTitle("Total Paisa Locked");
                                    alertDialoBuider.setIcon(R.drawable.warning);
                                    alertDialoBuider.setMessage("Too many failed attempts, Now Lock your device and then unlock again.");

                                    alertDialoBuider.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                            finish();
                                        }

                                    });

                                    alertDialog = alertDialoBuider.create();
                                    alertDialog.setCanceledOnTouchOutside(false);
                                    alertDialog.setCancelable(false);
                                    alertDialog.show();
                                    break;


                                case FINGERPRINT_ERROR_USER_CANCELED:
                                    finish();
                                    break;

                                case FINGERPRINT_ERROR_NO_FINGERPRINTS:
                                    break;

                                case FINGERPRINT_ERROR_HW_NOT_PRESENT:
                                    break;

                                case FINGERPRINT_ERROR_HW_UNAVAILABLE:
                                    break;

                                case BiometricPrompt.ERROR_NEGATIVE_BUTTON:
                                    finish();
                                    break;


                                default:

                                    finish();
                                    break;
                            }

                        }

                        @Override
                        public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                            super.onAuthenticationSucceeded(result);
                        }

                        @Override
                        public void onAuthenticationFailed() {
                            super.onAuthenticationFailed();


                        }

                    });

                    final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Biometric Authentication")
                            .setDescription("Unlock Total Paisa").setNegativeButtonText("Cancel").build();

                    biometricPrompt.authenticate(promptInfo);

                } else {

                    toggleStatusFingerPrint = false;

                }
            }
        });


    }

    private InstallStateUpdatedListener installStateUpdatedListener = new InstallStateUpdatedListener() {
        @Override
        public void onStateUpdate(@NonNull InstallState state) {
            if (state.installStatus() == InstallStatus.DOWNLOADED) {
                showCompletedUpdate();
            }
        }
    };


    private void showCompletedUpdate() {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.viewPager), "New app is ready!",
                Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Install", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAppUpdateManager.completeUpdate();
            }
        });

        snackbar.setActionTextColor(Color.parseColor("#3bd16f"));
        snackbar.setTextColor(Color.parseColor("#ffffff"));
        snackbar.setBackgroundTint(Color.parseColor("#10171f"));
        snackbar.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == RC_APP_UPDATE && resultCode != RESULT_OK) {
//            Toast.makeText(getApplicationContext(),"cancel", Toast.LENGTH_SHORT).show();
        }


        try {
            if (!bp.handleActivityResult(requestCode, resultCode, data)) {
                super.onActivityResult(requestCode, resultCode, data);
            }
        } catch (NullPointerException ignored) {}

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStop() {

        if (mAppUpdateManager != null)
            mAppUpdateManager.unregisterListener(installStateUpdatedListener);
        super.onStop();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    protected void onPause() {
        super.onPause();

        // These are used because when app resume after on pause so bottom result layout was appearing in the details fragament when it was on details framgents......

        try {
            editTextPayeeName.clearFocus();
        } catch (NullPointerException ignored) {
        }

        try {

            EditText tallyAmount = findViewById(R.id.tallyAmount);
            tallyAmount.clearFocus();
        } catch (NullPointerException ignored) {
        }


        try {
            editTextNumber2000.clearFocus();
        } catch (NullPointerException ignored) {
        }

        try {
            editTextNumber500.clearFocus();
        } catch (NullPointerException ignored) {
        }

        try {
            editTextNumber200.clearFocus();
        } catch (NullPointerException ignored) {
        }

        try {
            editTextNumber100.clearFocus();
        } catch (NullPointerException ignored) {
        }

        try {
            editTextNumber50.clearFocus();
        } catch (NullPointerException ignored) {
        }

        try {
            editTextNumber20.clearFocus();
        } catch (NullPointerException ignored) {
        }

        try {
            editTextNumber10.clearFocus();
        } catch (NullPointerException ignored) {
        }

        try {
            editTextNumber5.clearFocus();
        } catch (NullPointerException ignored) {
        }

        try {
            editTextNumber20Coin.clearFocus();
        } catch (NullPointerException ignored) {
        }

        try {
            editTextNumber10Coin.clearFocus();
        } catch (NullPointerException ignored) {
        }

        try {
            editTextNumber5Coin.clearFocus();
        } catch (NullPointerException ignored) {
        }

        try {
            editTextNumber2Coin.clearFocus();
        } catch (NullPointerException ignored) {
        }

        try {
            editTextNumber1Coin.clearFocus();
        } catch (NullPointerException ignored) {
        }

        try {
            editTextNumberCoinExtra.clearFocus();
        } catch (NullPointerException ignored) {}

        if(adRemoved){
            SharedPreferences sharedPreferences = getSharedPreferences("adRemoved", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.putBoolean("boolean", true);
            editor.commit();

        } else {
            SharedPreferences sharedPreferences = getSharedPreferences("adRemoved", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.putBoolean("boolean", false);
            editor.commit();

        }



        if (switchCompatFingerPrint.isChecked()) {
            toggleStatusFingerPrint = true;
            SharedPreferences sharedPreferences = getSharedPreferences("toggleStatusFingerPrint", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.putBoolean("boolean", true);
            editor.commit();

        } else {
            toggleStatusFingerPrint = false;
            SharedPreferences sharedPreferences = getSharedPreferences("toggleStatusFingerPrint", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("boolean", false);
            editor.commit();
        }


        if (switchCompatTellerSound.isChecked()) {
            toggleStatusTellerSound = true;
            SharedPreferences sharedPreferences = getSharedPreferences("toggleStatusTellerSound", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.putBoolean("boolean", true);
            editor.commit();

        } else {
            toggleStatusTellerSound = false;
            SharedPreferences sharedPreferences = getSharedPreferences("toggleStatusTellerSound", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("boolean", false);
            editor.commit();
        }


    }

    @Override
    protected void onDestroy() {

//        if (bp != null) {
//            bp.release();
//        }
        super.onDestroy();
//
//        if(adRemoved){
//            SharedPreferences sharedPreferences = getSharedPreferences("adRemoved", MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.clear();
//            editor.putBoolean("boolean", true);
//            editor.commit();
//
//        } else {
//            SharedPreferences sharedPreferences = getSharedPreferences("adRemoved", MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.clear();
//            editor.putBoolean("boolean", false);
//            editor.commit();
//
//        }


        if (switchCompatFingerPrint.isChecked()) {
            toggleStatusFingerPrint = true;
            SharedPreferences sharedPreferences = getSharedPreferences("toggleStatusFingerPrint", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.putBoolean("boolean", true);
            editor.commit();

        } else {
            toggleStatusFingerPrint = false;
            SharedPreferences sharedPreferences = getSharedPreferences("toggleStatusFingerPrint", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("boolean", false);
            editor.commit();
        }

        if (switchCompatTellerSound.isChecked()) {
            toggleStatusTellerSound = true;
            SharedPreferences sharedPreferences = getSharedPreferences("toggleStatusTellerSound", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.putBoolean("boolean", true);
            editor.commit();

        } else {
            toggleStatusTellerSound = false;
            SharedPreferences sharedPreferences = getSharedPreferences("toggleStatusTellerSound", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("boolean", false);
            editor.commit();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    try {
                        viewPager.setCurrentItem(0);
                    } catch (NullPointerException ignored) {}

                }
            }, 400);


        SharedPreferences getShared = getSharedPreferences("toggleStatusFingerPrint", MODE_PRIVATE);
        toggleStatusFingerPrint = getShared.getBoolean("boolean", false);

        getShared = getSharedPreferences("toggleStatusTellerSound", MODE_PRIVATE);
        toggleStatusTellerSound = getShared.getBoolean("boolean", true);


//        getShared = getSharedPreferences("adRemoved", MODE_PRIVATE);
//        adRemoved = getShared.getBoolean("boolean", false);


        if (toggleStatusTellerSound) {
            switchCompatTellerSound.setChecked(true);
        } else {
            switchCompatTellerSound.setChecked(false);
        }


        if (toggleStatusFingerPrint) {
            switchCompatFingerPrint.setChecked(true);
        } else {
            switchCompatFingerPrint.setChecked(false);
        }


        try {

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        } catch (Exception ignored) {
        }

        try {
            bottomNavigationView.setSelectedItemId(R.id.totalPaisa);
        } catch (Resources.NotFoundException ignored) {
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.gst:
                        startActivity(new Intent(getApplicationContext(), Gst.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.totalPaisa:
                        return true;
                }

                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        final int id = item.getItemId();


        if (id == R.id.RefreshButton) {
            try {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            } catch (Exception ignored) {
            }

            AlertDialog.Builder alertDialoBuider = new AlertDialog.Builder(MainActivity.this, R.style.alertDialog);
            alertDialoBuider.setTitle("Confirm Reset !");
            alertDialoBuider.setIcon(R.drawable.warning);
            alertDialoBuider.setMessage("Are you sure want to reset ?");

            alertDialoBuider.setPositiveButton("Reset", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialogInterface, int i) {


                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            NestedScrollView nestedScrollView = findViewById(R.id.nested);
                            nestedScrollView.scrollTo(0, 0);
                            NestedScrollView nestedScrollViewDetails = findViewById(R.id.nested_details);
                            nestedScrollViewDetails.scrollTo(0, 0);
                            AppBarLayout appBarLayout = findViewById(R.id.appBar_layout);
                            appBarLayout.setExpanded(true, true);
                        }
                    }, 100);

                    CalculationFragment.editTextNumber2000.getText().clear();
                    CalculationFragment.editTextNumber500.getText().clear();
                    CalculationFragment.editTextNumber200.getText().clear();
                    CalculationFragment.editTextNumber100.getText().clear();
                    CalculationFragment.editTextNumber50.getText().clear();
                    editTextNumber20.getText().clear();
                    editTextNumber10.getText().clear();
                    CalculationFragment.editTextNumber5.getText().clear();
                    CalculationFragment.editTextNumber20Coin.getText().clear();
                    CalculationFragment.editTextNumber10Coin.getText().clear();
                    CalculationFragment.editTextNumber5Coin.getText().clear();
                    CalculationFragment.editTextNumber2Coin.getText().clear();
                    CalculationFragment.editTextNumber1Coin.getText().clear();
                    editTextNumberCoinExtra.getText().clear();

                    String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                    TextView textViewCalender = findViewById(R.id.textViewCalender);
                    textViewCalender.setText(date);


                    TextView textViewTodayTomorrowYesterday = findViewById(R.id.textViewTodayTomorrowYesterday);
                    textViewTodayTomorrowYesterday.setText("Today :");


                    TextView textViewDayOfTheWeek = findViewById(R.id.textViewDayOfTheWeek);
                    DateFormat sourceFormat = new SimpleDateFormat("dd-MM-yyyy");
                    try {
                        Date date1 = sourceFormat.parse(date);
                        String newDay = android.text.format.DateFormat.format("EEEE", date1).toString();
                        textViewDayOfTheWeek.setText(newDay);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    EditText editTextPayeeName = findViewById(R.id.editTextPayeeName);
                    editTextPayeeName.setText("");

                    EditText editTextTellerBalance = findViewById(R.id.tallyAmount);
                    editTextTellerBalance.setText("");

                }

            });


            alertDialoBuider.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();

                }


            });
            AlertDialog alertDialog = alertDialoBuider.create();
            alertDialog.show();

        } else if (id == R.id.ShareButton) {

            try {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            } catch (Exception e) {

            }


            if (!textViewResult.getText().toString().replaceAll(",", "").equals("0")) {

                String myTextViewWord = DetailsFragment.TotalAmountInWords.getText().toString();
                String myTextView2000 = CalculationFragment.textViewRes2000.getText().toString().replaceAll(",", "");
                String myTextView500 = CalculationFragment.textViewRes500.getText().toString().replaceAll(",", "");
                String myTextView200 = CalculationFragment.textViewRes200.getText().toString().replaceAll(",", "");
                String myTextView100 = CalculationFragment.textViewRes100.getText().toString().replaceAll(",", "");
                String myTextView50 = CalculationFragment.textViewRes50.getText().toString().replaceAll(",", "");
                String myTextView20 = CalculationFragment.textViewRes20.getText().toString().replaceAll(",", "");
                String myTextView10 = CalculationFragment.textViewRes10.getText().toString().replaceAll(",", "");
                String myTextView5 = CalculationFragment.textViewRes5.getText().toString().replaceAll(",", "");
                String myTextViewCoin20 = CalculationFragment.textViewRes20Coin.getText().toString().replaceAll(",", "");
                String myTextViewCoin10 = CalculationFragment.textViewRes10Coin.getText().toString().replaceAll(",", "");
                String myTextViewCoin5 = CalculationFragment.textViewRes5Coin.getText().toString().replaceAll(",", "");
                String myTextViewCoin2 = CalculationFragment.textViewRes2Coin.getText().toString().replaceAll(",", "");
                String myTextViewCoin1 = CalculationFragment.textViewRes1Coin.getText().toString().replaceAll(",", "");
                String myTextViewCoins = CalculationFragment.textViewResCoinExtra.getText().toString().replaceAll(",", "");
                String myTextViewInNumbers = textViewResult.getText().toString().replaceAll(",", "");

                String myEditText2000 = CalculationFragment.editTextNumber2000.getText().toString();
                String myEditText500 = CalculationFragment.editTextNumber500.getText().toString();
                String myEditText200 = CalculationFragment.editTextNumber200.getText().toString();
                String myEditText100 = CalculationFragment.editTextNumber100.getText().toString();
                String myEditText50 = CalculationFragment.editTextNumber50.getText().toString();
                String myEditText20 = editTextNumber20.getText().toString();
                String myEditText10 = editTextNumber10.getText().toString();
                String myEditText5 = CalculationFragment.editTextNumber5.getText().toString();
                String myEditTextCoin20 = CalculationFragment.editTextNumber20Coin.getText().toString();
                String myEditTextCoin10 = CalculationFragment.editTextNumber10Coin.getText().toString();
                String myEditTextCoin5 = CalculationFragment.editTextNumber5Coin.getText().toString();
                String myEditTextCoin2 = CalculationFragment.editTextNumber2Coin.getText().toString();
                String myEditTextCoin1 = CalculationFragment.editTextNumber1Coin.getText().toString();
                String myEditTextExtraCoin = CalculationFragment.editTextNumberCoinExtra.getText().toString();

                int my2000 = Integer.parseInt(CalculationFragment.textViewRes2000.getText().toString().replaceAll(",", ""));
                int my500 = Integer.parseInt(CalculationFragment.textViewRes500.getText().toString().replaceAll(",", ""));
                int my200 = Integer.parseInt(CalculationFragment.textViewRes200.getText().toString().replaceAll(",", ""));
                int my100 = Integer.parseInt(CalculationFragment.textViewRes100.getText().toString().replaceAll(",", ""));
                int my50 = Integer.parseInt(CalculationFragment.textViewRes50.getText().toString().replaceAll(",", ""));
                int my20 = Integer.parseInt(CalculationFragment.textViewRes20.getText().toString().replaceAll(",", ""));
                int my10 = Integer.parseInt(CalculationFragment.textViewRes10.getText().toString().replaceAll(",", ""));
                int my5 = Integer.parseInt(CalculationFragment.textViewRes5.getText().toString().replaceAll(",", ""));

                int totalNotes = my2000 + my500 + my200 + my100 + my50 + my20 + my10 + my5;
                String totalNotesInString = String.valueOf(totalNotes);

                int my20Coin = Integer.parseInt(CalculationFragment.textViewRes20Coin.getText().toString().replaceAll(",", ""));
                int my10Coin = Integer.parseInt(CalculationFragment.textViewRes10Coin.getText().toString().replaceAll(",", ""));
                int my5Coin = Integer.parseInt(CalculationFragment.textViewRes5Coin.getText().toString().replaceAll(",", ""));
                int my2Coin = Integer.parseInt(CalculationFragment.textViewRes2Coin.getText().toString().replaceAll(",", ""));
                int my1Coin = Integer.parseInt(CalculationFragment.textViewRes1Coin.getText().toString().replaceAll(",", ""));
                int myCoinExtra = Integer.parseInt(CalculationFragment.textViewResCoinExtra.getText().toString().replaceAll(",", ""));

                int totalCoins = my20Coin + my10Coin + my5Coin + my2Coin + my1Coin + myCoinExtra;
                String totalCoinsInString = String.valueOf(totalCoins);


                TextView textViewDate = findViewById(R.id.textViewCalender);
                TextView textViewTime = findViewById(R.id.textViewTime);
                TextView textViewDay = findViewById(R.id.textViewDayOfTheWeek);
                EditText payeeName = findViewById(R.id.editTextPayeeName);


                StringBuilder forShare = new StringBuilder();

                forShare.append("Date :  ");
                forShare.append(textViewDate.getText().toString());
                forShare.append('\n');
                forShare.append("Day :  ");
                forShare.append(textViewDay.getText().toString());
                forShare.append('\n');
                forShare.append(textViewTime.getText().toString());
                forShare.append('\n');


                if (payeeName.getText().toString().trim().matches("")) {
                    forShare.append("Payee Name :  Unknown ");
                } else {

                    forShare.append("Payee Name :  ");
                    forShare.append(payeeName.getText().toString());

                }

                forShare.append('\n');
                forShare.append('\n');


                forShare.append("◕  Notes :  ");
                forShare.append('\n');

                if (myEditText2000.length() != 0) {
                    forShare.append("•  2000  x  ");
                    forShare.append(myEditText2000);
                    forShare.append("  =  ₹ ");
                    forShare.append(AddComma.getIndianCurrencyFormat(myTextView2000));
                    forShare.append('\n');
                }


                if (myEditText500.length() != 0) {
                    forShare.append("•  500    x  ");
                    forShare.append(myEditText500);
                    forShare.append("  =  ₹ ");
                    forShare.append(AddComma.getIndianCurrencyFormat(myTextView500));
                    forShare.append('\n');
                }


                if (myEditText200.length() != 0) {
                    forShare.append("•  200    x  ");
                    forShare.append(myEditText200);
                    forShare.append("  =  ₹ ");
                    forShare.append(AddComma.getIndianCurrencyFormat(myTextView200));
                    forShare.append('\n');
                }


                if (myEditText100.length() != 0) {
                    forShare.append("•  100    x  ");
                    forShare.append(myEditText100);
                    forShare.append("  =  ₹ ");
                    forShare.append(AddComma.getIndianCurrencyFormat(myTextView100));
                    forShare.append('\n');
                }


                if (myEditText50.length() != 0) {
                    forShare.append("•  50      x  ");
                    forShare.append(myEditText50);
                    forShare.append("  =  ₹ ");
                    forShare.append(AddComma.getIndianCurrencyFormat(myTextView50));
                    forShare.append('\n');
                }


                if (myEditText20.length() != 0) {
                    forShare.append("•  20      x  ");
                    forShare.append(myEditText20);
                    forShare.append("  =  ₹ ");
                    forShare.append(AddComma.getIndianCurrencyFormat(myTextView20));
                    forShare.append('\n');
                }


                if (myEditText10.length() != 0) {
                    forShare.append("•  10      x  ");
                    forShare.append(myEditText10);
                    forShare.append("  =  ₹ ");
                    forShare.append(AddComma.getIndianCurrencyFormat(myTextView10));
                    forShare.append('\n');
                }


                if (myEditText5.length() != 0) {
                    forShare.append("•  5        x  ");
                    forShare.append(myEditText5);
                    forShare.append("  =  ₹ ");
                    forShare.append(AddComma.getIndianCurrencyFormat(myTextView5));
                    forShare.append('\n');
                }


                forShare.append("•  Total value of Notes  =>  ₹ ");
                forShare.append(AddComma.getIndianCurrencyFormat(totalNotesInString));
                forShare.append('\n');
                forShare.append('\n');
                forShare.append('\n');
                forShare.append("◕  Coins :  ");
                forShare.append('\n');

                if (myEditTextCoin20.length() != 0) {
                    forShare.append("•  20 Coin  x  ");
                    forShare.append(myEditTextCoin20);
                    forShare.append("  =  ₹ ");
                    forShare.append(AddComma.getIndianCurrencyFormat(myTextViewCoin20));
                    forShare.append('\n');
                }

                if (myEditTextCoin10.length() != 0) {
                    forShare.append("•  10 Coin  x  ");
                    forShare.append(myEditTextCoin10);
                    forShare.append("  =  ₹ ");
                    forShare.append(AddComma.getIndianCurrencyFormat(myTextViewCoin10));
                    forShare.append('\n');
                }


                if (myEditTextCoin5.length() != 0) {
                    forShare.append("•  5 Coin    x  ");
                    forShare.append(myEditTextCoin5);
                    forShare.append("  =  ₹ ");
                    forShare.append(AddComma.getIndianCurrencyFormat(myTextViewCoin5));
                    forShare.append('\n');
                }


                if (myEditTextCoin2.length() != 0) {
                    forShare.append("•  2 Coin    x  ");
                    forShare.append(myEditTextCoin2);
                    forShare.append("  =  ₹ ");
                    forShare.append(AddComma.getIndianCurrencyFormat(myTextViewCoin2));
                    forShare.append('\n');
                }


                if (myEditTextCoin1.length() != 0) {
                    forShare.append("•  1 Coin    x  ");
                    forShare.append(myEditTextCoin1);
                    forShare.append("  =  ₹ ");
                    forShare.append(AddComma.getIndianCurrencyFormat(myTextViewCoin1));
                    forShare.append('\n');
                }


                if (myEditTextExtraCoin.length() != 0) {
                    forShare.append("•  Extra Coins  ");
                    forShare.append("  =  ₹ ");
                    forShare.append(AddComma.getIndianCurrencyFormat(myTextViewCoins));
                    forShare.append('\n');
                }


                forShare.append("•  Total value of Coins =>  ₹ ");
                forShare.append(AddComma.getIndianCurrencyFormat(totalCoinsInString));
                forShare.append('\n');
                forShare.append('\n');
                forShare.append('\n');
                forShare.append("•  Total Amount ");
                forShare.append("  =>  ₹ ");
                forShare.append(AddComma.getIndianCurrencyFormat(myTextViewInNumbers));

                forShare.append('\n');

                forShare.append("•  Total Amount In Words ");
                forShare.append("  =>  ");
                forShare.append('\n');
                forShare.append(myTextViewWord);
                forShare.append('\n');
                forShare.append('\n');
                forShare.append('\n');

                forShare.append(" Shared via Total Paisa App:");
                forShare.append('\n');
                forShare.append("https://play.google.com/store/apps/details?id=com.vibhunorby.totalpaisa");
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, (Serializable) forShare);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

            } else {
                Toast toast = Toast.makeText(MainActivity.this, "Zero amount can't be shared.", Toast.LENGTH_SHORT);
                View view1 = toast.getView();

                try {

                    TextView textView = toast.getView().findViewById(android.R.id.message);
                    textView.setTextColor(Color.parseColor("#ffffff"));

                } catch (NullPointerException ignored) {
                }


                try {
                    assert view1 != null;
                    view1.getBackground().setColorFilter(Color.parseColor("#10171f"), PorterDuff.Mode.SRC_IN);
                } catch (NullPointerException ignored) {
                }
                toast.show();
            }

        } else if (id == R.id.RemoveAd) {


            try {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            } catch (Exception ignored) {

            }

            fakeButton.performClick();

        } else if (id == R.id.DeleteAll) {
//            it took 2 days to find this out myself this is the fastest way to delete database without adapter array list etc :)


            AlertDialog.Builder alertDialoBuider = new AlertDialog.Builder(MainActivity.this, R.style.alertDialog);
            alertDialoBuider.setTitle("Delete All Data ?");
            alertDialoBuider.setIcon(R.drawable.warning);
            alertDialoBuider.setMessage("If you delete the data, Your history will be cleared.");

            alertDialoBuider.setPositiveButton(Html.fromHtml("<font color='#ff0000'>Delete All</font>"), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    snackbar = Snackbar.make(viewPager, "", 6000);
                    snackbar.setAction("Cancel", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            countDownTimer.cancel();

                        }
                    });

                    snackbar.setActionTextColor(Color.parseColor("#3bd16f"));
                    snackbar.setTextColor(Color.parseColor("#ffffff"));
                    snackbar.setBackgroundTint(Color.parseColor("#10171f"));

                    countDownTimer = new CountDownTimer(6000, 1000) {
                        public void onTick(long millisUntilFinished) {

                            if ((millisUntilFinished / 1000 == 1) || (millisUntilFinished / 1000 == 0)) {
                                snackbar.setText("Deleting all data in  " + millisUntilFinished / 1000 + "  Second.");
                                snackbar.setTextColor(Color.parseColor("#ff0000"));
                            } else {
                                snackbar.setText("Deleting all data in  " + millisUntilFinished / 1000 + "  Seconds.");
                            }

                        }

                        @Override
                        public void onFinish() {

                            save_button_was_pressed = true;

                            deleteDatabase("currencydb");
                            RecyclerView rvCurrency = findViewById(R.id.RVCurrencies);
                            rvCurrency.setVisibility(View.GONE);


                            Toast toast = Toast.makeText(MainActivity.this, "History has been cleared.", Toast.LENGTH_SHORT);
                            View view1 = toast.getView();

                            try {

                                TextView textView = toast.getView().findViewById(android.R.id.message);
                                textView.setTextColor(Color.parseColor("#ffffff"));

                            } catch (NullPointerException ignored) {
                            }

                            try {
                                assert view1 != null;
                                view1.getBackground().setColorFilter(Color.parseColor("#10171f"), PorterDuff.Mode.SRC_IN);
                            } catch (NullPointerException ignored) {
                            }
                            toast.show();


                        }
                    }.start();

                    snackbar.show();

                }

            });


            alertDialoBuider.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();

                }


            });
            AlertDialog alertDialog = alertDialoBuider.create();
            alertDialog.show();

        }
        return super.onOptionsItemSelected(item);
    }


    //    It took 2 days to set fragment to default on back pressed. Before I was messed up with tab layout fragment backpressed.
    @Override
    public void onBackPressed() {


        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();

            if (viewPager.getCurrentItem() == 0) {
//
//                ImageView image = new ImageView(this);
//                image.setImageResource(R.drawable.qureka1);
//
//                image.setOnClickListener(v ->
//                {
//
//                    Uri uri = Uri.parse("https://830.go.mglgamez.com");
//                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
//                    startActivity(intent);
//                });

                AlertDialog.Builder alertDialoBuider = new AlertDialog.Builder(MainActivity.this, R.style.alertDialog);
                alertDialoBuider.setTitle("Confirm Exit !");
                alertDialoBuider.setIcon(R.drawable.warning);
                alertDialoBuider.setMessage("Do you really want to exit ?");


                alertDialoBuider.setPositiveButton("Exit", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        finish();
                    }

                })/*.setView(image)*/;



                alertDialoBuider.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                    }
                });
                AlertDialog alertDialog = alertDialoBuider.create();
                alertDialog.show();

//                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) image.getLayoutParams();
//                marginLayoutParams.setMargins(1,1,1,1);


            } else {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 2);
            }
        }


    }


    public static void hideSoftKeyboard(MainActivity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isAcceptingText()) {
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(MainActivity.this);
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

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.rate_us:

                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.vibhunorby.totalpaisa");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);


                break;

            case R.id.share_app:

                StringBuilder forShareLink = new StringBuilder();
                forShareLink.append("Download the Total paisa app, Indian Cash denomination and cash tally ");
                forShareLink.append('\n');
                forShareLink.append("https://play.google.com/store/apps/details?id=com.vibhunorby.totalpaisa");
                intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, (Serializable) forShareLink);
                intent.setType("text/plain");
                startActivity(intent);

                break;

            case R.id.feedback:
                Intent feedbackEmail = new Intent(Intent.ACTION_SEND);
                feedbackEmail.setType("message/rfc822");
                feedbackEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{"vibhanshu.keshari@gmail.com"});
                feedbackEmail.putExtra(Intent.EXTRA_SUBJECT, "Feedback for Total paisa app");
                feedbackEmail.setPackage("com.google.android.gm");
                if (feedbackEmail.resolveActivity(getPackageManager()) != null) {
                    startActivity(feedbackEmail);
                } else {
                    Toast toast = Toast.makeText(MainActivity.this, "Gmail App is not installed.", Toast.LENGTH_SHORT);
                    View view1 = toast.getView();

                    try {
                        assert view1 != null;
                        view1.getBackground().setColorFilter(Color.parseColor("#10171f"), PorterDuff.Mode.SRC_IN);
                    } catch (NullPointerException ignored) {
                    }
                    toast.show();
                }

                break;

            case R.id.privacy:
//                flycricket is used to host this privacy free of cost.
//                Generated by github users nishant and contributors.
                uri = Uri.parse("https://total-paisa.flycricket.io/privacy.html");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.term_and_condition:

                uri = Uri.parse("https://total-paisa.flycricket.io/terms.html");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


//    @Override
//    public void onProductPurchased(String productId, TransactionDetails details) {
//
//
//        Toast toast = Toast.makeText(MainActivity.this, "Ads Removed Successfully.", Toast.LENGTH_SHORT);
//        View view1 = toast.getView();
//
//        try {
//
//            TextView textView = toast.getView().findViewById(android.R.id.message);
//            textView.setTextColor(Color.parseColor("#ffffff"));
//
//        } catch (NullPointerException ignored) {
//        }
//
//
//        try {
//            assert view1 != null;
//            view1.getBackground().setColorFilter(Color.parseColor("#10171f"), PorterDuff.Mode.SRC_IN);
//        } catch (NullPointerException ignored) {
//        }
//        toast.show();
//
//
//        try{
//            adscontainermain.removeView(adView);
//        }catch (NullPointerException ignored){}
//
//        try {
//            toolbar.setTitle("Total Paisa Pro");
//        }catch(NullPointerException ignored){}
//
//        adRemoved = true;
//
//            SharedPreferences sharedPreferences = getSharedPreferences("adRemoved", MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.clear();
//            editor.putBoolean("boolean", true);
//            editor.commit();
//
//
//    }
//
//    @Override
//    public void onPurchaseHistoryRestored() {
//
//    }
//
//    @Override
//    public void onBillingError(int errorCode, Throwable error) {
//
//    }
//
//    private boolean hasPurchased() {
//        if (purchaseTransactionDetails != null) {
//
//            return purchaseTransactionDetails.purchaseInfo != null;
//
//        }
//        return false;
//    }
//
//    @Override
//    public void onBillingInitialized() {
//
//        purchaseTransactionDetails = bp.getPurchaseTransactionDetails(getResources().getString(R.string.product_id));
//
//        fakeButton.setOnClickListener(view -> {
//
//            try {
//                if (bp.isOneTimePurchaseSupported()) {
//                    bp.purchase(this, getResources().getString(R.string.product_id));
//                }
//            }catch (NullPointerException ignored){}
//
//        });
//
//        if (hasPurchased()) {
//
//            try{
//                adscontainermain.removeView(adView);
//            }catch (NullPointerException ignored){}
//
//            try {
//                toolbar.setTitle("Total Paisa Pro");
//            }catch(NullPointerException ignored){}
//
//            adRemoved = true;
//
//            SharedPreferences sharedPreferences = getSharedPreferences("adRemoved", MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.clear();
//            editor.putBoolean("boolean", true);
//            editor.commit();
//
//
//        }
//
//
//
//
//    }
//    Activity activity = this;
//    private void connectToGooglePlayBilling(){
//        bc.startConnection(
//                new BillingClientStateListener() {
//                    @Override
//                    public void onBillingServiceDisconnected() {
//                        connectToGooglePlayBilling();
//                    }
//
//                    @Override
//                    public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
//
//                        if(billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK);
//                        getProductDetails();
//
//                    }
//                }
//        );
//    }
//    private void getProductDetails(){
//        List<String> productIds = new ArrayList<>();
//        productIds.add("remove_total_paisa_ad");
//        SkuDetailsParams  getProductDetailsQuery = SkuDetailsParams
//                .newBuilder()
//                .setSkusList(productIds)
//                .setType(BillingClient.SkuType.INAPP)
//                .build();
//        bc.querySkuDetailsAsync(
//                getProductDetailsQuery,
//                new SkuDetailsResponseListener() {
//                    @Override
//                    public void onSkuDetailsResponse(@NonNull BillingResult billingResult, @Nullable List<SkuDetails> list) {
//                        if(billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK &&
//                        list != null) {
//                            SkuDetails itemInfo = list.get(0);
//                            fakeButton.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    bc.launchBillingFlow(activity, BillingFlowParams.newBuilder()
//                                            .setSkuDetails(itemInfo).build()
//                                    );
//                                }
//                            });
//
//                        }
//                    }
//                }
//        );
//
//    }
//    public void onPurchased
}