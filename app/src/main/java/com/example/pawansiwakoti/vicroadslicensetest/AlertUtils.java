package com.example.pawansiwakoti.vicroadslicensetest;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class AlertUtils {
    /**
     * Static function to display simple alert with yes and no option to select
     * @param context
     * @param title
     * @param message
     * @param positiveButtonText
     * @param negativeButtonText
     * @param positiveListener
     * @param cancelListener
     */
    public static void displayYesNoAlert(Context context, String title, String message, String positiveButtonText, String negativeButtonText,
                                         DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener cancelListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setTitle(title)
                .setPositiveButton(positiveButtonText, positiveListener)
                .setNegativeButton(negativeButtonText, cancelListener);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Method to display simple alert only
     * @param context
     * @param title
     * @param message
     * @example displayAlertOnly(context, "My Title", "My Message")
     */
    public static void displayAlertOnly(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setTitle(title)
                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog =builder.create();
        dialog.show();
    }
}