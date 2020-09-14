package com.example.practice;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.practice.Bean.User;
import com.example.practice.databinding.FragmentSignUpBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {
    //DataBinding绑定Layout
    private FragmentSignUpBinding mBinding;
    //引入ViewModel
    private UserViewModel userViewModel;
    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //绑定Layout
        mBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up,container,false);
        View view=mBinding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userViewModel = ViewModelProviders.of(requireActivity()).get(UserViewModel.class);
        mBinding.signupbutton.setEnabled(false);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String username= mBinding.signupUsername.getText().toString().trim();
                String password=mBinding.signupPassword.getText().toString().trim();
                String confirm=mBinding.signupConfirm.getText().toString().trim();
                mBinding.signupbutton.setEnabled(!username.isEmpty() && !password.isEmpty()&&!confirm.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        mBinding.signupUsername.addTextChangedListener(textWatcher);
        mBinding.signupPassword.addTextChangedListener(textWatcher);
        mBinding.signupConfirm.addTextChangedListener(textWatcher);
        mBinding.signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {
                String username= mBinding.signupUsername.getText().toString().trim();
                String password=mBinding.signupPassword.getText().toString().trim();
                String confirm=mBinding.signupConfirm.getText().toString().trim();
                Log.d("mylog","usernameS"+username);
                Log.d("mylog","passwordS"+password);
                Log.d("mylog","confirmS"+confirm);
                 if (password.equals(confirm)){
                     User user=new User(username,password);
                     if(userViewModel.uniqueCheck(username)){
                         Toast.makeText(getActivity(), "注册失败！用户名重复！",
                                 Toast.LENGTH_SHORT).show();
                     }
                     else {
                         userViewModel.insertUsers(user);
                         Toast.makeText(getActivity(), "注册成功！",
                                 Toast.LENGTH_SHORT).show();
                         NavController navController = Navigation.findNavController(v);
                         navController.navigateUp();
                     }
                 }
                 else {
                     Toast.makeText(getActivity(), "密码与确认密码不一致！",
                             Toast.LENGTH_SHORT).show();
                 }
             }
             }
        );
    }
}
