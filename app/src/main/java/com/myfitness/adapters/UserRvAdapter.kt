package com.myfitness.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myfitness.BookingUserDetailsActivity
import com.myfitness.R
import com.myfitness.databinding.ItemLayoutBinding
import com.myfitness.models.Result
import com.myfitness.util.Constants.Companion.KEY
import com.myfitness.util.MyFitnessUtil
import com.myfitness.viewmodel.MyFitnessViewModel
import kotlinx.android.synthetic.main.item_layout.view.*
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class UserRvAdapter() : RecyclerView.Adapter<UserRvAdapter.UserViewHolder>() {

    private var results : ArrayList<Result> = ArrayList<Result>()
    private lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        context = parent.context
        return UserViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        /*
        var format1 = SimpleDateFormat("yyyy-MM-dd")
        var format2 = SimpleDateFormat("dd-MM-yyyy")

        var date : Date = format1.parse(results[position].registered?.date)
        var formattedDate = format2.format(date)
         */
        /*
        holder.itemView.tvFirstName.text = results[position].name?.first
        holder.itemView.tvLastName.text = results[position].name?.last

        var formattedDate = results[position].registered?.date?.let { MyFitnessUtil.formatDate(it) }

        holder.itemView.tvDate.text = formattedDate
        holder.itemView.tvStreetNo.text = results[position].location?.street?.number?.toString()
        holder.itemView.tvStreetName.text = results[position].location?.street?.name
        holder.itemView.tvCity.text = results[position].location?.city
        holder.itemView.tvState.text = results[position].location?.state
        holder.itemView.tvCountry.text = results[position].location?.country

        Glide.with(holder.itemView.context).load(results[position].picture?.thumbnail).into(holder.itemView.ivImage);

        holder.itemView.btnDetails.setOnClickListener {
            val intent = Intent(context,BookingUserDetailsActivity::class.java)
            intent.putExtra("Key",results[position])
            context.startActivity(intent)
        }

         */
        val currentItem = results[position]
        holder.bind(currentItem)

        holder.itemView.btnDetails.setOnClickListener {
            val intent = Intent(context,BookingUserDetailsActivity::class.java)
            intent.putExtra(KEY,results[position])
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
       return results.size
    }

    fun setData(userList: List<Result>){

        val userListDiffUtil = UserListDiffUtil(results,userList)
        val userListDiffResult = DiffUtil.calculateDiff(userListDiffUtil)
        this.results.addAll(userList as ArrayList<Result>)
        notifyDataSetChanged()

        userListDiffResult.dispatchUpdatesTo(this)
    }

    class UserViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(result : Result){
            binding.result = result
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup) : UserViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemLayoutBinding.inflate(layoutInflater,parent,false)
                return UserViewHolder(binding)
            }
        }
    }

}