package com.example.clubepl.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Club implements Parcelable {
    int image;
    Boolean clubStatus;
    List<Player> playerList = new ArrayList<>();

    public Club() {
    }

    public Club(int image, Boolean clubStatus) {
        this.image = image;
        this.clubStatus = clubStatus;
    }

    public Club(int image, Boolean clubStatus, List<Player> playersModelList) {
        this.image = image;
        this.clubStatus = clubStatus;
        this.playerList = playersModelList;
    }

    protected Club(Parcel in) {
        image = in.readInt();
        byte tmpClubStatus = in.readByte();
        clubStatus = tmpClubStatus == 0 ? null : tmpClubStatus == 1;
        playerList = in.createTypedArrayList(Player.CREATOR);
    }

    public static final Creator<Club> CREATOR = new Creator<Club>() {
        @Override
        public Club createFromParcel(Parcel in) {
            return new Club(in);
        }

        @Override
        public Club[] newArray(int size) {
            return new Club[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image);
        dest.writeByte((byte) (clubStatus == null ? 0 : clubStatus ? 1 : 2));
        dest.writeTypedList(playerList);
    }

    public List<Player> getPlayerList(){return playerList;}

    public void setPlayerList(List<Player> playerList){
        this.playerList = playerList;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Boolean getClubStatus() {
        return clubStatus;
    }

    public void setClubStatus(Boolean clubStatus) {
        this.clubStatus = clubStatus;
    }
}
