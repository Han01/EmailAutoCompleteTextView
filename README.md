EmailAutoCompleteTextView
=========================

EmailAutoCompleteTextView uses the unique e-mails associated with the device to provide auto-complete functionality for e-mail input fields. It also provides an "x" button on the right hand side as an easy way to clear text. 

![EmailAutoCompleteTextView Screenshot][1]

EmailAutoCompleteTextView is simple to integrate in your project. Simply download and include in your project via XML or programmatically. **NOTE**: since this view uses [AccountManager][2] to get the e-mails on the device, you must include the following in your `AndroidManifest.xml`:

```
  <uses-permission android:name="android.permission.GET_ACCOUNTS"/>
```

Please view the sample application under [example/][3] for more details.


## Including EmailAutoCompleteTextView using Gradle:

Add the following to your `build.gradle`:

```
	repositories {
    	mavenCentral()
	}
	dependencies {
		'com.greenhalolabs.emailautocompletetextview:library:1.1.0'
	}
```

## Usage:

In XML:

```
    <com.greenhalolabs.emailautocompletetextview.EmailAutoCompleteTextView
    	xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="@string/enter_an_email"
        android:layout_alignParentTop="true"
        app:clearButtonDrawable="@drawable/close" />
```

Programmatically:

```
        EmailAutoCompleteTextView emailAutoCompleteTextView = new EmailAutoCompleteTextView(context);
        emailAutoCompleteTextView.setHint(R.string.enter_an_email);
        emailAutoCompleteTextView.setClearButtonEnabled(true); // defaults to true
        emailAutoCompleteTextView.setClearButtonResId(R.drawable.close);

```


[1]: https://raw.github.com/greenhalolabs/EmailAutoCompleteTextView/master/images/EmailAutoCompleteTextView_demo.gif
[2]: http://developer.android.com/reference/android/accounts/AccountManager.html
[3]: https://github.com/greenhalolabs/EmailAutoCompleteTextView/tree/master/example