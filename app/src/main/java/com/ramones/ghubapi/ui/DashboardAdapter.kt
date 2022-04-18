package com.ramones.ghubapi.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ramones.ghubapi.databinding.ReposItemBinding
import com.ramones.ghubapi.db.dbmodels.Repository

class DashboardAdapter : RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {

    var repos = ArrayList<Repository>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    class DashboardViewHolder(private val binding: ReposItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: Repository) {
            binding.nameTextView.text = repo.ownerResponse?.name
            binding.repoNameTextView.text = repo.name
            binding.forksLabelTextView.text = repo.forks.toString()
            binding.issesLabelTextView.text = repo.issues.toString()
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