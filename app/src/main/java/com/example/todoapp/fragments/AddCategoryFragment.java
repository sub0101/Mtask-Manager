package com.example.todoapp.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.todoapp.R;
import com.example.todoapp.database.CategoryInfo;
import com.example.todoapp.database.TaskViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import yuku.ambilwarna.AmbilWarnaDialog;

public class AddCategoryFragment extends DialogFragment {

    Dialog dialog;
    TaskViewModel  viewModel;
    View viewcolor;
    MaterialButton choose_color,add_category;
    TextInputEditText category_input;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
         View view = inflater.inflate(R.layout.category_dialog , container ,false);

         return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        dialog.getWindow().setWindowAnimations(com.google.android.material.R.style.Animation_Design_BottomSheetDialog);
        viewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
        add_category =dialog.findViewById(R.id.addCategory);
        category_input = dialog.findViewById(R.id.category_name);
        viewcolor = dialog.findViewById(R.id.viewColor);
        choose_color = dialog.findViewById(R.id.addColor);
        choose_color.setOnClickListener(new ClickListner());
        add_category.setOnClickListener(new ClickListner());
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;

    }

    class ClickListner implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            if(v==add_category)
            {
                int color = Color.TRANSPARENT;
                Drawable background = viewcolor.getBackground();
                if (background instanceof ColorDrawable)
                    color = ((ColorDrawable) background).getColor();
//                String hexColor = String.format("#%06X", (0xFFFFFF & color));
            String category_name;
            category_name   = category_input.getText().toString();
                if(color!=0 && !category_name.equals(""))
                {
                    CategoryInfo categoryInfo = new CategoryInfo(category_name , color);
                    viewModel.insertCategory(categoryInfo);
                    dialog.cancel();

                }
                else {
                    MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(v.getContext())
                            .setMessage("Invalid Field")
                            .setIcon(android.R.drawable.stat_notify_error)
                            .setTitle("Alert");
                    alertDialogBuilder.show();

                }


            }
            if(v==choose_color)
            {
chooseColor();
            }
        }
        void chooseColor()
        {
            final int[] currentColor = {0};
                    AmbilWarnaDialog dialog = new AmbilWarnaDialog(getContext(), currentColor[0], false, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                        @Override
                        public void onOk(AmbilWarnaDialog dialog, int color) {
                            currentColor[0] = color;
                            viewcolor.setBackgroundColor(color);
                        }

                        @Override
                        public void onCancel(AmbilWarnaDialog dialog) {
                            Toast.makeText(getContext(), "Action canceled!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialog.show();
                }

        }

    }


