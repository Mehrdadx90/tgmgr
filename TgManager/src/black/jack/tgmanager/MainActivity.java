package black.jack.tgmanager;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ProgressDialog dialog;
	ArrayList<String> apiadds = new ArrayList<String>();
	ArrayList<String> userid = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		apiadds.add(" ");
		userid.add(" ");
		final EditText text = (EditText) findViewById(R.id.txtText);
		final Spinner id = (Spinner) findViewById(R.id.spnid);
		final Spinner api = (Spinner) findViewById(R.id.spnapi);
		final Button bold = (Button) findViewById(R.id.btnbold);
		final Button italic = (Button) findViewById(R.id.btnitalic);
		final Button hyper = (Button) findViewById(R.id.btnhyper);
		final Button pre = (Button) findViewById(R.id.btnpre);
		final Button delid = (Button) findViewById(R.id.btndelid);
		final Button delapi = (Button) findViewById(R.id.btndelapi);
		final CheckBox mute = (CheckBox) findViewById(R.id.chkmute);
		final CheckBox wv = (CheckBox) findViewById(R.id.chkwv);
		final WebView loader = (WebView) findViewById(R.id.loader);
		Button send = (Button) findViewById(R.id.btnSend);
		bold.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				int first = text.getSelectionStart();
				String bef = text.getText().toString().substring(0, first);
				String af = text.getText().toString().substring(first);
				text.setText(bef + "<b>متن مورد نظر</b>" + af);
				text.setSelection(first + 3,first + 15);
			}
		});
		italic.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				int first = text.getSelectionStart();
				String bef = text.getText().toString().substring(0, first);
				String af = text.getText().toString().substring(first);
				text.setText(bef + "<i>متن مورد نظر</i>" + af);
				text.setSelection(first + 3,first + 15);
			}
		});
		pre.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				int first = text.getSelectionStart();
				String bef = text.getText().toString().substring(0, first);
				String af = text.getText().toString().substring(first);
				text.setText(bef + "<pre>متن مورد نظر</pre>" + af);
				text.setSelection(first + 6 ,first + 15);
			}
		});
		hyper.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				int first = text.getSelectionStart();
				String bef = text.getText().toString().substring(0, first);
				String af = text.getText().toString().substring(first);
				text.setText(bef + "<a href=link> متن مورد نظر</a>" + af);
				text.setSelection(first + 8,first + 15);
			}
		});
		delid.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (!id.getSelectedItem().toString().equals(" ")) {
					SQLiteDatabase mydb = openOrCreateDatabase("enteries", Context.MODE_PRIVATE, null);
					mydb.execSQL("DELETE FROM recs WHERE ids LIKE '" + id.getSelectedItem().toString() + "';");
					reset();
				} else {
					Toast.makeText(MainActivity.this, "هیچ گیرنده ای انتخاب تشده!", Toast.LENGTH_LONG).show();
				}

			}
		});
		delapi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (!api.getSelectedItem().toString().equals(" ")) {
					SQLiteDatabase mydb = openOrCreateDatabase("enteries", Context.MODE_PRIVATE, null);
					mydb.execSQL("DELETE FROM recs WHERE apiaddress LIKE '" + api.getSelectedItem().toString() + "';");
					reset();
				} else {
					Toast.makeText(MainActivity.this, "هیچ رباتی انتخاب تشده!", Toast.LENGTH_LONG).show();
				}

			}
		});
		dialog = new ProgressDialog(MainActivity.this);
		dialog.setMessage("در حال ارسال پیام.");
		dialog.setCancelable(false);
		loader.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				if (progress == 100) {
					dialog.cancel();
				} else {
					dialog.show();
				}
			}
		});
		// ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓
		// ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓ دست نزن !
		// ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓
		// ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓

		SQLiteDatabase mydb = openOrCreateDatabase("enteries", Context.MODE_PRIVATE, null);
		mydb.execSQL("CREATE TABLE IF NOT EXISTS recs (id INTEGER PRIMARY KEY, apiaddress TEXT, ids TEXT);");
		Cursor numapi = mydb.rawQuery("SELECT apiaddress,ids FROM recs", null);
		if (numapi.moveToFirst()) {
			do {
				try {
					apiadds.add(new String(numapi.getString(0).toString()));
				} catch (Exception e) {
				}
				try {
					userid.add(new String(numapi.getString(1).toString()));
				} catch (Exception e) {
				}
			} while (numapi.moveToNext());
		}
		apiadds.add("+ افزودن ربات جدید");
		userid.add("+ افزودن گیرنده جدید");
		// ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓
		id.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				String sel = id.getSelectedItem().toString();
				if (sel == "+ افزودن گیرنده جدید") {
					AlertDialog.Builder builder = new Builder(MainActivity.this);
					final EditText text = new EditText(MainActivity.this);
					text.setInputType(InputType.TYPE_CLASS_NUMBER);
					builder.setTitle("گیرنده جدید")
							.setMessage(
									"درصورتی که گیرنده مخاطب خاصی باشد میتوانید آی دی فرد مورد نظر رو توسط ربات UserInfoBot@ بدست بیارید.  "
											+ '\n' + " (برای مثال  285095234)" + '\n' + '\n'
											+ "درصورتی که میخواهید مستفیما در کانال پست بفرستید از یوزر نیم کانال استفاده کنید."
											+ '\n' + "(برای مثال Telegram@)" + '\n' + '\n' + "توجه!" + '\n'
											+ "فرد دریافت کننده حتما باید عضو ربات شما باشد! برای ارسال پیام در کانال باید ربات خود را مدیر کانال کنید.")
							.setView(text);
					builder.setPositiveButton("ثبت", new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface di, int i) {
							final String id = text.getText().toString();
							SQLiteDatabase mydb = openOrCreateDatabase("enteries", Context.MODE_PRIVATE, null);
							mydb.execSQL("INSERT INTO recs (ids) VALUES('" + id + "')");
							reset();
						}

					});
					builder.setCancelable(false);
					builder.show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, userid);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		id.setAdapter(adapter);
		// ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓
		// ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓
		api.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				String sel = api.getSelectedItem().toString();
				if (sel == "+ افزودن ربات جدید") {
					AlertDialog.Builder builder = new Builder(MainActivity.this);
					final EditText text = new EditText(MainActivity.this);
					text.setInputType(InputType.TYPE_CLASS_TEXT);
					builder.setTitle("ربات جدید")
							.setMessage("API یا کد انحصاری ربات خود رو وارد کنید." + '\n'
									+ "میتونید با استفاده از ربات BotFather@ توکن یا API ربات خودتون رو دریافت کنید."
									+ '\n' + "مثال:" + '\n' + "123456:ABC-DEF1234ghIkl-zyx57W2v1u123ew11")
							.setView(text);
					builder.setPositiveButton("ثبت", new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface di, int i) {
							final String id = text.getText().toString();
							SQLiteDatabase mydb = openOrCreateDatabase("enteries", Context.MODE_PRIVATE, null);
							mydb.execSQL("INSERT INTO recs (apiaddress) VALUES('" + id + "')");
							Intent i2 = getBaseContext().getPackageManager()
									.getLaunchIntentForPackage(getBaseContext().getPackageName());
							i2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(i2);
						}
					});
					builder.setCancelable(false);
					builder.show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, apiadds);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		api.setAdapter(adapter2);
		// ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓
		send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (api.getSelectedItem().toString().equals(" ") || id.getSelectedItem().toString().equals(" ")
						|| text.getText().toString().isEmpty()) {
					Toast.makeText(MainActivity.this, "تمام فیلد هارو پر کنید!", Toast.LENGTH_LONG).show();
				} else {
					String request = "https://api.telegram.org/bot" + api.getSelectedItem().toString()
							+ "/SendMessage?chat_id=" + id.getSelectedItem().toString() + "&disable_web_page_preview="
							+ wv.isChecked() + "&disable_notification=" + mute.isChecked() + "&parse_mode=html&text="
							+ text.getText().toString().replaceAll( "\n" , "%0A");
					loader.loadUrl(request);
				}
			}
		});

	}

	public void reset() {
		// TODO Auto-generated method stub
		Intent i2 = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
		i2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i2);
	}
}
