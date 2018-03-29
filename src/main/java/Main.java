import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.BaseRecognizeCallback;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        SpeechToText service = new SpeechToText();

        service.setUsernameAndPassword("bde7e26e-ca2d-490b-9e7d-df7ed49835cb", "0fNKfykZgfiQ");

        RecognizeOptions options = new RecognizeOptions.Builder()
                .contentType("audio/wav")
                .model("en-US_BroadbandModel")
                .maxAlternatives(1).build();

        BaseRecognizeCallback callback = new BaseRecognizeCallback() {
            @Override
            public void onTranscription(SpeechResults speechResults) {
                System.out.println(speechResults);
            }

            @Override
            public void onDisconnected() {
                System.exit(0);
            }
        };

        try

        {
            service.recognizeUsingWebSocket
                    (new FileInputStream("/home/zemoso/Desktop/demoScriptAudio.wav"), options, callback);
        } catch (
                FileNotFoundException e)

        {
            e.printStackTrace();
        }
    }
}
