package com.fernandospr.groupsadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomAdapter extends GroupsRecyclerViewAdapter {

    private static final int STRING_VIEW_TYPE = 0;
    private static final int MODEL_VIEW_TYPE = 1;

    public CustomAdapter() {
        addGroup(new MyStringGroup(Arrays.asList("Hola", "Mundo")));
        addGroup(new MyModelGroup(Arrays.asList(new MyModel("Fer", "Spr"))));
        addGroup(new MyStringGroup(Arrays.asList("Prueba", "1", "2", "3")));
        addGroup(new MyModelGroup(Arrays.asList(new MyModel("Nombre1", "Apellido1"), new MyModel("Nombre2", "Apellido2"))));
        addGroup(new MyStringGroup(Arrays.asList("Hola", "Mundo")));
        addGroup(new MyModelGroup(Arrays.asList(new MyModel("Fer", "Spr"))));
        addGroup(new MyStringGroup(generateNumbers()));
        addGroup(new MyStringGroup(Arrays.asList("FOOTER")));
    }

    @NonNull
    private List<String> generateNumbers() {
        List<String> l = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            l.add("" + i);
        }
        return l;
    }


    private static class MyStringGroup extends Group<String, MyStringViewHolder> {

        MyStringGroup(@NonNull List<String> items) {
            super(STRING_VIEW_TYPE, items);
        }

        @Override
        public void onBindViewHolder(MyStringViewHolder holder, int position) {
            String s = getItems().get(position);
            holder.title.setText(s);
        }

        @Override
        public MyStringViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                     @NonNull LayoutInflater inflater) {
            View v = inflater.inflate(R.layout.my_string_item, parent, false);
            return new MyStringViewHolder(v);
        }
    }

    private static class MyStringViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;

        MyStringViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }








    private static class MyModelGroup extends Group<MyModel, MyModelViewHolder> {

        MyModelGroup(@NonNull List<MyModel> items) {
            super(MODEL_VIEW_TYPE, items);
        }

        @Override
        public void onBindViewHolder(MyModelViewHolder holder, int position) {
            MyModel myModel = getItems().get(position);
            holder.name.setText(myModel.getName());
            holder.lastname.setText(myModel.getLastname());
        }

        @Override
        public MyModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                    @NonNull LayoutInflater inflater) {
            View v = inflater.inflate(R.layout.my_model_item, parent, false);
            return new MyModelViewHolder(v);
        }

    }

    private static class MyModelViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView lastname;

        MyModelViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            lastname = (TextView) itemView.findViewById(R.id.lastname);
        }
    }


}
