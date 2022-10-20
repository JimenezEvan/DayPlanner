package com.example.dayplanner;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dayplanner.databinding.FragmentEventListItemBinding;
import com.google.gson.Gson;

import java.util.List;

/**
 *
 * TODO: Replace the implementation with code for your data type.
 */
public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.ViewHolder> {

    private static final String PREFS = "shared_prefs";
    private static final String SAVED_EVENTS = "saved_events";
    private List<Event> mValues;
    private final Context context;
    ImageButton deleteButton;

    public EventRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentEventListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getDate());
        holder.mContentView.setText(mValues.get(position).getName());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mValues.remove(holder.mItem);
                setEvents(mValues);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues == null ? 0 : mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public Event mItem;
        public final ImageButton deleteButton;

        public ViewHolder(FragmentEventListItemBinding binding) {
            super(binding.getRoot());
            mIdView = binding.txtDate;
            mContentView = binding.txtName;
            deleteButton = binding.btnDelete;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
    public void setEvents(List<Event> events) {
        this.mValues = events;
        notifyDataSetChanged();
        saveEvents();
    }

    private void saveEvents() {
        SharedPreferences pref = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String eventJSON = gson.toJson(mValues);
        pref.edit().putString(SAVED_EVENTS, eventJSON).commit();
    }

}