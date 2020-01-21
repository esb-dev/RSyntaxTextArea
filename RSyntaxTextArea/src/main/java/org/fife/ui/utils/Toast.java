/*
 * Toast.java - Java program that creates the toast message
 * from https://www.javatips.net/
 */

package org.fife.ui.utils;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toast extends JDialog {

	private static final int DURATION = 1500;

	String message;
	JFrame frame;

	private Toast(final JFrame f, String m) {
		super(f, false);
		frame = f;
		message = m;
		initComponents();
	}

	private void initComponents() {
		setLayout(new GridBagLayout());

		setFocusableWindowState(false);
		setUndecorated(true);
		setSize(new Dimension(300, 50));
		setLocationRelativeTo(frame);
		getContentPane().setBackground(Color.BLACK);

		final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		final GraphicsDevice gd = ge.getDefaultScreenDevice();
		final boolean isTranslucencySupported = gd.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.TRANSLUCENT);

		if (isTranslucencySupported) {
			setOpacity(0.5f);
		}

		final JLabel label = new JLabel();
		label.setForeground(Color.WHITE);
		label.setText(message);
		add(label);
	}

	public static void displayToast(final JFrame f, final String msg) {
		final JDialog dialog = new Toast(f, msg);
		final Timer timer = new Timer(DURATION, new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				dialog.setVisible(false);
				dialog.dispose();
			}
		});
		timer.setRepeats(false);
		timer.start();
		dialog.setVisible(true);
	}
}


