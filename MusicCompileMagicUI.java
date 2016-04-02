package dplmusiccompilemagic;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;

import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class MusicCompileMagicUI extends JFrame{
	
	private static Scanner reader;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFileSelect;
	private JFileChooser fileChooser;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MusicCompileMagicUI frame = new MusicCompileMagicUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MusicCompileMagicUI() {
		super("DPLMusicCompileMagic");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setSize(500, 100);
		setContentPane(contentPane);
		setResizable(false);
		
		JPanel panFileSelect = new JPanel();
		panFileSelect.setBackground(new Color(240, 240, 240));
		panFileSelect.setBorder(new TitledBorder(null, "Choose File", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		contentPane.add(panFileSelect, BorderLayout.NORTH);
		
		txtFileSelect = new JTextField();
		txtFileSelect.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtFileSelect.setEditable(false);
		panFileSelect.add(txtFileSelect);
		txtFileSelect.setColumns(25);
		
		JButton btnSelect = new JButton("Select");
		panFileSelect.add(btnSelect);
		btnSelect.setToolTipText("Select Lyrics Text File");
		btnSelect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
				fileChooser.setFileFilter(filter);
				int rVal = fileChooser.showOpenDialog(MusicCompileMagicUI.this);
				if (rVal == JFileChooser.APPROVE_OPTION){
					txtFileSelect.setText(fileChooser.getSelectedFile().getName());
			}
				
			}
		});
		
		JButton btnProcess = new JButton("Process");
		panFileSelect.add(btnProcess);
		btnProcess.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				File file = fileChooser.getSelectedFile();
				String file_name = file.toString();
				
				LexicalAnalysis lexical = new LexicalAnalysis();
				
				try{
					lexical.openFile(file_name);
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "There is an error opening the file", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				try{
				lexical.readFile();
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Error with reading file", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				try{
				lexical.closeFile();
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "There is an error closing the file", "Error", JOptionPane.ERROR_MESSAGE);
				}
				SyntaxAnalysis syntax = new SyntaxAnalysis();
				
				try{
				syntax.openFile("C:\\Users\\Des-Angelo\\Documents\\Year 4\\Sem2\\Design of Programming Languages\\Project\\MusicCompileMagic\\lexical.txt");
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Error with opening file", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				try{
				syntax.readFile();
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Error with reading file", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				try{
				syntax.closeFile();
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Error with closing file", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				try {
					CodeOptimizer.redundanceCheck("C:\\Users\\Des-Angelo\\Documents\\Year 4\\Sem2\\Design of Programming Languages\\Project\\MusicCompileMagic\\lexical.txt");
					CodeOptimizer.optimize("C:\\Users\\Des-Angelo\\Documents\\Year 4\\Sem2\\Design of Programming Languages\\Project\\MusicCompileMagic\\Optimize1.txt");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Optimizer Error: File not found", "Error", JOptionPane.ERROR_MESSAGE);
					Logger.getLogger(MusicCompileMagicUI.class.getName()).log(Level.SEVERE, null, e1);
				}
				
				TagStripper stripper = new TagStripper("C:\\Users\\Des-Angelo\\Documents\\Year 4\\Sem2\\Design of Programming Languages\\Project\\MusicCompileMagic\\lexical.txt");
				stripper.stripTagLine();
				
				try {
					SemanticAnalysis.alphaCheck("C:\\Users\\Des-Angelo\\Documents\\Year 4\\Sem2\\Design of Programming Languages\\Project\\MusicCompileMagic\\stripped.txt");
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "Semantic Error: File not found", "Error", JOptionPane.ERROR_MESSAGE);
					
				}
				
				InterCodeGenerator.readFile("C:\\Users\\Des-Angelo\\Documents\\Year 4\\Sem2\\Design of Programming Languages\\Project\\MusicCompileMagic\\stripped.txt");
				
				try {
					CodeOptimizer.optimizeAscii("C:\\Users\\Des-Angelo\\Documents\\Year 4\\Sem2\\Design of Programming Languages\\Project\\MusicCompileMagic\\ASCII Version.txt");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					CodeGenerator.toBinary("C:\\Users\\Des-Angelo\\Documents\\Year 4\\Sem2\\Design of Programming Languages\\Project\\MusicCompileMagic\\Optimize2.txt");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//TEXT TO SPEECH
				
		        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		        try {
		            reader = new Scanner(new File("C:\\Users\\Des-Angelo\\Documents\\Year 4\\Sem2\\Design of Programming Languages\\Project\\MusicCompileMagic\\Stripped.txt"));
		        } catch (FileNotFoundException ex) {
		            Logger.getLogger(MusicCompileMagicUI.class.getName()).log(Level.SEVERE, null, ex);
		            JOptionPane.showMessageDialog(null, "Text to Speech Error: File not found", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		        try{
		        Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
		        Synthesizer  synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
		        synthesizer.allocate();
		        synthesizer.resume();
		        
		        while(reader.hasNext())
		        {
		            String line = reader.nextLine();
		            synthesizer.speakPlainText(line, null);
		        }
		        reader.close();
		        
		        synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
		        synthesizer.deallocate();
		        } catch (EngineException ex) {
		            Logger.getLogger(MusicCompileMagicUI.class.getName()).log(Level.SEVERE, null, ex);
		        } catch (AudioException ex) {
		            Logger.getLogger(MusicCompileMagicUI.class.getName()).log(Level.SEVERE, null, ex);
		        } catch (EngineStateError ex) {
		            Logger.getLogger(MusicCompileMagicUI.class.getName()).log(Level.SEVERE, null, ex);
		        } catch (InterruptedException ex) {
		            Logger.getLogger(MusicCompileMagicUI.class.getName()).log(Level.SEVERE, null, ex);
		        } catch (IllegalArgumentException ex) {
		            Logger.getLogger(MusicCompileMagicUI.class.getName()).log(Level.SEVERE, null, ex);
		        }
			}
		});
	}
}
