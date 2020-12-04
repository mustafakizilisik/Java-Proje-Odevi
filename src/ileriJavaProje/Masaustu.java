package ileriJavaProje;


import javax.swing.*;

import java.awt.GridLayout;
import java.awt.event.*;

public class Masaustu extends JFrame {
	private JTextArea bilgiPaneli = new JTextArea();
	private JButton girisButonu = new JButton("Gönder");
	private JTextField kullaniciAdi = new JTextField();
	private JButton tikla = new JButton("Týkla");
	
	public Masaustu() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(2,1));

		bilgiPaneli.setEditable(false);
		this.add(new JScrollPane(bilgiPaneli));
		
		JPanel girisPaneli=new JPanel();
		girisPaneli.setLayout(new GridLayout(3,1));
		girisPaneli.add(kullaniciAdi);
		girisPaneli.add(girisButonu);
		girisPaneli.add(tikla);
		this.add(girisPaneli);
		
		//yontem1
		girisButonu.addActionListener(e ->{ //bir kaç satýr iþlem yapýlýyor
			
			
			butonIslemi(e);
		});
	}
	
	private  Object butonIslemi(ActionEvent e) {
		System.out.println("buton týklandý");
		
		String textInput = kullaniciAdi.getText();
		String s1="";
		String s2="";
		int sayi1, sayi2, sonuc;
		char harf;
		
		for (int i=0; i<textInput.length(); i++) {
			
			harf = textInput.charAt(i);
			
			if ( harf != '+' ) {
				s1 += String.valueOf(textInput.charAt(i));
			}
			else {
				i++;
				while(i < textInput.length()) {
					s2 += String.valueOf(textInput.charAt(i));
					i++;
				}
			}
		}
		
		sayi1 = Integer.parseInt(s1);
		sayi2 = Integer.parseInt(s2);
		sonuc = sayi1 + sayi2;
		
		if(textInput!=null && textInput.trim().length()> 0) {
			
			bilgiPaneli.setText( bilgiPaneli.getText() +"\n"+ sayi1 + " + " + sayi2 + " = " + sonuc);
			
			bilgiPaneli.setCaretPosition(bilgiPaneli.getDocument().getLength());
		} 
		kullaniciAdi.setText("");
		
		return e;
	}
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Masaustu ekran=new Masaustu();
		
		ekran.setVisible(true);
		
		ekran.setSize(500, 400);
		
		ekran.setTitle("Ornek 1");
		
		ekran.setResizable(false); //boyutu degismiyor
		
		ekran.setLocation(250,150); //koordinat vererek program orada çalýþýyor
		
		
		
		
	}

}