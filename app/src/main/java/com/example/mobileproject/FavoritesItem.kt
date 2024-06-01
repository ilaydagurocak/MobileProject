package com.example.mobileproject

import android.os.Parcel
import android.os.Parcelable

data class FavoriteItem(
    val name: String,
    val description: String,
    val imageRes: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeInt(imageRes)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FavoriteItem> {
        override fun createFromParcel(parcel: Parcel): FavoriteItem {
            return FavoriteItem(parcel)
        }

        override fun newArray(size: Int): Array<FavoriteItem?> {
            return arrayOfNulls(size)
        }
    }
}
