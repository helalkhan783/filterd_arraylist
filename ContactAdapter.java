package com.ismos_salt_erp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ismos_salt_erp.R;
import com.ismos_salt_erp.databinding.ContactItemBinding;
import com.ismos_salt_erp.databinding.TransactionInListLayoutBinding;
import com.ismos_salt_erp.serverResponseModel.TransactionInList;
import com.ismos_salt_erp.utils.ImageBaseUrl;
import com.ismos_salt_erp.utils.MtUtils;
import com.ismos_salt_erp.utils.NumberUtil;
import com.ismos_salt_erp.utils.replace.DataModify;
import com.ismos_salt_erp.view.fragment.customers.AddNewCustomer;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.viewHolder> {
    private AddNewCustomer context;
    List<NumberUtil> lists;
    View view;
    private List<NumberUtil> filteredList; // Filtered list of items

    public ContactAdapter(List<NumberUtil> lists, View view, AddNewCustomer context) {
        this.context = context;
        this.lists = lists;
        this.view = view;
        filteredList = new ArrayList<>(lists); // Initialize filteredList with originalList

    }

    @NonNull
    @NotNull
    @Override
    public ContactAdapter.viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ContactItemBinding binding = DataBindingUtil.inflate(LayoutInflater.
                from(parent.getContext()), R.layout.contact_item, parent, false);
        return new ContactAdapter.viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ContactAdapter.viewHolder holder, int position) {
        NumberUtil ppos = filteredList.get(position);
        holder.itembinding.nameTv.setText("" + ppos.getName());
        holder.itembinding.numTv.setText("" + ppos.getNumber());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.contactInfo(ppos.getName(), ppos.getNumber());
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private ContactItemBinding itembinding;

        public viewHolder(@NonNull @NotNull ContactItemBinding itembinding) {
            super(itembinding.getRoot());
            this.itembinding = itembinding;
        }
    }

    public void filter(CharSequence query) {
        filteredList.clear();
        if (query.toString().isEmpty()) {
            filteredList.addAll(lists);
        } else {
            for (int i = 0; i < lists.size(); i++) {
                String text = lists.get(i).getName().toLowerCase() + lists.get(i).getNumber();
                if (text.contains(query.toString().toLowerCase())) {
                    filteredList.add(lists.get(i));
                }
            }
        }

        notifyDataSetChanged();

    }

}
