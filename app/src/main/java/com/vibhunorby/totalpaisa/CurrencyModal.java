package com.vibhunorby.totalpaisa;


public class CurrencyModal {

    // variables for our coursename,
    // description, tracks and duration, id.
    private String currency2000;
    private String currency500;
    private String currency200;
    private String currency100;
    private String currency50;
    private String currency20;
    private String currency10;
    private String currency5;
    private String currency20_coin;
    private String currency10_coin;
    private String currency5_coin;
    private String currency2_coin;
    private String currency1_coin;
    private String currency_extra;
    private String result;
    private String payee;
    private String date;
    private String mytime;
    private String myday;
    private String notes;
    private String coins;
    private int id;


    public String getCurrency2000() {
        return currency2000;
    }

    public String getPayee() {
        return payee;
    }

    public String getdate() {
        return date;
    }

    public String getMytime() {
        return mytime;
    }

    public String getMyday() {
        return myday;
    }

    public String getNotes() {
        return notes;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public void setMydate(String date) {
        this.date = date;
    }

    public void setMytime(String mytime) {
        this.mytime = mytime;
    }

    public void setMyday(String myday) {
        this.myday = myday;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }

    public String getCoins() {
        return coins;
    }

    public String getCurrency500() {
        return currency500;
    }

    public String getCurrency200() {
        return currency200;
    }

    public String getCurrency100() {
        return currency100;
    }

    public String getCurrency50() {
        return currency50;
    }

    public void setCurrency2000(String currency2000) {
        this.currency2000 = currency2000;
    }

    public void setCurrency500(String currency500) {
        this.currency500 = currency500;
    }

    public void setCurrency200(String currency200) {
        this.currency200 = currency200;
    }

    public void setCurrency100(String currency100) {
        this.currency100 = currency100;
    }

    public void setCurrency50(String currency50) {
        this.currency50 = currency50;
    }

    public void setCurrency20(String currency20) {
        this.currency20 = currency20;
    }

    public void setCurrency10(String currency10) {
        this.currency10 = currency10;
    }

    public void setCurrency5(String currency5) {
        this.currency5 = currency5;
    }

    public void setCurrency20_coin(String currency20_coin) {
        this.currency20_coin = currency20_coin;
    }

    public void setCurrency10_coin(String currency10_coin) {
        this.currency10_coin = currency10_coin;
    }

    public void setCurrency5_coin(String currency5_coin) {
        this.currency5_coin = currency5_coin;
    }

    public void setCurrency2_coin(String currency2_coin) {
        this.currency2_coin = currency2_coin;
    }

    public void setCurrency1_coin(String currency1_coin) {
        this.currency1_coin = currency1_coin;
    }

    public void setCurrency_extra(String currency_extra) {
        this.currency_extra = currency_extra;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrency20() {
        return currency20;
    }

    public String getCurrency10() {
        return currency10;
    }

    public String getCurrency5() {
        return currency5;
    }

    public String getCurrency20_coin(){
        return currency20_coin;
    }

    public String getCurrency10_coin() {
        return currency10_coin;
    }

    public String getCurrency5_coin() {
        return currency5_coin;
    }

    public String getCurrency2_coin() {
        return currency2_coin;
    }

    public String getCurrency1_coin() {
        return currency1_coin;
    }

    public String getCurrency_extra() {
        return currency_extra;
    }

    public String getResult() {
        return result;
    }

    public int getId() {
        return id;
    }

    public CurrencyModal(String currency2000, String currency500, String currency200, String currency100, String currency50,
                         String currency20, String currency10, String currency5, String currency20_coin, String currency10_coin, String currency5_coin,
                         String currency2_coin, String currency1_coin, String currency_extra, String result, String payee, String date,
                         String mytime, String myday, String notes, String coins) {
        this.currency2000 = currency2000;
        this.currency500 = currency500;
        this.currency200 = currency200;
        this.currency100 = currency100;
        this.currency50 = currency50;
        this.currency20 = currency20;
        this.currency10 = currency10;
        this.currency5 = currency5;
        this.currency20_coin = currency20_coin;
        this.currency10_coin = currency10_coin;
        this.currency5_coin = currency5_coin;
        this.currency2_coin = currency2_coin;
        this.currency1_coin = currency1_coin;
        this.currency_extra = currency_extra;
        this.result = result;
        this.payee = payee;
        this.date = date;
        this.mytime = mytime;
        this.myday = myday;
        this.notes = notes;
        this.coins = coins;
    }
}