package com.myfitness.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.myfitness.R
import java.text.SimpleDateFormat
import java.util.*

class GlideUtils {

    companion object{
        @JvmStatic
        @BindingAdapter("android:profileImageUrl")
        fun profileImageUrl(imageView: ImageView, url: String?) {
            if (url?.isNullOrBlank() != true) {
                Glide.with(imageView.context)
                    .load(url)
                    .placeholder(R.drawable.profile)
                    .into(imageView)
            }
        }

        @JvmStatic
        @BindingAdapter("android:formattedDate")
        fun formattedDate(textView : TextView,strDate : String){

            var format1 = SimpleDateFormat("yyyy-MM-dd")
            var format2 = SimpleDateFormat("dd-MM-yyyy")

            var date : Date = format1.parse(strDate)
            var formattedDate = format2.format(date)

            textView.text = formattedDate

        }

    }
}