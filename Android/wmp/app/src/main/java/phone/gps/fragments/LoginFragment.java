package phone.gps.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import phone.gps.R;
import phone.gps.obj.AuthenticationRequest;
import phone.gps.obj.Globals;
import phone.gps.obj.User;
import phone.gps.preferences.Authentication;
import phone.gps.runnable.LoginRunnable;

public class LoginFragment extends Fragment {

    TextView email;
    TextView password;
    Authentication auth;

    LoginRunnable.LoginInterface loginInterface=new LoginRunnable.LoginInterface(){

        @Override
        public void onComplete(final AuthenticationRequest authenticationRequest) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                authenticationRequest.setEmail(email.getText().toString());
                authenticationRequest.setImei(Globals.getImei(getActivity().getApplicationContext()));
                auth.save(authenticationRequest);
                email.setEnabled(false);
                password.setEnabled(false);
                }
            });
        }

        @Override
        public void onError(final Exception ex) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                Toast.makeText(getActivity(),ex.getMessage().toString(),Toast.LENGTH_LONG).show();
                }
            });
        }
    };

    public LoginFragment() {
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button loginregister=(Button)view.findViewById(R.id.email_sign_in_button);

        email=(TextView)view.findViewById(R.id.email);
        password=(TextView)view.findViewById(R.id.password);

        auth=new Authentication(getContext());

        if(auth.isLogged()) {
            email.setText(auth.read().getEmail());
            email.setEnabled(false);
            password.setEnabled(false);
        }

        loginregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!auth.isLogged()) {
                    doLogin();
                }
            }
        });
    }

    private void doLogin(){
        User user=new User(email.getText().toString(),password.getText().toString());
        new Thread(new LoginRunnable(user,Globals.getImei(getActivity().getApplicationContext()),loginInterface)).start();
    }
}
