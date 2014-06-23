package example.musica;

import java.util.concurrent.TimeUnit;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public TextView tiempoRestante, tvCorrectas, tvIncorrectas;
	public String tiempo = "0";
	public int correctas;
	public int incorrectas;

	private double startTime = 0;

	private Handler handler1 = new Handler();
	private Runnable Hilo = new Runnable() {
		public void run() {
			alctualizaTiempo();
			abrePopup();
		}
	};

	MediaPlayer mp;
	int posicion = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tiempoRestante = (TextView) findViewById(R.id.tvTiempoRestante);
		tvCorrectas = (TextView) findViewById(R.id.tvCorrectas);
		tvIncorrectas = (TextView) findViewById(R.id.tvIncorrectas);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void iniciarHilo() {
		final Thread thread1 = new Thread() {
			public void run() {
				try {

					while (true) {
						Thread.sleep(1000);
						handler1.post(Hilo);

					}

				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		};
		thread1.start();
	}

	public void destruir() {
		if (mp != null)
			mp.release();
	}

	public void iniciar(View v) {
		destruir();
		mp = MediaPlayer.create(this, R.raw.my_boo);
		mp.start();

		incorrectas = 0;
		tvIncorrectas.setText(Integer.toString(incorrectas));
		correctas = 0;
		tvCorrectas.setText(Integer.toString(correctas));
		tiempoRestante.setText("0");

		iniciarHilo();
	}

	public void pausar(View v) {
		if (mp != null && mp.isPlaying()) {
			posicion = mp.getCurrentPosition();
			mp.pause();
		}
	}

	public void continuar(View v) {
		if (mp != null && mp.isPlaying() == false) {
			mp.seekTo(posicion);
			mp.start();
		}
	}

	public void detener(View v) {
		if (mp != null) {
			mp.stop();
			posicion = 0;
			incorrectas = 0;
			tvIncorrectas.setText(Integer.toString(incorrectas));
			correctas = 0;
			tvCorrectas.setText(Integer.toString(correctas));
			tiempoRestante.setText("0");

		}
	}

	public void alctualizaTiempo() {

		System.out.println(mp.getCurrentPosition());
		startTime = mp.getCurrentPosition();

		tiempoRestante.setText(String.format(
				"%d min, %d sec",
				TimeUnit.MILLISECONDS.toMinutes((long) startTime),
				TimeUnit.MILLISECONDS.toSeconds((long) startTime)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
								.toMinutes((long) startTime))));

		tiempo = tiempoRestante.getText().toString();

		System.out.println(tiempo);
	}

	public void abrePopup() {
		final View v = null;

		if (tiempo.equals("0 min, 22 sec")) {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pausar(v);

			final CharSequence[] items = { "it's clear for everyone to see.",
					"Ooh baby, you'll always be my boo.", };

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("What comes after? Know that you're that one for me.");
			builder.setSingleChoiceItems(items, -1,
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int item) {

							if (items[item]
									.equals("it's clear for everyone to see.")) {

								Toast toast = Toast.makeText(
										getApplicationContext(),
										"Haz elegido la opcion Correcta: "
												+ items[item],
										Toast.LENGTH_SHORT);
								toast.show();
								dialog.cancel();
								correctas++;
								tvCorrectas.setText(Integer.toString(correctas));
								continuar(v);
							} else {

								Toast toast = Toast.makeText(
										getApplicationContext(),
										"Haz elegido la opcion Incorrecta: "
												+ items[item],
										Toast.LENGTH_SHORT);
								toast.show();
								dialog.cancel();
								incorrectas++;
								tvIncorrectas.setText(Integer
										.toString(incorrectas));
								continuar(v);

							}

						}
					});
			AlertDialog alert = builder.create();
			alert.show();

		}

		if (tiempo.equals("0 min, 54 sec")) {

			try {

				Thread.sleep(1000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pausar(v);

			final CharSequence[] items = { "Future", "Past", "Present Simple" };

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("I was there and you were my baby. This sentences is?");
			builder.setSingleChoiceItems(items, -1,
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int item) {

							if (items[item].equals("Past")) {

								Toast toast = Toast.makeText(
										getApplicationContext(),
										"Haz elegido la opcion Correcta: "
												+ items[item],
										Toast.LENGTH_SHORT);
								toast.show();
								dialog.cancel();
								correctas++;
								tvCorrectas.setText(Integer.toString(correctas));
								continuar(v);
							} else {

								Toast toast = Toast.makeText(
										getApplicationContext(),
										"Haz elegido la opcion Incorrecta: "
												+ items[item],
										Toast.LENGTH_SHORT);
								toast.show();
								dialog.cancel();
								incorrectas++;
								tvIncorrectas.setText(Integer
										.toString(incorrectas));
								continuar(v);

							}

						}
					});
			AlertDialog alert = builder.create();
			alert.show();

		}

		if (tiempo.equals("1 min, 18 sec")) {

			try {

				Thread.sleep(1000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pausar(v);

			final CharSequence[] items = { "Wife", "Girlfriend", "Friend" };

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("The title - MY BOO - means?");
			builder.setSingleChoiceItems(items, -1,
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int item) {

							if (items[item].equals("Girlfriend")) {

								Toast toast = Toast.makeText(
										getApplicationContext(),
										"Haz elegido la opcion Correcta: "
												+ items[item],
										Toast.LENGTH_SHORT);
								toast.show();
								dialog.cancel();
								correctas++;
								tvCorrectas.setText(Integer.toString(correctas));
								continuar(v);
							} else {

								Toast toast = Toast.makeText(
										getApplicationContext(),
										"Haz elegido la opcion Incorrecta: "
												+ items[item],
										Toast.LENGTH_SHORT);
								toast.show();
								dialog.cancel();
								incorrectas++;
								tvIncorrectas.setText(Integer
										.toString(incorrectas));
								continuar(v);

							}

						}
					});
			AlertDialog alert = builder.create();
			alert.show();

		}

		if (tiempo.equals("1 min, 42 sec")) {

			try {

				Thread.sleep(1000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pausar(v);

			final CharSequence[] items = { "Usualy", "Never", "Always" };

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Complete: You will ---- be my boo.");
			builder.setSingleChoiceItems(items, -1,
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int item) {

							if (items[item].equals("Always")) {

								Toast toast = Toast.makeText(
										getApplicationContext(),
										"Haz elegido la opcion Correcta: "
												+ items[item],
										Toast.LENGTH_SHORT);
								toast.show();
								dialog.cancel();
								correctas++;
								tvCorrectas.setText(Integer.toString(correctas));
								continuar(v);
							} else {

								Toast toast = Toast.makeText(
										getApplicationContext(),
										"Haz elegido la opcion Incorrecta: "
												+ items[item],
										Toast.LENGTH_SHORT);
								toast.show();
								dialog.cancel();
								incorrectas++;
								tvIncorrectas.setText(Integer
										.toString(incorrectas));
								continuar(v);

							}

						}
					});
			AlertDialog alert = builder.create();
			alert.show();

		}

		if (tiempo.equals("2 min, 1 sec")) {

			try {

				Thread.sleep(1000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pausar(v);

			final CharSequence[] items = {
					"Yo solo puedo dormir acerca de tus labios",
					"Yo solo puedo sentir tus labios",
					"Yo solo puedo pensar acerca de tus labios" };

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Translate: I can only thik about you're lips.");
			builder.setSingleChoiceItems(items, -1,
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int item) {

							if (items[item].equals("Yo solo puedo pensar acerca de tus labios")) {

								Toast toast = Toast.makeText(
										getApplicationContext(),
										"Haz elegido la opcion Correcta: "
												+ items[item],
										Toast.LENGTH_SHORT);
								toast.show();
								dialog.cancel();
								correctas++;
								tvCorrectas.setText(Integer.toString(correctas));
								continuar(v);
							} else {

								Toast toast = Toast.makeText(
										getApplicationContext(),
										"Haz elegido la opcion Incorrecta: "
												+ items[item],
										Toast.LENGTH_SHORT);
								toast.show();
								dialog.cancel();
								incorrectas++;
								tvIncorrectas.setText(Integer
										.toString(incorrectas));
								continuar(v);

							}

						}
					});
			AlertDialog alert = builder.create();
			alert.show();

		}

	}

}
