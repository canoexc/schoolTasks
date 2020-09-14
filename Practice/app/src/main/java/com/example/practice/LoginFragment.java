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

import com.example.practice.Utils.InsertData;
import com.example.practice.databinding.FragmentLoginBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    //DataBinding绑定Layout
    private FragmentLoginBinding mBinding;
    //引入ViewModel
    private UserViewModel userViewModel;
    public LoginFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //绑定Layout
        mBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false);
        View view=mBinding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userViewModel = ViewModelProviders.of(requireActivity()).get(UserViewModel.class);
        mBinding.loginConfirm.setEnabled(false);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String username= mBinding.loginUserame.getText().toString().trim();
                String password=mBinding.loginPassword.getText().toString().trim();
                mBinding.loginConfirm.setEnabled(!username.isEmpty() && !password.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        mBinding.loginUserame.addTextChangedListener(textWatcher);
        mBinding.loginPassword.addTextChangedListener(textWatcher);
        mBinding.loginSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_loginFragment_to_signUpFragment);
            }
        });
        mBinding.loginConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username= mBinding.loginUserame.getText().toString().trim();
                String password=mBinding.loginPassword.getText().toString().trim();
                Log.d("mylog","usernameL"+username);
                Log.d("mylog","passwordL"+password);
                Boolean flag=userViewModel.loginCheck(username,password);
                Log.d("mylog",flag.toString());
                if (flag) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_loginFragment_to_mainFragment);
                }
                else Toast.makeText(getActivity(), "用户名或密码不正确！",
                        Toast.LENGTH_SHORT).show();
            }
        });
        mBinding.loginConfirm.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                userViewModel.deleteUsers();
                return false;
            }
        });
        mBinding.loginSignup.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                InsertData insertData=new InsertData(getActivity().getApplication());
                insertData.InsertExercise();
                return false;
            }
        });
    }

}
