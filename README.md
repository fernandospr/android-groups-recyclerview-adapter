**RecyclerView.Adapter** supports heterogenous layouts, however, when you have to show multiple arrays of heterogenous object classes, it's difficult to write the code for `getItemViewType` and `onBindViewHolder`.

Usually, one has to iterate through each array, doing explicit casts and/or a bunch of ifs, modifying the `position` value to match the corresponding array.

**GroupsRecyclerViewAdapter** allows to add groups of items (it could also be a group of a single item, e.g. a header or a footer), easing the development of the use cases mentioned above.

# Usage
* Create your view holders.
```
    private class MyModelViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView lastname;

        MyModelViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            lastname = (TextView) itemView.findViewById(R.id.lastname);
        }
    }
```
* Create your custom Group classes (inheriting from the Group class).
```
private class MyModelGroup extends Group<MyModel, MyModelViewHolder> {

}
```
* Inside your group class, specify the view type.
```
        MyModelGroup(@NonNull List<MyModel> items) {
            super(MODEL_VIEW_TYPE, items);
        }
```

* Inside your group class, override `onBindViewHolder` and `onCreateViewHolder`.
```
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
```
* Finally, instantiate **GroupsRecyclerViewAdapter** in your Activity/Fragment and add groups.
```
GroupsRecyclerViewAdapter adapter = new GroupsRecyclerViewAdapter();

Group group = new MyModelGroup(myModelList);
adapter.addGroup(group);

Group group2 = MyModel2Group(myModel2List);
adapter.addGroup(group2);

...
```
