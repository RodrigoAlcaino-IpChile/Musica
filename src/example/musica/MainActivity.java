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
	public int correctas = 0;
	public int incorrectas = 0;

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
		

		if (tiempo.equals("0 min, 5 sec")) {

			try {
				Thread.sleep(1000);
				final View v = null;
				pausar(v);
				
				final CharSequence[] items = {
						"Existe siempre esa unica persona que siempre tendra tu corazon",
						"No se todo acerca de ti pero yo se de nosotros",
						"recuerdas nena, quien era el que te dio tu primer beso"
				};

				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("De Que Habla Esta Primera Estrofa?");
				builder.setSingleChoiceItems(items, -1,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog, int item) {

								if (items[item].equals("Existe siempre esa unica persona que siempre tendra tu corazon")) {

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
									tvIncorrectas.setText(Integer.toString(incorrectas));
									continuar(v);

								}

							}
						});
				AlertDialog alert = builder.create();
				alert.show();

				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			if (tiempo.equals("0 min, 20 sec")) {

				try {

					Thread.sleep(1000);
					final View v = null;
					pausar(v);

					final CharSequence[] items = { "Android OS", "iOS",
							"Windows Phone", "Meego" };

					AlertDialog.Builder builder = new AlertDialog.Builder(this);
					builder.setTitle("Tu OS móvil preferido?");
					builder.setSingleChoiceItems(items, -1,
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int item) {

									if (items[item].equals("iOS")) {

										Toast toast = Toast.makeText(
												getApplicationContext(),
												"Haz elegido la opcion: "
														+ items[item],
												Toast.LENGTH_SHORT);
										toast.show();
										dialog.cancel();

										continuar(v);
									} else {

										Toast toast = Toast.makeText(
												getApplicationContext(),
												"Haz elegido la opcion Incorrecta: "
														+ items[item],
												Toast.LENGTH_SHORT);
										toast.show();
										dialog.cancel();

										continuar(v);

									}

								}
							});
					AlertDialog alert = builder.create();
					alert.show();

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

			} else {

			}

		}

	}

}
