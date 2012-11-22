import java.util.ArrayList;
import java.util.Collections;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class blackjack extends JFrame{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton nupp;
	private JButton nuppok;
	private JButton uusm;
	private JButton b;
	private JButton abi;
	private Color color=(new Color(19,152,42));
	JFrame frame = new JFrame("frame");
	JLabel totallabel = new JLabel("Punkte kokku: ");
	ImageIcon[] sinukaart = new ImageIcon[20];
  	JLabel[] sinukaartLabel=new JLabel[10];
  	ImageIcon[] vastasekaart = new ImageIcon[10];
  	ImageIcon[] vastasekaartLeht = new ImageIcon[10];
  	JLabel[] vastasekaartLabel = new JLabel[10];
	int nr = 0;
	int kordaja=0;
	int total=0;
	int totalvastane=0;
	int vaartus[] = new int[53];
  	int sinukasi[] = new int[53];
  	int vastasekasi[] = new int[53];
  	int d = 2;
  	int g = 4;
  	int f = 4;
  	int x = 3;
  	int m = 3;
  	 ArrayList<Integer> kaardipakk = new ArrayList<Integer>(); //Teen kaardipaki massiivi
	
    public  blackjack(){
    super("Blacjack -- Mairo & Ahti");
    setLayout(null);
    getContentPane().setBackground(color); 
    setResizable(false);
    
    //Lisab logo framele
    ImageIcon logo = new ImageIcon("pakk\\logo.png");
    JLabel logolabel = new JLabel(logo);
    logolabel.setBounds(10,50,200,200);
    add(logolabel);
    
    // taustavärvi muutmiseks actionlistener
    b = new JButton("Taustavärv");
	b.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent event){
					color = JColorChooser.showDialog(null, "Vali taustavärv", color);
					if(color == null)
						color=(new Color(19,152,42));
					
					getContentPane().setBackground(color); 
				}
			}
			);
	b.setBounds(20,515,100,25);
  	add(b);
   
   
   //lisab tsükkliga kaardipakki kaartide väärtused
    for (int u=1;u<=52;u++){
    kaardipakk.add(u);
    }

    Collections.shuffle(kaardipakk);//segab kaardid ära  

  	
    //kaartidele väärtus
  	for (int j = 1;j<=52;j++){
  		vaartus[j] = d;
  		if (j == g) {
  			g = g+4;
  			if (d == 10 && f >= 2){
  				f = f-1;
  			}else{
  			d = d+1;
  			}
  			}
  		}

  	
  	//Väljastab mängija käe
  	sinukasi[1] = kaardipakk.get(kordaja);//Esimene kaart
  	sinukaart[1]=new ImageIcon("pakk\\"+sinukasi[1]+".png"); //Otsib pildi
  	sinukaartLabel[kordaja]=new JLabel(sinukaart[1]); // teeb pildist labeli
  	sinukaartLabel[kordaja].setBounds(210, 400,72,96); // määrab asukoha labelile
  	add(sinukaartLabel[kordaja]); // lisab framele
  	kordaja++; // 1. kaart on võetud
  	
  	sinukasi[2] = kaardipakk.get(kordaja);//Teine kaart
  	sinukaart[2]=new ImageIcon("pakk\\"+sinukasi[2]+".png");
  	sinukaartLabel[kordaja]=new JLabel(sinukaart[2]);
  	sinukaartLabel[kordaja].setBounds(210+1*75, 400,72,96);
  	add(sinukaartLabel[kordaja]);
  	kordaja++;
  	
  	total = total+vaartus[sinukasi[1]]+vaartus[sinukasi[2]]; //Arvutab punktid

  	//Lisab Framele punktid
  	totallabel.setText("Punkte kokku: "+total);
  	totallabel.setBounds(20,295,100,30);
  	add(totallabel);

	//Väljastab vastase käe I lehe  	
  	vastasekasi[1] = kaardipakk.get(kordaja);//Vastase esimene kaart
  	vastasekaart[0]=new ImageIcon("pakk\\tagant.png");
  	vastasekaartLabel[1] = new JLabel(vastasekaart[0]);	
  	vastasekaartLabel[1].setBounds(210, 10,72,96);
  	add(vastasekaartLabel[1]);
  	kordaja++;
  	
  	vastasekasi[2] = kaardipakk.get(kordaja);//Vastase teine kaart
  	vastasekaartLabel[2] = new JLabel(vastasekaart[0]);	
  	vastasekaartLabel[2].setBounds(210+1*75, 10,72,96);
  	add(vastasekaartLabel[2]);
  	kordaja++; 
  	
  	totalvastane = totalvastane+vaartus[vastasekasi[1]]+vaartus[vastasekasi[2]]; //Arvutab vastase punktid
  	
  	//Lisab framele kaardipaki keskele
  	vastasekaartLabel[3] = new JLabel(vastasekaart[0]);	
  	vastasekaartLabel[3].setBounds(285, 200,72,96);
  	add(vastasekaartLabel[3]);
  	
  	
  	
  	//Lisab nupud framele
  	nupp = new JButton("Juurde");
  	nupp.setBounds(20,380,100,25);
  	add(nupp);
  	nuppok = new JButton("Lõpeta");
  	nuppok.setBounds(20,425,100,25);
  	add(nuppok);
  	uusm = new JButton("Uus Mäng");
  	uusm.setBounds(20,470,100,25);
  	add(uusm);
  	abi = new JButton("Abi");
  	abi.setBounds(20,335,100,25);
  	add(abi);
  	
  	//Juurde nupu tegevus
	nupp.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent event){
					validate();
				  	repaint();
				  	juurde();
			}
			}
			);
	
	//Lõpeta nupu tegevus
	nuppok.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent event){
					validate();
				  	repaint();
				  	ok();
			}
			}
			);
	
	// Uue mängu tegevus
	uusm.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent event){
					
					vastasekaartLabel[m] = new JLabel(vastasekaart[0]);	
			      	vastasekaartLabel[m].setBounds(125+m*75, 10,72,96);
			      	add(vastasekaartLabel[m]);
			}
			}
			);
	
	// Abi nupu tegevus
		abi.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						 JOptionPane.showMessageDialog(null, "Igale mängijale jagatakse kaks kaarti ning pakutakse seejärel \n võimalust lisakaartide võtmiseks.Suurema punktisummaga 'käsi' \n võidab, juhul, kui punktisumma ei ületa 21-te; 'käsi', mille \n punktisumma on suurem kui 21 loetakse kaotanuks (busted ehk 'üle').\n Kaardid 2-st 10-ni on nominaalväärtusega ning pildikaartide \n väärtuseks on kümme punkti (poisid, emandad, kuningad). \n Äss on kas 1 või 11 punkti, sõltuvalt sellest kas punktide \n kogusumma püsib 21-e piires. 'Käsi', milles ässa loetakse 1-ks\n punktiks, tuntakse kui 'pehme käsi' (soft hand), sest punkte \n ei saa järgneva lisakaardiga 'üle võtta'.\n ","Abi",JOptionPane.PLAIN_MESSAGE);
				}
				}
				);
	
	
}

 
    
 //Juurde 
public void juurde(){
	 if (total<=21) {
	    	sinukasi[x] = kaardipakk.get(kordaja); //uus leht
	    	sinukaart[x]=new ImageIcon("pakk\\"+sinukasi[x]+".png");
	      	sinukaartLabel[kordaja]=new JLabel(sinukaart[x]);
	      	sinukaartLabel[kordaja].setBounds(135+x*75, 400,72,96);
	      	add(sinukaartLabel[kordaja]);
	    	kordaja++;
	    	total = 0; //paneb summa nulli
	    	//arvutab uue summa
	    	for (int k=1;k<=x;k++){
	    		total=vaartus[sinukasi[k]]+total;
	    	}
	    	x=x+1;
	    }else{
	    	//ok();
	    }
	    	
	    //ässad
	    for (int r=1;r<=x;r++){
	    	if (vaartus[sinukasi[r]] == 11 && total > 21){
	    		total = total - 10;
	    		vaartus[sinukasi[r]] = 1;
	    	}
	    }
	    totallabel.setText("Punkte kokku: "+total);
}

// Vastane võtab enda lehed ja tulemus kas siis võit või kaotus
public void ok(){
	 
	 while (total<=21 && totalvastane<total){
	    	vastasekasi[m] = kaardipakk.get(kordaja);
	    	vastasekaartLabel[m] = new JLabel(vastasekaart[0]);	
	      	vastasekaartLabel[m].setBounds(135+m*75, 10,72,96);
	      	add(vastasekaartLabel[m]);
	    	kordaja++;
	    	totalvastane = 0;
	    	for (int s=1;s<=m;s++){
	    		totalvastane=vaartus[vastasekasi[s]]+totalvastane;
	    	}
	    	//vastase ässad
	    	for (int u=1; u<=m;u++){
	    			if (vaartus[vastasekasi[u]] == 11 && totalvastane > 21){
	    				totalvastane = totalvastane - 10;
	    				vaartus[vastasekasi[u]] = 1;
	    	}
	    	}
	    	m=m+1;
	    	//nr=0;
	    }  
	 
	 
   if (total > 21){
	   naita();
	 	JOptionPane.showMessageDialog(null, "Kaotasid! Vastane sai: "+totalvastane+" punkti.","Väga kahju!",JOptionPane.PLAIN_MESSAGE);
   }else if (total < 22 && total < totalvastane && totalvastane < 22){
	 	naita();
	 	JOptionPane.showMessageDialog(null, "Kaotasid! Vastane sai: "+totalvastane+" punkti.","Väga kahju!",JOptionPane.PLAIN_MESSAGE);
   }else{
	   naita();
	   JOptionPane.showMessageDialog(null, "Võitsid! Vastane sai: "+totalvastane+" punkti.","Palju Õnne!",JOptionPane.PLAIN_MESSAGE);
   }
}

// Näitab vastase kaarte kui mängija on lõpetanud kaartide juurdevõtmise
public void naita(){
	for(int counter=1;counter<m;counter++){
   		vastasekaartLeht[counter]=new ImageIcon("pakk\\"+vastasekasi[counter]+".png");
   		vastasekaartLabel[counter] = new JLabel(vastasekaartLeht[counter]);	
      	vastasekaartLabel[counter].setBounds(145+counter*75, 50,72,96);
      	add(vastasekaartLabel[counter]);
	}
}
    
}