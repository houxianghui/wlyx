package com.blue.frame;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

public class MyOutputStream extends OutputStream {
	private JTextArea jta;
	public MyOutputStream(JTextArea jta) {
		this.jta = jta;
	}
	@Override
	public void write(int b) throws IOException {

	}
	@Override
	public void write(byte[] b) throws IOException {
		jta.append(new String(b));
	}
	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		jta.append(new String(b,off,len));
		jta.setCaretPosition(jta.getText().length());
	}

}
