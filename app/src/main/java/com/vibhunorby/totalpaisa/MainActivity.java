package com.vibhunorby.totalpaisa;

import androidx.appcompat.app.ActionBar;
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
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.hardware.biometrics.BiometricManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.ProductDetailsResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.android.billingclient.api.QueryPurchasesParams;
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ///////////////////////////////////////////////////////
    //                                                   //
    private final String PRODUCT_PREMIUM = "remove_ads_total_paisa";
    //Price - 149 ₹  (28-04-23)
    // price- 9 ₹ (28/6/23)
    //price- 50 ₹ (28/09/23)
    ///////////////////////////////////////////////////////

    Prefs prefs;
    boolean isInterstitialAdshown = false;

    private ArrayList<String> purchaseItemIDs = new ArrayList<String>() {{
        add(PRODUCT_PREMIUM);
    }};

    private String TAG = "iapSample";

    private BillingClient billingClient;

    LinearLayout dividerNativeAd1,dividerNativeAd2;
    LinearLayout dividerNativeAd3,dividerNativeAd4;
    LinearLayout dividerNativeAd5,dividerNativeAd6;
    Boolean adLoaded = false;
    Boolean adLoaded2nd = false;
    Boolean adLoaded3rd = false;
    Boolean adLoaded4th = false;
    AdView mAdView;
    TemplateView template,template2,template3,template4;
    private InterstitialAd interstitial;
    public static boolean adRemoved;
    LinearLayout adscontainermain;
    public static boolean share_was_pressed = false;
    private CountDownTimer countDownTimer;

    static boolean toggleStatusIncrementDecrement;
    static boolean toggleStatusFingerPrint;

    static boolean toggleStatusKeepScreenOn;
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
    static boolean keyboard_up;
    SwitchCompat switchCompatFingerPrint;
    SwitchCompat switchCompatTellerSound;
    SwitchCompat switchCompatKeepScreenOn;
    SwitchCompat switchCompatIncrementDecrement;
    Menu menu;
    public LinearLayout textViewResultTopBar;

    static int ArrayListSize;
    private AlertDialog.Builder alertDialoBuider;
    private AlertDialog alertDialog;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    public ImageButton plus500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);




        HistoryFragment.isRecordVisible = false;


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
        switchCompatKeepScreenOn = MenuItemCompat.getActionView(menu.findItem(R.id.keep_screen_on)).findViewById(R.id.drawer_switch);
        switchCompatIncrementDecrement = MenuItemCompat.getActionView(menu.findItem(R.id.increment_decrement)).findViewById(R.id.drawer_switch);


//     I was noticed this after 5 months and then searched about this;
        getWindow().setNavigationBarColor(Color.parseColor("#15202b"));

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
        adscontainermain = findViewById(R.id.adsContainer_main);

        
        plus500 = findViewById(R.id.plus_500);

        getTabs();
        prefs = new Prefs(this);

////////////////////////////// All ADs  //////////////////////////////////////////////////////////////////



//----------------------------Banner Ad------------------------------------------------


        if(!prefs.isRemoveAd()){

            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {
                }
            });
        }

        mAdView = findViewById(R.id.adView);


        if(!prefs.isRemoveAd()) {


            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);


        }
        else {

            try {
                adscontainermain.setVisibility(View.GONE);
            }catch (NullPointerException ignored) {}

        }


//------------------------End Banner Ad ------------------------------------------------





//-----------------------------Native Ad 1 ------------------------------------------

        if(!prefs.isRemoveAd()) {

            ColorDrawable colorDrawable = new ColorDrawable(getColor(R.color.light_blue));
            ColorDrawable buttonBackground =  new ColorDrawable(getColor(R.color.highlight_blue));
            AdLoader adLoader = new AdLoader.Builder(this, "ca-app-pub-2808567025402378/2063210847")
                    .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                        @Override
                        public void onNativeAdLoaded(NativeAd nativeAd) {

                            NativeTemplateStyle styles = new
                                    NativeTemplateStyle.Builder().
                                    withCallToActionBackgroundColor(buttonBackground).
                                    withSecondaryTextTypefaceColor(getColor(R.color.light_white)).
                                    withPrimaryTextTypefaceColor(getColor(R.color.light_white)).
                                    withMainBackgroundColor(colorDrawable).build();

                            template = findViewById(R.id.nativeAds);
                            dividerNativeAd1 = findViewById(R.id.dividerNativeAd1);
                            dividerNativeAd2 = findViewById(R.id.dividerNativeAd2);


                            if(template != null) {
                                template.setNativeAd(nativeAd);
                                template.setStyles(styles);
                                template.setVisibility(View.VISIBLE);

                            }

                            try {
                                dividerNativeAd1.setVisibility(View.VISIBLE);
                                dividerNativeAd2.setVisibility(View.VISIBLE);

                            }catch (NullPointerException ignored){};


                            if (isDestroyed()) {
                                nativeAd.destroy();
                                adLoaded = false;
                                return;
                            }

                            adLoaded = true;

                        }
                    })
                    .withAdListener(new AdListener() {
                        @Override
                        public void onAdFailedToLoad(LoadAdError adError) {

                            try {
                                template.setVisibility(View.GONE);
                                dividerNativeAd1.setVisibility(View.GONE);
                                dividerNativeAd2.setVisibility(View.GONE);

                            }catch (NullPointerException ignored) {}


                            adLoaded = false;
                        }
                    })
                    .withNativeAdOptions(new NativeAdOptions.Builder()
                            .build())
                    .build();
            adLoader.loadAd(new AdRequest.Builder().build());


        }


//----------------------------End Native Ad 1st----------------------------------------



        //-----------------------------Native Ad 2 ------------------------------------------

        if(!prefs.isRemoveAd()) {

        ColorDrawable colorDrawable2 = new ColorDrawable(getColor(R.color.light_blue));
        ColorDrawable buttonBackground2 =  new ColorDrawable(getColor(R.color.highlight_blue));
        AdLoader adLoader2 = new AdLoader.Builder(this, "ca-app-pub-2808567025402378/3771044188")
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd2) {

                        NativeTemplateStyle styles2 = new
                                NativeTemplateStyle.Builder().
                                withCallToActionBackgroundColor(buttonBackground2).
                                withSecondaryTextTypefaceColor(getColor(R.color.light_white)).
                                withPrimaryTextTypefaceColor(getColor(R.color.light_white)).
                                withMainBackgroundColor(colorDrawable2).build();

                        template2 = findViewById(R.id.nativeAds2);
                        dividerNativeAd3 = findViewById(R.id.dividerNativeAd3);
                        dividerNativeAd4 = findViewById(R.id.dividerNativeAd4);

                        if(template2 != null) {
                            template2.setNativeAd(nativeAd2);
                            template2.setStyles(styles2);
                            template2.setVisibility(View.VISIBLE);
                        }
                        try {

                            dividerNativeAd3.setVisibility(View.VISIBLE);
                            dividerNativeAd4.setVisibility(View.VISIBLE);

                        }catch (NullPointerException ignored){};


                        if (isDestroyed()) {
                            nativeAd2.destroy();
                            adLoaded2nd = false;
                            return;
                        }



                        adLoaded2nd = true;


                    }
                })
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(LoadAdError adError) {

                        try {
                            template2.setVisibility(View.GONE);
                            dividerNativeAd3.setVisibility(View.GONE);
                            dividerNativeAd4.setVisibility(View.GONE);

                        }catch (NullPointerException ignored) {}


                        adLoaded2nd = false;
                    }
                })
                .withNativeAdOptions(new NativeAdOptions.Builder()
                        .build())
                .build();
        adLoader2.loadAd(new AdRequest.Builder().build());


        }


//----------------------------End Native Ad 2nd----------------------------------------

// Native ad 3 is onBackPressed() dialog ad;

//-----------------------------Native Ad 4th ------------------------------------------

//        if(!prefs.isRemoveAd()) {
//
//            ColorDrawable colorDrawable4 = new ColorDrawable(getColor(R.color.light_blue));
//            ColorDrawable buttonBackground4 =  new ColorDrawable(getColor(R.color.highlight_blue));
//            AdLoader adLoader4 = new AdLoader.Builder(this, "ca-app-pub-2808567025402378/1243047213")
//                    .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
//                        @Override
//                        public void onNativeAdLoaded(NativeAd nativeAd4) {
//
//                            NativeTemplateStyle styles4 = new
//                                    NativeTemplateStyle.Builder().
//                                    withCallToActionBackgroundColor(buttonBackground4).
//                                    withSecondaryTextTypefaceColor(getColor(R.color.light_white)).
//                                    withPrimaryTextTypefaceColor(getColor(R.color.light_white)).
//                                    withMainBackgroundColor(colorDrawable4).build();
//
//                            template4 = findViewById(R.id.nativeAds4);
//                            dividerNativeAd5 = findViewById(R.id.dividerNativeAd5);
//                            dividerNativeAd6 = findViewById(R.id.dividerNativeAd6);
//
//
//                            if(template4 != null) {
//                                template4.setNativeAd(nativeAd4);
//                                template4.setStyles(styles4);
//                                template4.setVisibility(View.VISIBLE);
//
//                            }
//
//                            try {
//
//                                dividerNativeAd5.setVisibility(View.VISIBLE);
//                                dividerNativeAd6.setVisibility(View.VISIBLE);
//
//                            }catch (NullPointerException ignored){};
//
//
//                            if (isDestroyed()) {
//                                nativeAd4.destroy();
//                                adLoaded4th = false;
//                                return;
//                            }
//
//                            adLoaded4th = true;
//
//                        }
//                    })
//                    .withAdListener(new AdListener() {
//                        @Override
//                        public void onAdFailedToLoad(LoadAdError adError) {
//
//                            try {
//                                template4.setVisibility(View.GONE);
//                                dividerNativeAd5.setVisibility(View.GONE);
//                                dividerNativeAd6.setVisibility(View.GONE);
//
//                            }catch (NullPointerException ignored) {}
//
//
//                            adLoaded4th = false;
//                        }
//                    })
//                    .withNativeAdOptions(new NativeAdOptions.Builder()
//                            .build())
//                    .build();
//            adLoader4.loadAd(new AdRequest.Builder().build());
//
//
//        }


//----------------------------End Native Ad 4th----------------------------------------



//----------------------------Interstitial-----------------------------------------

        if(!prefs.isRemoveAd()){
            AdRequest adRequestInterstitial = new AdRequest.Builder().build();
            InterstitialAd.load(this,"ca-app-pub-2808567025402378/8723523975", adRequestInterstitial,
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {

                            interstitial = interstitialAd;

                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {

                            interstitial = null;

                        }
                    });

        } else {
            interstitial = null;
        }
//


//        --------------------------End of Interstitial---------------------------------------------

/////////////////////////////////////  End of all ADs /////////////////////////////////////////////////////




///////////////////////////////// Start of Remove ads //////////////////////////////////////////////////////

        billingClient = BillingClient.newBuilder(this)
                .enablePendingPurchases()
                .setListener(
                        (billingResult, list) -> {

                            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && list != null) {
                                for (Purchase purchase : list) {

                                    Log.d(TAG, "Response is OK");
                                    handlePurchase(purchase);
                                }
                            } else {

                                Log.d(TAG, "Response NOT OK");
                            }
                        }
                ).build();

        //start the connection after initializing the billing client
        establishConnection();

        if(!prefs.isRemoveAd()){
            restorePurchases();
        }

////////////////////////////////End of Remove Ads////////////////////////////////////////////////////////////

        dbHandler = new DBHandler(MainActivity.this);




        ///////// automatic rate us prompt////////06/10/23////AppRater class and custom_app_rating.xml used////
        AppRater.app_launched(this);

        ////////////////////////////////////////////////////



        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        String greeting;
        int image;
        if (hourOfDay >= 4 && hourOfDay < 12) {
            greeting = "Good Morning";
            image = R.drawable.good_morning;
        } else if (hourOfDay >= 12 && hourOfDay < 17) {
            greeting = "Good Afternoon";
            image = R.drawable.good_afternoon;
        } else if (hourOfDay >=17 && hourOfDay < 20){
            greeting = "Good Evening";
            image = R.drawable.good_evening;
        } else {
            greeting = "Good Night";
            image = R.drawable.good_night;
        }





        ActionBar actionBar = getSupportActionBar();
//
// Set the title and subtitle
        if(actionBar != null){
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);

            TextView titleText = findViewById(R.id.titleText);
            ImageView iconImage = findViewById(R.id.iconImage);

            titleText.setText(greeting);
            iconImage.setImageResource(image);
        }











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
                    Toast.makeText(MainActivity.this, "Zero amount can't be saved.", Toast.LENGTH_SHORT).show();

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

                     Toast.makeText(MainActivity.this, "Data has been saved check History.", Toast.LENGTH_SHORT).show();


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



                        MaterialAlertDialogBuilder alertDialoBuider = new MaterialAlertDialogBuilder(view.getContext(),R.style.alertDialog);
                        alertDialoBuider.setTitle("Warning !");
                        alertDialoBuider.setIcon(R.drawable.alert_icon);
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

                                Toast.makeText(MainActivity.this, "Data has been saved again, check History.", Toast.LENGTH_SHORT).show();

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

                        androidx.appcompat.app.AlertDialog alertDialog = alertDialoBuider.create();
                        alertDialog.show();
                        alertDialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#1da1f3"));
                        alertDialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#8899a6"));


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

                         Toast.makeText(MainActivity.this, "Data has been saved check History.", Toast.LENGTH_SHORT).show();


                        try {
                            tabLayout.getTabAt(2).getOrCreateBadge().setVisible(true);
                            tabLayout.getTabAt(2).getOrCreateBadge().setBackgroundColor(Color.parseColor("#1da1f3"));
                        } catch (NullPointerException ignored) {
                        }

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

                        Toast.makeText(MainActivity.this, "Playing total amount.", Toast.LENGTH_SHORT).show();

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

                        Toast.makeText(MainActivity.this, "Can't play in silent mode.", Toast.LENGTH_SHORT).show();

                        break;

                    case AudioManager.RINGER_MODE_VIBRATE:

                        Toast.makeText(MainActivity.this, "Can't play in Vibration mode.", Toast.LENGTH_SHORT).show();

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
                    Toast.makeText(MainActivity.this, "Zero amount can't be shared.", Toast.LENGTH_SHORT).show();

                }

            }
        });


        resetButtonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                MaterialAlertDialogBuilder alertDialoBuider = new MaterialAlertDialogBuilder(MainActivity.this,R.style.alertDialog);
                alertDialoBuider.setTitle("Confirm Reset!");
                alertDialoBuider.setIcon(R.drawable.alert_icon);
                alertDialoBuider.setMessage("Are you sure you want to reset?");

                alertDialoBuider.setPositiveButton("Reset!", new DialogInterface.OnClickListener() {

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

                androidx.appcompat.app.AlertDialog alertDialog = alertDialoBuider.create();
                alertDialog.show();
                alertDialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#1da1f3"));
                alertDialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#8899a6"));


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
                    Toast.makeText(MainActivity.this, "Amount copied to clipboard.", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "Zero amount can't be copied.", Toast.LENGTH_SHORT).show();


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
//                        startActivity(new Intent(getApplicationContext(), Gst.class));
                        overridePendingTransition(R.anim.transparent, R.anim.fade_out);
                        return true;
                    case R.id.totalPaisa:
                        return true;

                    case R.id.simpleCalc:
                        startActivity(new Intent(getApplicationContext(), CalcLite.class));
                        overridePendingTransition(0, 0);
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


                    if(adLoaded){
                        nativeAdStyle();

                    }

                    if(adLoaded2nd){
                        nativeAdStyle2();

                    }

                    if(adLoaded3rd){
                        nativeAdStyle3();
                    }

                    if(adLoaded4th){
                        nativeAdStyle4();
                    }




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
                    if(adLoaded){
                        nativeAdStyle();

                    }

                    if(adLoaded2nd){
                        nativeAdStyle2();

                    }

                    if(adLoaded3rd){
                        nativeAdStyle3();
                    }

                    if(adLoaded4th){
                        nativeAdStyle4();
                    }

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


        switchCompatIncrementDecrement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {

                    toggleStatusIncrementDecrement = true;


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Check if the fragment is ready (you can replace this condition)


                            if(CalculationFragment.isCalculationFragmentReady){

                                CalculationFragment.plus500.setVisibility(View.VISIBLE);
                                CalculationFragment.plus200.setVisibility(View.VISIBLE);
                                CalculationFragment.plus100.setVisibility(View.VISIBLE);
                                CalculationFragment.plus50.setVisibility(View.VISIBLE);
                                CalculationFragment.plus20.setVisibility(View.VISIBLE);
                                CalculationFragment.plus10.setVisibility(View.VISIBLE);
                                CalculationFragment.plus5.setVisibility(View.VISIBLE);
                                CalculationFragment.plus20coin.setVisibility(View.VISIBLE);
                                CalculationFragment.plus10coin.setVisibility(View.VISIBLE);
                                CalculationFragment.plus5coin.setVisibility(View.VISIBLE);
                                CalculationFragment.plus2coin.setVisibility(View.VISIBLE);
                                CalculationFragment.plus1coin.setVisibility(View.VISIBLE);
                                CalculationFragment.plusExtraCoin.setVisibility(View.VISIBLE);



                                CalculationFragment.minus500.setVisibility(View.VISIBLE);
                                CalculationFragment.minus200.setVisibility(View.VISIBLE);
                                CalculationFragment.minus100.setVisibility(View.VISIBLE);
                                CalculationFragment.minus50.setVisibility(View.VISIBLE);
                                CalculationFragment.minus20.setVisibility(View.VISIBLE);
                                CalculationFragment.minus10.setVisibility(View.VISIBLE);
                                CalculationFragment.minus5.setVisibility(View.VISIBLE);
                                CalculationFragment.minus20coin.setVisibility(View.VISIBLE);
                                CalculationFragment.minus10coin.setVisibility(View.VISIBLE);
                                CalculationFragment.minus5coin.setVisibility(View.VISIBLE);
                                CalculationFragment.minus2coin.setVisibility(View.VISIBLE);
                                CalculationFragment.minus1coin.setVisibility(View.VISIBLE);
                                CalculationFragment.minusExtraCoin.setVisibility(View.VISIBLE);

                            }


                        }
                    }, 1000);







                } else {

                    toggleStatusIncrementDecrement = false;


                    CalculationFragment.plus500.setVisibility(View.GONE);
                    CalculationFragment.plus200.setVisibility(View.GONE);
                    CalculationFragment.plus100.setVisibility(View.GONE);
                    CalculationFragment.plus50.setVisibility(View.GONE);
                    CalculationFragment.plus20.setVisibility(View.GONE);
                    CalculationFragment.plus10.setVisibility(View.GONE);
                    CalculationFragment.plus5.setVisibility(View.GONE);
                    CalculationFragment.plus20coin.setVisibility(View.GONE);
                    CalculationFragment.plus10coin.setVisibility(View.GONE);
                    CalculationFragment.plus5coin.setVisibility(View.GONE);
                    CalculationFragment.plus2coin.setVisibility(View.GONE);
                    CalculationFragment.plus1coin.setVisibility(View.GONE);
                    CalculationFragment.plusExtraCoin.setVisibility(View.GONE);

                    CalculationFragment.minus500.setVisibility(View.GONE);
                    CalculationFragment.minus200.setVisibility(View.GONE);
                    CalculationFragment.minus100.setVisibility(View.GONE);
                    CalculationFragment.minus50.setVisibility(View.GONE);
                    CalculationFragment.minus20.setVisibility(View.GONE);
                    CalculationFragment.minus10.setVisibility(View.GONE);
                    CalculationFragment.minus5.setVisibility(View.GONE);
                    CalculationFragment.minus20coin.setVisibility(View.GONE);
                    CalculationFragment.minus10coin.setVisibility(View.GONE);
                    CalculationFragment.minus5coin.setVisibility(View.GONE);
                    CalculationFragment.minus2coin.setVisibility(View.GONE);
                    CalculationFragment.minus1coin.setVisibility(View.GONE);
                    CalculationFragment.minusExtraCoin.setVisibility(View.GONE);

                }
            }
        });

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


        switchCompatKeepScreenOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {

                    toggleStatusKeepScreenOn = true;
                    // it is used after getting feedback of a user on total paisa; but it was used without switch button now switch is added on 06/10/23 amrit hostel
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


                } else {

                    toggleStatusKeepScreenOn = false;
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


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


//                                Toast.makeText(MainActivity.this, "i am here", Toast.LENGTH_SHORT).show();

                                /////// ad removed from exit and put here on 27-09-23//////////



                                if (interstitial != null) {

                                    if(!isInterstitialAdshown){
                                        interstitial.show(MainActivity.this);

                                        interstitial.setFullScreenContentCallback(new FullScreenContentCallback() {
                                            @Override
                                            public void onAdClicked() {
                                                // Called when a click is recorded for an ad.

                                            }

                                            @Override
                                            public void onAdDismissedFullScreenContent() {
                                                // Called when ad is dismissed.
                                                interstitial = null;
//                                            MainActivity.super.onBackPressed();
                                            }

                                            @Override
                                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                                // Called when ad fails to show.
                                                interstitial = null;
//                                            MainActivity.super.onBackPressed();

                                            }

                                            @Override
                                            public void onAdImpression() {
                                                // Called when an impression is recorded for an ad.
                                            }

                                            @Override
                                            public void onAdShowedFullScreenContent() {
                                                // Called when ad is shown.

                                            }
                                        });
                                        isInterstitialAdshown = true;
                                    }

                                }




                            } catch (NullPointerException ignored) {}

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


                                    MaterialAlertDialogBuilder alertDialoBuider = new MaterialAlertDialogBuilder(MainActivity.this,R.style.alertDialog);
                                    alertDialoBuider.setTitle("Total Paisa is Locked");
                                    alertDialoBuider.setIcon(R.drawable.warning);
                                    alertDialoBuider.setMessage("Too many failed attempts, Now Lock your device and then unlock again.");



                                    alertDialoBuider.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                            dialogInterface.cancel();
                                            finish();

                                        }

                                    });



                                    androidx.appcompat.app.AlertDialog alertDialog = alertDialoBuider.create();
                                    alertDialog.show();
                                    alertDialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#1da1f3"));
                                    alertDialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#8899a6"));
                                    alertDialog.setCanceledOnTouchOutside(false);
                                    alertDialog.setCancelable(false);

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

        if (switchCompatIncrementDecrement.isChecked()) {
            toggleStatusIncrementDecrement = true;
            SharedPreferences sharedPreferences = getSharedPreferences("toggleStatusIncrementDecrement", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.putBoolean("boolean", true);
            editor.commit();

        } else {
            toggleStatusIncrementDecrement = false;
            SharedPreferences sharedPreferences = getSharedPreferences("toggleStatusIncrementDecrement", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
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


        if (switchCompatKeepScreenOn.isChecked()) {
            toggleStatusKeepScreenOn = true;
            SharedPreferences sharedPreferences = getSharedPreferences("toggleStatusKeepScreenOn", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.putBoolean("boolean", true);
            editor.commit();

        } else {
            toggleStatusKeepScreenOn = false;
            SharedPreferences sharedPreferences = getSharedPreferences("toggleStatusKeepScreenOn", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("boolean", false);
            editor.commit();
        }


    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

        if (switchCompatIncrementDecrement.isChecked()) {
            toggleStatusIncrementDecrement = true;
            SharedPreferences sharedPreferences = getSharedPreferences("toggleStatusIncrementDecrement", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.putBoolean("boolean", true);
            editor.commit();

        } else {
            toggleStatusIncrementDecrement = false;
            SharedPreferences sharedPreferences = getSharedPreferences("toggleStatusIncrementDecrement", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
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

        if (switchCompatKeepScreenOn.isChecked()) {
            toggleStatusKeepScreenOn = true;
            SharedPreferences sharedPreferences = getSharedPreferences("toggleStatusKeepScreenOn", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.putBoolean("boolean", true);
            editor.commit();

        } else {
            toggleStatusKeepScreenOn = false;
            SharedPreferences sharedPreferences = getSharedPreferences("toggleStatusKeepScreenOn", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("boolean", false);
            editor.commit();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

        // It is used for pending purchases;
        billingClient.queryPurchasesAsync(
                QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.INAPP).build(),
                (billingResult, list) -> {
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        for (Purchase purchase : list) {
                            if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED && !purchase.isAcknowledged()) {
                                handlePurchase(purchase);
                            }
                        }
                    }
                }
        );



        if(adLoaded){
            nativeAdStyle();

        }

        if(adLoaded2nd){
            nativeAdStyle2();

        }

        if(adLoaded3rd){
            nativeAdStyle3();
        }

        SharedPreferences getShared = getSharedPreferences("toggleStatusIncrementDecrement", MODE_PRIVATE);
        toggleStatusIncrementDecrement = getShared.getBoolean("boolean", true);

        getShared = getSharedPreferences("toggleStatusFingerPrint", MODE_PRIVATE);
        toggleStatusFingerPrint = getShared.getBoolean("boolean", false);

        getShared = getSharedPreferences("toggleStatusTellerSound", MODE_PRIVATE);
        toggleStatusTellerSound = getShared.getBoolean("boolean", true);

        getShared = getSharedPreferences("toggleStatusKeepScreenOn", MODE_PRIVATE);
        toggleStatusKeepScreenOn = getShared.getBoolean("boolean", true);


        if (toggleStatusIncrementDecrement) {
            switchCompatIncrementDecrement.setChecked(true);
        } else {
            switchCompatIncrementDecrement.setChecked(false);
        }


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


        if (toggleStatusKeepScreenOn) {
            switchCompatKeepScreenOn.setChecked(true);
        } else {
            switchCompatKeepScreenOn.setChecked(false);
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
                        overridePendingTransition(R.anim.transparent, R.anim.fade_out);
                        return true;
                    case R.id.totalPaisa:
//                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.simpleCalc:
                        startActivity(new Intent(getApplicationContext(),CalcLite.class));
                        overridePendingTransition(0,0);
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

            } catch (Exception ignored) {}

            MaterialAlertDialogBuilder alertDialoBuider = new MaterialAlertDialogBuilder(MainActivity.this,R.style.alertDialog);
            alertDialoBuider.setTitle("Confirm Reset!");
            alertDialoBuider.setIcon(R.drawable.alert_icon);
            alertDialoBuider.setMessage("Are you sure you want to reset?");

            alertDialoBuider.setPositiveButton("Reset!", new DialogInterface.OnClickListener() {

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

            androidx.appcompat.app.AlertDialog alertDialog = alertDialoBuider.create();
            alertDialog.show();
            alertDialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#1da1f3"));
            alertDialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#8899a6"));


        } else if (id == R.id.ShareButton) {

            try {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            } catch (Exception e) {}


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
                 Toast.makeText(MainActivity.this, "Zero amount can't be shared.", Toast.LENGTH_SHORT).show();

            }

        } else if (id == R.id.RemoveAd) {


            try {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);

            } catch (Exception ignored) {}


            GetSingleInAppDetail();

        } else if (id == R.id.DeleteAll) {
//            it took 2 days to find this out myself this is the fastest way to delete database without adapter array list etc :)

            if(ArrayListSize > 0){

                MaterialAlertDialogBuilder alertDialoBuider = new MaterialAlertDialogBuilder(MainActivity.this,R.style.alertDialog);
                alertDialoBuider.setTitle("Confirm Erase!");
                alertDialoBuider.setIcon(R.drawable.warning);
                alertDialoBuider.setMessage("If you erase the data, Your history will be cleared.");

                alertDialoBuider.setPositiveButton("Erase All!", new DialogInterface.OnClickListener() {

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
                                    snackbar.setText("Erasing all data in  " + millisUntilFinished / 1000 + "  Second.");
                                    snackbar.setTextColor(Color.parseColor("#ff0000"));
                                } else {
                                    snackbar.setText("Erasing all data in  " + millisUntilFinished / 1000 + "  Seconds.");
                                }

                            }

                            @Override
                            public void onFinish() {

                                save_button_was_pressed = true;

                                deleteDatabase("currencyDb");
                                viewPager.setCurrentItem(0);


                                Toast.makeText(MainActivity.this, "History has been cleared.", Toast.LENGTH_SHORT).show();

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

                androidx.appcompat.app.AlertDialog alertDialog = alertDialoBuider.create();
                alertDialog.show();
                alertDialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#A52121"));
                alertDialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#8899a6"));


            }else {

                Toast.makeText(MainActivity.this, "History is already empty.", Toast.LENGTH_SHORT).show();

            }


        }
        return super.onOptionsItemSelected(item);
    }


    //    It took 2 days to set fragment to default on back pressed. Before I was messed up with tab layout fragment backpressed.
    @Override
    public void onBackPressed() {


        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {


            if (viewPager.getCurrentItem() == 0) {

                final MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(MainActivity.this, R.style.alertDialog);
                dialogBuilder.setTitle("Confirm Exit !");
                dialogBuilder.setIcon(R.drawable.alert_24);
                dialogBuilder.setMessage("Are you sure you want to exit ?");


                LayoutInflater inflater = this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.native_alert, null);
                dialogBuilder.setView(dialogView);


                TextView tvExit = (TextView) dialogView.findViewById(R.id.tv_exit);
                TextView tvCancel = (TextView) dialogView.findViewById(R.id.tv_cancel);
//                template3 = (TemplateView) dialogView.findViewById(R.id.nativeAd3);
                androidx.appcompat.app.AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();

                tvExit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (interstitial != null) {


                            if(!isInterstitialAdshown){
                                interstitial.show(MainActivity.this);

                                interstitial.setFullScreenContentCallback(new FullScreenContentCallback() {
                                    @Override
                                    public void onAdClicked() {
                                        // Called when a click is recorded for an ad.

                                    }

                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        // Called when ad is dismissed.
                                        interstitial = null;
                                        MainActivity.super.onBackPressed();
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        // Called when ad fails to show.
                                        interstitial = null;
                                        MainActivity.super.onBackPressed();
                                    }

                                    @Override
                                    public void onAdImpression() {
                                        // Called when an impression is recorded for an ad.
                                    }

                                    @Override
                                    public void onAdShowedFullScreenContent() {
                                        // Called when ad is shown.

                                    }
                                });

                                isInterstitialAdshown = true;

                            }



                        } else {

                            MainActivity.super.onBackPressed();

                        }


                    }
                });

                tvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });



//                if(!prefs.isRemoveAd()) {
//                    ColorDrawable colorDrawable3 = new ColorDrawable(getColor(R.color.light_blue));
//                    ColorDrawable buttonBackground3 = new ColorDrawable(getColor(R.color.highlight_blue));
//                    AdLoader adLoader3 = new AdLoader.Builder(this, "ca-app-pub-2808567025402378/6898118013")
//                            .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
//                                @Override
//                                public void onNativeAdLoaded(NativeAd nativeAd3) {
//
//                                    NativeTemplateStyle styles3 = new
//                                            NativeTemplateStyle.Builder().
//                                            withCallToActionBackgroundColor(buttonBackground3).
//                                            withSecondaryTextTypefaceColor(getColor(R.color.light_white)).
//                                            withPrimaryTextTypefaceColor(getColor(R.color.light_white)).
//                                            withMainBackgroundColor(colorDrawable3).build();
//
//
//                                    if(template3 != null){
//                                        template3.setNativeAd(nativeAd3);
//                                        template3.setStyles(styles3);
//                                        template3.setVisibility(View.VISIBLE);
//
//                                    }
//
//                                    if (isDestroyed()) {
//                                        nativeAd3.destroy();
//                                        adLoaded3rd = false;
//                                        return;
//                                    }
//
//                                    adLoaded3rd = true;
//
//
//                                }
//                            })
//                            .withAdListener(new AdListener() {
//                                @Override
//                                public void onAdFailedToLoad(LoadAdError adError) {
//
//                                    try {
//                                        template3.setVisibility(View.GONE);
//
//
//                                    } catch (NullPointerException ignored) {
//                                    }
//
//
//
//                                    adLoaded3rd = false;
//                                }
//                            })
//                            .withNativeAdOptions(new NativeAdOptions.Builder()
//                                    .build())
//                            .build();
//                    adLoader3.loadAd(new AdRequest.Builder().build());
//                }

            } else {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 2);
            }
        }


    }


    public static void hideSoftKeyboard(MainActivity activity) {
        // it was getting crashed

        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
                     Toast.makeText(MainActivity.this, "Gmail App is not installed.", Toast.LENGTH_SHORT).show();

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

            case R.id.easy_loan:

                uri = Uri.parse("https://play.google.com/store/apps/details?id=com.loan.interest.rate.calculator.simple");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

public void nativeAdStyle(){


    ColorDrawable buttonBackground =  new ColorDrawable(getColor(R.color.highlight_blue));
    ColorDrawable colorDrawable = new ColorDrawable(getColor(R.color.light_blue));

    NativeTemplateStyle styles = new
            NativeTemplateStyle.Builder().
            withCallToActionBackgroundColor(buttonBackground).
            withSecondaryTextTypefaceColor(getColor(R.color.light_white)).
            withPrimaryTextTypefaceColor(getColor(R.color.light_white)).
            withMainBackgroundColor(colorDrawable).build();

    if(adLoaded){
        if(template != null){
            template.setStyles(styles);

        }
    }

}


    public void nativeAdStyle2(){
        ColorDrawable buttonBackground2 =  new ColorDrawable(getColor(R.color.highlight_blue));
        ColorDrawable colorDrawable2 = new ColorDrawable(getColor(R.color.light_blue));

        NativeTemplateStyle styles2 = new
                NativeTemplateStyle.Builder().
                withCallToActionBackgroundColor(buttonBackground2).
                withSecondaryTextTypefaceColor(getColor(R.color.light_white)).
                withPrimaryTextTypefaceColor(getColor(R.color.light_white)).
                withMainBackgroundColor(colorDrawable2).build();

        if(adLoaded2nd){
            if(template2 != null){
                template2.setStyles(styles2);
            }
        }

    }



    public void nativeAdStyle3(){
        ColorDrawable buttonBackground3 =  new ColorDrawable(getColor(R.color.highlight_blue));
        ColorDrawable colorDrawable3 = new ColorDrawable(getColor(R.color.light_blue));

        NativeTemplateStyle styles3 = new
                NativeTemplateStyle.Builder().
                withCallToActionBackgroundColor(buttonBackground3).
                withSecondaryTextTypefaceColor(getColor(R.color.light_white)).
                withPrimaryTextTypefaceColor(getColor(R.color.light_white)).
                withMainBackgroundColor(colorDrawable3).build();

        if(adLoaded3rd){
            if(template3 != null){
                template3.setStyles(styles3);

            }
        }



    }

    public void nativeAdStyle4(){
        ColorDrawable buttonBackground4 =  new ColorDrawable(getColor(R.color.highlight_blue));
        ColorDrawable colorDrawable4 = new ColorDrawable(getColor(R.color.light_blue));

        NativeTemplateStyle styles4 = new
                NativeTemplateStyle.Builder().
                withCallToActionBackgroundColor(buttonBackground4).
                withSecondaryTextTypefaceColor(getColor(R.color.light_white)).
                withPrimaryTextTypefaceColor(getColor(R.color.light_white)).
                withMainBackgroundColor(colorDrawable4).build();

        if(adLoaded4th){
            if(template4 != null){
                template4.setStyles(styles4);

            }
        }



    }

    void establishConnection() {
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {

                    // The BillingClient is ready. You can query purchases here.
                    //Use any of function below to get details upon successful connection

                    // GetSingleInAppDetail();
                    //GetListsInAppDetail();

                    Log.d(TAG, "Connection Established");
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
                Log.d(TAG, "Connection NOT Established");
                establishConnection();
            }
        });
    }



    void GetSingleInAppDetail() {
        ArrayList<QueryProductDetailsParams.Product> productList = new ArrayList<>();

        productList.add(
                QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(PRODUCT_PREMIUM)
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build()
        );

        QueryProductDetailsParams params = QueryProductDetailsParams.newBuilder()
                .setProductList(productList)
                .build();

        billingClient.queryProductDetailsAsync(params, new ProductDetailsResponseListener() {
            @Override
            public void onProductDetailsResponse(@NonNull BillingResult billingResult, @NonNull List<ProductDetails> list) {

                //Do Anything that you want with requested product details

                //Calling this function here so that once products are verified we can start the purchase behavior.
                //You can save this detail in separate variable or list to call them from any other location
                //Create another function if you want to call this in establish connections' success state

                if(list.size()>0){
                    // Before I was not using above condition to check list so it was crashing in virtual deives.
                    LaunchPurchaseFlow(list.get(0));
                }

            }
        });
    }

    void GetListsInAppDetail() {
        ArrayList<QueryProductDetailsParams.Product> productList = new ArrayList<>();

        for (String ids : purchaseItemIDs) {
            productList.add(
                    QueryProductDetailsParams.Product.newBuilder()
                            .setProductId(ids)
                            .setProductType(BillingClient.ProductType.INAPP)
                            .build());
        }

        QueryProductDetailsParams params = QueryProductDetailsParams.newBuilder()
                .setProductList(productList)
                .build();

        billingClient.queryProductDetailsAsync(params, new ProductDetailsResponseListener() {
            @Override
            public void onProductDetailsResponse(@NonNull BillingResult billingResult, @NonNull List<ProductDetails> list) {

                for (ProductDetails li : list) {
                    Log.d(TAG, "IN APP item Price" + li.getOneTimePurchaseOfferDetails().getFormattedPrice());
                }
                //Do Anything that you want with requested product details
            }
        });
    }





    void LaunchPurchaseFlow(ProductDetails productDetails) {
        ArrayList<BillingFlowParams.ProductDetailsParams> productList = new ArrayList<>();

        productList.add(
                BillingFlowParams.ProductDetailsParams.newBuilder()
                        .setProductDetails(productDetails)
                        .build());

        BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                .setProductDetailsParamsList(productList)
                .build();

        billingClient.launchBillingFlow(this, billingFlowParams);
    }




    void handlePurchase(Purchase purchases) {
        if (!purchases.isAcknowledged()) {
            billingClient.acknowledgePurchase(AcknowledgePurchaseParams
                    .newBuilder()
                    .setPurchaseToken(purchases.getPurchaseToken())
                    .build(), billingResult -> {

                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {

                    for (String pur : purchases.getProducts()) {
                        if (pur.equalsIgnoreCase(PRODUCT_PREMIUM)) {

                            Log.d("TAG", "vibhu p success");
                            prefs.setIsRemoveAd(true);

                            final Handler handler = new Handler(Looper.getMainLooper());
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    snackbar = Snackbar.make(findViewById(R.id.botton_navigation), "Congratulations!, Enjoy the Ads free Experience.",
                                            Snackbar.LENGTH_INDEFINITE);
                                    snackbar.setAction("Restart!", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            triggerRebirth(getApplicationContext());

                                        }
                                    });

                                    snackbar.setActionTextColor(Color.parseColor("#3bd16f"));
                                    snackbar.setTextColor(Color.parseColor("#ffffff"));
                                    snackbar.setBackgroundTint(Color.parseColor("#10171f"));
                                    snackbar.show();


                                }
                            }, 2000);

                        }
                    }
                }
            });
        }
    }



    void restorePurchases() {

        billingClient = BillingClient.newBuilder(this).enablePendingPurchases().setListener((billingResult, list) -> {
        }).build();
        final BillingClient finalBillingClient = billingClient;
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingServiceDisconnected() {
            }

            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {

                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    finalBillingClient.queryPurchasesAsync(
                            QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.INAPP).build(), (billingResult1, list) -> {
                                if (billingResult1.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                    if (list.size() > 0) {

                                        Log.d("TAG", "IN APP SUCCESS RESTORE: " + list);
                                        for (int i = 0; i < list.size(); i++) {

                                            if (list.get(i).getProducts().contains(PRODUCT_PREMIUM)) {

                                                prefs.setIsRemoveAd(true);
                                                Log.d("TAG", "Product id " + PRODUCT_PREMIUM + " will restore here");



                                                final Handler handler = new Handler(Looper.getMainLooper());
                                                handler.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {

                                                        snackbar = Snackbar.make(findViewById(R.id.botton_navigation), "Welcome Back!, Enjoy the Ads Free Experience Again.",
                                                                Snackbar.LENGTH_INDEFINITE);
                                                        snackbar.setAction("Restart!", new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View view) {

                                                                triggerRebirth(getApplicationContext());

                                                            }
                                                        });

                                                        snackbar.setActionTextColor(Color.parseColor("#3bd16f"));
                                                        snackbar.setTextColor(Color.parseColor("#ffffff"));
                                                        snackbar.setBackgroundTint(Color.parseColor("#10171f"));
                                                        snackbar.show();

                                                    }
                                                }, 5000);

                                            }

                                        }
                                    } else {

                                        prefs.setIsRemoveAd(false);

                                        Log.d("TAG", "In APP Not Found To Restore");
                                    }
                                }
                            });
                }
            }
        });
    }

    public static void triggerRebirth(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(context.getPackageName());
        assert intent != null;
        ComponentName componentName = intent.getComponent();
        Intent mainIntent = Intent.makeRestartActivityTask(componentName);
        context.startActivity(mainIntent);
        Runtime.getRuntime().exit(0);
    }

}