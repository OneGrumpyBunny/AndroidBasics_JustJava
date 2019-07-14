/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package android.example.justjava;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    int price = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText editText = findViewById(R.id.name_view);
        String text = editText.getText().toString();

        if (text.length() == 0) {
            Toast.makeText(this, "Please enter your name.", Toast.LENGTH_SHORT).show();
            return;
        }

        CheckBox addWhip = (CheckBox) findViewById(R.id.whipped_checkbox);
        boolean hasWhip = addWhip.isChecked();
        CheckBox addChoc = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChoc = addChoc.isChecked();

        if (addWhip.isChecked()) {
            price += 1;
        }
        if (addChoc.isChecked()) {
            price += 2;
        }
        int price = calculatePrice(hasWhip, hasChoc);
        String priceMessage = createOrderSummary(price, hasWhip, hasChoc, text);

//        String addresses = "bjvelez@me.com";
//        String subject = "Coffee order";
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
//        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
//        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
        displayMessage(priceMessage);
    }

    /**
     * Calculates the price of the order.
     *
     * @param hasWhip indicates if user selected whipped cream
     * @param hasChoc indicates if user selected chcolate
     */
    private int calculatePrice(boolean hasWhip, boolean hasChoc) {
        int priceOfCoffee = 10;

        if (hasWhip) {
            priceOfCoffee += 1;
        }
        if (hasChoc) {
            priceOfCoffee += 2;
        }

        return priceOfCoffee * quantity;

    }

    /**
     * Builds an order summary message.
     *
     * @param priceOfCoffee is the price per cup of coffee ordered
     * @param addWhip       is the Whipped cream checkbox
     */
    private String createOrderSummary(int priceOfCoffee, boolean addWhip, boolean addChoc, String nameText) {

        String orderSummary = getString(R.string.order_summary_name) + " " + nameText;
        //String orderSummary = getString(R.string.order_summary_name, nameText);
        orderSummary += "\nAdd " + getString(R.string.whipped) + "? " + addWhip;
        orderSummary += "\nAdd " + getString(R.string.chocolate) + "? " + addChoc;
        orderSummary += "\n" + getString(R.string.quantity) + ": " + quantity;
        orderSummary += "\n" + getString(R.string.total) + ": $" + priceOfCoffee;
        orderSummary += "\n" + getString(R.string.thank_you);
        return orderSummary;
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        } else {
            quantity++;
        }
        displayQuantity(quantity);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You cannot have fewer than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        } else {
            quantity--;
        }
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int orderAmt) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + orderAmt);
        //displayPrice(price);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}