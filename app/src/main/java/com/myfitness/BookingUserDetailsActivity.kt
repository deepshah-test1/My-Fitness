package com.myfitness

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.myfitness.databinding.ActivityBookingUserDetailsBinding
import com.myfitness.models.Result
import com.myfitness.util.Constants.Companion.KEY
import kotlinx.android.synthetic.main.activity_booking_user_details.*
import kotlinx.android.synthetic.main.item_layout.view.*

class BookingUserDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBookingUserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_booking_user_details)

        val result = intent.getParcelableExtra<Result>(KEY)
        /*
        val userImageUrl = result?.picture?.large
        Glide.with(this).load(userImageUrl).into(binding.ivLarge);
         */

        binding.result = result



        tvPhone.setOnClickListener {
            val callIntent : Intent = Intent(Intent.ACTION_DIAL)
            callIntent.setData(Uri.parse("tel:"+ tvPhone.text.toString()))

            startActivity(callIntent)
        }

        tvEmail.setOnClickListener {
            val email = tvEmail.text.toString()
            val emailIntent : Intent = Intent(Intent.ACTION_SENDTO)
            emailIntent.setData(Uri.parse("mailto: $email"))
            //emailIntent.putExtra(Intent.EXTRA_EMAIL,tvEmail.text.toString())
            startActivity(emailIntent)

        }
    }
}