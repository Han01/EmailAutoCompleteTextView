package com.greenhalolabs.emailautocompletetextview.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chris on 2/23/14.
 */
public class AccountUtil {

    /**
     * Retrieves a list of e-mails on the device
     *
     * @return A list of emails
     */
    public static List<String> getAccountEmails(Context context) {
        
        final List<String> emailList = new ArrayList<String>();
        final Account[] accounts = AccountManager.get(context).getAccounts();

        for (Account account : accounts) {
            if (isEmail(account.name) && !emailList.contains(account.name)) {
                emailList.add(account.name);
            }
        }

        return emailList;
    }

    private static boolean isEmail(String email) {

        if (TextUtils.isEmpty(email)) {
            return false;
        }

        final Pattern emailPattern = Patterns.EMAIL_ADDRESS;
        final Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

}
