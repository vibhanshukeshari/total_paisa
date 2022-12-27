package com.vibhunorby.totalpaisa;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;

public class CurrencyRVAdapter extends RecyclerView.Adapter<CurrencyRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<CurrencyModal> currencyModalArrayList;
    private Context context;


    // constructor
    public CurrencyRVAdapter(ArrayList<CurrencyModal> currencyModalArrayList, Context context) {
        this.currencyModalArrayList = currencyModalArrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_rv_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        CurrencyModal modal = currencyModalArrayList.get(position);
        holder.currency2000TV.setText(modal.getCurrency2000());
        holder.currency500TV.setText(modal.getCurrency500());
        holder.currency200TV.setText(modal.getCurrency200());
        holder.currency100TV.setText(modal.getCurrency100());
        holder.currency50TV.setText(modal.getCurrency50());
        holder.currency20TV.setText(modal.getCurrency20());
        holder.currency10TV.setText(modal.getCurrency10());
        holder.currency5TV.setText(modal.getCurrency5());
        holder.currency20CoinTV.setText(modal.getCurrency20_coin());
        holder.currency10CoinTV.setText(modal.getCurrency10_coin());
        holder.currency5CoinTV.setText(modal.getCurrency5_coin());
        holder.currency2CoinTV.setText(modal.getCurrency2_coin());
        holder.currency1CoinTV.setText(modal.getCurrency1_coin());
        holder.currencyExtraCoinTV.setText(modal.getCurrency_extra());
        holder.resultTV.setText(modal.getResult());
        holder.payeeName.setText(modal.getPayee());
        holder.date.setText(modal.getdate());
        holder.time.setText(modal.getMytime());
        holder.day.setText(modal.getMyday());
        holder.notes.setText(modal.getNotes());
        holder.coins.setText(modal.getCoins());

        holder.payeeName.setSelected(true);
        holder.date.setSelected(true);
        holder.time.setSelected(true);

        holder.linear2000.setVisibility(View.GONE);
        holder.linear200.setVisibility(View.GONE);
        holder.linear500.setVisibility(View.GONE);
        holder.linear100.setVisibility(View.GONE);
        holder.linear50.setVisibility(View.GONE);
        holder.linear20.setVisibility(View.GONE);
        holder.linear10.setVisibility(View.GONE);
        holder.linear5.setVisibility(View.GONE);
        holder.linear20Coin.setVisibility(View.GONE);
        holder.linear10Coin.setVisibility(View.GONE);
        holder.linear5Coin.setVisibility(View.GONE);
        holder.linear2Coin.setVisibility(View.GONE);
        holder.linear1Coin.setVisibility(View.GONE);
        holder.linearExtraCoin.setVisibility(View.GONE);


        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialoBuider = new AlertDialog.Builder(context, R.style.alertDialog);
                alertDialoBuider.setTitle("Delete ?");
                alertDialoBuider.setIcon(R.drawable.warning);
                alertDialoBuider.setMessage("Are you sure want to delete this data ?");

                alertDialoBuider.setPositiveButton(Html.fromHtml("<font color='#ff0000'>Delete</font>"), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast = Toast.makeText(context, "Data has been deleted.", Toast.LENGTH_SHORT);
                        View view1 = toast.getView();

                        try {

                            TextView textView = toast.getView().findViewById(android.R.id.message);
                            textView.setTextColor(Color.parseColor("#ffffff"));

                        }catch (NullPointerException ignored){ }


                        try {
                            assert view1 != null;
                            view1.getBackground().setColorFilter(Color.parseColor("#10171f"), PorterDuff.Mode.SRC_IN);
                        } catch (NullPointerException ignored) { }
                        toast.show();

                        DBHandler db = new DBHandler(context);

                        String currency2000 = holder.currency2000TV.getText().toString();
                        String currency500 = holder.currency500TV.getText().toString();
                        String currency200 = holder.currency200TV.getText().toString();
                        String currency100 = holder.currency100TV.getText().toString();
                        String currency50 = holder.currency50TV.getText().toString();
                        String currency20 = holder.currency20TV.getText().toString();
                        String currency10 = holder.currency10TV.getText().toString();
                        String currency5 = holder.currency5TV.getText().toString();
                        String currency20_coin = holder.currency20CoinTV.getText().toString();
                        String currency10_coin = holder.currency10CoinTV.getText().toString();
                        String currency5_coin = holder.currency5CoinTV.getText().toString();
                        String currency2_coin = holder.currency2CoinTV.getText().toString();
                        String currency1_coin = holder.currency1CoinTV.getText().toString();
                        String currency_extra_coin = holder.currencyExtraCoinTV.getText().toString();
                        String result = holder.resultTV.getText().toString();
                        String payee = holder.payeeName.getText().toString();
                        String date = holder.date.getText().toString();
                        String time = holder.time.getText().toString();
                        String day = holder.day.getText().toString();
                        String notes = holder.notes.getText().toString();
                        String coins = holder.coins.getText().toString();

                        db.deleteCurrency(currency2000, currency500, currency200, currency100
                                , currency50, currency20, currency10, currency5, currency20_coin, currency10_coin, currency5_coin, currency2_coin
                                , currency1_coin, currency_extra_coin, result, payee, date, time, day, notes, coins);

                        db.close();

//                Took two days, before i was using visibility.gone;
                        currencyModalArrayList.remove(position);
                        notifyItemRemoved(position);
                        notifyDataSetChanged();

                    }

                });


                alertDialoBuider.setNegativeButton(Html.fromHtml("<font color='#ffffff'>Cancel</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                    }


                });
                AlertDialog alertDialog = alertDialoBuider.create();
                alertDialog.show();

            }
        });


        holder.viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (holder.viewMore.isChecked()) {
                    if (!holder.currency2000TV.getText().toString().equals("0")) {

                        holder.linear2000.setVisibility(View.VISIBLE);
                        holder.editText2000Rv.setText(String.valueOf(Integer.parseInt(holder.currency2000TV.getText().toString().replaceAll(",", "")) / 2000));


                    }

                    if (!holder.currency500TV.getText().toString().equals("0")) {

                        holder.linear500.setVisibility(View.VISIBLE);
                        holder.editText500Rv.setText(String.valueOf(Integer.parseInt(holder.currency500TV.getText().toString().replaceAll(",", "")) / 500));


                    }

                    if (!holder.currency200TV.getText().toString().equals("0")) {

                        holder.linear200.setVisibility(View.VISIBLE);
                        holder.editText200Rv.setText(String.valueOf(Integer.parseInt(holder.currency200TV.getText().toString().replaceAll(",", "")) / 200));

                    }

                    if (!holder.currency100TV.getText().toString().equals("0")) {

                        holder.linear100.setVisibility(View.VISIBLE);
                        holder.editText100Rv.setText(String.valueOf(Integer.parseInt(holder.currency100TV.getText().toString().replaceAll(",", "")) / 100));

                    }

                    if (!holder.currency50TV.getText().toString().equals("0")) {

                        holder.linear50.setVisibility(View.VISIBLE);
                        holder.editText50Rv.setText(String.valueOf(Integer.parseInt(holder.currency50TV.getText().toString().replaceAll(",", "")) / 50));

                    }

                    if (!holder.currency20TV.getText().toString().equals("0")) {

                        holder.linear20.setVisibility(View.VISIBLE);
                        holder.editText20Rv.setText(String.valueOf(Integer.parseInt(holder.currency20TV.getText().toString().replaceAll(",", "")) / 20));
                    }

                    if (!holder.currency10TV.getText().toString().equals("0")) {

                        holder.linear10.setVisibility(View.VISIBLE);
                        holder.editText10Rv.setText(String.valueOf(Integer.parseInt(holder.currency10TV.getText().toString().replaceAll(",", "")) / 10));

                    }

                    if (!holder.currency5TV.getText().toString().equals("0")) {

                        holder.linear5.setVisibility(View.VISIBLE);
                        holder.editText5Rv.setText(String.valueOf(Integer.parseInt(holder.currency5TV.getText().toString().replaceAll(",", "")) / 5));

                    }


                    if (!holder.currency20CoinTV.getText().toString().equals("0")) {

                        holder.linear20Coin.setVisibility(View.VISIBLE);
                        holder.editText20CoinRv.setText(String.valueOf(Integer.parseInt(holder.currency20CoinTV.getText().toString().replaceAll(",", "")) / 20));

                    }



                    if (!holder.currency10CoinTV.getText().toString().equals("0")) {

                        holder.linear10Coin.setVisibility(View.VISIBLE);
                        holder.editText10CoinRv.setText(String.valueOf(Integer.parseInt(holder.currency10CoinTV.getText().toString().replaceAll(",", "")) / 10));

                    }

                    if (!holder.currency5CoinTV.getText().toString().equals("0")) {

                        holder.linear5Coin.setVisibility(View.VISIBLE);
                        holder.editText5CoinRv.setText(String.valueOf(Integer.parseInt(holder.currency5CoinTV.getText().toString().replaceAll(",", "")) / 5));

                    }

                    if (!holder.currency2CoinTV.getText().toString().equals("0")) {

                        holder.linear2Coin.setVisibility(View.VISIBLE);
                        holder.editText2CoinRv.setText(String.valueOf(Integer.parseInt(holder.currency2CoinTV.getText().toString().replaceAll(",", "")) / 2));

                    }

                    if (!holder.currency1CoinTV.getText().toString().equals("0")) {

                        holder.linear1Coin.setVisibility(View.VISIBLE);
                        holder.editText1CoinRv.setText(String.valueOf(Integer.parseInt(holder.currency1CoinTV.getText().toString().replaceAll(",", ""))));

                    }

                    if (!holder.currencyExtraCoinTV.getText().toString().equals("0")) {

                        holder.linearExtraCoin.setVisibility(View.VISIBLE);
                        holder.editTextExtraCoinRv.setText(String.valueOf(Integer.parseInt(holder.currencyExtraCoinTV.getText().toString().replaceAll(",", ""))));

                    }

                } else {
                    holder.linear2000.setVisibility(View.GONE);
                    holder.linear200.setVisibility(View.GONE);
                    holder.linear500.setVisibility(View.GONE);
                    holder.linear100.setVisibility(View.GONE);
                    holder.linear50.setVisibility(View.GONE);
                    holder.linear20.setVisibility(View.GONE);
                    holder.linear10.setVisibility(View.GONE);
                    holder.linear5.setVisibility(View.GONE);
                    holder.linear20Coin.setVisibility(View.GONE);
                    holder.linear10Coin.setVisibility(View.GONE);
                    holder.linear5Coin.setVisibility(View.GONE);
                    holder.linear2Coin.setVisibility(View.GONE);
                    holder.linear1Coin.setVisibility(View.GONE);
                    holder.linearExtraCoin.setVisibility(View.GONE);

                }


            }
        });


        holder.copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String myTextViewInNumbers = holder.resultTV.getText().toString();

                ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Currency_info", myTextViewInNumbers);
                clipboardManager.setPrimaryClip(clip);
                Toast toast = Toast.makeText(context, "Amount copied to clipboard.", Toast.LENGTH_SHORT);
                View view1 = toast.getView();

                try {

                    TextView textView = toast.getView().findViewById(android.R.id.message);
                    textView.setTextColor(Color.parseColor("#ffffff"));

                }catch (NullPointerException ignored){ }


                try {
                    assert view1 != null;
                    view1.getBackground().setColorFilter(Color.parseColor("#10171f"), PorterDuff.Mode.SRC_IN);
                } catch (NullPointerException ignored) { }
                toast.show();


            }
        });


        holder.speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toSpeak = holder.resultTV.getText().toString().replaceAll(",", "").replaceAll("₹ ", "");
                final String toSpeakReal = Words.convert(toSpeak) + "rupees only.";

                AudioManager audio = (AudioManager) context.getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
                switch (audio.getRingerMode()) {
                    case AudioManager.RINGER_MODE_NORMAL:


                        Toast toast = Toast.makeText(context, "Playing total amount.", Toast.LENGTH_SHORT);
                        View view1 = toast.getView();

                        try {

                            TextView textView = toast.getView().findViewById(android.R.id.message);
                            textView.setTextColor(Color.parseColor("#ffffff"));

                        }catch (NullPointerException ignored){ }


                        try {
                            assert view1 != null;
                            view1.getBackground().setColorFilter(Color.parseColor("#10171f"), PorterDuff.Mode.SRC_IN);
                        } catch (NullPointerException ignored) { }
                        toast.show();

                        holder.tts = new TextToSpeech(context.getApplicationContext(), new TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int i) {

                                try {
                                    holder.tts.setLanguage(new Locale("hi"));
                                }catch (NullPointerException ignored){ }


                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                                    try {
                                        holder.tts.speak(toSpeakReal, TextToSpeech.QUEUE_FLUSH, null, null);
                                    }catch (NullPointerException ignored){ }

                                } else {

                                    try{
                                        holder.tts.speak(toSpeakReal, TextToSpeech.QUEUE_FLUSH, null);
                                    }catch (NullPointerException ignored){ }

                                }

                            }
                        });

                        break;

                    case AudioManager.RINGER_MODE_SILENT:


                        toast = Toast.makeText(context, "Can't play in silent mode.", Toast.LENGTH_SHORT);
                         view1 = toast.getView();

                        try {

                            TextView textView = toast.getView().findViewById(android.R.id.message);
                            textView.setTextColor(Color.parseColor("#ffffff"));

                        }catch (NullPointerException ignored){ }


                        try {
                            assert view1 != null;
                            view1.getBackground().setColorFilter(Color.parseColor("#10171f"), PorterDuff.Mode.SRC_IN);
                        } catch (NullPointerException ignored) { }
                        toast.show();

                        break;

                    case AudioManager.RINGER_MODE_VIBRATE:


                        toast = Toast.makeText(context, "Can't play in vibration mode.", Toast.LENGTH_SHORT);
                         view1 = toast.getView();

                        try {

                            TextView textView = toast.getView().findViewById(android.R.id.message);
                            textView.setTextColor(Color.parseColor("#ffffff"));

                        }catch (NullPointerException ignored){ }


                        try {
                            assert view1 != null;
                            view1.getBackground().setColorFilter(Color.parseColor("#10171f"), PorterDuff.Mode.SRC_IN);
                        } catch (NullPointerException ignored) { }
                        toast.show();

                        break;


                }


            }
        });



        holder.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!holder.resultTV.getText().toString().replaceAll(",", "").equals("0")) {


                    MainActivity.share_was_pressed = true;

                    String myTextViewWord = Words.convert(holder.resultTV.getText().toString().replaceAll(",", "").replaceAll("₹ ", ""));
                    String myTextView2000 = holder.currency2000TV.getText().toString();
                    String myTextView500 = holder.currency500TV.getText().toString();
                    String myTextView200 = holder.currency200TV.getText().toString();
                    String myTextView100 = holder.currency100TV.getText().toString();
                    String myTextView50 = holder.currency50TV.getText().toString();
                    String myTextView20 = holder.currency20TV.getText().toString();
                    String myTextView10 = holder.currency10TV.getText().toString();
                    String myTextView5 = holder.currency5TV.getText().toString();
                    String myTextViewCoin20 = holder.currency20CoinTV.getText().toString();
                    String myTextViewCoin10 = holder.currency10CoinTV.getText().toString();
                    String myTextViewCoin5 = holder.currency5CoinTV.getText().toString();
                    String myTextViewCoin2 = holder.currency2CoinTV.getText().toString();
                    String myTextViewCoin1 = holder.currency1CoinTV.getText().toString();
                    String myTextViewCoins = holder.currencyExtraCoinTV.getText().toString();
                    String myTextViewInNumbers = holder.resultTV.getText().toString();


                    int myEditText2000 = (Integer.parseInt(holder.currency2000TV.getText().toString().replaceAll(",", "")) / 2000);
                    int myEditText500 = (Integer.parseInt(holder.currency500TV.getText().toString().replaceAll(",", "")) / 500);
                    int myEditText200 = (Integer.parseInt(holder.currency200TV.getText().toString().replaceAll(",", "")) / 200);
                    int myEditText100 = (Integer.parseInt(holder.currency100TV.getText().toString().replaceAll(",", "")) / 100);
                    int myEditText50 = (Integer.parseInt(holder.currency50TV.getText().toString().replaceAll(",", "")) / 50);
                    int myEditText20 = (Integer.parseInt(holder.currency20TV.getText().toString().replaceAll(",", "")) / 20);
                    int myEditText10 = (Integer.parseInt(holder.currency10TV.getText().toString().replaceAll(",", "")) / 10);
                    int myEditText5 = (Integer.parseInt(holder.currency5TV.getText().toString().replaceAll(",", "")) / 5);
                    int myEditTextCoin20 = (Integer.parseInt(holder.currency20CoinTV.getText().toString().replaceAll(",","")) / 20);
                    int myEditTextCoin10 = (Integer.parseInt(holder.currency10CoinTV.getText().toString().replaceAll(",", "")) / 10);
                    int myEditTextCoin5 = (Integer.parseInt(holder.currency5CoinTV.getText().toString().replaceAll(",", "")) / 5);
                    int myEditTextCoin2 = (Integer.parseInt(holder.currency2CoinTV.getText().toString().replaceAll(",", "")) / 2);
                    int myEditTextCoin1 = Integer.parseInt(holder.currency1CoinTV.getText().toString().replaceAll(",", ""));
                    int myEditTextExtraCoin = Integer.parseInt(holder.currencyExtraCoinTV.getText().toString().replaceAll(",", ""));

                    String totalNotesInString = holder.notes.getText().toString();
                    String totalCoinsInString = holder.coins.getText().toString();

                    String date = holder.date.getText().toString();
                    String day = holder.day.getText().toString();
                    String time = holder.time.getText().toString();
                    String payeeName = holder.payeeName.getText().toString();


                    StringBuilder forShare = new StringBuilder();

                    forShare.append("Date :  ");
                    forShare.append(date);
                    forShare.append('\n');
                    forShare.append("Day :  ");
                    forShare.append(day);
                    forShare.append('\n');
                    forShare.append("Time :  ");
                    forShare.append(time);
                    forShare.append('\n');


                    if (payeeName.trim().matches("")) {
                        forShare.append("Payee Name :  Unknown ");
                    } else {

                        forShare.append("Payee Name :  ");
                        forShare.append(payeeName);

                    }

                    forShare.append('\n');
                    forShare.append('\n');

                    forShare.append("◕  Notes :  ");
                    forShare.append('\n');

                    if (!String.valueOf(myEditText2000).equals("0")) {
                        forShare.append("•  2000  x  ");
                        forShare.append(myEditText2000);
                        forShare.append("  =  ₹ ");
                        forShare.append(myTextView2000);
                        forShare.append('\n');
                    }


                    if (!String.valueOf(myEditText500).equals("0")) {
                        forShare.append("•  500    x  ");
                        forShare.append(myEditText500);
                        forShare.append("  =  ₹ ");
                        forShare.append(myTextView500);
                        forShare.append('\n');
                    }


                    if (!String.valueOf(myEditText200).equals("0")) {
                        forShare.append("•  200    x  ");
                        forShare.append(myEditText200);
                        forShare.append("  =  ₹ ");
                        forShare.append(myTextView200);
                        forShare.append('\n');
                    }


                    if (!String.valueOf(myEditText100).equals("0")) {
                        forShare.append("•  100    x  ");
                        forShare.append(myEditText100);
                        forShare.append("  =  ₹ ");
                        forShare.append(myTextView100);
                        forShare.append('\n');
                    }


                    if (!String.valueOf(myEditText50).equals("0")) {
                        forShare.append("•  50      x  ");
                        forShare.append(myEditText50);
                        forShare.append("  =  ₹ ");
                        forShare.append(myTextView50);
                        forShare.append('\n');
                    }


                    if (!String.valueOf(myEditText20).equals("0")) {
                        forShare.append("•  20      x  ");
                        forShare.append(myEditText20);
                        forShare.append("  =  ₹ ");
                        forShare.append(myTextView20);
                        forShare.append('\n');
                    }


                    if (!String.valueOf(myEditText10).equals("0")) {
                        forShare.append("•  10      x  ");
                        forShare.append(myEditText10);
                        forShare.append("  =  ₹ ");
                        forShare.append(myTextView10);
                        forShare.append('\n');
                    }


                    if (!String.valueOf(myEditText5).equals("0")) {
                        forShare.append("•  5        x  ");
                        forShare.append(myEditText5);
                        forShare.append("  =  ₹ ");
                        forShare.append(myTextView5);
                        forShare.append('\n');
                    }


                    forShare.append("•  Total value of Notes  =>  ");
                    forShare.append(totalNotesInString);
                    forShare.append('\n');
                    forShare.append('\n');
                    forShare.append('\n');
                    forShare.append("◕  Coins :  ");
                    forShare.append('\n');

                    if (!String.valueOf(myEditTextCoin20).equals("0")) {
                        forShare.append("•  20 Coin  x  ");
                        forShare.append(myEditTextCoin20);
                        forShare.append("  =  ₹ ");
                        forShare.append(myTextViewCoin20);
                        forShare.append('\n');
                    }

                    if (!String.valueOf(myEditTextCoin10).equals("0")) {
                        forShare.append("•  10 Coin  x  ");
                        forShare.append(myEditTextCoin10);
                        forShare.append("  =  ₹ ");
                        forShare.append(myTextViewCoin10);
                        forShare.append('\n');
                    }


                    if (!String.valueOf(myEditTextCoin5).equals("0")) {
                        forShare.append("•  5 Coin    x  ");
                        forShare.append(myEditTextCoin5);
                        forShare.append("  =  ₹ ");
                        forShare.append(myTextViewCoin5);
                        forShare.append('\n');
                    }


                    if (!String.valueOf(myEditTextCoin2).equals("0")) {
                        forShare.append("•  2 Coin    x  ");
                        forShare.append(myEditTextCoin2);
                        forShare.append("  =  ₹ ");
                        forShare.append(myTextViewCoin2);
                        forShare.append('\n');
                    }


                    if (!String.valueOf(myEditTextCoin1).equals("0")) {
                        forShare.append("•  1 Coin    x  ");
                        forShare.append(myEditTextCoin1);
                        forShare.append("  =  ₹ ");
                        forShare.append(AddComma.getIndianCurrencyFormat(myTextViewCoin1));
                        forShare.append('\n');
                    }


                    if (!String.valueOf(myEditTextExtraCoin).equals("0")) {
                        forShare.append("•  Extra Coins  ");
                        forShare.append("  =  ₹ ");
                        forShare.append(myTextViewCoins);
                        forShare.append('\n');
                    }


                    forShare.append("•  Total value of Coins =>  ");
                    forShare.append(totalCoinsInString);
                    forShare.append('\n');
                    forShare.append('\n');
                    forShare.append('\n');
                    forShare.append("•  Total Amount =>  ");
//                    forShare.append("  =>  ₹ ");
                    forShare.append(myTextViewInNumbers);

                    forShare.append('\n');

                    forShare.append("•  Total Amount In Words ");
                    forShare.append("  =>  ");
                    forShare.append('\n');
                    forShare.append(myTextViewWord).append("Rupees Only.");
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
                    context.startActivity(sendIntent);

                } else {

//                    That is useless even though i have used for comfort haha lol.
                    Toast toast = Toast.makeText(context, "Zero amount can't be shared.", Toast.LENGTH_SHORT);
                    View view1 = toast.getView();

                    try {
                        assert view1 != null;
                        view1.getBackground().setColorFilter(Color.parseColor("#10171f"), PorterDuff.Mode.SRC_IN);
                    } catch (NullPointerException ignored) { }
                    toast.show();
                }


            }
        });


        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Clicked");
            }
        });



    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return currencyModalArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView currency2000TV, currency500TV, currency200TV, currency100TV, currency50TV;
        private TextView currency20TV, currency10TV, currency5TV, currency20CoinTV, currency10CoinTV, currency5CoinTV;
        private TextView currency2CoinTV, currency1CoinTV, currencyExtraCoinTV, resultTV;
        private TextView payeeName, date, time, day, notes, coins;
        private CardView layout;
        private ToggleButton viewMore;
        private LinearLayout linear2000;
        private LinearLayout linear500;
        private LinearLayout linear200;
        private LinearLayout linear100;
        private LinearLayout linear50;
        private LinearLayout linear20;
        private LinearLayout linear10;
        private LinearLayout linear5;
        private LinearLayout linear20Coin;
        private LinearLayout linear10Coin;
        private LinearLayout linear5Coin;
        private LinearLayout linear2Coin;
        private LinearLayout linear1Coin;
        private LinearLayout linearExtraCoin;
        private Button deleteButton;
        private Button copyButton;
        private Button speakButton;
        private Button shareButton;
        TextToSpeech tts;
        private EditText editText2000Rv;
        private EditText editText500Rv;
        private EditText editText200Rv;
        private EditText editText100Rv;
        private EditText editText50Rv;
        private EditText editText20Rv;
        private EditText editText10Rv;
        private EditText editText5Rv;
        private EditText editText20CoinRv;
        private EditText editText10CoinRv;
        private EditText editText5CoinRv;
        private EditText editText2CoinRv;
        private EditText editText1CoinRv;
        private EditText editTextExtraCoinRv;
        private LinearLayout linearCurrencyRvItem;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our text views
            currency2000TV = itemView.findViewById(R.id.currency2000);
            currency500TV = itemView.findViewById(R.id.currency500);
            currency200TV = itemView.findViewById(R.id.currency200);
            currency100TV = itemView.findViewById(R.id.currency100);
            currency50TV = itemView.findViewById(R.id.currency50);
            currency20TV = itemView.findViewById(R.id.currency20);
            currency10TV = itemView.findViewById(R.id.currency10);
            currency5TV = itemView.findViewById(R.id.currency5);
            currency20CoinTV = itemView.findViewById(R.id.currency20Coin);
            currency10CoinTV = itemView.findViewById(R.id.currency10Coin);
            currency5CoinTV = itemView.findViewById(R.id.currency5Coin);
            currency2CoinTV = itemView.findViewById(R.id.currency2Coin);
            currency1CoinTV = itemView.findViewById(R.id.currency1Coin);
            currencyExtraCoinTV = itemView.findViewById(R.id.currencyExtraCoin);
            resultTV = itemView.findViewById(R.id.result);
            payeeName = itemView.findViewById(R.id.mypayeeName);
            date = itemView.findViewById(R.id.mydate);
            time = itemView.findViewById(R.id.mytime);
            day = itemView.findViewById(R.id.myday);
            notes = itemView.findViewById(R.id.mynotes);
            coins = itemView.findViewById(R.id.mycoins);


            linearCurrencyRvItem = itemView.findViewById(R.id.linear_currency_rv_item);
            layout = itemView.findViewById(R.id.layout);
            linear2000 = itemView.findViewById(R.id.linear2000);
            linear500 = itemView.findViewById(R.id.linear500);
            linear200 = itemView.findViewById(R.id.linear200);
            linear100 = itemView.findViewById(R.id.linear100);
            linear50 = itemView.findViewById(R.id.linear50);
            linear20 = itemView.findViewById(R.id.linear20);
            linear10 = itemView.findViewById(R.id.linear10);
            linear5 = itemView.findViewById(R.id.linear5);
            linear20Coin = itemView.findViewById(R.id.linear20Coin);
            linear10Coin = itemView.findViewById(R.id.linear10Coin);
            linear5Coin = itemView.findViewById(R.id.linear5Coin);
            linear2Coin = itemView.findViewById(R.id.linear2Coin);
            linear1Coin = itemView.findViewById(R.id.linear1Coin);
            linearExtraCoin = itemView.findViewById(R.id.linearExtraCoin);
            viewMore = itemView.findViewById(R.id.viewMore);
            deleteButton = itemView.findViewById(R.id.delete_button);
            copyButton = itemView.findViewById(R.id.copy_button_currency_rv_item);
            speakButton = itemView.findViewById(R.id.speak_button_currency_rv_item);
            shareButton = itemView.findViewById(R.id.share_button_currency_rv_item);

            editText2000Rv = itemView.findViewById(R.id.editText2000Rv);
            editText500Rv = itemView.findViewById(R.id.editText500Rv);
            editText200Rv = itemView.findViewById(R.id.editText200Rv);
            editText100Rv = itemView.findViewById(R.id.editText100Rv);
            editText50Rv = itemView.findViewById(R.id.editText50Rv);
            editText20Rv = itemView.findViewById(R.id.editText20Rv);
            editText10Rv = itemView.findViewById(R.id.editText10Rv);
            editText5Rv = itemView.findViewById(R.id.editText5Rv);
            editText20CoinRv = itemView.findViewById(R.id.editText20CoinRv);
            editText10CoinRv = itemView.findViewById(R.id.editText10CoinRv);
            editText5CoinRv = itemView.findViewById(R.id.editText5CoinRv);
            editText2CoinRv = itemView.findViewById(R.id.editText2CoinRv);
            editText1CoinRv = itemView.findViewById(R.id.editText1CoinRv);
            editTextExtraCoinRv = itemView.findViewById(R.id.editTextExtraCoinRv);


        }

    }


}


