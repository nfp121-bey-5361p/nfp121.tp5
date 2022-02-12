package question2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

public class JPanelListe2 extends JPanel implements ActionListener, ItemListener {

    private JPanel cmd = new JPanel();
    private JLabel afficheur = new JLabel();
    private JTextField saisie = new JTextField();

    private JPanel panelBoutons = new JPanel();
    private JButton boutonRechercher = new JButton("rechercher");
    private JButton boutonRetirer = new JButton("retirer");

    private CheckboxGroup mode = new CheckboxGroup();
    private Checkbox ordreCroissant = new Checkbox("croissant", mode, false);
    private Checkbox ordreDecroissant = new Checkbox("décroissant", mode, false);

    private JButton boutonOccurrences = new JButton("occurrence");

    private JButton boutonAnnuler = new JButton("annuler");

    private TextArea texte = new TextArea();

    private List<String> liste;
    private Map<String, Integer> occurrences;
    
    private Caretaker caretaker;

    public JPanelListe2(List<String> liste, Map<String, Integer> occurrences) {
        this.liste = liste;
        this.occurrences = occurrences;
        this.caretaker = new Caretaker();
        
        cmd.setLayout(new GridLayout(3, 1));

        cmd.add(afficheur);
        cmd.add(saisie);

        panelBoutons.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelBoutons.add(boutonRechercher);
        panelBoutons.add(boutonRetirer);
        panelBoutons.add(new JLabel("tri du texte :"));
        panelBoutons.add(ordreCroissant);
        panelBoutons.add(ordreDecroissant);
        panelBoutons.add(boutonOccurrences);
        panelBoutons.add(boutonAnnuler);
        cmd.add(panelBoutons);


        if(liste!=null && occurrences!=null){
            afficheur.setText(liste.getClass().getName() + " et "+ occurrences.getClass().getName());
            texte.setText(liste.toString());
        }else{
            texte.setText("la classe Chapitre2CoreJava semble incomplète");
        }

        setLayout(new BorderLayout());

        add(cmd, "North");
        add(texte, "Center");

        boutonRechercher.addActionListener(this);
        boutonRechercher.addActionListener(this);
        saisie.addActionListener(this);
        boutonRetirer.addActionListener(this);
        ordreCroissant.addItemListener(this);
        ordreDecroissant.addItemListener(this);
        boutonOccurrences.addActionListener(this);
        boutonAnnuler.addActionListener(this);

    }

    public void actionPerformed(ActionEvent ae) {
        try {
            boolean res = false;
            if (ae.getSource() == boutonRechercher || ae.getSource() == saisie) {
                res = liste.contains(saisie.getText());
                Integer occur = occurrences.get(saisie.getText());
                afficheur.setText("résultat de la recherche de : "
                    + saisie.getText() + " -->  " + res);
            } else if (ae.getSource() == boutonRetirer) {
                caretaker.setMemento(createMemento());
                res = retirerDeLaListeTousLesElementsCommencantPar(saisie
                    .getText());
                afficheur
                .setText("résultat du retrait de tous les éléments commençant par -->  "
                    + saisie.getText() + " : " + res);
            } else if (ae.getSource() == boutonOccurrences) {
                Integer occur = occurrences.get(saisie.getText());
                if (occur != null)
                    afficheur.setText(" -->  " + occur + " occurrence(s)");
                else
                    afficheur.setText(" -->  ??? ");
            } else if (ae.getSource() == boutonAnnuler) {
                setMemento(caretaker.getMemento());
                recalculerOccurrence(liste);
            }
            
            texte.setText(liste.toString());

        } catch (Exception e) {
            afficheur.setText(e.toString());
        }
    }

    public void itemStateChanged(ItemEvent ie) {
        caretaker.setMemento(createMemento());
        if (ie.getSource() == ordreCroissant)
            Collections.sort(liste);
        else if (ie.getSource() == ordreDecroissant) {
            Collections.sort(liste, new Comparator<String>() {
                @Override
                public int compare(String mot1, String mot2) {
                    return mot2.compareTo(mot1);
                } 
            });
        }
        texte.setText(liste.toString());
    }

    private boolean retirerDeLaListeTousLesElementsCommencantPar(String prefixe) {
        boolean resultat = false;
        List<String> motsAvecPrefixe = new LinkedList<String>();
        for (String mot : liste) {
            if (mot.startsWith(prefixe)) 
                motsAvecPrefixe.add(mot);
        }
        resultat = liste.removeAll(motsAvecPrefixe);
        if (resultat)
            resetOccurrence(motsAvecPrefixe);
        return resultat;
    }
    
    private void resetOccurrence(List<String> mots) {
        for (String mot : mots) {
            occurrences.put(mot, 0);
        }
    }
    
    private void recalculerOccurrence(List<String> mots) {
        occurrences = Chapitre2CoreJava2.occurrencesDesMots(mots);
    }
    
    public void setListe(List<String> liste) {
        this.liste = liste;
    }
    
    public Memento createMemento() {
        Memento memento = new Memento();
        memento.setState();
        
        return memento;
    }
    
    public void setMemento(Memento memento) {
        if (memento == null)
            return;
        memento.getState();
    }
    
    public class Memento {
        private List<String> mementoListe;
        
        public void setState() {
            mementoListe = new LinkedList<String>(liste);
        }
        
        public void getState() {
            setListe(mementoListe);
        }
    }

}