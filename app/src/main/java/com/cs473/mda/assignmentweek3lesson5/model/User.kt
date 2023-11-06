package com.cs473.mda.assignmentweek3lesson5.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class User(val email: String?, val password: String, val firstName: String, val lastName: String): Serializable