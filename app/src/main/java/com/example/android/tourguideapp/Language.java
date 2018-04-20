package com.example.android.tourguideapp;

public class Language {

    private int mLanguageDefault;

    private int mLanguageLocal;

    private int mLanguageAudio;


    public Language(int languageDefault, int languageLocal, int languageAudio) {

        mLanguageDefault = languageDefault;
        mLanguageLocal = languageLocal;
        mLanguageAudio = languageAudio;

    }


    public int getLanguageDefault() {
        return mLanguageDefault;
    }

    public int getLanguageLocal() {
        return mLanguageLocal;
    }

    public int getLanguageAudio() {
        return mLanguageAudio;
    }


}
