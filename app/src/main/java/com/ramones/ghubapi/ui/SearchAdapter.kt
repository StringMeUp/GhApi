package com.ramones.ghubapi.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ramones.ghubapi.databinding.ReposItemBinding
import com.ramones.ghubapi.db.dbmodels.Repository
import java.lang.StringBuilder

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.DashboardViewHolder>() {

    var repos = ArrayList<Repository>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    class DashboardViewHolder(private val binding: ReposItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: Repository) {
            binding.apply {
                Glide.with(binding.root).load(repo.owner?.avatar).into(binding.avatarImageView)
                nameTextView.text = repo.owner?.name
                repoNameTextView.text = repo.name
                forksLabelTextView.text = StringBuilder().append("Forks").append(":").append("\n").append("${repo.forks}")
                issuesLabelTextView.text =  StringBuilder().append("Issues").append(":").append("\n").append("${repo.issues}")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        return DashboardViewHolder(
            ReposItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        holder.bind(repos[position])
    }

    override fun getItemCount(): Int = repos.size

}