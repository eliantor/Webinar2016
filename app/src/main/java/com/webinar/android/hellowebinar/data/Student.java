package com.webinar.android.hellowebinar.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by aktor on 08/09/16.
 */

public class Student implements Parcelable{

    private String mName;
    private int mAge;

    public Student(String name,int age){
        //,,,,,
    }
    private Student(Parcel parcel){
        mName = parcel.readString();
        mAge = parcel.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeInt(mAge);
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
