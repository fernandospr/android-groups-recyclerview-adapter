package com.fernandospr.groupsadapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int INVALID_COUNT = -1;

    @NonNull
    private final List<Group> mGroups;

    private int mItemCount;

    public GroupsRecyclerViewAdapter() {
        this.mGroups = new ArrayList<>();
        this.mItemCount = INVALID_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        for (Group group : mGroups) {
            if (position < group.getItemCount()) {
                return group.getItemViewType();
            }
            position -= group.getItemCount();
        }
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        for (Group group : mGroups) {
            if (position < group.getItemCount()) {
                return group.getItemId(position);
            }
            position -= group.getItemCount();
        }
        return RecyclerView.NO_ID;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        for (Group group : mGroups) {
            if (viewType == group.getItemViewType()) {
                return group.onCreateViewHolder(parent, LayoutInflater.from(parent.getContext()));
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        for (Group group : mGroups) {
            if (position < group.getItemCount()) {
                group.onBindViewHolder(holder, position);
                return;
            }
            position -= group.getItemCount();
        }
    }

    @Override
    public int getItemCount() {
        if (mItemCount == INVALID_COUNT) {
            mItemCount = doGetItemCount();
        }
        return mItemCount;
    }

    private int doGetItemCount() {
        int count = 0;
        for (Group s : mGroups) {
            count += s.getItemCount();
        }
        return count;
    }

    public void addGroup(@NonNull Group group) {
        mItemCount = INVALID_COUNT;

        mGroups.add(group);
    }

    public void clearGroups() {
        mItemCount = INVALID_COUNT;

        mGroups.clear();
    }

    public static abstract class Group<E,VH extends RecyclerView.ViewHolder> {

        private final int mViewType;

        @NonNull
        private final List<E> mItems;

        public Group(int mViewType, @NonNull List<E> mItems) {
            this.mViewType = mViewType;
            this.mItems = Collections.unmodifiableList(mItems);
        }

        public int getItemCount() {
            return mItems.size();
        }

        @Nullable
        public E getItem(int position) {
            return mItems.get(position);
        }

        @NonNull
        public List<E> getItems() {
            return mItems;
        }

        public long getItemId(int position) {
            return RecyclerView.NO_ID;
        }

        public int getItemViewType() {
            return this.mViewType;
        }

        public abstract void onBindViewHolder(VH holder, int position);

        public abstract VH onCreateViewHolder(@NonNull ViewGroup parent,
                                              @NonNull LayoutInflater inflater);

    }

}