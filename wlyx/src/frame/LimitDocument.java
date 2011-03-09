package frame;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

public class LimitDocument extends PlainDocument {
	private JTextComponent jtc;
	private int limit = 30;
	public LimitDocument(JTextComponent jtc,int limit) {
		this.jtc = jtc;
		this.limit = limit;
	}
	public LimitDocument(JTextComponent jtc) {
		this.jtc = jtc;
	}
	@Override
	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {
		String value = jtc.getText();
		int overrun = 0;
		if(value!=null && value.split("\n").length >= limit){
			overrun = value.indexOf("\n")+1;
			super.remove(0, overrun);
		}
		super.insertString(offs, str, a);
	}
	
}
