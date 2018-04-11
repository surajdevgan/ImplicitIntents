package suraj.dev.com.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickOpenWebpageButton(View v) {
        String urlAsString = "http://www.github.com";
        openWebPage(urlAsString);
    }

    public void onClickOpenAddressButton(View v) {
        String addressString = "1600 Amphitheatre Parkway, CA";

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("geo")
                .path("0,0")  // in path we can put lat long of the location and we can remove query method
                .query(addressString);
        Uri addressUri = builder.build();

        showMap(addressUri);
    }

    public void onClickCallTextButton(View view) {

        String phoneString = "987654321";

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("tel")
                .query(phoneString);
        Uri phoneUri = builder.build();

        callPhone(phoneUri);


    }
    public void onClickShareTextButton(View v) {

        String textThatYouWantToShare =
                "Sharing the coolest thing I've learned so far";
        shareText(textThatYouWantToShare);


    }


    private void openWebPage(String url) {

        Uri webpage = Uri.parse(url);


        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);


        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    private void showMap(Uri geoLocation) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    private void shareText(String textToShare) {

        String mimeType = "text/plain";
        String title = "Learning How to Share";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(title)
                .setText(textToShare)
                .startChooser();
    }

    private void callPhone( Uri phone)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(phone);


        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }




}
