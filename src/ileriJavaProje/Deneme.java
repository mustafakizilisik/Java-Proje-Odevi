package ileriJavaProje;

import javax.swing.*;

import java.net.*;

import java.util.Scanner;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.io.*;

import java.sql.*;

import java.util.*;

public class Deneme extends JFrame {

	private static JLabel text = new JLabel("Ogrenci Ekle");

	private static JButton btn = new JButton("EKLE");

	private static JTextArea bilgipaneli = new JTextArea();

	private static JLabel ad = new JLabel("Ad :");

	private static JLabel soyad = new JLabel("Soyad :");

	private static JTextField txtad = new JTextField();

	private static JTextField txtsoyad = new JTextField();

	private static JLabel bos = new JLabel();

	private static Statement veritabaniKomutu;

	public Deneme() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setLayout(new GridLayout(3, 1));

		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(4, 1));

		panel.add(text);

		panel.add(bos);

		panel.add(ad);

		panel.add(txtad);

		panel.add(soyad);

		panel.add(txtsoyad);

		// JScrollPane pane=new JScrollPane();

		// pane.add(bilgipaneli);

		JPanel panel2 = new JPanel();

		panel2.setLayout(new GridLayout(3, 1));

		panel2.add(btn);

		panel2.add(bilgipaneli);

		bilgipaneli.setEditable(false);

		this.add(panel);

		this.add(panel2);

		bilgipaneli.setEditable(false);

		this.add(new JScrollPane(bilgipaneli));

		btn.addActionListener(e -> {

			try {

				butonIslemi(e);

			} catch (SQLException e1) {

				// TODO Auto-generated catch block

				e1.printStackTrace();

			}

		});

	}

	private Object butonIslemi(ActionEvent e) throws SQLException {

		String name = txtad.getText();

		String username = txtsoyad.getText();

		String kullaniciGiris = name + " " + username;

		String[] girdiler = kullaniciGiris.split(" ");

		String isim = girdiler[0];

		String soyisim = girdiler[1];

		String insertSql1 = String.format("INSERT INTO ogrenciler VALUES('%s','%s')", isim, soyisim);

		veritabaniKomutu.executeUpdate(insertSql1);

		bilgipaneli.setText("");

		ResultSet sonuclar = veritabaniKomutu.executeQuery("SELECT * FROM ogrenciler");

		while (sonuclar.next()) {

			String sonucIsim = sonuclar.getString("ad");

			String sonucSoyisim = sonuclar.getString("soyad");

			bilgipaneli.setText(bilgipaneli.getText() + sonucIsim + " " + sonucSoyisim + "\n");

		}

		txtad.setText(" ");

		txtsoyad.setText(" ");

		return e;

	}

	public static void main(String[] args) throws Exception {

		// TODO Auto-generated method stub

		Deneme ekran = new Deneme();

		ekran.setVisible(true);

		ekran.setSize(700, 400);

		// ekran.setResizable(false);

		ekran.setLocation(250, 150);

		String jdbcUrl = "jdbc"

				+ ":derby:"

				+ "./.veritabani3"

				+ ";create=true";

		Connection veritabaniBaglantisi = DriverManager.getConnection(jdbcUrl);

		System.out.printf("Veritabanina baglandi: %s %n", veritabaniBaglantisi.toString());

		veritabaniKomutu = veritabaniBaglantisi.createStatement();

		try {

			String sql = "CREATE TABLE ogrenciler ( ad varchar(40), soyad varchar(40))";

			veritabaniKomutu.executeUpdate(sql);

		} catch (Exception e) {

			System.out.println("Tablo zaten var olabilir:" + e.getMessage());

		}

		ResultSet sonuclar = veritabaniKomutu.executeQuery("SELECT * FROM ogrenciler");

		while (sonuclar.next()) {

			String sonucIsim = sonuclar.getString("ad");

			String sonucSoyisim = sonuclar.getString("soyad");

			bilgipaneli.setText(bilgipaneli.getText() + sonucIsim + " " + sonucSoyisim + "\n");

		}

	}

}