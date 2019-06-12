package com.example.myapplication;

public class word {


    private String mdefaultTranslation;//english translation
    private String mTranslation;  //the langauage to be translated
    private int ImageResourceId;
    private int mAudioResourceId;
    public word(String x,String y,int z)
    {
        mdefaultTranslation=x;
        mTranslation=y;
        mAudioResourceId=z;

    }
    public word(String x, String y, int ImageResourceId,int z)
    {
        mdefaultTranslation=x;
        mTranslation=y;
        this.ImageResourceId=ImageResourceId;
        mAudioResourceId=z;

    }

    public String getDefaultTreanslation()
    {
        return mdefaultTranslation;
    }
    public String getMowTreanslation()
    {
        return mTranslation;
    }
    public int getImageResourceId()
    {
        return ImageResourceId;
    }
    public int getmAudioResourceId(){return  mAudioResourceId;}

}
