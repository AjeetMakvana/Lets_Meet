package com.example.letsmeet.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.letsmeet.DashboardActivity;
import com.example.letsmeet.R;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class HomeFragment extends Fragment {

    private EditText code;
    private AppCompatButton join;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        code = root.findViewById(R.id.meeting_code);
        join = root.findViewById(R.id.join_meeting_btn);

        URL serverUrl;

        try {
            serverUrl = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultOptions =
                    new JitsiMeetConferenceOptions.Builder()
                            .setServerURL(serverUrl)
                            .setWelcomePageEnabled(false)
                            .build();

            JitsiMeet.setDefaultConferenceOptions(defaultOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JitsiMeetConferenceOptions options =
                        new JitsiMeetConferenceOptions.Builder()
                                .setRoom(code.getText().toString())
                                .setWelcomePageEnabled(false)
                                .build();

                JitsiMeetActivity.launch( getContext(), options);
            }
        });

        return root;
    }
}