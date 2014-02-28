EmailAutoCompleteTextView
=========================

EmailAutoCompleteTextView uses the unique e-mails associated with the device to provide auto-complete functionality for e-mail input fields. It also provides an "x" button on the right hand side as an easy way to clear text. 

![EmailAutoCompleteTextView Screenshot][1]

EmailAutoCompleteTextView is simple to integrate in your project. Simply download and include in your project via XML or programmatically. **NOTE**: since this view uses [AccountManager][2] to get the e-mails on the device, you must include the following in your `AndroidManifest.xml`:

```
  <uses-permission android:name="android.permission.GET_ACCOUNTS"/>
```

Please view the sample application under [example/][3] for more details.


### Including EmailAutoCompleteTextView using Gradle:

Add the following to your `build.gradle`:

```
	repositories {
    	maven { url "https://oss.sonatype.org/content/repositories/snapshots/" }
	}
	dependencies {
		'com.greenhalolabs.emailautocompletetextview:library:1.0.1-SNAPSHOT'
	}
```

[1]: https://raw.github.com/greenhalolabs/EmailAutoCompleteTextView/master/images/emailautocompletetextview_screenshot.png
[2]: http://developer.android.com/reference/android/accounts/AccountManager.html
[3]: https://github.com/greenhalolabs/EmailAutoCompleteTextView/tree/master/example