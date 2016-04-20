
import javax.swing.*;     // Para os Frames
import java.awt.*;        // Para a interface grafica
import java.awt.event.*;  // Para os eventos
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Font;

class Notepad extends JFrame implements ActionListener{
	
	private static JPanel Panel = new JPanel();
	JPanel Panel1 = new JPanel();
	JPanel Panel2 = new JPanel();
	JPanel Panel3 = new JPanel();
	
	private JTextField textField = new JTextField(30);
	private JTextArea textArea = new JTextArea(20,40);
	
	private JTextField textField2 = new JTextField(3);
	private JTextField textField3 = new JTextField(6);
	
	private JMenuBar MenuBar = new JMenuBar ();
	
	private JMenu fileMenu = new JMenu ("File");
	
	private JMenuItem item_Salvar = new JMenuItem("Salvar");
	
	private Lista_Anotacoes Lista = new Lista_Anotacoes ();
	
	private JButton Botao = new JButton ("Ordenar por Data");
	
	private JLabel Label0 = new JLabel("Anotacoes");
	private JLabel Label1 = new JLabel("Anotacao:");
	private JLabel Label2 = new JLabel("Escolha uma anotacao para excluir:");
	private JLabel Label3 = new JLabel("Troque uma anotacao(nº,subst):");
//	private ArrayList<Anotacao> Lista = new ArrayList<Anotacao>();
	
	public Notepad ()
	{
		
		super("Notepad");
		Panel.setSize(300, 300);
		textField.setFont(new Font(("Serif"), Font.BOLD, 16));
		textArea.setFont(new Font(("Serif"), Font.BOLD, 16));
		textField2.setFont(new Font(("Serif"), Font.BOLD, 16));
		textField3.setFont(new Font(("Serif"), Font.BOLD, 16));
		
		textArea.setEditable(false);
        
		Panel1.add(Label0);
		Panel1.add(textArea);
		Panel1.add(Botao);
		
		Panel2.add(Label1);
		Panel2.add(textField);
		Panel2.add(Label2);
		Panel2.add(textField2);
		Panel2.add(Label3);
		Panel2.add(textField3);
		
		add(Panel1, BorderLayout.NORTH);
		add(Panel2, BorderLayout.SOUTH);
		//Label1.setLabelFor(textField);
		//Label1.setLabelFor(textField2);
		//this.add(MenuBar);
        this.setJMenuBar(MenuBar);
        
		MenuBar.add(fileMenu);
		
		fileMenu.add(item_Salvar);
		
		
		textField.addActionListener(this);
		textField2.addActionListener(this);
		textField3.addActionListener(this);
		
		Botao.addActionListener(this);
		
		item_Salvar.addActionListener(this);
		
	}
	
	public void actionPerformed (ActionEvent e)
	{
		Object source = e.getSource();
		int n;
		if (source == textField)
		{
			Anotacao Lido = new Anotacao ();
			String input = textField.getText();
			
			Lido.Escrever_Texto(input);
			Lido.Escrever_Data();
			Lista.add(Lido);
			
			textArea.append("Anotacao " + Lista.size() + ": \"" + Lista.get(Lista.size() - 1).getTexto() + "\", anotado em " + Lista.get(Lista.size() - 1).getData()+"\n");
			textField.selectAll();
		}
		
		else if (source == textField2)
		{
			n = Integer.parseInt(textField2.getText());
			Remover(n - 1);
		}
		else if (source == textField3)
		{
			Anotacao Edicao = new Anotacao();
			String input2 = textField3.getText();
			String numero;
			if (input2.indexOf(',') > 0)
			{
				numero = input2.substring(0, input2.indexOf(',') );
				
				
				System.out.println("NUMERO eh:" + numero + "indexOf(',') = " + input2.indexOf(',') );
				
				input2 = input2.substring(input2.indexOf(',') + 1);
				
				Edicao.Escrever_Texto(input2);
				Edicao.Escrever_Data();
						
				Editar(Integer.parseInt(numero) - 1, Edicao);
				
				Show_All();
				
			}
			else  System.out.println("ERRO: Entrada indevida!");
		}
		else if (source == Botao)
		{
			Data_Crescente ();
			
		}
		else if (source == item_Salvar)
		{
			try
			{
				Salvar("saida.txt");
			}
			catch (IOException ev)
			{
				;
			}
		}
	}
	
	
	private void Anotar_Posicao_n (int n, String input)
	{
		Anotacao Lido = new Anotacao ();
		Lido.Escrever_Texto(input);
		Lido.Escrever_Data();
		Lista.add(Lido);
	}
	
	private void Show_All ()
	{
		for (int i=0; i< Lista.size(); i++)
			textArea.append("Anotacao " + (i + 1)+ ": \"" + Lista.get(i).getTexto() + "\", anotado em " + Lista.get(i).getData() + "\n");
	}
	
	public void Remover (int n)                //Remove a (n+1)ésima anotação: 0 é a primeira anotação.
	{
		textArea.setText("");
		if (n < Lista.size())
		{
			Lista.remove(n);
			Show_All();	
		}
		else textArea.append("ERRO");
	}
	
	public void Editar (int n, Anotacao Anotacao_Substituta)    //Remove a (n+1)ésima anotação, substituindo-a por Anotacao_Substituta
	{
		textArea.setText("");
		if (n < Lista.size())
		{
			Lista.remove(n);
			Lista.add(n, Anotacao_Substituta);		
		}
		else textArea.append("ERRO");
	}
	
	public void Data_Crescente ()
	{
		Lista.Ordenar_Por_Data();
		textArea.setText("");
		Show_All();
	}
	
	public void Salvar (String Nome_Arquivo) throws IOException
	{
		File arquivo = new File (Nome_Arquivo);
		arquivo.createNewFile();
		FileWriter Escritor = null;
		try
		{
			Escritor = new FileWriter(arquivo);
			
			for (int i=0; i< Lista.size(); i++)
				Escritor.write("Anotacao " + (i + 1) + ": \r\n" + Lista.get(i).getNome()  + "\r\n" + Lista.get(i).getTexto() + "\r\n" + Lista.get(i).getMetaTag() + "\r\n" +  Lista.get(i).getData() + "\r\n");
			
			Escritor.flush();
			Escritor.close();
		}
		catch (IOException e)
		{
		    JOptionPane.showMessageDialog(this, "ERRO no arquivo de SAIDA!");
		}
		
	}
	
	public static void main(String[] args) {
		
		Notepad bloco = new Notepad ();
		
		bloco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bloco.add(Panel);
		bloco.pack();
		bloco.setVisible(true);
		
	}
}