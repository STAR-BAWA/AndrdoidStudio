package com.example.texttospeech;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.TypedArrayUtils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button b1;
    Button listen;
    TextView txt,txt2;
    EditText edt;
    private TextToSpeech tts;
    private SpeechRecognizer recognizer;
    ActivityResultLauncher<String> launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt=findViewById(R.id.textview);
        listen=findViewById(R.id.button2);
        b1=findViewById(R.id.button);
        edt=findViewById(R.id.editTextTextPersonName);
        txt2=findViewById(R.id.textView2);
//        In Android Studio, requestFocus() is a method that's used to request focus for a particular View or widget, such as a TextView or EditText. When a View or widget has focus,
//        it can respond to user input, such as keyboard input or touch events.
//Here's an example of how to use requestFocus() to set focus on an EditText:


        listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recognizer =SpeechRecognizer.createSpeechRecognizer(v.getContext());
                Intent itent =new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                itent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//                recognizer.startListening(itent);
                Toast.makeText(MainActivity.this, "Listen", Toast.LENGTH_SHORT).show();
                recognizer.setRecognitionListener(new RecognitionListener() {
                    @Override
                    public void onReadyForSpeech(Bundle params) {

                    }

                    @Override
                    public void onBeginningOfSpeech() {

                    }

                    @Override
                    public void onRmsChanged(float rmsdB) {

                    }

                    @Override
                    public void onBufferReceived(byte[] buffer) {

                    }

                    @Override
                    public void onEndOfSpeech() {
                        Toast.makeText(MainActivity.this, "Speech complete", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(int error) {
                        Toast.makeText(MainActivity.this, "some error ", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResults(Bundle results) {
                        ArrayList<String> matches=results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                        for(String str:matches){
                            txt2.setText(str.toString()+"");
                        }

                    }

                    @Override
                    public void onPartialResults(Bundle partialResults) {

                    }

                    @Override
                    public void onEvent(int eventType, Bundle params) {

                    }
                });
//                The EXTRA_LANGUAGE_MODEL extra is set to LANGUAGE_MODEL_FREE_FORM, which specifies that the speech input can be any sequence of words.
//                Finally, the startListening method is called on the recognizer instance,
//                passing in the Intent as a parameter.
                  recognizer.startListening(itent);
            }
        });

        tts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!=TextToSpeech.ERROR)
                {
                 tts.setLanguage(Locale.ENGLISH);
                 tts.setPitch(1f);
                 tts.setSpeechRate(1f);
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                tts.speak(edt.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
                tts.speak(edt.getText().toString(),TextToSpeech.QUEUE_FLUSH,null,null);
            }
        });
    }
}